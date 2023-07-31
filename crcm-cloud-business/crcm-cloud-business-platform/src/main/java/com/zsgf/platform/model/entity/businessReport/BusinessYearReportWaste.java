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
 * 危险废物_经营年报_03经营废物信息对象 pp_wxfw_business_year_report_waste
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营年报_03经营废物信息")
@TableName("pp_wxfw_business_year_report_waste")
public class BusinessYearReportWaste implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 申报基本信息ID
     */
    @ApiModelProperty(value = "申报基本信息ID")
    @Size(max = 100, message = "申报基本信息ID最多输入100个字符")
    private String mainid;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String dwid;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 100, message = "名录版本最多输入100个字符")
    private String mlnf;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别")
    @Size(max = 200, message = "废物类别最多输入200个字符")
    private String fwdlbh;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称")
    @Size(max = 200, message = "废物类别名称最多输入200个字符")
    private String fwdlmc;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码")
    @Size(max = 200, message = "废物代码最多输入200个字符")
    private String fwxlbh;

    /**
     * 废物描述
     */
    @ApiModelProperty(value = "废物描述")
    @Size(max = 1000, message = "废物描述最多输入1000个字符")
    private String fwxlmc;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称")
    @Size(max = 1000, message = "废物名称最多输入1000个字符")
    private String fwxxmc;

    /**
     * 主要废物来源
     */
    @ApiModelProperty(value = "主要废物来源")
    @Size(max = 200, message = "主要废物来源最多输入200个字符")
    private String zyfwly;

    /**
     * 废物描述 (备注)
     */
    @ApiModelProperty(value = "废物描述 (备注)")
    @Size(max = 200, message = "废物描述 (备注)最多输入200个字符")
    private String fwms;

    /**
     * 形态
     */
    @ApiModelProperty(value = "形态")
    @Size(max = 200, message = "形态最多输入200个字符")
    private String xt;

    /**
     * 密度
     */
    @ApiModelProperty(value = "密度")
    @Digits(integer = 15, fraction = 4, message = "密度整数最多15位，小数最多4位")
    private BigDecimal md;

    /**
     * 接收量
     */
    @ApiModelProperty(value = "接收量")
    @Digits(integer = 15, fraction = 4, message = "接收量整数最多15位，小数最多4位")
    private BigDecimal jsl;

    /**
     * 遗留贮存量
     */
    @ApiModelProperty(value = "遗留贮存量")
    @Digits(integer = 15, fraction = 4, message = "遗留贮存量整数最多15位，小数最多4位")
    private BigDecimal ylzcl;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 200, message = "计量单位最多输入200个字符")
    private String jldw;

    /**
     * 其中铅蓄电池量
     */
    @ApiModelProperty(value = "其中铅蓄电池量")
    @Digits(integer = 15, fraction = 4, message = "其中铅蓄电池量整数最多15位，小数最多4位")
    private BigDecimal qxdc;

    /**
     * 其中镉镍电池量
     */
    @ApiModelProperty(value = "其中镉镍电池量")
    @Digits(integer = 15, fraction = 4, message = "其中镉镍电池量整数最多15位，小数最多4位")
    private BigDecimal gndc;

    /**
     * 其中阴极射线管量
     */
    @ApiModelProperty(value = "其中阴极射线管量")
    @Digits(integer = 15, fraction = 4, message = "其中阴极射线管量整数最多15位，小数最多4位")
    private BigDecimal yjsxg;

    /**
     * 其他电池量
     */
    @ApiModelProperty(value = "其他电池量")
    @Digits(integer = 15, fraction = 4, message = "其他电池量整数最多15位，小数最多4位")
    private BigDecimal qt;

    /**
     * 省代码
     */
    @ApiModelProperty(value = "省代码")
    @Size(max = 40, message = "省代码最多输入40个字符")
    private String sssheng;

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
