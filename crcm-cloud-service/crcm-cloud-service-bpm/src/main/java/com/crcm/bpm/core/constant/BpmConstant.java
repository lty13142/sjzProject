package com.crcm.bpm.core.constant;

/**
 * @ClassName BpmnConstant
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/22/16:12
 **/
public interface BpmConstant<assignTaskUserType> {
    int YES = 1;
    int NO = 0;

    // 流程部署状态 未部署
    int PROCESS_NOT_DEPLOY = 0;
    // 流程部署状态 已部署
    int PROCESS_DEPLOYED = 1;
    // 流程部署状态 有新版本，待部署
    int PROCESS_HAS_NEW_VERSION = 2;


    // 模型主版本
    int MAIN_VERSION = 1;
    // 非模型主版本
    int NOT_MAIN_VERSION = 0;
    // 节点指定人字段
    String ASSIGNEE_USER_EXP = "assignee";
    // 默认节点指定人字段
    String DEFAULT_ASSIGNEE_USER_EXP = "assigneeExp";

    // 分配任务用户方式字段
    String ASSIGN_TASK_USER_WAY = "assignMode";
    // 分配任务用户方式 自动指定
    int ASSIGN_TASK_USER_AUTO = 0;
    // 分配任务用户方式 手动指定
    int ASSIGN_TASK_USER_MANUAL = 1;


    /**
     * 申请状态 草稿
     */
    int APPLY_STATUS_DRAFT = 1;
    /**
     * 申请状态 审批
     */
    int APPLY_STATUS_APPROVING = 2;
    /**
     * 申请状态 通过
     */
    int APPLY_STATUS_PASS = 3;
    /**
     * 申请状态 驳回
     */
    int APPLY_STATUS_REJECT = 4;
    /**
     * 申请状态 失效
     */
    int APPLY_STATUS_DISABLE = 5;

    //    // 节点 找人方式
//    // 角色组
//     int FIND_USER_TYPE_BY_ROLE_GROUP = 1;
//    // 角色
//     int FIND_USER_TYPE_BY_ROLE = 2;
//    // 固定人员
//     int FIND_USER_TYPE_BY_USER = 3;
//    // 前端指定人员
//     int FIND_USER_TYPE_BY_DESIGNATED_PERSONNEL = 4;
//    // 同申请人
//     int FIND_USER_TYPE_BY_APPLYER = 5;
//    // 同节点人员
//     int FIND_USER_TYPE_BY_NODE_USER = 6;

    // 节点 找人方式
    // 选择器
    int FIND_USER_TYPE_BY_SELECTOR = 1;
    // 固定人员
    int FIND_USER_TYPE_BY_USER = 2;
    // 前端指定人员
    int FIND_USER_TYPE_BY_DESIGNATED_PERSONNEL = 3;
    // 角色
    int FIND_USER_TYPE_BY_ROLE = 4;
    // 岗位
    int FIND_USER_TYPE_BY_POST = 5;
    // 同节点人员
    int FIND_USER_TYPE_BY_NODE_USER = 6;
    // 角色加申请人部门
    int FIND_USER_TYPE_BY_ROLE_AND_DEPARTMENT = 7;

    // 用户选择器
    // 当前登录用户
    String USER_SELECTOR_CURRENT_USER = "current";
    // 流程发起人
    String USER_SELECTOR_PROMOTER = "promoter";
    // 上一步执行人
    String USER_SELECTOR_THE_LAST_USER = "theLastUser";
    // 部门负责人
    String USER_SELECTOR_DEPT_HEAD = "deptHead";
    // 分管领导
    String USER_SELECTOR_CHARGE_LEADER = "chargeLeader";


    String TASK_ID = "taskId";

    String FORM_DATA_LIST = "list";

    String SYSTEM = "system";

    String APPLY_SN = "applySn";

    String PALTFORM = "platform";

    String PROCESS_ID = "processId";

    String APPLY_ID = "applyId";

    String TENANT_ID = "tenantId";

    String APPLY_USER_INFO = "applyUserInfo";

    String AUTO_NODE = "autoNode";

    String APPLY_DEPT_ID = "applyDeptId";

    String APPLY_COMPANY_ID = "applyCompanyId";

    String APPLY_ROLE_ID = "applyRoleId";

    String APPLY_ROLE_CODE = "applyRoleCode";

    String APPLY_ROLE_NAME = "applyRoleName";

    String BUSINESS_LINE = "businessLine";

    String APPLY_DEPT = "applyDept";

    String FLOW_ADMIN = "flowAdmin";

    String APPROVE_ACTION = "approveAction";


    // 节点任务类型 发起
    String TASK_TYPE_START = "start";
    // 节点任务类型 审批
    String TASK_TYPE_APPROVE = "approve";
    // 节点任务类型 会签
    String TASK_TYPE_COUNTERSIGN = "countersign";

