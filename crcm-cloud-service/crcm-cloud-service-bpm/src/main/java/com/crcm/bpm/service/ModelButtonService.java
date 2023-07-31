package com.crcm.bpm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.entity.ModelButtonDO;
import com.crcm.bpm.domain.vo.ModelButtonVO;

import java.util.List;

/**
 * @author zwj
 * @date 2020-11-02
 */
public interface ModelButtonService extends IService<ModelButtonDO> {
    /**
     * 保存
     * @param modelButtonDOList
     * @return
     */
    void saveModelButtonDO(String modelButtonDOList);

    /**
     * 更新
     * @param modelButtonDO
     * @return
     */
    int updateModelButtonDO(ModelButtonDO modelButtonDO);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ModelButtonDO findById(String id);

    /**
     * 查询列表
     * @param modelButtonDO
     * @return
     */
    List<ModelButtonDO> findList(ModelButtonDO modelButtonDO);

    /**
     * 获取审批中流程按钮
     * @param nodeId
     * @param modelId
     * @return
     */
    List<ModelButtonDO> getButtonList(String nodeId, Long modelId);
}
