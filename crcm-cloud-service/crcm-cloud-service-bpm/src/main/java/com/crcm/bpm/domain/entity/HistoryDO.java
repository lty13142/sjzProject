package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("流程审批记录")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("bpm_history")
public class HistoryDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 历史数据编号
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "ID")
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
    private Integer parentTaskId;
    /**
     * 任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务
     * task_status：int ==》 taskStatus：Integer
     */
    @ApiModelProperty(value = "任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务")
    private Integer taskStatus;
    /**
     * 候选用户列表
     * candidate_user_list：text ==》 candidateUserList：String
     */
    @ApiModelProperty(value = "候选用户列表")
    private String candidateUserList;
    /**
     * 候选用户名列表
     * candidate_user_name_list：text ==》 candidateUserNameList：String
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
     * 审批意见
     * approve_opinion：varchar(1000) ==》 approveOpinion：String
     */
    @ApiModelProperty(value = "审批意见")
    private String approveOpinion;
    /**
     * 审批结果
     * approve_opinion：varchar(16) ==》 approveResult：String
     */
    @ApiModelProperty(value = "审批结果")
    private String approveResult;
    /**
     * 审批操作
     */
    @ApiModelProperty(value = "审批操作")
    private String approveAction;
    /**
     * 可编辑字段
     */
    @ApiModelProperty(value = "可编辑字段")
    private String formEditField;
    /**
     * 申请节点（0，不是申请节点，1是申请节点）
     * apply_node：int ==》 applyNode：Integer
     */
    @ApiModelProperty(value = "申请节点（0，不是申请节点，1是申请节点）")
    private Integer applyNode;
}