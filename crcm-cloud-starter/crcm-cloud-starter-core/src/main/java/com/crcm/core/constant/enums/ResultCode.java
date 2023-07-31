package com.crcm.core.constant.enums;

import cn.hutool.http.HttpStatus;

/**
 * @ClassName SystemStatusEnum
 * @Description 系统状态枚举
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
public enum ResultCode implements BaseStatusEnum {

    //登录
    ACCOUNT_LOCKED("登录失败，账户被锁定，请联系管理员进行解锁！", 40101),
    ACCOUNT_DISABLE("登录失败，账户被禁用，请联系管理员！", 40102),
    ACCOUNT_EXPIRED("登录失败，账户已过期！", 40103),
    PASSWORD_EXPIRED("登录失败，密码已过期！", 40104),
    USERNAME_PASSWORD_ERROR("登录失败，用户名或密码错误！", 40105),
    LOGIN_FAIL("登陆失败！", 40106),
    LOGIN_OUT_FAIL("登出失败！", 40107),
    USER_INFO_NO_FIND("用户信息不存在！", 40108),
    LOGIN_FAIL_LIMITED("登录错误次数超过限制！", 40109),
    OTHER_CLIENT_LOGIN_IN("其他人登录了这个账户", 40100),
    // 用户注册
    SMS_CODE_BAD("短信验证码无效", HttpStatus.HTTP_INTERNAL_ERROR),
    PHONE_ALREADY_USE("电话号已经注册过", HttpStatus.HTTP_INTERNAL_ERROR),

    //权限
    LOGIN_OUT_OF_DATE("登录过期，请重新登录！", 40101),
    PERMISSION_DENIED("访问被拒绝，您没有该操作的权限！", 40301),
    TOKEN_AUTH_FAILED("认证失败，请重新登录！", 40102),


    //用户
    USER_ALREADY_EXISTS("该用户已存在！", 40001),
    USER_NAME_CANNOT_BE_NULL("用户名不能为空", HttpStatus.HTTP_INTERNAL_ERROR),
    USER_NO_FOUNT("用户不存在！", 40401),
    USER_NO_PASSWORD("密码不能为空！", 40002),
    USER_NOT_PASSWORD("请使用8位以上数字、大小字母、特殊符号组合！", 40003),
    USER_NOT_PHONE("电话号码格式不正确！", 40004),
    USER_NOT_EMAIL("邮箱格式不正确！", 40005),
    USER_PWD_CHECK_FILD("旧密码不正确！", 40006),
    USER_PHONE_ALREADY_EXISTS("电话号码已存在！", 40409),


    // websocket
    SOCKET_USER_OFF_LINE("用户未在线", HttpStatus.HTTP_INTERNAL_ERROR),

    //系统操作
    CHANGE_ROLE_MENU_FILED("角色菜单权限修改失败！", HttpStatus.HTTP_INTERNAL_ERROR),
    CHANGE_ROLE_PERMISSION_FILED("角色系统操作权限修改失败！", HttpStatus.HTTP_INTERNAL_ERROR),
    //文件上传
    UPLOAD_NULL_FILE("上传失败，文件为空！", HttpStatus.HTTP_INTERNAL_ERROR),
    UPLOAD_FILE_FAILED("文件上传失败",HttpStatus.HTTP_INTERNAL_ERROR),
    FILE_MERGE_FAILED("合并文件失败",HttpStatus.HTTP_INTERNAL_ERROR),
    FILE_ABNORMAL_READING("文件读取异常",HttpStatus.HTTP_NOT_FOUND),
    UPLOAD_FILE_NAME_NULL("文件名称不能为空",HttpStatus.HTTP_INTERNAL_ERROR),
    //文件下载
    FILE_NO_FOUND("文件不存在！",HttpStatus.HTTP_INTERNAL_ERROR),

    // APP
    APK_NO_FIND("APK文件不存在", HttpStatus.HTTP_INTERNAL_ERROR),

    //API
    GENERATE_WITH_NULL_ID("生成失败，API ID为空", HttpStatus.HTTP_INTERNAL_ERROR),
    // 路由
    ROUTE_NOT_FOUND("错误，系统路由不存在", HttpStatus.HTTP_UNAVAILABLE),
    ROUTE_INIT_CONFLICT("错误，路由初始化冲突", HttpStatus.HTTP_INTERNAL_ERROR),

    SYSTEM_INNER_ERROR("系统内部错误，请稍后再试", HttpStatus.HTTP_INTERNAL_ERROR),

    //查询
    FIND_WITH_NULL_ID("id为空，查询失败！", HttpStatus.HTTP_BAD_REQUEST),
    FIND_WITH_METHOD("请求方式错误",HttpStatus.HTTP_INTERNAL_ERROR),
    // 修改
    EDIT_WITH_NULL_ID("id为空，操作失败！", HttpStatus.HTTP_BAD_REQUEST),

    ERROR("系统异常，操作失败！", HttpStatus.HTTP_INTERNAL_ERROR),

    NOT_IS_COMPANY("当前账号无权限，请登录企业账号！", HttpStatus.HTTP_INTERNAL_ERROR);


    public final int code;
    public final String msg;

    ResultCode(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
