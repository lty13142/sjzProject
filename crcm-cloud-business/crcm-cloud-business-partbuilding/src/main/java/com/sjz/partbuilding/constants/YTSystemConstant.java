package com.sjz.partbuilding.constants;


import com.crcm.core.annotation.ChName;

/**
 * @ClassName：YTSystemConstant
 * @Description：系统常量类，查询是根据CODE查询
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：Administrator
 * @Date：2019/12/12
 **/
public class YTSystemConstant {

    /**
     * 启用状态
     */
    public interface ENABLE_STATUS {
        @ChName("启用状态")
        String CODE = "ENABLE_STATUS";
        @ChName("未启用")
        String UN_ENABLE = "0";
        @ChName("已启用")
        String ENABLE = "1";
        @ChName("已停用")
        String DISABLE = "-1";
    }

    /**
     * 是或否
     */
    public interface YES_OR_NO {
        @ChName("是或否")
        String CODE = "YES_OR_NO";
        @ChName("是")
        String YES = "1";
        @ChName("否")
        String NO = "0";
    }


    /**
     * 用户类型
     */
    public interface USER_TYPE {
        @ChName("用户类型")
        String CODE = "USER_TYPE";
        @ChName("主系统管理员")
        String MAIN_ADMIN = "0";
        @ChName("子系统管理员")
        String SON_ADMIN = "1";
        @ChName("普通用户")
        String NORMAL_USER = "2";
    }

    /**
     * 用户状态
     */
    public interface USER_STATUS {
        @ChName("用户状态")
        String CODE = "USER_STATUS";
        @ChName("不在线")
        String OFF_LINE = "0";
        @ChName("在线")
        String ON_LINE = "1";
    }

    /**
     * 锁定状态
     */
    public interface LOCK_STATUS {
        @ChName("锁定状态")
        String CODE = "LOCK_STATUS";
        @ChName("未锁定")
        String UN_LOCK = "0";
        @ChName("已锁定")
        String LOCKED = "1";
    }

    /**
     * 开启状态
     */
    public interface OPEN_STATUS {
        @ChName("开启状态")
        String CODE = "OPEN_STATUS";
        @ChName("关闭")
        String OFF = "0";
        @ChName("开启")
        String ON = "1";
    }

    /**
     * 文件上传方式
     */
    public interface UPLOAD_MODE {
        @ChName("文件上传方式")
        String CODE = "UPLOAD_MODE";
        @ChName("普通上传")
        String NORMAL = "0";
        @ChName("FTP上传")
        String FTP = "1";
        @ChName("Minio上传")
        String Minio = "2";
    }

    /**
     * 角色类型
     */
    public interface ROLE_TYPE {
        @ChName("角色类型")
        String CODE = "ROLE_TYPE";
        @ChName("普通")
        String NORMAL = "0";
        @ChName("管理员")
        String ADMIN = "1";
    }

    /**
     * 组织类型
     */
    public interface ORG_TYPE {
        @ChName("组织类型")
        String CODE = "ORG_TYPE";
        @ChName("组织")
        String ORG = "0";
        @ChName("部门")
        String DEPT = "1";
        @ChName("岗位")
        String JOB = "2";
    }

    /**
     * 菜单类型
     */
    public interface MENU_TYPE {
        @ChName("菜单类型")
        String CODE = "MENU_TYPE";
        @ChName("菜单")
        String MENU = "0";
        @ChName("按钮")
        String BUTTON = "1";
    }

    /**
     * 权限类型
     */
    public interface PERMISSION_TYPE {
        @ChName("权限类型")
        String CODE = "PERMISSION_TYPE";
        @ChName("菜单权限")
        String MENU = "0";
        @ChName("数据（功能）权限")
        String ACTION = "1";
    }

    /**
     * 功能类型
     */
    public interface ACTION_TYPE {
        @ChName("功能类型")
        String CODE = "ACTION_TYPE";
        @ChName("功能")
        String ACTION = "0";
        @ChName("目录")
        String LIST = "1";
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
        @ChName("支付回调")
        String WE_CHAR_PAY = "weCharPay";
    }

