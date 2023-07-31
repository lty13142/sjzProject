package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:领导班子表 </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("领导班子表")
@TableName("t_leader")
public class Leader extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 人员id
     * user_id：varchar(32) ==》 userId：String
     */
    @ApiModelProperty(value = "人员id")
    private String userId;
    /**
     * 人员名称
     * user_name：varchar(64) ==》 userName：String
     */
    @ApiModelProperty(value = "人员名称")
    private String userName;
    /**
     * 党内职务id
     * role_id：varchar(32) ==》 roleId：String
     */
    @ApiModelProperty(value = "党内职务id")
    private String postId;
    /**
     * 组织类型 直属支部0 地属支部1 团支部2 工会3
     * role_name：varchar(32) ==》 roleName：String
     */
    @ApiModelProperty(value = "组织类型")
    private String branchType;
    /**
     * 党内任职开始日期
     * start_time：datetime ==》 startTime：Date
     */
    @ApiModelProperty(value = "党内任职开始日期")
    private Date startTime;
    /**
     * 党内任职结束日期
     * end_time：datetime ==》 endTime：Date
     */
    @ApiModelProperty(value = "党内任职结束日期")
    private Date endTime;

    @ApiModelProperty(value = "证件照")
    @TableField(exist = false)
    private String facePic;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;

    /**
     * 党内任职开始日期年月日
     *
     * @return
     */
//    public String getStartTimeCh() {
//        return UtilDic.getSpecificDate(this.startTime);
//    }

    /**
     * 党内任职结束日期年月日
     *
     * @return
     */
//    public String getEndTimeCh() {
//        return UtilDic.getSpecificDate(this.endTime);
//    }

    /**
     * 前端党内职务处理
     * @return
     */
//    public String getPostIdCh() {
        /**
         * 前端工会职务处理 “3”代表工会
         */
//        if("3".equals(this.branchType)){
//            return UtilDic.getChNameByCode(DictionaryBusinessEnum.G_ORG_POST_TYPE.code, this.postId);
//        }else {
//            return UtilDic.getChNameByCode(DictionaryBusinessEnum.ORG_POST_TYPE.code, this.postId);
//        }
//    }


}