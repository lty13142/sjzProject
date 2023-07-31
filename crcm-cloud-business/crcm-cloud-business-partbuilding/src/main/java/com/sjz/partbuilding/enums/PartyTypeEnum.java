package com.sjz.partbuilding.enums;

import lombok.Getter;

@Getter
public enum PartyTypeEnum {

    CCP_MEMBER(0, "党员"),
    PROBATIONARY(1, "预备党员"),
    ACTIVIST(2, "入党积极分子");

    private Integer code;
    private String value;

    PartyTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCodeByValue(String value) {
        PartyTypeEnum[] enums = PartyTypeEnum.values();
        for (PartyTypeEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        PartyTypeEnum[] enums = PartyTypeEnum.values();
        for (PartyTypeEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
