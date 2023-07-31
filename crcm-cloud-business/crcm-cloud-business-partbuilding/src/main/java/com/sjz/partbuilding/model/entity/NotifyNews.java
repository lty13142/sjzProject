package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.enums.NotifyTypeEnum;
import com.sjz.partbuilding.enums.PublishTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:通知新闻表 </p>
 * <p>Description:通知新闻表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-04-13
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("通知新闻")
@TableName("dj_notify_news")
public class NotifyNews extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * id
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.ASSIGN_UUID)
        private String id;
    /**
     * 标题
     * title_name：varchar(255) ==》 titleName：String
     */
    @ApiModelProperty(value = "标题")
    private String titleName;
    /**
     * (1:新闻 2:通知)
     * type：int(1) ==》 type：Integer
     */
    @ApiModelProperty(value = "(1:新闻 2:通知)")
    private Integer type;
    /**
     * 新闻类别
     * news_type：varchar(32) ==》 newsType：String
     */
    @ApiModelProperty(value = "新闻类别")
    private String newsType;
    /**
     * 新闻关键字
     * keywords：varchar(32) ==》 keywords：String
     */
    @ApiModelProperty(value = "新闻关键字")
    private String keywords;
    /**
     * 接收人id
     * accept_id：text ==》 acceptId：String
     */
    @ApiModelProperty(value = "接收人id")
    private String acceptId;
    /**
     * 接收人名字
     * accept_name：text ==》 acceptName：String
     */
    @ApiModelProperty(value = "接收人名字")
    private String acceptName;
    /**
     * 组织id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "组织id")
    private String orgId;
    /**
     * 新闻封面
     * cover：varchar(32) ==》 cover：String
     */
    @ApiModelProperty(value = "新闻封面")
    private String cover;
    /**
     * 发布状态 0:未发布 1:已发布
     * publish_type：varchar(1) ==》 publishType：String
     */
    @ApiModelProperty(value = "发布状态")
    private String publishType;
    /**
     * 发布时间
     * publish_time：datetime  ==》 publishTime：date
     */
    @ApiModelProperty(value = "发布时间")
    @TableField(value = "publish_time",
            insertStrategy = FieldStrategy.IGNORED,
            updateStrategy = FieldStrategy.IGNORED)
    private Date publishTime;
    /**
     * 内容
     * content：text  ==》 content：String
     */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
     * 附件ids
     * attachment：text ==》 attachment：String
     */
    @ApiModelProperty(value = "附件ids")
    private String attachment;
    /**
     * 新闻同步 0:不同步 1:同步
     * news_flag：varchar(1) ==》 newsFlag：String
     */
    @ApiModelProperty(value = "新闻同步")
    private String newsFlag;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;

    public String getPublishTypeCh() {
        return PublishTypeEnum.getValueByCode(Integer.valueOf(this.publishType));
    }

//    public String getPublishTimeCh() {
//        return UtilDic.getSpecificDate(this.publishTime);
//    }






}