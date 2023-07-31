package com.crcm.cloud.start.file.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ChunkUploadUrl
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/2020/9/28/11:45
 **/
@Data
public class ChunkUploadUrl implements Serializable {
    private static final long serialVersionUID = -281691502061019182L;
    /** 是否已上传 */
    private Boolean uploaded;
    /** 切片序号 */
    private Integer partNumber;
    /** 上传地址 */
    private String uploadUrl;
}
