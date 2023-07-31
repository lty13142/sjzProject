package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName UserTaskDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
@ToString
public class UserTaskDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -139098823960490889L;

    private Long id;
    /**
     * flowable 任务编号
     */
    private String actTaskId;
    /**
     * 申请编号
     */
    private Long applyId;
    /**
     * 流程编号
     */
    private Long processId;
    /**
     * 流程编号
     */
    private Long modelId;
    /**
     * 流程实例编号
     */
    private String procInstId;
    /**
     * 所属租户
     */
    private String tenantId;

    private String taskType;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务对应节点编号
     */
    private String taskNodeCode;
    /**
     * 父级任务编号
     */
    private Long parentTaskId;
    /**
     * 任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务 7：挂起
     */
    private Integer taskStatus;
    /**
     * 角色组编号
     */
    private String roleGroupCode;
    /**
     * 角色编号
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 任务归属人工号
     */
    private String taskOwnerUserId;
    /**
     * 任务归属人姓名
     */
    private String taskOwnerRealName;
    /**
     * 任务处理人工号
     */
    private String taskAssigneeUserId;
    /**
     * 任务处理人姓名
     */
    private String taskAssigneeRealName;
    /**
     * 任务优先等级
     */
    private Integer taskPriority;
    /**
     * 表单关联KEY
     */
    private String formKey;
    /**
     * 任务认领时间
     */
    private Date claimTime;
    /**
     * 任务到期时间
     */
    private LocalDateTime dueTime;
    /**
     * 任务审批时间
     */
    private LocalDateTime approveTime;
    /**
     * 来源系统
     */
    private String system;
    /**
     * 来源平台
     */
    private String paltform;
    /**
     * 任务备注
     */
    private String remarks;
    /**
     * 审批意见
     */
    private String approveOpinion;
    /**
     * 审批结果
     */
    private String approveResult;
    /**
     * 审批操作
     */
    private String approveAction;
    /**
     * 节点动作集合，用于控制按钮
     */
    private String actionList;

    /**
     * 流程表单可编辑字段
     */
    private String formEditField;
    /**
     * 候选用户列表
     */
    private String candidateUserList;
    /**
     * 候选用户名列表
     */
    private String candidateUserNameList;

    // 是否申请节点
    private int applyNode;
}
