package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.FindUserTypeConstant;
import com.crcm.bpm.core.constant.FormFormatConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.constant.enums.SysMessageStatusEnum;
import com.crcm.bpm.core.constant.enums.SysMessageTypeEnum;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.handler.TaskHandler;
import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.core.utils.FlowElementUtils;
import com.crcm.bpm.core.utils.SendTaskMessageUtil;
import com.crcm.bpm.domain.dto.DoActionDto;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.request.FormDataSaveOrUpdateDTO;
import com.crcm.bpm.domain.dto.response.ApplyDTO;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.dto.response.NodeInfoDTO;
import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import com.crcm.bpm.domain.entity.*;
import com.crcm.bpm.service.*;
import com.crcm.bpm.utils.FormDataUtil;
import com.crcm.bpm.utils.PrecessThreadLocalUtil;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: <a>bot</a>
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProcessCirculationServiceImpl implements ProcessCirculationService {

    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    @Lazy
    private TaskHandler taskHandler;

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private ApproveOpinionService approveOpinionService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FormDataService formDataService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private SendDuplicateService sendDuplicateService;

    @Resource
    private RemoteFileService remoteSystemFileService;
//
//    @Resource
//    private FeignSignatureService feignSignatureService;

    private ThreadLocal<Integer> threadLocal = new ThreadLocal();

    @Override
    public Boolean rejectProcess(ProcessCirculationDto processCirculationDto) {
        return doRejectProcess(doActionPrefix(processCirculationDto));
    }

    private DoActionDto doActionPrefix(ProcessCirculationDto processCirculationDto) {
        UserTaskDTO userTaskDTO = userTaskService.getUserTaskByTaskId(processCirculationDto.getTaskId());
        if (userTaskDTO == null || userTaskDTO.getId() == null) {
            throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "任务不存在，请确认任务信息！");
        }
        if (TaskConstant.COUNTERSIGN_OUTCOME_PASS.equals(processCirculationDto.getApproveActionCode())) {
            processCirculationDto = formatFormJson(processCirculationDto, userTaskDTO);
        }
        // 挂起状态
        if (userTaskDTO != null && userTaskDTO.getTaskStatus() != null && userTaskDTO.getTaskStatus() == TaskConstant.TASK_HANG) {
            ApplyDO applyDO = new ApplyDO();
            applyDO.setId(userTaskDTO.getApplyId());
            // 设置申请状态审批中
            applyDO.setApplyStatus(2);
            applyService.updateById(applyDO);
        }
        TaskService taskService = processEngine.getTaskService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> actionMap = new HashMap<>();
        actionMap.put(TaskConstant.APPROVE_OPINION_DESC, processCirculationDto.getApproveOpinion());
        DoActionDto doActionDto = DoActionDto.builder()
                .processCirculationDto(processCirculationDto)
                .userTaskDTO(userTaskDTO)
                .taskService(taskService)
                .runtimeService(runtimeService)
                .actionMap(actionMap)
                .build();
        // 获取表单数据
        Map<String, Object> businessDataMap = processCirculationDto.getBusinessData() == null ? new HashMap<>() : processCirculationDto.getBusinessData();
        // 解析表单数据
        Map<String, Object> dataMap = JSON.parseObject(JSON.toJSONString(businessDataMap.get(BpmConstant.FORM_DATA_KEY)), Map.class);
        if (dataMap != null) {
            for (String key : dataMap.keySet()) {
                businessDataMap.put(key, FormDataUtil.toDecimal(dataMap.get(key)));
            }
        }
        Object assigneeData = businessDataMap.get(TaskConstant.ASSIGNEE_DATA);
        // 该数据不入库
        businessDataMap.remove(TaskConstant.ASSIGNEE_DATA);
        // 保存或更新表单数据
        FormDataSaveOrUpdateDTO formDataSaveOrUpdateDTO = FormDataSaveOrUpdateDTO.builder()
                .applyId(userTaskDTO.getApplyId())
                .userTaskDTO(userTaskDTO)
                .processId(userTaskDTO.getProcessId())
                .procInstId(userTaskDTO.getProcInstId())
                .formKey(userTaskDTO.getFormKey())
                .dataMap(businessDataMap).build();
        formDataService.batchSaveOrUpdateFormData(formDataSaveOrUpdateDTO);
        actionMap.putAll(businessDataMap);
        // 在自动完成任务的时候去除上一节点选择的前端指定人员，避免下一节点审批人出错
        if (!processCirculationDto.isAutoPass()) {
            actionMap.put(TaskConstant.ASSIGNEE_DATA, assigneeData);
        }
        return doActionDto;
    }

    private ProcessCirculationDto formatFormJson(ProcessCirculationDto processCirculationDto, UserTaskDTO userTaskDTO) {
        Map<String, Object> businessData = processCirculationDto.getBusinessData();
        if (businessData == null || businessData.get(BpmConstant.FORM_JSON_KEY) == null) {
            return processCirculationDto;
        }
        String jsonStr = businessData.get(BpmConstant.FORM_JSON_KEY).toString();
        String formEditField = userTaskDTO.getFormEditField();
        String[] formEditFields = formEditField.split(",");
        JSONObject formJsonObject = JSONObject.parseObject(jsonStr);
        JSONArray jsonObjectList = formJsonObject.getJSONArray(FormFormatConstant.FIELDS);
        Map<String, JSONObject> jsonObjectMap = new HashMap<>();
        Map<String, String> stringMap = new HashMap<>();
        jsonObjectList.forEach(jsonObject -> {
            JSONObject newJsonObject = (JSONObject) jsonObject;
            if (newJsonObject.get(FormFormatConstant.FIELDS_TABLE_VMODEL) != null) {
                if (newJsonObject.get(FormFormatConstant.FORM_TYPE) != null && FormFormatConstant.SIGNATURE_TYPE.equals(newJsonObject.get(FormFormatConstant.FORM_TYPE))) {
                    String vModel = newJsonObject.get(FormFormatConstant.FIELDS_TABLE_VMODEL).toString();
                    jsonObjectMap.put(vModel, newJsonObject);
                    stringMap.put(vModel, newJsonObject.get(FormFormatConstant.FORM_TYPE).toString());
                }
            }
        });
        if (!stringMap.isEmpty()) {
            for (String editField : formEditFields) {
                if (stringMap.get(editField) != null) {
                    // TODO 对接个人签章接口
//                    RestResult restResult = feignSignatureService.getSignature(SecurityUtil.getCurrentUserNoNull().getUserId(), AuthConstants.FROM_IN);
                    RestResult restResult =  RestResult.failed();
                    if (restResult.getCode() != HttpStatus.HTTP_OK) {
                        throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, "个人签章信息查询失败");
                    }
                    if (restResult.getData() == null || StringUtils.isEmpty(restResult.getData().toString())) {
                        throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "未查询到签章，请确认个人签章信息");
                    }
                    JSONObject newJsonObject = jsonObjectMap.get(editField);
                    newJsonObject.put(FormFormatConstant.SIGNATURE_SRC, restResult.getData().toString());
                }
            }
        }
        businessData.put(BpmConstant.FORM_JSON_KEY, JSON.toJSONString(formJsonObject));
        processCirculationDto.setBusinessData(businessData);
        return processCirculationDto;
    }

    private Boolean doRejectProcess(DoActionDto doActionDto) {
        Assert.isTrue(StringUtils.isNotEmpty(doActionDto.getUserTaskDTO().getProcInstId()), "procInstId不能为空");
        doCheck(doActionDto);
        // 设置审批结果
        doActionDto.getActionMap().put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_REJECT);
        doActionDto.getTaskService().setVariables(doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getActionMap());
        updateUserTask(doActionDto);
        // 删除流程实例
        doActionDto.getRuntimeService().deleteProcessInstance(doActionDto.getUserTaskDTO().getProcInstId(), TaskConstant.APPROVE_ACTION_FAIL);
        //删除用户任务同时保存历史记录
        userTaskService.removeAndSaverHistory(doActionDto.getUserTaskDTO(), doActionDto.getProcessCirculationDto().getBusinessData());
        userTaskService.remove(new LambdaQueryWrapper<UserTaskDO>().eq(UserTaskDO::getApplyId, doActionDto.getUserTaskDTO().getApplyId()));
        ApplyDO applyDO = new ApplyDO();
        applyDO.setId(doActionDto.getUserTaskDTO().getApplyId());
        applyDO.setUpdateTime(LocalDateTime.now());
        applyDO.setApplyStatus(BpmConstant.APPLY_STATUS_REJECT);
        applyService.updateById(applyDO);
        return saveApproveOpinion(doActionDto, null);
    }

    private void doCheck(DoActionDto doActionDto) {
        if (doActionDto.getUserTaskDTO().getTaskStatus() > TaskConstant.TASK_CLAIM && doActionDto.getUserTaskDTO().getTaskStatus() != TaskConstant.TASK_HANG) {
            throw new BpmException(BpmError.TASK_HAS_COMPLETED);
        }
        String taskUserId = StringUtils.isNotBlank(doActionDto.getUserTaskDTO().getTaskAssigneeUserId()) ?
                doActionDto.getUserTaskDTO().getTaskAssigneeUserId() : doActionDto.getUserTaskDTO().getTaskOwnerUserId();
        if (!SecurityUtil.getCurrentUserNoNull().getUserId().equals(taskUserId)) {
            throw new BpmException(BpmError.TASK_CLAIM_BY_OTHER);
        }
    }

    private Boolean saveApproveOpinion(DoActionDto doActionDto, String taskName) {
        UserAccount userDetails = SecurityUtil.getCurrentUserNoNull();
        ApproveOpinionDO approveOpinionDO = new ApproveOpinionDO();
        approveOpinionDO.setActTaskId(Long.valueOf(doActionDto.getUserTaskDTO().getActTaskId()));
        approveOpinionDO.setTaskName(taskName != null ? taskName : doActionDto.getUserTaskDTO().getTaskName());
        approveOpinionDO.setApplyId(doActionDto.getUserTaskDTO().getApplyId());
        approveOpinionDO.setApproveOpinion(doActionDto.getProcessCirculationDto().getApproveOpinion());
        approveOpinionDO.setApproveAction(doActionDto.getActionMap().get(TaskConstant.APPROVE_ACTION_DESC).toString());
        approveOpinionDO.setCreateBy(userDetails.getUsername());
        approveOpinionDO.setCreateUserId(userDetails.getUserId());
        approveOpinionDO.setUpdateBy(userDetails.getUsername());
        approveOpinionDO.setUpdateUserId(userDetails.getUserId());
        return approveOpinionService.save(approveOpinionDO);
    }

    @Override
    public Boolean failProcess(ProcessCirculationDto processCirculationDto) {
        return doFailProcess(doActionPrefix(processCirculationDto));
    }

    private Boolean doFailProcess(DoActionDto doActionDto) {
        doActionDto.getActionMap().put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_FAIL);
        doActionDto.getTaskService().setVariables(doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getActionMap());
        if (StringUtils.isNotEmpty(doActionDto.getUserTaskDTO().getProcInstId())) {
            // 删除流程实例
            doActionDto.getRuntimeService().deleteProcessInstance(doActionDto.getUserTaskDTO().getProcInstId(), TaskConstant.APPROVE_ACTION_FAIL);
        }
        return saveApproveOpinion(doActionDto, null);
    }

    @Override
    public Boolean passProcess(ProcessCirculationDto processCirculationDto) {
        return doPassProcess(doActionPrefix(processCirculationDto));
    }

    private Boolean doPassProcess(DoActionDto doActionDto) {
        doCheck(doActionDto);
        doActionDto.getActionMap().put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_PASS);
        updateUserTask(doActionDto);
        saveApproveOpinion(doActionDto, null);
        // 完成任务
        complete(doActionDto.getUserTaskDTO().getId(), doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getUserTaskDTO().getApplyId(), doActionDto.getActionMap(), null);
        editAttachments(doActionDto.getUserTaskDTO().getApplyId().toString(), doActionDto.getProcessCirculationDto().getBusinessData());
        return true;
    }

    private Boolean updateUserTask(DoActionDto doActionDto) {
        UserTaskDO userTaskDO = BeanUtil.copyProperties(doActionDto.getUserTaskDTO(), UserTaskDO.class);
        userTaskDO.setUpdateTime(LocalDateTime.now());
        userTaskDO.setTaskAssigneeUserId(SecurityUtil.getCurrentUserNoNull().getUserId());
        userTaskDO.setTaskAssigneeRealName(SecurityUtil.getCurrentUserNoNull().getUsername());
        userTaskDO.setTaskStatus(TaskConstant.TASK_COMPLETED);
        userTaskDO.setApproveTime(LocalDateTime.now());
        userTaskDO.setApproveOpinion(doActionDto.getProcessCirculationDto().getApproveOpinion());
        userTaskDO.setDueTime(LocalDateTime.now());
        userTaskDO.setApproveAction(doActionDto.getActionMap().get(TaskConstant.APPROVE_ACTION_DESC).toString());
        userTaskDO.setApproveResult(doActionDto.getProcessCirculationDto().getApproveResult());
        doActionDto.setUserTaskDTO(BeanUtil.copyProperties(userTaskDO, UserTaskDTO.class));
        return userTaskService.updateById(userTaskDO);
    }

    @Override
    public Boolean editAttachments(String applyId, Map<String, Object> map) {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map.get(FormFormatConstant.FORM_DATA)));
        List<String> attachmentsIds = new ArrayList<>();
        jsonObject.forEach((k, v) -> {
            if (v.getClass() == JSONArray.class) {
                JSONArray jsonArray = (JSONArray) v;
                jsonArray.stream().anyMatch(json -> {
                    if (json == null || json.getClass() != JSONObject.class) {
                        return true;
                    }
                    JSONObject newJsonObject = (JSONObject) json;
                    // TODO 附加修改对接
                    if (FormFormatConstant.UPLOAD_FILE.equals(newJsonObject.get(FormFormatConstant.FORM_TYPE))) {
//                        AttachmentsEntity attachmentsEntity = new AttachmentsEntity();
//                        attachmentsEntity.setId(newJsonObject.get(FormFormatConstant.UPLOAD_FILE_ID).toString());
//                        attachmentsEntity.setBusinessId(applyId);
                        attachmentsIds.add(newJsonObject.get(FormFormatConstant.UPLOAD_FILE_ID).toString());
                    }
                    return false;
                });
            }
        });
        if (attachmentsIds.size() == 0) {
            return true;
        }
        RestResult restResult = remoteSystemFileService.updateBatchByBussinessId(String.join(",", attachmentsIds), applyId);
        if (restResult.getCode() != HttpStatus.HTTP_OK) {
            log.error("editAttachments调用remoteSystemFileService.editByIds失败，参数={}", String.join(",", attachmentsIds));
            throw new CustomException(HttpStatus.HTTP_BAD_METHOD, "附件上传失败");
        } else {
            return true;
        }
    }

    @Override
    public Boolean randomReturnProcess(ProcessCirculationDto processCirculationDto) {
        return doRandomReturnProcess(doActionPrefix(processCirculationDto));
    }

    private Boolean doRandomReturnProcess(DoActionDto doActionDto) {
        doCheck(doActionDto);
        UserTaskDTO userTaskDTO = doActionDto.getUserTaskDTO();
        BestBpmAsset.isEmpty(doActionDto.getProcessCirculationDto().getReturnNodeId(), "nodeId is not  allow empty .");
        String targetTaskDefKey = doActionDto.getProcessCirculationDto().getReturnNodeId();
        doActionDto.getActionMap().put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_RANDOM_RETURN);
        doActionDto.getActionMap().put(TaskConstant.TASK_ID, doActionDto.getProcessCirculationDto().getTaskId());
        doActionDto.getActionMap().put(TaskConstant.RETURN_NODE_ID, doActionDto.getProcessCirculationDto().getReturnNodeId());
        doActionDto.getTaskService().setVariables(doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getActionMap());
        log.info("流程申请编号[{}],流程实例[{}],源节点[{}]，目标节点[{}]",
                doActionDto.getUserTaskDTO().getApplyId(), doActionDto.getUserTaskDTO().getProcInstId(), doActionDto.getUserTaskDTO().getTaskNodeCode(), targetTaskDefKey);
        // 退回到指定节点
        doActionDto.getRuntimeService().createChangeActivityStateBuilder()
                .processInstanceId(doActionDto.getUserTaskDTO().getProcInstId())
                .moveActivityIdTo(doActionDto.getUserTaskDTO().getTaskNodeCode(), targetTaskDefKey)
                .changeState();
        ApplyDTO applyDTO = applyService.getApplyByApplyId(userTaskDTO.getApplyId());
        if (applyDTO == null) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }
        NodeInfoDTO nodeInfoDTO = nodeService.getNodeInfoByNodeIdAndDefinitionId(targetTaskDefKey, applyDTO.getDefinitionId());
        if (nodeInfoDTO == null) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }
        // 删除用户任务，保存操作历史
        userTaskDTO.setTaskStatus(TaskConstant.TASK_COMPLETED);
        userTaskDTO.setApproveTime(LocalDateTime.now());
        userTaskDTO.setDueTime(LocalDateTime.now());
        userTaskDTO.setApproveAction(TaskConstant.APPROVE_ACTION_RANDOM_RETURN);
        userTaskService.removeAndSaverHistory(userTaskDTO, null);
        // 清除多实例流程
        userTaskService.clearMultiInstanceTasks(userTaskDTO);
        // 推送消息
        sendTodoMsg(applyDTO.getId(), applyDTO.getApplyTitle());
        return saveApproveOpinion(doActionDto, null);
    }

    @Override
    public Boolean cancelProcess(ProcessCirculationDto processCirculationDto) {
        return doCancelProcess(doActionPrefix(processCirculationDto));
    }

    private Boolean doCancelProcess(DoActionDto doActionDto) {
        doActionDto.getActionMap().put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_CANCEL);
        doActionDto.getTaskService().setVariables(doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getActionMap());
        if (StringUtils.isNotEmpty(doActionDto.getUserTaskDTO().getProcInstId())) {
            // 删除流程实例
            doActionDto.getRuntimeService().deleteProcessInstance(doActionDto.getUserTaskDTO().getProcInstId(), TaskConstant.APPROVE_ACTION_CANCEL);
        }
        ApplyDO applyDO = new ApplyDO();
        applyDO.setId(doActionDto.getUserTaskDTO().getApplyId());
        applyDO.setApplyStatus(BpmConstant.APPLY_STATUS_DISABLE);
        applyDO.setCompleteTime(LocalDateTime.now());
        applyService.updateById(applyDO);
        LambdaQueryWrapper<HistoryDO> historyQueryWrapper = new LambdaQueryWrapper<>();
        // 获取申请人节点将节点名作为审批节点名
        historyQueryWrapper.eq(HistoryDO::getApplyId, doActionDto.getUserTaskDTO().getApplyId());
        historyQueryWrapper.eq(HistoryDO::getTaskType, BpmConstant.TASK_TYPE_START);
        historyQueryWrapper.groupBy(HistoryDO::getTaskType);
        saveApproveOpinion(doActionDto, historyService.getOne(historyQueryWrapper).getTaskName());
        LambdaQueryWrapper<UserTaskDO> queryWrapper = new LambdaQueryWrapper<>();
        // 已认领
        queryWrapper.eq(UserTaskDO::getApplyId, doActionDto.getUserTaskDTO().getApplyId());
        queryWrapper.eq(UserTaskDO::getTaskStatus, 2);
        List<UserTaskDO> taskDOList = userTaskService.list(queryWrapper);
        queryWrapper.clear();
        // 未认领
        queryWrapper.eq(UserTaskDO::getApplyId, doActionDto.getUserTaskDTO().getApplyId());
        queryWrapper.eq(UserTaskDO::getTaskStatus, 1);
        List<UserTaskDO> newTaskDOList = userTaskService.list(queryWrapper);
        // 删除任务
        queryWrapper.clear();
        queryWrapper.eq(UserTaskDO::getApplyId, doActionDto.getUserTaskDTO().getApplyId());
        userTaskService.remove(queryWrapper);

        applyDO = applyService.findById(applyDO.getId() + "");
        // 已认领发撤销通知
        String str = applyDO.getApplyRealName() + "发起的[" + applyDO.getProcessName() + "]流程已被撤销！";
