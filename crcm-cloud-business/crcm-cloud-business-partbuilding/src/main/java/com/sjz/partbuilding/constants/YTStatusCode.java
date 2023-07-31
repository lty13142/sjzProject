package com.sjz.partbuilding.constants;

/**
 * @ClassName：YTStatusCodeConstants
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/11/25
 **/
public interface YTStatusCode {

    // 登录认证失败状态码
    int BAD_REQUEST = 400;
    // 登录认证失败状态码
    int AUTHENTICATION_FAILED = 401;
    //用户被锁定
    int LOCKED = 402;
    // 权限认证失败状态码
    int PERMISSION_FAILED = 403;
    // 系统内部错误状态码
    int INTERNAL_SYSTEM_ERROR = 500;
    // 资源不存在状态码
    int NOT_FOUND = 404;

    // 非法的令牌
    int ILLEGAL_TOKEN = 50008;
    // 单点登录错误码
    int SINGLE_LOGIN = 50012;
    // token过期
    int TOKEN_TIMEOUT = 50014;
    // socket连接错误
    int SOCKET_ERROR = 50020;
    // 非法的令牌
}
