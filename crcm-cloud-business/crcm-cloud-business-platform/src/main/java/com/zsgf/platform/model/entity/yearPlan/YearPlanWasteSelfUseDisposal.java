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
 * 危险废物_管理计划_04危废自行利用处置信息对象 pp_wxfw_year_plan_waste_self_use_disposal
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_04危废自行利用处置信息")
@TableName("pp_wxfw_year_plan_waste_self_use_disposal")
public class YearPlanWasteSelfUseDisposal implements Serializable {
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
     * 危险废物产生概况信息主键
     */
    @ApiModelProperty(value = "危险废物产生概况信息主键")
    @Size(max = 100, message = "危险废物产生概况信息主键最多输入100个字符")
    private String childid;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @Size(max = 200, message = "序号最多输入200个字符")
    private String xh;

    /**
     * 本年度计划利用处置量
     */
    @ApiModelProperty(value = "本年度计划利用处置量")
    @Digits(integer = 15, fraction = 4, message = "本年度计划利用处置量整数最多15位，小数最多4位")
    private BigDecimal bndjhlyczl;

    /**
     * 上年度实际利用处置量
     */
    @ApiModelProperty(value = "上年度实际利用处置量")
    @Digits(integer = 15, fraction = 4, message = "上年度实际利用处置量整数最多15位，小数最多4位")
    private BigDecimal sndsjlyczl;

    /**
     * 设施编号
     */
    @ApiModelProperty(value = "设施编号")
    @Size(max = 200, message = "设施编号最多输入200个字符")
    private String ssid;

    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 200, message = "设施名称最多输入200个字符")
    private String ssmc;

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
