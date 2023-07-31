package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @program: open-ai-center
 * @description:
 * @author: zxb
 * @create: 2021-03-31 09:11
 **/
@Data
@ToString
public class SendDuplicateDTO extends BaseResponseDTO {
    private static final long serialVersionUID = 6843259705146367299L;

    private long id;
    private long applyId;
    private long processId;
    private String procInstId;
    private long taskId;
    private String taskNodeCode;
    private String createUserId;
    private String taskAssigneeUserId;
    private String taskAssigneeRealName;
    private int taskStatus;
    private String formKey;
    private Date claimTime;
    private String applyTitle;
    private String applyStatus;
    private String applyCompanyName;
    private String modelId;
    private String processName;
    private String applyDeptName;
    private Date applyDate;

    private String formType;
    private String formId;
    private String formCode;

    private String formEditField;
}
