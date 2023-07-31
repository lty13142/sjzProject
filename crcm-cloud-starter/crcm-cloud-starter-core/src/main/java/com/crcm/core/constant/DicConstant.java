package com.crcm.core.constant;

import com.crcm.core.annotation.ChName;

/**
 * @ClassName DicConstant
 * @Description 数据字典
 * @Author GZL
 * @Date 2023/3/27 14:21
 * @Version 1.0
 **/
public class DicConstant {

    @ChName("废物形态")
    public interface WASTE_SHAPE {
        String CODE = "WASTE_SHAPE";
    }

    @ChName("废物打包方式")
    public interface WASTE_PACKING_TYPE {
        String CODE = "WASTE_PACKING_TYPE";
    }

    @ChName("学习中心文档类型")
    public interface STUDY_DOCUMENT_TYPE {
        String CODE = "STUDY_DOCUMENT_TYPE";
    }

    @ChName("性别")
    public interface SEX {
        String CODE = "SEX";
    }

    @ChName("奖惩事项类型")
    public interface ORGANIZATIONAL_MATTERS{
        String CODE="ORGANIZATIONAL_MATTERS";
    }

    @ChName("发布状态")
    public interface RELEASE_STATUS{
        String CODE="RELEASE_STATUS";
    }

   @ChName("是否确认")
    public interface CONFIRM_STATUS{
        String CODE="CONFIRM_STATUS";
    }

    @ChName("是否完成")
    public interface COMPLETE_STATUS{
        String CODE="COMPLETE_STATUS";
    }
    @ChName("村民类型")
    public interface VILLAGER_TYPE {
        String CODE = "VILLAGER_TYPE";
    }

    @ChName("重点人员类型")
    public interface KEY_PERSON_TYPE {
        String CODE = "KEY_PERSON_TYPE";
    }

    @ChName("车辆品牌")
    public interface VEHICLE_BRAND {
        String CODE = "VEHICLE_BRAND";
    }

    @ChName("车辆颜色")
    public interface VEHICLE_COLOR {
        String CODE = "VEHICLE_COLOR";
    }

    @ChName("事件处理流程节点")
    public interface EVENT_DEAL_NODE {
        String CODE = "EVENT_DEAL_NODE";
        @ChName("镇派发")
        String EVENT_DEAL_NODE_1 = "1";
        @ChName("管区派发")
        String EVENT_DEAL_NODE_2 = "2";
        @ChName("待接收")
        String EVENT_DEAL_NODE_3 = "3";
        @ChName("待处理")
        String EVENT_DEAL_NODE_4 = "4";
        @ChName("待反馈")
        String EVENT_DEAL_NODE_5 = "5";
        @ChName("已完成")
        String EVENT_DEAL_NODE_6 = "6";
    }

    @ChName("流程节点处理状态")
    public interface NODE_DEAL_STATUS {
        String CODE = "NODE_DEAL_STATUS";
        @ChName("未处理")
        String UNDEAL = "0";
        @ChName("通过")
        String PASS = "1";
        @ChName("退回")
        String BACK = "2";
    }

    @ChName("监控国标地址")
    public interface GB28181_CAMERA {
        String CODE = "GB28181_CAMERA";
    }

    @ChName("告警事件是否误报")
    public interface IS_MISINFORMATION {
        String CODE = "IS_MISINFORMATION";
        @ChName("是")
        String YES = "1";
        @ChName("否")
        String NO = "0";
    }
    @ChName("监控类型")
    public interface CAMERA_TYPE {
        String CODE = "CAMERA_TYPE";
    }

    @ChName("智能识别类型")
    public interface CAMERA_CAPTURE_TYPE {
        String CODE = "CAMERA_CAPTURE_TYPE";
    }

    @ChName("AI分析阈值")
    public interface AI_THRESHOLD {
        String CODE = "AI_THRESHOLD";
    }

}
