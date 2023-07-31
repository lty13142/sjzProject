package com.crcm.core.constant.enums;

/**
 * @ClassName UserType
 * @Description 用户类型
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/25
 **/
public enum UserType {
    // 管理平台用户
    ADMIN("admin"),
    // 企业用户
    ENTERPRISE("enterprise"),
    // 供应商
    SUPPLIER("supplier"),
    // 服务商
    SERVICES("services");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
