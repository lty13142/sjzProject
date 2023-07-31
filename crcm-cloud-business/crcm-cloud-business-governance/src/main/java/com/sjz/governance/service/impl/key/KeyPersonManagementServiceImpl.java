package com.sjz.governance.service.impl.key;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ZzyConstants;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import com.sjz.governance.mapper.key.KeyPersonManagementMapper;
import com.sjz.governance.model.dto.key.KeyPersonManagementDTO;
import com.sjz.governance.model.dto.zzy.FaceDeleteDTO;
import com.sjz.governance.model.dto.zzy.FaceRegisterDTO;
import com.sjz.governance.model.vo.key.KeyPersonManagementVO;
import com.sjz.governance.model.vo.zzy.FaceRegisterImageResultVO;
import com.sjz.governance.model.vo.zzy.FaceRegisterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sjz.governance.model.entity.key.KeyPersonManagement;
import com.sjz.governance.service.key.KeyPersonManagementService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;


/**
 * 综合治理_重点人员管理Service业务层处理
 * 
 * @author pengl
 * @date 2023-04-03
 */
@Slf4j
@Service
@Transactional
public class KeyPersonManagementServiceImpl extends ServiceImpl<KeyPersonManagementMapper, KeyPersonManagement> implements KeyPersonManagementService {

    @Resource
    private RemoteFileService remoteFileService;

    /**
     * 综合治理_创建人脸数据库
     * @param dto
     * @return
     */
    @Override
    public String createFaceDatabase(KeyPersonManagementDTO dto) {
        String url = ZzyConstants.ZZY_URL + ZzyConstants.CREATE_FACE_DATABASE_URI;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("database_name",ZzyConstants.FACE_REGISTER_DATABASE);
        paramMap.put("remarks",ZzyConstants.FACE_REGISTER_DATABASE);
        //链式构建请求
        String result = HttpRequest.post(url)
                .header(Header.AUTHORIZATION, ZzyConstants.ZZY_AK + ":" + ZzyConstants.ZZY_SK)
                .body(JSON.toJSONString(paramMap))
                .timeout(20000)
                .execute().body();
        return JSON.parseObject(result).getString("message");
    }

    /**
     * 新增综合治理_重点人员管理
     * 
     * @param dto 综合治理_重点人员管理
     * @return 结果
     */
    @Override
    public boolean saveKeyPersonManagement(KeyPersonManagementDTO dto) {

        //新增
        boolean save = this.save(dto);

        //人脸注册
        FaceRegisterVO faceRegisterVO = faceRegister(dto);

        //保存人脸注册的id
        KeyPersonManagement keyPersonManagement = this.getById(dto.getId());
        keyPersonManagement.setFaceRegisterPersonId(faceRegisterVO.getFace_person_id());
        keyPersonManagement.setFaceRegisterDatabaseId(faceRegisterVO.getFace_database_id());
        this.updateById(keyPersonManagement);

        return save;
    }

