package com.crcm.bpm.core.components;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.service.UserTaskService;
import com.crcm.core.utils.SpringContextHolder;
import com.crcm.security.utils.SecurityUtil;
import org.flowable.engine.delegate.DelegateExecution;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName MultiInstanceCompleteTask
 * @Description：多实例会签执行判断
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/12
 **/
public class MultiInstanceCompleteTask implements Serializable {
    /**
     * 评估结果判定条件
     *
     * @param execution 分配执行实例
     */
    public boolean accessCondition(DelegateExecution execution) throws Exception {
        String activityId = execution.getCurrentActivityId();
        //已完成的实例数
        int completedInstance = Convert.toInt(execution.getVariable(TaskConstant.NR_OF_COMPLETED_INSTANCES), 0);
        // 会签总人数
        int nrOfInstances = Convert.toInt(execution.getVariable(TaskConstant.NR_OF_INSTANCES), 0);
        Map<String, Object> variables = execution.getVariables();
        // 同意总数
        int passCount = Convert.toInt(execution.getVariable(activityId + TaskConstant.COUNTERSIGN_PASS_COUNT), 0);
        if (passCount == 0) { // 兼容历史审核
            passCount = Convert.toInt(execution.getVariable(TaskConstant.COUNTERSIGN_PASS_COUNT), 0);
        }
        // 不同意总数
        int noPassCount = Convert.toInt(execution.getVariable(activityId + TaskConstant.COUNTERSIGN_NO_PASS_COUNT), 0);
        if (noPassCount == 0) { // 兼容历史审核
            noPassCount = Convert.toInt(execution.getVariable(TaskConstant.COUNTERSIGN_NO_PASS_COUNT), 0);
        }
        boolean wholeDeal = Convert.toBool(execution.getVariable(TaskConstant.COUNTERSIGN_WHOLEDEAL), false);
        int proportion = Convert.toInt(execution.getVariable(TaskConstant.COUNTERSIGN_PROPORTION), 100);
        String approveResult = Convert.toStr(execution.getVariable(TaskConstant.APPROVE_RESULT_DESC));
        // 一票否决数量
        int oneBallotVeto = Convert.toInt(TaskConstant.COUNTERSIGN_ONE_BALLOT_VETO, 0);

        String passSkip = Convert.toStr(execution.getVariable(TaskConstant.COUNTERSIGN_PASSSKIP), String.valueOf(TaskConstant.TASK_SKIP_NEXT_NODE));
        String noPassSkip = Convert.toStr(execution.getVariable(TaskConstant.COUNTERSIGN_NOPASSSKIP), String.valueOf(TaskConstant.TASK_SKIP_LAST_NODE));


        if (TaskConstant.APPROVE_ACTION_PASS.equals(approveResult)) {
            execution.setVariable(activityId + TaskConstant.COUNTERSIGN_PASS_COUNT, ++passCount);
        } else {
            execution.setVariable(activityId + TaskConstant.COUNTERSIGN_NO_PASS_COUNT, ++noPassCount);
        }

        // 判断是否设置全部执行
        if (wholeDeal) {
            //所有实例任务未全部做完则继续其他实例任务
            if (completedInstance != nrOfInstances) {
                return false;
            }
            // 一票否决
            else if (oneBallotVeto > 0) {
                //输出方向为拒绝
                setNextStep(execution, Integer.parseInt(noPassSkip));
                return true;
            }
            // 全部执行
            else {
                // 通过比例大于等于设置的通比例
                if ((double) passCount / nrOfInstances >= (double) proportion / 100) {
                    //输出方向为同意
                    setNextStep(execution, Integer.parseInt(passSkip));
                } else {
                    //输出方向为拒绝
                    setNextStep(execution, Integer.parseInt(noPassSkip));
                }
                return true;
            }
        }
        // 不用全部执行
        else {
            // 通过比例大于等于设置的通比例
            if ((double) passCount / nrOfInstances >= (double) proportion / 100) {
                //输出方向为同意
                setNextStep(execution, Integer.parseInt(passSkip));
                return true;
            }

            // 不通过的比例大于设置比例
            if (noPassCount != 0 && (double) noPassCount / nrOfInstances > 1 - (double) proportion / 100) {
                //输出方向为拒绝
                setNextStep(execution, Integer.parseInt(noPassSkip));
                return true;
            }
            // 当前所以实例执行完成后任然未通过，则驳回
            if (completedInstance == nrOfInstances) {
                //输出方向为拒绝
                setNextStep(execution, Integer.parseInt(noPassSkip));
                return true;
            }

            return false;
        }
    }

    /**
     * 设置会签完成后的走向
     *
     * @param execution
     * @param skip
     */
    private void setNextStep(DelegateExecution execution, int skip) {
        UserTaskService userTaskService = SpringContextHolder.getBean(UserTaskService.class);
        userTaskService.remove(new LambdaQueryWrapper<UserTaskDO>()
                .eq(UserTaskDO::getApplyId, execution.getVariable(BpmConstant.APPLY_ID))
                .ne(UserTaskDO::getTaskAssigneeUserId, SecurityUtil.getCurrentUserId()));
        switch (skip) {
            // 跳转下一节点
            case TaskConstant.TASK_SKIP_NEXT_NODE:
                execution.setVariable(TaskConstant.COUNTERSIGN_OUTCOME_DESC, TaskConstant.COUNTERSIGN_OUTCOME_PASS);
                break;
            // 退回上一节点
            case TaskConstant.TASK_SKIP_LAST_NODE:
                execution.setVariable(TaskConstant.COUNTERSIGN_OUTCOME_DESC, TaskConstant.COUNTERSIGN_OUTCOME_REJECT);
                break;
            // 终止流程
            case TaskConstant.TASK_SKIP_STOP:
                execution.setVariable(TaskConstant.COUNTERSIGN_OUTCOME_DESC, TaskConstant.COUNTERSIGN_OUTCOME_STOP);
                break;
            // 默认终止流程
            default:
                execution.setVariable(TaskConstant.COUNTERSIGN_OUTCOME_DESC, TaskConstant.COUNTERSIGN_OUTCOME_STOP);
        }
    }
}
