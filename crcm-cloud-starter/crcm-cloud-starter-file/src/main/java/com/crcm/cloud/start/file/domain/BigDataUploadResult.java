package com.crcm.cloud.start.file.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BigDataUploadResult
 * @Description 超大文件上传结果
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/24
 **/
@Data
@Builder
public class BigDataUploadResult implements Serializable {

    private static final long serialVersionUID = 5585127018521055000L;

    /** 是否秒传标识 */
    private Boolean skipUpload;
    /** 是否需要合并文件 */
    private Boolean needMerge;
    /** 是否需要上传 */
    private Boolean needUpload;
    /** 切片文件信息 */
    private List<ChunkUploadUrl> chunkUploadUrls;
    /** 文件地址 */
    private String url;
    /** 信息 */
    private String message;
    /** 文件信息 */
    private FileVo fileData;
}
