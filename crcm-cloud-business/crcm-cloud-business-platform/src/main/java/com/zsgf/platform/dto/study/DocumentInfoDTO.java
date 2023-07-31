package com.zsgf.platform.dto.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DocumentInfoDTO
 * @Description 文档信息查询DTO
 * @Author GZL
 * @Date 2023/3/28 9:33
 * @Version 1.0
 **/
@Data
public class DocumentInfoDTO {
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    /**
     * 类型（政策法规、指导行文件、标准规范、相关函复等, 字典：STUDY_DOCUMENT_TYPE）
     */
    @ApiModelProperty(value = "类型（政策法规、指导行文件、标准规范、相关函复等, 字典：STUDY_DOCUMENT_TYPE）")
    private Integer docType;
}
