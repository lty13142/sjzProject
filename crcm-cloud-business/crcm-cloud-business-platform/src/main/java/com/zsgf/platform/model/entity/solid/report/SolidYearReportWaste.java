package com.zsgf.platform.model.entity.solid.report;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 数据共享_一般工业固体废物_产生年报_02产废信息对象 pp_ybgygf_solid_year_report_waste
 *
 * @author gzl
 * @date 2023-03-27
 */
@Setter
@Getter
@ToString
@ApiModel("数据共享_一般工业固体废物_产生年报_02产废信息")
@TableName("pp_ybgygf_solid_year_report_waste")
public class SolidYearReportWaste implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
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
     * 废物大类
     */
    @ApiModelProperty(value = "废物大类")
    @Size(max = 100, message = "废物大类最多输入100个字符")
    private String fwdlbh;

    /**
     * 废物大类名称
     */
    @ApiModelProperty(value = "废物大类名称")
    @Size(max = 255, message = "废物大类名称最多输入255个字符")
    private String fwdlmc;

    /**
     * 本年产生量
     */
    @ApiModelProperty(value = "本年产生量")
    @Digits(integer = 15, fraction = 4, message = "本年产生量整数最多15位，小数最多4位")
    private BigDecimal cszl;

    /**
     * 上年底贮存量
     */
    @ApiModelProperty(value = "上年底贮存量")
    @Digits(integer = 15, fraction = 4, message = "上年底贮存量整数最多15位，小数最多4位")
    private BigDecimal ylzcl;

    /**
     * 数量单位
     */
    @ApiModelProperty(value = "数量单位")
    @Size(max = 200, message = "数量单位最多输入200个字符")
    private String sldw;

    /**
     * 废物描述信息
     */
    @ApiModelProperty(value = "废物描述信息")
    @Size(max = 2000, message = "废物描述信息最多输入2000个字符")
    private String fwms;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 1000, message = "备注信息最多输入1000个字符")
    private String remark;

    /**
     * 倾倒丢弃量
     */
    @ApiModelProperty(value = "倾倒丢弃量")
    @Digits(integer = 15, fraction = 4, message = "倾倒丢弃量整数最多15位，小数最多4位")
    private BigDecimal dql;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    @Size(max = 200, message = "产品名称最多输入200个字符")
    private String cpmc;

    /**
     * 产品产量
     */
    @ApiModelProperty(value = "产品产量")
    @Digits(integer = 15, fraction = 4, message = "产品产量整数最多15位，小数最多4位")
    private BigDecimal cpcl;

    /**
     * 原材料名称
     */
    @ApiModelProperty(value = "原材料名称")
    @Size(max = 200, message = "原材料名称最多输入200个字符")
    private String yclmc;

    /**
     * 污水处理量
     */
    @ApiModelProperty(value = "污水处理量")
    @Digits(integer = 15, fraction = 4, message = "污水处理量整数最多15位，小数最多4位")
    private BigDecimal gyfscll;

    /**
     * 原材料消耗量
     */
    @ApiModelProperty(value = "原材料消耗量")
    @Digits(integer = 15, fraction = 4, message = "原材料消耗量整数最多15位，小数最多4位")
    private BigDecimal yclxhl;

    /**
     * 丢弃方式
     */
    @ApiModelProperty(value = "丢弃方式")
    @Size(max = 200, message = "丢弃方式最多输入200个字符")
    private String dqfs;

    /**
     * 名录年份
     */
    @ApiModelProperty(value = "名录年份")
    @Size(max = 200, message = "名录年份最多输入200个字符")
    private String mlnf;

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

    /**
     * 申报状态
     */
    @ApiModelProperty(value = "申报状态")
    @Size(max = 100, message = "申报状态最多输入100个字符")
    private String state;

}
