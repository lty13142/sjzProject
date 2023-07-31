package com.sjz.governance.model.vo.camera;

import com.baomidou.mybatisplus.annotation.TableId;
import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @ClassName CameraManagementVo
 * @Description 监控视频Vo
 * @Author GZL
 * @Date 2023/4/6 15:01
 * @Version 1.0
 **/
@Data
public class CameraManagementVo {

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
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 设备描述
     */
    @ApiModelProperty(value = "设备描述")
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

    public String getCameraTypeCh() {
        return UtilDic.getDictName(DicConstant.CAMERA_TYPE.CODE, this.getCameraType());
    }

    public String getCameraCaptureTypeCh() {
        if (StringUtils.isBlank(this.getCameraCaptureType())) {
            return "";
        }
        String cameraCaptureType = this.getCameraCaptureType();
        String[] split = cameraCaptureType.split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = UtilDic.getDictName(DicConstant.CAMERA_CAPTURE_TYPE.CODE, split[i]);
        }
        return String.join(",", split);
    }

    public String getStatusCh() {
        return UtilDic.getDictName(SystemConstant.USER_STATUS.CODE, String.valueOf(this.getStatus()));
    }

    public String getDistrictCh() {
        return UtilSysArea.getAreaName(SystemConstant.AREA_TYPE.VILLAGE, this.getDistrictId());
    }
}
