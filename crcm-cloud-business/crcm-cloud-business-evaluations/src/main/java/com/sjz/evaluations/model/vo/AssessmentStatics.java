package com.sjz.evaluations.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Tianyu
 * @date 2023/4/10 18:20
 */
@Data
public class AssessmentStatics {

    @ApiModelProperty(value = "管区名称")
    private String areaName;

    @ApiModelProperty(value = "村级数量")
    private int villagerNum;

    @ApiModelProperty(value = "完成情况")
    private String finishPer;

}
