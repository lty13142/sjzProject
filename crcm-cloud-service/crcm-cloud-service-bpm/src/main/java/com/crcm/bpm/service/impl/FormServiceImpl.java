package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.baomidou.mybatisplus.core.metadata.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;
import com.baomidou.mybatisplus.extension.service.impl.*;
import com.crcm.bpm.domain.dto.response.*;
import com.crcm.bpm.domain.entity.*;
import com.crcm.bpm.mapper.*;
import com.crcm.bpm.service.*;
import com.crcm.core.exception.CustomException;
import com.crcm.security.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.*;
import java.util.*;

/**
 * 流程单Service业务层处理
 *
 * @author zzyt
 * @date 2020-08-26
 */
@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, FormDO> implements FormService {

    @Autowired
    private ModelService modelService;


    @Resource
    private ModelMapper modelMapper;

    /**
     * 新增流程单
     *
     * @param formDo 流程单
     * @return 结果
     */
    @Override
    public int saveFlowForm(FormDO formDo) {
        return this.baseMapper.insert(formDo);
    }

    /**
     * 修改流程单
     *
     * @param formDo 流程单
     * @return 结果
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int updateFlowForm(FormDO formDo) {
        FormDO old = this.baseMapper.selectById(formDo.getId());
        int len = this.baseMapper.updateById(formDo);
        if (len > 0) {
            // 判断是否修改了表单名
            if (!StringUtils.isEmpty(formDo.getName()) && !old.getName().equals(formDo.getName())) {
                // 表单名修改了，判断该表单是否被模型使用，若被使用则修改模型中表单名称
                LambdaQueryWrapper<ModelDO> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ModelDO::getFormId, formDo.getId());
                List<ModelDO> list = modelService.list(wrapper);
                list.forEach(modelDO -> {
                    modelDO.setFormName(formDo.getName());
                });
                modelService.updateBatchById(list);
            }
        }
        return len;
    }

    /**
     * 删除流程单信息
     *
     * @param id 流程单ID
     * @return 结果
     */
    @Override
    public int deleteFlowFormById(String id) {
        //查询表单在模型中的个数
        QueryWrapper<ModelDO> wrapper = new QueryWrapper<>();
        wrapper.eq("form_id", id).eq("deleted", 0).eq("enabled", 1);
        List<ModelDO> modelDOS = modelMapper.selectList(wrapper);
        if (CollUtil.isNotEmpty(modelDOS))
        return this.baseMapper.deleteById(id);{
            throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, "该表单不能删除<原因:表单已经被'" + modelDOS.get(0).getModelName() + "'流程使用。>");
        }
    }

    /**
     * 查询流程单
     *
     * @param id 流程单ID
     * @return 流程单
     */
    @Override
    public FormDO findFlowFormById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询流程单列表
     *
     * @param formDo 流程单
     * @return 流程单
     */
    @Override
    public List<FormDO> findFlowFormList(FormDO formDo) {
        LambdaQueryWrapper<FormDO> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询流程单
     *
     * @param page   分页参数
     * @param formDo 流程单
     * @return 流程单
     */
    @Override
    public IPage<FormDO> findFlowFormPage(Page page, FormDO formDo) {
        LambdaQueryWrapper<FormDO> queryWrapper = getQueryWrapper(formDo);
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<FormDO> getQueryWrapper(FormDO formDo) {
        LambdaQueryWrapper<FormDO> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(formDo.getName())) {
            queryWrapper.like(FormDO::getName, formDo.getName());
        }
        if (StringUtils.isNotBlank(formDo.getFormKey())) {
            queryWrapper.like(FormDO::getFormKey, formDo.getFormKey());
        }
        if (formDo.getFormType() != null) {
            queryWrapper.eq(FormDO::getFormType, formDo.getFormType());
        }
        if (StringUtils.isNotBlank(formDo.getCompanyId())) {
            queryWrapper.eq(FormDO::getCompanyId, formDo.getCompanyId());
        } else {
            queryWrapper.eq(FormDO::getCompanyId, Objects.requireNonNull(SecurityUtil.getCurrentUser()).getComId());
        }
        queryWrapper.orderByDesc(FormDO::getCreateTime);
        return queryWrapper;
    }

    @Override
    public List<FormDO> findByFormKey(String formKey) {
        LambdaQueryWrapper<FormDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormDO::getFormKey, formKey);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public FormDTO getFormByFormKey(String formKey) {
        LambdaQueryWrapper<FormDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormDO::getFormKey, formKey);
        FormDO formDO = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(formDO, FormDTO.class);
    }

    @Override
    public IPage<FormDO> getPageUnUse(Page page, FormDO formDo) {
        String companyId = StringUtils.isBlank(formDo.getCompanyId()) ? Objects.requireNonNull(SecurityUtil.getCurrentUser()).getComId() : formDo.getCompanyId();
        List<String> list = modelService.selectFormIdList(companyId);
        LambdaQueryWrapper<FormDO> queryWrapper = getQueryWrapper(formDo);
        queryWrapper.notIn(list.size() != 0, FormDO::getId, list);
        queryWrapper.orderByDesc(FormDO::getCreateTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public FormDO copyForm(FormDO formDo) {
        FormDO oldForm = baseMapper.selectById(formDo.getId());
        formDo.setId(null);
        formDo.setFormData(oldForm.getFormData());
        formDo.setJsonData(oldForm.getJsonData());
        formDo.setHtml(oldForm.getHtml());
        formDo.setFields(oldForm.getFields());
        formDo.setFieldSize(oldForm.getFieldSize());
        formDo.setCreateBy(Objects.requireNonNull(SecurityUtil.getCurrentUsername()));
        baseMapper.insert(formDo);
        return formDo;
    }
}
