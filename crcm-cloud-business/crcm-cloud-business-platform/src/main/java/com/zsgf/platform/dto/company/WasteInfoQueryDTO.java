package com.zsgf.platform.dto.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName WasteInfoQueryDTO
 * @Description 企业废物信息查询DTO
 * @Author GZL
 * @Date 2023/2/23 9:56
 * @Version 1.0
 **/
@Data
public class WasteInfoQueryDTO {
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private String companyId;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称")
    private String wasteName;
    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    private String directoryVersion;

    /**
     * 废物种类 0:危废 1：固废
     */
    @ApiModelProperty(value = "废物种类")
    private Long wasteCategory;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别")
    private String wasteType;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码")
    private String wasteCode;

    /**
     * 危险类别（数据字典）
     */
    @ApiModelProperty(value = "危险类别（数据字典）")
    private String dangerousType;
}
