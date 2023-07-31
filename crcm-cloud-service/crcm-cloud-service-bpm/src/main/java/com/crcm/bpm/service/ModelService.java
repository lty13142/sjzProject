package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.dto.response.ModelDTO;
import com.crcm.bpm.domain.dto.response.ModelInfoDTO;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.domain.vo.ModelDeployVo;
import com.crcm.bpm.domain.vo.ProcessSaveVo;

import java.util.List;

/**
 * 流程模型图Service接口
 *
 * @author zzyt
 * @date 2020-08-18
 */
public interface ModelService extends IService<ModelDO>{

    /**
     * 新增流程模型图
     *
     * @param modelDo 流程模型图
     * @return 结果
     */
    int saveModel(ModelDO modelDo);

    /**
     * 创建一个新版本
     * @param modelDo
     * @return
     */
    int createNewVersion(ModelDO modelDo);

    /**
     * 修改流程模型图
     *
     * @param modelDo 流程模型图
     * @return 结果
     */
    int updateModel(ModelDO modelDo);

    /**
     * 删除流程模型图信息
     *
     * @param id 流程模型图ID
     * @return 结果
     */
    int deleteModelById(Long id);

    /**
     * 查询流程模型图
     *
     * @param id 流程模型图ID
     * @return 流程模型图
     */
    ModelDO findModelById(String id);

    /**
     * 查询流程模型图列表
     *
     * @param modelDo 流程模型图
     * @return 流程模型图集合
     */
    List<ModelDO> findModelList(ModelDO modelDo);

    /**
     * 查询流程模型图列表
     * @param modelDo 流程模型图
     * @return 流程模型图集合
     */
    IPage<ModelDTO> findModelPage(Page page, ModelDO modelDo);

    /**
     * 分页查询未使用的已发布的流程模型图
     *
     * @param page
     * @param modelDo
     * @return
     */
    IPage<ModelDO> getMainPageUnUse(Page page, ModelDO modelDo);

    /**
     * 保存流程模型
     * @param vo
     */
    void saveProcess(ProcessSaveVo vo);

    /**
     * 发布流程模型
     * @param vo
     * @return
     */
    boolean processPublish(ModelDeployVo vo);

    /**
     * 保存流程模型节点信息
     * @param model
     * @return
     */
    public boolean saveNodeList(ModelDO model);

    /**
     * 通过流程KEY查询模型信息
     * @param processKey 流程KEY
     * @return
     */
    List<ModelDO> findByProcessKey(String processKey);

    /**
     * 通过流程KEY和模型版本查询模型信息
     * @param processKey 流程KEY
     * @param modelVersion 模型版本
     * @return
     */
    ModelInfoDTO findByProcessKeyAndVersion(String processKey, int modelVersion);

    void setMainVersion(String modelId);

    ModelInfoDTO findMainModel(String processKey);

    /**
     * 查找已绑定的formId
     *
     * @param companyId
     * @return
     */
    List<String> selectFormIdList(String companyId);

    IPage<ModelDO> getMainPageProcess(Page page, ModelDO modelDo);
}
