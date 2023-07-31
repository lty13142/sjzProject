package com.sjz.governance.model.dto.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CameraManagementDTO
 * @Description 监控查询DTO
 * @Author GZL
 * @Date 2023/4/6 15:27
 * @Version 1.0
 **/
@Data
public class CameraManagementDTO {

    /**
     * 监控名称
     */
    @ApiModelProperty(value = "监控名称")
    private String cameraName;

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    /**
     * 监控状态（0：不在线，1：在线，字典：CAMERA_STATUS）
     */
    @ApiModelProperty(value = "监控状态（0：不在线，1：在线，字典：CAMERA_STATUS）")
    private Integer status;

    /**
     * 监控类型，字典：CAMERA_TYPE
     */
    @ApiModelProperty(value = "监控类型，字典：CAMERA_TYPE")
    private String cameraType;

    /**
     * 智能识别类型，字典：CAMERA_CAPTURE_TYPE
     */
    @ApiModelProperty(value = "智能识别类型，字典：CAMERA_CAPTURE_TYPE")
    private String cameraCaptureType;

    /**
     * 所属村id
     */
    @ApiModelProperty(value = "所属村id")
    private String districtId;
}