    String NODE_TYPE_USER_TASK = "UserTask";
    String DYNAMIC_KEY_LIST = "dynamicKeyList";
    String DYNAMIC_KEY = "dynamicKey";
    String DYNAMIC = "dynamic";
    String OPTIONS = "options";
    String FORMAT = "format";
    String IS_CHOOSE_TIMES = "isChooseTimes";
    String DISABLED = "disabled";

    String AUTO_COMPLETE_FIRST_NODE_KEY = "autoCompleteFirstNode";

    // 自动完成首个用户任务
    int AUTO_COMPLETE_FIRST_NODE = 1;
    // 不自动完成首个用户任务
    int NO_AUTO_COMPLETE_FIRST_NODE = 0;

    // 根据 角色组获取关联角色
    int ROLE_GROUP_TYPE_COMMON = 1;
    // 根据 条线及层级获取 角色组 关联角色
    int ROLE_GROUP_TYPE_SPECIAL = 2;

    String HISTORY_START = "发起";

    // 节点处理策略 skip: 执行人为空跳过,admin: 为空时管理员处理,error:为空时报错
    String HANDLER_STRATEGY_ERROR = "error";
    String HANDLER_STRATEGY_ADMIN = "admin";
    String HANDLER_STRATEGY_SKIP = "skip";

    // 审批方式 串行审批
    String COMPLETE_SEQUENTIAL = "sequential";
    // 审批方式 并行审批
    String COMPLETE_PARALLEL = "parallel";

    // 会签已完成实例数
    String COUNTERSIGN_COMPLETED_INSTANCES ="nrOfCompletedInstances";
    // 会签总实例数
    String COUNTERSIGN_INSTANCES ="nrOfInstances";


    // 表单数据key
    String FORM_DATA_KEY ="formData";
    String FORM_JSON_KEY ="formJson";
    String FORM_DATA_TYPE_INPUT = "input";
    String FORM_DATA_TYPE_TEXT = "text";
    String FORM_DATA_TYPE_NUMBER = "number";
    String FORM_DATA_TYPE_SELECT = "select";
    String FORM_DATA_TYPE_CASCADER = "cascader";
    String FORM_DATA_TYPE_CHECKBOX = "checkbox";
    String FORM_DATA_TYPE_RADIO = "radio";
    String FORM_DATA_TYPE_DATE = "date";
    String FORM_DATA_TYPE_TIME = "time";
    String FORM_DATA_TYPE_UPLOADFILE = "uploadfile";
    String FORM_DATA_TYPE_UPLOADIMG = "uploadImg";
    String FORM_DATA_TYPE_SWITCH = "switch";
    String FORM_DATA_TYPE_SLIDER = "slider";
    String FORM_DATA_TYPE_CHILDTABLE = "childtable";
    String FORM_DATA_TYPE_P = "p";
    String FORM_DATA_TYPE_SUPER = "super";
    String FORM_DATA_TYPE_BUTTON = "button";
    String FORM_DATA_TYPE_DIVIDER = "divider";
    String FORM_DATA_TYPE_CARD = "card";
    String FORM_DATA_TYPE_GRID = "grid";
    String FORM_DATA_TYPE_TABLE = "table";

    int SELECT_PATCH_TRUE = 1;
    int HANDLER_STRATEGY_FALSE = 0;

    /**
     * node_type并行ParallelGateway
     */
    String PARALLEL_GATEWAY = "ParallelGateway";

    /**
     * 流程表单填充userName
     */
    String PROCESS_USER_NAME = "${username}";

    /**
     * 流程表单填充companyName
     */
    String PROCESS_COMPANY_NAME = "${companyName}";

    /**
     * 流程表单填充departmentName
     */
    String PROCESS_DEPARTMENT_NAME = "${departmentName}";

    /**
     * 流程表单填充positionName
     */
    String PROCESS_POSITION_NAME = "${positionName}";

    /**
     * 流程表单填充replace-userName
     */
    String REPLACE_PROCESS_USER_NAME = "\\$\\{username\\}";

    /**
     * 流程表单填充replace-companyName
     */
    String REPLACE_PROCESS_COMPANY_NAME = "\\$\\{companyName\\}";

    /**
     * 流程表单填充replace-departmentName
     */
    String REPLACE_PROCESS_DEPARTMENT_NAME = "\\$\\{departmentName\\}";

    /**
     * 流程表单填充replace-positionName
     */
    String REPLACE_PROCESS_POSITION_NAME = "\\$\\{positionName\\}";

    /**
     * 流程表单填充replace-positionName-label-value
     */
    String REPLACE_PROCESS_POSITION_NAME_LABEL_VALUE = "\\[\\{\\\"label\\\"\\:\\\"\\$\\{positionName\\}\\\",\"value\\\"\\:\\\"\\$\\{positionName\\}\\\"\\}\\]";



    /**
     * 系统设置管理员code
     */
    String COMPANY_ADMINISTRATOR = "companyAdministrator";

    /**
     * 我的收藏
     */
    String MY_COLLECTION = "myCollection";

    // 申请节点
    int IS_APPLY_NODE = 1;
    // 非申请节点
    int NOT_APPLY_NODE = 0;
}

