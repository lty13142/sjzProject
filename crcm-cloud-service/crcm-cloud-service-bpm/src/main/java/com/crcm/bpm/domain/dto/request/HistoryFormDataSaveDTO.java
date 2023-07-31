package com.crcm.bpm.domain.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName HistoryFormDataSaveDTO
 * @Description：历史流程数据保存或修改类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/14
 **/
@Data
@Accessors(chain = true)
public class HistoryFormDataSaveDTO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 表单id
     */
    private Long formId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 流程id
     */
    private Long processId;
    /**
     * 申请编号
     */
    private Long applyId;
    /**
     * 流程实例ID
     */
    private String procInstId;
    /**
     * 任务ID
     */
    private String taskNodeId;
    /**
     * 流程节点数据
     */
    private Map<String, Object> processMap;
}
