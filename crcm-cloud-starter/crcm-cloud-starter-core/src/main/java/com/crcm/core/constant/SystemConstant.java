package com.crcm.core.constant;

import com.crcm.core.annotation.ChName;

/**
 * @ClassName SystemConstant
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public class SystemConstant {
    /**
     * 启用状态
     */
    public interface ENABLE_STATUS {
        @ChName("启用状态")
        String CODE = "ENABLE_STATUS";
        @ChName("未启用")
        int UN_ENABLE = 0;
        @ChName("已启用")
        int ENABLE = 1;
        @ChName("已停用")
        int DISABLE = -1;
    }

    /**
     * 是或否
     */
    public interface YES_OR_NO {
        @ChName("是或否")
        String CODE = "YES_OR_NO";
        @ChName("是")
        int YES = 1;
        @ChName("否")
        int NO = 0;
    }

    /**
     * 用户类型
     */
    public interface USER_TYPE {
        @ChName("用户类型")
        String CODE = "USER_TYPE";
        @ChName("监管账号")
        int SUPERVISE_ACCOUNT = 1;
        @ChName("企业账号")
        int ENTERPRISE_ACCOUNT = 0;
    }

    /**
     * 监管用户类型
     */
    public interface SUPERVISE_USER_TYPE {
        @ChName("监管用户类型")
        String CODE = "SUPERVISE_USER_TYPE";
        @ChName("市局账号")
        int CITY_ACCOUNT = 0;
        @ChName("分局账号")
        int BRANCH_ACCOUNT = 1;
    }

    /**
     * 企业用户类型
     */
    public interface ENTERPRISE_USER_TYPE {
        @ChName("企业用户类型")
        String CODE = "ENTERPRISE_USER_TYPE";
        @ChName("产废单位")
        int WASTE_ACCOUNT = 0;
        @ChName("经营单位")
        int BUSINESS_ACCOUNT = 1;
        @ChName("运输单位")
        int TRANSPORT_ACCOUNT = 2;
        @ChName("医疗单位")
        int HOSPITAL_ACCOUNT = 3;
        @ChName("驾驶员")
        int DRIVER_ACCOUNT = 4;
        @ChName("医疗科室收集账号")
        int MEDICAL_DEPARTMENT_COLLECTED_ACCOUNT = 5;
        @ChName("其他账号")
        int OTHER_ACCOUNT = -1;
    }

    /**
     * 用户状态
     */
    public interface USER_STATUS {
        @ChName("用户状态")
        String CODE = "USER_STATUS";
        @ChName("不在线")
        int OFF_LINE = 0;
        @ChName("在线")
        int ON_LINE = 1;
    }

    /**
     * 锁定状态
     */
    public interface LOCK_STATUS {
        @ChName("锁定状态")
        String CODE = "LOCK_STATUS";
        @ChName("未锁定")
        int UN_LOCK = 0;
        @ChName("已锁定")
        int LOCKED = 1;
    }

    /**
     * 开启状态
     */
    public interface OPEN_STATUS {
        @ChName("开启状态")
        String CODE = "OPEN_STATUS";
        @ChName("关闭")
        int OFF = 0;
        @ChName("开启")
        int ON = 1;
    }

    /**
     * 文件上传方式
     */
    public interface UPLOAD_MODE {
        @ChName("文件上传方式")
        String CODE = "UPLOAD_MODE";
        @ChName("普通上传")
        int NORMAL = 0;
        @ChName("FTP上传")
        int FTP = 1;
        @ChName("FastDFS上传")
        int FAST_DFS = 2;
        @ChName("minio上传")
        int MINIO = 3;
    }

    /**
     * 文件上传状态
     */
    public interface UPLOAD_STATUS {
        @ChName("文件上传状态")
        String CODE = "UPLOAD_STATUS";
        @ChName("未上传")
        Integer NOT_UPLOADED = 0;
        @ChName("部分上传")
        Integer PART_UPLOAD = 1;
        @ChName("已上传")
        Integer UPLOADED = 2;
    }

    /**
     * 角色类型
     */
    public interface ROLE_TYPE {
        @ChName("角色类型")
        String CODE = "ROLE_TYPE";
        @ChName("普通")
        int NORMAL = 0;
        @ChName("管理员")
        int ADMIN = 1;
    }

    /**
     * 组织类型
     */
    public interface ORG_TYPE {
        @ChName("组织类型")
        String CODE = "ORG_TYPE";
        @ChName("政府")
        int ORG_TYPE_1 = 1;
        @ChName("党委")
        int ORG_TYPE_2 = 2;
    }

    /**
     * 菜单类型
     */
    public interface MENU_TYPE {
        @ChName("菜单类型")
        String CODE = "MENU_TYPE";
        @ChName("菜单")
        int MENU = 0;
        @ChName("按钮")
        int BUTTON = 1;
    }

    /**
     * 菜单类型
     */
    public interface MENU_BELONG {
        @ChName("菜单所属")
        String CODE = "MENU_BELONG";
        @ChName("PC")
        int PC = 0;
        @ChName("APP")
        int APP = 1;
    }

    /**
     * 权限类型
     */
    public interface PERMISSION_TYPE {
        @ChName("权限类型")
        String CODE = "PERMISSION_TYPE";
        @ChName("菜单权限")
        int MENU = 0;
        @ChName("资源（数据）权限")
        int RESOURCE = 1;
    }

    /**
     * 资源类型
     */
    public interface RESOURCE_TYPE {
        @ChName("资源类型")
        String CODE = "RESOURCE_TYPE";
        @ChName("资源")
        int RESOURCE = 0;
        @ChName("目录")
        int LIST = 1;
    }

    /**
     * 资源鉴权方式
     */
    public interface RESOURCE_AUTH_TYPE {
        @ChName("鉴权方式")
        String CODE = "RESOURCE_AUTH_TYPE";
        @ChName("权限匹配")
        int BY_CODE = 1;
        @ChName("路径匹配")
        int BY_URL = 2;
    }

    /**
     * 系统消息类型
     */
    public interface MESSAGE_TYPE {
        @ChName("系统消息类型")
        String CODE = "MESSAGE_TYPE";
        @ChName("系统公告")
        String NOTICE = "notice";
        @ChName("用户消息")
        String NOTIFY = "notify";
        @ChName("待办工作")
        String WORK = "work";
        @ChName("聊天信息")
        String CHAT = "chat";
    }

    /**
     * 系统日志类型
     */
    public interface LOG_TYPE {
        @ChName("系统日志类型")
        String CODE = "LOG_TYPE";
        @ChName("操作日志")
        int OPERATION = 0;
        @ChName("登录日志")
        int LOGIN = 1;
        @ChName("异常日志")
        int UNUSUAL = 2;
        @ChName("错误日志")
        int ERROR = 3;
        @ChName("测试日志")
        int TEST = 4;
    }

    public interface OPERATE_TYPE {
        @ChName("操作类型")
        String CODE = "OPERATE_TYPE";
        @ChName("新增操作")
        String ADD = "INSERT";
        @ChName("修改操作")
        String UPDATE = "UPDATE";
        @ChName("删除操作")
        String DELETE = "DELETE";
        @ChName("查询操作")
        String SEARCH = "SELECT";

    }

    /**
     * 功能访问权限限制
     */
    public interface ACTION_AUTH_LIMIT {
        @ChName("功能访问权限限制")
        String CODE = "ACTION_AUTH_LIMIT";
        @ChName("需要登录")
        int NEED_LOGIN = 0;
        @ChName("需要KEY")
        int NEED_KEY = 1;
        @ChName("需要登录和KEY")
        int NEED_LOGIN_AND_KEY = 2;
        @ChName("公开的")
        int OPEN = 3;
        @ChName("无")
        int NONE = 4;
    }


    /**
     * 等级
     */
    public interface LEVEL {
        @ChName("等级")
        String CODE = "LEVEL";
        @ChName("零级")
        int LEVEL_0 = 0;
        @ChName("一级")
        int LEVEL_1 = 1;
        @ChName("二级")
        int LEVEL_2 = 2;
        @ChName("三级")
        int LEVEL_3 = 3;
        @ChName("四级")
        int LEVEL_4 = 4;
        @ChName("五级")
        int LEVEL_5 = 5;
        @ChName("六级")
        int LEVEL_6 = 6;
        @ChName("七级")
        int LEVEL_7 = 7;
        @ChName("八级")
        int LEVEL_8 = 8;
        @ChName("九级")
        int LEVEL_9 = 9;
        @ChName("十级")
        int LEVEL_10 = 10;
    }

    /**
     * 按钮权限类型
     */
    public interface BUTTON_TYPE {
        @ChName("按钮权限类型")
        String CODE = "BUTTON_TYPE";
        @ChName("查看操作(view)")
        String VIEW = "view";
        @ChName("添加操作(add)")
        String ADD = "add";
        @ChName("编辑操作(edit)")
        String EDIT = "edit";
        @ChName("删除操作(delete)")
        String DELETE = "delete";
        @ChName("搜索操作(search)")
        String SEARCH = "search";
        @ChName("清空操作(clear)")
        String CLEAR = "clear";
        @ChName("导入操作(input)")
        String INPUT = "input";
        @ChName("禁用操作(disable)")
        String DISABLE = "disable";
        @ChName("启用操作(enable)")
        String ENABLE = "enable";
        @ChName("分配权限(editPerm)")
        String EDITPERM = "editPerm";
        @ChName("上传文件(upload)")
        String UPLOAD = "upload";
        @ChName("导出操作(export)")
        String EXPORT = "export";
        @ChName("设为默认(setDefault)")
        String SETDEFAULT = "setDefault";
        @ChName("其他操作(other)")
        String OTHER = "other";
    }

    /**
     * 处理状态
     */
    public interface DEAL_STATUS {
        @ChName("处理状态")
        String CODE = "DEAL_STATUS";
        @ChName("未处理")
        int UNSTART = 0;
        @ChName("处理中")
        int START = 1;
        @ChName("处理完成")
        int FINISH = 2;
        @ChName("处理失败")
        int FILED = 3;
    }

    /**
     * 反馈类型
     */
    public interface FEED_BACK_TYPE {
        @ChName("反馈类型")
        String CODE = "FEED_BACK_TYPE";
        @ChName("使用反馈")
        int USE = 0;
        @ChName("异常反馈")
        int ERROR = 1;
    }

    /**
     * 任务状态
     */
    public interface JOB_STATUS {
        @ChName("任务状态")
        String CODE = "JOB_STATUS";
        @ChName("未启动")
        int UNSTART = 0;
        @ChName("已启动")
        int STARTED = 1;
        @ChName("已暂停")
        int PAUSE = 2;
    }

    /**
     * 任务类型
     */
    public interface JOB_TYPE {
        @ChName("任务类型")
        String CODE = "JOB_TYPE";
        @ChName("系统任务")
        int SYSTEM = 1;
        @ChName("业务任务")
        int BUSIBESS = 2;
    }

    /**
     * 字典类型
     */
    public interface DICT_TYPE {
        @ChName("字典类型")
        String CODE = "DICT_TYPE";
        @ChName("字典目录")
        int LIST = 0;
        @ChName("字典内容")
        int CONTENT = 1;
    }

    /**
     * 授权类型,可选值包括authorization_code,password,refresh_token,implicit,client_credentials,
     */
    public interface AUTHORIZED_GRANT_TYPES {
        @ChName("授权类型")
        String CODE = "AUTHORIZED_GRANT_TYPES";
        @ChName("授权码模式")
        String AUTHORIZATION_CODE = "authorization_code";
        @ChName("密码模式")
        String PASSWORD = "password";
        @ChName("刷新TOKEN")
        String REFRESH_TOKEN = "refresh_token";
        @ChName("简化(隐式)模式")
        String IMPLICIT = "implicit";
        @ChName("客户端模式")
        String CLIENT_CREDENTIALS = "client_credentials";
    }

    /**
     * 数据回流周期类型
     */
    public interface DATA_RETURN_CYCLE_TYPE {
        @ChName("数据回流周期类型")
        String CODE = "DATA_RETURN_CYCLE_TYPE";
        @ChName("日")
        String DAY = "DAY";
        @ChName("周")
        String WEEK = "WEEK";
        @ChName("月")
        String MONTH = "MONTH";
        @ChName("年")
        String YEAR = "YEAR";
    }

    /**
     * 危废名录数据级别
     */
    public interface WASTE_BASE_LEVEL {
        @ChName("危废名录数据级别")
        String CODE = "WASTE_BASE_LEVEL";
        @ChName("父级")
        Integer PARENT = 1;
        @ChName("子集")
        Integer CHILD = 2;
    }

    /**
     * 废物种类
     */
    public interface WASTE_CATEGORY {
        @ChName("废物种类")
        String CODE = "WASTE_CATEGORY";
        @ChName("危废")
        Integer WASTE = 0;
        @ChName("固废")
        Integer SOLID = 1;
    }

    /**
     * 数据来源
     */
    public interface DATA_SOURCE {
        @ChName("数据来源")
        String CODE = "DATA_SOURCE";
        @ChName("省平台")
        String PROVINCIAL_PLATFORM = "0";
        @ChName("本平台")
        String SELF_PLATFORM = "1";
        @ChName("其他平台")
        String OTHER_PLATFORM = "9";
    }

    /**
     * 经营许可申报状态 （0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停）
     */
    public interface BUSINESS_LICENSE_STATE {
        @ChName("经营许可申报状态")
        String CODE = "BUSINESS_LICENSE_STATE";
        @ChName("填写")
        String ZERO = "0";
        @ChName("审核")
        String ONE = "1";
        @ChName("被退回")
        String TWO = "2";
        @ChName("审核完毕")
        String THREE = "3";
        @ChName("作废")
        String FOUR = "4";
        @ChName("续证")
        String FIVE = "5";
        @ChName("过期")
        String SIX = "6";
        @ChName("暂停")
        String SEVEN = "7";
    }

    /**
     * 处置方式类型
     */
    public interface WASTE_DISPOSAL_WAY_TYPE {
        @ChName("处置方式类型")
        String CODE = "WASTE_DISPOSAL_WAY_TYPE";
        @ChName("大类")
        String BROAD_HEADING = "0";
        @ChName("小类")
        String SUBCLASS = "1";
    }

    /**
     * 处置方式类型
     */
    public interface EQUIPMENT_SHEET_EQUIPMENT_TYPE {
        @ChName("设备确认单设备类型")
        String CODE = "EQUIPMENT_SHEET_EQUIPMENT_TYPE";
        @ChName("电子称")
        String ELECTRONIC_SCALE = "1";
        @ChName("叉车称")
        String FORKLIFT_SCALE = "2";
        @ChName("监控摄像头")
        String CAMERA = "3";
        @ChName("蓝牙打印机")
        String BLUETOOTH_PRINTER = "4";
        @ChName("打印纸")
        String PRINTING_PAPER = "5";
        @ChName("手持终端")
        String HANDHELD_TERMINAL = "6";
    }

    /**
     * 区域类型
     */
    public interface AREA_TYPE {
        @ChName("区域类型")
        String CODE = "AREA_TYPE";
        @ChName("镇")
        String CITY = "1";
        @ChName("区域")
        String AREA = "2";
        @ChName("村")
        String VILLAGE = "3";
    }
    /**
     * 岗位职级
     */
    public interface POSITION_LEVEL {
        @ChName("岗位职级")
        String CODE = "POSITION_LEVEL";

    }

    /**
     * 智能识别类型
     */
    public interface CAMERA_CAPTURE_TYPE {
        @ChName("智能识别类型")
        String CODE = "CAMERA_CAPTURE_TYPE";
        @ChName("路口违停")
        String INTERSECTION_VIOLATION = "1";
        @ChName("重点人员")
        String KEY_PERSONNEL = "2";
        @ChName("火点识别")
        String FIRE_POINT_IDENTIFICATION = "3";
        @ChName("人员进入")
        String PERSONNEL_ENTRY = "4";
        @ChName("重点车辆")
        String KEY_VEHICLES = "5";
    }

    /**
     * 智能识别目标
     */
    public interface RECOGNITION_TARGET {
        @ChName("智能识别目标")
        String CODE = "RECOGNITION_TARGET";
        @ChName("智能识别类型")
        String TYPE = "type";
        @ChName("人员")
        String PERSON = "person";
        @ChName("安全帽")
        String HAT = "hat";
        @ChName("工作服")
        String CLOTHES = "clothes";
    }

    /**
     * 告警事件来源
     */
    public interface ALARM_EVENT_SOURCE {
        @ChName("告警事件来源")
        String CODE = "ALARM_EVENT_SOURCE";
        @ChName("自动生成")
        String AUTO = "1";
        @ChName("手动生成")
        String MANUAL = "2";
    }
    /**
     * 角色标识
     */
    public interface ROlE_VALUE {
        @ChName("角色标识")
        String CODE = "ROlE_VALUE";
        @ChName("镇区工作人员")
        String TOWN_ROLE = "ZQGZRY";
        @ChName("管区工作人员")
        String DISTRICT_ROLE = "GQGZRY";
        @ChName("村工作人员")
        String VILLAGE_ROLE = "CGZRY";

    }
    /**
     * 考核评价-考核状态
     */
    public interface EXAMINE_STATUS {
        @ChName("考核评价-考核状态")
        String CODE = "EXAMINE_STATUS";
        @ChName("待反馈")
        Integer STAUTS0 = 0;
        @ChName("已反馈")
        Integer STAUTS1 = 1;
        @ChName("已核实")
        Integer STAUTS2 = 2;
        @ChName("已完成")
        Integer STAUTS3 = 3;
        @ChName("待分配")
        Integer STAUTS4 = 4;
    }
}
