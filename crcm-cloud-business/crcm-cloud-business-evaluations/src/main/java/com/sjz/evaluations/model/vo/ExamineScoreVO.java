package com.sjz.evaluations.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 考核评分返回实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 19:35
 */
@Data
@Accessors(chain = true)
public class ExamineScoreVO implements Serializable {

    /**
     * 考核任务id
     */
    private String id;
    /**
     * 年份
     */
    private Integer examineYear;

    /**
     * 考核名称
     */
    private String examineName;

    /**
     * 管区代码
     */
    private String regionAreaCode;

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
