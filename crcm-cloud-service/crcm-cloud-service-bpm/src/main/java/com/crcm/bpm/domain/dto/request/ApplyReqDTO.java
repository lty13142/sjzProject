package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApplyReqDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/24/10:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplyReqDTO extends BaseRequestDTO implements Serializable {
    private static final long serialVersionUID = -8841152253959600406L;
    /**
     * 申请ID
     */
    private Long applyId;
    /**
     * 申请标题
     */
    private String applyTitle;
    /**
     * 流程实例ID
     */
    private Long processId;
    /**
     * 流程实例KEY
     */
    private String processKey;
    /**
     * 流程数据
     */
    private Map<String,Object> data;

    /**
     *  流程发起人
     */
    private String startUserId;

    /**
     *  流程制单人，制单人可以帮 其他人发起流程，区分
     */
    private String creatorUserId;
    /**
     * 流程制单人名称
     */
    private String creatorName;
    /**
     * 表单KEY
     */
    private String formKey;
    /**
     * 分配任务用户方式 1（自动指定）  2 （手动指定）
     */
    private Integer assignTaskUserWay;
    /**
     * 指定审核人ID
     */
    private String assignUserId;

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;

    public Integer getAssignTaskUserWay() {
        return assignTaskUserWay == null ? BpmConstant.ASSIGN_TASK_USER_AUTO : assignTaskUserWay;
    }

    /**
     * 指定审核人ID集合
     */
    private List<String> assignUserList;
    /**
     * 指定审核人名称集合
     */
    private List<String> assignUserNameList;
}
