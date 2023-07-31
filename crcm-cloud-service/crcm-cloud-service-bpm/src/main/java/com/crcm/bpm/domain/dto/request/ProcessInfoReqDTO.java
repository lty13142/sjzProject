package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName ProcssInfoReqDTO
 * @Description：流程信息请求转换体
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/13
 **/
@Data
@ToString
public class ProcessInfoReqDTO extends BaseRequestDTO {
    /**
     * 流程ID
     */
    private Long processId;
    /**
     * 流程KEY
     */
    private String processKey;
    /**
     * 是否是主版本
     */
    private Integer mainVersion;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 部门名
     */
    private String departmentName;
}
