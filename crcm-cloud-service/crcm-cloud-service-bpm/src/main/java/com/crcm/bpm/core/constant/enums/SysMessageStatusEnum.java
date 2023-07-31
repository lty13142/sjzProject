package com.crcm.bpm.core.constant.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/9/28 11:08 <br>
 * @Author: <a>bot</a>
 */
public enum SysMessageStatusEnum implements IEnum, EnumUtil {

    UNREAD(0,"未读"),
    READ(1,"已读")
    ;

    SysMessageStatusEnum(Integer value, String desc){
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
