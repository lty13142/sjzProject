package com.sjz.governance.model.dto.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CameraPlayBackDTO
 * @Description 监控回放 DTO
 * @Author GZL
 * @Date 2023/4/4 9:38
 * @Version 1.0
 **/
@Data
public class CameraPlayBackDTO {

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "通道ID")
    private String channelId;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}

