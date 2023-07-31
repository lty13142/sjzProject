package com.crcm.cloud.start.file.minio.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UploadDto
 * @Description TODO
 * @Author Ryan.
 * @Emial_AND_OICQ ryan.tang@cyberwisdom.net AND 100792057
 * @Date 2020/8/27 10:10
 * @Version 1.0
 **/
@Data
@JsonIgnoreProperties
public class UploadVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 存储桶名称 */
    @ApiModelProperty(value = "存储桶名称")
    private String bucketName;

    /** 分片数量 */
    @ApiModelProperty(value = "分片数量")
    private Integer chunkCount;

    /** 上传文件的md5 */
    @ApiModelProperty(value = "上传文件的md5")
    private String fileMd5;

    /** 文件名称  */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /** 文件大小 */
    @ApiModelProperty(value = "文件大小")
    private Long size;

    /** 存储目录 */
    @ApiModelProperty(value = "存储目录")
    private String savePath;

}
