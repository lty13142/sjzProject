package com.sjz.governance.model.dto.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CameraHtmlPreviewDTO
 * @Description 监控摄像头预览DTO
 * @Author GZL
 * @Date 2023/4/4 9:52
 * @Version 1.0
 **/
@Data
public class CameraHtmlPreviewDTO {

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "通道ID")
    private String channelId;

    @ApiModelProperty(value = "rtsp播放流")
    private String deviceDesc;
}
