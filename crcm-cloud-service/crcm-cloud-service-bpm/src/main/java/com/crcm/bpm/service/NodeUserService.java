package com.crcm.bpm.service;

import com.crcm.bpm.domain.dto.request.NodeUserSaveOrUpdateDTO;
import com.crcm.bpm.domain.entity.NodeUserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface NodeUserService extends IService<NodeUserDO> {

    int saveNodeUser(NodeUserDO nodeUserDO);

    int updateNodeUser(NodeUserDO nodeUserDO);

    int deleteById(String id);

    int realDelete(String id);

    NodeUserDO findById(String id);

    List<NodeUserDO> findList(NodeUserDO nodeUserDO);

    IPage<NodeUserDO> findPage(Page page, NodeUserDO nodeUserDO);

    boolean batchSave(List<NodeUserSaveOrUpdateDTO> nodeUserSaveList);

    boolean saveNodeUser(NodeUserSaveOrUpdateDTO nodeUserSaveOrUpdateDTO);
}
