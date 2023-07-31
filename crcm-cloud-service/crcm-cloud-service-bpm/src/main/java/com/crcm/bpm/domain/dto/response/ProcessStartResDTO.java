package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName ProcessStartDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/24/18:04
 **/
@Data
@ToString
public class ProcessStartResDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -4566095867800436276L;
    /** 流程名称 */
    private String processName;
    /** 流程编号 */
    private String processNo;
    /** 流程 key */
    private String processKey;
    /** 流程描述 */
    private String remarks;
    /** 图标 */
    private String icon;
    /** 模型名称 */
    private String modelName;
    /** 模型ID */
    private String modelId;
    /** 是否为主版本 */
    private Integer mainVersion;
    /** 模型状态 */
    private String modelStatus;
    /** 模型发布ID */
    private String definitionId;
    /** 表单ID */
    private String formId;
    /** 表单KEY */
    private String formKey;
    /** 表单数据 */
    private String formJson;
    /** 系统流程ID */
    private String processId;
    /** 表单可编辑字段 */
    private String formEditField;
    /** 流程图 */
    private String processSvg;

}
