package com.crcm.bpm.api.enums;

import java.util.Arrays;

public enum CustomFormEnum {

    FROM_TEST("test", "测试", "com.crcm.bpm.core.execute.TestExecute");

    CustomFormEnum(String value, String desc, String javaClass){
        this.value = value;
        this.desc = desc;
        this.javaClass = javaClass;
    }

    private String value;

    private String desc;

    private String javaClass;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getJavaClass() {
        return javaClass;
    }

    public static String getJavaClassByValue(String value) {
        if (value == null) {
            return "";
        }
        CustomFormEnum[] enumAry = CustomFormEnum.values();
        for(int i = 0; i < Arrays.asList(enumAry).size(); i++){
            if (enumAry[i].getValue().equals(value)) {
                return enumAry[i].getJavaClass();
            }
        }
        return "";
    }
}
