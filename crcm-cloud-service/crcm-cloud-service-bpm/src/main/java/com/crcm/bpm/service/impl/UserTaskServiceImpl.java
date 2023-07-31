package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.api.enums.ApproveActionEnum;
import com.crcm.bpm.api.vo.GetApproveOpinionListVO;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.constant.enums.SysMessageStatusEnum;
import com.crcm.bpm.core.constant.enums.SysMessageTypeEnum;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.core.utils.SendTaskMessageUtil;
import com.crcm.bpm.domain.dto.NodeAssigneesDTO;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.request.ClaimTaskDTO;
import com.crcm.bpm.domain.dto.request.HistoryFormDataSaveDTO;
import com.crcm.bpm.domain.dto.request.UserTaskInfoQueryDTO;
import com.crcm.bpm.domain.dto.response.*;
import com.crcm.bpm.domain.entity.ApplyDO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.domain.vo.NodeQueryVO;
import com.crcm.bpm.mapper.UserTaskMapper;
import com.crcm.bpm.service.*;
import com.crcm.bpm.utils.EnumHelperUtil;
import com.crcm.bpm.utils.FileUtil;
import com.crcm.bpm.utils.FormDataUtil;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserTaskServiceImpl extends ServiceImpl<UserTaskMapper, UserTaskDO> implements UserTaskService {


    @Autowired
    private HistoryService historyService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;

    @Autowired
    private HistoryFormDataService historyFormDataService;


    @Autowired
    private ProcessCirculationService processCirculationService;

    @Override
    public int saveUserTask(UserTaskDO userTaskDO) {
        return this.baseMapper.insert(userTaskDO);
    }

    @Override
    public int updateUserTask(UserTaskDO userTaskDO) {
        return this.baseMapper.updateById(userTaskDO);
    }

    @Override
    public int deleteById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public UserTaskDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<UserTaskDO> findList(UserTaskDO userTaskDO) {
        QueryWrapper<UserTaskDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<UserTaskDO> findPage(Page page, UserTaskDO userTaskDO) {
        QueryWrapper<UserTaskDO> queryWrapper = new QueryWrapper<>();
        IPage<UserTaskDO> pageUserTask = this.baseMapper.selectPage(page, queryWrapper);
        return pageUserTask;
    }

    @Override
    public List<UserTaskDTO> getUserTaskByProcInstId(String procInstId) {
        BestBpmAsset.isAssetEmpty(procInstId);
        LambdaQueryWrapper<UserTaskDO> queryWrapper = new QueryWrapper<UserTaskDO>()
                .lambda().eq(UserTaskDO::getProcInstId, procInstId);
        List<UserTaskDO> result = this.list(queryWrapper);
        if (result == null) {
            result = new ArrayList<>();
        }
        List<UserTaskDTO> list = new ArrayList<>();
        for (UserTaskDO userTaskDO : result) {
            list.add(BeanUtil.copyProperties(userTaskDO, UserTaskDTO.class));
        }

        return list;
    }

    @Override
    public UserTaskDTO getUserTaskByTaskId(Long taskId) {
        UserTaskDO taskDO = this.getById(taskId);
        if (taskDO == null) {
            return null;
        }
        return BeanUtil.copyProperties(taskDO, UserTaskDTO.class);
    }

    @Override
    public IPage<UserTaskInfoDTO> getApplyListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQuery) {
        IPage<UserTaskInfoDTO> iPage = this.baseMapper.selectApplyList(page, userTaskInfoQuery);
        iPage = getAvatar(iPage);
        return iPage;
    }

    private IPage<UserTaskInfoDTO> getAvatar(IPage<UserTaskInfoDTO> iPage) {
        if (iPage.getRecords().size() == 0) {
            return iPage;
        }
        List<String> ids = new ArrayList<>();
        iPage.getRecords().forEach(userTaskInfoDTO -> {
            ids.add(userTaskInfoDTO.getApplyUserId());
        });
        Map<String, String> map = FileUtil.getAvatarMap(ids);
        iPage.getRecords().forEach(userTaskInfoDTO -> {
            userTaskInfoDTO.setAvatar(map.get(userTaskInfoDTO.getApplyUserId()));
            if (userTaskInfoDTO.getTaskId() == null) {
                userTaskInfoDTO.setTaskId(0L);
            }
        });
        return iPage;
    }

    @Override
    public IPage<UserTaskInfoDTO> getToDoListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQuery) {
        IPage<UserTaskInfoDTO> iPage = baseMapper.getToDoListByCondition(page, userTaskInfoQuery);
        iPage = getAvatar(iPage);
        return iPage;
    }

    @Override
    public IPage<UserTaskInfoDTO> getDraftListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQuery) {
        IPage<UserTaskInfoDTO> iPage = baseMapper.getDraftListByCondition(page, userTaskInfoQuery);
        iPage = getAvatar(iPage);
        return iPage;
    }

    @Override
    public IPage<UserTaskInfoDTO> getHaveDoListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQuery) {
        IPage<UserTaskInfoDTO> iPage = baseMapper.getHaveDoListByCondition(page, userTaskInfoQuery);
        iPage = getAvatar(iPage);
        return iPage;
    }

    @Override
    public boolean completeTask(ProcessCirculationDto processCirculationDto) {
        if (processCirculationDto.getBusinessData() == null) {
            processCirculationDto.setBusinessData(new HashMap<>());
        }
        if (processCirculationDto.getTaskId() != null) {
            // 申请信息
            UserTaskDO userTaskDO = this.findById(processCirculationDto.getTaskId() + "");
            if (userTaskDO != null) {
                ApplyDO applyDO = applyService.findById(userTaskDO.getApplyId() + "");
                ApplyUserInfoDTO applyUserInfoDTO = new ApplyUserInfoDTO().builder()
                        .userId(applyDO.getApplyUserId())
                        .username(applyDO.getApplyRealName())
                        .companyId(applyDO.getApplyCompanyId())
                        .companyName(applyDO.getApplyCompanyName())
                        .deptId(applyDO.getApplyDeptId())
                        .deptName(applyDO.getApplyDeptName()).build();
                processCirculationDto.getBusinessData().put("applyUserInfo", applyUserInfoDTO);
            }
        }

        switch (processCirculationDto.getApproveActionCode()) {
            /* 审批拒绝 */
            case TaskConstant.APPROVE_ACTION_REJECT:
                processCirculationService.rejectProcess(processCirculationDto);
                break;
            /* 中止流程 */
            case TaskConstant.APPROVE_ACTION_FAIL:
                processCirculationService.failProcess(processCirculationDto);
                break;
            /* 审批通过 */
            case TaskConstant.APPROVE_ACTION_PASS:
                processCirculationService.passProcess(processCirculationDto);
                break;
            /* 会签 */
            case TaskConstant.APPROVE_ACTION_COUNTER_SIGN:
                processCirculationService.signProcess(processCirculationDto);
                break;
            /* 指定节点退回 */
            case TaskConstant.APPROVE_ACTION_RETURN:
                break;
            /* 自由退回 */
            case TaskConstant.APPROVE_ACTION_RANDOM_RETURN:
                processCirculationService.randomReturnProcess(processCirculationDto);
                break;
            /* 撤回 */
            case TaskConstant.APPROVE_ACTION_RECALL:
                processCirculationService.recallProcess(processCirculationDto);
                break;
            /* 撤销 */
            case TaskConstant.APPROVE_ACTION_CANCEL:
                processCirculationService.cancelProcess(processCirculationDto);
                break;
            /* 挂起 */
            case TaskConstant.APPROVE_TASK_HANG:
                processCirculationService.setTaskHang(processCirculationDto);
                break;
            /* 抄送 */
            case TaskConstant.APPROVE_COPY_SEND:
                processCirculationService.copySend(processCirculationDto);
                break;
            /* 转派 */
            case TaskConstant.APPROVE_REASSIGNMENT:
                processCirculationService.reassignment(processCirculationDto);
                break;
            /* 默认审批通过 */
            default:
                processCirculationService.passProcess(processCirculationDto);
                break;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<UserTaskDO> getUserTaskByApplyId(Long applyId) {
        BestBpmAsset.isAssetEmpty(applyId);
        LambdaQueryWrapper<UserTaskDO> wrapper = new QueryWrapper<UserTaskDO>().lambda().eq(UserTaskDO::getApplyId, applyId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean claimTask(ClaimTaskDTO claimTaskDTO) {
        BestBpmAsset.isAssetEmpty(claimTaskDTO);
        BestBpmAsset.isAssetEmpty(claimTaskDTO.getTaskId());
//        BestBpmAsset.isAssetEmpty(claimTaskDTO.getTenantId());
        BestBpmAsset.isAssetEmpty(claimTaskDTO.getClaimId());
        BestBpmAsset.isAssetEmpty(claimTaskDTO.getClaimName());
        UserTaskDTO userTaskDTO = getUserTaskByTaskId(claimTaskDTO.getTaskId());
        // 任务已认领
        if (userTaskDTO.getTaskStatus() == TaskConstant.TASK_CLAIM) {
            throw new BpmException(BpmError.TASK_CLAIM_BY_OTHER);
        } else if (userTaskDTO.getTaskStatus() >= TaskConstant.TASK_CLAIM) {
            // 任务已完成
            throw new BpmException(BpmError.TASK_HAS_COMPLETED);
        }

        // 修改当前任务状态为已签收
        UserTaskDO taskDO = UserTaskDO.builder()
                .id(userTaskDTO.getId())
                .claimTime(LocalDateTime.now())
                .taskAssigneeUserId(claimTaskDTO.getClaimId())
                .taskAssigneeRealName(claimTaskDTO.getClaimName())
                .taskStatus(TaskConstant.TASK_CLAIM)
                .build();

        // 设置流程引擎签收人
        processEngine.getTaskService().claim(userTaskDTO.getActTaskId(), claimTaskDTO.getClaimId());
        boolean flag = this.updateById(taskDO);
        List<UserTaskDO> taskDOList = new ArrayList<>();
        taskDOList.add(baseMapper.selectById(taskDO.getId()));
        SendTaskMessageUtil.send(taskDOList, null, SysMessageTypeEnum.WORK_TO_DO.getValue(), SysMessageStatusEnum.READ.getValue(), 1, true, true, null);
        return flag;
    }

    @Override
    public boolean unClaimTask(String taskId) {
        processEngine.getTaskService().unclaim(taskId);
        LambdaUpdateWrapper<UserTaskDO> updateWrapper = new UpdateWrapper<UserTaskDO>().lambda()
                .eq(UserTaskDO::getActTaskId, taskId)
                .set(UserTaskDO::getTaskAssigneeRealName, null)
                .set(UserTaskDO::getTaskAssigneeUserId, null);
        return this.update(updateWrapper);
    }


    @Override
    public List<ReturnNodeDTO> getReturnNode(Long taskId) {
        BestBpmAsset.isAssetEmpty(taskId);
        UserTaskDTO userTaskDTO = getUserTaskByTaskId(taskId);
        if (Objects.isNull(userTaskDTO)) {
            throw new BpmException("用户任务不存在", BpmError.DATA_NOT_FOUND_ERROR);
        }
        ApplyDTO apply = applyService.getApplyByApplyId(userTaskDTO.getApplyId());
        if (Objects.isNull(apply)) {
            throw new BpmException("对应申请不存在", BpmError.DATA_NOT_FOUND_ERROR);
        }

        LambdaQueryWrapper<HistoryDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HistoryDO::getApplyId, userTaskDTO.getApplyId());
        queryWrapper.orderByAsc(HistoryDO::getId);
        List<HistoryDO> completedTaskList = historyService.list(queryWrapper);

        Map<String, HistoryDO> completedTaskMap = new HashMap<>();
        if (completedTaskList != null) {
            for (HistoryDO historyDO : completedTaskList) {
                if (userTaskDTO.getTaskNodeCode().equals(historyDO.getTaskNodeCode())) {
                    break;
                }
//                if(BpmConstant.TASK_TYPE_COUNTERSIGN.equals(historyDO.getTaskType())){
//                    continue;
//                }
                if (!completedTaskMap.containsKey(historyDO.getTaskNodeCode())) {
                    completedTaskMap.put(historyDO.getTaskNodeCode(), historyDO);
                }
            }
        }
        Map<String, HistoryDO> returnNodeDTOS = new HashMap<>();
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(apply.getDefinitionId());
        getCanReturnNode(bpmnModel, completedTaskMap, returnNodeDTOS, userTaskDTO.getTaskNodeCode());

        return returnNodeDTOS.values().stream()
                .filter(s -> StringUtils.isNotBlank(s.getCandidateUserList()) && StringUtils.isNotBlank(s.getTaskAssigneeRealName())
                        && !BpmConstant.TASK_TYPE_COUNTERSIGN.equals(s.getTaskType()))
                .sorted(Comparator.comparing(HistoryDO::getCreateTime))
                .map(s -> new ReturnNodeDTO().setNodeId(s.getTaskNodeCode()).setNodeName(s.getTaskName())
                        .setAssigneeUserIdList(Arrays.asList(s.getCandidateUserList().split(",")))
                        .setAssigneeUserNameList(Arrays.asList(s.getTaskAssigneeRealName().split(","))))
                .collect(Collectors.toList());

        /*List<HistoryDO> completedTasks = new ArrayList<>();
//        List<HistoryDO> completedTasks = historyService.getCanReturnList(userTaskDTO.getApplyId());
        if(completedTaskList != null){
            for (HistoryDO historyDO : completedTaskList) {
                if(userTaskDTO.getTaskNodeCode().equals(historyDO.getTaskNodeCode())){
                    break;
                }
                if(BpmConstant.TASK_TYPE_COUNTERSIGN.equals(historyDO.getTaskType())){
                    continue;
                }
                completedTasks.add(historyDO);
            }
        }
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(apply.getDefinitionId());
        //组装数据
        for (HistoryDO task : completedTasks) {
            FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(userTaskDTO.getTaskNodeCode());
            SequenceFlow sequenceFlow = flowNode.getIncomingFlows().get(0);
            FlowElement upNode = sequenceFlow.getSourceFlowElement();
            // 判断是否是并行网关 不支持并行网关退回
            if (upNode != null && (upNode instanceof ParallelGateway || upNode instanceof InclusiveGateway)) {
                continue;
            }
            // 获取上一个节点的activityId
            String sourceRef = sequenceFlow.getSourceRef();
            ReturnNodeDTO returnNode = new ReturnNodeDTO().setNodeId(task.getTaskNodeCode()).setNodeName(task.getTaskName());
            if (StringUtils.isNotBlank(task.getCandidateUserList())) {
                List<String> list = Arrays.asList(task.getCandidateUserList().split(","));
                returnNode.setAssigneeUserIdList(list);
            }
            if (StringUtils.isNotBlank(task.getTaskAssigneeRealName())) {
                List<String> list = Arrays.asList(task.getTaskAssigneeRealName().split(","));
                returnNode.setAssigneeUserNameList(list);
            }
            returnNodeDTOS.add(returnNode);
        }
        // 去重
        LinkedHashMap<String, ReturnNodeDTO> map = new LinkedHashMap<>();
        returnNodeDTOS.stream().forEach(task -> {
            map.put(task.getNodeId(), task);
        });
        return map.values().stream().collect(Collectors.toList());*/
    }

    // 获取可回退节点
    private void getCanReturnNode(BpmnModel bpmnModel, Map<String, HistoryDO> completedTaskMap, Map<String, HistoryDO> returnNodeDTOS, String taskNodeCode) {
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(taskNodeCode);
        for (SequenceFlow sequenceFlow : flowNode.getIncomingFlows()) {
            FlowElement upNode = sequenceFlow.getSourceFlowElement();

            // 用户任务
            if (upNode instanceof UserTask) {
                if (completedTaskMap.containsKey(upNode.getId()) && !returnNodeDTOS.containsKey(upNode.getId())) {
                    returnNodeDTOS.put(upNode.getId(), completedTaskMap.get(upNode.getId()));
                    getCanReturnNode(bpmnModel, completedTaskMap, returnNodeDTOS, upNode.getId());
                }
            }
            // 排他网关
            else if (upNode instanceof ExclusiveGateway) {
                getCanReturnNode(bpmnModel, completedTaskMap, returnNodeDTOS, upNode.getId());
            }
            // 并行网关或包含网关
            else if (upNode instanceof ParallelGateway || upNode instanceof InclusiveGateway) {
                if (((Gateway) upNode).getOutgoingFlows().size() == 1) {
                    // 网关结束，查询网关活动之前的可回退节点
                    String gatewayId = getGatewayActivitiStartId(bpmnModel, upNode.getId());
                    if (gatewayId != null) {
                        getCanReturnNode(bpmnModel, completedTaskMap, returnNodeDTOS, gatewayId);
                    }
                }
            }
        }
    }

    private String getGatewayActivitiStartId(BpmnModel bpmnModel, String taskNodeCode) {
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(taskNodeCode);
        String gatewayASId = null;
        for (SequenceFlow sequenceFlow : flowNode.getIncomingFlows()) {
            List<String> hasNodeCode = new ArrayList<>();
            FlowElement activitiNode = getGatewayNode(bpmnModel, hasNodeCode, sequenceFlow.getSourceFlowElement().getId());
            if (activitiNode != null) {
                if (gatewayASId != null && !gatewayASId.equals(activitiNode.getId())) {
                    gatewayASId = null;
                    break;
                } else {
                    gatewayASId = activitiNode.getId();
                }
            }
        }
        return gatewayASId;
    }

    private FlowElement getGatewayNode(BpmnModel bpmnModel, List<String> hasNodeCode, String taskNodeCode) {
        FlowElement returnNode = null;
        if (hasNodeCode.indexOf(taskNodeCode) < 0) {
            hasNodeCode.add(taskNodeCode);
            FlowElement thisNode = bpmnModel.getFlowElement(taskNodeCode);
            if (thisNode instanceof ParallelGateway || thisNode instanceof InclusiveGateway) {
                if (((Gateway) thisNode).getOutgoingFlows().size() == 1) {
                    String gatewayASId = getGatewayActivitiStartId(bpmnModel, thisNode.getId());
                    returnNode = getGatewayNode(bpmnModel, hasNodeCode, gatewayASId);
                }
            }
            if (returnNode == null) {
                FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(taskNodeCode);
                for (SequenceFlow sequenceFlow : flowNode.getIncomingFlows()) {
                    FlowElement upNode = sequenceFlow.getSourceFlowElement();
                    // 用户任务
                    if (upNode instanceof UserTask) {
                        returnNode = getGatewayNode(bpmnModel, hasNodeCode, upNode.getId());
                    }
                    // 排他网关
                    else if (upNode instanceof ExclusiveGateway) {
                        returnNode = getGatewayNode(bpmnModel, hasNodeCode, upNode.getId());
                    }
                    // 并行网关或包含网关
                    else if (upNode instanceof ParallelGateway || upNode instanceof InclusiveGateway) {
                        if (((Gateway) upNode).getOutgoingFlows().size() == 1) {
                            String gatewayASId = getGatewayActivitiStartId(bpmnModel, upNode.getId());
                            returnNode = getGatewayNode(bpmnModel, hasNodeCode, gatewayASId);
                        } else {
                            returnNode = upNode;
                        }
                    }
                    if (returnNode != null) {
                        break;
                    }
                }
            }
        }
        return returnNode;
    }


    @Override
    public FlowUserTaskDTO getNextNodeInfo(NodeQueryVO nodeQueryVO) {
        final List<FlowUserTaskDTO> nextNodeList = nodeService.getNextNodeList(nodeQueryVO.getProcessDefId()
                , nodeQueryVO.getTaskNodeId()
                , nodeQueryVO.getDataMap()
                , false);
        if (nextNodeList.size() == 0) {
//            throw new BpmException("没有找到下一个节点", BpmError.DATA_NOT_FOUND_ERROR);
            return null;
        }
        return nextNodeList.get(0);
    }

    @Override
    public List<NodeAssigneesDTO> getNextNodeAssignees(NodeQueryVO nodeQueryVO) {
        Map<String, Object> businessDataMap = nodeQueryVO.getDataMap();
        // 解析表单数据
        Map<String, Object> dataMap = JSON.parseObject(JSON.toJSONString(businessDataMap.get(BpmConstant.FORM_DATA_KEY)), Map.class);
        for (String key : dataMap.keySet()) {
            businessDataMap.put(key, FormDataUtil.toDecimal(dataMap.get(key)));
        }

        // 申请信息
        UserTaskDO userTaskDO = this.findById(nodeQueryVO.getTaskId());
        if (userTaskDO == null) {
            throw new BpmException(BpmError.USER_TASK_NO_FIND_ERROR);
        }
        ApplyDO applyDO = applyService.findById(userTaskDO.getApplyId() + "");
        ApplyUserInfoDTO applyUserInfoDTO = new ApplyUserInfoDTO().builder()
                .userId(applyDO.getApplyUserId())
                .username(applyDO.getApplyRealName())
                .companyId(applyDO.getApplyCompanyId())
                .companyName(applyDO.getApplyCompanyName())
                .deptId(applyDO.getApplyDeptId())
                .deptName(applyDO.getApplyDeptName()).build();
        businessDataMap.put("applyUserInfo", applyUserInfoDTO);
        businessDataMap.put(BpmConstant.PROCESS_ID, applyDO.getProcInstId());

        List<FlowUserTaskDTO> nextNodeList = nodeService.getNextNodeList(applyDO.getDefinitionId()
                , nodeQueryVO.getTaskNodeId()
                , businessDataMap
                , false);
        ArrayList<NodeAssigneesDTO> list = new ArrayList<>();
        if (nextNodeList.size() != 0) {
            for (FlowUserTaskDTO flowUserTaskDTO : nextNodeList) {
                NodeAssigneesDTO dto = new NodeAssigneesDTO();
                BeanUtil.copyProperties(flowUserTaskDTO, dto);
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public List<GetApproveOpinionListVO> getApproveOpinionList(String applyId) {
        List<GetApproveOpinionListVO> approveOpinionList = baseMapper.getApproveOpinionList(applyId);
        List<String> ids = new ArrayList<>();
        approveOpinionList.forEach(getApproveOpinionListVO -> {
            ids.add(getApproveOpinionListVO.getTaskAssigneeUserId());
            if (getApproveOpinionListVO.getApproveAction() != null) {
                getApproveOpinionListVO.setApproveAction((String) EnumHelperUtil.customEnumUtil(ApproveActionEnum.class).getDesc(getApproveOpinionListVO.getApproveActionCode()));
            }
        });
        Map<String, String> map = FileUtil.getAvatarMap(ids);
        approveOpinionList.forEach(getApproveOpinionListVO -> {
            getApproveOpinionListVO.setAvatar(map.get(getApproveOpinionListVO.getTaskAssigneeUserId()));
        });
        return approveOpinionList;
    }


    /**
     * 清除多实例的用户任务
     *
     * @param userTaskDTO
     */
    @Override
    public void clearMultiInstanceTasks(UserTaskDTO userTaskDTO) {
        List<UserTaskDO> userTasks = getUserTaskByApplyId(userTaskDTO.getApplyId());
        userTasks.stream().filter(temp ->
                temp.getTaskStatus() < TaskConstant.TASK_COMPLETED).forEach(
                userTask -> {
                    boolean needDisable = true;
                    if (!userTaskDTO.getTaskNodeCode().equals(userTask.getTaskNodeCode())) {
                        needDisable = false;
                    }
                    if (needDisable) {
                        UserTaskDO disableUserTask = BeanUtil.copyProperties(userTask, UserTaskDO.class);
                        disableUserTask.setUpdateTime(LocalDateTime.now());
                        disableUserTask.setTaskStatus(TaskConstant.TASK_DISABLE);
                        disableUserTask.setApproveTime(LocalDateTime.now());
                        disableUserTask.setDueTime(LocalDateTime.now());
                        // 删除用户任务，保存操作历史
                        this.removeAndSaverHistory(BeanUtil.copyProperties(disableUserTask, UserTaskDTO.class), null);
                    }
                }
        );
    }

    @Override
    public boolean removeAndSaverHistory(UserTaskDTO userTaskDTO, Map<String, Object> processMap) {
        if (Objects.isNull(userTaskDTO)) {
            return false;
        }
        HistoryDO historyDO = new HistoryDO();
        BeanUtil.copyProperties(userTaskDTO, historyDO);
        // 保存历史记录
        historyService.insertHistory(historyDO);
        if (processMap != null) {
            HistoryFormDataSaveDTO historyFormDataSaveDTO = new HistoryFormDataSaveDTO().setApplyId(userTaskDTO.getApplyId())
                    .setProcessId(userTaskDTO.getProcessId())
                    .setProcInstId(userTaskDTO.getProcInstId())
                    .setTaskNodeId(userTaskDTO.getTaskNodeCode())
                    .setTenantId(userTaskDTO.getTenantId())
                    .setProcessMap(processMap);
            // 保存或修改历史流程数据
            historyFormDataService.saveOrUpdateFormData(historyFormDataSaveDTO);
        }
        // 删除已完成的任务
        this.deleteById(userTaskDTO.getId());
        return true;
    }

    @Override
    public Integer getToDoNumber() {
        // 当前登录人信息
        UserAccount userDetails = SecurityUtil.getCurrentUserNoNull();
        return this.baseMapper.selectToDoNumber(userDetails.getUserId());
    }
}
