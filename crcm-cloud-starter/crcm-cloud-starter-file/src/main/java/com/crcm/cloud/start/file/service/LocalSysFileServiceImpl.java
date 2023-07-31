package com.crcm.cloud.start.file.service;

import com.crcm.cloud.start.file.domain.BigDataUploadResult;
import com.crcm.cloud.start.file.domain.UploadResult;
import com.crcm.cloud.start.file.utils.FileDeleteUtils;
import com.crcm.cloud.start.file.utils.FileUploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName LocalSysFileServiceImpl
 * @Description 本地文件存储
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/
@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService {
    /**
     * 资源映射路径 前缀
     */
//    @Value("${file.local.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
//    @Value("${file.local.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
//    @Value("${file.local.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public UploadResult uploadFile(MultipartFile file, String savePath) throws Exception {
        String name = FileUploadUtils.upload(localFilePath + savePath, file);
        return UploadResult.builder().saveFileName(name).fileUri(domain + localFilePrefix + savePath + name).build();
    }

    @Override
    public BigDataUploadResult uploadBigFile(String fileMd5, int chunkCount) throws Exception {
        return null;
    }

    @Override
    public boolean composeFile(String fileMd5, String fileName, String savePath) {
        return false;
    }

    @Override
    public boolean deleteFile(String fileName, String savePath) throws Exception {
        return FileDeleteUtils.deleteFile(getFilePath(fileName, savePath));
    }

    @Override
    public String getFileUri(String fileName, String savePath) throws Exception {
        return domain + localFilePrefix + savePath + fileName;
    }

    @Override
    public String getFileUri(String path) throws Exception {
        return domain + localFilePrefix + path;
    }

    @Override
    public InputStream getFile(String fileName, String savePath) throws Exception {
        String filePath = getFilePath(fileName, savePath);
        File file = new File(filePath);
        if (file.exists()) {
            new FileInputStream(file);
        }
        return null;
    }

    private String getFilePath(String fileName, String savePath) {
        StringBuilder filePath = new StringBuilder(localFilePath);
        if (StringUtils.isNotBlank(savePath)) {
            if (!savePath.startsWith("/")) {
                filePath.append(savePath.substring(1, filePath.length()));
            } else {
                filePath.append(savePath);
            }
        }
        filePath.append(fileName);
        return filePath.toString();
    }
}
