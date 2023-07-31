package com.crcm.bpm.core.constant.enums;

import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/9/9 11:21 <br>
 * @Author: <a>bot</a>
 */
public enum DailySumTypeEnum implements EnumUtil {


    DEPARTMENT_SUMMARY(0,"部门汇总"),
    COMPANY_SUMMARY(1,"公司汇总"),
    PARENT_COMPANY_SUMMARY(2,"总公司汇总"),
    ;

    DailySumTypeEnum(Integer value,String desc){
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
