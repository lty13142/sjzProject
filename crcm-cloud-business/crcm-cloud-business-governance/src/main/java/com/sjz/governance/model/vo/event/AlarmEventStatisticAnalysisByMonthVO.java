package com.sjz.governance.model.vo.event;

import lombok.Data;

import java.util.List;

/**
 * 综合治理_告警事件统计分析对象月度统计
 *
 * @author pengl
 * @date 2023-04-04
 */
@Data
public class AlarmEventStatisticAnalysisByMonthVO {

    /**
     * 事件
     */
    private Long eventCount;
    /**
     * 月份
     */
    private String month;

}
