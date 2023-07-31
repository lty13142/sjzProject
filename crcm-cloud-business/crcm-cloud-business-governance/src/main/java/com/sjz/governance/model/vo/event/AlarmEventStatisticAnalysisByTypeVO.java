package com.sjz.governance.model.vo.event;

import lombok.Data;

import java.util.List;

/**
 * 综合治理_告警事件统计分析对象按类型分
 *
 * @author pengl
 * @date 2023-04-04
 */
@Data
public class AlarmEventStatisticAnalysisByTypeVO {
    /**
     * 事件总数
     */
    private Long eventCount;
    /**
     * 事件占比
     */
    private String percent;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件类型
     */
    private String eventTypeCh;

}
