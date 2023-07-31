package com.crcm.cloud.start.file.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UploadResult
 * @Description 文件上传结果
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/24
 **/
@Data
@Builder
public class UploadResult implements Serializable {
    private static final long serialVersionUID = -1078906716918370904L;
    /**
     * 文件UUID
     */
    private String uuid;
    /**
     * 文件存储名称
     */
    private String saveFileName;
    /**
     * 文件URI
     */
    private String fileUri;
    /**
     * minio存储桶名称
     */
    private String bucketName;
}