//        SendTaskMessageUtil.send(taskDOList, str, SysMessageTypeEnum.WORK_TO_READ.getValue(), SysMessageStatusEnum.UNREAD.getValue(), 0, false, false, null);
        SendTaskMessageUtil.sendRevoke(str, taskDOList);
        // 未认领通知前端删除消息
        SendTaskMessageUtil.send(newTaskDOList, str, SysMessageTypeEnum.WORK_TO_READ.getValue(), SysMessageStatusEnum.READ.getValue(), 1, true, true, null);
        return true;
    }

    @Override
    public Boolean signProcess(ProcessCirculationDto processCirculationDto) {
        return doSignProcess(doActionPrefix(processCirculationDto));
    }

    private Boolean doSignProcess(DoActionDto doActionDto) {
        doCheck(doActionDto);
        doActionDto.getActionMap().put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_COUNTER_SIGN);
        doActionDto.getActionMap().put(TaskConstant.APPROVE_RESULT_DESC, doActionDto.getProcessCirculationDto().getApproveResult());
        doActionDto.getTaskService().setVariables(doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getActionMap());
        updateUserTask(doActionDto);
        saveApproveOpinion(doActionDto, null);
        complete(doActionDto.getUserTaskDTO().getId(), doActionDto.getUserTaskDTO().getActTaskId(), doActionDto.getUserTaskDTO().getApplyId(), doActionDto.getActionMap(), null);
        editAttachments(doActionDto.getUserTaskDTO().getApplyId().toString(), doActionDto.getProcessCirculationDto().getBusinessData());
        return true;
    }

    @Override
    public Boolean recallProcess(ProcessCirculationDto processCirculationDto) {
        BestBpmAsset.isEmpty(processCirculationDto.getActTaskId());
        HistoryDO historyDO = historyService.getHistoryByTaskId(processCirculationDto.getActTaskId());
        if (Objects.isNull(historyDO)) {
            throw new BpmException("用户任务不存在！", BpmError.DATA_NOT_FOUND_ERROR);
        }
        if (TaskConstant.TASK_ACTION_COUNTER_SIGN.equals(historyDO.getTaskType())) {
            throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "会签任务，无法撤回！");
        }
        // 获取申请信息
        ApplyDTO apply = applyService.getApplyByApplyId(historyDO.getApplyId());
        if (Objects.isNull(apply)) {
            throw new BpmException("申请任务不存在！", BpmError.DATA_NOT_FOUND_ERROR);
        }
        if (apply.getApplyStatus() != BpmConstant.APPLY_STATUS_APPROVING) {
            throw new BpmException("当前任务不可撤回！", BpmError.DATA_NOT_FOUND_ERROR);
        }

        // 解析模型获取下一节点
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(apply.getDefinitionId());
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();

        List<FlowUserTaskDTO> userTasks = new ArrayList<>();
        FlowElement currentFlowElement = FlowElementUtils.getFlowElementById(historyDO.getTaskNodeCode(), flowElements);
        getNextNode(flowElements, currentFlowElement, userTasks);

        if (userTasks.size() == 0) {
            throw new BpmException("当前任务不可撤回！", BpmError.DATA_NOT_FOUND_ERROR);
        }

        List<String> nodeIds = userTasks.stream().map(FlowUserTaskDTO::getNodeId).collect(Collectors.toList());
        List<UserTaskDO> userTaskDOList = userTaskService.getUserTaskByApplyId(historyDO.getApplyId());
        userTaskDOList = userTaskDOList.stream().filter(s -> nodeIds.indexOf(s.getTaskNodeCode()) >= 0).collect(Collectors.toList());

        if (userTaskDOList.size() != 1) {
            throw new BpmException("当前任务不可撤回！", BpmError.DATA_NOT_FOUND_ERROR);
        }

        UserTaskDO userTask1 = userTaskDOList.get(0);
        if (TaskConstant.TASK_ACTION_COUNTER_SIGN.equals(userTask1.getTaskType())) {
            throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "下一任务为会签任务，无法撤回！");
        }
        if (TaskConstant.TASK_CLAIM <= userTask1.getTaskStatus()) {
            throw new BpmException("当前任务已被签收，无法撤回！", BpmError.NEXT_TASK_HAS_COMPLETED);
        }

        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(historyDO.getTaskNodeCode());
        SequenceFlow sequenceFlow = flowNode.getOutgoingFlows().get(0);
        FlowElement targetFlowElement = sequenceFlow.getTargetFlowElement();
        String currentNodeKey = sequenceFlow.getTargetRef();
        // 判断下一节点是否是并行网关 不支持并行网关撤回
        if (targetFlowElement != null && (targetFlowElement instanceof ParallelGateway || targetFlowElement instanceof InclusiveGateway)) {
            throw new BpmException("并行任务无法撤回！", BpmError.PARALLEL_CANNOT_BE_WITHDRAWN);
        } else if (targetFlowElement != null && targetFlowElement instanceof ExclusiveGateway) {
            List<Task> taskList = processEngine.getTaskService().createTaskQuery().processInstanceId(historyDO.getProcInstId()).list();
            if (CollectionUtil.isNotEmpty(taskList)) {
                Task task = taskList.get(0);
                currentNodeKey = task.getTaskDefinitionKey();
            }
        }
        // 获取下一节点的所有任务
        List<Task> tasks = processEngine.getTaskService().createTaskQuery()
                .processInstanceId(historyDO.getProcInstId())
                .taskDefinitionKey(currentNodeKey).list();
        List<UserTaskDO> userTaskDOS = userTaskService.getUserTaskByApplyId(historyDO.getApplyId());
        boolean noFindTask = true;
        List<UserTaskDO> returnTasks = new ArrayList<>();
        for (UserTaskDO userTaskDO : userTaskDOS) {
            if (userTaskDO.getTaskNodeCode().equals(currentNodeKey)) {
                if (TaskConstant.TASK_ACTION_COUNTER_SIGN.equals(userTaskDO.getTaskType())) {
                    throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "下一任务为会签任务，无法撤回！");
                }
                noFindTask = false;
                if (TaskConstant.TASK_CLAIM <= userTaskDO.getTaskStatus()) {
                    throw new BpmException("当前任务已被签收，无法撤回！", BpmError.NEXT_TASK_HAS_COMPLETED);
                }
                returnTasks.add(userTaskDO);
            }
        }
        if (noFindTask) {
            throw new BpmException("当前任务不可撤回！", BpmError.DATA_NOT_FOUND_ERROR);
        }
        RuntimeService runtimeService = processEngine.getRuntimeService();
        if (tasks.size() > 0) {
            Task task = tasks.get(0);
            runtimeService.setVariable(task.getExecutionId(), TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_RECALL);
            runtimeService.setVariable(task.getExecutionId(), TaskConstant.TASK_ID, historyDO.getId());
        }
        // 流程回退到当前申请回退的节点，审批人继续审批
        runtimeService
                .createChangeActivityStateBuilder().processInstanceId(historyDO.getProcInstId())
                .moveActivityIdTo(currentNodeKey, historyDO.getTaskNodeCode()).changeState();

        Set<Long> deletedIds = new HashSet<>();
        // 移除下一节点正在运行的用户任务
        for (UserTaskDO userTask : returnTasks) {
            for (Task task : tasks) {
                if (userTask.getActTaskId().equals(task.getId())) {
                    deletedIds.add(userTask.getId());
                }
            }
        }
        userTaskService.removeByIds(deletedIds);
