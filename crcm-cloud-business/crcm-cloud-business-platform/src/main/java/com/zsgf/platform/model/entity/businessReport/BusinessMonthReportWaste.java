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
 * 危险废物_经营月报_02经营废物信息对象 pp_wxfw_business_month_report_waste
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营月报_02经营废物信息")
@TableName("pp_wxfw_business_month_report_waste")
public class BusinessMonthReportWaste implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 基本信息表ID
     */
    @ApiModelProperty(value = "基本信息表ID")
    @Size(max = 100, message = "基本信息表ID最多输入100个字符")
    private String mainid;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 100, message = "名录版本最多输入100个字符")
    private String mlnf;

    /**
     * 废物大类编号
     */
    @ApiModelProperty(value = "废物大类编号")
    @Size(max = 100, message = "废物大类编号最多输入100个字符")
    private String fwdlbh;

    /**
     * 废物大类名称
     */
    @ApiModelProperty(value = "废物大类名称")
    @Size(max = 200, message = "废物大类名称最多输入200个字符")
    private String fwdlmc;

    /**
     * 废物小类编号
     */
    @ApiModelProperty(value = "废物小类编号")
    @Size(max = 100, message = "废物小类编号最多输入100个字符")
    private String fwxlbh;

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
    @Size(max = 1000, message = "废物详细名称最多输入1000个字符")
    private String fwxxmc;

    /**
     * 当月接收量
     */
    @ApiModelProperty(value = "当月接收量")
    @Digits(integer = 15, fraction = 4, message = "当月接收量整数最多15位，小数最多4位")
    private BigDecimal dyjsl;

    /**
     * 当月利用处置量
     */
    @ApiModelProperty(value = "当月利用处置量")
    @Digits(integer = 15, fraction = 4, message = "当月利用处置量整数最多15位，小数最多4位")
    private BigDecimal dylycz;

    /**
     * 当月二次转移量
     */
    @ApiModelProperty(value = "当月二次转移量")
    @Digits(integer = 15, fraction = 4, message = "当月二次转移量整数最多15位，小数最多4位")
    private BigDecimal dyeczyl;

    /**
     * 当月月底库存量
     */
    @ApiModelProperty(value = "当月月底库存量")
    @Digits(integer = 15, fraction = 4, message = "当月月底库存量整数最多15位，小数最多4位")
    private BigDecimal dydzcl;

    /**
     * 上月底贮存量
     */
    @ApiModelProperty(value = "上月底贮存量")
    @Digits(integer = 15, fraction = 4, message = "上月底贮存量整数最多15位，小数最多4位")
    private BigDecimal sydzcl;

    /**
     * 当年累计接收量
     */
    @ApiModelProperty(value = "当年累计接收量")
    @Digits(integer = 15, fraction = 4, message = "当年累计接收量整数最多15位，小数最多4位")
    private BigDecimal dnljjsl;

    /**
     * 当年累计利用处置量
     */
    @ApiModelProperty(value = "当年累计利用处置量")
    @Digits(integer = 15, fraction = 4, message = "当年累计利用处置量整数最多15位，小数最多4位")
    private BigDecimal dnlycz;

    /**
     * 当年二次转移量
     */
    @ApiModelProperty(value = "当年二次转移量")
    @Digits(integer = 15, fraction = 4, message = "当年二次转移量整数最多15位，小数最多4位")
    private BigDecimal dneczyl;

    /**
     * 数量单位
     */
    @ApiModelProperty(value = "数量单位")
    @Size(max = 10, message = "数量单位最多输入10个字符")
    private String sldw;

    /**
     * 申报状态
     */
    @ApiModelProperty(value = "申报状态")
    @Size(max = 100, message = "申报状态最多输入100个字符")
    private String state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 2000, message = "备注最多输入2000个字符")
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
