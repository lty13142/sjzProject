package com.crcm.bpm.domain.dto.request;

import lombok.Data;

/**
 * @ClassName TaskRecallVo
 * @Description：任务撤回DTO
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/18
 **/
@Data
public class TaskRecallReqDTO {

    /**
     * 历史任务ID
     */
    private Long hisTaskId;
    /**
     * 备注/意见
     */
    private String opinion;
}
