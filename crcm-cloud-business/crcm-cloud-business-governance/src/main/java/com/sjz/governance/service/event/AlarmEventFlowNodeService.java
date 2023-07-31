package com.sjz.governance.service.event;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.sjz.governance.model.dto.event.AlarmEventFlowNodeDTO;
import com.sjz.governance.model.entity.event.AlarmEventFlowNode;
import com.sjz.governance.model.vo.event.AlarmEventFlowNodeVO;

/**
 * 综合治理_告警事件流转节点Service接口
 * 
 * @author pengl
 * @date 2023-04-04
 */
public interface AlarmEventFlowNodeService extends IService<AlarmEventFlowNode>{

    /**
     * 新增综合治理_告警事件流转节点
     * 
     * @param alarmEventFlowNode 综合治理_告警事件流转节点
     * @return 结果
     */
    boolean saveAlarmEventFlowNode(AlarmEventFlowNode alarmEventFlowNode);

    /**
     * 修改综合治理_告警事件流转节点
     * 
     * @param alarmEventFlowNode 综合治理_告警事件流转节点
     * @return 结果
     */
    boolean updateAlarmEventFlowNode(AlarmEventFlowNode alarmEventFlowNode);

    /**
     * 删除综合治理_告警事件流转节点信息
     * 
     * @param id 综合治理_告警事件流转节点ID
     * @return 结果
     */
    boolean deleteAlarmEventFlowNode(Integer id);

    /**
     * 查询综合治理_告警事件流转节点
     *
     * @param id 综合治理_告警事件流转节点ID
     * @return 综合治理_告警事件流转节点
     */
    AlarmEventFlowNode findAlarmEventFlowNodeById(Integer id);

    /**
     * 查询综合治理_告警事件流转节点列表
     *
     * @param dto 综合治理_告警事件流转节点
     * @return 综合治理_告警事件流转节点集合
     */
    List<AlarmEventFlowNodeVO> findAlarmEventFlowNodeList(AlarmEventFlowNodeDTO dto);

    /**
     * 分页查询综合治理_告警事件流转节点列表
     * @param page 分页参数
     * @param alarmEventFlowNode 综合治理_告警事件流转节点
     * @return 综合治理_告警事件流转节点集合
     */
    PageT<AlarmEventFlowNode> findAlarmEventFlowNodePage(PageT<AlarmEventFlowNode> page, AlarmEventFlowNode alarmEventFlowNode);

    void deleteByAlarmEventId(Integer id);
}
