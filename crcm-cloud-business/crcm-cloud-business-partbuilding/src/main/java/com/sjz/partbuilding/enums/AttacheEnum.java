package com.sjz.partbuilding.enums;

public enum AttacheEnum {

    CENTER(1, "中央"),
    PROVINCE(2, "省（市、区）"),
    CITY(3, "市（地、州、盟）"),
    COUNTY(4, "县（市、区、旗）"),
    CITY_STREET(5, "城市街道"),
    COUNTRY(6, "乡"),
    TOWN(7, "镇"),
    URBAN_COMMUNITY(8, "城市社区（居委会）"),
    VILLAGE_COMMUNITY(9, "乡镇社区（居委会）"),
    ORGANIC_VILLAGE(10, "建制村"),
    RURAL_COMMUNITY(11, "农村社区");

    private Integer code;
    private String value;

    AttacheEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCodeByValue(String value) {
        AttacheEnum[] enums = AttacheEnum.values();
        for (AttacheEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        AttacheEnum[] enums = AttacheEnum.values();
        for (AttacheEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
