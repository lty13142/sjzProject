package com.sjz.governance.service.impl.event;

import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.service.event.AlarmEventService;
import com.sjz.governance.service.event.AlarmEventSyncService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 综合治理_告警事件Service业务层处理
 *
 * @author pengl
 * @date 2023-04-23
 */
@Service
public class AlarmEventSyncServiceImpl implements AlarmEventSyncService {

    @Resource
    private AlarmEventService alarmEventService;

    /**
     * 新增综合治理_告警事件_加锁
     *
     * @param dto 综合治理_告警事件_加锁
     * @return 结果
     */
    @Override
    public synchronized boolean saveAlarmEvent(AlarmEventDTO dto) {
        return alarmEventService.saveAlarmEvent(dto);
    }

}
