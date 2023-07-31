package com.sjz.governance.model.vo.event;

import lombok.Data;

/**
 * 综合治理_告警事件统计分析对象按时间分
 *
 * @author pengl
 * @date 2023-04-04
 */
@Data
public class AlarmEventStatisticAnalysisByTimeVO {
    /**
     * 事件总数
     */
    private Long totalEventCount;
    /**
     * 今日事件
     */
    private Long todayEventCount;
    /**
     * 上周事件
     */
    private Long lastWeekEventCount;
    /**
     * 上月事件
     */
    private Long lastMontheventCount;

}
