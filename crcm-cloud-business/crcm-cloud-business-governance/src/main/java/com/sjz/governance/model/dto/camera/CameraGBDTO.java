package com.sjz.governance.model.dto.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName CameraGBDTO
 * @Description 国标接入DTO
 * @Author GZL
 * @Date 2023/4/4 11:00
 * @Version 1.0
 **/
@Data
public class CameraGBDTO {
    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    @Size(max = 20, message = "设备ID最多输入20个字符")
    @NotBlank(message = "设备ID不能为空")
    private String deviceId;
}
