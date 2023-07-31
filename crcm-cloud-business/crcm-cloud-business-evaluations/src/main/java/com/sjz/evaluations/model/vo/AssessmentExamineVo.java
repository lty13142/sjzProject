package com.sjz.evaluations.model.vo;

import lombok.Data;
import net.sf.jsqlparser.statement.select.First;

/**
 * @author yzw
 * @data 2023/4/7
 * @apiNote
 */
@Data
public class AssessmentExamineVo {

    /**
     * 一级指标
     */
    private String  level0;
    private String id0;

    /**
     * 二级指标
     */
    private String  level1;
    private String id1;

    /**
     * 三级指标
     */
    private String  level2;
    private String id2;

    /**
     * 四级指标
     */
    private String level3;
    private String id3;

    private String createTime;

    private Boolean lastStage;

    private String examineId;

    private String content;

    private String assessmentDepartment;

    private String post;

    private String assessmentDepartmentName;

    private String postName;
}
