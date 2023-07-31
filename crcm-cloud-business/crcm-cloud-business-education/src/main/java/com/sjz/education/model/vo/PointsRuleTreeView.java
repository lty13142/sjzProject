package com.sjz.education.model.vo;

import com.crcm.core.dto.TreeView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: sjz-project
 * @description: 积分规则树形结构
 * @author: sssccc
 * @create: 2023-04-03 17:02
 **/
@Data
public class PointsRuleTreeView extends TreeView {
    /**
     * 赋分方式
     */
    @ApiModelProperty(value = "赋分方式")
    private String scoringMethod;

    /**
     * 父级pids
     */
    @ApiModelProperty(value = "父级pids")
    private String pids;

    /**
     * 年度计分规则
     */
    @ApiModelProperty(value = "年度计分规则")
    private String ruleDetail;

    /**
     * 可获得积分
     */
    @ApiModelProperty(value = "可获得积分")
    private Integer earnPoints;
}
