package com.sjz.partbuilding.enums;


/**
 * @author rmc
 * @version 1.0
 * @date 2020/7/24 9:57
 */
public enum StatusCode {

    APP_WHETHER_EXISTS("1"),

    /**
     * 直属地属支部number
     */
    DIRECTLY_BRANCH_NUMBER("01"),
    GROUND_GENUS_BRANCH_NUMBER("02"),
    GROUND_BRANCH_NUMBER("03"),//团支部
    LABOUR_UNION("04"),//工会

    PARTY_BRANCH("2"),

    /**
     * 男
     */
    MALE("0"),
    /**
     * 女
     */
    FEMALE("1"),

    PARTY_PERSON("0"),
    PREPARE_PARTY("1"),
    ENTRY_PARTY("2"),
    PREPARE_APPLY("3"),


    DAY_LAST_SECOND(" 23:59:59");


    public final String code;

    StatusCode(String code) {
        this.code = code;
    }
}
