package com.crcm.bpm.service;

import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/22 16:06 <br>
 * @Author: <a>bot</a>
 */
public interface ProcessCirculationService {

    /**
     * 审批拒绝
     *
     * @param processCirculationDto
     * @return
     */
    Boolean rejectProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 审批中止
     *
     * @param processCirculationDto
     * @return
     */
    Boolean failProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 审批通过
     *
     * @param processCirculationDto
     * @return
     */
    Boolean passProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 审批退回
     *
     * @param processCirculationDto
     * @return
     */
    Boolean randomReturnProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 审批撤销
     *
     * @param processCirculationDto
     * @return
     */
    Boolean cancelProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 审批会签
     *
     * @param processCirculationDto
     * @return
     */
    Boolean signProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 撤回任务
     *
     * @param processCirculationDto
     * @return
     */
    Boolean recallProcess(ProcessCirculationDto processCirculationDto);

    /**
     * 遍历forDataMap检查是否有文件，修改附件表
     *
     * @param applyId
     * @param forDataMap
     * @return
     */
    Boolean editAttachments(String applyId, Map<String, Object> forDataMap);

    /**
     * 完成任务并判断是否有候选人，若没有则直接跳过
     * @param taskId
     * @param actTaskId
     * @param applyId
     * @param processMap
     */
    void complete(Long taskId, String actTaskId, Long applyId, Map<String, Object> processMap, String applyTitle);

    /**
     * 自动完成下一任务
     *
     * @param applyId
     * @param processMap
     * @return
     */
    List<FlowUserTaskDTO> autoComplete(Long applyId, Map<String, Object> processMap, String applyTitle);

    /**
     * 挂起
     *
     * @param processCirculationDto
     * @return
     */
    Boolean setTaskHang(ProcessCirculationDto processCirculationDto);

    /**
     * 抄送
     *
     * @param processCirculationDto
     * @return
     */
    Boolean copySend(ProcessCirculationDto processCirculationDto);

    /**
    * @Description:  转派
    * @Param:
    * @Date: 2022/3/24 10:08
    */
    Boolean reassignment(ProcessCirculationDto processCirculationDto);
}
