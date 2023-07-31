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
 * 危险废物_管理计划_02危险废物产生概况信息对象 pp_wxfw_year_plan_waste_generation_overview
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_02危险废物产生概况信息")
@TableName("pp_wxfw_year_plan_waste_generation_overview")
public class YearPlanWasteGenerationOverview implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100, message = "主键最多输入100个字符")
    private String id;

    /**
     * 基本信息表主键
     */
    @ApiModelProperty(value = "基本信息表主键")
    @Size(max = 100, message = "基本信息表主键最多输入100个字符")
    private String mainid;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 100, message = "名录版本最多输入100个字符")
    private String mlnf;

    /**
     * 废物大类代码
     */
    @ApiModelProperty(value = "废物大类代码")
    @Size(max = 100, message = "废物大类代码最多输入100个字符")
    private String fwdldm;

    /**
     * 废物大类名称
     */
    @ApiModelProperty(value = "废物大类名称")
    @Size(max = 200, message = "废物大类名称最多输入200个字符")
    private String fwdlmc;

    /**
     * 废物小类代码
     */
    @ApiModelProperty(value = "废物小类代码")
    @Size(max = 100, message = "废物小类代码最多输入100个字符")
    private String fwxldm;

    /**
     * 废物小类名称
     */
    @ApiModelProperty(value = "废物小类名称")
    @Size(max = 1000, message = "废物小类名称最多输入1000个字符")
    private String fwxlmc;

    /**
     * 废物详细名称
     */
    @ApiModelProperty(value = "废物详细名称")
    @Size(max = 2000, message = "废物详细名称最多输入2000个字符")
    private String fwxxmc;

    /**
     * 废物特性
     */
    @ApiModelProperty(value = "废物特性")
    @Size(max = 200, message = "废物特性最多输入200个字符")
    private String fwtx;

    /**
     * 物理性状
     */
    @ApiModelProperty(value = "物理性状")
    @Size(max = 200, message = "物理性状最多输入200个字符")
    private String fwxz;

    /**
     * 有害物质
     */
    @ApiModelProperty(value = "有害物质")
    @Size(max = 200, message = "有害物质最多输入200个字符")
    private String yhwz;

    /**
     * 本年度计划产生量
     */
    @ApiModelProperty(value = "本年度计划产生量")
    @Digits(integer = 15, fraction = 4, message = "本年度计划产生量整数最多15位，小数最多4位")
    private BigDecimal bndjhcsl;

    /**
     * 上年度实际产生量
     */
    @ApiModelProperty(value = "上年度实际产生量")
    @Digits(integer = 15, fraction = 4, message = "上年度实际产生量整数最多15位，小数最多4位")
    private BigDecimal sndsjcsl;

    /**
     * 本年度实际委托量
     */
    @ApiModelProperty(value = "本年度实际委托量")
    @Digits(integer = 15, fraction = 4, message = "本年度实际委托量整数最多15位，小数最多4位")
    private BigDecimal bndsjwtl;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 200, message = "计量单位最多输入200个字符")
    private String jldw;

    /**
     * 计划转移频次
     */
    @ApiModelProperty(value = "计划转移频次")
    @Size(max = 200, message = "计划转移频次最多输入200个字符")
    private String jhzypc;

    /**
     * 计划转移频次单位
     */
    @ApiModelProperty(value = "计划转移频次单位")
    @Size(max = 200, message = "计划转移频次单位最多输入200个字符")
    private String jhzypcdw;

    /**
     * 贮存原因
     */
    @ApiModelProperty(value = "贮存原因")
    @Size(max = 2000, message = "贮存原因最多输入2000个字符")
    private String zcyy;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 2000, message = "备注最多输入2000个字符")
    private String remark;

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
