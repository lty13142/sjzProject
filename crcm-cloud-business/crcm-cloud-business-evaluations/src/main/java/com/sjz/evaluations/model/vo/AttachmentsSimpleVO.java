package com.sjz.evaluations.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yzw
 * @data 2023/4/12
 * @apiNote
 */
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