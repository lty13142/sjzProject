package com.sjz.governance.model.entity.event;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 综合治理_告警事件流转节点对象 ct_alarm_event_flow_node
 *
 * @author pengl
 * @date 2023-04-04
 */
@Setter
@Getter
@ToString
@ApiModel("综合治理_告警事件流转节点")
@TableName("ct_alarm_event_flow_node")
public class AlarmEventFlowNode extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 事件id
     */
    @ApiModelProperty(value = "事件id")
    private Integer eventId;

    /**
     * 处理人
     */
    @ApiModelProperty(value = "处理人")
    @Size(max = 100, message = "处理人最多输入100个字符")
    private String dealPerson;

    /**
     * 处理人姓名
     */
    @ApiModelProperty(value = "处理人姓名")
    @Size(max = 100, message = "处理人最多输入100个字符")
    private String dealPersonName;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    @Size(max = 100, message = "说明最多输入100个字符")
    private String dealExplain;

    /**
     * 处理流转节点：1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成
     */
    @ApiModelProperty(value = "处理流转节点：1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成")
    @Size(max = 100, message = "处理流转节点最多输入100个字符")
    private String dealNode;

    /**
     * 处理状态：1已处理  0未处理
     */
    @ApiModelProperty(value = "处理状态：1已处理  0未处理")
    @Size(max = 100, message = "处理状态：1已处理  0未处理最多输入100个字符")
    private String dealStatus;

    /**
     * 处理附件
     */
    @ApiModelProperty(value = "处理附件")
    @Size(max = 100, message = "处理附件最多输入100个字符")
    private String dealFileUrl;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
