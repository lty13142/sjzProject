package com.crcm.bpm.core.constant.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/10/9 09:52 <br>
 * @Author: <a>bot</a>
 */
public enum DailyContentTypeEnum implements IEnum,EnumUtil {

    CURRENT_WEEK(0,"本周工作总结"),
    NEXT_WEEKLY(1,"下周工作计划");

    DailyContentTypeEnum(Integer value, String desc){
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
