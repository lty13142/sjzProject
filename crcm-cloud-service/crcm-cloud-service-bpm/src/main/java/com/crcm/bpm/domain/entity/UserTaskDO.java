package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Title:用户任务表 </p>
 * <p>Description:用户任务表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author ${Author}
 * @Date  2020-10-09
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户任务表")
@TableName("bpm_user_task")
public class UserTaskDO implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * 用户任务编号
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "用户任务编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * flowable 任务编号
     * act_task_id：varchar(32) ==》 actTaskId：String
     */
    @ApiModelProperty(value = "flowable 任务编号")
    private String actTaskId;
    /**
     * 申请编号
     * apply_id：int ==》 applyId：Integer
     */
    @ApiModelProperty(value = "申请编号")
    private Long applyId;
    /**
     * 流程编号
     * process_id：int ==》 processId：Integer
     */
    @ApiModelProperty(value = "流程编号")
    private Long processId;
    /**
     * 模型ID
     * model_id：int ==》 modelId：Long
     */
    @ApiModelProperty(value = "模型ID")
    private Long modelId;
    /**
     * 流程实例编号
     * proc_inst_id：varchar(32) ==》 procInstId：String
     */
    @ApiModelProperty(value = "流程实例编号")
    private String procInstId;
    /**
     * 所属租户
     * tenant_id：varchar(32) ==》 tenantId：String
     */
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 任务名称
     * task_name：varchar(64) ==》 taskName：String
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    /**
     * 任务类型
     * task_type：varchar(16) ==》 taskType：String
     */
    @ApiModelProperty(value = "任务类型")
    private String taskType;
    /**
     * 任务对应节点编号
     * task_node_code：varchar(32) ==》 taskNodeCode：String
     */
    @ApiModelProperty(value = "任务对应节点编号")
    private String taskNodeCode;
    /**
     * 父级任务编号
     * parent_task_id：int ==》 parentTaskId：Integer
     */
    @ApiModelProperty(value = "父级任务编号")
    private Long parentTaskId;
    /**
     * 任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务
     * task_status：int ==》 taskStatus：Integer
     */
    @ApiModelProperty(value = "任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务 7:挂起")
    private Integer taskStatus;
    /**
     * 候选用户列表
     * candidate_user_list：text ==》 candidateUserList：String
     */
    @ApiModelProperty(value = "候选用户列表")
    private String candidateUserList;
    /**
     * 候选用户名列表
     * candidate_user_list：text ==》 candidateUserList：String
     */
    @ApiModelProperty(value = "候选用户名列表")
    private String candidateUserNameList;
    /**
     * 任务归属人ID
     * task_owner_user_id：int ==》 taskOwnerUserId：Integer
     */
    @ApiModelProperty(value = "任务归属人ID")
    private String taskOwnerUserId;
    /**
     * 任务归属人姓名
     * task_owner_real_name：varchar(32) ==》 taskOwnerRealName：String
     */
    @ApiModelProperty(value = "任务归属人姓名")
    private String taskOwnerRealName;
    /**
     * 任务处理人工号
     * task_assignee_user_id：varchar(32) ==》 taskAssigneeUserId：String
     */
    @ApiModelProperty(value = "任务处理人工号")
    private String taskAssigneeUserId;
    /**
     * 任务处理人姓名
     * task_assignee_real_name：varchar(32) ==》 taskAssigneeRealName：String
     */
    @ApiModelProperty(value = "任务处理人姓名")
    private String taskAssigneeRealName;
    /**
     * 任务优先等级
     * task_priority：int ==》 taskPriority：Integer
     */
    @ApiModelProperty(value = "任务优先等级")
    private Integer taskPriority;
    /**
     * 表单关联KEY
     * form_key：varchar(32) ==》 formKey：String
     */
    @ApiModelProperty(value = "表单关联KEY")
    private String formKey;
    /**
     * 任务认领时间
     * claim_time：datetime ==》 claimTime：Date
     */
    @ApiModelProperty(value = "任务认领时间")
    private LocalDateTime claimTime;
    /**
     * 任务到期时间
     * due_time：datetime ==》 dueTime：Date
     */
    @ApiModelProperty(value = "任务到期时间")
    private LocalDateTime dueTime;
    /**
     * 任务审批时间
     * approve_time：datetime ==》 approveTime：Date
     */
    @ApiModelProperty(value = "任务审批时间")
    private LocalDateTime approveTime;
    /**
     * 任务备注
     * remarks：varchar(2000) ==》 remarks：String
     */
    @ApiModelProperty(value = "任务备注")
    private String remarks;

    /**
     * 节点动作集合，用于控制按钮
     * action_list：varchar(1000) ==》 actionList：String
     */
    @ApiModelProperty(value = "节点动作集合，用于控制按钮")
    private String actionList;

    /**
     * 流程表单可编辑字段
     * form_edit_field：text ==》 formEditField：String
     */
    @ApiModelProperty(value = "流程表单可编辑字段")
    private String formEditField;

    /**
     * 审批意见
     * approve_opinion：varchar(1000) ==》 approveOpinion：String
     */
    @ApiModelProperty(value = "审批意见")
    private String approveOpinion;

    /**
     * 审批操作
     */
    @ApiModelProperty(value = "审批操作")
    private String approveAction;

    /**
     * 审批结果
     * approve_opinion：varchar(16) ==》 approveResult：String
     */
    @ApiModelProperty(value = "审批结果")
    private String approveResult;
    /**
     * 申请节点（0，不是申请节点，1是申请节点）
     * apply_node：int ==》 applyNode：Integer
     */
    @ApiModelProperty(value = "申请节点（0，不是申请节点，1是申请节点）")
    private Integer applyNode;

    /**
     * 来源操作（上一节点操作）
     */
    @ApiModelProperty(value = "审批操作")
    private String sourceAction;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)   //公共字段自动填充（新增），详见com.crcm.core.config.mybatis.MybatisMateHandler
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//公共字段自动填充（新增、修改）
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 表单类型：0：组件表单  1：自定义表单
     */
    @TableField(exist = false)
    private String formType;

    /**
     * 关联表单Id（或自定义表单标识code）
     */
    @TableField(exist = false)
    private String formId;

    /**
     * 流程定义ID（部署流程的ID）
     */
    @TableField(exist = false)
    private String processDefId;
}
