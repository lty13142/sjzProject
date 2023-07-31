package com.sjz.partbuilding.constants;

/**
 * @ClassName YTConstant
 * @Description：基础常量
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/5/10 17:16
 **/
public interface YTBaseConstant {

    String SYSTEM_NAME = "后台管理系统";

    /**
     * 系统应用名称
     */
    String SERVER_NAME = "SERVER";

    /**
     * 系统数据存入redis名称
     */
    String REDIS_DATA_NAME = "SYSTEM_DATA";

    /**
     * 系统用户存入token的键
     */
    String REDIS_TOKEN_NAME = "USER_TOKEN";

    /**
     * 系统用户存入token的键
     */
    String REDIS_TOKEN_USER = "TOKEN_USER";
    /**
     * 系统用户存入token的键
     */
    String REDIS_TOKEN_REFRESH = "TOKEN_REFRESH";

    /**
     * 系统登录信息缓存键
     */
    String REDIS_LOGIN_DATA = "LOGIN_DATA";

    /**
     * 系统登录信息缓存键
     */
    String SINGLE_LOGIN_CACHE = "SINGLE_LOGIN_CACHE";

    /**
     * 系统登录错误次数缓存键
     */
    String REDIS_LOGIN_FAIL_TIME = "LOGIN_FAIL_TIME";

    /**
     * 系统登录锁定状态缓存键
     */
    String REDIS_LOGIN_LOCKED = "LOGIN_LOCKED";
    /**
     * 系统配置数据
     */
    String REDIS_SYSTEM_LIST_DATA = "SYSTEM_LIST_DATA";
    /**
     * 多系统通用数据
     */
    String REDIS_SYSTEM_COMMON_DATA = "SYSTEM_COMMON_DATA";
    /**
     * redis中用户数据名称
     */
    String REDIS_DATA_USER = "USER_DATA";
    /**
     * redis中用户数据名称
     */
    String REDIS_DATA_USER_ROLE = "USER_ROLE_DATA";
    /**
     * redis中菜单数据名称
     */
    String REDIS_DATA_MENU = "MENU_DATA";
    /**
     * redis中角色数据名称
     */
    String REDIS_DATA_ROLE = "ROLE_DATA";
    /**
     * redis中字典列表数据名称
     */
    String REDIS_DATA_DIC_CODE = "DIC_CODE_DATA";
    /**
     * redis中字典内容数据名称
     */
    String REDIS_DATA_DIC_CONTENT = "DIC_CONTENT_DATA";

    String REDIS_VALIDATE_CODE_KEY = "VALIDATE_CODE";

    String SUPER_ADMIN_USER_ROLE = "ROLE_SUPER_ADMIN";



}
