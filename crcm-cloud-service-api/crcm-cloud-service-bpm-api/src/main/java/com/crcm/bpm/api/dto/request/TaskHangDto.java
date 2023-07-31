package com.crcm.bpm.api.dto.request;

import lombok.Data;

/**
 * @author
 */
@Data
public class TaskHangDto {

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 申请id
     */
    private Long applyId;
}
