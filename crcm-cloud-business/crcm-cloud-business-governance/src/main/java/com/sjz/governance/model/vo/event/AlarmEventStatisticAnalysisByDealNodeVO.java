package com.sjz.governance.model.vo.event;

import lombok.Data;

/**
 * 综合治理_告警事件统计分析对象按处理节点分
 *
 * @author pengl
 * @date 2023-04-06
 */
@Data
public class AlarmEventStatisticAnalysisByDealNodeVO {
    /**
     * 事件总数
     */
    private Long eventCount;
    /**
     * 事件占比
     */
    private String percent;
    /**
     * 事件流转节点
     */
    private String dealNode;
    /**
     * 事件流转节点
     */
    private String dealNodeCh;

}
