package com.sjz.partbuilding.enums;


import com.sjz.partbuilding.constants.YTStatusCode;

/**
 * @ClassName：YTSystemErrorEnum
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/11/25
 **/
public enum YTSystemStatusEnum {
    //登录
    ACCOUNT_LOCKED("登录失败，账户被锁定，请联系管理员进行解锁！", YTStatusCode.LOCKED),
    ACCOUNT_DISABLE("登录失败，账户被禁用，请联系管理员！", YTStatusCode.AUTHENTICATION_FAILED),
    ACCOUNT_EXPIRED("登录失败，账户已过期！", YTStatusCode.AUTHENTICATION_FAILED),
    PASSWORD_EXPIRED("登录失败，密码已过期！", YTStatusCode.AUTHENTICATION_FAILED),
    USERNAME_PASSWORD_ERROR("登录失败，用户名或密码错误！", YTStatusCode.AUTHENTICATION_FAILED),
    LOGIN_FAIL_LIMITED("登录错误次数超过限制！", YTStatusCode.AUTHENTICATION_FAILED),
    OTHER_CLIENT_LOGIN_IN("其他人登录了这个账户", YTStatusCode.SINGLE_LOGIN),
    ORG_LOGIN_IN("工会用户，无法登录！", YTStatusCode.PERMISSION_FAILED),

    //用户
    USER_ALREADY_EXISTS("该用户已存在！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_NAME_CANNOT_BE_NULL("用户名不能为空", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_NO_FOUNT("系统错误，用户不存在！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_NO_PASSWORD("密码不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_NOT_PASSWORD("密码过于简单,以字母开头，长度在6~18之间，只能包含字母、数字和下划线！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_NOT_PHONE("电话号码格式不正确！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_NOT_EMAIL("邮箱格式不正确！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    USER_PWD_CHECK_FILD("旧密码错误，请重新输入！", YTStatusCode.AUTHENTICATION_FAILED),
    USER_RESET_PASSWORD("两次输入密码不相同！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_USER_NUMBER("用户编号重复，请重新输入！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //权限
    LOGIN_OUT_OF_DATE("登录过期，请重新登录！", YTStatusCode.TOKEN_TIMEOUT),
    PERMISSION_DENIED("访问被拒绝，您没有该操作的权限！", YTStatusCode.PERMISSION_FAILED),
    TOKEN_AUTH_FAILED("认证失败，请重新登录！", YTStatusCode.ILLEGAL_TOKEN),

    // websocket
    SOCKET_USER_OFF_LINE("用户未在线", YTStatusCode.SOCKET_ERROR),

    //系统操作
    CHANGE_ROLE_MENU_FILED("角色菜单权限修改失败！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    CHANGE_ROLE_PERMISSION_FILED("角色系统操作权限修改失败！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //文件上传
    UPLOAD_NULL_FILE("上传失败，文件为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    UPLOAD_FILE_SIZE_TOO_BIG("上传文件过大，不能超过10MB！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    UPLOAD_FILE_FILED("文件上传失败", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //文件下载
    FILE_NO_FOUND("文件不存在！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    // APP
    APK_NO_FIND("APK文件不存在", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //发文管理
    SEND_FILE_NOTNULL("接收人为空,请重试！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //党员申请
    APPLICATION_OF_PARTY_MEMBERS("请勿重复申请！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //API
    GENERATE_WITH_NULL_ID("生成失败，API ID为空", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //查询支付订单状体
    WX_SUCCESS("该订单已支付,请勿重复点击！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    ORDER_IS_NULL_ERROR("不存在该数据，请重试！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    WX_ERROR("支付错误！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //角色管理验证
    ROLE_NAME_IS_NULL("角色名称不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    AUTHORIZED_SIGNS_IS_NULL("授权标识不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_ROLE_NAME("角色名称重复，请重新输入！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_AUTHORIZED_SIGNS("授权标识重复，请重新输入！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    IS_NOT_PARTY_PERSON("您的身份目前不是党员！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //组织管理验证
    ORG_NAME_IS_NULL("组织名称不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    ORG_CODE_IS_NULL("组织代码不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_ORG_NAME("组织代码重复，请重新输入！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_ORG_CODE("组织代码重复，请重新输入！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //支委会管理职位验证
    POSITIONS_WITHIN_THE_PARTY("党内职务不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_POSITIONS_WITHIN_THE_PARTY("党内职务重复，请重新输入！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    PARENT_ORGANIZATION("上级组织不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //新闻通知
    NOTIFY_ACCEPT_IS_NULL("参会人员不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    //党员验证
    PARTY_ORG_ID_IS_NULL("党组织不能为空！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    REPEAT_SECRETARY("书记已存在，请勿重复添加！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    PROMPT_TIME_OUT("您距离上次提醒还未超过一天,请", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //工作反馈
    WORK_FEEDBACK_NO("抱歉，发布范围内没有您的组织！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    WORK_FEEDBACK__GOT("你当前所在组织已经反馈了！", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //党建经费
    PARTY_PAYMENTS_OUT("当前新增支出金额已超过结余金额！",YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //领导班子成员
    LEADER_EXISTS("该班子成员已存在！",YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //新增验证
    TITLE_LENGTH_CROSS("标题长度过长，请重试！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    //新增验证
    EXPERECE_EXISTS("该教案存在心得体会！请先删除心得体会", YTStatusCode.INTERNAL_SYSTEM_ERROR),

    //公司
    COMPANY_EXISTS("该公司或下属公司所属组织的成员不为空，请先清空对应组织成员！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    COMPANY_EXISTS1("该公司存在对应组织，请先删除对应组织！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    COMPANY_UPDATE("该公司的上级公司不能修改为该公司自己及其所属子公司，请重新选择上级公司！", YTStatusCode.INTERNAL_SYSTEM_ERROR),
    COMPANY_IS_NULL("该组织所关联的公司已删除！", YTStatusCode.INTERNAL_SYSTEM_ERROR);

    public final int code;
    public final String desc;

    YTSystemStatusEnum(String desc, int code) {
        this.code = code;
        this.desc = desc;
    }
}
