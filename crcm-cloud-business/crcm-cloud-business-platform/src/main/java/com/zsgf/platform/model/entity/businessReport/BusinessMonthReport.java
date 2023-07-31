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
 * 危险废物_经营月报_01基本信息对象 pp_wxfw_business_month_report
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营月报_01基本信息")
@TableName("pp_wxfw_business_month_report")
public class BusinessMonthReport implements Serializable {
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
     * 申报日期
     */
    @ApiModelProperty(value = "申报日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sbrq;

    /**
     * 经营年份
     */
    @ApiModelProperty(value = "经营年份")
    @Size(max = 100, message = "经营年份最多输入100个字符")
    private String jynf;

    /**
     * 经营月份
     */
    @ApiModelProperty(value = "经营月份")
    @Size(max = 100, message = "经营月份最多输入100个字符")
    private String jyyf;

    /**
     * 当月利用处置设施运行天数
     */
    @ApiModelProperty(value = "当月利用处置设施运行天数")
    @Digits(integer = 15, fraction = 4, message = "当月利用处置设施运行天数整数最多15位，小数最多4位")
    private BigDecimal ssyxts;

    /**
     * 填写人
     */
    @ApiModelProperty(value = "填写人")
    @Size(max = 200, message = "填写人最多输入200个字符")
    private String txr;

    /**
     * 填写人手机
     */
    @ApiModelProperty(value = "填写人手机")
    @Size(max = 200, message = "填写人手机最多输入200个字符")
    private String txrphone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String bz;

    /**
     * 申报状态
     */
    @ApiModelProperty(value = "申报状态")
    @Size(max = 200, message = "申报状态最多输入200个字符")
    private String state;

    /**
     * 是否上报
     */
    @ApiModelProperty(value = "是否上报")
    @Size(max = 200, message = "是否上报最多输入200个字符")
    private String sfsb;

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
