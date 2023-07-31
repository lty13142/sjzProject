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
 * 危险废物_经营年报_02许可证核准年经营规模对象 pp_wxfw_business_year_report_approved_scale
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营年报_02许可证核准年经营规模")
@TableName("pp_wxfw_business_year_report_approved_scale")
public class BusinessYearReportApprovedScale implements Serializable {
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
     * 核准类别
     */
    @ApiModelProperty(value = "核准类别")
    @Size(max = 200, message = "核准类别最多输入200个字符")
    private String hzlb;

    /**
     * 核准量
     */
    @ApiModelProperty(value = "核准量")
    @Digits(integer = 15, fraction = 4, message = "核准量整数最多15位，小数最多4位")
    private BigDecimal hzl;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 100, message = "计量单位最多输入100个字符")
    private String jldw;

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
