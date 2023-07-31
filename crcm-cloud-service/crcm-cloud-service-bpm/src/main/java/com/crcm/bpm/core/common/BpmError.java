package com.crcm.bpm.core.common;

import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Bpmn流程异常类
 * @Date 2020.9.21
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class BpmError implements Serializable {

    private static final long serialVersionUID = 1147559618212462501L;
    private int code;
    private String message;

    private static final int HTTP_INTERNAL_ERROR = HttpStatus.HTTP_INTERNAL_ERROR;

    public static final BpmError SUCCESS = new BpmError(200, "success");
    public static final BpmError ILLEGAL_ARGUMENT_ERROR = new BpmError(400, "参数非法");
    public static final BpmError ILLEGAL_LOGIN_ERROR = new BpmError(401, "Unauthorized");
    public static final BpmError ILLEGAL_ACCESS_ERROR = new BpmError(403, "ILLEGAL ACCESS ERROR");
    public static final BpmError ILLEGAL_PATH_ERROR = new BpmError(404, "请求路径不存在！");
    public static final BpmError SYSTEM_ERROR = new BpmError(500, "系统错误");
    public static final BpmError ILLEGAL_CHECK_ERROR = new BpmError(400, "参数校验不通过");
    public static final BpmError DATA_NOT_FOUND_ERROR = new BpmError(501, "数据不存在");
    public static final BpmError USERNAME_OR_PASSWORD_ERROR = new BpmError(502, "用户名或密码出错！");
    public static final BpmError USER_NO_FIND_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "用户不存在！");

    public static final BpmError UNPUBLISH_PROCESS_DETAIL_DEFAULT_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "未发布流程详细不允许设置为默认版本，请先发布！");
    public static final BpmError UNPUBLISH_PROCESS_START_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "不允许发起未发布流程！");
    public static final BpmError SUBMIT_REPET_APPLY_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "请勿重复提交单据！");
    public static final BpmError SAVE_REPET_APPLY_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "数据已提交，请勿重复保存草稿！");
    public static final BpmError TASK_HAS_COMPLETED = new BpmError(HTTP_INTERNAL_ERROR, "当前任务已完成，请刷新页面！");
    public static final BpmError NEXT_TASK_HAS_COMPLETED = new BpmError(HTTP_INTERNAL_ERROR, "当前任务已被下一审批人审批或认领！无法撤回！");
    public static final BpmError TASK_CLAIM_BY_OTHER = new BpmError(HTTP_INTERNAL_ERROR, "当前任务已被其他人认领，请刷新页面！");
    public static final BpmError MODEL_NO_FIND_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "模型不存在！");
    public static final BpmError PROCESS_NO_FIND_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "流程不存在！");
    public static final BpmError MODEL_NO_VALIDATED_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "模型格式验证失败！");
    public static final BpmError MODEL_DEPLOY_FAILED_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "模型格式部署失败！");
    public static final BpmError PROCESS_KEY_NO_FIND = new BpmError(HTTP_INTERNAL_ERROR, "流程KEY不存在！");
    public static final BpmError PROCESS_KEY_ALREADY_DEFINED = new BpmError(HTTP_INTERNAL_ERROR, "流程KEY已存在！");
    public static final BpmError FORM_NO_FIND_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "流程表单不存在！");
    public static final BpmError APPLY_NO_FIND_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "流程申请不存在！");
    public static final BpmError USER_TASK_NO_FIND_ERROR = new BpmError(HTTP_INTERNAL_ERROR, "用户任务不存在！");
    public static final BpmError USER_TASK_NO_FIND_COPY = new BpmError(HTTP_INTERNAL_ERROR, "抄送接收人，不能为空！");
    public static final BpmError USER_APPLY_DELETE_LIMIT = new BpmError(HTTP_INTERNAL_ERROR, "流程审核中，不能删除！");
    public static final BpmError PROCESS_DELETE = new BpmError(HTTP_INTERNAL_ERROR, "业务模型在使用中，无法删除! 请先删除业务模型!");
    public static final BpmError NO_EQUIL_APPLY_USER = new BpmError(HTTP_INTERNAL_ERROR, "您不是流程发起人!");
    public static final BpmError PARALLEL_CANNOT_BE_WITHDRAWN = new BpmError(HTTP_INTERNAL_ERROR, "并行节点不能撤回!");
    public static final BpmError PROCESS_NOT_FINISH = new BpmError(HTTP_INTERNAL_ERROR, "流程未通过，不能引用!");

    public BpmError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
