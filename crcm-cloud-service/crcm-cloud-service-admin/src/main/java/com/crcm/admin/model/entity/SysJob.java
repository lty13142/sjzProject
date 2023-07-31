package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.crcm.admin.utils.UtilDic;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.annotation.ValidatorCron;
import com.crcm.core.constant.SystemConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Title:任务调度表 </p>
 * <p>Description:任务调度表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-01-06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("任务调度表")
@Deprecated
//@TableName("sys_quartz_job")
public class SysJob extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 任务名称
     * job_name：varchar(255) ==》 jobName：String
     */
    @NotBlank(message = "任务名称不能为空")
    @ApiModelProperty(value = "任务名称")
    private String jobName;
    /**
     * 服务全类名
     * job-class_name：varchar(255) ==》 jobClassName：String
     */
    @NotBlank(message = "服务全类名不能为空")
    @ApiModelProperty(value = "服务全类名")
    private String jobClassName;
    /**
     * cron表达式
     * cron_expression：varchar(255) ==》 cronExpression：String
     */
    @ValidatorCron
    @NotBlank(message = "cron表达式不能为空")
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;
    /**
     * 参数
     * parameter：varchar(255) ==》 parameter：String
     */
    @ApiModelProperty(value = "参数")
    private String parameter;
    /**
     * 状态 0-未启动 1-已启动 2-已暂停 -1-停止
     * status：varchar(16) ==》 status：String
     */
    @ApiModelProperty(value = "状态 0-未启动 1-已启动 2-已暂停 -1-停止")
    private Integer status;
    /**
     * 描述
     * description：varchar(1000) ==》 description：String
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 类型 1-系统任务 2-业务任务
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "1-系统任务 2-业务任务")
    private String type;
    /**
     * 上次启动时间
     * last_start_time：varchar(16) ==》 lastStartTime：String
     */
    @ApiModelProperty(value = "上次启动时间")
    private LocalDateTime lastStartTime;

    public String getStatusCh() {
        return UtilDic.getDictName(SystemConstant.JOB_STATUS.CODE, this.status + "");
    }

    public String getTypeCh() {
        return UtilDic.getDictName(SystemConstant.JOB_TYPE.CODE,this.type);
    }




}