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
 * 危险废物_管理计划_19减量化计划和措施对象 pp_wxfw_year_plan_minimization_plan_measures
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_19减量化计划和措施")
@TableName("pp_wxfw_year_plan_minimization_plan_measures")
public class YearPlanMinimizationPlanMeasures implements Serializable {
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
     * 减少危险废物危害性的计划
     */
    @ApiModelProperty(value = "减少危险废物危害性的计划")
    @Size(max = 2000, message = "减少危险废物危害性的计划最多输入2000个字符")
    private String jswfwhxjh;

    /**
     * 减少危险废物产生量和危害性的措施
     */
    @ApiModelProperty(value = "减少危险废物产生量和危害性的措施")
    @Size(max = 2000, message = "减少危险废物产生量和危害性的措施最多输入2000个字符")
    private String jswfcslhwhxcs;

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
