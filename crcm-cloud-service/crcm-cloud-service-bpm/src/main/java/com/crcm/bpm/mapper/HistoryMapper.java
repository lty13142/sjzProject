package com.crcm.bpm.mapper;

import com.crcm.bpm.domain.entity.HistoryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface HistoryMapper extends BaseMapper<HistoryDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    int updateCustomIfNullById(@Param("History") HistoryDO historyDO);

    /**
     * 获取可以退回的列表
     *
     * @param applyId
     * @return
     */
    List<HistoryDO> getCanReturnList(@Param("applyId") Long applyId);
}
