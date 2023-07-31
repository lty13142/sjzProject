package com.sjz.partbuilding.enums;

import lombok.Getter;

@Getter
public enum PublishTypeEnum {

    UNPUBLISHED(0, "未发布"),
    PUBLISHED(1, "已发布");

    private Integer code;
    private String value;

    PublishTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCodeByValue(String value) {
        PublishTypeEnum[] enums = PublishTypeEnum.values();
        for (PublishTypeEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        PublishTypeEnum[] enums = PublishTypeEnum.values();
        for (PublishTypeEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
