package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.constants.YTSystemConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:工作部署表 </p>
 * <p>Description:工作部署表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2020-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("工作部署表")
@TableName("t_work_deploy")
public class WorkDeploy extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 任务名称
     * task_name：varchar(128) ==》 taskName：String
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    /**
     * 任务内容
     * task_content：text ==》 taskContent：String
     */
    @ApiModelProperty(value = "任务内容")
    private String taskContent;
    /**
     * 下发组织id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "下发组织id")
    private String orgId;
    /**
     * 发布范围
     * issue_range：text ==》 issueRange：String
     */
    @ApiModelProperty(value = "发布范围")
    private String issueRange;
    /**
     * 截止时间
     * close_time：datetime ==》 closeTime：Date
     */
    @ApiModelProperty(value = "截止时间")
    private Date closeTime;
    /**
     * 附件ids
     * attachments：text ==》 attachments：String
     */
    @ApiModelProperty(value = "附件ids")
    private String attachments;
    /**
     * 是否发布
     * type：varchar(1) ==》 type：String
     */
    @ApiModelProperty(value = "是否发布")
    private String type;
    /**
     * 发布时间
     * release_time：datetime ==》 releaseTime：Date
     */
    @ApiModelProperty(value = "发布时间")
    private Date releaseTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    /**
     * 是否发布处理
     * @return
     */
//    public String getTypeCh() {
//        return UtilDic.getChNameByCode(YTSystemConstant.NOTIFY_NEWS_TYPE.CODE,this.type);
//    }



}