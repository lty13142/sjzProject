package com.zsgf.platform.model.entity.yearPlan;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 危险废物_管理计划_20上年度管理计划回顾对象 pp_wxfw_year_plan_last_year_review
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_20上年度管理计划回顾")
@TableName("pp_wxfw_year_plan_last_year_review")
public class YearPlanLastYearReview implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100, message = "主键最多输入100个字符")
    private String id;

    /**
     * 基本信息主键
     */
    @ApiModelProperty(value = "基本信息主键")
    @Size(max = 100, message = "基本信息主键最多输入100个字符")
    private String mainid;

    /**
     * 检查、监测与公开
     */
    @ApiModelProperty(value = "检查、监测与公开")
    @Size(max = 2000, message = "检查、监测与公开最多输入2000个字符")
    private String jcjcygk;

    /**
     * 危险废物比较分析
     */
    @ApiModelProperty(value = "危险废物比较分析")
    @Size(max = 2000, message = "危险废物比较分析最多输入2000个字符")
    private String wxfwbjfx;

    /**
     * 是否将危险废物委托给有资质单位收集、贮存、利用、处置
     */
    @ApiModelProperty(value = "是否将危险废物委托给有资质单位收集、贮存、利用、处置")
    @Size(max = 200, message = "是否将危险废物委托给有资质单位收集、贮存、利用、处置最多输入200个字符")
    private String wwczsfyzz;

    /**
     * 是否与有资质单位签订危险废物利用处置合同/协议
     */
    @ApiModelProperty(value = "是否与有资质单位签订危险废物利用处置合同/协议")
    @Size(max = 200, message = "是否与有资质单位签订危险废物利用处置合同/协议最多输入200个字符")
    private String qdhtsfyzz;

    /**
     * 是否对危险废物许可证进行审查确认
     */
    @ApiModelProperty(value = "是否对危险废物许可证进行审查确认")
    @Size(max = 200, message = "是否对危险废物许可证进行审查确认最多输入200个字符")
    private String sfdxkzjxscqr;

    /**
     * 转移危险废物是否经过环保部门批准
     */
    @ApiModelProperty(value = "转移危险废物是否经过环保部门批准")
    @Size(max = 200, message = "转移危险废物是否经过环保部门批准最多输入200个字符")
    private String zywfsfjhbbmpz;

    /**
     * 是否按照规定填写危险废物转移联单
     */
    @ApiModelProperty(value = "是否按照规定填写危险废物转移联单")
    @Size(max = 200, message = "是否按照规定填写危险废物转移联单最多输入200个字符")
    private String sfazgdtxwxfwzyld;

    /**
     * 危险废物收集、贮存、处置设施场所是否设置危险废物识别标志
     */
    @ApiModelProperty(value = "危险废物收集、贮存、处置设施场所是否设置危险废物识别标志")
    @Size(max = 200, message = "危险废物收集、贮存、处置设施场所是否设置危险废物识别标志最多输入200个字符")
    private String lycssfszbz;

    /**
     * 危险废物的容器和包装物是否设置危险废物标签
     */
    @ApiModelProperty(value = "危险废物的容器和包装物是否设置危险废物标签")
    @Size(max = 200, message = "危险废物的容器和包装物是否设置危险废物标签最多输入200个字符")
    private String wfbzsfszbz;

    /**
     * 是否按照国家规定建立危险废物台账
     */
    @ApiModelProperty(value = "是否按照国家规定建立危险废物台账")
    @Size(max = 2000, message = "是否按照国家规定建立危险废物台账最多输入2000个字符")
    private String sfjlwftz;

    /**
     * 危险废物收集、贮存、处置等污染防治设施是否通过环评审批
     */
    @ApiModelProperty(value = "危险废物收集、贮存、处置等污染防治设施是否通过环评审批")
    @Size(max = 200, message = "危险废物收集、贮存、处置等污染防治设施是否通过环评审批最多输入200个字符")
    private String sssftghpsp;

    /**
     * 上述危险废物相关污染防治设施是否与主体工程同时通过环保验收
     */
    @ApiModelProperty(value = "上述危险废物相关污染防治设施是否与主体工程同时通过环保验收")
    @Size(max = 200, message = "上述危险废物相关污染防治设施是否与主体工程同时通过环保验收最多输入200个字符")
    private String sssfyztgctstghbys;

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
    private Date xgsj;

}
