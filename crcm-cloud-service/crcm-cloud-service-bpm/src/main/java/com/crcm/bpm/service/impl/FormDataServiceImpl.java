package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.domain.dto.request.FormDataSaveOrUpdateDTO;
import com.crcm.bpm.domain.dto.response.FormDataTemplateDTO;
import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import com.crcm.bpm.domain.entity.ApproveOpinionDO;
import com.crcm.bpm.domain.entity.FormDataDO;
import com.crcm.bpm.mapper.FormDataMapper;
import com.crcm.bpm.service.*;
import com.crcm.file.api.feign.RemoteFileService;
import com.crcm.file.api.feign.dto.res.FileResDTO;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class FormDataServiceImpl extends ServiceImpl<FormDataMapper, FormDataDO> implements FormDataService {

    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;

    @Resource
    private RemoteFileService remoteFileService;

    @Autowired
    private ModelButtonService modelButtonService;

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private ApproveOpinionService approveOpinionService;

    @Autowired
    private HistoryFormDataService historyFormDataService;

    @Override
    public int saveFormData(FormDataDO formDataDO) {
        return this.baseMapper.insert(formDataDO);
    }

    @Override
    public int updateFormData(FormDataDO formDataDO) {
        return this.baseMapper.updateById(formDataDO);
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
    public FormDataDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<FormDataDO> findList(FormDataDO formDataDO) {
        QueryWrapper<FormDataDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<FormDataDO> findPage(Page page, FormDataDO formDataDO) {
        QueryWrapper<FormDataDO> queryWrapper = new QueryWrapper<>();
        IPage<FormDataDO> pageFormData = this.baseMapper.selectPage(page, queryWrapper);
        return pageFormData;
    }

    @Override
    public boolean batchSaveOrUpdateFormData(FormDataSaveOrUpdateDTO dto) {
        BestBpmAsset.isAssetEmpty(dto.getApplyId());
//        BestBpmAsset.isAssetEmpty(dto.getTenantId());
        boolean resut = true;

        if (dto.getDataMap() == null || dto.getDataMap().size() == 0) {
            log.error("DataMap为空");
            throw new BpmException(BpmError.DATA_NOT_FOUND_ERROR);
        }
        Map<String, Object> dataMap = dto.getDataMap();
        UserTaskDTO userTaskDTO = null;
        userTaskDTO = dto.getUserTaskDTO();
        if (userTaskDTO == null) {
            throw new BpmException(BpmError.USER_TASK_NO_FIND_ERROR);
        }
        // 保存formData
        saveFormData(dto);
        // 保存流程变量
        if (userTaskDTO != null) {
            processEngine.getTaskService().setVariablesLocal(userTaskDTO.getActTaskId(), dataMap);
        }
        return resut;
    }

    @Override
    public Boolean saveFormData(FormDataSaveOrUpdateDTO dto) {
        List<FormDataDO> formDataDOS = new ArrayList<>();
        LambdaQueryWrapper<FormDataDO> queryMapper = new QueryWrapper<FormDataDO>().lambda()
                .eq(FormDataDO::getApplyId, dto.getApplyId());
        formDataDOS = this.baseMapper.selectList(queryMapper);
        ConcurrentMap<String, FormDataDO> formDataMap = formDataDOS.stream().collect(Collectors.toConcurrentMap(FormDataDO::getDataKey, a -> a, (newVal, oldVal) -> newVal));
        String tenantId = dto.getTenantId();
        List<FormDataDO> saveFormDataList = new ArrayList<>();
        List<FormDataDO> updateFormDataList = new ArrayList<>();
        boolean updateFlag;
        Map<String, Object> dataMap = dto.getDataMap();
        // 后续可以新增权限保存
        FormDataTemplateDTO templateDTO = null;
        // 有一部分流程数据，不在FormData 也需要保存
        Map<String, Object> tempMap = new ConcurrentHashMap<>();
        FormDataDO templateDO = null;
        // 根据 dataMap 保存数据
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            if (tempMap.get(entry.getKey()) == null) {
                updateFlag = false;
                templateDO = new FormDataDO();

                if (formDataMap.get(entry.getKey()) != null) {
                    templateDO = BeanUtil.copyProperties(formDataMap.get(entry.getKey()), FormDataDO.class);
                    updateFlag = true;
                }

                switch (entry.getValue().getClass().getName()) {
                    case "java.lang.String":
//                        templateDO.setDataValue(StringEscapeUtils.unescapeJava(String.valueOf(entry.getValue())));
                        templateDO.setDataValue(String.valueOf(entry.getValue()));
                        break;
                    default:
                        templateDO.setDataValue(JSON.toJSONString(entry.getValue()));
                        break;
                }

                templateDO.setDataType(entry.getValue().getClass().getName());
                templateDO.setDataKey(entry.getKey());
                templateDO.setDataName(entry.getKey());
                templateDO.setTenantId(tenantId);
                templateDO.setApplyId(dto.getApplyId());
                templateDO.setProcInstId(dto.getProcInstId());
                templateDO.setProcessId(dto.getProcessId());
                if (updateFlag) {
                    updateFormDataList.add(templateDO);
                } else {
                    saveFormDataList.add(templateDO);
                }
            }
        }
        Boolean flag = true;
        if (saveFormDataList.size() > 0) {
            flag = this.saveBatch(saveFormDataList);
        }
        if (updateFormDataList.size() > 0) {
            flag = this.updateBatchById(updateFormDataList);
        }
        return flag;
    }


    @Override
    public Map<String, Object> getFormDataByApplyId(Long applyId, String nodeId, Long modelId) {
        BestBpmAsset.isAssetEmpty(applyId);
        LambdaQueryWrapper<FormDataDO> wrapper = new QueryWrapper<FormDataDO>().lambda().eq(FormDataDO::getApplyId, applyId);
        List<FormDataDO> formDataDOS = this.list(wrapper);
        if (formDataDOS == null || formDataDOS.size() == 0) {
            formDataDOS = historyFormDataService.getFormDataDOByApplyId(applyId);
        }
        List<FileResDTO> fileResultVoList = remoteFileService.findDetailListByBid(applyId.toString()).getData();
        /*List<FileDetailVo> fileDetailVos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(fileResultVoList)) {
            fileResultVoList.forEach(attachmentsEntity -> {
                FileDetailVo fileDetailVo = new FileDetailVo();
                BeanUtil.copyProperties(attachmentsEntity, fileDetailVo);
                fileDetailVos.add(fileDetailVo);
            });
        }*/
        Map<String, Object> map = listToMap(formDataDOS);
//        map.put("fileList", fileDetailVos);
        map.put("fileList", fileResultVoList);
        Boolean flag = (nodeId == null || "".equals(nodeId)) && modelId == null;
        // 是否历史数据
        if (!flag && !(Boolean) map.get("isOldData")) {
            map.put("buttonList", modelButtonService.getButtonList(nodeId, modelId));
        }
        // 历史意见
        List<ApproveOpinionDO> opinionDOList = approveOpinionService.getApproveOpinion(applyId);
        map.put("approveOpinionList", opinionDOList);
        return map;
    }

    @Override
    public Map<String, Object> getFormDataByProcInstId(String procInstId) {
        BestBpmAsset.isAssetEmpty(procInstId);
        LambdaQueryWrapper<FormDataDO> wrapper = new QueryWrapper<FormDataDO>().lambda().eq(FormDataDO::getProcInstId, procInstId);
        List<FormDataDO> formDataDOS = this.list(wrapper);
        if (formDataDOS == null) {
            formDataDOS = new ArrayList<>();
        }

        return listToMap(formDataDOS);
    }

    @Override
    public int deleteProcessData(String procInstId) {
        LambdaQueryWrapper<FormDataDO> queryWrapper = new QueryWrapper<FormDataDO>().lambda().eq(FormDataDO::getProcInstId, procInstId);
        return this.baseMapper.delete(queryWrapper);
    }


    private Map<String, Object> listToMap(List<FormDataDO> formDataDOS) {

        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        Object dataValue = null;
        resultMap.put("isOldData", false);
        for (FormDataDO formDataDO : formDataDOS) {
            dataValue = null;
            switch (formDataDO.getDataType()) {
                case "java.lang.String":
                    dataValue = formDataDO.getDataValue();
                    break;
                case "html":
                    dataValue = formDataDO.getDataValue();
                    // 历史数据才会包含html格式的数据
                    resultMap.put("isOldData", true);
                    break;
                default:
                    dataValue = JSON.parse(formDataDO.getDataValue());
                    break;
            }
            if (dataValue == null) {
                dataValue = "";
            }
            resultMap.put(formDataDO.getDataKey(), dataValue);
        }

        return resultMap;
    }

}
