package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.CustomFormUtil;
import com.crcm.bpm.domain.dto.request.HistoryFormDataSaveDTO;
import com.crcm.bpm.domain.entity.*;
import com.crcm.bpm.mapper.HistoryFormDataMapper;
import com.crcm.bpm.service.ApplyService;
import com.crcm.bpm.service.HistoryFormDataService;
import com.crcm.bpm.service.ModelService;
import com.crcm.bpm.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class HistoryFormDataServiceImpl extends ServiceImpl<HistoryFormDataMapper, HistoryFormDataDO> implements HistoryFormDataService {

    @Resource
    private ApplyService applyService;
    @Resource
    private ModelService modelService;
    @Resource
    private NodeService nodeService;

    @Override
    public int saveHistoryFormData(HistoryFormDataDO historyFormDataDO) {
        return this.baseMapper.insert(historyFormDataDO);
    }

    @Override
    public int updateHistoryFormData(HistoryFormDataDO historyFormDataDO) {
        return this.baseMapper.updateById(historyFormDataDO);
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
    public HistoryFormDataDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<HistoryFormDataDO> findList(HistoryFormDataDO historyFormDataDO) {
        QueryWrapper<HistoryFormDataDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<HistoryFormDataDO> findPage(Page page, HistoryFormDataDO historyFormDataDO) {
        QueryWrapper<HistoryFormDataDO> queryWrapper = new QueryWrapper<>();
        IPage<HistoryFormDataDO> pageHistoryFormData = this.baseMapper.selectPage(page, queryWrapper);
        return pageHistoryFormData;
    }

    @Override
    public boolean saveOrUpdateFormData(HistoryFormDataSaveDTO dto) {
        boolean resut = true;
        if (dto.getProcessMap() == null || dto.getProcessMap().size() == 0) {
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }

        List<HistoryFormDataDO> formDataDOS = new ArrayList<>();
        List<HistoryFormDataDO> saveFormDataList = new ArrayList<>();
        List<HistoryFormDataDO> updateFormDataList = new ArrayList<>();
        Map<String, Object> dataMap = dto.getProcessMap();
        String tenantId = dto.getTenantId();
        Long applyId = dto.getApplyId();
        Long formId = dto.getFormId();
        LambdaQueryWrapper<HistoryFormDataDO> queryMapper = new QueryWrapper<HistoryFormDataDO>().lambda()
                .eq(HistoryFormDataDO::getApplyId, dto.getApplyId());
        formDataDOS = this.baseMapper.selectList(queryMapper);
        ConcurrentMap<String, HistoryFormDataDO> formDataMap = formDataDOS.stream()
                .collect(Collectors.toConcurrentMap(HistoryFormDataDO::getDataKey, a -> a, (newVal, oldVal) -> newVal));
        boolean updateFlag;
        HistoryFormDataDO templateDO = null;
        // 根据 dataMap 保存数据
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            updateFlag = false;
            templateDO = new HistoryFormDataDO();

            if (formDataMap.get(entry.getKey()) != null) {
                templateDO = BeanUtil.copyProperties(formDataMap.get(entry.getKey()), HistoryFormDataDO.class);
                updateFlag = true;
            }

            if (entry.getValue() == null) {
                continue;
            }
            switch (entry.getValue().getClass().getName()) {
                case "java.lang.String":
                    templateDO.setDataValue(entry.getValue().toString());
                    break;
                default:
                    templateDO.setDataValue(JSON.toJSONString(entry.getValue()));
                    break;
            }
            if ("formData".equals(entry.getKey()) || "formJson".equals(entry.getKey())) {
                templateDO.setFormId(dto.getFormId());
            }
            templateDO.setDataType(entry.getValue().getClass().getName());
            templateDO.setDataKey(entry.getKey());
            templateDO.setDataName(entry.getKey());
            templateDO.setTenantId(tenantId);
            templateDO.setApplyId(applyId);
            templateDO.setFormId(formId);
            templateDO.setProcInstId(dto.getProcInstId());
            templateDO.setProcessId(dto.getProcessId());
            templateDO.setTaskNodeId(dto.getTaskNodeId());
            if (updateFlag) {
                updateFormDataList.add(templateDO);
            } else {
                saveFormDataList.add(templateDO);
            }
        }

        if (saveFormDataList.size() > 0) {
            resut = this.saveBatch(saveFormDataList);
        }
        if (updateFormDataList.size() > 0) {
            resut = this.updateBatchById(updateFormDataList);
        }

        updateCustomForm(dto.getApplyId(), dataMap, dto.getTaskNodeId());

        return resut;
    }

    private void updateCustomForm(Long applyId, Map<String, Object> dataMap, String nodeId) {
        ApplyDO applyDO = applyService.getById(applyId);
        ModelDO modelDO = modelService.getById(applyDO.getModelId());
        NodeDO nodeDO = nodeService.getOne(new LambdaQueryWrapper<NodeDO>().eq(NodeDO::getModelId, modelDO.getId()).eq(NodeDO::getNodeId, nodeId));
        if (StringUtils.isNotEmpty(modelDO.getFormCode())) {
            Boolean flag = CustomFormUtil.saveCustomForm(modelDO.getFormCode(), applyId, JSONObject.toJSONString(dataMap.get("formData")), nodeDO.getFormEditField());
            if (!flag) {
                throw new BpmException(BpmError.SYSTEM_ERROR);
            }
        }
    }

    @Override
    public List<FormDataDO> getFormDataDOByApplyId(Long applyId) {
        return baseMapper.getFormDataDOByApplyId(applyId);
    }
}
