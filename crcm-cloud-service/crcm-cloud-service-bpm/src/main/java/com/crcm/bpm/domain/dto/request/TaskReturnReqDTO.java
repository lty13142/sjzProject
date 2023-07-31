package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.Data;

/**
 * @ClassName TaskReturnReqDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/17
 **/
@Data
public class TaskReturnReqDTO extends BaseRequestDTO {
    /**
     * 任务ID
     */
    Long taskId;
    /**
     * 目标节点退回key
     */
    String returnNodeId;
    /**
     * 备注/意见
     */
    String approveOpinion;
}
