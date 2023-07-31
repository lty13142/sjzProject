package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Title:流程历史数据表 </p>
 * <p>Description:流程历史数据表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2020-11-14
 */
@Data
@ApiModel("流程历史数据表")
@TableName("bpm_history_form_data")
public class HistoryFormDataDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表单数据编号
     * id：bigint ==》 id：Integer
     */
    @ApiModelProperty(value = "表单数据编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 数据KEY
     * data_key：varchar(32) ==》 dataKey：String
     */
    @ApiModelProperty(value = "数据KEY")
    private String dataKey;
    /**
     * 数据中文名称
     * data_name：varchar(64) ==》 dataName：String
     */
    @ApiModelProperty(value = "数据中文名称")
    private String dataName;
    /**
     * 数据值
     * data_value：text ==》 dataValue：String
     */
    @ApiModelProperty(value = "数据值")
    private String dataValue;
    /**
     * 数据格式
     * data_format：varchar(32) ==》 dataFormat：String
     */
    @ApiModelProperty(value = "数据格式")
    private String dataFormat;
    /**
     * 数据类型
     * data_type：varchar(255) ==》 dataType：String
     */
    @ApiModelProperty(value = "数据类型")
    private String dataType;
    /**
     * 表单编号
     * form_id：int ==》 formId：Integer
     */
    @ApiModelProperty(value = "表单编号")
    private Long formId;
    /**
     * 表单KEY
     * form_key：varchar(255) ==》 formKey：String
     */
    @ApiModelProperty(value = "表单KEY")
    private String formKey;
    /**
     * 租户ID
     * tenant_id：varchar(32) ==》 tenantId：String
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 流程编号
     * process_id：int ==》 processId：Integer
     */
    @ApiModelProperty(value = "流程编号")
    private Long processId;
    /**
     * 申请编号
     * apply_id：int ==》 applyId：Integer
     */
    @ApiModelProperty(value = "申请编号")
    private Long applyId;
    /**
     * 流程实例编号
     * proc_inst_id：varchar(32) ==》 procInstId：String
     */
    @ApiModelProperty(value = "流程实例编号")
    private String procInstId;
    /**
     * 任务节点ID
     * task_node_id：varchar(32) ==》 taskNodeId：String
     */
    @ApiModelProperty(value = "任务节点ID")
    private String taskNodeId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)   //公共字段自动填充（新增），详见com.crcm.core.config.mybatis.MybatisMateHandler
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
}