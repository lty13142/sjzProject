package com.sjz.evaluations.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 考核评分接收实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 19:31
 */
@Data
public class ExamineScoreDTO implements Serializable {

    /**
     * 年份
     */
    private Integer examineYear;

    /**
     * 考核名称
     */
    private String examineName;
}
