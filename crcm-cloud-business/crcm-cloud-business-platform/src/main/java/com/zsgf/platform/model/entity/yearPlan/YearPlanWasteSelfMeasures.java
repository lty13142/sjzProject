package com.zsgf.platform.model.entity.yearPlan;

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
 * 危险废物_管理计划_06危废自行利用/处置措施对象 pp_wxfw_year_plan_waste_self_measures
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_06危废自行利用/处置措施")
@TableName("pp_wxfw_year_plan_waste_self_measures")
public class YearPlanWasteSelfMeasures implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100, message = "主键最多输入100个字符")
    private String id;

    /**
     * 基本信息表主键
     */
    @ApiModelProperty(value = "基本信息表主键")
    @Size(max = 100, message = "基本信息表主键最多输入100个字符")
    private String mainid;

    /**
     * 利用处置方式大类
     */
    @ApiModelProperty(value = "利用处置方式大类")
    @Size(max = 200, message = "利用处置方式大类最多输入200个字符")
    private String ssdllb;

    /**
     * 利用处置方式小类
     */
    @ApiModelProperty(value = "利用处置方式小类")
    @Size(max = 200, message = "利用处置方式小类最多输入200个字符")
    private String ssxllb;

    /**
     * 设施编号
     */
    @ApiModelProperty(value = "设施编号")
    @Size(max = 100, message = "设施编号最多输入100个字符")
    private String ssbh;

    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 200, message = "设施名称最多输入200个字符")
    private String ssmc;

    /**
     * 设施地址
     */
    @ApiModelProperty(value = "设施地址")
    @Size(max = 200, message = "设施地址最多输入200个字符")
    private String ssdz;

    /**
     * 总投资（万元）
     */
    @ApiModelProperty(value = "总投资（万元）")
    @Digits(integer = 15, fraction = 4, message = "总投资（万元）整数最多15位，小数最多4位")
    private BigDecimal ztz;

    /**
     * 设计能力
     */
    @ApiModelProperty(value = "设计能力")
    @Digits(integer = 15, fraction = 4, message = "设计能力整数最多15位，小数最多4位")
    private BigDecimal sjnl;

    /**
     * 主要设备及数量
     */
    @ApiModelProperty(value = "主要设备及数量")
    @Size(max = 200, message = "主要设备及数量最多输入200个字符")
    private String zysb;

    /**
     * 设计使用年限
     */
    @ApiModelProperty(value = "设计使用年限")
    @Size(max = 200, message = "设计使用年限最多输入200个字符")
    private String sjsynx;

    /**
     * 投入运行时间
     */
    @ApiModelProperty(value = "投入运行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tryxsj;

    /**
     * 运行费用
     */
    @ApiModelProperty(value = "运行费用")
    @Digits(integer = 15, fraction = 4, message = "运行费用整数最多15位，小数最多4位")
    private BigDecimal yxfy;

    /**
     * 危险废物利用处置效果
     */
    @ApiModelProperty(value = "危险废物利用处置效果")
    @Size(max = 200, message = "危险废物利用处置效果最多输入200个字符")
    private String wflyczxg;

    /**
     * 是否定期监测污染物排放情况
     */
    @ApiModelProperty(value = "是否定期监测污染物排放情况")
    @Size(max = 200, message = "是否定期监测污染物排放情况最多输入200个字符")
    private String sfdqjcpfqk;

    /**
     * 污染物排放达标情况
     */
    @ApiModelProperty(value = "污染物排放达标情况")
    @Size(max = 200, message = "污染物排放达标情况最多输入200个字符")
    private String wrwpfdbqk;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 200, message = "计量单位最多输入200个字符")
    private String jldw;

    /**
     * 二次环境污染控制和事故预防措施
     */
    @ApiModelProperty(value = "二次环境污染控制和事故预防措施")
    @Size(max = 5000, message = "二次环境污染控制和事故预防措施最多输入5000个字符")
    private String echjwrkzhsgyfcs;

    /**
     * 工艺说明
     */
    @ApiModelProperty(value = "工艺说明")
    @Size(max = 5000, message = "工艺说明最多输入5000个字符")
    private String gysm;

    /**
     * 所属省
     */
    @ApiModelProperty(value = "所属省")
    @Size(max = 40, message = "所属省最多输入40个字符")
    private String sssheng;

    /**
     * 所属市
     */
    @ApiModelProperty(value = "所属市")
    @Size(max = 40, message = "所属市最多输入40个字符")
    private String ssshi;

    /**
     * 所属区
     */
    @ApiModelProperty(value = "所属区")
    @Size(max = 40, message = "所属区最多输入40个字符")
    private String ssqu;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
