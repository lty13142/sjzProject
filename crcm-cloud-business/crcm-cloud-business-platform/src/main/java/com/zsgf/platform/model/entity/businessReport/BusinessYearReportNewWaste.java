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
 * 危险废物_经营年报_07新产生危废情况对象 pp_wxfw_business_year_report_new_waste
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营年报_07新产生危废情况")
@TableName("pp_wxfw_business_year_report_new_waste")
public class BusinessYearReportNewWaste implements Serializable {
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
    @Size(max = 200, message = "废物描述最多输入200个字符")
    private String fwxlmc;

    /**
     * 密度
     */
    @ApiModelProperty(value = "密度")
    @Digits(integer = 15, fraction = 4, message = "密度整数最多15位，小数最多4位")
    private BigDecimal md;

    /**
     * 形态
     */
    @ApiModelProperty(value = "形态")
    @Size(max = 200, message = "形态最多输入200个字符")
    private String xt;

    /**
     * 产生源
     */
    @ApiModelProperty(value = "产生源")
    @Size(max = 200, message = "产生源最多输入200个字符")
    private String csy;

    /**
     * 产生量
     */
    @ApiModelProperty(value = "产生量")
    @Digits(integer = 15, fraction = 4, message = "产生量整数最多15位，小数最多4位")
    private BigDecimal csl;

    /**
     * 贮存数量
     */
    @ApiModelProperty(value = "贮存数量")
    @Digits(integer = 15, fraction = 4, message = "贮存数量整数最多15位，小数最多4位")
    private BigDecimal zcsl;

    /**
     * 上年底库存量
     */
    @ApiModelProperty(value = "上年底库存量")
    @Digits(integer = 15, fraction = 4, message = "上年底库存量整数最多15位，小数最多4位")
    private BigDecimal sndkcl;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 200, message = "计量单位最多输入200个字符")
    private String jldw;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 200, message = "备注最多输入200个字符")
    private String bz;

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
