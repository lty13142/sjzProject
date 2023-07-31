package com.crcm.bpm.core.constant;

/**
 * @ClassName TaskConstant
 * @Description：任务常量
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/9
 **/
public interface TaskConstant {
    // 任务状态 未完成
    int TASK_STATUS_NOT_DONE = 0;
    // 任务状态 已完成
    int TASK_STATUS_HAS_DONE = 1;

    // 未认领
    int TASK_UN_CLAIM = 1;
    // 已认领
    int TASK_CLAIM = 2;
    // 已完成
    int TASK_COMPLETED = 3;
    // 已取消
    int TASK_DISABLE = 4;
    // 找不到节点人员，系统自动完成
    int TASK_AUTO_COMPLETE = 5;
    // 挂起
    int TASK_HANG = 7;

    // 节点指定人字段
    String ASSIGNEE_USER_EXP = "assignee";
    // 默认节点指定人集合字段
    String DEFAULT_ASSIGNEE_LIST_EXP = "assigneeList";
    // 默认节点指定人名称集合字段
    String DEFAULT_ASSIGNEE_NAME_LIST_EXP = "assigneeNameList";
    // 默认节点指定人字段
    String DEFAULT_ASSIGNEE_USER_EXP = "assigneeExp";
    // 默认节点指定人名称字段
    String DEFAULT_ASSIGNEE_USER_NAME_EXP = "assigneeNameExp";

    // 前端选定节点指定人集合字段
    String CHECKED_ASSIGNEE_LIST_EXP = "checkedAssigneeList";
    //  前端选定节点指定人名称集合字段
    String CHECKED_ASSIGNEE_NAME_LIST_EXP = "checkedAssigneeNameList";

    // 节点委托人字段
    String OWNER_USER_EXP = "owner";
    // 节点委托人名称字段
    String OWNER_USER_NAME_EXP = "ownerName";

    String NODE_ID = "nodeId";
    String NODE_NAME = "nodeName";
    String NODE_USERS = "nodeUsers";
    String SIGN_TYPE = "signType";
    String SIGN_TYPE_BEFORE = "before";
    String SIGN_TYPE_AFTER = "after";

    // 审批动作
    String APPROVE_ACTION_DESC = "approveAction";
    // 通过
    String APPROVE_ACTION_PASS = "pass";
    // 驳回
    String APPROVE_ACTION_REJECT = "reject";
    // 中止流程
    String APPROVE_ACTION_FAIL = "fail";
    // 撤销
    String APPROVE_ACTION_CANCEL = "cancel";
    // 会签
    String APPROVE_ACTION_COUNTER_SIGN = "countersign";
    // 加签
    String APPROVE_ACTION_ADD_SIGN = "addSign";
    // 退回
    String APPROVE_ACTION_RETURN = "return";
    // 自由退回
    String APPROVE_ACTION_RANDOM_RETURN = "randomReturn";
    // 新增临时节点
    String APPROVE_ACTION_ADD_TEMP_NODE = "addTempNode";
    // 撤回
    String APPROVE_ACTION_RECALL = "recall";
    // 挂起
    String APPROVE_TASK_HANG = "taskHang";
    // 抄送
    String APPROVE_COPY_SEND = "copySend";
    // 转派
    String APPROVE_REASSIGNMENT = "reassignment";


    // 任务节点类型
    String TASK_ACTION_TYPE = "taskType";
    // 任务节点类型 发起
    String TASK_ACTION_START = "start";
    // 任务节点类型 审核
    String TASK_ACTION_APPROVE = "approve";
    // 任务节点类型 会签
    String TASK_ACTION_COUNTER_SIGN = "countersign";
    // 会签状态 会签流程结束判断字段
    String COUNTERSIGN_STATUS_KEY = "countersignStatus";
    // 会签通过总数
    String COUNTERSIGN_PASS_COUNT = "passCount";
    // 会签未通过总数
    String COUNTERSIGN_NO_PASS_COUNT = "noPassCount";
    // 会签多实例完成数量
    String NR_OF_COMPLETED_INSTANCES = "nrOfCompletedInstances";
    // 会签多实例总数
    String NR_OF_INSTANCES = "nrOfInstances";

    // 会签是否全部处理
    String COUNTERSIGN_WHOLEDEAL = "wholeDeal";
    // 会签通过跳转
    String COUNTERSIGN_PASSSKIP = "passSkip";
    // 会签未通过跳转
    String COUNTERSIGN_NOPASSSKIP = "noPassSkip";
    // 会签审批结果
    String COUNTERSIGN_APPROVERESULT = "approveResult";
    // 会签通过比例
    String COUNTERSIGN_PROPORTION = "proportion";
    // 会签 一票否决
    String COUNTERSIGN_ONE_BALLOT_VETO = "oneBallotVeto";

    //  审批结果
    String APPROVE_RESULT_DESC = "approveResult";
    // 审批意见
    String APPROVE_OPINION_DESC = "approveOpinion";

    // 会签节点流转方向
    String COUNTERSIGN_OUTCOME_DESC = "outcome";
    // 会签节点流转方向 下一步
    String COUNTERSIGN_OUTCOME_PASS = "pass";
    // 会签节点流转方向 上一步
    String COUNTERSIGN_OUTCOME_REJECT = "reject";
    // 会签节点流转方向 终止
    String COUNTERSIGN_OUTCOME_STOP = "stop";
    // 节点跳转 下一节点
    int TASK_SKIP_NEXT_NODE = 1;
    // 节点跳转 上一节点
    int TASK_SKIP_LAST_NODE = 2;
    // 节点跳转 终止流程
    int TASK_SKIP_STOP = 3;

    /**
     * 节点审核人指派方式，0:自动指派
     */
    String ASSIGN_MODE_AUTO = "0";

    /**
     * 节点审核人指派方式 1:手动指派
     */
    String ASSIGN_MODE_MANUAL = "1";

    /**
     * 任务id
     */
    String TASK_ID = "taskId";

    /**
     * 任务id
     */
    String RETURN_NODE_ID = "returnNodeId";

    /**
     * 指定审核人员存储key
     */
    String ASSIGNEE_DATA = "assigneeData";

    // 抄送状态 未读
    int COPY_STATUS_NOT_ERAD = 1;
    // 抄送状态 已读
    int COPY_STATUS_HAS_READ = 2;
}
