package com.crcm.cloud.start.file.service;

import com.crcm.cloud.start.file.domain.BigDataUploadResult;
import com.crcm.cloud.start.file.domain.ChunkUploadUrl;
import com.crcm.cloud.start.file.domain.UploadResult;
import com.crcm.cloud.start.file.minio.MinioConfig;
import com.crcm.cloud.start.file.minio.MinioUtil;
import com.crcm.cloud.start.file.minio.domain.MinioDeleteDTO;
import com.crcm.cloud.start.file.minio.event.MinioDeleteEvent;
import com.crcm.cloud.start.file.utils.FileUploadUtils;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.UtilFileValid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * @ClassName MinioSysFileServiceImpl
 * @Description minio 文件存储
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/
@Service
public class MinioSysFileServiceImpl implements ISysFileService {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private ApplicationContext applicationContext;

    // 默认分享链接过期时间 7天
    private static final int DEFAULT_FILE_EXPIRE_SECOND = 604800;


    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public UploadResult uploadFile(MultipartFile file, String savePath) throws Exception {
        String uploadVerify = UtilFileValid.uploadVerify(file);
        if(StringUtils.isNotBlank(uploadVerify)){
            throw new CustomException(uploadVerify);
        }
        String fileName = FileUploadUtils.extractFilename(file);
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        String fileUri = minioUtil.putObject(minioConfig.getBucketName(), savePath + fileName, file.getInputStream(), file.getSize(), file.getContentType());
        return UploadResult.builder().saveFileName(fileName).fileUri(fileUri).bucketName(minioConfig.getBucketName()).build();
    }

    @Override
    public BigDataUploadResult uploadBigFile(String fileMd5, int chunkCount) throws Exception {
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        BigDataUploadResult uploadResult = BigDataUploadResult.builder().build();
        //获取到该文件已上传分片
        Map<Integer, String> okChunkMap = minioUtil.mapChunkObjectNames(minioConfig.getChunkBucKet(), fileMd5);
        ArrayList<ChunkUploadUrl> chunkUploadUrls = new ArrayList<>();
        if (okChunkMap.size() > 0) {
            // 续传
            for (int i = 1; i <= chunkCount; i++) {
                //判断当前分片是否已经上传过了
                if (!okChunkMap.containsKey(i)) {
                    //生成分片上传url
                    ChunkUploadUrl url = new ChunkUploadUrl();
                    url.setPartNumber(i);
                    url.setUploadUrl(minioUtil.createUploadChunkUrl(minioConfig.getChunkBucKet(), fileMd5, i));
                    url.setUploaded(false);
                    chunkUploadUrls.add(url);
                }
            }
            if (chunkUploadUrls.size() == 0) {
                uploadResult.setNeedMerge(true);
                uploadResult.setMessage("所有分片已经上传完成，仅需要合并文件");
                return uploadResult;
            }

            uploadResult.setNeedUpload(true);
            uploadResult.setChunkUploadUrls(chunkUploadUrls);
            uploadResult.setNeedMerge(true);
            uploadResult.setMessage("继续上传");
        } else {
            uploadResult.setMessage("初次上传");
            uploadResult.setNeedMerge(true);
            //初次上传和已有文件信息但未上传任何分片的情况下则直接生成所有上传url
            List<String> uploadUrls = minioUtil.createUploadChunkUrlList(minioConfig.getChunkBucKet(), fileMd5, chunkCount);
            for (int i = 1; i <= uploadUrls.size(); i++) {
                ChunkUploadUrl url = new ChunkUploadUrl();
                url.setPartNumber(i);
                url.setUploadUrl(minioUtil.createUploadChunkUrl(minioConfig.getBucketName(), fileMd5, i));
                chunkUploadUrls.add(url);
            }
            uploadResult.setNeedUpload(true);
            uploadResult.setChunkUploadUrls(chunkUploadUrls);
        }
        return uploadResult;
    }

    @Override
    public boolean composeFile(String fileMd5, String fileName, String savePath) {
        //根据md5获取所有分片文件名称(minio的文件名称 = 文件path)
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        List<String> chunks = minioUtil.listObjectNames(minioConfig.getChunkBucKet(), fileMd5);
        chunks.sort(Comparator.naturalOrder());
        boolean result = minioUtil.composeObject(minioConfig.getBucketName(), chunks, getFilePath(fileName, savePath));
        if (result) {
            // 发布事件 异步删除切片文件
            applicationContext.publishEvent(new MinioDeleteEvent(new MinioDeleteDTO(minioConfig.getChunkBucKet(), chunks)));
        }
        return result;
    }

    @Override
    public boolean deleteFile(String fileName, String savePath) throws Exception {
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        return minioUtil.removeObjects(minioConfig.getBucketName(), Collections.singletonList(this.getFilePath(fileName, savePath)));
    }

    @Override
    public String getFileUri(String fileName, String savePath) throws Exception {
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        return minioUtil.getObjectUrl(minioConfig.getBucketName(), this.getFilePath(fileName, savePath), DEFAULT_FILE_EXPIRE_SECOND);
    }

    @Override
    public String getFileUri(String path) throws Exception {
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        return minioUtil.getObjectUrl(minioConfig.getBucketName(), path, DEFAULT_FILE_EXPIRE_SECOND);
    }

    @Override
    public InputStream getFile(String fileName, String savePath) throws Exception {
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        return minioUtil.getObject(minioConfig.getBucketName(), this.getFilePath(fileName, savePath));
    }

    /**
     * 获取文件存储地址
     *
     * @param fileName 文件名称
     * @param savePath 存储路径
     * @return
     */
    private String getFilePath(String fileName, String savePath) {
        StringBuilder filePath = new StringBuilder();
        if (StringUtils.isNotBlank(savePath)) {
            if (savePath.startsWith("/")) {
                filePath.append(savePath.substring(1, filePath.length()));
            } else {
                filePath.append(savePath);
            }

            if (!filePath.toString().endsWith("/")) {
                filePath.append("/");
            }
        }
        filePath.append(fileName);
        return filePath.toString();
    }
}