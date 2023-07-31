package com.sjz.governance.model.vo.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @ClassName IntelligentRecognitionEventVo
 * @Description 智能识别事件信息
 * @Author GZL
 * @Date 2023/4/9 11:26
 * @Version 1.0
 **/
@Data
public class IntelligentRecognitionEventVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 事件图片
     */
    @ApiModelProperty(value = "事件图片")
    private String eventUrl;

    /**
     * 事件名称
     */
    @ApiModelProperty(value = "事件名称")
    private String eventName;

    /**
     * 监控名称
     */
    @ApiModelProperty(value = "监控名称")
    private String cameraName;

    /**
     * 识别率
     */
    @ApiModelProperty(value = "识别率")
    private String recognitionRate;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
