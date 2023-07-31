package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.crcm.bpm.domain.dto.response.FormDTO;
import com.crcm.bpm.domain.entity.FormDO;

/**
 * 流程单Service接口
 *
 * @author zzyt
 * @date 2020-08-26
 */
public interface FormService extends IService<FormDO>{

    /**
     * 新增流程单
     *
     * @param formDo 流程单
     * @return 结果
     */
    int saveFlowForm(FormDO formDo);

    /**
     * 修改流程单
     *
     * @param formDo 流程单
     * @return 结果
     */
    int updateFlowForm(FormDO formDo);

    /**
     * 删除流程单信息
     *
     * @param id 流程单ID
     * @return 结果
     */
    int deleteFlowFormById(String id);

    /**
     * 查询流程单
     *
     * @param id 流程单ID
     * @return 流程单
     */
    FormDO findFlowFormById(String id);

    /**
     * 查询流程单列表
     *
     * @param formDo 流程单
     * @return 流程单集合
     */
    List<FormDO> findFlowFormList(FormDO formDo);

    /**
     * 查询流程单列表
     *
     * @param formDo 流程单
     * @return 流程单集合
     */
    IPage<FormDO> findFlowFormPage(Page page, FormDO formDo);

    List<FormDO> findByFormKey(String formKey);

    FormDTO getFormByFormKey(String formKey);

    /**
     * 分页查询未使用的本公司流程单
     *
     * @param page
     * @param formDo
     * @return
     */
    IPage<FormDO> getPageUnUse(Page page, FormDO formDo);

    /**
     * 复制表单
     *
     * @param formDo
     * @return
     */
    FormDO copyForm(FormDO formDo);
}
