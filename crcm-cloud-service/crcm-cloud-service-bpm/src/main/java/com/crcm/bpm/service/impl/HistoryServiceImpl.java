package com.crcm.bpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.mapper.HistoryMapper;
import com.crcm.bpm.service.HistoryService;
import com.crcm.bpm.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, HistoryDO> implements HistoryService {

    @Autowired
    private UserTaskService userTaskService;


    @Override
    public int saveHistory(HistoryDO historyDO) {
        return this.baseMapper.insert(historyDO);
    }

    @Override
    public int updateHistory(HistoryDO historyDO) {
        return this.baseMapper.updateById(historyDO);
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
    public HistoryDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<HistoryDO> findList(HistoryDO historyDO) {
        QueryWrapper<HistoryDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<HistoryDO> findPage(Page page, HistoryDO historyDO) {
        QueryWrapper<HistoryDO> queryWrapper = new QueryWrapper<>();
        IPage<HistoryDO> pageHistory = this.baseMapper.selectPage(page, queryWrapper);
        return pageHistory;
    }

    @Override
    public int insertHistory(Long applyId, String tenantId, Long taskId, String taskName, String approverUserId, String approveRealName, String approveActionCode, String approveOpinion) {
        BestBpmAsset.isAssetEmpty(applyId);
//        BestBpmAsset.isAssetEmpty(tenantId);
        BestBpmAsset.isAssetEmpty(approverUserId);
        BestBpmAsset.isAssetEmpty(approveRealName);
        BestBpmAsset.isAssetEmpty(approveOpinion);

        HistoryDO historyDO = new HistoryDO().setApplyId(applyId).setTenantId(tenantId)
                .setTaskAssigneeUserId(String.valueOf(approverUserId)).setTaskAssigneeRealName(approveRealName).setApproveOpinion(approveOpinion);
//        if (taskId != null) {
//            historyDO.setActTaskId(taskId);
//        }
//        if (!StringUtils.isEmpty(taskName)) {
//            historyDO.setTaskName(taskName);
//        }
//        if (!StringUtils.isEmpty(approveActionCode)) {
//
//            historyDO.setApproveActionCode(approveActionCode);
//            // 这里需要放入MAP获取
//            historyDO.setApproveActionName(approveActionCode);
//        }
        int insert = this.baseMapper.insert(historyDO);
        return insert;
    }

    @Override
    public int insertHistory(HistoryDO historyDO) {
        return this.baseMapper.insert(historyDO);
    }

    @Override
    public HistoryDO getHistoryByTaskId(Long hisTaskId) {
        QueryWrapper<HistoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HistoryDO::getActTaskId, hisTaskId);
        List<HistoryDO> list = this.baseMapper.selectList(queryWrapper);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<HistoryDO> getCanReturnList(Long applyId) {
        return baseMapper.getCanReturnList(applyId);
    }

    @Override
    public HistoryDO getReturnMsg(Map<String, Object> map) {
        UserTaskDO userTaskDO = userTaskService.getById(Long.valueOf(map.get(TaskConstant.TASK_ID).toString()));
        LambdaQueryWrapper<HistoryDO> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(HistoryDO::getTaskNodeCode, map.get(TaskConstant.RETURN_NODE_ID));
        queryWrapper.eq(HistoryDO::getApplyId, userTaskDO.getApplyId());
        queryWrapper.orderByDesc(HistoryDO::getCreateTime);
        List<HistoryDO> historyDOList = baseMapper.selectList(queryWrapper);
        return historyDOList.get(0);
    }

}
