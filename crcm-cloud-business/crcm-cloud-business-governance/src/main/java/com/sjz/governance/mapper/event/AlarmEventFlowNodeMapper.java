package com.sjz.governance.mapper.event;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.governance.model.entity.event.AlarmEventFlowNode;
import com.sjz.governance.model.entity.key.KeyPersonManagement;
import com.sjz.governance.model.vo.key.KeyPersonManagementVO;
import org.apache.ibatis.annotations.Param;

/**
 * 综合治理_告警事件流转节点Mapper接口
 * 
 * @author pengl
 * @date 2023-04-04
 */
public interface AlarmEventFlowNodeMapper extends BaseMapper<AlarmEventFlowNode> {

    void deleteByAlarmEventId(Integer eventId);

}
