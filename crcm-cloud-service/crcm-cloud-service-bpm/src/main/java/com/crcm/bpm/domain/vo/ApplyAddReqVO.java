package com.crcm.bpm.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * todo:
 *
 * @author : pig
 * @date : 2020/5/21 10:35
 */
@Data
@ToString
@ApiModel("发起流程")
public class ApplyAddReqVO implements Serializable {

    private static final long serialVersionUID = -1904908751341094576L;
    /** 申请编号 */
    private Long applyId;
    /** 申请标题 */
    private String applyTitle;
    /** 流程ID */
    @NotNull(message = "流程ID不能为空")
    private String processId;
    /** 流程key */
    private String processKey;
    /** 流程名称 */
    private String processName;
    /** 表单key */
    private String formKey;
    /** 流程数据 */
    private Map<String,Object> data;
    /** 发起用户ID */
    private String startUserId;
    /**
     *  流程制单人，制单人可以帮 其他人发起流程，区分
     */
    private String creatorUserId;

    private String creatorName;

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;
}
