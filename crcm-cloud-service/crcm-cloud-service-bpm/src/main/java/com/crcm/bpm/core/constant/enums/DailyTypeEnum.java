package com.crcm.bpm.core.constant.enums;

import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/9/7 11:25 <br>
 * @Author: <a>bot</a>
 */
public enum DailyTypeEnum implements EnumUtil {

    DAILY(0,"今日日报"),
    WEEKLY(1,"本周周报");

    DailyTypeEnum(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    }

    private Integer value;

    private String desc;

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
