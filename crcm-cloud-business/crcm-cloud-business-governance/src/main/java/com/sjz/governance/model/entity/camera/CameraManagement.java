package com.sjz.governance.model.entity.camera;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 视频监控管理对象 ct_camera_management
 *
 * @author guozhilin
 * @date 2023-04-03
 */
@Setter
@Getter
@ToString
@ApiModel("视频监控管理")
@TableName("ct_camera_management")
public class CameraManagement extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "ID")
    @Size(max = 32, message = "ID最多输入32个字符")
    private String id;

    /**
     * 监控名称
     */
    @ApiModelProperty(value = "监控名称", required = true)
    @NotBlank(message = "监控名称不能为空")
    @Size(max = 64, message = "监控名称最多输入64个字符")
    private String cameraName;

    /**
     * IP
     */
    @ApiModelProperty(value = "IP")
    @Size(max = 32, message = "IP最多输入32个字符")
    private String ip;

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码")
    @Size(max = 36, message = "设备编码最多输入36个字符")
    private String deviceCode;

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    @Size(max = 20, message = "设备ID最多输入20个字符")
    private String deviceId;

    /**
     * 通道ID
     */
    @ApiModelProperty(value = "通道ID")
    @Size(max = 20, message = "通道ID最多输入20个字符")
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
    @Size(max = 128, message = "监控地址最多输入128个字符")
    private String address;

    /**
     * 中心经度
     */
    @ApiModelProperty(value = "中心经度")
    @Digits(integer = 5, fraction = 14, message = "中心经度整数最多5位，小数最多14位")
    private BigDecimal longitude;

    /**
     * 中心纬度
     */
    @ApiModelProperty(value = "中心纬度")
    @Digits(integer = 5, fraction = 14, message = "中心纬度整数最多5位，小数最多14位")
    private BigDecimal latitude;

    /**
     * 监控类型，字典：CAMERA_TYPE
     */
    @ApiModelProperty(value = "监控类型，字典：CAMERA_TYPE")
    @Size(max = 32, message = "监控类型最多输入32个字符")
    private String cameraType;

    /**
     * 智能识别类型，字典：CAMERA_CAPTURE_TYPE
     */
    @ApiModelProperty(value = "智能识别类型，字典：CAMERA_CAPTURE_TYPE")
    @Size(max = 32, message = "智能识别类型最多输入32个字符")
    private String cameraCaptureType;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @Size(max = 24, message = "账号最多输入24个字符")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Size(max = 128, message = "密码最多输入128个字符")
    private String password;

    /**
     * 设备描述
     */
    @ApiModelProperty(value = "设备描述")
    @Size(max = 500, message = "设备描述最多输入500个字符")
    private String deviceDesc;

    /**
     * 是否支持云台控制
     */
    @ApiModelProperty(value = "是否支持云台控制")
    private Long cloudConsole;

    /**
     * 所属村id
     */
    @ApiModelProperty(value = "所属村id")
    @Size(max = 64, message = "所属村id最多输入64个字符")
    private String districtId;

    /**
     * 负责人id
     */
    @ApiModelProperty(value = "负责人id")
    @Size(max = 64, message = "负责人id最多输入64个字符")
    private String userId;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    @Size(max = 64, message = "负责人最多输入64个字符")
    private String userName;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
