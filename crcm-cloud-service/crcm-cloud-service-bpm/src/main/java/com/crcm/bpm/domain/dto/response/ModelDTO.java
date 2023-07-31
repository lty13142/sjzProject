package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;

/**
 * @ClassName ModelDTO
 * @Description：模型数据传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：Administrator
 * @Date：2020/9/24/18:23
 **/
@Data
public class ModelDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -1800477386321803305L;
    /** 模型编号 */
    private Long id;
    /** 流程名 */
    private String modelName;
    /** 流程定义ID（部署流程的ID） */
    private String definitionId;
    /** 类别 */
    private String flowType;
    /** 表单类型：0：组件表单  1：自定义表单 */
    private String formType;
    /** 关联表单Id（或自定义表单标识code） */
    private String formId;
    /** 关联表单的名称 */
    private String formName;
    /** 租户Id */
    private String tenantId;
    /** 发布状态 0:未发布 1:已发布  2:停用 3:有新版，未发布 */
    private Integer status;
    /** 启用状态0:未启用 1:已启用  2:停用 -1:已停用 */
    private Integer enabled;
    /** 模型xml  */
    private String processXml;
    /** 模型图片  */
    private String processSvg;
    /** 流程模型唯一key  */
    private String processKey;
    /** 备注  */
    private String remark;
    /**逻辑删除 */
    private Integer  deleted;
    /** 模型版本 */
    private Integer  modelVersion;
    /** 是否是主版本 */
    private Integer mainVersion;
    /**是否自动完成第一个用户节点 1是 0否*/
    private Integer autoCompleteFirstNode;
    /** 表单字段 */
    private String fields;
    /** 公司id */
    private String companyId;
    /** 公司名 */
    private String companyName;
    /**流程图标路径*/
    private String flowIcon;

//    public String getStatusCh() {
//        return UtilDic.getDictName(DictConstant.MODEL_STATUS.CODE,String.valueOf(this.status));
//    }
//    public String getMainVersionCh() {
//        return UtilDic.getDictName(YTSystemConstant.YES_OR_NO.CODE,String.valueOf(this.mainVersion));
//    }
//    public String getEnabledCh() {
//        return UtilDic.getDictName(YTSystemConstant.ENABLE_STATUS.CODE,String.valueOf(this.enabled));
//    }
//    public String getFlowTypeCh() {
//        return UtilDic.getDictName(DictConstant.FLOW_TYPE.CODE,this.flowType);
//    }


}
