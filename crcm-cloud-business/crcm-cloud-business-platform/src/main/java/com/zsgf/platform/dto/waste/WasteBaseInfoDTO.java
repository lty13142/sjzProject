package com.zsgf.platform.dto.waste;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName WasteBaseInfoDTO
 * @Description 危废名录查询DTO
 * @Author GZL
 * @Date 2023/3/20 15:39
 * @Version 1.0
 **/
@Data
@ApiModel()
public class WasteBaseInfoDTO {

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    private String directoryVersion;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private Integer level;

    /**
     * 废物种类 0:危废 1：固废 2 医废
     */
    @ApiModelProperty(value = "废物种类 0:危废 1：固废 2 医废")
    private Integer wasteCategory;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别")
    private String wasteType;
}
