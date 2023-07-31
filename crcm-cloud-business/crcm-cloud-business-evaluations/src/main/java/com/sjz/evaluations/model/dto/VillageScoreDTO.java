package com.sjz.evaluations.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 村级考核接收实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 21:01
 */
@Data
@Accessors(chain = true)
public class VillageScoreDTO implements Serializable {

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
     * 管区区域code
     */
    private String regionAreaCode;

    /**
     * 村级区域名称
     */
    private String villageAreaName;
    /**
     * 村级区域代码
     */
    private String villageAreaCode;
    /**
     * 考核状态(0：待反馈 1：已反馈 2：已评分)
     */
    private Integer status;
}
