package com.crcm.bpm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.domain.entity.ModelButtonDO;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.mapper.ModelButtonMapper;
import com.crcm.bpm.service.ModelButtonService;
import com.crcm.bpm.service.ModelService;
import com.crcm.core.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwj
 * @date 2020-11-02
 */

@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class ModelButtonServiceImpl extends ServiceImpl<ModelButtonMapper, ModelButtonDO> implements ModelButtonService {

    @Autowired
    private ModelService modelService;

    @Override
    public void saveModelButtonDO(String modelButtonDOList) {
        List<ModelButtonDO> buttonList = JSON.parseArray(modelButtonDOList, ModelButtonDO.class);

        deleteBatchByCondition(buttonList);

        // 新增流程按钮
        this.saveBatch(buttonList);
    }

    /**
     * 删除同一个模型、版本的节点按钮数据
     *
     * @param buttonList
     */
    private void deleteBatchByCondition(List<ModelButtonDO> buttonList) {
        // 模型未部署情况下，可以一直更新节点信息
        // 删除同一个模型、版本的节点按钮数据
        List<Long> ids = new ArrayList<>();
        buttonList.forEach(button -> {
            ModelButtonDO modelButtonDO = new ModelButtonDO();
            modelButtonDO.setProcessKey(button.getProcessKey());
            modelButtonDO.setNodeId(button.getNodeId());
            modelButtonDO.setModelVersion(button.getModelVersion());
            List<ModelButtonDO> list = findList(modelButtonDO);
            if (CollUtil.isNotEmpty(list)) {
                ids.add(list.get(0).getId());
            }
        });
        if(CollUtil.isNotEmpty(ids)){
            this.baseMapper.deleteBatchIds(ids);
        }
    }

    @Override
    public int updateModelButtonDO(ModelButtonDO modelButtonDO) {
        return this.baseMapper.updateById(modelButtonDO);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public ModelButtonDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<ModelButtonDO> findList(ModelButtonDO modelButtonDO) {
        return this.baseMapper.selectList(createConditionQuery(modelButtonDO));
    }

    @Override
    public List<ModelButtonDO> getButtonList(String nodeId, Long modelId) {
        // 根据modelId查询流程模型数据
        ModelDO modelDO = modelService.findModelById(modelId.toString());
        // 封装流程按钮查询条件
        ModelButtonDO modelButtonDO = new ModelButtonDO();
        modelButtonDO.setNodeId(nodeId);
        modelButtonDO.setProcessKey(modelDO.getProcessKey());
        modelButtonDO.setModelVersion(modelDO.getModelVersion());

        return findList(modelButtonDO);
    }

    private LambdaQueryWrapper<ModelButtonDO> createConditionQuery(ModelButtonDO modelButtonDO) {
        LambdaQueryWrapper<ModelButtonDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelButtonDO::getDeleted, SystemConstant.YES_OR_NO.NO);
        if (modelButtonDO != null) {
            if (StringUtils.isNotBlank(modelButtonDO.getProcessKey())) {
                wrapper.eq(ModelButtonDO::getProcessKey, modelButtonDO.getProcessKey());
            }
            if (modelButtonDO.getNodeId() != null) {
                wrapper.eq(ModelButtonDO::getNodeId, modelButtonDO.getNodeId());
            }
            if (modelButtonDO.getModelVersion() != null) {
                wrapper.eq(ModelButtonDO::getModelVersion, modelButtonDO.getModelVersion());
            }
            if (StringUtils.isNotBlank(modelButtonDO.getButtonValue())) {
                wrapper.eq(ModelButtonDO::getButtonValue, modelButtonDO.getButtonValue());
            }
        }
        return wrapper;
    }
}
