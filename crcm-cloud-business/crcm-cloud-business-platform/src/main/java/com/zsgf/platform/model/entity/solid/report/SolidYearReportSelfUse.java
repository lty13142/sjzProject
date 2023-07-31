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
 * 数据共享_一般工业固体废物_产生年报_03自行利用情况对象 pp_ybgygf_solid_year_report_self_use
 *
 * @author gzl
 * @date 2023-03-27
 */
@Setter
@Getter
@ToString
@ApiModel("数据共享_一般工业固体废物_产生年报_03自行利用情况")
@TableName("pp_ybgygf_solid_year_report_self_use")
public class SolidYearReportSelfUse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
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
     * 设施ID
     */
    @ApiModelProperty(value = "设施ID")
    @Size(max = 100, message = "设施ID最多输入100个字符")
    private String ssid;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    @Size(max = 200, message = "处置方式大类最多输入200个字符")
    private String lyczfsdl;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 200, message = "处置方式小类最多输入200个字符")
    private String lyczfsxl;

    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 400, message = "设施名称最多输入400个字符")
    private String ssmc;

    /**
     * 处置数量
     */
    @ApiModelProperty(value = "处置数量")
    @Digits(integer = 15, fraction = 4, message = "处置数量整数最多15位，小数最多4位")
    private BigDecimal czsl;

    /**
     * 上个周期贮存量
     */
    @ApiModelProperty(value = "上个周期贮存量")
    @Digits(integer = 15, fraction = 4, message = "上个周期贮存量整数最多15位，小数最多4位")
    private BigDecimal wnzcl;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 200, message = "计量单位最多输入200个字符")
    private String sldw;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 1000, message = "备注信息最多输入1000个字符")
    private String remark;

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
