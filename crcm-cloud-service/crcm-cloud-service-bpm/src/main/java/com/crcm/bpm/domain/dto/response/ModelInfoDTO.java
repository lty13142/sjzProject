package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName ModelInfoDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/24/18:24
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ModelInfoDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -4887416621353714444L;
    /** ID */
    private Long id;
    /** 流程名 */
    private String modelName;
    /** 展示名 */
    private String showName;
    /** 流程定义ID（部署流程的ID） */
    private String definitionId;
    /** 类别 */
    private String flowType;
    /** 关联表单Id */
    private String formId;
    /** 关联表单的名称 */
    private String formName;
    /** 定制表单的Code */
    private String formCode;
    /** 租户Id */
    private String tenantId;
    /** 0:未发布 1:已发布  2:停用 3:有新版，未发布 */
    private Integer status;
    /** 可以插叙每个节点的可编辑字段 */
    private String modelNode;
    /** 模型xml  */
    private String processXml;
    /** 模型图片  */
    private String processSvg;
    /** 流程模型唯一key  */
    private String processKey;
    /** 备注  */
    private String remark;
    /**
     * 乐观锁
     */
    private Integer  version;
    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    private Integer  deleted;
    /**
     * 模型版本
     */
    private Integer  modelVersion;
    /**
     * 是否是主版本
     */
    private Integer mainVersion;
    /**
     * 是否自动完成第一个用户节点 1是 0否
     */
    private Integer autoCompleteFirstNode;
}
