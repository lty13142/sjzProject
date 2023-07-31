package com.crcm.bpm.domain.dto;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author ${Author}
 */
@Data
public class UserTaskMsgDto implements Serializable{
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
     * 申请标题
     * applyTitle
     */
    @ApiModelProperty(value = "申请标题")
    private String applyTitle;
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
     * 任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务
     * task_status：int ==》 taskStatus：Integer
     */
    @ApiModelProperty(value = "任务状态 1  表示未认领 2 表示已认领 3 表示已完成 4表示已取消 5：取消重新发起 6:找不到人员系统自动完成任务")
    private Integer taskStatus;

    /**
     * 表单关联KEY
     * form_key：varchar(32) ==》 formKey：String
     */
    @ApiModelProperty(value = "表单关联KEY")
    private String formKey;

    @ApiModelProperty(value = "表单类型：0：组件表单  1：自定义表单")
    private String formType;

    @ApiModelProperty(value = "关联表单Id")
    private String formId;

    @ApiModelProperty(value = "自定义表单标识code")
    private String formCode;

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
     * 流程定义ID（部署流程的ID）
     */
    @TableField(exist = false)
    private String processDefId;

}
