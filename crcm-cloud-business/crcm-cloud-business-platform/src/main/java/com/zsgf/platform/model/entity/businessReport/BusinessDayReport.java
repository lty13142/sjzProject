package com.zsgf.platform.model.entity.businessReport;

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
 * 危险废物_经营日报_01基本信息对象 pp_wxfw_business_day_report
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营日报_01基本信息")
@TableName("pp_wxfw_business_day_report")
public class BusinessDayReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String dwid;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Size(max = 100, message = "年份最多输入100个字符")
    private String year;

    /**
     * 月份
     */
    @ApiModelProperty(value = "月份")
    @Size(max = 100, message = "月份最多输入100个字符")
    private String month;

    /**
     * 季度
     */
    @ApiModelProperty(value = "季度")
    @Size(max = 100, message = "季度最多输入100个字符")
    private String quarter;

    /**
     * 申报日期
     */
    @ApiModelProperty(value = "申报日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sbrq;

    /**
     * 填写人
     */
    @ApiModelProperty(value = "填写人")
    @Size(max = 100, message = "填写人最多输入100个字符")
    private String txr;

    /**
     * 填写人联系电话
     */
    @ApiModelProperty(value = "填写人联系电话")
    @Size(max = 100, message = "填写人联系电话最多输入100个字符")
    private String txrphone;

    /**
     * 当日利用处置设施是否运行(是/否)
     */
    @ApiModelProperty(value = "当日利用处置设施是否运行(是/否)")
    @Size(max = 100, message = "当日利用处置设施是否运行(是/否)最多输入100个字符")
    private String drlyczss;

    /**
     * 当日是否有次生危废产生(是/否)
     */
    @ApiModelProperty(value = "当日是否有次生危废产生(是/否)")
    @Size(max = 100, message = "当日是否有次生危废产生(是/否)最多输入100个字符")
    private String drsfycswf;

    /**
     * 申报状态
     */
    @ApiModelProperty(value = "申报状态")
    @Size(max = 200, message = "申报状态最多输入200个字符")
    private String state;

    /**
     * 接收量-吨
     */
    @ApiModelProperty(value = "接收量-吨")
    @Digits(integer = 15, fraction = 4, message = "接收量-吨整数最多15位，小数最多4位")
    private BigDecimal jslD;

    /**
     * 接收量-个
     */
    @ApiModelProperty(value = "接收量-个")
    @Digits(integer = 15, fraction = 4, message = "接收量-个整数最多15位，小数最多4位")
    private BigDecimal jslG;

    /**
     * 接收量-支
     */
    @ApiModelProperty(value = "接收量-支")
    @Digits(integer = 15, fraction = 4, message = "接收量-支整数最多15位，小数最多4位")
    private BigDecimal jslZ;

    /**
     * 次生废物产量-吨
     */
    @ApiModelProperty(value = "次生废物产量-吨")
    @Digits(integer = 15, fraction = 4, message = "次生废物产量-吨整数最多15位，小数最多4位")
    private BigDecimal csfwclD;

    /**
     * 次生废物产量-个
     */
    @ApiModelProperty(value = "次生废物产量-个")
    @Digits(integer = 15, fraction = 4, message = "次生废物产量-个整数最多15位，小数最多4位")
    private BigDecimal csfwclG;

    /**
     * 次生废物产量-支
     */
    @ApiModelProperty(value = "次生废物产量-支")
    @Digits(integer = 15, fraction = 4, message = "次生废物产量-支整数最多15位，小数最多4位")
    private BigDecimal csfwclZ;

    /**
     * 处置量-吨
     */
    @ApiModelProperty(value = "处置量-吨")
    @Digits(integer = 15, fraction = 4, message = "处置量-吨整数最多15位，小数最多4位")
    private BigDecimal czzlD;

    /**
     * 处置量-个
     */
    @ApiModelProperty(value = "处置量-个")
    @Digits(integer = 15, fraction = 4, message = "处置量-个整数最多15位，小数最多4位")
    private BigDecimal czzlG;

    /**
     * 处置量-支
     */
    @ApiModelProperty(value = "处置量-支")
    @Digits(integer = 15, fraction = 4, message = "处置量-支整数最多15位，小数最多4位")
    private BigDecimal czzlZ;

    /**
     * 二次转移量-吨
     */
    @ApiModelProperty(value = "二次转移量-吨")
    @Digits(integer = 15, fraction = 4, message = "二次转移量-吨整数最多15位，小数最多4位")
    private BigDecimal eczylD;

    /**
     * 二次转移量-个
     */
    @ApiModelProperty(value = "二次转移量-个")
    @Digits(integer = 15, fraction = 4, message = "二次转移量-个整数最多15位，小数最多4位")
    private BigDecimal eczylG;

    /**
     * 二次转移量-支
     */
    @ApiModelProperty(value = "二次转移量-支")
    @Digits(integer = 15, fraction = 4, message = "二次转移量-支整数最多15位，小数最多4位")
    private BigDecimal eczylZ;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String bz;

    /**
     * 地市代码
     */
    @ApiModelProperty(value = "地市代码")
    @Size(max = 40, message = "地市代码最多输入40个字符")
    private String ssshi;

    /**
     * 区县代码
     */
    @ApiModelProperty(value = "区县代码")
    @Size(max = 40, message = "区县代码最多输入40个字符")
    private String ssqu;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
