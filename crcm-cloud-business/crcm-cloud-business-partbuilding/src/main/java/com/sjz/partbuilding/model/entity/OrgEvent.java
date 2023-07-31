package com.sjz.partbuilding.model.entity;



import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>Title:组织活动表 </p>
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("组织活动表")
@TableName("dj_org_event")
public class OrgEvent extends BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 组织Id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "组织Id")
    private String orgId;
    /**
     * 组织名称
     * org_name：varchar(128) ==》 orgName：String
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;
    /**
     * 主题
     * theme：varchar(255) ==》 theme：String
     */
    @ApiModelProperty(value = "主题")
    @NotBlank(message = "主题名称不能为空")
    @Length(max = 255, message = "主题名称不能超过255个字符")
    private String theme;
    /**
     * 时间
     * event_time：datetime ==》 eventTime：Date
     */
    @ApiModelProperty(value = "时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd" )
    private LocalDate eventTime;
    /**
     * 地点
     * place：varchar(64) ==》 place：String
     */
    @ApiModelProperty(value = "地点")
    private String place;
    /**
     * 主持人
     * host：varchar(32) ==》 host：String
     */
    @ApiModelProperty(value = "主持人")
    private String host;
    /**
     * 记录人
     * recorder：varchar(32) ==》 recorder：String
     */
    @ApiModelProperty(value = "记录人")
    private String recorder;
    /**
     * 活动类型：0，党员大会，1，支委会，2，党小组会，3党课，4，主题党日活动
     * type：varchar(32) ==》 type：String
     */
    @ApiModelProperty(value = "活动类型：0：党员大会，1：支委会，2：党小组会，3：党课，4：主题党日活动，5：组织生活会")
    @NotBlank(message = "请选择活动类型")
    @Size(max = 5, min = 0, message = "0：党员大会，1：支委会，2：党小组会，3：党课，4：主题党日活动，5：组织生活会")
    private String type;
    /**
     * 出席
     * present：text ==》 present：String
     */
    @ApiModelProperty(value = "出席")
    private String present;
    /**
     * 列席
     * attend：text ==》 attend：String
     */
    @ApiModelProperty(value = "列席")
    private String attend;
    /**
     * 缺席
     * absence：text ==》 absence：String
     */
    @ApiModelProperty(value = "缺席")
    private String absence;
    /**
     * 0,2,3是否同步至4：1同步
     * unite：varchar(32) ==》 unite：String  @NotBlank(message = "请选择是否同步到主题党日活动")
     */
    @ApiModelProperty(value = "0,1,2是否同步至4,5。0,都不同步，2同步至主题党日活动，1同步至组织生活会，3同时同步")
    private String unite;
    /**
     * 小组id
     * group：varchar(32) ==》 group：String
     */
    @ApiModelProperty(value = "小组id")
    private String groupId;
    /**
     * 党课材料id
     * material_ids：text ==》 materialIds：String
     */
    @ApiModelProperty(value = "党课材料id")
    private String materialIds;
    /**
     * 活动记录id
     * notes_ids：text ==》 notesIds：String
     */
    @ApiModelProperty(value = "活动记录id")
    private String notesIds;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "文件号")
    private String num;

    @ApiModelProperty(value = "出席人id，逗号分隔")
    private String presentIds;

    @ApiModelProperty(value = "列席人id，逗号分隔")
    private String attendIds;

    @ApiModelProperty(value = "缺席人id，逗号分隔")
    private String absenceIds;

    @ApiModelProperty(value = "出席人数")
    private Integer presentCount;

    @ApiModelProperty(value = "列席人数")
    private Integer attendCount;

    @ApiModelProperty(value = "缺席人数")
    private Integer absenceCount;

    @ApiModelProperty(value = "年份，查询条件")
    @TableField(exist = false)
    private String year;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

//    @Version
//    private Integer version;

//    public String getEventTimeCh() {
//        return UtilDic.getSpecificDate(this.eventTime);
//    }

}