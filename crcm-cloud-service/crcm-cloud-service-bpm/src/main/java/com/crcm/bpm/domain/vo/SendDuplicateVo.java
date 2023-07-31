package com.crcm.bpm.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 抄送
 */
@Data
@TableName("bpm_send_duplicate")
public class SendDuplicateVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请编号
     */
    private Long applyId;

    /**
     * 流程编号
     */
    private Long processId;

    /**
     * 流程实例编号
     */
    private String procInstId;

    /**
     * 对应任务id
     */
    private Long taskId;

    /**
     * 任务对应节点编号
     */
    private String taskNodeCode;

    /**
     * 任务抄送人id
     */
    private String createUserId;

    /**
     * 接收人id
     */
    private String taskAssigneeUserId;

    /**
     * 接收人姓名
     */
    private String taskAssigneeRealName;

    /**
     * 状态（1：未读 2：已读）
     */
    private Integer taskStatus;

    /**
     * 表单关联KEY
     */
    private String formKey;

    /**
     * 表单可编辑字段
     */
    private String formEditField;

    /**
     * 任务阅读时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date claimTime;

    @TableField(exist = false)
    private String applyTitle;

    @TableField(exist = false)
    private Long modelId;

    @TableField(exist = false)
    private String appParam;

    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}