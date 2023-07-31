package com.sjz.education.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjz.education.model.entity.EduPointsRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 德育积分_积分上报记录
 * @TableName edu_report_record
 * @author: sssccc
 */
@Data
@Accessors(chain = true)
public class ReportRecordVO extends EduPointsRule implements Serializable {
    /**
     * 父级积分名称
     */
    private String fatherRuleName;

    /**
     * 记录id
     */
    private String recordId;

    /**
     * 文字说明
     */
    private String textDescription;

    /**
     * 上传图片，可多个
     */
    private String imagePath;

    /**
     * 审核意见
     */
    private String reviewComment;
    /**
     * 获得积分
     */
    private Integer earnPoints;


    private static final long serialVersionUID = 1L;
}