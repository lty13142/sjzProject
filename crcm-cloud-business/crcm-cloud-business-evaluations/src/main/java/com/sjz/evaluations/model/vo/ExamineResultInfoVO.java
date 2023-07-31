package com.sjz.evaluations.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 考核结果信息返回实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/27 16:33
 */
@Data
public class ExamineResultInfoVO implements Serializable {

    /**
     * 考核名称
     */
    private String examineName;

    /**
     * 年份
     */
    private Integer examineYear;
    /**
     * 负责部门名称
     */
    private String orgName;

    /**
     * 负责人名称
     */
    private String nikeName;
    /**
     * 指标id
     */
    private String indicatorsId;
    /**
     * 指标名称
     */
    private String indicatorsName;

    /**
     * 指标类型(0:定性指标 1:定量指标)
     */
    private Integer type;

    /**
     * 目标
     */
    private String target;

    /**
     * 单位
     */
    private String unit;

    /**
     * 管区code
     */
    private String regionAreaCode;
    /**
     * 管区name
     */
    private String regionAreaName;

    /**
     * 村级code
     */
    private String villageAreaCode;
    /**
     * 村名称
     */
    private String villageAreaName;

    /**
     * 核实分
     */
    private BigDecimal score;

    /**
     * 核实完成状态(0:未完成 1:已完成)
     */
    private Integer verifyCompleteStatus;
}
