package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @ClassName FormDataSaveOrUpdateDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/16
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormDataSaveOrUpdateDTO {
    /**
     * 流程申请ID
     */
    private Long applyId;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 表单KEY
     */
    private String formKey;
    /**
     * 流程数据
     */
    private Map<String, Object> dataMap;
    /**
     * 用户任务节点数据
     */
    private UserTaskDTO userTaskDTO;
    /**
     * 流程实例ID
     */
    private String procInstId;
    /**
     * 系统业务流程ID
     */
    private Long processId;
}
