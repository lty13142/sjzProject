package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 流程模型图对象 t_flow_model
 * true
 * @author zzyt
 * @date 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("bpm_model")
public class ModelDO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 模型编号 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 流程名 */
    private String modelName;

    /** 流程定义ID（部署流程的ID） */
    private String definitionId;

    /** 类别 */
    private String flowType;

    /** 表单类型：0：组件表单  1：自定义表单 */
    private String formType;

    /** 关联表单Id */
    private String formId;

    /** 关联表单的名称 */
    private String formName;

    /** 自定义表单标识code */
    private String formCode;

    /** 租户Id */
    private String tenantId;

    /** 发布状态 0:未发布 1:已发布 2:有新版，未发布 */
    private Integer status;

    /** 启用状态0:未启用 1:已启用 -1:停用 */
    private Integer enabled;

    /** 模型xml  */
    private String processXml;

    /** 模型图片  */
    private String processSvg;

    /** 流程模型唯一key  */
    private String processKey;

    /** 备注  */
    private String remark;

    /**
     * 模型版本
     */
    private Integer modelVersion;

    /**
     * 是否是主版本
     */
    private Integer mainVersion;

    /**
     * 是否自动完成第一个用户节点 1是 0否
     */
    private Integer autoCompleteFirstNode;


    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 流程图标路径
     */
    private String flowIcon;
//
//    public String getStatusCh() {
//        return UtilDic.getDictName(YTSystemConstant.ENABLE_STATUS.CODE, String.valueOf(this.status));
//    }
//
//    public String getFlowTypeCh() {
//        return UtilDic.getDictName(YTDicConstant.FLOW_TYPE.CODE, String.valueOf(this.flowType));
//    }
}
