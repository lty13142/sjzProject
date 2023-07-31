package com.sjz.governance.model.entity.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 综合治理_告警事件对象 ct_alarm_event
 *
 * @author pengl
 * @date 2023-04-04
 */
@Setter
@Getter
@ToString
@ApiModel("综合治理_告警事件")
@TableName("ct_alarm_event")
public class AlarmEvent extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 事件编号
     */
    @ApiModelProperty(value = "事件编号")
    @Size(max = 100, message = "事件编号最多输入100个字符")
    private String eventNumber;

    /**
     * 事件图片
     */
    @ApiModelProperty(value = "事件图片")
    @Size(max = 100, message = "事件图片最多输入100个字符")
    private String eventUrl;

    /**
     * 事件类型
     */
    @ApiModelProperty(value = "事件类型")
    @Size(max = 100, message = "事件类型最多输入100个字符")
    private String eventType;

    /**
     * 事件名称
     */
    @ApiModelProperty(value = "事件名称")
    @Size(max = 100, message = "事件名称最多输入100个字符")
    private String eventName;

    /**
     * 处理流转节点：1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成
     */
    @ApiModelProperty(value = "处理流转节点：1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成")
    @Size(max = 100, message = "处理流转节点最多输入100个字符")
    private String dealNode;

    /**
     * 地址/网络
     */
    @ApiModelProperty(value = "地址/网络")
    @Size(max = 100, message = "地址/网络最多输入100个字符")
    private String addressNetwork;

    /**
     * 识别率
     */
    @ApiModelProperty(value = "识别率")
    @Size(max = 100, message = "识别率最多输入100个字符")
    private String recognitionRate;

    /**
     * 是否误报：0正常 1误报
     */
    @ApiModelProperty(value = "是否误报：0正常 1误报")
    @Size(max = 100, message = "是否误报：0正常 1误报最多输入100个字符")
    private String isMisinformation;

    /**
     * 所属村庄
     */
    @ApiModelProperty(value = "所属村庄")
    @Size(max = 100, message = "所属村庄最多输入100个字符")
    private String villageId;

    /**
     * 摄像头id
     */
    @ApiModelProperty(value = "摄像头id")
    @Size(max = 100, message = "摄像头id最多输入100个字符")
    private String cameraId;

    /**
     * 事件来源
     */
    @ApiModelProperty(value = "事件来源 1自动生成  2手动生成")
    private String eventSource;

    /**
     * 告警时间
     */
    @ApiModelProperty(value = "告警时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime alarmTime;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 100, message = "备注信息最多输入100个字符")
    private String remark;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