    /**
     * 系统日志类型
     */
    public interface LOG_TYPE {
        @ChName("系统日志类型")
        String CODE = "LOG_TYPE";
        @ChName("操作日志")
        String OPERATION = "0";
        @ChName("登录日志")
        String LOGIN = "1";
        @ChName("异常日志")
        String UNUSUAL = "2";
        @ChName("错误日志")
        String ERROR = "3";
        @ChName("测试日志")
        String TEST = "4";
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
        String NEED_LOGIN = "0";
        @ChName("需要KEY")
        String NEED_KEY = "1";
        @ChName("需要登录和KEY")
        String NEED_LOGIN_AND_KEY = "2";
        @ChName("公开的")
        String OPEN = "3";
        @ChName("无")
        String NONE = "4";
    }

    /**
     * 等级
     */
    public interface LEVEL {
        @ChName("等级")
        String CODE = "LEVEL";
        @ChName("零级")
        String LEVEL_0 = "0";
        @ChName("一级")
        String LEVEL_1 = "1";
        @ChName("二级")
        String LEVEL_2 = "2";
        @ChName("三级")
        String LEVEL_3 = "3";
        @ChName("四级")
        String LEVEL_4 = "4";
        @ChName("五级")
        String LEVEL_5 = "5";
        @ChName("六级")
        String LEVEL_6 = "6";
        @ChName("七级")
        String LEVEL_7 = "7";
        @ChName("八级")
        String LEVEL_8 = "8";
        @ChName("九级")
        String LEVEL_9 = "9";
        @ChName("九级")
        String LEVEL_10 = "10";
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
        String UNSTART = "0";
        @ChName("处理中")
        String START = "1";
        @ChName("处理完成")
        String FINISH = "2";
        @ChName("处理失败")
        String FILED = "3";
    }

    /**
     * 反馈类型
     */
    public interface FEED_BACK_TYPE {
        @ChName("反馈类型")
        String CODE = "FEED_BACK_TYPE";
        @ChName("使用反馈")
        String USE = "0";
        @ChName("异常反馈")
        String ERROR = "1";
    }

    /**
     * 任务状态
     */
    public interface JOB_STATUS {
        @ChName("任务状态")
        String CODE = "JOB_STATUS";
        @ChName("未启动")
        String UNSTART = "0";
        @ChName("已启动")
        String STARTED = "1";
        @ChName("已暂停")
        String PAUSE = "2";
    }

    /**
     * 任务类型
     */
    public interface JOB_TYPE {
        @ChName("任务类型")
        String CODE = "JOB_TYPE";
        @ChName("系统任务")
        String SYSTEM = "1";
        @ChName("业务任务")
        String BUSIBESS = "2";
    }

    /**
     * 发布类别
     */
    public interface NOTIFY_NEWS_TYPE {
        @ChName("发布类别")
        String CODE = "NOTIFY_NEWS_TYPE";
        @ChName("未发布")
        String BUSIBESS = "0";
        @ChName("已发布")
        String SYSTEM = "1";

    }

    /**
     * 1、中央2、省（市、区）3、市（地、州、盟）4、县（市、区、旗）5、城市街道 " +
     * "6、乡 7、镇 8、城市社区（居委会）9、乡镇社区（居委会）10、建制村 11、农村社区
     */
    public interface RELATION_TYPE {
        @ChName("组织隶属关系类型")
        String CODE = "RELATION_TYPE";
        @ChName("中央")
        String CENTER = "1";
        @ChName("省（市、区）")
        String PROVINCE = "2";
        @ChName("市（地、州、盟）")
        String CITY = "3";
        @ChName("县（市、区、旗）")
        String COUNTY = "4";
        @ChName("城市街道")
        String STREET = "5";
        @ChName("乡")
        String VILLAGE = "6";
        @ChName("镇")
        String TOWN = "7";
        @ChName("城市社区（居委会）")
        String CCOMMUNITY = "8";
        @ChName("乡镇社区（居委会）")
        String TCOMMUNITY = "9";
        @ChName("建制村")
        String ORGANIC = "10";
        @ChName("农村社区")
        String COUNTRYSIDE = "11";
    }

