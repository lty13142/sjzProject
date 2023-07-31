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
 * 危险废物_产生月报_02产废信息对象 pp_wxfw_waste_month_report_waste
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生月报_02产废信息")
@TableName("pp_wxfw_waste_month_report_waste")
public class WasteMonthReportWaste implements Serializable {
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
     * 申报状态
     */
    @ApiModelProperty(value = "申报状态")
    @Size(max = 200, message = "申报状态最多输入200个字符")
    private String state;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 255, message = "名录版本最多输入255个字符")
    private String mlnf;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称")
    @Size(max = 200, message = "废物类别名称最多输入200个字符")
    private String fwdlmc;

    /**
     * 废物类别代码
     */
    @ApiModelProperty(value = "废物类别代码")
    @Size(max = 100, message = "废物类别代码最多输入100个字符")
    private String fwdldm;

    /**
     * 废物描述
     */
    @ApiModelProperty(value = "废物描述")
    @Size(max = 1000, message = "废物描述最多输入1000个字符")
    private String fwxlmc;

    /**
     * 废物小类代码
     */
    @ApiModelProperty(value = "废物小类代码")
    @Size(max = 100, message = "废物小类代码最多输入100个字符")
    private String fwxldm;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称")
    @Size(max = 1000, message = "废物名称最多输入1000个字符")
    private String fwxxmc;

    /**
     * 废物形态
     */
    @ApiModelProperty(value = "废物形态")
    @Size(max = 200, message = "废物形态最多输入200个字符")
    private String wlxz;

    /**
     * 容器数量
     */
    @ApiModelProperty(value = "容器数量")
    @Digits(integer = 15, fraction = 4, message = "容器数量整数最多15位，小数最多4位")
    private BigDecimal rqsl;

    /**
     * 容器数量计量单位（个/袋/桶）
     */
    @ApiModelProperty(value = "容器数量计量单位（个/袋/桶）")
    @Size(max = 200, message = "容器数量计量单位（个/袋/桶）最多输入200个字符")
    private String jldw;

    /**
     * 本月产生量
     */
    @ApiModelProperty(value = "本月产生量")
    @Digits(integer = 15, fraction = 4, message = "本月产生量整数最多15位，小数最多4位")
    private BigDecimal csl;

    /**
     * 本月产生量修正值
     */
    @ApiModelProperty(value = "本月产生量修正值")
    @Digits(integer = 15, fraction = 4, message = "本月产生量修正值整数最多15位，小数最多4位")
    private BigDecimal xzz;

    /**
     * 本月底库存量
     */
    @ApiModelProperty(value = "本月底库存量")
    @Digits(integer = 15, fraction = 4, message = "本月底库存量整数最多15位，小数最多4位")
    private BigDecimal sumLjzcl;

    /**
     * 其他量
     */
    @ApiModelProperty(value = "其他量")
    @Digits(integer = 15, fraction = 4, message = "其他量整数最多15位，小数最多4位")
    private BigDecimal qtl;

    /**
     * 废物计量单位
     */
    @ApiModelProperty(value = "废物计量单位")
    @Size(max = 200, message = "废物计量单位最多输入200个字符")
    private String csljldw;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String remark;

    /**
     * 上年底贮存量
     */
    @ApiModelProperty(value = "上年底贮存量")
    @Digits(integer = 15, fraction = 4, message = "上年底贮存量整数最多15位，小数最多4位")
    private BigDecimal sndkcl;

    /**
     * 上月底贮存量
     */
    @ApiModelProperty(value = "上月底贮存量")
    @Digits(integer = 15, fraction = 4, message = "上月底贮存量整数最多15位，小数最多4位")
    private BigDecimal sumSyjcl;

    /**
     * 上月底贮存量转换值（只有一月份 且 废物计量单位不为吨可以填写）
     */
    @ApiModelProperty(value = "上月底贮存量转换值（只有一月份 且 废物计量单位不为吨可以填写）")
    @Digits(integer = 15, fraction = 4, message = "上月底贮存量转换值（只有一月份 且 废物计量单位不为吨可以填写）整数最多15位，小数最多4位")
    private BigDecimal syzclZhz;

    /**
     * 今年累计产废量(累计计算)
     */
    @ApiModelProperty(value = "今年累计产废量(累计计算)")
    @Digits(integer = 15, fraction = 4, message = "今年累计产废量(累计计算)整数最多15位，小数最多4位")
    private BigDecimal sumDnbycsl;

    /**
     * 当年累计其他量
     */
    @ApiModelProperty(value = "当年累计其他量")
    @Digits(integer = 15, fraction = 4, message = "当年累计其他量整数最多15位，小数最多4位")
    private BigDecimal sumDnqtl;

    /**
     * 当年自行利用处置量(累计计算)
     */
    @ApiModelProperty(value = "当年自行利用处置量(累计计算)")
    @Digits(integer = 15, fraction = 4, message = "当年自行利用处置量(累计计算)整数最多15位，小数最多4位")
    private BigDecimal sumDnzxczl;

    /**
     * 当年委外利用处置量(累计计算)
     */
    @ApiModelProperty(value = "当年委外利用处置量(累计计算)")
    @Digits(integer = 15, fraction = 4, message = "当年委外利用处置量(累计计算)整数最多15位，小数最多4位")
    private BigDecimal sumDnwtczl;

    /**
     * 当月自行利用处置量
     */
    @ApiModelProperty(value = "当月自行利用处置量")
    @Digits(integer = 15, fraction = 4, message = "当月自行利用处置量整数最多15位，小数最多4位")
    private BigDecimal sumZxczl;

    /**
     * 当月委外利用处置量
     */
    @ApiModelProperty(value = "当月委外利用处置量")
    @Digits(integer = 15, fraction = 4, message = "当月委外利用处置量整数最多15位，小数最多4位")
    private BigDecimal sumWtczl;

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
