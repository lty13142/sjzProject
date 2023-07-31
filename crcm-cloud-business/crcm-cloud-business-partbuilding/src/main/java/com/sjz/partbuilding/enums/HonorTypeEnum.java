package com.sjz.partbuilding.enums;

public enum HonorTypeEnum {

    COMMUNITY(0, "集体荣誉"),
    PERSON(1, "个人荣誉");

    private Integer code;
    private String value;

    HonorTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCodeByValue(String value) {
        HonorTypeEnum[] enums = HonorTypeEnum.values();
        for (HonorTypeEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        HonorTypeEnum[] enums = HonorTypeEnum.values();
        for (HonorTypeEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
