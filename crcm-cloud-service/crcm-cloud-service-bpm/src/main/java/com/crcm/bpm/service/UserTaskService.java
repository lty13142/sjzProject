package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.api.dto.request.TaskHangDto;
import com.crcm.bpm.api.vo.GetApproveOpinionListVO;
import com.crcm.bpm.domain.dto.NodeAssigneesDTO;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.request.*;
import com.crcm.bpm.domain.dto.response.*;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.domain.vo.NodeQueryVO;

import java.util.List;
import java.util.Map;

public interface UserTaskService extends IService<UserTaskDO> {

    int saveUserTask(UserTaskDO userTaskDO);

    int updateUserTask(UserTaskDO userTaskDO);

    int deleteById(Long id);

    int realDelete(String id);

    UserTaskDO findById(String id);

    List<UserTaskDO> findList(UserTaskDO userTaskDO);

    IPage<UserTaskDO> findPage(Page page, UserTaskDO userTaskDO);

    List<UserTaskDTO> getUserTaskByProcInstId(String procInstId);

    UserTaskDTO getUserTaskByTaskId(Long taskId);

    /**
     * 获取申请列表
     *
     * @param page
     * @param userTaskInfoQueryDTO
     * @return
     */
    IPage<UserTaskInfoDTO> getApplyListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQueryDTO);

    /**
     * 获取待办列表
     *
     * @param userTaskInfoQueryDTO
     * @return
     */
    IPage<UserTaskInfoDTO> getToDoListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQueryDTO);

    /**
     * 获取草稿列表
     *
     * @param page
     * @param userTaskInfoQueryDTO
     * @return
     */
    IPage<UserTaskInfoDTO> getDraftListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQueryDTO);

    /**
     * 获取已办列表
     *
     * @param page
     * @param userTaskInfoQueryDTO
     * @return
     */
    IPage<UserTaskInfoDTO> getHaveDoListByCondition(Page page, UserTaskInfoQueryDTO userTaskInfoQueryDTO);

    /**
     * 完成任务
     *
     * @param processCirculationDto
     * @return
     */
    boolean completeTask(ProcessCirculationDto processCirculationDto);

    /**
     * 通过申请ID查询用户任务节点
     *
     * @param applyId
     * @return
     */
    List<UserTaskDO> getUserTaskByApplyId(Long applyId);

    /**
     * 签收任务
     *
     * @param claimTaskDTO
     * @return
     */
    boolean claimTask(ClaimTaskDTO claimTaskDTO);

    /**
     * 任务反签收
     * @param taskId
     */
    boolean unClaimTask(String taskId);

    /**
     * 获取可以退回的节点
     *
     * @param taskId
     * @return
     */
    List<ReturnNodeDTO> getReturnNode(Long taskId);

    /**
     * 获取下一节点信息
     * @param nodeQueryVO
     * @return
     */
    FlowUserTaskDTO getNextNodeInfo(NodeQueryVO nodeQueryVO);

    /**
     * 获取下一节点审核人信息
     * @param nodeQueryVO
     * @return
     */
    List<NodeAssigneesDTO> getNextNodeAssignees(NodeQueryVO nodeQueryVO);



    /**
     * 获取意见流转
     *
     * @param applyId
     * @return
     */
    List<GetApproveOpinionListVO> getApproveOpinionList(String applyId);

    /**
     * 删除用户任务同时保存历史记录
     *
     * @param userTaskDTO
     * @param processMap
     * @return
     */
    boolean removeAndSaverHistory(UserTaskDTO userTaskDTO, Map<String, Object> processMap);

    /**
     * 清除多实例的用户任务
     *
     * @param userTaskDTO
     */
    void clearMultiInstanceTasks(UserTaskDTO userTaskDTO);
    
    /** 
    * @Description: getToDoNumber
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/2/25 9:55
    */ 
    Integer getToDoNumber();
}