    private FaceRegisterVO faceRegister(KeyPersonManagementDTO dto) {
        //先删除原有人脸注册数据
        faceDelete(dto);

        //人脸注册
        FaceRegisterVO faceRegisterVO = new FaceRegisterVO();
        log.info("人脸注册图片附件id：{}",dto.getFaceUrl());
        if (StringUtils.isNotBlank(dto.getFaceUrl())){
            String[] fileIdArray = dto.getFaceUrl().split(",");
            if (fileIdArray.length > 0){
                ArrayList<String> images = new ArrayList<>();
                for (String id : fileIdArray) {
                    RestResult<String> res = remoteFileService.getFilePath(id, AuthConstants.FROM_IN);
                    if (res.isSuccess() && StringUtils.isNotBlank(res.getData())){
                        HttpResponse execute = HttpRequest.get(res.getData()).timeout(15000).execute();
                        byte[] resultBytes = execute.bodyBytes();
                        String base64 = Base64Utils.encodeToString(resultBytes);
                        images.add(base64);
                    }
                }
                String url = ZzyConstants.ZZY_URL + ZzyConstants.FACE_REGISTER;
                FaceRegisterDTO faceRegisterDTO = new FaceRegisterDTO();
                faceRegisterDTO.setImages(images);
                faceRegisterDTO.setImg_type("base64");
                faceRegisterDTO.setUser_code(dto.getId() + "");
                faceRegisterDTO.setDatabase_name(ZzyConstants.FACE_REGISTER_DATABASE);
                faceRegisterDTO.setGroup_name("default");
                faceRegisterDTO.setRemarks("");
                String result = HttpRequest.post(url)
                        .header(Header.AUTHORIZATION, ZzyConstants.ZZY_AK + ":" + ZzyConstants.ZZY_SK)
                        .body(JSON.toJSONString(faceRegisterDTO))
                        .timeout(20000)
                        .execute().body();
                JSONObject jsonObject = JSON.parseObject(result);
                log.info("人脸注册返回结果：{}",result);
                if ("200".equals(jsonObject.getString("code"))){
                    faceRegisterVO = jsonObject.getJSONObject("data").toJavaObject(FaceRegisterVO.class);
                    List<FaceRegisterImageResultVO> img_result = faceRegisterVO.getImg_result();
                    if (ObjectUtils.isNotNull(img_result)){
                        for (FaceRegisterImageResultVO faceRegisterImageResultVO : img_result) {
                            if (!faceRegisterImageResultVO.isSuccess()){
                                throw new CustomException("人脸图片不符合要求，请重新上传");
                            }else {
                                return faceRegisterVO;
                            }
                        }
                    }
                }else {
                    throw new CustomException("人脸图片不符合要求，请重新上传");
                }
            }
        }else {
            throw new CustomException("人脸图片不能为空");
        }
        return faceRegisterVO;
    }

    private void faceDelete(KeyPersonManagementDTO dto) {
        if (dto.getFaceRegisterPersonId() != null &&
                StringUtils.isNotBlank(dto.getFaceRegisterDatabaseId())){
            FaceDeleteDTO faceDeleteDTO = new FaceDeleteDTO();
            faceDeleteDTO.setPerson_id(dto.getFaceRegisterPersonId());
            faceDeleteDTO.setDatabase_id(dto.getFaceRegisterDatabaseId());
            String deleteUrl = ZzyConstants.ZZY_URL + ZzyConstants.DELETE_FACE_PERSON;
            String result = HttpRequest.post(deleteUrl)
                    .header(Header.AUTHORIZATION, ZzyConstants.ZZY_AK + ":" + ZzyConstants.ZZY_SK)
                    .body(JSON.toJSONString(faceDeleteDTO))
                    .timeout(20000)
                    .execute().body();
            log.info("人脸图片删除结果：{}", result);
        }
    }

    /**
     * 修改综合治理_重点人员管理
     * 
     * @param dto 综合治理_重点人员管理
     * @return 结果
     */
    @Override
    public boolean updateKeyPersonManagement(KeyPersonManagementDTO dto) {
        //人脸注册
        FaceRegisterVO faceRegisterVO = faceRegister(dto);
        dto.setFaceRegisterPersonId(faceRegisterVO.getFace_person_id());
        dto.setFaceRegisterDatabaseId(faceRegisterVO.getFace_database_id());
        //更新
        return this.updateById(dto);
    }

    /**
     * 删除综合治理_重点人员管理信息
     * 
     * @param id 综合治理_重点人员管理ID
     * @return 结果
     */
    @Override
    public boolean deleteKeyPersonManagement(Integer id) {
        return this.removeById(id);
    }

    /**
     * 查询综合治理_重点人员管理
     *
     * @param id 综合治理_重点人员管理ID
     * @return 综合治理_重点人员管理
     */
    @Override
    public KeyPersonManagement findKeyPersonManagementById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询综合治理_重点人员管理列表
     *
     * @param keyPersonManagement 综合治理_重点人员管理
     * @return 综合治理_重点人员管理
     */
    @Override
    public List<KeyPersonManagement> findKeyPersonManagementList(KeyPersonManagement keyPersonManagement) {
        LambdaQueryWrapper<KeyPersonManagement> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询综合治理_重点人员管理
     *
     * @param page 分页参数
     * @param dto 综合治理_重点人员管理
     * @return 综合治理_重点人员管理
     */
    @Override
    public PageT<KeyPersonManagementVO> findKeyPersonManagementPage(PageT<KeyPersonManagementVO> page, KeyPersonManagementDTO dto) {
        return this.baseMapper.findKeyPersonManagementPage(page,dto);
    }

}
