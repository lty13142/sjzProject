package com.sjz.governance.model.dto.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PTZCtrlProxyDTO
 * @Description 监控云台控制器
 * @Author GZL
 * @Date 2023/4/4 9:39
 * @Version 1.0
 **/
@Data
public class PTZCtrlProxyDTO {


    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "通道ID")
    private String channelId;

    @ApiModelProperty(value = "监控左右移动方向（0：停止；1：左；2：右）")
    private Integer leftRight = 0;

    @ApiModelProperty(value = "监控上下移动方向（0：停止；1：上；2：下）")
    private Integer upDown = 0;

    @ApiModelProperty(value = "监控调焦（0：停止；1：调焦-；2：调焦+）")
    private Integer inOut = 0;

    @ApiModelProperty(value = "监控移动速度")
    private Integer moveSpeed = 50;

    @ApiModelProperty(value = "监控缩放速度")
    private Integer zoomSpeed = 20;

}
