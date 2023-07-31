package com.sjz.evaluations.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 管区考核评分返回实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 21:54
 */
@Data
@Accessors(chain = true)
public class RegionScoreVO implements Serializable {

    /**
     * 考核任务id
     */
    private String examineId;
    /**
     * 管区代码
     */
    private String regionAreaCode;
    /**
     * 管区名称
     */
    private String regionAreaName;
    /**
     * 考核总数
     */
    private Integer countNum;

    /**
     * 待反馈
     */
    private Integer noFeedback;

    /**
     * 已反馈
     */
    private Integer alreadyFeedback;

    /**
     * 已评分
     */
    private Integer rated;

}