//        LambdaQueryWrapper<UserTaskDO> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(UserTaskDO::getApplyId, apply.getId());
        // 未认领
//        queryWrapper.eq(UserTaskDO::getTaskStatus, 1);
        LambdaQueryWrapper<ApproveOpinionDO> newQueryWrapper = new LambdaQueryWrapper<>();
        newQueryWrapper.eq(ApproveOpinionDO::getActTaskId, processCirculationDto.getActTaskId());
        ApproveOpinionDO approveOpinion = new ApproveOpinionDO();
        approveOpinion.setDeleted(true);
        approveOpinionService.update(approveOpinion, newQueryWrapper);
        SendTaskMessageUtil.send(returnTasks, null, SysMessageTypeEnum.WORK_TO_DO.getValue(), SysMessageStatusEnum.READ.getValue(), 1, true, true, null);
        return true;
    }

    private void getNextNode(Collection<FlowElement> flowElements, FlowElement flowElement, List<FlowUserTaskDTO> nextUserTask) {
        // 如果是结束节点
        if (flowElement instanceof EndEvent) {
            //如果是子任务的结束节点
            if (FlowElementUtils.getSubProcess(flowElements, flowElement) != null) {
                flowElement = FlowElementUtils.getSubProcess(flowElements, flowElement);
            }
        }
        List<SequenceFlow> outGoingFlows = null;
        if (flowElement instanceof org.flowable.bpmn.model.Task) {
            outGoingFlows = ((org.flowable.bpmn.model.Task) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof Gateway) {
            outGoingFlows = ((Gateway) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof StartEvent) {
            outGoingFlows = ((StartEvent) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof SubProcess) {
            outGoingFlows = ((SubProcess) flowElement).getOutgoingFlows();
        }

        if (outGoingFlows != null && outGoingFlows.size() > 0) {
            //遍历所有的出线
            for (SequenceFlow sequenceFlow : outGoingFlows) {
                //出线的下一节点
                String nextFlowElementID = sequenceFlow.getTargetRef();
                //查询下一节点的信息
                FlowElement nextFlowElement = FlowElementUtils.getFlowElementById(nextFlowElementID, flowElements);

                //用户任务
                if (nextFlowElement instanceof UserTask) {
                    UserTask userTask = (UserTask) nextFlowElement;
                    FlowUserTaskDTO flowUserTaskDTO = new FlowUserTaskDTO();
                    flowUserTaskDTO.setNodeId(userTask.getId());
                    flowUserTaskDTO.setNodeName(userTask.getName());
                    flowUserTaskDTO.setParentNodeId(flowElement.getId());
                    flowUserTaskDTO.setParentNodeName(flowElement.getName());
                    nextUserTask.add(flowUserTaskDTO);
                }
                //排他网关
                else if (nextFlowElement instanceof ExclusiveGateway) {
                    getNextNode(flowElements, nextFlowElement, nextUserTask);
                }
            }
        }
    }

    @Override
    public void complete(Long taskId, String actTaskId, Long applyId, Map<String, Object> processMap, String applyTitle) {
        TaskService taskService = processEngine.getTaskService();
        // 查找当前applyUserInfo塞入processMap
        Object applyUserInfo = taskService.getVariable(actTaskId, BpmConstant.APPLY_USER_INFO);
        processMap.put(BpmConstant.APPLY_USER_INFO, applyUserInfo);
        taskService.complete(actTaskId, processMap);
        CustomException businessException = PrecessThreadLocalUtil.THREAD_LOCAL.get();
        if (businessException != null) {
            PrecessThreadLocalUtil.THREAD_LOCAL.remove();
            throw businessException;
        }
        Integer num = threadLocal.get();
        Boolean flag = false;
        if (num == null) {
            threadLocal.set(1);
            flag = true;
        }

        // 记录自动流转节点
//        List<String> autoNode = new ArrayList<>();
//        if (processMap.containsKey(BpmConstant.AUTO_NODE)) {
//            autoNode = (List<String>) processMap.get(BpmConstant.AUTO_NODE);
//        }
//        String nodeId = getNodeIdByTaskId(actTaskId);
//        if (autoNode.indexOf(nodeId) >= 0) {
//            throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "流程审核进入循环，无法审核！");
//        }
//        autoNode.add(nodeId);
//        processMap.put(BpmConstant.AUTO_NODE, autoNode);

        autoComplete(applyId, processMap, applyTitle);
        if (flag) {
            SendTaskMessageUtil.delete(SysMessageTypeEnum.WORK_TO_DO.getValue(), String.valueOf(taskId));
            sendTodoMsg(applyId, applyTitle);
            threadLocal.remove();
        }
    }
    
    /** 
    * @Description: 发送接收任务信息
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/4/26 14:52 
    */ 
    private void sendTodoMsg(Long applyId, String applyTitle) {
        LambdaQueryWrapper<UserTaskDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTaskDO::getApplyId, applyId);
        List<UserTaskDO> userTaskDOList = userTaskService.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(userTaskDOList)) {
            String str = userTaskDOList.get(0).getTaskOwnerRealName() + "发起的流程正待您审核";
            SendTaskMessageUtil.send(userTaskDOList, str, SysMessageTypeEnum.WORK_TO_DO.getValue(), SysMessageStatusEnum.UNREAD.getValue(), 0, false, true, applyTitle);
        }
    }

    // 判断是否陷入循环
    private void isLoop(Map<String, Object> processMap, UserTaskDO userTaskDO) {
        List<String> autoNode = new ArrayList<>();
        if (processMap.containsKey(BpmConstant.AUTO_NODE)) {
            autoNode = (List<String>) processMap.get(BpmConstant.AUTO_NODE);
        }
        String nodeCode = userTaskDO.getTaskNodeCode();
        if (autoNode.indexOf(nodeCode) >= 0) {
            throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "流程审核进入循环，无法审核！");
        }
        autoNode.add(nodeCode);
        processMap.put(BpmConstant.AUTO_NODE, autoNode);
    }

    /**
     * 自动完成下一任务
     */
    @Override
    public List<FlowUserTaskDTO> autoComplete(Long applyId, Map<String, Object> processMap, String applyTitle) {
        UserAccount userDetails = SecurityUtil.getCurrentUserNoNull();
        LambdaQueryWrapper<UserTaskDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTaskDO::getApplyId, applyId);
        List<UserTaskDO> userTaskDOList = userTaskService.list(queryWrapper);
        List<FlowUserTaskDTO> flowUserTaskDTOList = new ArrayList<>();
        if (userTaskDOList != null && userTaskDOList.size() != 0) {
            ModelDO modelDO = modelService.getById(userTaskDOList.get(0).getModelId());
            for (UserTaskDO userTaskDO : userTaskDOList) {
                List<UserTaskDO> userTaskDOS = new ArrayList<>();
                // 无任务处理候选人
                if (StringUtils.isBlank(userTaskDO.getCandidateUserList())) {
                    isLoop(processMap, userTaskDO);
                    ApproveOpinionDO approveOpinionDO = new ApproveOpinionDO();
                    approveOpinionDO.setActTaskId(Long.valueOf(userTaskDO.getActTaskId()));
                    approveOpinionDO.setTaskName(userTaskDO.getTaskName());
                    approveOpinionDO.setApplyId(userTaskDO.getApplyId());
                    approveOpinionDO.setApproveOpinion("无任务执行人，执行跳过");
                    approveOpinionDO.setApproveAction(TaskConstant.APPROVE_ACTION_PASS);
                    approveOpinionService.save(approveOpinionDO);
                    userTaskDO.setApproveOpinion("无任务执行人，执行跳过");
                    userTaskService.updateById(userTaskDO);
                    complete(userTaskDO.getId(), userTaskDO.getActTaskId(), applyId, processMap, applyTitle);
                    break;
                }
                // 下一任务包含当前审核人
                else if (userTaskDO.getCandidateUserList().contains(userDetails.getUserId())) {
                    // 无可编辑字段
                    if (StringUtils.isBlank(userTaskDO.getFormEditField())) {
                        // 会签
                        if (TaskConstant.TASK_ACTION_COUNTER_SIGN.equals(userTaskDO.getTaskType())) {
                            // 判断是否进入了循环
                            isLoop(processMap, userTaskDO);

                            // 认领任务
                            userTaskDO.setTaskAssigneeUserId(userDetails.getUserId());
                            userTaskDO.setTaskAssigneeRealName(userDetails.getEmpName());
                            userTaskDO.setClaimTime(LocalDateTime.now());
                            userTaskDO.setTaskStatus(TaskConstant.TASK_CLAIM);
                            userTaskService.updateById(userTaskDO);

                            ProcessCirculationDto processCirculationDto = new ProcessCirculationDto();
                            processCirculationDto.setTaskId(userTaskDO.getId());
                            processCirculationDto.setBusinessData(processMap);
                            processCirculationDto.setApproveOpinion("自动流转");
                            processCirculationDto.setApproveActionCode(BpmConstant.TASK_TYPE_COUNTERSIGN);
                            processCirculationDto.setApproveResult(TaskConstant.COUNTERSIGN_OUTCOME_PASS);
                            signProcess(processCirculationDto);
                        } else {
                            // 获取下一任务节点信息
                            flowUserTaskDTOList = nodeService.getNextNodeList(modelDO.getDefinitionId(), userTaskDO.getTaskNodeCode(), processMap, false, true);
                            CustomException businessException = PrecessThreadLocalUtil.THREAD_LOCAL.get();
                            if (businessException != null) {
                                PrecessThreadLocalUtil.THREAD_LOCAL.remove();
                                throw businessException;
                            }
                            Boolean flag = true;
                            for (FlowUserTaskDTO flowUserTaskDTO : flowUserTaskDTOList) {
                                // 下一任务为前端指派或手动指定审核人
                                Boolean newFlag = FindUserTypeConstant.FRONT_END_SPECIFIED.equals(flowUserTaskDTO.getFindUserType()) || TaskConstant.ASSIGN_MODE_MANUAL.equals(flowUserTaskDTO.getAssignMode());
                                if (newFlag) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                isLoop(processMap, userTaskDO);
                                ProcessCirculationDto processCirculationDto = new ProcessCirculationDto();
                                processCirculationDto.setTaskId(userTaskDO.getId());
                                processCirculationDto.setBusinessData(processMap);
                                processCirculationDto.setApproveOpinion("自动流转");
                                // 认领任务
                                userTaskDO.setTaskAssigneeUserId(userDetails.getUserId());
                                userTaskDO.setTaskAssigneeRealName(userDetails.getEmpName());
                                userTaskDO.setClaimTime(LocalDateTime.now());
                                userTaskDO.setTaskStatus(TaskConstant.TASK_CLAIM);
                                userTaskService.updateById(userTaskDO);
                                userTaskDOS.add(userTaskDO);
                                SendTaskMessageUtil.send(userTaskDOS, null, SysMessageTypeEnum.WORK_TO_DO.getValue(), 1, 1, true, true, applyTitle);
                                processCirculationDto.setApproveActionCode(TaskConstant.APPROVE_ACTION_PASS);
                                processCirculationDto.setAutoPass(true);
                                passProcess(processCirculationDto);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return flowUserTaskDTOList;
    }

    /**
     * 挂起
     *
     * @param processCirculationDto
     * @return
     */
    @Override
    public Boolean setTaskHang(ProcessCirculationDto processCirculationDto) {
        UserTaskDO userTaskDO = new UserTaskDO();
        userTaskDO.setId(processCirculationDto.getTaskId());
        // 任务状态挂起
        userTaskDO.setTaskStatus(7);
        if (!userTaskService.updateById(userTaskDO)) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "挂起失败");
        }
        ApplyDO applyDO = new ApplyDO();
        applyDO.setId(processCirculationDto.getApplyId());
        // 申请状态挂起
        applyDO.setApplyStatus(6);
        if (!applyService.updateById(applyDO)) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "挂起失败");
        }
        return true;
    }

    @Override
    public Boolean copySend(ProcessCirculationDto processCirculationDto) {
        return sendDuplicateService.saveSendDuplicate(processCirculationDto);
    }

    @Override
    public Boolean reassignment(ProcessCirculationDto processCirculationDto) {
        if (processCirculationDto.getTaskId() == null) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "任务id不能为空");
        }
        if (processCirculationDto.getAssigneeList() == null) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "转办人不能为空");
        }
        if (processCirculationDto.getAssigneeList().size() != 1) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "转办人数需为一人");
        }

        UserTaskDO userTaskDO = userTaskService.getById(processCirculationDto.getTaskId());
        if (userTaskDO == null) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "当前任务不存在");
        }
        UserAccount userDetails = SecurityUtil.getCurrentUser();
        if (!userTaskDO.getTaskAssigneeUserId().equals(userDetails.getUserId())) {
            throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "您没有操作权限");
        }
        // 抄送给自己
        ProcessCirculationDto send = new ProcessCirculationDto();
        send.setActTaskId(Long.parseLong(userTaskDO.getActTaskId()));
        send.setAssigneeList(Arrays.asList(userTaskDO.getTaskAssigneeUserId()));
        send.setAssigneeNameList(Arrays.asList(userTaskDO.getTaskAssigneeRealName()));
        send.setApproveOpinion("系统转办抄送");
        sendDuplicateService.saveSendDuplicate(send);

        // 修改流程处理人
        TaskService taskService = processEngine.getTaskService();
        taskService.setAssignee(userTaskDO.getActTaskId(), processCirculationDto.getAssigneeList().get(0));

        userTaskDO.setTaskAssigneeUserId(processCirculationDto.getAssigneeList().get(0));
        userTaskDO.setTaskAssigneeRealName(processCirculationDto.getAssigneeNameList().get(0));
        userTaskService.updateById(userTaskDO);

        if (StringUtils.isNotEmpty(processCirculationDto.getApproveOpinion())) {
            // 保存转办意见
            ApproveOpinionDO approveOpinionDO = new ApproveOpinionDO();
            approveOpinionDO.setActTaskId(Long.valueOf(userTaskDO.getActTaskId()));
            approveOpinionDO.setTaskName(userTaskDO.getTaskName());
            approveOpinionDO.setApplyId(userTaskDO.getApplyId());
            approveOpinionDO.setApproveOpinion(processCirculationDto.getApproveOpinion());
            approveOpinionDO.setApproveAction(TaskConstant.APPROVE_REASSIGNMENT);
            approveOpinionDO.setCreateBy(userDetails.getUsername());
            approveOpinionDO.setCreateUserId(userDetails.getUserId());
            approveOpinionDO.setUpdateBy(userDetails.getUsername());
            approveOpinionDO.setUpdateUserId(userDetails.getUserId());
            approveOpinionService.save(approveOpinionDO);
        }

        return null;
    }
}
