package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName ClaimTaskDTO
 * @Description：签收业务数据传输对象
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/19
 **/
@Data
public class ClaimTaskDTO extends BaseRequestDTO {
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 签收人
     */
    private String claimId;
    /**
     * 签收人名称
     */
    private String claimName;
    /**
     * 业务数据
     */
    private Map<String,Object> businessData;
}
