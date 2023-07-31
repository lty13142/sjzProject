package com.crcm.bpm.domain.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @ClassName CountersignDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/10
 **/
@Data
@Accessors(chain = true)
public class CountersignCompletedDTO {

    /**
     * 会签人ID
     */
    private String assignee;
    /**
     * 审核（会签）结果
     */
    private String approveResult;
    /**
     * 会签意见
     */
    private String approveOpinion;
    /**
     * 会签时间
     */
    private LocalDateTime approveTime;
    /**
     * 会签状态 0 未签 1 已签
     */
    private Integer status;
    /**
     * 流程编号
     */
    private Long processId;
    /**
     * 申请编号
     */
    private Long applyId;
    /**
     * 流程实例编号
     */
    private String procInstId;
    /**
     * 任务编号
     */
    private Long taskId;

}
