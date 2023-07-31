package com.crcm.cloud.start.file.minio.vo;

import com.crcm.cloud.start.file.domain.ChunkUploadUrl;
import com.crcm.cloud.start.file.domain.FileVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MinIoResultVo
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/2020/9/28/11:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinIoResultVo implements Serializable {
    /** 是否秒传标识 */
    private Boolean skipUpload;
    /** 是否需要合并文件 */
    private Boolean needMerge;
    /** 是否需要上传 */
    private Boolean needUpload;
    /** 上传状态 */
//    private Integer uploadStatus;
    /** 切片文件信息 */
    private List<ChunkUploadUrl> chunkUploadUrls;
    /** 文件地址 */
    private String url;
    /** 信息 */
    private String message;
    /** 文件信息 */
    private FileVo fileData;
}
