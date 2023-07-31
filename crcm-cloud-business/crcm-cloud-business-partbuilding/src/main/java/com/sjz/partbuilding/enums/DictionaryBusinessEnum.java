package com.sjz.partbuilding.enums;

/**
 * 字典业务枚举类
 *
 * @author rmc
 * @version 1.0
 * @date 2020/9/24 10:03
 */
public enum DictionaryBusinessEnum {

    /**
     * 组织类型
     */
    ORG_CATEGORY("ORG_CATEGORY"),
    /**
     * 支部类别
     */
    BRANCH_TYPE("BRANCH_TYPE"),

    /**
     * 性别
     */
    SEX("SEX"),

    /**
     * 组织隶属关系
     */
    ORG_SUBORDINATION("ORG_SUBORDINATION"),
    /**
     * 政治面貌
     */
    POLITICAL("POLITICAL"),


    /**
     * 学历
     */
    EDUCATION("EDUCATION"),

    /**
     * 党内职务类型
     */
    ORG_POST_TYPE("ORG_POST_TYPE"),

    /**
     * 工会职务类型
     */
    G_ORG_POST_TYPE("G_ORG_POST_TYPE");

    public final String code;

    DictionaryBusinessEnum(String code) {
        this.code = code;
    }
}
