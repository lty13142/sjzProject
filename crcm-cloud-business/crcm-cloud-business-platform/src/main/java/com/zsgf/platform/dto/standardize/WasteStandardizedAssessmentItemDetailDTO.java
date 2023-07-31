package com.zsgf.platform.dto.standardize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName WasteStandardizedAssessmentItemDetailDTO
 * @Description 规范化考核小类查询DTO
 * @Author GZL
 * @Date 2023/3/20 15:49
 * @Version 1.0
 **/
@Data
public class WasteStandardizedAssessmentItemDetailDTO {

    /**
     * 分类，产废-0，经营-1
     */
    @ApiModelProperty(value = "分类，产废-0，经营-1")
    private String itemSort;

    /**
     * 主要检查内容
     */
    @ApiModelProperty(value = "主要检查内容")
    private String title;

    /**
     * 检查项id
     */
    @ApiModelProperty(value = "检查项id")
    private String itemId;

    /**
     * 检查项
     */
    @ApiModelProperty(value = "检查项")
    private String itemName;
}
