package com.zsgf.platform.dto.standardize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName WasteStandardizedAssessmentItemDTO
 * @Description 规范化考核大类查询DTO
 * @Author GZL
 * @Date 2023/3/20 15:43
 * @Version 1.0
 **/
@Data
public class WasteStandardizedAssessmentItemDTO {

    /**
     * 检查项目
     */
    @ApiModelProperty(value = "检查项目")
    private String name;

    /**
     * 分类，产废-0，经营-1
     */
    @ApiModelProperty(value = "分类，产废-0，经营-1")
    private String itemSort;

}
