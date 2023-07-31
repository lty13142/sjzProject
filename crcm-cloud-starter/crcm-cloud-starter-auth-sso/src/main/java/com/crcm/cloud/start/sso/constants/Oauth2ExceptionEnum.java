package com.crcm.cloud.start.sso.constants;


import org.springframework.http.HttpStatus;

/**
 * @ClassName Oauth2ExceptionEnum
 * @Description oauth2异常枚举
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/13
 **/
public enum Oauth2ExceptionEnum {

    /**
     * 用户未登录
     */
    USER_NOT_LOGGED_IN("请登陆后再进行访问", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    /**
     * 无效TOKEN
     */
    INVALID_AUTHENTICATION("Token无效", HttpStatus.UNAUTHORIZED.value()),
    /**
     * 权限被拒绝
     */
    PERMISSION_DENIED("权限不足，操作被拒绝", HttpStatus.FORBIDDEN.value()),
    /**
     * 客户端登录异常
     */
    CLIENT_LOGIN_ERR("客户端异常", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    /**
     * 未知OAUTH认证异常
     */
    OAUTH_EXCEPTION("未知OAUTH认证异常", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    /**
     * 刷新token异常
     */
    REFRESH_TOKEN_ERR("刷新Token异常", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    /**
     * 无效的TOKEN
     */
    INVALID_TOKEN("无效的令牌", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 权限被拒绝
     */
    USER_PERMISSION_DENIED("权限不足，操作被拒绝", HttpStatus.FORBIDDEN.value()),

    /**
     * 密码错误
     */
    PASSWORD_ERR("用户名或密码错误", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 用户名或密码为空
     */
    USERNAME_OR_PASSWORD_NULL("用户名或密码为空", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 用户名为空
     */
    USERNAME_NULL("用户名为空", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 无效的刷新令牌
     */
    INVALID_GRANT_REFRESH_TOKEN("无效的刷新令牌", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 无效的授权码
     */
    INVALID_GRANT_AUTHORIZATION_CODE("无效的授权码", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND("用户不存在", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERR("验证码错误", HttpStatus.BAD_REQUEST.value()),

    /**
     * 验证码为空
     */
    VERIFICATION_CODE_EMPTY("验证码为空", HttpStatus.BAD_REQUEST.value()),

    /**
     * 验证码唯一键为空
     */
    VERIFICATION_CODE_UNICODE_KEY_EMPTY("验证码唯一键为空", HttpStatus.BAD_REQUEST.value()),

    /**
     * 手机验号不能为空
     */
    PHONE_NULL("手机验号不能为空", HttpStatus.BAD_REQUEST.value()),

    /**
     * 手机验证码为空
     */
    PHONE_VERIFICATION_CODE_NULL("手机验证码为空", HttpStatus.BAD_REQUEST.value()),

    /**
     * 手机验证码错误或已过期
     */
    PHONE_VERIFICATION_CODE_ERR_OR_EXPIRE("手机验证码错误或已过期", HttpStatus.BAD_REQUEST.value()),

    /**
     * 无效的授予
     */
    INVALID_GRANT("无效的授予", HttpStatus.UNAUTHORIZED.value()),
    /**
     * 无效的作用域
     */
    INVALID_SCOPE("无效的作用域", HttpStatus.UNAUTHORIZED.value()),

    /**
     * 客户端不存在
     */
    CLIENT_NOT_FOUND("客户端不存在", HttpStatus.NOT_FOUND.value()),

    /**
     * clientId 为空
     */
    CLIENT_ID_EMPTY("clientId为空", HttpStatus.BAD_REQUEST.value()),

    /**
     * 作用域规则为空
     */
    SCOPE_EMPTY("作用域规则为空", HttpStatus.BAD_REQUEST.value()),

    /**
     * 微信unionId为空
     */
    UNIONID_EMPTY("微信unionId为空", HttpStatus.BAD_REQUEST.value()),
    ;


    private String msg;
    private int code;

    Oauth2ExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
