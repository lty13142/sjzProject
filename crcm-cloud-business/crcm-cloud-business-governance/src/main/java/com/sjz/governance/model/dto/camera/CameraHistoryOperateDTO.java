package com.sjz.governance.model.dto.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CameraHistoryOperateDTO
 * @Description 监控历史回放操作DTO
 * @Author GZL
 * @Date 2023/4/7 16:56
 * @Version 1.0
 **/
@Data
public class CameraHistoryOperateDTO {

    @ApiModelProperty(value = "权限id")
    private String streamId;

    @ApiModelProperty(value = "倍速")
    private String speed;

    @ApiModelProperty(value = "拖动时间")
    private String seekTime;
}
