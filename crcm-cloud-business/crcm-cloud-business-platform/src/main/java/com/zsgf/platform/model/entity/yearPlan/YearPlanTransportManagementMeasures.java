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
 * 危险废物_管理计划_10运输管理措施对象 pp_wxfw_year_plan_transport_management_measures
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_10运输管理措施")
@TableName("pp_wxfw_year_plan_transport_management_measures")
public class YearPlanTransportManagementMeasures implements Serializable {
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
     * 运输过程中是否遵守危险货物运输管理的规定
     */
    @ApiModelProperty(value = "运输过程中是否遵守危险货物运输管理的规定")
    @Size(max = 200, message = "运输过程中是否遵守危险货物运输管理的规定最多输入200个字符")
    private String sfzswfysglgd;

    /**
     * 是否按危险废物特性分类运输
     */
    @ApiModelProperty(value = "是否按危险废物特性分类运输")
    @Size(max = 200, message = "是否按危险废物特性分类运输最多输入200个字符")
    private String sfflys;

    /**
     * 是否委托运输
     */
    @ApiModelProperty(value = "是否委托运输")
    @Size(max = 200, message = "是否委托运输最多输入200个字符")
    private String sfwtys;

    /**
     * 运输过程中采取的污染防治措施
     */
    @ApiModelProperty(value = "运输过程中采取的污染防治措施")
    @Size(max = 2000, message = "运输过程中采取的污染防治措施最多输入2000个字符")
    private String ysgczcqcs;

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
