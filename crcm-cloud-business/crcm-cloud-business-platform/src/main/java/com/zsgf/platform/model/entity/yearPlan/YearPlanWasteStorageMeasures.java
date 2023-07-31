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
 * 危险废物_管理计划_07危险废物贮存措施对象 pp_wxfw_year_plan_waste_storage_measures
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_07危险废物贮存措施")
@TableName("pp_wxfw_year_plan_waste_storage_measures")
public class YearPlanWasteStorageMeasures implements Serializable {
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
     * 贮存场所是否符合《危险废物贮存污染控制标准》有关要求
     */
    @ApiModelProperty(value = "贮存场所是否符合《危险废物贮存污染控制标准》有关要求")
    @Size(max = 200, message = "贮存场所是否符合《危险废物贮存污染控制标准》有关要求最多输入200个字符")
    private String sffhygyq;

    /**
     * 是否按危险废物特性分类收集、贮存
     */
    @ApiModelProperty(value = "是否按危险废物特性分类收集、贮存")
    @Size(max = 200, message = "是否按危险废物特性分类收集、贮存最多输入200个字符")
    private String sfflsjzc;

    /**
     * 是否混合贮存未经安全性处置且性质不相容的危险废物
     */
    @ApiModelProperty(value = "是否混合贮存未经安全性处置且性质不相容的危险废物")
    @Size(max = 200, message = "是否混合贮存未经安全性处置且性质不相容的危险废物最多输入200个字符")
    private String sfhhzcwjaqxczqxzbxrwf;

    /**
     * 是否将危险废物混入非危险废物中贮存
     */
    @ApiModelProperty(value = "是否将危险废物混入非危险废物中贮存")
    @Size(max = 200, message = "是否将危险废物混入非危险废物中贮存最多输入200个字符")
    private String sfhhzc;

    /**
     * 是否通过建设项目环境影响评价审批及竣工环境保护验收
     */
    @ApiModelProperty(value = "是否通过建设项目环境影响评价审批及竣工环境保护验收")
    @Size(max = 200, message = "是否通过建设项目环境影响评价审批及竣工环境保护验收最多输入200个字符")
    private String sftgspjys;

    /**
     * 贮存过程中采取的污染防治和事故预防措施
     */
    @ApiModelProperty(value = "贮存过程中采取的污染防治和事故预防措施")
    @Size(max = 2000, message = "贮存过程中采取的污染防治和事故预防措施最多输入2000个字符")
    private String wrfzhsgyfcs;

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
