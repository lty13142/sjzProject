package com.sjz.evaluations.constants;

/**
 * 考核常量池
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/25 20:05
 */
public interface ExamineConstant {

    /**
     * 考核任务发布状态 0：未发布 1：已发布
     */
    Integer PUBLISH_TYPE = 1;

    /**
     * 管区审核状态 0：未审核
     */
    Integer UNAUDITED = 0;

    /**
     * 指标类型(0:定性指标 1:定量指标)
     */
    Integer QUALITATIVE_INDICATORS = 0;
    Integer QUANTIFY_INDICATORS = 1;

    /**
     * 村级考核实体 完成状态(0:未完成 1:已完成)
     */
    Integer INCOMPLETE = 0;
    Integer SUCCESS = 1;


    /**
     * 村级考核实体 考核状态(0：待反馈 1：已反馈 2：已核实 3：已完成 4: 待分配)
     */
    Integer PENDING_FEEDBACK = 0;
    Integer ALREADY_FEEDBACK = 1;
    Integer RATED = 2;
    Integer VILLAGE_SUCCESS = 3;
    Integer DISTRIBUTED = 4;
}
