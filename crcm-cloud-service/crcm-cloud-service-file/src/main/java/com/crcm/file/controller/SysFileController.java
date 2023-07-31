package com.crcm.file.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpStatus;
import com.crcm.cloud.start.file.domain.BigDataUploadResult;
import com.crcm.cloud.start.file.domain.FileVo;
import com.crcm.cloud.start.file.domain.UploadResult;
import com.crcm.cloud.start.file.minio.constants.MinioBucket;
import com.crcm.cloud.start.file.minio.dto.FileUploadDTO;
import com.crcm.cloud.start.file.minio.vo.UploadVO;
import com.crcm.cloud.start.file.service.ISysFileService;
import com.crcm.cloud.start.file.service.MinioSysFileServiceImpl;
import com.crcm.cloud.start.sso.annation.Inner;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.Assert;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.FileUtil;
import com.crcm.core.utils.QRCodeUtil;
import com.crcm.file.model.entity.Attachment;
import com.crcm.file.model.vo.AttachmentsSimpleVO;
import com.crcm.file.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName SysFileController
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/
@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Api(value = "minio", tags = "上传下载模块")
public class SysFileController {
    private final AttachmentService attachmentService;
    private final HttpServletResponse response;

    @Resource(type = MinioSysFileServiceImpl.class)
    private ISysFileService sysFileService;


    /**
     * 上传文件
     * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
     *
     * @param files 资源
     * @return R(bucketName, filename)
     */
    @Inner
    @PostMapping("/minioUpload")
    @ApiOperation(value = "批量上传文件", notes = "批量上传文件", hidden = true)
    public RestResult<List<FileVo>> minioUpload(@RequestParam("files") MultipartFile[] files, FileUploadDTO dto) {
        return uploads(files, dto);
    }

    /**
     * 上传文件
     * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
     *
     * @param files 资源
     * @return R(bucketName, filename)
     */
    @PostMapping("/upload")
    @ApiOperation(value = "批量上传文件", notes = "批量上传文件")
    public RestResult<List<FileVo>> uploads(@RequestParam("files") MultipartFile[] files, FileUploadDTO dto) {
        if (files == null || files.length == 0) {
            throw new CustomException(ResultCode.UPLOAD_NULL_FILE.code, ResultCode.UPLOAD_NULL_FILE.msg);
        }
        MinioBucket type = MinioBucket.getPathByType(dto.getType());
        if (Objects.isNull(type)) {
            throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, ResultCode.UPLOAD_FILE_FAILED.msg);
        }
        dto.setSavePath(type.getPath());
        ArrayList<FileVo> fileVos = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                FileVo fileVo = new FileVo();
                if (StringUtils.isBlank(file.getOriginalFilename())) {
                    throw new CustomException(ResultCode.UPLOAD_FILE_NAME_NULL.code, ResultCode.UPLOAD_FILE_NAME_NULL.msg);
                }
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String savePath = this.formatSavePath(dto.getSavePath());
                String fileName = file.getOriginalFilename();
                UploadResult uploadResult = sysFileService.uploadFile(file, savePath);
                Attachment attachment = new Attachment();
                attachment.setFileName(fileName);
                attachment.setBucketName(uploadResult.getBucketName());
                attachment.setSuffix(ext);
                attachment.setPath(savePath);
                attachment.setSaveName(uploadResult.getSaveFileName());
                try {
                    attachment.setMd5(DigestUtils.md5Hex(file.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachment.setSize(cn.hutool.core.io.FileUtil.readableFileSize(file.getSize()));
                attachment.setUploadMode(SystemConstant.UPLOAD_MODE.MINIO);
                attachment.setUploadStatus(SystemConstant.UPLOAD_STATUS.UPLOADED);
                attachmentService.saveAttachments(attachment);
                fileVo.setId(attachment.getId());
                fileVo.setPath(sysFileService.getFileUri(uploadResult.getFileUri())
                );
                fileVo.setFileName(file.getOriginalFilename());
                fileVos.add(fileVo);
            } catch (Exception e) {
                log.error("上传失败", e);
                return RestResult.failed(HttpStatus.HTTP_INTERNAL_ERROR, "文件上传失败");
            }

        }
        return RestResult.succeed(fileVos);
    }

