package com.crcm.bpm.core.constant;

/**
 * @author Administrator
 */
public class FindUserTypeConstant {
    /**
     * 查询人员方式(1:用户选择器，2：固定人员，3：前端指定。4：角色。5：岗位，6：同指定节点人员，7：角色（同申请人部门）)
     */
    public static final Integer USER_SELECTOR = 1;

    public static final Integer REGULAR_STAFF = 2;

    public static final Integer FRONT_END_SPECIFIED = 3;

    public static final Integer ROLE = 4;

    public static final Integer POST = 5;

    public static final Integer SAME_AS_THE_SPECIFIED_NODE_PERSONNEL = 6;

    public static final Integer SAME_AS_THE_APPLY_USER_DEPARTMENT = 7;
}
