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
 * 危险废物_产生年报_04委外利用情况对象 pp_wxfw_waste_year_report_outsourcing_use
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生年报_04委外利用情况")
@TableName("pp_wxfw_waste_year_report_outsourcing_use")
public class WasteYearReportOutsourcingUse implements Serializable {
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
     * 废物产生情况ID
     */
    @ApiModelProperty(value = "废物产生情况ID")
    @Size(max = 100, message = "废物产生情况ID最多输入100个字符")
    private String childid;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String dwid;

    /**
     * 接收省
     */
    @ApiModelProperty(value = "接收省")
    @Size(max = 200, message = "接收省最多输入200个字符")
    private String sheng;

    /**
     * 接收单位ID
     */
    @ApiModelProperty(value = "接收单位ID")
    @Size(max = 100, message = "接收单位ID最多输入100个字符")
    private String jsdwid;

    /**
     * 接收单位名称
     */
    @ApiModelProperty(value = "接收单位名称")
    @Size(max = 200, message = "接收单位名称最多输入200个字符")
    private String dwmc;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    @Size(max = 200, message = "处置方式大类最多输入200个字符")
    private String wbczfs;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 200, message = "处置方式小类最多输入200个字符")
    private String wbczfsxl;

    /**
     * 处理数量
     */
    @ApiModelProperty(value = "处理数量")
    @Digits(integer = 15, fraction = 4, message = "处理数量整数最多15位，小数最多4位")
    private BigDecimal wbclsl;

    /**
     * 许可证编号
     */
    @ApiModelProperty(value = "许可证编号")
    @Size(max = 200, message = "许可证编号最多输入200个字符")
    private String xkzbh;

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
