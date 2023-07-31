package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CompleteTaskDTO
 * @Description：提交任务数据传输DTO
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/16
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CompleteTaskDTO extends BaseRequestDTO {

    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 审核人ID
     */
    private String approveId;
    /**
     * 审核人名称
     */
    private String approveName;
    /**
     * 任务ID
     */
    private List<Long> taskIds;
    /**
     * 业务数据
     */
    private Map<String,Object> businessData;
    /**
     * 审核意见
     */
    private String approveOpinion;
    /**
     * 审核操作代码
     */
    private String approveActionCode;
    /**
     * 会签审核结果  pass 同意 reject 不同意
     */
    private String approveResult;

    /**
     * 退回节点定义ID
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
}
