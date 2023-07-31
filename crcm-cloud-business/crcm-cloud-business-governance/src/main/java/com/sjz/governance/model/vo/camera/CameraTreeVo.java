package com.sjz.governance.model.vo.camera;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CameraTreeVo
 * @Description 监控设备树
 * @Author GZL
 * @Date 2023/4/4 10:20
 * @Version 1.0
 **/
@Data
public class CameraTreeVo {

    /**
     * ID
     */
    @TableId(value = "id")
    private String id;

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
     * 通道ID
     */
    @ApiModelProperty(value = "通道ID")
    private String channelId;

    /**
     * 监控状态（0：不在线，1：在线，字典：CAMERA_STATUS）
     */
    @ApiModelProperty(value = "监控状态（0：不在线，1：在线，字典：CAMERA_STATUS）")
    private Integer status;

    /**
     * 是否支持云台控制
     */
    @ApiModelProperty(value = "是否支持云台控制, 0 否，1 是")
    private Integer cloudConsole;

    /**
     * 是否是监控
     */
    @ApiModelProperty(value = "是否是监控, 0 否，1 是")
    private Integer cameraFlag;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private String parentId;

    /**
     * 子集
     */
    @ApiModelProperty(value = "子集")
    private List<CameraTreeVo> children;
}
