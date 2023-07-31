package com.sjz.partbuilding.model.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 首页三会一课统计
 */
@Data
@ApiModel("首页三会一课统计")
public class OrgEventTotal implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 主题名称
     */
    @ApiModelProperty(value = "组织名称")
    private String name;
    /**
     * 活动类型
     */
    @ApiModelProperty(value = "活动类型：0：党员大会，1：支委会，2：党小组会，3：党课，4：主题党日活动，5：组织生活会")
    private String type;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer count;
    /**
     * 百分比
     */
    @ApiModelProperty(value = "百分比")
    private Integer percent;
    /**
     * 是否有权限点击 0和1有权限 2和3无权限
     */
    @ApiModelProperty(value = "是否有权限点击0直属 1地属 2团支部 3工会")
    private Integer branchType;

}