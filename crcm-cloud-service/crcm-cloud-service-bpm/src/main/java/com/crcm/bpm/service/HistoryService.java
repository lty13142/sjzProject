package com.crcm.bpm.service;

import com.crcm.bpm.domain.entity.HistoryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public interface HistoryService extends IService<HistoryDO> {

    int saveHistory(HistoryDO historyDO);

    int updateHistory(HistoryDO historyDO);

    int deleteById(String id);

    int realDelete(String id);

    HistoryDO findById(String id);

    List<HistoryDO> findList(HistoryDO historyDO);

    IPage<HistoryDO> findPage(Page page, HistoryDO historyDO);

    int insertHistory(Long applyId, String tenantId, Long taskId, String taskName, String approverUserId, String approveRealName, String approveActionCode, String approveOpinion);

    int insertHistory(HistoryDO historyDO);

    HistoryDO getHistoryByTaskId(Long hisTaskId);

    /**
     * 获取可以退回的列表
     *
     * @param applyId
     * @return
     */
    List<HistoryDO> getCanReturnList(Long applyId);

    /**
     * 获取退回节点历史数据
     *
     * @param map
     * @return
     */
    HistoryDO getReturnMsg(Map<String, Object> map);
}
