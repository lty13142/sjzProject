package com.crcm.security.constants;


/**
 * @ClassName EnableDynamicAutEnum
 * @Description token 信息常量
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/13
 **/
public enum EnableDynamicAutEnum {
    /**
     * 开启动态权限投票
     */
    ENABLE_DYNAMIC_AUTH("enable_dynamic_auth","AUTHORITIES_"),

    /**
     * 开启动态Scope投票
     */
    ENABLE_DYNAMIC_SCOPE_AUTH("enable_dynamic_scope_auth","SCOPE_"),
    ;

    private final String name;

    private final String prefix;

    EnableDynamicAutEnum(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
