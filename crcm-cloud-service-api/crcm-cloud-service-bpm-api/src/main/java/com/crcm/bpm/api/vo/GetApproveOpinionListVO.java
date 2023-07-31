package com.crcm.bpm.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/2 16:40 <br>
 * @Author: <a>bot</a>
 */
@Data
public class GetApproveOpinionListVO implements Serializable {

    /**
     * 用户任务编号
     */
    private Long id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务处理人工号
     */
    private String taskAssigneeUserId;

    /**
     * 任务处理人姓名
     */
    private String taskAssigneeRealName;

    /**
     * 任务审批时间
     */
    private LocalDateTime approveTime;

    /**
     * 审批意见
     */
    private String approveOpinion;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 审批操作
     */
    private String approveAction;

    /**
     * 审批操作Code
     */
    private String approveActionCode;
}
