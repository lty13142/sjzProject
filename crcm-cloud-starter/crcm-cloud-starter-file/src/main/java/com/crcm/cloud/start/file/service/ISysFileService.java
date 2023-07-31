package com.crcm.cloud.start.file.service;

import com.crcm.cloud.start.file.domain.BigDataUploadResult;
import com.crcm.cloud.start.file.domain.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @ClassName ISysFileService
 * @Description 文件存储服务接口
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/
public interface ISysFileService {
    /**
     * 文件上传
     *
     * @param file     上传的文件
     * @param savePath 存储的路径
     * @return 访问地址
     * @throws Exception
     */
    UploadResult uploadFile(MultipartFile file, String savePath) throws Exception;


    /**
     * 超大文件上传
     * @param fileMd5 文件md5
     * @param chunkCount 分片数量
     * @return
     * @throws Exception
     */
    BigDataUploadResult uploadBigFile(String fileMd5, int chunkCount) throws Exception;

    /**
     * 大文件切片合并
     * @param fileMd5 文件md5
     * @param fileName 存储文件名称
     * @param savePath 存储文件路径
     * @return
     */
    boolean composeFile(String fileMd5, String fileName, String savePath);


    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @param savePath 存储路径，以 / 结尾
     * @return
     * @throws Exception
     */
    boolean deleteFile(String fileName, String savePath) throws Exception;

    /**
     * 获取文件地址
     *
     * @param fileName 文件名称
     * @param savePath 存储路径，以 / 结尾
     * @return
     * @throws Exception
     */
    String getFileUri(String fileName, String savePath) throws Exception;

    /**
     * 获取文件地址
     *
     * @param path 存储路径，以 / 结尾
     * @return 完整路径
     */
    String getFileUri(String path) throws Exception;

    /**
     * 获取文件输入流
     *
     * @param fileName 文件名称
     * @param savePath 存储路径，以 / 结尾
     * @return
     * @throws Exception
     */
    InputStream getFile(String fileName, String savePath) throws Exception;

}
