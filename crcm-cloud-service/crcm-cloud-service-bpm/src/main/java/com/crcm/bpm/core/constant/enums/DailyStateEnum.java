package com.crcm.bpm.core.constant.enums;

import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/9/8 16:24 <br>
 * @Author: <a>bot</a>
 */
public enum  DailyStateEnum implements EnumUtil {

    SAVE(0,"保存"),
    COMMITED(1,"已提交"),
    COPY(2,"副本")
    ;

    private Integer value;

    private String desc;

    DailyStateEnum(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    }

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
