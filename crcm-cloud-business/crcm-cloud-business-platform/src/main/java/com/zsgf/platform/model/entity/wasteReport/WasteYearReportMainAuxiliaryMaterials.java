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
 * 危险废物_产生年报_06主要原辅材料及能源对象 pp_wxfw_waste_year_report_main_auxiliary_materials
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生年报_06主要原辅材料及能源")
@TableName("pp_wxfw_waste_year_report_main_auxiliary_materials")
public class WasteYearReportMainAuxiliaryMaterials implements Serializable {
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
     * 种类
     */
    @ApiModelProperty(value = "种类")
    @Size(max = 200, message = "种类最多输入200个字符")
    private String kind;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Size(max = 200, message = "名称最多输入200个字符")
    private String name;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @Size(max = 200, message = "编号最多输入200个字符")
    private String number;

    /**
     * 消耗量
     */
    @ApiModelProperty(value = "消耗量")
    @Digits(integer = 15, fraction = 4, message = "消耗量整数最多15位，小数最多4位")
    private BigDecimal consumption;

    /**
     * 消耗量计量单位
     */
    @ApiModelProperty(value = "消耗量计量单位")
    @Size(max = 200, message = "消耗量计量单位最多输入200个字符")
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
