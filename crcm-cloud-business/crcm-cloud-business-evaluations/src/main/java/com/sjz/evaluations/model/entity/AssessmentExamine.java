package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 * 考核指标对象 gr_assessment_examine
 *
 * @author guozhilin
 * @date 2023-04-06
 */
@Setter
@Getter
@ToString
@ApiModel("考核指标")
@TableName("gr_assessment_examine")
public class AssessmentExamine extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /** 指标名称 */
        @ApiModelProperty(value = "指标名称")
    @Size(max = 64, message = "指标名称最多输入64个字符")
    private String name;

    /** 父指标ID */
    @ApiModelProperty(value = "父指标ID")
    private String pid;

    /** 是否末级 0 不是末级  1 是末级 */
        @ApiModelProperty(value = "是否末级 0 不是末级  1 是末级")
    private Boolean lastStage;

    /** 考核表ID */
        @ApiModelProperty(value = "考核表ID")
    @Size(max = 64, message = "考核表ID最多输入64个字符")
    private String examineId;

    /** 内容 */
        @ApiModelProperty(value = "内容")
    private String content;

    /** 考核部门 */
        @ApiModelProperty(value = "考核部门")
    private String assessmentDepartment;

    @ApiModelProperty(value = "考核岗位")
        private String post;

    /** 删除  0 未删除 1 删除 */
        @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除  0 未删除 1 删除")
    private Integer deleted;

        @TableField(exist = false)
    @ApiModelProperty(value = "发布状态 字典code(RELEASE_STATUS)")
    private String releaseStatus;

    @ApiModelProperty(value = "年份")
    @TableField(exist = false)
    private String yearly;

}
