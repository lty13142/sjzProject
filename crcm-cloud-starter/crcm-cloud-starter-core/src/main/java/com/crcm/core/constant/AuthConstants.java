package com.crcm.core.constant;

/**
 * @ClassName SecurityConstants
 * @Description 系统权限常量
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/5 17:52
 **/
public interface AuthConstants {
    /**
     * 系统内部调用请求头标志
     */
    String FROM = "from";
    /**
     * 内部
     */
    String FROM_IN = "Y";


    String ROLE_PREFIX = "ROLE_";

    /**
     * 授权码获取code登录页面URL{@value}
     */
    String LOGIN_PAGE = "/authorize/login";

    /**
     * 认证成功URL{@value}
     */
    String LOGIN_PROCESS_URL = "/authorize/login/process";

    /**
     * 获取授权码后返回URL{@value}
     */
    String AUTHORIZE_CODE = "/authorize/code";

    /**
     * 自定义授权页面URL{@value}
     */
    String CONFIRM_ACCESS = "/auth/confirm_access";

    /**
     * 资源服务器默认bean名称
     */
    String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";



    /**
     * 表单用户名{@value}
     */
    String USERNAME_PARAMETER_NAME = "username";

    /**
     * 表单密码{@value}
     */
    String PASSWORD_PARAMETER_NAME = "password";

    /**
     * 图片验证码{@value}
     */
    String VERIFICATION_CODE_COMMON = "verification_code";

    /**
     * 验证码缓存过期时间
     */
    Long CAPTCHA_CACHE_EXPIRE_TIME = 60L;

    /**
     * 图片验证码唯一键 {@value}
     */
    String VERIFICATION_CODE_KEY_PARAMETER_NAME = "key";

    /**
     * 用户类型 {@value}
     */
    String USER_TYPE = "user_type";

    /**
     * 手机号 {@value}
     */
    String AUTH_PHONE = "phoneNumber";

    /**
     * 手机号验证码 {@value}
     */
    String AUTH_CODE = "phoneCode";

    /**
     * 微信唯一ID {@value}
     */
    String AUTH_WECHAT_UNIONID = "unionId";

    /**
     * 前缀
     */
    String PROJECT_PREFIX = SystemBaseConstants.BASE_PATH + ":";

    /**
     * oauth 相关前缀
     */
    String OAUTH_PREFIX = "OAUTH:";

    /**
     * 刷新
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * token
     */
    String ACCESS_TOKEN = "access_token";

    /**
     * OAUTH URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * 手机号登录URL
     */
    String SMS_TOKEN_URL = "/user/login/sms";
}
