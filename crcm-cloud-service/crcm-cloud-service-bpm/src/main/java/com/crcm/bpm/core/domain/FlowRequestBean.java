package com.crcm.bpm.core.domain;

import lombok.Data;

import java.util.Map;

/**
 * 流程传输Bean
 * @author Spur
 * @date 2019-11-27
 */
@Data
public class FlowRequestBean {
    // 流程定义ID
    private String procDefKey;

    // 业务ID
    private String bizKey;

    // 流程实例ID
    private String procInsId;

    // 任务ID
    private String taskId;

    // 标题
    private String title;

    // 意见
    private String comment;

    // 发起人
    private String applyUser;

    // 处理人
    private String userId;

    // 跳转目标节点
    private String targetNodeId;

    // 变量
    private Map<String, Object> map;
}
