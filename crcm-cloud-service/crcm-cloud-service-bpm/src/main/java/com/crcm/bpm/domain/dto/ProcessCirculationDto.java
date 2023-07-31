package com.crcm.bpm.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/23 11:40 <br>
 * @Author: <a>bot</a>
 */
@Data
public class ProcessCirculationDto implements Serializable {

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 申请id
     */
    private Long applyId;

    /**
     * 流程actTaskId
     */
    private Long actTaskId;

    /**
     * 审批意见
     */
    private String approveOpinion;

    /**
     * 审批操作code
     */
    private String approveActionCode;

    /**
     * 业务数据
     */
    private Map<String, Object> businessData;

    /**
     * 退回节点
     */
    private String returnNodeId;

    /**
     * 指定下一节点审核人ID集合
     */
    private List<String> assigneeList;

    /**
     * 指定下一节点审核人名称集合
     */
    private List<String> assigneeNameList;

    /**
     * 会签审核结果  pass 同意 reject 不同意
     */
    private String approveResult;
    /**
     * 是否自动审批
     */
    private boolean autoPass = false;

    public ProcessCirculationDto(){}
}
