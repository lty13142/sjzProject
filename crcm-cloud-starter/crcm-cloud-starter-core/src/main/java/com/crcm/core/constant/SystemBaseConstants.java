package com.crcm.core.constant;

/**
 * @ClassName CommonConstant
 * @Description 通用常量
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/20
 **/
public interface SystemBaseConstants {
    /**
     * 成功标记
     */
    int SUCCESS = 0;

    /**
     * 失败标记
     */
    int FAIL = 1;
    
    String BASE_PATH = "SJZ";

    /**
     * token请求头名称
     */
    String TOKEN_HEADER_NAME = "Authorization";

    /**
     * OAUTH2客户端中数据名称
     */
    String OAUTH_CLIENT_DATA = BASE_PATH+":OAUTH_CLIENT";

    /**
     * redis中字典数据名称
     */
    String REDIS_DICT_DATA = BASE_PATH+":DICT_DATA";
    /**
     * redis中区域数据名称
     */
    String REDIS_AREA_DATA = BASE_PATH+":AREA_DATA";
    /**
     * 默认系统超级管理员角色
     */
    String SUPER_ADMIN_USER_ROLE = "ROLE_SUPER_ADMIN";
    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";
    /**
     * 编码
     */
    String UTF8 = "UTF-8";
    /**
     * 网关标识值
     */
    String GATEWAY_TOKEN_VALUE = "crcm-gateway-request";
    /**
     * 网关标识头
     */
    String GATEWAY_TOKEN_HEADER = "Gateway";

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = BASE_PATH+":CAPTCHA:";
    /**
     * 验证码有效期(秒)
     */
    int CODE_TIME = 60;

    /**
     * 验证码长度
     */
    String CODE_SIZE = "4";


    /**
     * 路由存放
     */
    String ROUTE_KEY = BASE_PATH+":GATEWAY_ROUTE";
    /**
     * 默认头像在设置中的code
     */
    String SYSTEM_DEFAULT_AVATAR_KEY = "SYSTEM_DEFAULT_AVATAR";
    /**
     * 默认密码在设置中的code
     */
    String SYSTEM_DEFAULT_PASSWORD = "SYSTEM_DEFAULT_PASSWORD";
    /**
     * 密码错误超出限定次数锁定时间在设置中的code
     */
    String PASSWORD_ERROR_LOCK_TIME = "PASSWORD_ERROR_LOCK_TIME";
    /**
     * 密码错误限定次数在设置中的code
     */
    String PASSWORD_ERROR_LIMIT_COUNT = "PASSWORD_ERROR_LIMIT_COUNT";
    /**
     * 默认密码
     */
    String SYSTEM_DEFAULT_PASSWORD_VALUE = "Sjz415236@";
    /**
     * 危废名录最新版本号
     */
    String WASTE_LIST_NEW_VERSION = "WASTE_LIST_NEW_VERSION";
    /**
     * 省平台数据库回流Token
     */
    String PROVINCIAL_PLATFORM_RETURN_TOKEN = "PROVINCIAL_PLATFORM_RETURN_TOKEN";
    /**
     * 省平台数据库回流Api地址
     */
    String PROVINCIAL_PLATFORM_RETURN_API = "PROVINCIAL_PLATFORM_RETURN_API";
    /**
     * header 中租户ID
     */
    String TENANT_HEADER = "TENANT-ID";

    /**
     * header 中版本信息
     */
    String VERSION_HEADER = "VERSION";

    /**
     * 租户ID
     */
    Integer TENANT_ID_1 = 1;

    /**
     * 验证码redis key
     */
    String VERIFICATION_CODE = BASE_PATH + ":verification_code";
    /**
     * 菜单redis key
     */
    String MENU_REDIS_KEY = BASE_PATH + ":MENU";
    /**
     * 系统设置redis key
     */
    String SYSTEM_SETTINGS_REDIS_KEY = BASE_PATH + ":SETTINGS";

    /**
     * 微信token_key
     */
    String WECHAT_ACCESS_TOKEN_KEY = BASE_PATH + "weChat_token_key";
}
