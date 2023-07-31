package com.sjz.evaluations.model.vo;

import com.sjz.evaluations.model.entity.ExamineIndicators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 村级指标返回实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 9:29
 */
@Data
@Accessors(chain = true)
public class VillageIndicatorsVO extends ExamineIndicators {

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
     * 村级id
     */
    private String villageId;
    /**
     * 管区id
     */
    private String regionExamineId;
    /**
     * 指标id
     */
    private String indicatorsId;
    /**
     * 村级代码
     */
    private String villageAreaCode;
    /**
     * 村级名称
     */
    private String villageAreaName;
    /**
     * 定性/定量目标
     */
    private String target;
    /**
     * 单位
     */
    private String unit;
    /**
     * 考核状态(0：待反馈 1：已反馈 2：已评分)
     */
    private Integer status;

    /**
     * 完成状态(0:未完成 1:已完成)
     */
    private Integer completeStatus;
    /**
     * 核实完成状态(0:未完成 1:已完成)
     */
    private Integer verifyCompleteStatus;

    /**
     * 进度目标/完成情况
     */
    private String completeValue;

    /**
     * 反馈内容
     */
    private String feedbackContent;

    /**
     * 反馈附件
     */
    private String feedbackAttr;

    /**
     * 反馈附件名称
     */
    private String feedbackAttrName;

    /**
     * 打分分值
     */
    private BigDecimal score;

    /**
     * 打分内容
     */
    private String scoreContent;




}
