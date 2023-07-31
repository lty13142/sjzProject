package com.sjz.governance.model.vo.camera;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @ClassName CameraIdentificationVo
 * @Description TODO
 * @Author GZL
 * @Date 2023/4/6 11:26
 * @Version 1.0
 **/
@Data
public class CameraIdentificationVo {

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
     * IP
     */
    @ApiModelProperty(value = "IP")
    private String ip;

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码")
    private String deviceCode;

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
     * 监控地址
     */
    @ApiModelProperty(value = "监控地址")
    private String address;

    /**
     * 中心经度
     */
    @ApiModelProperty(value = "中心经度")
    private BigDecimal longitude;

    /**
     * 中心纬度
     */
    @ApiModelProperty(value = "中心纬度")
    private BigDecimal latitude;

    /**
     * 智能识别类型，字典：CAMERA_CAPTURE_TYPE
     */
    @ApiModelProperty(value = "智能识别类型，字典：CAMERA_CAPTURE_TYPE")
    private String cameraCaptureType;

    /**
     * 所属管区id
     */
    @ApiModelProperty(value = "所属管区id")
    private String districtId;

    /**
     * 负责人id
     */
    @ApiModelProperty(value = "负责人id")
    private String userId;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String userName;

    /**
     * 监控抓拍图片地址
     */
    private String snapUrl;


    /**
     * 智能识别类型
     */
    private String cameraCaptureTypeCh;

    /**
     * 结果标记
     */
    private boolean resultFlag;
}
