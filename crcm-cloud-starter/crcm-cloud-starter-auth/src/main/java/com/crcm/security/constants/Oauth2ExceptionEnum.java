package com.crcm.security.constants;

import cn.hutool.http.HttpStatus;

/**
 * @ClassName Oauth2ExceptionEnum
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/13
 **/
public enum  Oauth2ExceptionEnum {

    /**
     * 用户未登录
     */
    USER_NOT_LOGGED_IN("请登陆后再进行访问", HttpStatus.HTTP_UNAUTHORIZED),
    /**
     * 无效TOKEN
     */
    INVALID_AUTHENTICATION("Token无效", HttpStatus.HTTP_UNAUTHORIZED),
    /**
     * 权限被拒绝
     */
    PERMISSION_DENIED("权限不足，操作被拒绝", HttpStatus.HTTP_FORBIDDEN),
    /**
     * 客户端登录异常
     */
    CLIENT_LOGIN_ERR("客户端异常", HttpStatus.HTTP_INTERNAL_ERROR),
    /**
     * 未知OAUTH认证异常
     */
    OAUTH_EXCEPTION("未知OAUTH认证异常", HttpStatus.HTTP_INTERNAL_ERROR),
    /**
     * 刷新token异常
     */
    REFRESH_TOKEN_ERR("刷新Token异常", HttpStatus.HTTP_INTERNAL_ERROR),
    /**
     * 无效的TOKEN
     */
    INVALID_TOKEN("无效的令牌", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 权限被拒绝
     */
    USER_PERMISSION_DENIED("权限不足，操作被拒绝", HttpStatus.HTTP_FORBIDDEN),

    /**
     * 密码错误
     */
    PASSWORD_ERR("用户名或密码错误", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 用户名或密码为空
     */
    USERNAME_OR_PASSWORD_NULL("用户名或密码为空", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 用户名为空
     */
    USERNAME_NULL("用户名为空", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 无效的刷新令牌
     */
    INVALID_GRANT_REFRESH_TOKEN("无效的刷新令牌", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 无效的授权码
     */
    INVALID_GRANT_AUTHORIZATION_CODE("无效的授权码", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND("用户不存在", HttpStatus.HTTP_INTERNAL_ERROR),

    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERR("验证码错误", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 验证码为空
     */
    VERIFICATION_CODE_EMPTY("验证码为空", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 验证码唯一键为空
     */
    VERIFICATION_CODE_UNICODE_KEY_EMPTY("验证码唯一键为空", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 手机验号不能为空
     */
    PHONE_NULL("手机验号不能为空", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 手机验证码为空
     */
    PHONE_VERIFICATION_CODE_NULL("手机验证码为空", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 手机验证码错误或已过期
     */
    PHONE_VERIFICATION_CODE_ERR_OR_EXPIRE("手机验证码错误或已过期", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 无效的授予
     */
    INVALID_GRANT("无效的授予", HttpStatus.HTTP_UNAUTHORIZED),

    /**
     * 客户端不存在
     */
    CLIENT_NOT_FOUND("客户端不存在", HttpStatus.HTTP_NOT_FOUND),

    /**
     * clientId 为空
     */
    CLIENT_ID_EMPTY("clientId为空", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 作用域规则为空
     */
    SCOPE_EMPTY("作用域规则为空", HttpStatus.HTTP_BAD_REQUEST),

    /**
     * 微信unionId为空
     */
    UNIONID_EMPTY("微信unionId为空", HttpStatus.HTTP_BAD_REQUEST),
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
