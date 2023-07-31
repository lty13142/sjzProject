package com.crcm.file.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName AttachmentsSimpleVO
 * @Description 附件信息
 * @Author GZL
 * @Date 2023/3/30 10:18
 * @Version 1.0
 **/
@Getter
@Setter
public class AttachmentsSimpleVO {

    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String path;
}
