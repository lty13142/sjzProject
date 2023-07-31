package com.crcm.bpm.domain.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName CompleteTaskVO
 * @Description：提交任务接口数据
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/16
 **/
@Data
@ToString
public class CompleteTaskVO implements Serializable {

    private static final long serialVersionUID = 4713495052653320297L;
    /**
     * 业务数据
     */
    private Map<String,Object> businessData;
    /**
     * 任务ID
     */
    @NotNull
    private Long taskId;
    /**
     * 审批操作代码
     */
    @NotNull
    private String approveActionCode;
    /**
     * 审核意见
     */
    private String approveOpinion;
    /**
     * 审核结果  pass 同意 reject 反对
     */
    private String approveResult;
    /**
     * 退回节点定义ID
     */
    private String returnNodeId;
}
