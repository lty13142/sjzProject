package com.sjz.partbuilding.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import com.sjz.partbuilding.enums.YTBusinessStatusEnum;
import com.sjz.partbuilding.mapper.AttachmentsMapper;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.vo.AttachmentsVo;
import com.sjz.partbuilding.service.AttachmentsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zzyt
 * @version 1.0
 * @date 2020/7/20 11:26
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AttachmentsServiceImpl extends ServiceImpl<AttachmentsMapper, Attachments> implements AttachmentsService {

    @Autowired
    private RemoteFileService fileService;

    /**
     * 预览服务器
     */
//    @Value("${file.preview.host}")
//    private String previewHost;
//
//    /**
//     * 预览端口
//     */
//    @Value("${file.preview.port}")
//    private int previewPort;
//
//    @Value("${zzyt.preview.folder}")
//    private String folder;
//
//    @Value("${zzyt.preview.externalHost}")
//    private String externalHost;
//
//    /**
//     * minIo文件服务器ip
//     */
//    @Value("${minio.url}")
//    private String minIoUrl;
//
//    /**
//     * minIo文件服务器文件名
//     */
//    @Value("${minio.bucket-name}")
//    private String bucketName;

    /**
     * 域名路径
     * 替换minIoUrl
     */
    /*@Value("${zzyt.minioPath}")
    private String domainNamePath;*/


    @Override
    public int saveAttachments(Attachments t) {
        return this.baseMapper.insert(t);
    }

    @Override
    public int updateAttachments(Attachments t) {
        return this.baseMapper.updateById(t);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public Attachments findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public IPage<Attachments> findPage(Page<Attachments> pageT, Attachments t) {
        IPage<Attachments> pageAttachments = this.baseMapper.selectPage(pageT, null);
        return pageAttachments;
    }

    @Override
    public List<AttachmentsVo> findAttachments(AttachmentsVo vo) {
        if (StringUtils.isBlank(vo.getId()) && StringUtils.isBlank(vo.getIds())) {
            throw new CustomException(YTBusinessStatusEnum.FIND_WITH_NULL_ID.code, YTBusinessStatusEnum.FIND_WITH_NULL_ID.desc);
        }
        QueryWrapper<Attachments> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(vo.getIds())) {
            wrapper.in("id", Arrays.asList(vo.getIds().split(",")));
        }
        if (StringUtils.isNotBlank(vo.getId())) {
            wrapper.eq("id", vo.getId());
        }
        List<Attachments> attachments = this.baseMapper.selectList(wrapper);
        ArrayList<AttachmentsVo> vos = new ArrayList<>();
        for (Attachments attachment : attachments) {
            AttachmentsVo attachmentsVo = new AttachmentsVo();
            BeanUtils.copyProperties(attachment, attachmentsVo);
            if (String.valueOf(SystemConstant.UPLOAD_MODE.FTP).equals(attachment.getUploadMode())) {
                String filePath = getFilePath(attachment.getPath(), attachment.getSaveName());
                attachmentsVo.setPath(filePath);

            }
            vos.add(attachmentsVo);
        }
        return vos;
    }

    @Override
    public String findFilePath(String attachmentId) {
        if (StringUtils.isNotBlank(attachmentId)) {
            Attachments attachment = this.baseMapper.selectById(attachmentId);
            if (attachment != null) {
                return getFilePath(attachment.getPath(), attachment.getSaveName());
            }
        }
        return null;
    }

    /**
     * ftp
     *
     * @param remotePath   文件路径
     * @param fileSaveName 存储文件名
     * @return
     */
    @Override
    public String getFilePath(String remotePath, String fileSaveName) {
        boolean flag = remotePath.startsWith("/");
        if (StringUtils.isNotBlank(remotePath) && !flag) {
            remotePath = "/" + remotePath;
        }
//        return "http://" + previewHost + ":" + previewPort + "/" + folder + remotePath + "/" + fileSaveName;
        return null;
    }

    @Override
    public List<Attachments> selectCustomBatchById(String[] split) {
        List<Attachments> list = this.baseMapper.selectCustomBatchByIds(split);

        if (CollUtil.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        list.parallelStream().forEach(
                a -> {
                    String filePath = this.getFilePath(a.getPath(), a.getSaveName());
                    a.setPath(filePath);
                }
        );
        return list;
    }

    /**
     * 根据attId获取minIO附件信息
     *
     * @param attId 附件id
     * @return
     */
    @Override
    public Attachments getMinIoByAttId(String attId) {
        LambdaQueryWrapper<Attachments> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据id查询
        lambdaQueryWrapper.eq(Attachments::getId, attId);
        //查询字段
        lambdaQueryWrapper.select(Attachments::getId, Attachments::getFileName, Attachments::getFileFullName, Attachments::getMinioPath,Attachments::getSaveName);
        Attachments attachments = this.baseMapper.selectOne(lambdaQueryWrapper);
        //不为空则组装活minIo访问路径
        if (attachments != null && !"".equals(attachments)) {
//            attachments.setMinioPath(minIoUrl + "/" + bucketName + "/" + attachments.getSaveName());
        }
        return attachments;
    }

    /**
     * 通过attIds获取minIO附件信息集
     *
     * @param attIds 附件ids
     * @return
     */
    @Override
    public List<Attachments> getMinIoListByAttIds(String attIds) {
        //为空则返回空集合
        if (attIds == null||",".equals(attIds)) {
            return new ArrayList<>();
        }
        //获取附件数组
        String[] attArrays = attIds.split(",");
        LambdaQueryWrapper<Attachments> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据ids查询
        lambdaQueryWrapper.in(Attachments::getId, attArrays);
        //查询字段
        lambdaQueryWrapper.select(Attachments::getId, Attachments::getFileName, Attachments::getFileFullName, Attachments::getMinioPath,Attachments::getSaveName);
        //排序
        lambdaQueryWrapper.orderByAsc(Attachments::getCreateTime);
        //取到返回结果集
        List<Attachments> list = this.baseMapper.selectList(lambdaQueryWrapper);
        //有值情况下组装活minIo访问路径
        if (list.size() > 0) {
            for (Attachments attachments : list) {
//                attachments.setMinioPath(minIoUrl + "/" + bucketName + "/" + attachments.getSaveName());
            }
        }
        return list;

    }

    @Override
    public List<Map<String, String>> getAttachmentListByIds(String attIds) {
        RestResult<List<Map<String, String>>> result = fileService.getAttachmentListByIds(attIds, AuthConstants.FROM_IN);
        if(result!=null){
            return result.getData();
        }
        return null;
    }

    @Override
    public String getFilePathById(String attId) {
        String path = "";
        RestResult<List<Map<String, String>>> result = fileService.getAttachmentListByIds(attId, AuthConstants.FROM_IN);
        if(result!=null){
            List<Map<String, String>> list= result.getData();
            if(list.size()>0){
                path = list.get(0).get("path");
            }
        }
        return path;
    }

}
