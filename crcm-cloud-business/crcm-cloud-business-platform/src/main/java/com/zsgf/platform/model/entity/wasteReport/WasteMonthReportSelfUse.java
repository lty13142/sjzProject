package com.zsgf.platform.model.entity.wasteReport;

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
 * 危险废物_产生月报_03废物自行利用信息对象 pp_wxfw_waste_month_report_self_use
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生月报_03废物自行利用信息")
@TableName("pp_wxfw_waste_month_report_self_use")
public class WasteMonthReportSelfUse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100, message = "主键最多输入100个字符")
    private String id;

    /**
     * 基本信息表ID
     */
    @ApiModelProperty(value = "基本信息表ID")
    @Size(max = 100, message = "基本信息表ID最多输入100个字符")
    private String mainid;

    /**
     * 产废信息表ID
     */
    @ApiModelProperty(value = "产废信息表ID")
    @Size(max = 100, message = "产废信息表ID最多输入100个字符")
    private String childid;

    /**
     * 设施编码
     */
    @ApiModelProperty(value = "设施编码")
    @Size(max = 200, message = "设施编码最多输入200个字符")
    private String ssbm;

    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 200, message = "设施名称最多输入200个字符")
    private String ssmc;

    /**
     * 修正值
     */
    @ApiModelProperty(value = "修正值")
    @Digits(integer = 15, fraction = 4, message = "修正值整数最多15位，小数最多4位")
    private BigDecimal xzz;

    /**
     * 容器数量计量单位
     */
    @ApiModelProperty(value = "容器数量计量单位")
    @Size(max = 200, message = "容器数量计量单位最多输入200个字符")
    private String jldw;

    /**
     * 容器个数
     */
    @ApiModelProperty(value = "容器个数")
    @Digits(integer = 15, fraction = 4, message = "容器个数整数最多15位，小数最多4位")
    private BigDecimal rqgs;

    /**
     * 设施类型
     */
    @ApiModelProperty(value = "设施类型")
    @Size(max = 200, message = "设施类型最多输入200个字符")
    private String sslx;

    /**
     * 设计容量(立方米)-填埋场
     */
    @ApiModelProperty(value = "设计容量(立方米)-填埋场")
    @Digits(integer = 15, fraction = 4, message = "设计容量(立方米)-填埋场整数最多15位，小数最多4位")
    private BigDecimal sjrl;

    /**
     * 设计处置能力-填埋场/焚烧设备
     */
    @ApiModelProperty(value = "设计处置能力-填埋场/焚烧设备")
    @Digits(integer = 15, fraction = 4, message = "设计处置能力-填埋场/焚烧设备整数最多15位，小数最多4位")
    private BigDecimal sjcznl;

    /**
     * 设计能力计量单位
     */
    @ApiModelProperty(value = "设计能力计量单位")
    @Size(max = 200, message = "设计能力计量单位最多输入200个字符")
    private String sjcznljldw;

    /**
     * 利用处置方式大类
     */
    @ApiModelProperty(value = "利用处置方式大类")
    @Size(max = 200, message = "利用处置方式大类最多输入200个字符")
    private String lyczdl;

    /**
     * 利用处置方式小类
     */
    @ApiModelProperty(value = "利用处置方式小类")
    @Size(max = 200, message = "利用处置方式小类最多输入200个字符")
    private String lyczxl;

    /**
     * 处置废物量
     */
    @ApiModelProperty(value = "处置废物量")
    @Digits(integer = 15, fraction = 4, message = "处置废物量整数最多15位，小数最多4位")
    private BigDecimal lyczl;

    /**
     * 处置废物量计量单位
     */
    @ApiModelProperty(value = "处置废物量计量单位")
    @Size(max = 200, message = "处置废物量计量单位最多输入200个字符")
    private String lyczljldw;

    /**
     * 已填容量(立方米)-填埋场
     */
    @ApiModelProperty(value = "已填容量(立方米)-填埋场")
    @Digits(integer = 15, fraction = 4, message = "已填容量(立方米)-填埋场整数最多15位，小数最多4位")
    private BigDecimal ytrl;

    /**
     * 已填重量(吨)-填埋场
     */
    @ApiModelProperty(value = "已填重量(吨)-填埋场")
    @Digits(integer = 15, fraction = 4, message = "已填重量(吨)-填埋场整数最多15位，小数最多4位")
    private BigDecimal ytzl;

    /**
     * 当期填埋量(立方米)-填埋场
     */
    @ApiModelProperty(value = "当期填埋量(立方米)-填埋场")
    @Digits(integer = 15, fraction = 4, message = "当期填埋量(立方米)-填埋场整数最多15位，小数最多4位")
    private BigDecimal dqtml;

    /**
     * 累积已填容量(吨)-填埋场
     */
    @ApiModelProperty(value = "累积已填容量(吨)-填埋场")
    @Digits(integer = 15, fraction = 4, message = "累积已填容量(吨)-填埋场整数最多15位，小数最多4位")
    private BigDecimal ljytrla;

    /**
     * 累积已填容量(立方米)-填埋场
     */
    @ApiModelProperty(value = "累积已填容量(立方米)-填埋场")
    @Digits(integer = 15, fraction = 4, message = "累积已填容量(立方米)-填埋场整数最多15位，小数最多4位")
    private BigDecimal ljytrlb;

    /**
     * 是否次生产生废物
     */
    @ApiModelProperty(value = "是否次生产生废物")
    @Size(max = 30, message = "是否次生产生废物最多输入30个字符")
    private String sfcs;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String remark;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
