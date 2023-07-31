package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.api.enums.ApproveActionEnum;
import com.crcm.bpm.domain.dto.BaseResponseDTO;
import com.crcm.bpm.utils.EnumHelperUtil;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName UserTaskInfoDTO
 * @Description：用户任务信息
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/15
 **/
@Data
@ToString
public class UserTaskInfoDTO extends BaseResponseDTO {
    private static final long serialVersionUID = 6843259705146367299L;
    private Long applyId;

    private String applyTitle;

    private String applySn;

    private String procInstId;

    private String applyUserId;

    private String applyRealName;

    private String applyDeptId;

    private String applyDeptCode;

    private String applyDeptName;

    private String applyCompanyId;

    private String applyCompanyCode;

    private String applyCompanyName;

    private String tenantId;

    private Long processId;

    private String processKey;

    private String processName;

    private String definitionId;

    private String modelId;

    /**
     * 节点动作集合，用于控制按钮
     */
    private String actionList;

    private String flowType;

    private String sourceSystem;
    /**
     * 来源编码
     */
    private String platform;

    private Long parentApplyId;

    private Integer applyStatus;

    private String remarks;

    private Integer validState;

    private String operatorId;

    private String operatorName;

    private Long taskId;
    /**
     * flowable 任务编号
     */
    private String actTaskId;
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
     * 任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务
     */
    private Integer taskStatus = 0;
    /**
     * 角色组编号
     */
    private String roleGroupCode;
    /**
     * 角色编号
     */
    private String roleCode;

    private String taskType;


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

    private String currentApproveName;

    private LocalDateTime applyDate;

    /**
     * 流程表单可编辑字段
     */
    private String formEditField;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 审批操作
     */
    private String approveAction;
    /**
     * 审批意见
     */
    private String approveOpinion;
    /**
     * 审批结果
     */
    private String approveResult;

    /**
     * 当前任务的下一步处理的taskId
     */
    private String doingTaskId;

    /**
     * 关联表单类型 0：组件表单  1：自定义表单
     */
    private String formType;

    /**
     * 关联表单Id
     */
    private String formId;

    /**
     * 自定义表单标识code
     */
    private String formCode;

    /**
     * 是否老数据
     */
    private Boolean isOldData;

    /**
     * svg
     */
    private String processSvg;

    /**
     * 来源操作
     */
    private String sourceAction;

//    public String getApplyStatusCh() {
//        return UtilDic.getDictName(DictConstant.FLOW_APPLY_STATUS.CODE, String.valueOf(this.applyStatus));
//    }
//
//    public String getFlowTypeCh() {
//        return UtilDic.getDictName(DictConstant.FLOW_TYPE.CODE, String.valueOf(this.flowType));
//    }
//
//    public String getTaskTypeCh() {
//        return UtilDic.getDictName(DictConstant.FLOW_TASK_TYPE.CODE, String.valueOf(this.taskType));
//    }
//
    public String getApproveActionCh() {
        if(StringUtils.isNotBlank(this.approveAction)) {
            return (String) EnumHelperUtil.customEnumUtil(ApproveActionEnum.class).getDesc(this.approveAction);
        }
        return "";
    }
}
