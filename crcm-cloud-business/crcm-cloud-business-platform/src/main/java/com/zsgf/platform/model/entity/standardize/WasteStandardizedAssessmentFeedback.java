package com.zsgf.platform.model.entity.standardize;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 规范化考核整改反馈对象 tbl_waste_standardized_assessment_feedback
 *
 * @author gzl
 * @date 2023-03-24
 */
@Setter
@Getter
@ToString
@ApiModel("规范化考核整改反馈")
@TableName("tbl_waste_standardized_assessment_feedback")
public class WasteStandardizedAssessmentFeedback extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 100, message = "id最多输入100个字符")
    private String id;

    /**
     * 规范化考核id
     */
    @ApiModelProperty(value = "规范化考核id")
    @Size(max = 32, message = "规范化考核id最多输入32个字符")
    private String judgeId;

    /**
     * 评分记录id
     */
    @ApiModelProperty(value = "评分记录id")
    @Size(max = 100, message = "评分记录id最多输入100个字符")
    private String historyId;

    /**
     * 反馈用户id
     */
    @ApiModelProperty(value = "反馈用户id")
    @Size(max = 100, message = "反馈用户id最多输入100个字符")
    private String userId;

    /**
     * 反馈人员
     */
    @ApiModelProperty(value = "反馈人员")
    @Size(max = 255, message = "反馈人员最多输入255个字符")
    private String userName;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称")
    @Size(max = 255, message = "组织名称最多输入255个字符")
    private String orgName;

    /**
     * 0--企业端 1--监管端
     */
    @ApiModelProperty(value = "0--企业端 1--监管端")
    private Long queryStatus;

    /**
     * 反馈时间
     */
    @ApiModelProperty(value = "反馈时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date feedbackTime;

    /**
     * 反馈信息
     */
    @ApiModelProperty(value = "反馈信息")
    private String feedbackContent;

    /**
     * 状态（1：通过/提交，-1：驳回）
     */
    @ApiModelProperty(value = "状态（1：通过/提交，-1：驳回）")
    private Long auditStatus;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    @Size(max = 255, message = "附件最多输入255个字符")
    private String attachment;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
