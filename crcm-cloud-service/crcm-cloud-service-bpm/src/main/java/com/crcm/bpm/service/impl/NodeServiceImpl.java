package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.FindUserTypeConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.FlowElementUtils;
import com.crcm.bpm.domain.dto.request.UserRoleDetailQueryDTO;
import com.crcm.bpm.domain.dto.response.*;
import com.crcm.bpm.domain.entity.ApplyDO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.NodeDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.fegin.*;
import com.crcm.bpm.mapper.NodeMapper;
import com.crcm.bpm.service.ApplyService;
import com.crcm.bpm.service.HistoryService;
import com.crcm.bpm.service.NodeService;
import com.crcm.bpm.service.UserTaskService;
import com.crcm.bpm.service.impl.api.ImageServiceImpl;
import com.crcm.bpm.utils.PrecessThreadLocalUtil;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.SpringContextHolder;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NodeServiceImpl extends ServiceImpl<NodeMapper, NodeDO> implements NodeService {

    @Autowired
    private RemoteUserService userService;
    @Autowired
    private RemoteRoleService roleService;
    @Autowired
    private RemoteUserRoleService userRoleService;
    @Autowired
    private RemoteDeptService deptService;
    @Autowired
    private RemotePostService postService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserTaskService userTaskService;
//    @Resource
//    private FeignEmployeeService feignEmployeeService;
//    @Resource
//    private FeignSysSettingsService feignSysSettingsService;
    @Resource
    @Lazy
    private ImageServiceImpl imageService;

    private static ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    @Override
    public int saveNode(NodeDO nodeDO) {
        return this.baseMapper.insert(nodeDO);
    }

    @Override
    public int updateNode(NodeDO nodeDO) {
        return this.baseMapper.updateById(nodeDO);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public NodeDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<NodeDO> findList(NodeDO nodeDO) {
        QueryWrapper<NodeDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<NodeDO> findPage(Page page, NodeDO nodeDO) {
        QueryWrapper<NodeDO> queryWrapper = new QueryWrapper<>();
        IPage<NodeDO> pageNode = this.baseMapper.selectPage(page, queryWrapper);
        return pageNode;
    }

    @Override
    public List<FlowUserTaskDTO> getNextNodeList(String procDefId, String nodeId, Map<String, Object> dataMap, boolean multipleRecursion, Boolean setError) {
        threadLocal.set(setError);
        return getNextNodeList(procDefId, nodeId, dataMap, multipleRecursion);
    }

    @Override
    public List<FlowUserTaskDTO> getNextNodeList(String procDefId, String nodeId, Map<String, Object> dataMap, boolean multipleRecursion) {

        List<FlowUserTaskDTO> userTasks = new ArrayList<>();
        ProcessEngine processEngine = SpringContextHolder.getBean(ProcessEngine.class);
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        FlowElement currentFlowElement = null;

        if (StringUtils.isEmpty(nodeId)) {
            currentFlowElement = FlowElementUtils.getStartFlowElement(flowElements);
        } else {
            currentFlowElement = FlowElementUtils.getFlowElementById(nodeId, flowElements);
        }

        getNextNode(procDefId, flowElements, currentFlowElement, dataMap, userTasks, multipleRecursion);

        return userTasks;
    }

    private void getNextNode(String definitionId, Collection<FlowElement> flowElements, FlowElement flowElement, Map<String, Object> map, List<FlowUserTaskDTO> nextUserTask, boolean multipleRecursion) {

        // 还需支持默认连线 如果有设置默认条线，则先判断其他条线是否满足，如果都不满足，则加上默认条线
        String defaultFlow = "";

        //如果是结束节点
        if (flowElement instanceof EndEvent) {
            //如果是子任务的结束节点
            if (FlowElementUtils.getSubProcess(flowElements, flowElement) != null) {
                flowElement = FlowElementUtils.getSubProcess(flowElements, flowElement);
            }
        }
        List<SequenceFlow> outGoingFlows = null;
        if (flowElement instanceof Task) {
            defaultFlow = ((Task) flowElement).getDefaultFlow();
            outGoingFlows = ((Task) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof Gateway) {
            defaultFlow = ((Gateway) flowElement).getDefaultFlow();
            outGoingFlows = ((Gateway) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof StartEvent) {
            outGoingFlows = ((StartEvent) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof SubProcess) {
            defaultFlow = ((SubProcess) flowElement).getDefaultFlow();
            outGoingFlows = ((SubProcess) flowElement).getOutgoingFlows();
        }

        if (outGoingFlows != null && outGoingFlows.size() > 0) {
            //遍历所有的出线--找到可以正确执行的那一条
            for (SequenceFlow sequenceFlow : outGoingFlows) {
                //1.有表达式，且为true

                //2.无表达式
                String expression = sequenceFlow.getConditionExpression();
                if (StringUtils.isEmpty(expression) || FlowElementUtils.getJuleValue(expression, map)) {
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
                        calcNodeUser(flowUserTaskDTO, definitionId, map);
                        /*if (flowUserTaskDTO.isError()) {
                            // 跳出
                            break;
                        }*/

                        nextUserTask.add(flowUserTaskDTO);
                        // 是否需要连续找下一个节点 直到结束 true ：连续 false : 直找到第一个即可；
                        // 自动指派且候选人为空且执行策略为候选人为空时跳过时继续执行
                        if (multipleRecursion || (TaskConstant.ASSIGN_MODE_AUTO.equals(flowUserTaskDTO.getAssignMode()) && flowUserTaskDTO.isSkip())) {
                            getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                        }

                    }
                    //排他网关
                    else if (nextFlowElement instanceof ExclusiveGateway) {
                        getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                    }
                    //并行网关
                    else if (nextFlowElement instanceof ParallelGateway) {
                        if (((ParallelGateway) nextFlowElement).getIncomingFlows().size() == 1) {
                            getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                        } else if (map.containsKey(BpmConstant.PROCESS_ID)) {
                            List<String> accessLinesIds = imageService.getAccessLinesIds(map.get(BpmConstant.PROCESS_ID).toString());
                            Boolean flag = true;
                            for (SequenceFlow sequenceFlow1 : ((ParallelGateway) nextFlowElement).getIncomingFlows()) {
                                if (!sequenceFlow1.getId().equals(sequenceFlow.getId()) && accessLinesIds.indexOf(sequenceFlow1.getId()) < 0) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                            }
                        }
                    }
                    //接收任务
                    else if (nextFlowElement instanceof ReceiveTask) {
                        getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                    }
                    //子任务的起点
                    else if (nextFlowElement instanceof StartEvent) {
                        getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                    }
                    //结束节点
                    else if (nextFlowElement instanceof EndEvent) {
                        getNextNode(definitionId, flowElements, nextFlowElement, map, nextUserTask, multipleRecursion);
                    }
                }
            }
        }
    }

    @Override
    public NodeInfoDTO getNodeInfoByNodeIdAndDefinitionId(String nodeId, String definitionId) {

        if (StringUtils.isEmpty(definitionId)
                || StringUtils.isEmpty(nodeId)) {
            throw new BpmException(BpmError.ILLEGAL_ARGUMENT_ERROR);
        }
        LambdaQueryWrapper<NodeDO> queryWrapper = new QueryWrapper<NodeDO>().lambda()
                .eq(NodeDO::getNodeId, nodeId)
                .eq(NodeDO::getDefinitionId, definitionId);
        NodeDO nodeDO = this.baseMapper.selectOne(queryWrapper);
        if (nodeDO == null) {
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }
        NodeInfoDTO nodeInfoDTO = BeanUtil.copyProperties(nodeDO, NodeInfoDTO.class);
        return nodeInfoDTO;
    }

    @Override
    public FlowUserTaskDTO calcNodeUsers(String procDefId, String nodeId, Map<String, Object> dataMap, Boolean setError) {
        threadLocal.set(setError);
        FlowUserTaskDTO flowUserTaskDTO = new FlowUserTaskDTO();
        flowUserTaskDTO.setNodeId(nodeId);
        calcNodeUser(flowUserTaskDTO, procDefId, dataMap);
        return flowUserTaskDTO;
    }

    private void calcNodeUser(FlowUserTaskDTO flowUserTaskDTO, String definitionId, Map<String, Object> dataMap) {
        try {
            if (StringUtils.isEmpty(definitionId)
                    || flowUserTaskDTO == null
                    || StringUtils.isEmpty(flowUserTaskDTO.getNodeId())) {
                return;
            }
            NodeInfoDTO nodeInfoDTO = getNodeInfoByNodeIdAndDefinitionId(flowUserTaskDTO.getNodeId(), definitionId);
            List<String> owners = new ArrayList<>();
            List<String> assigneeNames = new ArrayList<>();
            List<String> assigneeIds = new ArrayList<>();
            List<Long> roleIdList = new ArrayList<>();

            RestResult<List<UserRoleDetailDTO>> result2 = null;
            String[] split = null;
            RestResult<UserInfoDTO> result3 = null;
            // 如果是发起人节点
            if (nodeInfoDTO.getTaskType().equals(BpmConstant.TASK_TYPE_START)) {
                if (dataMap.get(BpmConstant.APPLY_USER_INFO) != null) {
                    ApplyUserInfoDTO userInfoDTO = JSON.parseObject(JSON.toJSONString(dataMap.get(BpmConstant.APPLY_USER_INFO)), ApplyUserInfoDTO.class);
                    owners.add(userInfoDTO.getUserId());
                    assigneeNames.add(userInfoDTO.getUsername());
                    assigneeIds.add(userInfoDTO.getUserId().toString());
                }
            } else {
                // 通过不同的选择方式选择节点用户
                switch (nodeInfoDTO.getFindUserType()) {
                    // 用户选择器
                    case BpmConstant.FIND_USER_TYPE_BY_SELECTOR:
                        String userSelectors = nodeInfoDTO.getUserSelectors();
                        if (StringUtils.isNotBlank(userSelectors)) {
                            for (String selector : userSelectors.split(",")) {
                                switch (selector) {
                                    // 当前用户
                                    case BpmConstant.USER_SELECTOR_CURRENT_USER:
                                        UserAccount currentUser = SecurityUtil.getCurrentUser();
                                        owners.add(currentUser.getUserId());
                                        assigneeNames.add(currentUser.getUsername());
                                        assigneeIds.add(currentUser.getUserId());
                                        break;
                                    // 流程申请人
                                    case BpmConstant.USER_SELECTOR_PROMOTER:
                                        ApplyDO currentApply = applyService.getById(String.valueOf(dataMap.get(BpmConstant.APPLY_ID)));
                                        if (currentApply != null) {
                                            owners.add(currentApply.getApplyUserId());
                                            assigneeNames.add(currentApply.getApplyRealName());
                                            assigneeIds.add(currentApply.getApplyUserId());
                                        } else {
                                            ApplyUserInfoDTO userInfoDTO = JSONObject.parseObject(JSONObject.toJSONString(dataMap.get(BpmConstant.APPLY_USER_INFO))).toJavaObject(ApplyUserInfoDTO.class);
                                            owners.add(userInfoDTO.getUserId());
                                            assigneeNames.add(userInfoDTO.getUsername());
                                            assigneeIds.add(userInfoDTO.getUserId());
                                        }
                                        break;
                                    // 上一步执行人 暂时不搞
                                    case BpmConstant.USER_SELECTOR_THE_LAST_USER:
                                        break;
                                    // 部门负责人
                                    case BpmConstant.USER_SELECTOR_DEPT_HEAD:
                                        ApplyUserInfoDTO userInfoDTO = JSONObject.parseObject(JSONObject.toJSONString(dataMap.get(BpmConstant.APPLY_USER_INFO))).toJavaObject(ApplyUserInfoDTO.class);
                                        RestResult<DeptInfoDTO> result = deptService.getDeptInfo(userInfoDTO.getDeptId(), userInfoDTO.getTenantId());
                                        if (!result.isSuccess() || result.getData() == null || StringUtils.isBlank(result.getData().getDeptHeader())) {
//                                            throw new BpmException("部门负责人查询失败！", BpmError.DATA_NOT_FOUND_ERROR);
                                            break;
                                        }
                                        DeptInfoDTO deptInfo = result.getData();
                                        String postId = deptInfo.getDeptHeader();
                                        RestResult<List<PostUserDTO>> result1 = postService.getPostUsers(postId);
                                        if (result1.isSuccess() && result1.getData() != null) {
                                            List<PostUserDTO> postUsers = result1.getData();
                                            for (PostUserDTO postUser : postUsers) {
                                                owners.add(postUser.getUserId());
                                                assigneeNames.add(postUser.getUserName());
                                                assigneeIds.add(postUser.getUserId());
                                            }
                                        }
                                        break;
                                    // 分管领导
                                    case BpmConstant.USER_SELECTOR_CHARGE_LEADER:
                                        userInfoDTO = JSONObject.parseObject(JSONObject.toJSONString(dataMap.get(BpmConstant.APPLY_USER_INFO))).toJavaObject(ApplyUserInfoDTO.class);
//                                        Employee employee = deptService.getDeptLeaderInfo(userInfoDTO.getDeptId(), userInfoDTO.getTenantId());
//                                        if (employee == null) {
//                                            break;
//                                        }
//                                        owners.add(employee.getEmployeeId());
//                                        assigneeNames.add(employee.getEmployeeName());
//                                        assigneeIds.add(employee.getEmployeeId());
                                        break;
                                }
                            }
                        }
                        break;
                    case BpmConstant.FIND_USER_TYPE_BY_ROLE:
                        if (nodeInfoDTO.getRoleList() == null || nodeInfoDTO.getRoleList().size() == 0) {
                            throw new BpmException("配置角色为空！", BpmError.DATA_NOT_FOUND_ERROR);
                        }
                        result2 = userRoleService.getUserRoleDetailByCondition(UserRoleDetailQueryDTO.builder().roleIdList(nodeInfoDTO.getRoleList()).build());
                        if (!result2.isSuccess()) {
//                        log.error("getUserRoleDetailByCondition error ，param 【{}】 : message {} ", roleIdList, userRoleDetailDTOS.getEntityError());
                            throw new BpmException("角色用户未找到！", BpmError.DATA_NOT_FOUND_ERROR);
                        }
                        for (UserRoleDetailDTO userRoleDetailDTO : result2.getData()) {
                            owners.add(userRoleDetailDTO.getUserId());
                            assigneeNames.add(userRoleDetailDTO.getName());
                            assigneeIds.add(userRoleDetailDTO.getUserId().toString());
                        }

                        break;
                    case BpmConstant.FIND_USER_TYPE_BY_USER:

                        if (StringUtils.isEmpty(nodeInfoDTO.getUserIdList())) {
                            return;
                        }
                        split = nodeInfoDTO.getUserIdList().split(",");
                        String[] names = nodeInfoDTO.getUserNameList().split(",");
                        for (int i = 0; i < split.length; i++) {
                            owners.add(split[i]);
                            assigneeIds.add(split[i]);
                            assigneeNames.add(names[i]);
                        }
//                    for (String userId : split) {
//                        if (StringUtils.isEmpty(userId)) {
//                            continue;
//                        }
//                        // 暂时先用循环查询
//                        result3 = userService.getUserInfoById(userId);
//                        if (result3.isSuccess()) {
//                            ownerNames.add(result3.getData().getName());
//                        }
//
//                        owners.add(userId);
//                        ownerUserIds.add(userId);
//                    }
                        break;
                    case BpmConstant.FIND_USER_TYPE_BY_DESIGNATED_PERSONNEL:
                        if (dataMap.get(nodeInfoDTO.getAssigneeField()) != null) {
                            String string = dataMap.get(nodeInfoDTO.getAssigneeField()).toString();
                            split = nodeInfoDTO.getUserIdList().split(",");
                            for (String userId : split) {
                                if (StringUtils.isEmpty(userId)) {
                                    continue;
                                }
                                result3 = userService.getUserInfoById(userId);
                                if (result3.isSuccess()) {
                                    assigneeNames.add(result3.getData().getName());
                                }
                                owners.add(userId);
                                assigneeIds.add(userId);
                            }
                        }
                        break;
                    case BpmConstant.FIND_USER_TYPE_BY_NODE_USER:
                        if (StringUtils.isEmpty(nodeInfoDTO.getRelationNodeId())) {
                            throw new BpmException("获取指定节点信息失败！", BpmError.DATA_NOT_FOUND_ERROR);
                        }
                        LambdaQueryWrapper<HistoryDO> wrapper = new LambdaQueryWrapper<>();
                        wrapper.eq(dataMap.containsKey(BpmConstant.PROCESS_ID), HistoryDO::getProcInstId, dataMap.get(BpmConstant.PROCESS_ID));
                        wrapper.eq(dataMap.containsKey(BpmConstant.APPLY_ID), HistoryDO::getApplyId, dataMap.get(BpmConstant.APPLY_ID));
                        wrapper.in(HistoryDO::getTaskNodeCode, nodeInfoDTO.getRelationNodeId());
                        wrapper.ne(HistoryDO::getApproveOpinion, "无任务执行人，执行跳过");
                        List<HistoryDO> historyDOList = historyService.list(wrapper);
                        if (historyDOList != null && historyDOList.size() > 0) {
                            for (HistoryDO historyDO : historyDOList) {
                                if (owners.indexOf(historyDO.getTaskAssigneeUserId()) < 0) {
                                    owners.add(historyDO.getTaskAssigneeUserId());
                                    assigneeIds.add(historyDO.getTaskAssigneeUserId());
                                    assigneeNames.add(historyDO.getTaskAssigneeRealName());
                                }
                            }
                        }
                        LambdaQueryWrapper<UserTaskDO> userTaskwrapper = new LambdaQueryWrapper<>();
                        userTaskwrapper.eq(dataMap.containsKey(BpmConstant.PROCESS_ID), UserTaskDO::getProcInstId, dataMap.get(BpmConstant.PROCESS_ID));
                        userTaskwrapper.eq(dataMap.containsKey(BpmConstant.APPLY_ID), UserTaskDO::getApplyId, dataMap.get(BpmConstant.APPLY_ID));
                        userTaskwrapper.in(UserTaskDO::getTaskNodeCode, nodeInfoDTO.getRelationNodeId());
                        List<UserTaskDO> userTaskDOList = userTaskService.list(userTaskwrapper);
                        if (userTaskDOList != null && userTaskDOList.size() > 0) {
                            for (UserTaskDO userTaskDO : userTaskDOList) {
                                if (!StringUtils.isEmpty(userTaskDO.getTaskAssigneeUserId()) && owners.indexOf(userTaskDO.getTaskAssigneeUserId()) < 0) {
                                    owners.add(userTaskDO.getTaskAssigneeUserId());
                                    assigneeIds.add(userTaskDO.getTaskAssigneeUserId());
                                    assigneeNames.add(userTaskDO.getTaskAssigneeRealName());
                                }
                            }
                        }
                        break;
                    case BpmConstant.FIND_USER_TYPE_BY_POST:
                        String postId = nodeInfoDTO.getPostId();
                        RestResult<List<PostUserDTO>> result = postService.getPostUsers(postId);
                        if (result.getData() == null) {
                            throw new BpmException("岗位用户未找到！", BpmError.DATA_NOT_FOUND_ERROR);
                        }
                        List<PostUserDTO> postUsers = result.getData();
                        for (PostUserDTO postUser : postUsers) {
                            owners.add(postUser.getUserId());
                            assigneeNames.add(postUser.getUserName());
                            assigneeIds.add(postUser.getUserId());
                        }
                        break;
                    case BpmConstant.FIND_USER_TYPE_BY_ROLE_AND_DEPARTMENT:
                        if (nodeInfoDTO.getRoleList() == null || nodeInfoDTO.getRoleList().size() == 0) {
                            throw new BpmException("配置角色为空！", BpmError.DATA_NOT_FOUND_ERROR);
                        }
                        ApplyUserInfoDTO userInfoDTO = JSONObject.parseObject(JSONObject.toJSONString(dataMap.get(BpmConstant.APPLY_USER_INFO))).toJavaObject(ApplyUserInfoDTO.class);
//                        RestResult<List<Employee>> restResult = feignEmployeeService.getByRoleIdAndDepartmentId(nodeInfoDTO.getRoleList(), userInfoDTO.getDeptId());
                        // TODO 上面的远程调用调通后删除下面的RestResult.failed()
                        RestResult<Object> restResult = RestResult.failed();
                        if (restResult.getData() == null) {
                            throw new BpmException("角色用户未找到！", BpmError.DATA_NOT_FOUND_ERROR);
                        }
//                        for (Employee employee : restResult.getData()) {
//                            owners.add(employee.getEmployeeId());
//                            assigneeNames.add(employee.getEmployeeName());
//                            assigneeIds.add(employee.getEmployeeId());
//                        }
                        break;
                    default:
                        break;
                }
            }

            BeanUtil.copyProperties(nodeInfoDTO, flowUserTaskDTO);
            flowUserTaskDTO.setAssigneeNames(assigneeNames);
            flowUserTaskDTO.setOwners(owners);
            flowUserTaskDTO.setAssigneeIds(assigneeIds);
            if (StringUtils.isNotBlank(nodeInfoDTO.getFormEditField())) {
                flowUserTaskDTO.setFormEditFields(Arrays.asList(nodeInfoDTO.getFormEditField().split(",")));
            } else {
                flowUserTaskDTO.setFormEditFields(new ArrayList<>());
            }

            if (owners.size() == 0) {
                switch (nodeInfoDTO.getHandlerStrategy()) {
                    case BpmConstant.HANDLER_STRATEGY_SKIP:
                        flowUserTaskDTO.setSkip(true);
                        break;
                    case BpmConstant.HANDLER_STRATEGY_ADMIN:
                        flowUserTaskDTO.setDefaultSetAdmin(true);
                        break;
                    case BpmConstant.HANDLER_STRATEGY_ERROR:
                        flowUserTaskDTO.setError(true);
                        break;
                    default:
                        break;
                }
            }
            dataMap.put(nodeInfoDTO.getNodeId(), owners);
            // 不是前端指定
            if (!FindUserTypeConstant.FRONT_END_SPECIFIED.equals(flowUserTaskDTO.getFindUserType())) {
                checkFlowUserTaskDTO(flowUserTaskDTO);
            }
        } catch (CustomException e) {
            if (threadLocal.get() != null && threadLocal.get() == true) {
                PrecessThreadLocalUtil.THREAD_LOCAL.set(e);
            }
            throw e;
        } catch (BpmException e) {
            if (threadLocal.get() != null && threadLocal.get() == true) {
                CustomException businessException = new CustomException(e.getBpmError().getCode(), e.getMessage() != null ? e.getMessage() : e.getBpmError().getMessage());
                PrecessThreadLocalUtil.THREAD_LOCAL.set(businessException);
            }
            throw e;
        } catch (Exception e) {
            log.error("查找下一节点审核人失败", e);
            CustomException businessException = new CustomException(HttpStatus.HTTP_BAD_METHOD, "查找下一节点审核人失败,请联系管理员！");
            if (threadLocal.get() != null && threadLocal.get() == true) {
                PrecessThreadLocalUtil.THREAD_LOCAL.set(businessException);
            }
            throw businessException;
        } finally {
            threadLocal.remove();
        }
    }

    public void checkFlowUserTaskDTO(FlowUserTaskDTO flowUserTaskDTO) {
        if (CollectionUtil.isEmpty(flowUserTaskDTO.getAssigneeIds())) {
            CustomException businessException;
            // 用户为空时处理 报错
            if (flowUserTaskDTO.isError()) {
                log.error("节点[" + flowUserTaskDTO.getNodeId() + "]人员找不到，请联系管理员处理！");
                throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "节点[" + flowUserTaskDTO.getNodeName() + "]找不到审核人员，请联系管理员处理！");
            }
            // 用户为空时处理 默认管理员处理
            if (flowUserTaskDTO.isDefaultSetAdmin()) {
                List assigneeList = new ArrayList<>();
                List assigneeNameList = new ArrayList<>();
                // 暂时写死
//                RestResult<List<SysSettings>> result = feignSysSettingsService.getMsgByCode(BpmConstant.COMPANY_ADMINISTRATOR, SecurityConstants.FROM_IN);
                // TODO 上面的远程调用调通后删除下面的RestResult.failed()
                RestResult<Object> result = RestResult.failed();
                if (ObjectUtils.isNotEmpty(result.getData())) {
//                    SysSettings sysSetting = result.getData().get(0);
//                    if (sysSetting.getValue() == null || "".equals(sysSetting.getValue())) {
//                        log.error("各公司管理员设置为空！");
//                        throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "节点[" + flowUserTaskDTO.getNodeName() + "]找不到审核人员，请联系管理员处理！");
//                    }
//                    JSONObject jsonObject = JSONObject.parseObject(sysSetting.getValue());
//                    Object user = jsonObject.get(SecurityUtil.getCurrentUser().getComId());
//                    if (user != null) {
//                        String[] userAndName = user.toString().split("-");
//                        assigneeList.add(userAndName[0]);
//                        assigneeNameList.add(userAndName[1]);
//                    } else {
//                        log.error("公司[" + SecurityUtil.getCurrentUser().getComId() + "]找不到管理员！");
//                        throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "节点[" + flowUserTaskDTO.getNodeName() + "]找不到审核人员，请联系管理员处理！");
//                    }
                } else {
                    log.error("未设置各公司管理员！");
                    throw new CustomException(HttpStatus.HTTP_BAD_REQUEST, "节点[" + flowUserTaskDTO.getNodeName() + "]找不到审核人员，请联系管理员处理！");
                }

                flowUserTaskDTO.setAssigneeIds(assigneeList);
                flowUserTaskDTO.setAssigneeNames(assigneeNameList);
            }
        }
    }
}
