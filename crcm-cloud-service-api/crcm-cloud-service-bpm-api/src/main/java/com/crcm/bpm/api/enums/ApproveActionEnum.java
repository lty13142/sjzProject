package com.crcm.bpm.api.enums;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/2 18:21 <br>
 * @Author: <a>bot</a>
 */
public enum ApproveActionEnum {

    APPROVE_ACTION_PASS("pass", "通过"),
    APPROVE_ACTION_REJECT("reject", "驳回"),
    APPROVE_ACTION_FAIL("fail", "中止流程"),
    APPROVE_ACTION_CANCEL("cancel", "撤销"),
    APPROVE_ACTION_COUNTER_SIGN("countersign", "会签"),
    APPROVE_ACTION_ADD_SIGN("addSign", "加签"),
    APPROVE_ACTION_RETURN("return", "退回"),
    APPROVE_ACTION_RANDOM_RETURN("randomReturn", "自由退回"),
    APPROVE_ACTION_ADD_TEMP_NODE("addTempNode", "新增临时节点");

    ApproveActionEnum(String value, String desc) {
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