    private String formatSavePath(String savePath) {
        StringBuilder path = new StringBuilder();
        if (StringUtils.isNotBlank(savePath)) {
            String filePath = savePath.startsWith("/") ? savePath.replaceFirst("/", "") : savePath;
            filePath = filePath.endsWith("/") ? filePath : filePath + "/";
            path.append(filePath);
        }
        return path.toString();
    }


    /**
     * 下载文件
     *
     * @param id 附件表ID
     */

    @GetMapping("/download/{id}")
    @ApiOperation(value = "下载文件", notes = "下载文件")
    public RestResult<String> downloadById(@PathVariable String id) {
        Attachment att = attachmentService.findById(id);
        if (att != null) {
            try (InputStream inputStream = sysFileService.getFile(att.getSaveName(), att.getPath())) {
                FileUtil.download(inputStream, response, att.getFileFullName());
                return null;
            } catch (Exception e) {
                log.error("文件下载异常", e);
                return RestResult.failed(ResultCode.FILE_ABNORMAL_READING);
            }
        }
        return RestResult.failed(HttpStatus.HTTP_NOT_FOUND, "文件读取异常!");
    }

    /**
     * 获取文件
     *
     * @param id 附件表ID
     */

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据ID获取文件", notes = "获取文件")
    public void getFileById(@PathVariable String id) {
        Attachment att = attachmentService.findById(id);
        if (att != null) {
            try (InputStream inputStream = sysFileService.getFile(att.getSaveName(), att.getPath())) {
                IoUtil.copy(inputStream, response.getOutputStream());
            } catch (Exception e) {
                log.error("文件读取异常", e);
                throw new CustomException(ResultCode.FILE_ABNORMAL_READING);
            }
        } else {
            throw new CustomException(ResultCode.FILE_NO_FOUND);
        }
    }


    /**
     * 获取文件
     *
     * @param fileName 文件名称
     */

    @GetMapping("/get/{fileName}")
    @ApiOperation(value = "根据文件名获取文件", notes = "获取文件")
    public RestResult<String> file(@PathVariable String fileName) {
        try (InputStream inputStream = sysFileService.getFile(fileName, null)) {
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(inputStream, response.getOutputStream());
            return RestResult.succeed("文件读取成功！");
        } catch (Exception e) {
            log.error("文件读取异常", e);
            return RestResult.failed(ResultCode.FILE_ABNORMAL_READING);
        }
    }

    /**
     * 初始化大文件上传
     *
     * @param uploadVO 文件
     * @return 结果
     */
    @SneakyThrows
    @PostMapping("init-chunk-upload")
    @ApiOperation(value = "初始化大文件上传", notes = "初始化大文件上传")
    public RestResult<BigDataUploadResult> initChunkUpload(@RequestBody UploadVO uploadVO) {
        Attachment attachment = attachmentService.findByMd5(uploadVO.getFileMd5());
        BigDataUploadResult uploadResult = BigDataUploadResult.builder().build();
        String savePath = uploadVO.getSavePath();
        if (StringUtils.isNotBlank(uploadVO.getSavePath())) {
            savePath = savePath.startsWith("/") ? savePath.replaceFirst("/", "") : savePath;
        }
        //校验文件md5，该文件是否上传过
        if (attachment != null) {
            //秒传
            if (SystemConstant.UPLOAD_STATUS.UPLOADED.equals(attachment.getUploadStatus())) {
                uploadResult.setSkipUpload(true);
                uploadResult.setMessage("秒传");
                FileVo fileVo = new FileVo();
                fileVo.setId(attachment.getId());
                fileVo.setPath(sysFileService.getFileUri(attachment.getSaveName(), attachment.getPath()));
                fileVo.setFileName(attachment.getFileFullName());
                uploadResult.setFileData(fileVo);
                return RestResult.succeed(uploadResult);
            }
        }

        // 断点续传或初始化上传
        uploadResult = sysFileService.uploadBigFile(uploadVO.getFileMd5(), uploadVO.getChunkCount());
        //向数据库中记录该文件的上传信息
        Attachment att = new Attachment();
        att.setUploadStatus(SystemConstant.UPLOAD_STATUS.PART_UPLOAD);
        att.setMd5(uploadVO.getFileMd5());
        String fileName = uploadVO.getFileName();
        att.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
        att.setPath(savePath);
        att.setSize(cn.hutool.core.io.FileUtil.readableFileSize(uploadVO.getSize()));
        att.setUploadMode(SystemConstant.UPLOAD_MODE.MINIO);
        att.setBucketName(uploadVO.getBucketName());
        att.setChunkCount(uploadVO.getChunkCount());
        attachmentService.save(att);
        return RestResult.succeed(uploadResult);
    }

