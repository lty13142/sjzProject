package com.sjz.evaluations.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 考核结果请求实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/28 11:42
 */
@Data
@Accessors(chain = true)
public class ExamineResultDTO  implements Serializable {


    /**
     * 指标名称
     */
    private String indicatorsName;
    /**
     * 考核年份
     */
    private String examineYear;
    /**
     * 负责部门
     */
    private String orgId;

    /**
     * 负责人
     */
    private String nikeName;

    /**
     * 指标类型
     */
    private Integer type;

    /**
     * 考核任务id
     */
    private String examineId;

    /**
     * 管区代码
     */
    private String regionAreaCode;



}
