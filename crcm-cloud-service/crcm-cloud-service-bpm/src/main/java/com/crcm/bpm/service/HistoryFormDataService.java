package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.dto.request.HistoryFormDataSaveDTO;
import com.crcm.bpm.domain.entity.FormDataDO;
import com.crcm.bpm.domain.entity.HistoryFormDataDO;

import java.util.List;

public interface HistoryFormDataService extends IService<HistoryFormDataDO> {

    int saveHistoryFormData(HistoryFormDataDO historyFormDataDO);

    int updateHistoryFormData(HistoryFormDataDO historyFormDataDO);

    int deleteById(String id);

    int realDelete(String id);

    HistoryFormDataDO findById(String id);

    List<HistoryFormDataDO> findList(HistoryFormDataDO historyFormDataDO);

    IPage<HistoryFormDataDO> findPage(Page page, HistoryFormDataDO historyFormDataDO);

    boolean saveOrUpdateFormData(HistoryFormDataSaveDTO historyFormDataSaveDTO);

    /**
     * 根据applyId获取信息
     *
     * @param applyId
     * @return List<FormDataDO>
     */
    List<FormDataDO> getFormDataDOByApplyId(Long applyId);

}
