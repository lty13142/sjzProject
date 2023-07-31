package com.sjz.governance.service.event;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.model.dto.event.IntelligentRecognitionEventDTO;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.vo.event.*;

import java.util.List;

/**
 * 综合治理_告警事件Service接口
 *
 * @author pengl
 * @date 2023-04-23
 */
public interface AlarmEventSyncService {

    /**
     * 综合治理_告警事件_加锁
     *
     * @param dto 综合治理_告警事件_加锁
     * @return 结果
     */
    boolean saveAlarmEvent(AlarmEventDTO dto);

}
