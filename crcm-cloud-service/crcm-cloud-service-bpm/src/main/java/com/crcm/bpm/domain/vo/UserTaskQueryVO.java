package com.crcm.bpm.domain.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserTaskQueryVO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/15
 **/
@Data
@ToString
public class UserTaskQueryVO implements Serializable {

    private static final long serialVersionUID = 6719395813199160878L;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    private LocalDateTime applyStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    private LocalDateTime applyEndDate;

    private String flowType;

    private String searchName;

    // APP查询参数，移动端一个参数查询多个字段（申请编号、申请标题、流程名称、申请人）
    private String appParam;
}
