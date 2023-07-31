package com.crcm.bpm.service;

import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.dto.response.NodeInfoDTO;
import com.crcm.bpm.domain.entity.NodeDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.vo.NodeQueryVO;

import java.util.List;
import java.util.Map;

public interface NodeService extends IService<NodeDO> {

    int saveNode(NodeDO nodeDO);

    int updateNode(NodeDO nodeDO);

    int deleteById(String id);

    int realDelete(String id);

    NodeDO findById(String id);

    List<NodeDO> findList(NodeDO nodeDO);

    IPage<NodeDO> findPage(Page page, NodeDO nodeDO);

    /**
     * 获取下一个节点
     *
     * @param procDefId
     * @param nodeId
     * @param dataMap
     * @param multipleRecursion
     * @param setError
     * @return
     */
    List<FlowUserTaskDTO> getNextNodeList(String procDefId, String nodeId, Map<String, Object> dataMap, boolean multipleRecursion, Boolean setError);

    /**
     * 获取下一个节点
     * @param procDefId
     * @param nodeId
     * @param dataMap
     * @param multipleRecursion
     * @return
     */
    List<FlowUserTaskDTO> getNextNodeList(String procDefId, String nodeId, Map<String, Object> dataMap,boolean multipleRecursion);

    /**
     * 通过节点编号和流程定义ID获取节点信息
     * @param nodeId
     * @param definitionId
     * @return
     */
    NodeInfoDTO getNodeInfoByNodeIdAndDefinitionId(String nodeId, String definitionId);
    /**
     * 计算节点用户
     * @param procDefId
     * @param nodeId
     * @param dataMap
     * @return
     */
    FlowUserTaskDTO calcNodeUsers(String procDefId, String nodeId, Map<String, Object> dataMap, Boolean setError);
}
