package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.enums.PublishTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-09-30
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("")
@TableName("dj_announcement")
public class Announcement extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * id主键
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "id主键")
        @TableId(value = "id", type = IdType.ASSIGN_UUID)
        private String id;
    /**
     * 公告标题
     * title：varchar(255) ==》 title：String
     */
    @ApiModelProperty(value = "公告标题")
    private String title;
    /**
     * 组织id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "组织id")
    private String orgId;
    /**
     * 关键字
     * keywords：varchar(255) ==》 keywords：String
     */
    @ApiModelProperty(value = "关键字")
    private String keywords;
    /**
     * 附件id
     * attachment_id：longtext ==》 attachmentId：String
     */
    @ApiModelProperty(value = "附件id")
    private String attachmentId;
    /**
     * 公告内容
     * content：longtext ==》 content：String
     */
    @ApiModelProperty(value = "公告内容")
    private String content;
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
    @TableField(value = "publish_time", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 附件集
     */
    @TableField(exist = false)
    private List<Map<String,String>> attachmentList;

    /**
     * 附件集
     */
    @TableField(exist = false)
    private List<KeyWord> keyWordList;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;


    /**
     * 处理发布状态
     * @return
     */
    public String getPublishTypeCh() {
        return PublishTypeEnum.getValueByCode(Integer.parseInt(this.publishType));
    }

//    public String getPublishTimeCh() {
//        return UtilDic.getSpecificDate(this.publishTime);
//    }




}