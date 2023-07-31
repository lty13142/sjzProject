package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.core.utils.BpmnUtil;
import com.crcm.bpm.core.utils.FlowElementUtils;
import com.crcm.bpm.domain.dto.response.ModelDTO;
import com.crcm.bpm.domain.dto.response.ModelInfoDTO;
import com.crcm.bpm.domain.entity.ModelButtonDO;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.domain.entity.NodeDO;
import com.crcm.bpm.domain.entity.ProcessDO;
import com.crcm.bpm.domain.vo.ModelDeployVo;
import com.crcm.bpm.domain.vo.ProcessSaveVo;
import com.crcm.bpm.mapper.ModelMapper;
import com.crcm.bpm.service.ModelButtonService;
import com.crcm.bpm.service.ModelService;
import com.crcm.bpm.service.NodeService;
import com.crcm.bpm.service.ProcessService;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.SpringContextHolder;
import com.crcm.security.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 流程模型图Service业务层处理
 *
 * @author zzyt
 * @date 2020-08-18
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, ModelDO> implements ModelService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private ProcessService processService;


    /**
     * 新增流程模型图
     *
     * @param modelDo 流程模型图
     * @return 结果
     */
    @Override
    public int saveModel(ModelDO modelDo) {
        modelDo.setModelVersion(1);
        // 设置主版本
        modelDo.setMainVersion(BpmConstant.MAIN_VERSION);
        if (StringUtils.isBlank(modelDo.getTenantId())) {
            modelDo.setTenantId(SecurityUtil.getCurrentUser().getTenantId());
        }
        if (modelDo.getEnabled() == null) {
            modelDo.setEnabled(SystemConstant.ENABLE_STATUS.UN_ENABLE);
        }
        if (modelDo.getStatus() == null) {
            modelDo.setStatus(BpmConstant.PROCESS_NOT_DEPLOY);
        }
        // 避免模型流程KEY重复
        List<ModelDO> models = this.findByProcessKey(modelDo.getProcessKey());
        if (models != null && models.size() > 0) {
            throw new BpmException(BpmError.PROCESS_KEY_ALREADY_DEFINED);
        }
        modelDo.setCreateBy(SecurityUtil.getCurrentUsername());
        modelDo.setUpdateBy(SecurityUtil.getCurrentUsername());
        checkCanUse(modelDo);
        return this.baseMapper.insert(modelDo);
    }

    private void checkCanUse(ModelDO modelDo) {
        LambdaQueryWrapper<ModelDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ModelDO::getFormType, modelDo.getFormType());
        queryWrapper.eq(ModelDO::getFormId, modelDo.getFormId());
        queryWrapper.ne(ModelDO::getProcessKey, modelDo.getProcessKey());
        List list = baseMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            throw new CustomException(HttpStatus.SC_PRECONDITION_FAILED, "不可使用重复编码");
        }
    }

    @Override
    public int createNewVersion(ModelDO modelDo) {
        // 创建一个新的模型
        Integer max = baseMapper.getMaxVersion(modelDo.getProcessKey());
        max = max == null ? 0 : max;
        ModelDO newVersion = ModelDO.builder()
                .modelName(modelDo.getModelName())
                .autoCompleteFirstNode(modelDo.getAutoCompleteFirstNode())
                .companyId(modelDo.getCompanyId())
                .companyName(modelDo.getCompanyName())
                .enabled(modelDo.getEnabled())
                .flowType(modelDo.getFlowType())
                .formType(modelDo.getFormType())
                .formId(modelDo.getFormId())
                .formName(modelDo.getFormName())
                .formCode(modelDo.getFormCode())
                .mainVersion(BpmConstant.NOT_MAIN_VERSION)
                .status(BpmConstant.PROCESS_NOT_DEPLOY)
                .processKey(modelDo.getProcessKey())
                .remark(modelDo.getRemark())
                .tenantId(modelDo.getTenantId())
                .processSvg(modelDo.getProcessSvg())
                .processXml(modelDo.getProcessXml())
                .modelVersion(max + 1).build();
        checkCanUse(modelDo);

        //zwj新增保存流程节点按钮方法
        saveButton(max, modelDo.getProcessKey());

        return this.baseMapper.insert(newVersion);
    }

    @Autowired
    private ModelButtonService modelButtonService;

    /**
     * 保存流程节点按钮
     *
     * @param max
     * @param processKey
     */
    private void saveButton(Integer max, String processKey) {
        // 查询当前版本的流程按钮数据
        ModelButtonDO modelButtonDO = new ModelButtonDO();
        modelButtonDO.setProcessKey(processKey);
        modelButtonDO.setModelVersion(max);
        List<ModelButtonDO> modelButtonDOList = modelButtonService.findList(modelButtonDO);

        // 节点批量新增流程按钮(当前模型版本的按钮+1)
        if (CollUtil.isNotEmpty(modelButtonDOList)) {
            modelButtonDOList.forEach(m -> {
                m.setId(null);
                m.setModelVersion(m.getModelVersion() + 1);
                m.setCreateBy(SecurityUtil.getCurrentUsername());
                m.setCreateTime(LocalDateTime.now());
            });
            modelButtonService.saveBatch(modelButtonDOList);
        }
    }
    /**
     * 删除流程模型图信息
     *
     * @param id 流程模型图ID
     * @return 结果
     */
    @Override
    public int deleteModelById(Long id) {
        //查询是否住版本
        ModelDO modelDO = this.baseMapper.selectById(id);
        if (modelDO.getMainVersion()==BpmConstant.MAIN_VERSION){
            //查询业务流程是否在使用，没有使用直接删除
            List<ProcessDO> processDOS = processService.selectByProcesskey(modelDO.getProcessKey());
            if (CollUtil.isNotEmpty(processDOS)){
                throw new BpmException(BpmError.PROCESS_DELETE);
            }
            //删除所有的流程模型
            return this.baseMapper.delete(new LambdaQueryWrapper<ModelDO>(){{eq(ModelDO::getProcessKey,modelDO.getProcessKey());}});
        }
        return this.baseMapper.deleteById(id);
    }


    /**
     * 修改流程模型图
     *
     * @param modelDo 流程模型图
     * @return 结果
     */
    @Override
    public int updateModel(ModelDO modelDo) {
        modelDo.setUpdateBy(SecurityUtil.getCurrentUsername());
        checkCanUse(modelDo);
        return this.baseMapper.updateById(modelDo);
    }

    /**
     * 查询流程模型图
     *
     * @param id 流程模型图ID
     * @return 流程模型图
     */
    @Override
    public ModelDO findModelById(String id) {
        return this.baseMapper.findModelById(id);
    }

    /**
     * 查询流程模型图列表
     *
     * @param modelDo 流程模型图
     * @return 流程模型图
     */
    @Override
    public List<ModelDO> findModelList(ModelDO modelDo) {
        LambdaQueryWrapper<ModelDO> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询流程模型图
     *
     * @param page    分页参数
     * @param modelDo 流程模型图
     * @return 流程模型图
     */
    @Override
    public IPage<ModelDTO> findModelPage(Page page, ModelDO modelDo) {
        return this.baseMapper.selectPageModel(page, modelDo);
    }


    private LambdaQueryWrapper<ModelDO> getQueryWrapper(ModelDO modelDo) {
        LambdaQueryWrapper<ModelDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(modelDo.getProcessKey()), ModelDO::getProcessKey, modelDo.getProcessKey());
        queryWrapper.like(StringUtils.isNotBlank(modelDo.getModelName()), ModelDO::getModelName, modelDo.getModelName());
        queryWrapper.eq(StringUtils.isNotBlank(modelDo.getFlowType()), ModelDO::getFlowType, modelDo.getFlowType());
        queryWrapper.eq(ModelDO::getCompanyId, modelDo.getCompanyId() == null ? SecurityUtil.getCurrentUser().getComId() : modelDo.getCompanyId());
        queryWrapper.eq(modelDo.getMainVersion() != null, ModelDO::getMainVersion, modelDo.getMainVersion());
        queryWrapper.orderByDesc(ModelDO::getCreateTime);
        return queryWrapper;
    }

    @Override
    public IPage<ModelDO> getMainPageUnUse(Page page, ModelDO modelDo) {
        List<String> list = processService.getUsedModelIdList(modelDo.getCompanyId() == null ? SecurityUtil.getCurrentUser().getComId() : modelDo.getCompanyId());
        LambdaQueryWrapper<ModelDO> queryWrapper = getQueryWrapper(modelDo);
        queryWrapper.isNotNull(ModelDO::getDefinitionId);
        queryWrapper.notIn(list.size() != 0, ModelDO::getProcessKey, list);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void saveProcess(ProcessSaveVo vo) {
        ModelDO model = new ModelDO();
        model.setId(vo.getProcessId());
        model.setProcessXml(vo.getXml());
        model.setProcessSvg(vo.getSvg());
        this.baseMapper.updateById(model);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean processPublish(ModelDeployVo vo) {
        BestBpmAsset.isAssetEmpty(vo.getModelId());
        ModelDO model = this.getById(vo.getModelId());
        if (model == null) {
            throw new BpmException(BpmError.MODEL_NO_FIND_ERROR);
        }
        /* 校验流程格式 */
        boolean valid = BpmnUtil.checkProcessXml(model.getProcessXml());
        if (!valid) {
            throw new BpmException(BpmError.MODEL_NO_VALIDATED_ERROR);
        }
        RepositoryService repositoryService = SpringContextHolder.getBean(RepositoryService.class);

        Deployment deploy = null;
        try {
            deploy = repositoryService.createDeployment()
                    .name(model.getModelName())
                    .category(model.getFlowType())
                    .key(model.getProcessKey())
                    .tenantId(model.getTenantId())
                    .addBytes(model.getProcessKey() + ".bpmn20.xml", model.getProcessXml().getBytes("UTF-8"))
                    .deploy();
            //获取流程实例信息
            ProcessDefinitionQuery createProcessDefinitionQuery = repositoryService.createProcessDefinitionQuery();
            ProcessDefinitionQuery deploymentId = createProcessDefinitionQuery.deploymentId(deploy.getId());
            ProcessDefinition singleResult = deploymentId.singleResult();
            model.setDefinitionId(singleResult.getId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BpmException(BpmError.MODEL_DEPLOY_FAILED_ERROR);
        }

        model.setStatus(BpmConstant.PROCESS_DEPLOYED);
        // 保存 nodeList
        saveNodeList(model);
        return this.updateById(model);
    }

    @Override
    public boolean saveNodeList(ModelDO model) {
        /* 校验流程格式 */
        String processXml = model.getProcessXml();
        boolean valid = BpmnUtil.checkProcessXml(model.getProcessXml());
        if (!valid) {
            new BpmException(BpmError.MODEL_NO_VALIDATED_ERROR);
        }
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] bytes = new byte[0];
        try {
            bytes = processXml.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            new BpmException(BpmError.SYSTEM_ERROR);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = null;
        boolean result = false;
        try {
            in = new InputStreamReader(inputStream, "UTF-8");
            XMLStreamReader xtr = xif.createXMLStreamReader(in);
            BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(xtr);
            List<NodeDO> nodeDOList = FlowElementUtils.bpmnModelToNodeDOList(bpmnModel);
            for (NodeDO nodeDO : nodeDOList) {
                nodeDO.setModelId(model.getId());
                nodeDO.setProcessKey(model.getProcessKey());
                nodeDO.setDefinitionId(model.getDefinitionId());
                nodeDO.setTenantId(model.getTenantId());
            }
            result = nodeService.saveBatch(nodeDOList);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BpmException(BpmError.MODEL_DEPLOY_FAILED_ERROR);
        }
        return result;
    }

    @Override
    public List<ModelDO> findByProcessKey(String processKey) {
        LambdaQueryWrapper<ModelDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDO::getProcessKey, processKey);
        return this.list(wrapper);
    }

    @Override
    public ModelInfoDTO findByProcessKeyAndVersion(String processKey, int modelVersion) {
        LambdaQueryWrapper<ModelDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDO::getProcessKey, processKey);
        wrapper.eq(ModelDO::getModelVersion, modelVersion);
        ModelDO model = this.getOne(wrapper);
        return model == null ? null : BeanUtil.copyProperties(model, ModelInfoDTO.class);
    }

    @Override
    public void setMainVersion(String modelId) {
        ModelDO model = this.getById(modelId);
        if (model == null) {
            throw new BpmException(BpmError.MODEL_NO_FIND_ERROR);
        }
        if (model.getMainVersion() == BpmConstant.MAIN_VERSION) {
            return;
        }
        if (BpmConstant.PROCESS_NOT_DEPLOY == model.getStatus()) {
            throw new BpmException("模型未发布，请发布之后再设置为主版本~", BpmError.MODEL_NO_FIND_ERROR);
        }
        // 设置当前模型为主版本
        model.setMainVersion(BpmConstant.MAIN_VERSION);
        // 设置其他版本为非主版本
        List<ModelDO> modelDOS = this.findByProcessKey(model.getProcessKey());
        ArrayList<ModelDO> list = new ArrayList<>();
        for (ModelDO modelDO : modelDOS) {
            if (BpmConstant.MAIN_VERSION == modelDO.getMainVersion()) {
                modelDO.setMainVersion(BpmConstant.NOT_MAIN_VERSION);
                list.add(modelDO);
            }
        }
        this.updateBatchById(list);
        this.updateById(model);
    }

    @Override
    public ModelInfoDTO findMainModel(String processKey) {
        LambdaQueryWrapper<ModelDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ModelDO::getProcessKey, processKey);
        queryWrapper.eq(ModelDO::getMainVersion, BpmConstant.MAIN_VERSION);
        ModelDO modelDO = this.getOne(queryWrapper);
        return BeanUtil.copyProperties(modelDO, ModelInfoDTO.class);
    }

    @Override
    public List<String> selectFormIdList(String companyId) {
        return baseMapper.selectFormIdList(companyId);
    }

    @Override
    public IPage<ModelDO> getMainPageProcess(Page page, ModelDO modelDo) {
        LambdaQueryWrapper<ModelDO> queryWrapper = getQueryWrapper(modelDo);
        queryWrapper.isNotNull(ModelDO::getDefinitionId);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
