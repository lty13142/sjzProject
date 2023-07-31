package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserTaskInfoQueryDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/15
 **/
@Data
@ToString
public class UserTaskInfoQueryDTO extends BaseRequestDTO {

    private Long applyId;

    private String applyTitle;

    private Long processId;

    private String processName;

    private String procInstId;

    private List<Long> processIdList;

    private String applySn;

    private Integer applyStatus;

    private String companyCode;

    private String deptCode;

    private String tenantId;

    private String applyUserId;

    private String applyRealName;

    private String system;

    private Long taskId;

    private String taskNodeCode;

    private String taskName;

    private Long lastId;

    private Integer taskStatus;

    private LocalDateTime applyStartDate;

    private LocalDateTime applyEndDate;

    private String searchName;
    /**
     * 流程类型
     */
    private String flowType;

    // APP查询参数，移动端一个参数查询多个字段（申请编号、申请标题、流程名称、申请人）
    private String appParam;

}
