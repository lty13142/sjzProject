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
 * 危险废物_产生月报_04废物委外利用信息对象 pp_wxfw_waste_month_report_outsourcing_use
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生月报_04废物委外利用信息")
@TableName("pp_wxfw_waste_month_report_outsourcing_use")
public class WasteMonthReportOutsourcingUse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 产废信息ID
     */
    @ApiModelProperty(value = "产废信息ID")
    @Size(max = 100, message = "产废信息ID最多输入100个字符")
    private String childid;

    /**
     * 基本信息ID
     */
    @ApiModelProperty(value = "基本信息ID")
    @Size(max = 100, message = "基本信息ID最多输入100个字符")
    private String mainid;

    /**
     * 转移联单编号
     */
    @ApiModelProperty(value = "转移联单编号")
    @Size(max = 100, message = "转移联单编号最多输入100个字符")
    private String zyldbh;

    /**
     * 接收单位所属省份
     */
    @ApiModelProperty(value = "接收单位所属省份")
    @Size(max = 40, message = "接收单位所属省份最多输入40个字符")
    private String jsdwssseng;

    /**
     * 接收单位名称
     */
    @ApiModelProperty(value = "接收单位名称")
    @Size(max = 200, message = "接收单位名称最多输入200个字符")
    private String jsdwmc;

    /**
     * 委外利用处置量
     */
    @ApiModelProperty(value = "委外利用处置量")
    @Digits(integer = 15, fraction = 4, message = "委外利用处置量整数最多15位，小数最多4位")
    private BigDecimal wwlyczl;

    /**
     * 修正值（吨）
     */
    @ApiModelProperty(value = "修正值（吨）")
    @Digits(integer = 15, fraction = 4, message = "修正值（吨）整数最多15位，小数最多4位")
    private BigDecimal xzz;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    @Size(max = 200, message = "处置方式大类最多输入200个字符")
    private String czfsdl;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 200, message = "处置方式小类最多输入200个字符")
    private String czfsxl;

    /**
     * 危废经营许可证编号
     */
    @ApiModelProperty(value = "危废经营许可证编号")
    @Size(max = 40, message = "危废经营许可证编号最多输入40个字符")
    private String xkzbh;

    /**
     * 容器数量（袋/个/桶）
     */
    @ApiModelProperty(value = "容器数量（袋/个/桶）")
    @Digits(integer = 15, fraction = 4, message = "容器数量（袋/个/桶）整数最多15位，小数最多4位")
    private BigDecimal rqsl;

    /**
     * 容器数量计量单位
     */
    @ApiModelProperty(value = "容器数量计量单位")
    @Size(max = 200, message = "容器数量计量单位最多输入200个字符")
    private String jldw;

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
