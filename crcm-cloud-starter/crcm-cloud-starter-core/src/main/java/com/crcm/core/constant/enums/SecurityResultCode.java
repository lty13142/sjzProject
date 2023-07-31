package com.crcm.core.constant.enums;

import cn.hutool.http.HttpStatus;

/**
 * @ClassName SecurityErrorEnum
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/24
 **/
public enum SecurityResultCode implements BaseStatusEnum {

    //登录
    AUTHENTICATION_FAILED(HttpStatus.HTTP_UNAUTHORIZED, "认证失败"),
    TOKEN_EXPIRED(401013, "刷新令牌已过期，请重新登录"),
    AUTHENTICATION_NOT_SUPPORTED(401012, "不支持该认证类型"),
    SCOPE_INVALID(401011, "不是有效的scope值"),
    REDIRECT_URI_ERROR(401010, "redirect_uri值不正确"),
    ILLEGAL_CLIENT(40109, "client值不合法"),
    ILLEGAL_RESPONSE_TYPE(40108, "response_type值不合法"),
    INVALID_REFRESH_TOKEN(40107, "refresh token无效"),
    ILLEGAL_AUTHENTICATION_CODE(40106, "授权码不合法"),

    ACCOUNT_DISABLE(40105, "账户被禁用，请联系管理员！"),
    ACCOUNT_LOCKED(40104, "账户已被锁定，请联系管理员"),
    ACCOUNT_EXPIRED(40103, "登录失败，账户已过期！"),
    PASSWORD_EXPIRED(40102, "登录失败，密码已过期！"),
    USERNAME_OR_PASSWORD_NOT_MATCH(40101, "用户名不存在或者密码错误"),
    USERNAME_PASSWORD_ERROR(40101, "登录失败，用户名不存在或者密码错误！"),
    LOGIN_FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "登陆失败！"),
    USER_INFO_NO_FIND(HttpStatus.HTTP_UNAUTHORIZED, "用户信息不存在！"),
    LOGIN_FAIL_LIMITED(HttpStatus.HTTP_UNAUTHORIZED, "登录错误次数超过限制！"),
    OTHER_CLIENT_LOGIN_IN(HttpStatus.HTTP_UNAUTHORIZED, "其他人登录了这个账户"),
    QUIT_LOGIN_FAILED(HttpStatus.HTTP_INTERNAL_ERROR, "退出登陆失败！"),
    LOGIN_OUT_OF_DATE(HttpStatus.HTTP_FORBIDDEN, "登录过期，请重新登录！"),

    //用户
    USER_ALREADY_EXISTS(HttpStatus.HTTP_INTERNAL_ERROR, "该用户已存在！"),
    USER_NAME_CANNOT_BE_NULL(HttpStatus.HTTP_INTERNAL_ERROR, "用户名不能为空"),
    USER_NO_FOUNT(HttpStatus.HTTP_INTERNAL_ERROR, "用户不存在！"),
    USER_NO_PASSWORD(HttpStatus.HTTP_INTERNAL_ERROR, "密码不能为空！"),
    USER_NOT_PASSWORD(HttpStatus.HTTP_INTERNAL_ERROR, "密码过于简单,以字母开头，长度在6~18之间，只能包含字母、数字和下划线！"),
    USER_NOT_PHONE(HttpStatus.HTTP_INTERNAL_ERROR, "手机号码不匹配！"),
    USER_NOT_EMAIL(HttpStatus.HTTP_INTERNAL_ERROR, "邮箱格式不正确！"),
    USER_PWD_CHECK_FILD(HttpStatus.HTTP_INTERNAL_ERROR, "密码校验失败！"),

    //权限
    PERMISSION_DENIED(HttpStatus.HTTP_FORBIDDEN, "访问被拒绝，您没有该操作的权限！"),
    TOKEN_AUTH_FAILED(HttpStatus.HTTP_FORBIDDEN, "认证失败，请重新登录！"),
    HTTP_FORBIDDEN(HttpStatus.HTTP_FORBIDDEN, "授权失败，禁止访问")
    ;

    private int code;
    private String msg;

    SecurityResultCode(int code, String msg) {
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
