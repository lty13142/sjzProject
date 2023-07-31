package com.sjz.partbuilding.enums;

import lombok.Getter;

@Getter
public enum MemberScoreTypeEnum {

    ADD(1, "加分"),
    SUBTRACT(2, "减分");

    private Integer code;
    private String value;

    MemberScoreTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCodeByValue(String value) {
        MemberScoreTypeEnum[] enums = MemberScoreTypeEnum.values();
        for (MemberScoreTypeEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        MemberScoreTypeEnum[] enums = MemberScoreTypeEnum.values();
        for (MemberScoreTypeEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