    /**
     * 合并文件并返回文件信息
     *
     * @param uploadVO 合并文件
     * @return 文件信息
     */
    @SneakyThrows
    @PostMapping("compose-file")
    @ApiOperation(value = "合并文件并返回文件信息", notes = "合并文件并返回文件信息")
    public RestResult<Object> composeFile(@RequestBody UploadVO uploadVO) {
        //自定义文件名称
        String fileName = uploadVO.getFileName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        fileName = df.format(LocalDateTime.now()) + suffix;
        //获取数据库里记录的文件信息，修改数据并返回文件信息
        Attachment attachment = attachmentService.findByMd5(uploadVO.getFileMd5());
        Assert.isNull(attachment, "文件不存在！");
        //合并文件
        if (sysFileService.composeFile(uploadVO.getFileMd5(), fileName, uploadVO.getSavePath())) {
            //获取文件访问外链(7天之后过期)
            String url = sysFileService.getFileUri(fileName, uploadVO.getSavePath());
            attachment.setUploadStatus(SystemConstant.UPLOAD_STATUS.UPLOADED);
            attachment.setSaveName(fileName);
            attachment.setPath(uploadVO.getSavePath());
            attachment.setBucketName(uploadVO.getBucketName());
            attachment.setSuffix(suffix);
            attachmentService.updateById(attachment);
            FileVo fileVo = new FileVo();
            fileVo.setId(attachment.getId());
            fileVo.setPath(url);
            fileVo.setFileName(attachment.getFileFullName());
            return RestResult.succeed(fileVo);
        }
        return RestResult.failed(ResultCode.FILE_MERGE_FAILED);
    }


    @ApiOperation("获取下载二维码")
    @GetMapping("/qrCode")
    @ApiImplicitParam(name = "附件id", value = "id", paramType = "query", required = true)
    public void getQrCode(@ApiIgnore String id) {
        Attachment att = attachmentService.findById(id);
        if (att == null) {
            throw new CustomException(ResultCode.FILE_NO_FOUND.code, ResultCode.FILE_NO_FOUND.msg);
        }
        try {
            String fileUrl = sysFileService.getFileUri(att.getSaveName(), att.getPath());
            QRCodeUtil.encode(fileUrl, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCode.FILE_ABNORMAL_READING);
        }
    }

    @ApiOperation("获取附件地址")
    @GetMapping("/getFilePath")
    @ApiImplicitParam(name = "附件id", value = "id", paramType = "query", required = true)
    public RestResult<AttachmentsSimpleVO> getFilePath(@ApiIgnore String id) {
        AttachmentsSimpleVO simpleVO = new AttachmentsSimpleVO();
        Attachment att = attachmentService.findById(id);
        if (att == null) {
            throw new CustomException(ResultCode.FILE_NO_FOUND.code, ResultCode.FILE_NO_FOUND.msg);
        }
        String fileUrl;
        try {
            fileUrl = sysFileService.getFileUri(att.getSaveName(), att.getPath());
            simpleVO.setFileName(att.getFileName());
            simpleVO.setPath(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCode.FILE_ABNORMAL_READING);
        }
        return RestResult.succeed(simpleVO);
    }

    @ApiOperation("获取附件地址")
    @GetMapping("/getFileByPath")
    @ApiImplicitParam(name = "附件地址", value = "path", paramType = "query", required = true)
    public RestResult<String> getFileByPath(@ApiIgnore String path) {
        String fileUrl;
        try {
            fileUrl = sysFileService.getFileUri(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCode.FILE_ABNORMAL_READING);
        }
        return RestResult.succeed(fileUrl);
    }


}
