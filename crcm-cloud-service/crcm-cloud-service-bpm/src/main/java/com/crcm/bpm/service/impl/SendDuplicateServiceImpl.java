package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.constant.enums.SysMessageStatusEnum;
import com.crcm.bpm.core.constant.enums.SysMessageTypeEnum;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.SendTaskMessageUtil;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.response.SendDuplicateDTO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.domain.vo.SendDuplicateVo;
import com.crcm.bpm.mapper.HistoryMapper;
import com.crcm.bpm.mapper.SendDuplicateMapper;
import com.crcm.bpm.mapper.UserTaskMapper;
import com.crcm.bpm.service.SendDuplicateService;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendDuplicateServiceImpl extends ServiceImpl<SendDuplicateMapper, SendDuplicateVo> implements SendDuplicateService {

    @Resource
    private UserTaskMapper userTaskMapper;
    @Resource
    private HistoryMapper historyMapper;

    @Override
    public Boolean saveSendDuplicate(ProcessCirculationDto processCirculationDto) {
        if (processCirculationDto.getActTaskId() == null) {
            throw new BpmException(BpmError.ILLEGAL_ARGUMENT_ERROR);
        }
        List<String> assigneeList = processCirculationDto.getAssigneeList();
        if (assigneeList == null || assigneeList.size() == 0) {
            throw new BpmException(BpmError.USER_TASK_NO_FIND_COPY);
        }

        LambdaQueryWrapper<UserTaskDO> wrapper = new LambdaQueryWrapper();
        wrapper.eq(UserTaskDO::getActTaskId, processCirculationDto.getActTaskId());
        List<UserTaskDO> taskList = userTaskMapper.selectList(wrapper);
        UserTaskDO userTask = new UserTaskDO();
        if (taskList != null && taskList.size() > 0) {
            userTask = taskList.get(0);
        } else {
            // 任务已结束从历史表查询
            LambdaQueryWrapper<HistoryDO> historyWrapper = new LambdaQueryWrapper<>();
            historyWrapper.eq(HistoryDO::getActTaskId, processCirculationDto.getActTaskId());
            List<HistoryDO> historyDOList = historyMapper.selectList(historyWrapper);
            if (historyDOList != null && historyDOList.size() > 0) {
                BeanUtil.copyProperties(historyDOList.get(0), userTask);
            }
        }
//        UserTaskDO userTask = userTaskMapper.selectById(processCirculationDto.getTaskId());

        // 根据申请id查询抄送记录
        List<String> userIds = this.baseMapper.selectCopyToBYApplyId(userTask.getApplyId());

        List<SendDuplicateVo> saveList = new ArrayList<>();
        for (int i = 0; i < assigneeList.size(); i++) {
            if (userIds.indexOf(assigneeList.get(i)) < 0) {
                SendDuplicateVo sendDuplicateVo = new SendDuplicateVo();
                sendDuplicateVo.setApplyId(userTask.getApplyId());
                sendDuplicateVo.setProcessId(userTask.getProcessId());
                sendDuplicateVo.setProcInstId(userTask.getProcInstId());
                sendDuplicateVo.setTaskId(userTask.getId());
                sendDuplicateVo.setTaskNodeCode(userTask.getTaskNodeCode());
                sendDuplicateVo.setCreateUserId(SecurityUtil.getCurrentUserNoNull().getUserId());
                sendDuplicateVo.setTaskAssigneeUserId(assigneeList.get(i));
                sendDuplicateVo.setTaskAssigneeRealName(processCirculationDto.getAssigneeNameList().get(i));
                sendDuplicateVo.setFormKey(userTask.getFormKey());
                sendDuplicateVo.setModelId(userTask.getModelId());
                sendDuplicateVo.setFormEditField(userTask.getFormEditField());
                saveList.add(sendDuplicateVo);
            }
        }

        if (saveList.size() > 0) {
            // 保存抄送数据
            Boolean flag = this.saveBatch(saveList);

            if (flag) {
                // 推送消息给接收抄送人
                SendTaskMessageUtil.sendCopy(saveList, "您有新的抄送待查看", SysMessageTypeEnum.WORK_TO_READ.getValue(), SysMessageStatusEnum.UNREAD.getValue(), 0, false, false);
            }

            return flag;
        }

        return true;
    }

    @Override
    public Boolean setCopyReaded(SendDuplicateVo sendDuplicate) {
        Boolean flag = false;
        sendDuplicate = this.baseMapper.selectById(sendDuplicate.getId());
        if (sendDuplicate.getDeleted() == 0 && sendDuplicate.getTaskStatus() == TaskConstant.COPY_STATUS_NOT_ERAD) {
            sendDuplicate.setTaskStatus(TaskConstant.COPY_STATUS_HAS_READ);
            sendDuplicate.setUpdateTime(LocalDateTime.now());
            flag = this.updateById(sendDuplicate);
            if (flag) {
                List<SendDuplicateVo> list = new ArrayList<>();
                list.add(sendDuplicate);
                SendTaskMessageUtil.sendCopy(list, null, SysMessageTypeEnum.WORK_TO_DO.getValue(), SysMessageStatusEnum.READ.getValue(), 1, true, false);
            }
        }
        return flag;
    }

    @Override
    public IPage<SendDuplicateDTO> getPage(Page page, SendDuplicateVo sendDuplicate) {
        // 当前登录人信息
        UserAccount userDetails = SecurityUtil.getCurrentUserNoNull();
        sendDuplicate.setTaskAssigneeUserId(userDetails.getUserId());
        IPage<SendDuplicateDTO> pageData = this.baseMapper.selectSendDuplicatePage(page, sendDuplicate);
        return pageData;
    }

    @Override
    public IPage<SendDuplicateDTO> sendPage(Page page, SendDuplicateVo sendDuplicate) {
        // 当前登录人信息
        UserAccount userDetails = SecurityUtil.getCurrentUserNoNull();
        sendDuplicate.setTaskAssigneeUserId(userDetails.getUserId());
        IPage<SendDuplicateDTO> pageData = this.baseMapper.selectSendDuplicatePage(page, sendDuplicate);
        return pageData;
    }

    @Override
    public IPage<SendDuplicateDTO> getAllPage(Page page, SendDuplicateVo sendDuplicate) {
        IPage<SendDuplicateDTO> pageData = this.baseMapper.selectSendDuplicatePage(page, sendDuplicate);
        return pageData;
    }

    @Override
    public int deleteById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
