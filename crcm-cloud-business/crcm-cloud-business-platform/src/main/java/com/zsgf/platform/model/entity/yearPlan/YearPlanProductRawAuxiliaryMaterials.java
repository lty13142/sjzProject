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
 * 危险废物_管理计划_13产品生产主要原辅材料对象 pp_wxfw_year_plan_product_raw_auxiliary_materials
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_13产品生产主要原辅材料")
@TableName("pp_wxfw_year_plan_product_raw_auxiliary_materials")
public class YearPlanProductRawAuxiliaryMaterials implements Serializable {
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
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @Size(max = 200, message = "序号最多输入200个字符")
    private String xh;

    /**
     * 主要原辅材料名称
     */
    @ApiModelProperty(value = "主要原辅材料名称")
    @Size(max = 200, message = "主要原辅材料名称最多输入200个字符")
    private String zyyfclmc;

    /**
     * 本年度计划消耗量
     */
    @ApiModelProperty(value = "本年度计划消耗量")
    @Digits(integer = 15, fraction = 4, message = "本年度计划消耗量整数最多15位，小数最多4位")
    private BigDecimal bndxhl;

    /**
     * 上年度消耗量
     */
    @ApiModelProperty(value = "上年度消耗量")
    @Digits(integer = 15, fraction = 4, message = "上年度消耗量整数最多15位，小数最多4位")
    private BigDecimal sndxhl;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 200, message = "计量单位最多输入200个字符")
    private String jldw;

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
