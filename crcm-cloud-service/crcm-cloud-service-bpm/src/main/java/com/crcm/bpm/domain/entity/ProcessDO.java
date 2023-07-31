package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:流程表 </p>
 * <p>Description:流程表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author ${Author}
 * @Date  2020-09-22
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("流程表")
@TableName("bpm_process")
public class ProcessDO extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * 流程编号
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "流程编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 流程编号
     * process_no：varchar(32) ==》 processNo：String
     */
    @ApiModelProperty(value = "流程编号")
    private String processNo;
    /**
     * 流程名称
     * process_name：varchar(64) ==》 processName：String
     */
    @ApiModelProperty(value = "流程名称")
    private String processName;
    /**
     * 流程二级名称
     * process_name：varchar(64) ==》 processName：String
     */
    @ApiModelProperty(value = "流程二级名称")
    private String subName;
    /**
     * 所属公司
     * company_id：int ==》 companyId：Integer
     */
    @ApiModelProperty(value = "所属公司")
    private String companyId;
    /**
     * 所属公司名
     * company_id：int ==》 companyId：Integer
     */
    @ApiModelProperty(value = "所属公司名")
    private String companyName;
    /**
     * 租户ID
     * tenant_id：varchar(32) ==》 tenantId：String
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 排序
     * sort_order：int ==》 sortOrder：Integer
     */
    @ApiModelProperty(value = "排序")
    private Integer sortOrder;
    /**
     * 流程状态
     * process_status：int ==》 processStatus：Integer
     */
    @ApiModelProperty(value = "流程状态")
    private Integer processStatus;
    /**
     * 备注
     * remarks：varchar(1000) ==》 remarks：String
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 流程模型唯一KEY
     * process_key：varchar(64) ==》 processKey：String
     */
    @ApiModelProperty(value = "流程模型唯一KEY")
    private String processKey;
    /**
     * 模型ID
     * model_id：int ==》 modelId：Long
     */
    @ApiModelProperty(value = "模型ID")
    private Long modelId;
    /**
     * 模型名称
     * model_name：varchar(64) ==》 modelName：String
     */
    @ApiModelProperty(value = "模型名称")
    private String modelName;
    /**
     * 申请标题规则
     * apply_title_rule：varchar(255) ==》 applyTitleRule：String
     */
    @ApiModelProperty(value = "申请标题规则")
    private String applyTitleRule;
    /**
     * 流程到期时间
     * apply_due_date：datetime ==》 applyDueDate：Date
     */
    @ApiModelProperty(value = "流程到期时间")
    private Date applyDueDate;
    /**
     * 图标
     * icon：varchar(32) ==》 icon：String
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    @TableField(exist = false)
    private String iconColor;

    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

//    public String getIconColor() {
//        if (!StringUtils.isEmpty(this.icon)) {
//            return UtilDic.getDictName(YTDicConstant.ICON_COLOR.CODE, this.icon);
//        }
//        return "";
//    }
}
