package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:会签记录 </p>
 * <p>Description:会签记录 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author diaoy
 * @Date  2020-11-08
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("会签记录")
@TableName("bpm_counter_sign")
public class CounterSignDO extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * ID
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 会签人ID
     * assignee：varchar(32) ==》 assignee：String
     */
    @ApiModelProperty(value = "会签人ID")
    private String assignee;
    /**
     * 会签人名称
     * assignee_name：varchar(32) ==》 assigneeName：String
     */
    @ApiModelProperty(value = "会签人名称")
    private String assigneeName;
    /**
     * 审核（会签）结果
     * approve_result：varchar(16) ==》 approveResult：String
     */
    @ApiModelProperty(value = "审核（会签）结果")
    private String approveResult;
    /**
     * 会签意见
     * approve_opinion：varchar(1000) ==》 approveOpinion：String
     */
    @ApiModelProperty(value = "会签意见")
    private String approveOpinion;
    /**
     * 会签时间
     * approve_time：datetime ==》 approveTime：Date
     */
    @ApiModelProperty(value = "会签时间")
    private Date approveTime;
    /**
     * 会签状态 0 未签 1 已签
     * status：int ==》 status：Integer
     */
    @ApiModelProperty(value = "会签状态 0 未签 1 已签")
    private Integer status;
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
     * 任务编号
     * task_id：int ==》 taskId：Integer
     */
    @ApiModelProperty(value = "任务编号")
    private Long taskId;


    /**
     * 默认排序顺序
     * default_sort：int ==》 defaultSort：Integer
     */
    @ApiModelProperty(value = "默认排序顺序")
    private Integer defaultSort;






}