package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.handler.TaskHandler;
import com.crcm.bpm.core.utils.CustomFormUtil;
import com.crcm.bpm.core.utils.SendTaskMessageUtil;
import com.crcm.bpm.domain.dto.NodeAssigneesDTO;
import com.crcm.bpm.domain.dto.request.ApplyReqDTO;
import com.crcm.bpm.domain.dto.request.FormDataSaveOrUpdateDTO;
import com.crcm.bpm.domain.dto.request.UserTaskUpdateDTO;
import com.crcm.bpm.domain.dto.response.*;
import com.crcm.bpm.domain.entity.*;
import com.crcm.bpm.domain.vo.ApplyAddReqVO;
import com.crcm.bpm.mapper.ApplyMapper;
import com.crcm.bpm.service.*;
import com.crcm.bpm.utils.FormDataUtil;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, ApplyDO> implements ApplyService {

    @Autowired
    private ProcessService processService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private FormService formService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private FormDataService formDataService;
    @Autowired
    private ProcessCirculationService processCirculationService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;
    @Autowired
    @Lazy
    private TaskHandler taskHandler;


    @Override
    public int saveApply(ApplyDO applyDO) {
        return this.baseMapper.insert(applyDO);
    }

    @Override
    public int updateApply(ApplyDO applyDO) {
        return this.baseMapper.updateById(applyDO);
    }

    @Override
    public int deleteById(String id) {
        ApplyDO applyDO = this.baseMapper.selectById(id);
        if (applyDO == null) {
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }
        if (applyDO.getApplyStatus() != BpmConstant.APPLY_STATUS_DRAFT && applyDO.getApplyStatus() != BpmConstant.APPLY_STATUS_DISABLE) {
            throw new BpmException(BpmError.USER_APPLY_DELETE_LIMIT);
        }

        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public ApplyDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<ApplyDO> findList(ApplyDO applyDO) {
        QueryWrapper<ApplyDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<ApplyDO> findPage(Page page, ApplyDO applyDO) {
        QueryWrapper<ApplyDO> queryWrapper = new QueryWrapper<>();
        IPage<ApplyDO> pageApply = this.baseMapper.selectPage(page, queryWrapper);
        return pageApply;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<FlowUserTaskDTO> startProcess(ApplyReqDTO applyReqDTO) {
        if (applyReqDTO == null) {
            throw new BpmException(BpmError.PROCESS_KEY_NO_FIND);
        }
        ProcessInfoDTO processInfo = processService.getProcessInfoById(applyReqDTO.getProcessId());
        if (processInfo == null) {
            throw new BpmException(BpmError.PROCESS_NO_FIND_ERROR);
        }
        Map<String, Object> variables = applyReqDTO.getData();
        if (variables == null) {
            variables = new HashMap<>();
        }
        if (StringUtils.isBlank(applyReqDTO.getStartUserId())) {
            applyReqDTO.setStartUserId(applyReqDTO.getCurrentUserId());
        }
        UserAccount currentUser = SecurityUtil.getCurrentUser();
        ApplyUserInfoDTO applyUserInfo = ApplyUserInfoDTO.builder()
                .userId(currentUser.getUserId())
                .companyId(currentUser.getComId())
                .companyName(currentUser.getComName())
                .tenantId(currentUser.getTenantId())
                .username(currentUser.getEmpName())
                .deptId(applyReqDTO.getDeptId())
                .deptName(applyReqDTO.getDeptName()).build();
        // 设置申请人部门信息
        if (StringUtils.isBlank(applyReqDTO.getDeptId())) {
            if (StringUtils.isBlank(currentUser.getDeptId())) {
                if (CollectionUtils.isNotEmpty(currentUser.getDeptList())) {
                    applyUserInfo.setDeptId(currentUser.getDeptList().get(0));
                }
            } else {
                applyUserInfo.setDeptId(currentUser.getDeptId());
            }

        }

        // 解析表单数据
        Map<String, Object> dataMap = JSON.parseObject(JSON.toJSONString(variables.get(BpmConstant.FORM_DATA_KEY)), Map.class);
        for (String key : dataMap.keySet()) {
            variables.put(key, FormDataUtil.toDecimal(dataMap.get(key)));
        }

        variables.put(BpmConstant.APPLY_ID, Convert.toInt(applyReqDTO.getApplyId()));
        variables.put(BpmConstant.PROCESS_ID, applyReqDTO.getProcessId());
        variables.put(BpmConstant.TENANT_ID, applyReqDTO.getTenantId());
        variables.put(BpmConstant.APPLY_USER_INFO, applyUserInfo);
        variables.put(BpmConstant.DEFAULT_ASSIGNEE_USER_EXP, applyReqDTO.getStartUserId());
        variables.put(BpmConstant.APPLY_COMPANY_ID, applyUserInfo.getCompanyId());
        variables.put(TaskConstant.OWNER_USER_EXP, applyUserInfo.getUserId());
        variables.put(TaskConstant.OWNER_USER_NAME_EXP, applyUserInfo.getUsername());
        variables.put(BpmConstant.APPLY_DEPT, applyUserInfo.getDeptId());
        // 指定审核人方式
        variables.put(BpmConstant.ASSIGN_TASK_USER_WAY, applyReqDTO.getAssignTaskUserWay());
        // 指定审核人方式
        variables.put(BpmConstant.ASSIGN_TASK_USER_WAY, applyReqDTO.getAssignTaskUserWay());
        // 前端选定审核人ID
        variables.put(TaskConstant.CHECKED_ASSIGNEE_LIST_EXP, applyReqDTO.getAssignUserList());
        // 前端选定审核人ID
        variables.put(TaskConstant.CHECKED_ASSIGNEE_NAME_LIST_EXP, applyReqDTO.getAssignUserNameList());

        boolean firstSubmit;
        ApplyDO applyDO = new ApplyDO();
        UserTaskUpdateDTO userTaskUpdateDTO = null;
        UserTaskUpdateDTO firstUserTask = null;
        TaskService taskService = processEngine.getTaskService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String applySn = null;
        // 非初次提交
        if (applyReqDTO.getApplyId() != null && Convert.toLong(applyReqDTO.getApplyId()) > 0) {
            applyDO = this.getById(applyReqDTO.getApplyId());
            if (applyDO == null) {
                log.error("startProcess applyDO is null ");
                throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
            }
            applySn = applyDO.getApplySn();
            if (applyDO.getApplyStatus() != BpmConstant.APPLY_STATUS_DRAFT) {
                throw new BpmException(BpmError.SUBMIT_REPET_APPLY_ERROR);
            }
        }

        ModelInfoDTO modelInfo = null;
        FormDO formInfo = null;
        // 判断是否是初次提交
        if (StringUtils.isEmpty(applyDO.getProcInstId())) {  // 初次提交
            firstSubmit = true;
            modelInfo = modelService.findMainModel(applyReqDTO.getProcessKey());
            if (modelInfo == null) {
                throw new BpmException(BpmError.MODEL_NO_FIND_ERROR);
            }
            formInfo = formService.getById(modelInfo.getFormId());
            applyDO = generateApply(applyUserInfo, processInfo, modelInfo, applyReqDTO, "");
            this.saveOrUpdate(applyDO);
            applyDO.setApplyStatus(BpmConstant.APPLY_STATUS_APPROVING);
            variables.put(BpmConstant.AUTO_COMPLETE_FIRST_NODE_KEY, modelInfo.getAutoCompleteFirstNode());
            variables.put(BpmConstant.APPLY_ID, applyDO.getId());
            // 第一次提交
//            Authentication.setAuthenticatedUserId(String.valueOf(applyReqDTO.getStartUserId()));
            /* 以Builder 方式启动流程，解决 无 租户 和 Id 的启动方式 使用租户的前提时，部署时 也需要设置 租户编号！ */
//            ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
////                    .tenantId(applyReqDTO.getTenantId())
//                    .processDefinitionId(modelInfo.getDefinitionId())
//                    .variables(variables)
//                    .start();
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(modelInfo.getDefinitionId(), variables);
            /* 防止多线程的时候出问题 */
            Authentication.setAuthenticatedUserId(null);
            applyDO.setProcInstId(processInstance.getProcessInstanceId());
            this.updateById(applyDO);
        } else {
            firstSubmit = false;
            ModelDO modelDO = modelService.getById(applyDO.getModelId());
            modelInfo = BeanUtil.copyProperties(modelDO, ModelInfoDTO.class);
            formInfo = formService.getById(modelInfo.getFormId());
            // 非第一次提交
            ApplyDO tempApply = generateApply(applyUserInfo, processInfo, modelInfo, applyReqDTO, applyDO.getProcInstId());
            tempApply.setId(applyDO.getId());
            tempApply.setApplySn(applyDO.getApplySn());
            tempApply.setApplyStatus(BpmConstant.APPLY_STATUS_APPROVING);
            this.updateById(tempApply);
        }

        // find firstUserTask
        List<UserTaskDTO> userTasks = userTaskService.getUserTaskByProcInstId(applyDO.getProcInstId());
        if (userTasks == null || userTasks.size() == 0) {
            log.error("startProcess error ,message {}", "用户任务不存在！");
            throw new BpmException(BpmError.USER_TASK_NO_FIND_ERROR);
        }

        FormDataSaveOrUpdateDTO formDataSaveOrUpdateDTO = FormDataSaveOrUpdateDTO.builder()
                .applyId(applyDO.getId())
                .taskId(firstUserTask != null ? firstUserTask.getId() : null)
                .processId(applyDO.getProcessId())
                .procInstId(applyDO.getProcInstId())
                .tenantId(applyReqDTO.getTenantId())
                .formKey(applyReqDTO.getFormKey()).build();

        for (UserTaskDTO userTaskDTO : userTasks) {
            userTaskUpdateDTO = BeanUtil.copyProperties(userTaskDTO, UserTaskUpdateDTO.class);
            if (BpmConstant.TASK_TYPE_START.equals(userTaskUpdateDTO.getTaskType())
                    && userTaskUpdateDTO.getTaskStatus() < TaskConstant.TASK_COMPLETED) {
                firstUserTask = userTaskUpdateDTO;
                firstUserTask.setProcessId(applyDO.getProcessId());
                formDataSaveOrUpdateDTO.setUserTaskDTO(userTaskDTO);
            }
        }

        variables.put(BpmConstant.APPLY_SN, applyDO.getApplySn());
        formDataSaveOrUpdateDTO.setDataMap(variables);
        //保存数据
        formDataService.batchSaveOrUpdateFormData(formDataSaveOrUpdateDTO);

        // 根据配置自动完成首个任务
        List<FlowUserTaskDTO> flowUserTaskDTOList = new ArrayList<>();
        if (BpmConstant.AUTO_COMPLETE_FIRST_NODE == (modelInfo.getAutoCompleteFirstNode())
                && firstUserTask != null) {
            variables.put(BpmConstant.TASK_ID, firstUserTask.getId());
            taskService.setVariables(firstUserTask.getActTaskId(), variables);
            firstUserTask.setTaskAssigneeUserId(firstUserTask.getTaskOwnerUserId());
            firstUserTask.setTaskAssigneeRealName(firstUserTask.getTaskOwnerRealName());
            firstUserTask.setClaimTime(LocalDateTime.now());
            firstUserTask.setDueTime(LocalDateTime.now());
            firstUserTask.setTaskStatus(TaskConstant.TASK_COMPLETED);
            firstUserTask.setApproveTime(LocalDateTime.now());
            firstUserTask.setApproveOpinion("通过");
            firstUserTask.setApproveResult(TaskConstant.APPROVE_ACTION_PASS);
            userTaskService.updateUserTask(BeanUtil.copyProperties(firstUserTask, UserTaskDO.class));

            // 提交任务
            processCirculationService.complete(firstUserTask.getId(), firstUserTask.getActTaskId(), firstUserTask.getApplyId(), variables, applyDO.getApplyTitle());
//            taskService.complete(firstUserTask.getActTaskId());
        } else {
            SendTaskMessageUtil.sendMsg(BeanUtil.copyProperties(firstUserTask, UserTaskDO.class), firstUserTask.getTaskOwnerRealName());
        }
        processCirculationService.editAttachments(applyDO.getId().toString(), variables);

//        // 保存定制表单信息
//        saveCustomForm(modelInfo.getFormCode(), applyDO.getId(), dataMap);
        return flowUserTaskDTOList;
    }

    private void saveCustomForm(String formCode, Long applyId, Map<String, Object> formData, String authority) {
        if (StringUtils.isNotEmpty(formCode)) {
            Boolean flag = CustomFormUtil.saveCustomForm(formCode, applyId, JSON.toJSONString(formData), authority);
            if (!flag) {
                throw new BpmException(BpmError.SYSTEM_ERROR);
            }
        }
    }

    private ApplyDO generateApply(ApplyUserInfoDTO applyUserInfo, ProcessInfoDTO processDTO, ModelInfoDTO modelInfo, ApplyReqDTO applyReqDTO, String processInstanceId) {

        /* 单据编号默认规则 流程简称 + 用户工号 + 当前时间mmssSSSS  */
        StringBuffer applySn = new StringBuffer();

        applySn.append(generateApplySn(processDTO.getProcessAbbr()));

        return ApplyDO.builder()
                .id(applyReqDTO.getApplyId())
                .tenantId(applyReqDTO.getTenantId())
                .applyCompanyId(applyUserInfo.getCompanyId())
                .applyCompanyName(applyUserInfo.getCompanyName())
                .applyDeptId(applyUserInfo.getDeptId())
                .applyDeptName(applyUserInfo.getDeptName())
                .applyUserId(applyUserInfo.getUserId())
                .applyRealName(applyUserInfo.getUsername())
                .procInstId(processInstanceId)
                .formKey(!StringUtils.isEmpty(applyReqDTO.getFormKey()) ? applyReqDTO.getFormKey() : "")
                .modelId(modelInfo.getId())
                .flowType(String.valueOf(modelInfo.getFlowType()))
                .processId(processDTO.getId())
                .processKey(modelInfo.getProcessKey())
                .processName(processDTO.getProcessName())
                .definitionId(modelInfo.getDefinitionId())
                .applyRealName(applyUserInfo.getUsername())
                .applySn(applySn.toString())
                .applyTitle(StringUtils.isEmpty(applyReqDTO.getApplyTitle()) ? generateApplyTitle(processDTO, applyUserInfo.getUsername(), applyUserInfo.getDeptName()) : applyReqDTO.getApplyTitle())
                .build();
    }

    @Override
    public String generateApplySn(String applySnPrefix) {

        if (applySnPrefix == null) {
            applySnPrefix = "";
        }
        return applySnPrefix + LocalDateTime.now().format(DateTimeFormatter.ofPattern("mmssSSSS"));
    }

    @Override
    public ApplyDTO getApplyByApplyId(Long applyId) {
        ApplyDO applyDO = this.getById(applyId);
        return BeanUtil.copyProperties(applyDO, ApplyDTO.class);
    }

    @Override
    public void updateApplyStatusByProInsId(ApplyDO applyDO) {
        LambdaQueryWrapper<ApplyDO> queryWrapper = new QueryWrapper<ApplyDO>().lambda().eq(ApplyDO::getProcInstId, applyDO.getProcInstId());
        this.baseMapper.update(applyDO, queryWrapper);
    }

    @Override
    public Boolean saveDraft(ApplyAddReqVO applyAddReqVO) {
        UserAccount currentUser = SecurityUtil.getCurrentUser();
        ModelInfoDTO modelInfo = modelService.findMainModel(applyAddReqVO.getProcessKey());
        ApplyDO applyDO = new ApplyDO();
        // 草稿
        applyDO.setId(applyAddReqVO.getApplyId());
        applyDO.setApplyTitle(applyAddReqVO.getApplyTitle());
        applyDO.setApplyStatus(1);
        applyDO.setApplyUserId(currentUser.getUserId());
        applyDO.setApplyRealName(currentUser.getEmpName());
        applyDO.setApplyCompanyId(currentUser.getComId());
        applyDO.setApplyCompanyName(currentUser.getComName());
        applyDO.setApplyDeptId(applyAddReqVO.getDeptId());
        applyDO.setApplyDeptName(applyAddReqVO.getDeptName());
        applyDO.setProcessId(Long.valueOf(applyAddReqVO.getProcessId()));
        applyDO.setProcessKey(applyAddReqVO.getProcessKey());
        applyDO.setProcessName(applyAddReqVO.getProcessName());
        applyDO.setDefinitionId(modelInfo.getDefinitionId());
        applyDO.setFormKey(applyAddReqVO.getFormKey());
        this.saveOrUpdate(applyDO);
        //保存数据
        FormDataSaveOrUpdateDTO formDataSaveOrUpdateDTO = FormDataSaveOrUpdateDTO.builder()
                .applyId(applyDO.getId())
                .processId(applyDO.getProcessId())
                .procInstId(applyDO.getProcInstId())
                .formKey(applyAddReqVO.getFormKey()).build();
        Map<String, Object> variables = applyAddReqVO.getData();
        Map<String, Object> dataMap = JSON.parseObject(JSON.toJSONString(variables.get(BpmConstant.FORM_DATA_KEY)), Map.class);
        for (String key : dataMap.keySet()) {
            variables.put(key, dataMap.get(key));
        }
        formDataSaveOrUpdateDTO.setDataMap(variables);

        // 附件信息
        processCirculationService.editAttachments(applyDO.getId().toString(), variables);

        // 定制表单-保存到业务表
        saveCustomForm(modelInfo.getFormId(), applyDO.getId(), dataMap, "draft");


        return formDataService.saveFormData(formDataSaveOrUpdateDTO);
    }

    @Override
    public List<NodeAssigneesDTO> getStartNodeInfo(ApplyReqDTO applyReqDTO) {
        if (applyReqDTO == null) {
            throw new BpmException(BpmError.PROCESS_KEY_NO_FIND);
        }
        ProcessInfoDTO processInfo = processService.getProcessInfoById(applyReqDTO.getProcessId());
        if (processInfo == null) {
            throw new BpmException(BpmError.PROCESS_NO_FIND_ERROR);
        }
        Map<String, Object> variables = applyReqDTO.getData();
        if (variables == null) {
            variables = new HashMap<>();
        }
        if (StringUtils.isBlank(applyReqDTO.getStartUserId())) {
            applyReqDTO.setStartUserId(applyReqDTO.getCurrentUserId());
        }
        UserAccount currentUser = SecurityUtil.getCurrentUser();
        ApplyUserInfoDTO applyUserInfo = ApplyUserInfoDTO.builder()
                .userId(currentUser.getUserId())
                .companyId(currentUser.getComId())
                .companyName(currentUser.getComName())
                .tenantId(currentUser.getTenantId())
                .username(currentUser.getEmpName())
                .deptId(applyReqDTO.getDeptId())
                .deptName(applyReqDTO.getDeptName()).build();
        // 设置申请人部门信息
        if (StringUtils.isBlank(applyReqDTO.getDeptId())) {
            if (StringUtils.isBlank(currentUser.getDeptId())) {
                if (CollectionUtils.isNotEmpty(currentUser.getDeptList())) {
                    applyUserInfo.setDeptId(currentUser.getDeptList().get(0));
                }
            } else {
                applyUserInfo.setDeptId(currentUser.getDeptId());
            }
        }

        // 解析表单数据
        Map<String, Object> dataMap = JSON.parseObject(JSON.toJSONString(variables.get(BpmConstant.FORM_DATA_KEY)), Map.class);
        for (String key : dataMap.keySet()) {
            variables.put(key, FormDataUtil.toDecimal(dataMap.get(key)));
        }

        variables.put(BpmConstant.APPLY_ID, Convert.toInt(applyReqDTO.getApplyId()));
        variables.put(BpmConstant.PROCESS_ID, applyReqDTO.getProcessId());
        variables.put(BpmConstant.TENANT_ID, applyReqDTO.getTenantId());
        variables.put(BpmConstant.APPLY_USER_INFO, applyUserInfo);
        variables.put(BpmConstant.DEFAULT_ASSIGNEE_USER_EXP, applyReqDTO.getStartUserId());
        variables.put(BpmConstant.APPLY_COMPANY_ID, applyUserInfo.getCompanyId());
        variables.put(TaskConstant.OWNER_USER_EXP, applyUserInfo.getUserId());
        variables.put(TaskConstant.OWNER_USER_NAME_EXP, applyUserInfo.getUsername());
        variables.put(BpmConstant.APPLY_DEPT, applyUserInfo.getDeptId());
        // 指定审核人方式
        variables.put(BpmConstant.ASSIGN_TASK_USER_WAY, applyReqDTO.getAssignTaskUserWay());
        // 指定审核人方式
        variables.put(BpmConstant.ASSIGN_TASK_USER_WAY, applyReqDTO.getAssignTaskUserWay());
        // 前端选定审核人ID
        variables.put(TaskConstant.CHECKED_ASSIGNEE_LIST_EXP, applyReqDTO.getAssignUserList());
        // 前端选定审核人ID
        variables.put(TaskConstant.CHECKED_ASSIGNEE_NAME_LIST_EXP, applyReqDTO.getAssignUserNameList());

        ApplyDO applyDO = new ApplyDO();
        // 非初次提交
        if (applyReqDTO.getApplyId() != null && Convert.toLong(applyReqDTO.getApplyId()) > 0) {
            applyDO = this.getById(applyReqDTO.getApplyId());
            if (applyDO == null) {
                log.error("startProcess applyDO is null ");
                throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
            }
            if (applyDO.getApplyStatus() != BpmConstant.APPLY_STATUS_DRAFT) {
                throw new BpmException(BpmError.SUBMIT_REPET_APPLY_ERROR);
            }
        }

        ModelInfoDTO modelInfo;
        // 判断是否是初次提交
        if (StringUtils.isEmpty(applyDO.getProcInstId())) {  // 初次提交
            modelInfo = modelService.findMainModel(applyReqDTO.getProcessKey());
            if (modelInfo == null) {
                throw new BpmException(BpmError.MODEL_NO_FIND_ERROR);
            }
            applyDO = generateApply(applyUserInfo, processInfo, modelInfo, applyReqDTO, "");
            applyDO.setApplyStatus(BpmConstant.APPLY_STATUS_APPROVING);
            variables.put(BpmConstant.AUTO_COMPLETE_FIRST_NODE_KEY, modelInfo.getAutoCompleteFirstNode());
            variables.put(BpmConstant.APPLY_ID, applyDO.getId());
        } else {
            ModelDO modelDO = modelService.getById(applyDO.getModelId());
            modelInfo = BeanUtil.copyProperties(modelDO, ModelInfoDTO.class);
        }
        variables.put(BpmConstant.APPLY_SN, applyDO.getApplySn());
        if (BpmConstant.AUTO_COMPLETE_FIRST_NODE == (modelInfo.getAutoCompleteFirstNode())) {// 发起节点自动流传
            // 查询发起节点id
            LambdaQueryWrapper<NodeDO> nodeDOWrapper = new LambdaQueryWrapper<>();
            nodeDOWrapper.eq(NodeDO::getModelId, modelInfo.getId());
            nodeDOWrapper.eq(NodeDO::getTaskType, BpmConstant.TASK_TYPE_START);
            nodeDOWrapper.eq(NodeDO::getDeleted, 0);
            List<NodeDO> nodeDOList = nodeService.list(nodeDOWrapper);

            List<FlowUserTaskDTO> nextNodeList = nodeService.getNextNodeList(applyDO.getDefinitionId()
                    , nodeDOList.get(0).getNodeId()
                    , variables
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
        return null;
    }

    @Override
    public ApplyDO getForRelation(ApplyDO applyDO) {
        if (applyDO.getId() == null) {
            throw new BpmException(BpmError.ILLEGAL_ARGUMENT_ERROR);
        }

        ApplyDO history = this.baseMapper.selectById(applyDO.getId());
        if (history == null) {
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }

        // 登录人
        if (!SecurityUtil.getCurrentUserId().equals(history.getApplyUserId())) {
            throw new BpmException(BpmError.NO_EQUIL_APPLY_USER);
        }

        // 判断流程是否结束
        if (history.getApplyStatus() != BpmConstant.APPLY_STATUS_PASS) {
            throw new BpmException(BpmError.PROCESS_NOT_FINISH);
        }

        return history;
    }

    /**
     * 功能描述:  统一获取 申请标题
     *
     * @param processInfo   流程基本信息
     * @param applyRealName 申请人姓名
     * @param deptName      部门姓名
     * @return : java.lang.String
     * @author : pig
     * @date : 2020/6/8 17:38
     */
    private String generateApplyTitle(ProcessInfoDTO processInfo, String applyRealName, String deptName) {
        String dateNow = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        if (!StringUtils.isEmpty(processInfo.getApplyTitleRule())) {
            return processInfo.getApplyTitleRule().trim()
                    .replaceAll("\\$", "")
                    .replaceAll("\\{applyRealName\\}", applyRealName)
                    .replaceAll("\\{processName\\}", processInfo.getProcessName())
                    .replaceAll("\\{startDate\\}", dateNow)
                    .replaceAll("\\{deptName\\}", deptName);
        } else {
            return new StringBuffer()
                    .append(StringUtils.isBlank(deptName) ? "" : deptName)
                    .append(StringUtils.isBlank(applyRealName) ? "" : applyRealName)
                    .append("在")
                    .append(dateNow)
                    .append("发起")
                    .append(processInfo.getProcessName()).toString();
        }
    }
}
