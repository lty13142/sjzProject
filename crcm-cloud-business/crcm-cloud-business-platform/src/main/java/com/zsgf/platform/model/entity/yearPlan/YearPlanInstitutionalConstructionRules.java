package com.zsgf.platform.model.entity.yearPlan;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 危险废物_管理计划_12制度建设规章制度情况对象 pp_wxfw_year_plan_institutional_construction_rules
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_12制度建设规章制度情况")
@TableName("pp_wxfw_year_plan_institutional_construction_rules")
public class YearPlanInstitutionalConstructionRules implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100, message = "主键最多输入100个字符")
    private String id;

    /**
     * 基本信息主键
     */
    @ApiModelProperty(value = "基本信息主键")
    @Size(max = 100, message = "基本信息主键最多输入100个字符")
    private String mainid;

    /**
     * 管理制度
     */
    @ApiModelProperty(value = "管理制度")
    @Size(max = 200, message = "管理制度最多输入200个字符")
    private String glzd;

    /**
     * 岗位责任制度
     */
    @ApiModelProperty(value = "岗位责任制度")
    @Size(max = 200, message = "岗位责任制度最多输入200个字符")
    private String gwzrzd;

    /**
     * 安全操作规程
     */
    @ApiModelProperty(value = "安全操作规程")
    @Size(max = 200, message = "安全操作规程最多输入200个字符")
    private String aqczlc;

    /**
     * 管理台账
     */
    @ApiModelProperty(value = "管理台账")
    @Size(max = 200, message = "管理台账最多输入200个字符")
    private String gltz;

    /**
     * 培训制度
     */
    @ApiModelProperty(value = "培训制度")
    @Size(max = 200, message = "培训制度最多输入200个字符")
    private String pxzd;

    /**
     * 意外事故防范措施和应急预案
     */
    @ApiModelProperty(value = "意外事故防范措施和应急预案")
    @Size(max = 200, message = "意外事故防范措施和应急预案最多输入200个字符")
    private String ywff;

    /**
     * 所属省
     */
    @ApiModelProperty(value = "所属省")
    @Size(max = 40, message = "所属省最多输入40个字符")
    private String sssheng;

    /**
     * 所属市
     */
    @ApiModelProperty(value = "所属市")
    @Size(max = 40, message = "所属市最多输入40个字符")
    private String ssshi;

    /**
     * 所属区
     */
    @ApiModelProperty(value = "所属区")
    @Size(max = 40, message = "所属区最多输入40个字符")
    private String ssqu;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xgsj;

}
