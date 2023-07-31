package com.crcm.bpm.core.constant.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.crcm.bpm.utils.EnumUtil;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/9/10 15:05 <br>
 * @Author: <a>bot</a>
 */
public enum DailyBusinessTypeEnum implements EnumUtil, IEnum {

    PERSONAL_COMMIT(0, "个人提交"),
    DEPARTMENT_SUMMARY(1, "部门汇总"),
    COMPANY_SUMMARY(2, "公司汇总"),
    PARENT_COMPANY_SUMMARY(3, "公司汇总"),
    ;

    private Integer value;

    private String desc;

    DailyBusinessTypeEnum(Integer value, String desc) {
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
