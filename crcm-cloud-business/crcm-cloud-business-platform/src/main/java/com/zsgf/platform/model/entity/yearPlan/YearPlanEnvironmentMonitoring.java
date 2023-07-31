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
 * 危险废物_管理计划_17环境监测情况对象 pp_wxfw_year_plan_environment_monitoring
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_17环境监测情况")
@TableName("pp_wxfw_year_plan_environment_monitoring")
public class YearPlanEnvironmentMonitoring implements Serializable {
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
     * 利用处置设施运行参数监测情况
     */
    @ApiModelProperty(value = "利用处置设施运行参数监测情况")
    @Size(max = 2000, message = "利用处置设施运行参数监测情况最多输入2000个字符")
    private String lyczssjcqk;

    /**
     * 污染物监测指标及频次
     */
    @ApiModelProperty(value = "污染物监测指标及频次")
    @Size(max = 2000, message = "污染物监测指标及频次最多输入2000个字符")
    private String wrwjczbpc;

    /**
     * 自行监测情况
     */
    @ApiModelProperty(value = "自行监测情况")
    @Size(max = 2000, message = "自行监测情况最多输入2000个字符")
    private String zxjcqk;

    /**
     * 委托监测情况
     */
    @ApiModelProperty(value = "委托监测情况")
    @Size(max = 2000, message = "委托监测情况最多输入2000个字符")
    private String wtjcqk;

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
