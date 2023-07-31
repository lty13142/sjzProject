package com.crcm.bpm.api.dto.request;

import com.crcm.bpm.api.dto.BaseRequestDTO;

import java.util.Date;

/**
 * @ClassName ProcessQueryDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/2020/9/24/14:01
 **/
public class ProcessQueryDTO extends BaseRequestDTO {
    /**
     * 流程ID
     */
    private Long id;
    /**
     * 流程编号
     */
    private String processNo;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 所属公司
     */
    private Long companyId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 流程状态
     */
    private Integer processStatus;
    /**
     * 流程模型ID
     */
    private Long modelId;
}
