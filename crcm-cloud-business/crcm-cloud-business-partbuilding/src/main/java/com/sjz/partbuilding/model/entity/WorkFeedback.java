package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:工作反馈表 </p>
 * <p>Description:工作反馈表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-10-17
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("工作反馈表")
@TableName("dj_work_feedback")
public class WorkFeedback extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * id
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.UUID)
        private String id;
    /**
     * 工作部署id
     * task_id：varchar(32) ==》 taskId：String
     */
    @ApiModelProperty(value = "工作部署id")
    private String taskId;
    /**
     * 任务名称
     * task_name：varchar(128) ==》 taskName：String
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    /**
     * 反馈内容
     * feedback_content：text ==》 feedbackContent：String
     */
    @ApiModelProperty(value = "反馈内容")
    private String feedbackContent;
    /**
     * 反馈组织id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "反馈组织id")
    private String orgId;
    /**
     * 完成时间
     * complete_time：datetime ==》 completeTime：Date
     */
    @ApiModelProperty(value = "完成时间")
    private Date completeTime;
    /**
     * 附件ids
     * attachments：text ==》 attachments：String
     */
    @ApiModelProperty(value = "附件ids")
    private String attachments;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    /**
     * 附件返回集
     */
    @TableField(exist = false)
    private List<Attachments> attachmentsList;







}