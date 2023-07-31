package com.crcm.cloud.start.file.minio.constants;

import lombok.Getter;

import java.util.Objects;

/**
 * @ClassName MinioFilePathEnum
 * @Description minio文件上传文件路径枚举
 * @Author GZL
 * @Date 2023/2/24 11:05
 * @Version 1.0
 **/
@Getter
public enum MinioBucket {

    AVATAR(0, "avatar", "用户头像", false),
    QUALIFICATION(1, "qualification", "资质文件夹", false),
    BUSINESS_LICENSE(2, "businesslicense", "营业执照文件夹", true),
    VEHICLES(3, "vehicles", "运输车辆附件", false),
    DRIVER_LICENSE(4, "driverlicense", "司机驾驶证文件夹", false),
    ESCORT_LICENSE(5, "escortlicense", "司机押运证文件夹", false),
    DRIVER_QUALIFICATIONS(6, "driverqualifications", "司机资格证", false),
    SYS_MENU(7, "sysmenu", "菜单图标", false),
    REMOTE_SENSING(8, "remotesensing", "遥感图片", false),
    ENFORCEMENT(10, "bjmzf", "不见面执法", true),
    OTHER_ATTACHMENT(20, "other", "其它附件文件夹", true),
    QRCODE(22, "bjmzfqrcode", "不见面执法打卡点二维码", true),
    MATERIAL(23, "bjmzfmaterial", "不见面执法素材库", true),
    DOCUMENT_FILE(24, "documentInfo", "文档附件", true),
    EQUIPMENT_SHEET_FILE(26, "equipmentSheet", "设备确认书", false),
    KEY_PERSON_PIC(27, "keyPersonPic", "重点人员图片", false),
    KEY_VEHICLE_PIC(28, "keyVehiclePic", "重点车辆图片", false),
    ALARM_EVENT_PIC(29, "alarmEventPic", "告警事件图片", false),
    REWARD_PUNISHMENT(30,"rewardPunishment","奖惩管理",true),
    ALARM_EVENT_FLOW_NODE_FILE(31, "alarmEventFlowNodeFile", "告警事件流转节点处理附件", false),;

    /**
     * 路径名称说明
     */
    private final String desc;
    /**
     * 文件类型
     */
    private final Integer type;
    /**
     * 路径
     */
    private final String path;
    /**
     * 是否可以匿名操作
     */
    private final boolean anonymousFlag;

    MinioBucket(Integer type, String path, String desc, boolean anonymousFlag) {
        this.desc = desc;
        this.type = type;
        this.path = path;
        this.anonymousFlag = anonymousFlag;
    }

    /**
     * 根据类型获取文件保存路径
     *
     * @return 文件保存路径
     * @Author GZL
     * @Date 2023/2/24 11:25
     * @Param type 类型
     **/
    public static MinioBucket getPathByType(Integer type) {
        if(Objects.isNull(type)){
            return null;
        }
        for (MinioBucket value : MinioBucket.values()) {
            if (Objects.equals(type, value.getType())) {
                return value;
            }
        }
        return null;
    }
}
