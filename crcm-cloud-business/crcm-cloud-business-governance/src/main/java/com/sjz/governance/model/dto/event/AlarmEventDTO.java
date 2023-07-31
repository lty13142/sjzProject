package com.sjz.governance.model.dto.event;

import com.sjz.governance.model.entity.event.AlarmEvent;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * 综合治理_告警事件对象
 *
 * @author pengl
 * @date 2023-04-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmEventDTO extends AlarmEvent {

    /**
     * 事件开始时间
     */
    @ApiModelProperty(value = "事件开始时间")
    private String startTime;

    /**
     * 事件结束时间
     */
    @ApiModelProperty(value = "事件结束时间")
    private String endTime;


    /**
     * 事件说明
     */
    @ApiModelProperty(value = "事件说明")
    private String dealExplain;


    /**
     * 处理附件
     */
    @ApiModelProperty(value = "处理附件")
    private String dealFileUrl;

    /**
     * 处理人
     */
    @ApiModelProperty(value = "处理人")
    private String dealPerson;

    /**
     * 处理人姓名
     */
    @ApiModelProperty(value = "处理人姓名")
    private String dealPersonName;

    /**
     * 下一流程节点
     */
    @ApiModelProperty(value = "下一流程节点")
    private String nextDealNode;

    /**
     * 当前流程节点id
     */
    @ApiModelProperty(value = "当前流程节点id")
    private Integer currentDealNodeId;

    /**
     * 处理流转节点：1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成
     */
    @ApiModelProperty(value = "处理流转节点：1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成")
    private String dealNodes;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    private String year;

}
