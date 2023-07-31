package com.crcm.cloud.start.file.service;

import cn.hutool.http.HttpStatus;
import com.crcm.cloud.start.file.domain.BigDataUploadResult;
import com.crcm.cloud.start.file.domain.UploadResult;
import com.crcm.cloud.start.file.fastdfs.FastDfsClient;
import com.crcm.cloud.start.file.fastdfs.FastDfsConfig;
import com.crcm.cloud.start.file.fastdfs.FastDfsDeleteResult;
import com.crcm.cloud.start.file.fastdfs.FastDfsUploadResult;
import com.crcm.cloud.start.file.utils.FileUploadUtils;
import com.crcm.core.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @ClassName FastDfsSysFileServiceImpl
 * @Description fastdfs文件存储
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService {

    @Autowired
    private FastDfsConfig fastDfsConfig;

    /**
     * FastDfs文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public UploadResult uploadFile(MultipartFile file, String savePath) throws Exception {
        FastDfsClient client = FastDfsClient.init(fastDfsConfig);
        String saveFileName = FileUploadUtils.extractFilename(file);
        FastDfsUploadResult result = client.doUpload(saveFileName, file.getInputStream(), savePath);
        return UploadResult.builder().saveFileName(saveFileName).fileUri(result.getPath()).build();
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
        FastDfsClient client = FastDfsClient.init(fastDfsConfig);
        FastDfsDeleteResult result = client.doDelete(getFileUri(fileName, savePath));
        return StringUtils.equals(result.getStatus(), HttpStatus.HTTP_OK + "");
    }

    @Override
    public String getFileUri(String fileName, String savePath) throws Exception {
        FastDfsClient client = FastDfsClient.init(fastDfsConfig);
        return client.getUri(getFilePath(fileName, savePath));
    }

    @Override
    public String getFileUri(String path) throws Exception {
        FastDfsClient client = FastDfsClient.init(fastDfsConfig);
        return client.getUri(path);
    }

    @Override
    public InputStream getFile(String fileName, String savePath) throws Exception {
        String fileUri = this.getFileUri(fileName, savePath);
        if (StringUtils.isNotBlank(fileUri)) {
            return FileUtil.downloadWebFile(fileUri);
        }
        return null;
    }

    public String getFilePath(String fileName, String savePath) {
        StringBuilder filePath = new StringBuilder();
        if (StringUtils.isNotBlank(savePath)) {
            if (savePath.startsWith("/")) {
                filePath.append(savePath.substring(1, filePath.length()));
            } else {
                filePath.append(savePath);
            }
        }
        filePath.append(fileName);
        return filePath.toString();
    }
}