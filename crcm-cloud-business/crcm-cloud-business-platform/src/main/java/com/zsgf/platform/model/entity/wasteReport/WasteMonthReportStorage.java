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
 * 危险废物_产生月报_05产废贮存信息对象 pp_wxfw_waste_month_report_storage
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生月报_05产废贮存信息")
@TableName("pp_wxfw_waste_month_report_storage")
public class WasteMonthReportStorage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 基本信息ID
     */
    @ApiModelProperty(value = "基本信息ID")
    @Size(max = 100, message = "基本信息ID最多输入100个字符")
    private String mainid;

    /**
     * 产废信息ID
     */
    @ApiModelProperty(value = "产废信息ID")
    @Size(max = 100, message = "产废信息ID最多输入100个字符")
    private String childid;

    /**
     * 贮存设施名称
     */
    @ApiModelProperty(value = "贮存设施名称")
    @Size(max = 200, message = "贮存设施名称最多输入200个字符")
    private String ssmc;

    /**
     * 贮存设施编号
     */
    @ApiModelProperty(value = "贮存设施编号")
    @Size(max = 200, message = "贮存设施编号最多输入200个字符")
    private String ssbh;

    /**
     * 容器数量
     */
    @ApiModelProperty(value = "容器数量")
    @Digits(integer = 15, fraction = 4, message = "容器数量整数最多15位，小数最多4位")
    private BigDecimal rqsl;

    /**
     * 容器个数计量单位
     */
    @ApiModelProperty(value = "容器个数计量单位")
    @Size(max = 200, message = "容器个数计量单位最多输入200个字符")
    private String rqsljldw;

    /**
     * 上月底贮存量
     */
    @ApiModelProperty(value = "上月底贮存量")
    @Digits(integer = 15, fraction = 4, message = "上月底贮存量整数最多15位，小数最多4位")
    private BigDecimal kcjs;

    /**
     * 本月底贮存量
     */
    @ApiModelProperty(value = "本月底贮存量")
    @Digits(integer = 15, fraction = 4, message = "本月底贮存量整数最多15位，小数最多4位")
    private BigDecimal rkzcl;

    /**
     * 设计贮存能力
     */
    @ApiModelProperty(value = "设计贮存能力")
    @Digits(integer = 15, fraction = 4, message = "设计贮存能力整数最多15位，小数最多4位")
    private BigDecimal zcnl;

    /**
     * 贮存处置方式大类
     */
    @ApiModelProperty(value = "贮存处置方式大类")
    @Size(max = 200, message = "贮存处置方式大类最多输入200个字符")
    private String zcczfsdl;

    /**
     * 贮存设施类型
     */
    @ApiModelProperty(value = "贮存设施类型")
    @Size(max = 100, message = "贮存设施类型最多输入100个字符")
    private String sslx;

    /**
     * 上年底贮存量转换值
     */
    @ApiModelProperty(value = "上年底贮存量转换值")
    @Digits(integer = 15, fraction = 4, message = "上年底贮存量转换值整数最多15位，小数最多4位")
    private BigDecimal sndzclzhz;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
