package com.zsgf.platform.model.entity.yearPlan;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 危险废物_管理计划_08危险废物贮存设施现状对象 pp_wxfw_year_plan_waste_storage_facilities_status
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_08危险废物贮存设施现状")
@TableName("pp_wxfw_year_plan_waste_storage_facilities_status")
public class YearPlanWasteStorageFacilitiesStatus implements Serializable {
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
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 200, message = "设施名称最多输入200个字符")
    private String ssmc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @Digits(integer = 15, fraction = 4, message = "数量整数最多15位，小数最多4位")
    private BigDecimal sl;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 200, message = "类型最多输入200个字符")
    private String type;

    /**
     * 面积
     */
    @ApiModelProperty(value = "面积")
    @Digits(integer = 15, fraction = 4, message = "面积整数最多15位，小数最多4位")
    private BigDecimal area;

    /**
     * 数量单位
     */
    @ApiModelProperty(value = "数量单位")
    @Size(max = 200, message = "数量单位最多输入200个字符")
    private String sldw;

    /**
     * 设计贮存能力
     */
    @ApiModelProperty(value = "设计贮存能力")
    @Digits(integer = 15, fraction = 4, message = "设计贮存能力整数最多15位，小数最多4位")
    private BigDecimal sjzcnl;

    /**
     * 贮存能力计量单位
     */
    @ApiModelProperty(value = "贮存能力计量单位")
    @Size(max = 255, message = "贮存能力计量单位最多输入255个字符")
    private String sjzcnljldw;

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
    private Date modifyrq;

}
