package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.bpm.domain.entity.FormDataDO;
import com.crcm.bpm.domain.entity.HistoryFormDataDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface HistoryFormDataMapper extends BaseMapper<HistoryFormDataDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 根据applyId获取信息
     *
     * @param applyId
     * @return List<FormDataDO>
     */
    List<FormDataDO> getFormDataDOByApplyId(@Param("applyId") Long applyId);
}
