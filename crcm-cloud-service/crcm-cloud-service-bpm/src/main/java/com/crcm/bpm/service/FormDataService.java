package com.crcm.bpm.service;

import com.crcm.bpm.domain.dto.request.FormDataSaveOrUpdateDTO;
import com.crcm.bpm.domain.entity.FormDataDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public interface FormDataService extends IService<FormDataDO> {

    int saveFormData(FormDataDO formDataDO);

    int updateFormData(FormDataDO formDataDO);

    int deleteById(String id);

    int realDelete(String id);

    FormDataDO findById(String id);

    List<FormDataDO> findList(FormDataDO formDataDO);

    IPage<FormDataDO> findPage(Page page, FormDataDO formDataDO);

    boolean batchSaveOrUpdateFormData(FormDataSaveOrUpdateDTO formDataSaveOrUpdateDTO);

    /**
     * 保存formData
     *
     * @param dto
     * @return
     */
    Boolean saveFormData(FormDataSaveOrUpdateDTO dto);

    /**
     * 根据申请编号获取表单数据
     * @param applyId
     * @param nodeId
     * @param modelId
     * @return
     */
    Map<String, Object> getFormDataByApplyId(Long applyId,String nodeId,Long modelId);

    /**
     * 根据流程实例编号获取表单数据
     * @param procInstId
     * @return
     */
    Map<String, Object> getFormDataByProcInstId(String procInstId);

    /**
     * 删除流程数据
     * @param procInstId 流程实例ID
     * @return
     */
    int deleteProcessData(String procInstId);
}
