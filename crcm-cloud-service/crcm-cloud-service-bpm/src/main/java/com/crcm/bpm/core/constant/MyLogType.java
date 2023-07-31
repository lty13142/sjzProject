package com.crcm.bpm.core.constant;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/11 15:44 <br>
 * @Author: <a>bot</a>
 */
public enum MyLogType {

    FORM("form", "表单"),
    MODEL("model", "模板");

    MyLogType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;

    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
