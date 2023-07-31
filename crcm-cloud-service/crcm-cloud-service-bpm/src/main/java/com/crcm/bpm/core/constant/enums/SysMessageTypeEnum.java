package com.crcm.bpm.core.constant.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/9/25 17:11 <br>
 * @Author: <a>bot</a>
 */
public enum SysMessageTypeEnum implements IEnum, EnumUtil {

    WORK_TO_DO(1,"待办工作"),
    WORK_TO_READ(2,"待阅"),
    URGENT_TASKS(3,"紧急任务提醒")
    ;

    SysMessageTypeEnum(Integer value, String desc){
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
