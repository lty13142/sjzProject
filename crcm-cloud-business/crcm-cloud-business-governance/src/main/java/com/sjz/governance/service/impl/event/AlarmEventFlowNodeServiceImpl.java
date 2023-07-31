package com.sjz.governance.service.impl.event;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.crcm.core.constant.DicConstant;
import com.sjz.governance.mapper.event.AlarmEventFlowNodeMapper;
import com.sjz.governance.model.dto.event.AlarmEventFlowNodeDTO;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.vo.event.AlarmEventFlowNodeVO;
import com.sjz.governance.service.event.AlarmEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.sjz.governance.model.entity.event.AlarmEventFlowNode;
import com.sjz.governance.service.event.AlarmEventFlowNodeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 综合治理_告警事件流转节点Service业务层处理
 * 
 * @author pengl
 * @date 2023-04-04
 */
@Service
@Transactional
public class AlarmEventFlowNodeServiceImpl extends ServiceImpl<AlarmEventFlowNodeMapper, AlarmEventFlowNode> implements AlarmEventFlowNodeService {

    /**
     * 新增综合治理_告警事件流转节点
     * 
     * @param alarmEventFlowNode 综合治理_告警事件流转节点
     * @return 结果
     */
    @Override
    public boolean saveAlarmEventFlowNode(AlarmEventFlowNode alarmEventFlowNode) {
        return this.save(alarmEventFlowNode);
    }

    /**
     * 修改综合治理_告警事件流转节点
     * 
     * @param alarmEventFlowNode 综合治理_告警事件流转节点
     * @return 结果
     */
    @Override
    public boolean updateAlarmEventFlowNode(AlarmEventFlowNode alarmEventFlowNode) {
        return this.updateById(alarmEventFlowNode);
    }

    /**
     * 删除综合治理_告警事件流转节点信息
     * 
     * @param id 综合治理_告警事件流转节点ID
     * @return 结果
     */
    @Override
    public boolean deleteAlarmEventFlowNode(Integer id) {
        return this.removeById(id);
    }

    /**
     * 查询综合治理_告警事件流转节点
     *
     * @param id 综合治理_告警事件流转节点ID
     * @return 综合治理_告警事件流转节点
     */
    @Override
    public AlarmEventFlowNode findAlarmEventFlowNodeById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询综合治理_告警事件流转节点列表
     *
     * @param dto 综合治理_告警事件流转节点
     * @return 综合治理_告警事件流转节点
     */
    @Override
    public List<AlarmEventFlowNodeVO> findAlarmEventFlowNodeList(AlarmEventFlowNodeDTO dto) {
        LambdaQueryWrapper<AlarmEventFlowNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotNull(dto.getEventId()),AlarmEventFlowNode::getEventId,dto.getEventId())
                .orderByAsc(BaseEntity::getCreateTime);
        List<AlarmEventFlowNode> alarmEventFlowNodes = this.baseMapper.selectList(queryWrapper);
        List<AlarmEventFlowNodeVO> collect = alarmEventFlowNodes.stream().map(e -> {
            AlarmEventFlowNodeVO vo = new AlarmEventFlowNodeVO();
            BeanUtils.copyProperties(e, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 分页查询综合治理_告警事件流转节点
     *
     * @param page 分页参数
     * @param alarmEventFlowNode 综合治理_告警事件流转节点
     * @return 综合治理_告警事件流转节点
     */
    @Override
    public PageT<AlarmEventFlowNode> findAlarmEventFlowNodePage(PageT<AlarmEventFlowNode> page, AlarmEventFlowNode alarmEventFlowNode) {
        LambdaQueryWrapper<AlarmEventFlowNode> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

    @Override
    public void deleteByAlarmEventId(Integer id) {
        if (id != null){
            this.baseMapper.deleteByAlarmEventId(id);
        }
    }
}