    /**
     * 0：无 1：差额 2：等额 3：直接选举 4：上级任命 5：其他
     */
    public interface ELECT_TYPE {
        @ChName("选举方式")
        String CODE = "ELECT_TYPE";
        @ChName("无")
        String NONE = "0";
        @ChName("差额")
        String DIFFERENCE = "1";
        @ChName("等额")
        String EQUAL = "2";
        @ChName("直接选举")
        String DIRECT = "3";
        @ChName("上级任命")
        String APPOINT = "4";
        @ChName("其他")
        String OTHER = "5";
    }

    /**
     *  0集体荣誉  1 个人荣誉
     */
    public interface HONOR_TYPE {
        @ChName("荣誉类别")
        String CODE = "HONOR_TYPE";
        @ChName("集体荣誉")
        String GROUP = "0";
        @ChName("个人荣誉")
        String PERSONAL = "1";
    }

    /**
     *  学习计划类型
     */
    public interface LEARN_TYPE {
        @ChName("荣誉类别")
        String CODE = "HONOR_TYPE";
        @ChName("专项计划")
        String SPECIAL = "0";
        @ChName("月计划")
        String MONTH = "1";
        @ChName("个人计划")
        String PERSONAL = "2";
    }

    /**
     * 首页党员年龄分布
     */
    public interface AGE {
        @ChName("年龄分布")
        String CODE = "AGE";
        @ChName("25岁以下")
        Integer mix = 0;
        @ChName("25岁")
        Integer TWENTY_FIVE = 25;
        @ChName("30岁")
        Integer THIRTY = 30;
        @ChName("40岁")
        Integer FORTY = 40;
        @ChName("50岁")
        Integer FIFTY = 50;
        @ChName("60岁")
        Integer SIXTY = 60;
        @ChName("60岁以上")
        Integer NINETY_NINE = 99;
    }

    /**
     * 首页党龄分布
     */
    public interface PARTY_AGE {
        @ChName("年龄分布")
        String CODE = "PARTY_AGE";
        @ChName("5年以下")
        Integer mix = 0;
        @ChName("5年")
        Integer five = 5;
        @ChName("10年")
        Integer ten = 10;
        @ChName("15年")
        Integer FIFTEEN = 15;
        @ChName("20年")
        Integer TWENTY = 20;
        @ChName("25年")
        Integer TWENTY_FIVE = 25;
        @ChName("25年以上")
        Integer NINETY_NINE = 99;
    }

    /**
     * 完成情况
     */
    public interface COMPLETE_TYPE {
        @ChName("完成情况")
        String CODE = "COMPLETE_TYPE";
        @ChName("已完成")
        String COMPLETED = "已完成";
        @ChName("未完成")
        String IN_COMPLETE = "未完成";
    }

    /**
     * 超时情况
     */
    public interface OVER_TIME_TYPE {
        @ChName("超时情况")
        String CODE = "OVER_TIME_TYPE";
        @ChName("已超时")
        String TIMED_OUT = "已超时";
        @ChName("未超时")
        String NO_TIME_OUT = "未超时";
    }

    /**
     * 活动类型
     */
    public interface ORG_EVENT_TYPE {
        @ChName("活动类型")
        String CODE = "ORG_EVENT_TYPE";
        @ChName("党员大会")
        String CONGRESS_OF_PARTY = "0";
        @ChName("支委会")
        String BRANCH_COMMITTEE = "1";
        @ChName("党小组会")
        String PARTY_GROUP_MEETING = "2";
        @ChName("党课")
        String PARTY_CLASS = "3";
        @ChName("主题党日活动")
        String  THEME_PARTY_DAY = "4";
        @ChName("组织生活会")
        String  ORG_LIFE_MEETING = "5";
    }

    /**
     * 新闻通知或公告
     */
    public interface NOTIFY_OR_ANNOUNCEMENT {
        @ChName("新闻通知或公告")
        String CODE = "NEWS_OR_ANNOUNCEMENT";
        @ChName("新闻通知")
        String NOTIFY = "0";
        @ChName("公告")
        String ANNOUNCEMENT = "1";
    }

    /**
     * 党建费用明细类型
     */
    public interface PAYMENTS_TYPE {
        @ChName("党建费用明细类型")
        String CODE = "PAYMENTS_TYPE";
        @ChName("收入")
        String IN = "0";
        @ChName("支出")
        String OUT = "1";
    }
}
