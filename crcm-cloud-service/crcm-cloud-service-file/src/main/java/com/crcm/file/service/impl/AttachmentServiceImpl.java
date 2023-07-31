package com.crcm.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.file.domain.FileVo;
import com.crcm.cloud.start.file.service.ISysFileService;
import com.crcm.cloud.start.file.service.MinioSysFileServiceImpl;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.FileUtil;
import com.crcm.core.utils.HttpUtil;
import com.crcm.file.mapper.AttachmentMapper;
import com.crcm.file.model.entity.Attachment;
import com.crcm.file.model.vo.AttachmentsSimpleVO;
import com.crcm.file.model.vo.AttachmentsVO;
import com.crcm.file.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Slf4j
@Service
@Transactional
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {

    @Resource(type = MinioSysFileServiceImpl.class)
    private ISysFileService sysFileService;

    @Override
    public int saveAttachments(Attachment t) {
        return this.baseMapper.insert(t);
    }

    @Override
    public int updateAttachments(Attachment t) {
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
    public Attachment findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public PageT<Attachment> findPage(PageT<Attachment> pageT, Attachment t) {
        LambdaQueryWrapper<Attachment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(t.getFileName()), Attachment::getFileName, t.getFileName())
                .eq(StringUtils.isNotBlank(t.getSuffix()), Attachment::getSuffix, t.getSuffix());
        return this.page(pageT, wrapper);
    }

    @Override
    public List<AttachmentsVO> findAttachments(AttachmentsVO vo) {
        if (StringUtils.isBlank(vo.getId()) && StringUtils.isBlank(vo.getIds())) {
            throw new CustomException(ResultCode.FIND_WITH_NULL_ID.code, ResultCode.FIND_WITH_NULL_ID.msg);
        }
        LambdaQueryWrapper<Attachment> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(StringUtils.isNotBlank(vo.getIds()), Attachment::getId, Arrays.asList(vo.getIds().split(",")))
                .eq(StringUtils.isNotBlank(vo.getId()), Attachment::getId, vo.getId());
        List<Attachment> attachments = this.baseMapper.selectList(wrapper);
        ArrayList<AttachmentsVO> vos = new ArrayList<>();
        for (Attachment attachment : attachments) {
            AttachmentsVO attachmentsVo = new AttachmentsVO();
            BeanUtil.copyProperties(attachment, attachmentsVo);
            try {
                attachmentsVo.setPath(getFileUrl(attachment));
            } catch (Exception e) {
                e.printStackTrace();
            }
            vos.add(attachmentsVo);
        }
        return vos;
    }

    @Override
    @Cacheable(value = {SystemBaseConstants.BASE_PATH + ":attachments"}, key = "#attachmentId")
    public String findFilePath(String attachmentId) {
        if (StringUtils.isNotBlank(attachmentId)) {
            Attachment attachment = this.baseMapper.selectById(attachmentId);
            if (attachment != null) {
                try {
                    return getFileUrl(attachment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<String> findBitchFilePath(List<String> attachmentIds) {
        if (attachmentIds == null) {
            attachmentIds = new ArrayList<>();
        }
        LambdaQueryWrapper<Attachment> queryWrapper = new LambdaQueryWrapper<Attachment>()
                .in(Attachment::getId, attachmentIds);
        List<String> paths = new ArrayList<>();
        List<Attachment> attachments = this.baseMapper.selectList(queryWrapper);
        if (attachments != null) {
            for (Attachment attachment : attachments) {
                try {
                    paths.add(getFileUrl(attachment));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return paths;
    }

    @Override
    public String getFileUrl(Attachment att) throws Exception {
        return sysFileService.getFileUri(att.getSaveName(), att.getPath());
    }

    @Override
    public Attachment findByMd5(String fileMd5) {
        LambdaQueryWrapper<Attachment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attachment::getMd5, fileMd5);
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据attId获取minIO附件信息
     *
     * @param attId 附件id
     * @return
     */
    @Override
    public Attachment getMinIoByAttId(String attId) {
        LambdaQueryWrapper<Attachment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据id查询
        lambdaQueryWrapper.eq(Attachment::getId, attId);
        //查询字段
        lambdaQueryWrapper.select(Attachment::getId, Attachment::getFileName, Attachment::getFileFullName, Attachment::getPath, Attachment::getSaveName);
        Attachment att = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (att != null) {
            try {
                att.setPath(sysFileService.getFileUri(att.getSaveName(), att.getPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return att;
    }

    /**
     * 通过attIds获取minIO附件信息集
     *
     * @param attIds 附件ids
     * @return
     */
    @Override
    public List<Attachment> getMinIoListByAttIds(String attIds) {
        //为空则返回空集合
        if (attIds == null) {
            return new ArrayList<>();
        }
        //获取附件数组
        String[] attArrays = attIds.split(",");
        LambdaQueryWrapper<Attachment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据ids查询
        lambdaQueryWrapper.in(Attachment::getId, Arrays.asList(attArrays));
        //查询字段
        lambdaQueryWrapper.select(Attachment::getId, Attachment::getFileName, Attachment::getFileFullName, Attachment::getPath, Attachment::getSaveName);
        lambdaQueryWrapper.orderByAsc(Attachment::getCreateTime);
        //取到返回结果集
        List<Attachment> list = this.baseMapper.selectList(lambdaQueryWrapper);
        //有值情况下组装活minIo访问路径
        if (list.size() > 0) {
            for (Attachment att : list) {
                if (att != null) {
                    try {
                        att.setPath(sysFileService.getFileUri(att.getSaveName(), att.getPath()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;

    }

    @Override
    public String uploadNetworkFile(String networkFile, String saveDir) {
        if (StringUtils.isBlank(networkFile)) {
            return "";
        }

        JSONObject parse = (JSONObject) JSONObject.parse(networkFile);
        String imgUrl = (String) parse.get("imgUrl");
        //获取文件名
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);

        //保存的图片路径
        String saveImg = "";
        InputStream inputStream = null;
        try {
            //获取网络文件的input流
            inputStream = FileUtil.downloadWebFile(imgUrl);
            //获取input流为MultipartFile
            MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
            MultipartFile[] files = {multipartFile};
            HashMap<String, Object> data = new HashMap<>();
            data.put("files", files);
            data.put("savePath", saveDir);
            JSONObject jsonObject = HttpUtil.doPost("/file/uploads", data);
            RestResult result = jsonObject.toJavaObject(RestResult.class);
            List<FileVo> fileVos = (List<FileVo>) result.getData();
            if (fileVos.size() > 0) {
                saveImg = fileVos.get(0).getFileName();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return saveImg;
    }

    @Override
    public List<AttachmentsSimpleVO> findFileListByIds(String ids) {
       List<AttachmentsSimpleVO>  attachmentsSimpleVOS=new ArrayList<>();
        List<String> split = StrUtil.split(ids, ",", 0, true, true);
        LambdaQueryWrapper<Attachment> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(Attachment::getId, split);
        List<Attachment> attachments = this.baseMapper.selectList(queryWrapper);
        attachments.stream().forEach(e->{
            AttachmentsSimpleVO simpleVO = new AttachmentsSimpleVO();
            String fileUrl;
            try {
                fileUrl = sysFileService.getFileUri(e.getSaveName(), e.getPath());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            simpleVO.setId(e.getId());
            simpleVO.setFileName(e.getFileName());
            simpleVO.setPath(fileUrl);
            attachmentsSimpleVOS.add(simpleVO);
        });
        return attachmentsSimpleVOS;
    }

    @Override
    public List<Map<String, String>> findAttachmentListByIds(String ids) {
        if (StringUtils.isBlank(ids) && StringUtils.isBlank(ids)) {
            throw new CustomException(ResultCode.FIND_WITH_NULL_ID.code, ResultCode.FIND_WITH_NULL_ID.msg);
        }
        LambdaQueryWrapper<Attachment> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(StringUtils.isNotBlank(ids), Attachment::getId, Arrays.asList(ids.split(",")));
        List<Attachment> attachments = this.baseMapper.selectList(wrapper);
        ArrayList<Map<String,String>> vos = new ArrayList<>();
        for (Attachment attachment : attachments) {
            Map<String,String> map = new HashMap<>();
            map.put("id",attachment.getId());
            map.put("fileName",attachment.getFileName());

            try {
                map.put("path",getFileUrl(attachment));
            } catch (Exception e) {
                e.printStackTrace();
            }
            vos.add(map);
        }
        return vos;
    }
}
