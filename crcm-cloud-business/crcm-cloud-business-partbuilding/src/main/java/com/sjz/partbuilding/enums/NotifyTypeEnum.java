package com.sjz.partbuilding.enums;

import lombok.Getter;

@Getter
public enum NotifyTypeEnum {

    NEWS(1, "新闻"),
    NOTIFY(2, "通知");

    private Integer code;
    private String value;

    NotifyTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCodeByValue(String value) {
        NotifyTypeEnum[] enums = NotifyTypeEnum.values();
        for (NotifyTypeEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        NotifyTypeEnum[] enums = NotifyTypeEnum.values();
        for (NotifyTypeEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
