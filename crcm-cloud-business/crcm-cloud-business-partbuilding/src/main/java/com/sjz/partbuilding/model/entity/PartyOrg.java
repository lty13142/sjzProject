package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.enums.AttacheEnum;
import com.sjz.partbuilding.enums.DictionaryBusinessEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:党组织详细信息表 </p>
 * <p>Description:党组织详细信息表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("党组织详细信息表")
@TableName("dj_party_org")
public class PartyOrg extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 组织ID
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "组织ID")
    private String orgId;
    /**
     * 上级组织id
     * sup_org：varchar(32) ==》 supOrg：String
     */
    @ApiModelProperty(value = "上级组织id")
    private String supOrg;
    /**
     * 组织名称
     * full_name：varchar(64) ==》 fullName：String
     */
    @ApiModelProperty(value = "组织名称")
    private String fullName;
    /**
     * 组织类别
     * org_category：varchar(64) ==》 orgCategory：String
     */
    @ApiModelProperty(value = "组织类别")
    private String orgCategory;
    /**
     * 简称
     * simple_name：varchar(255) ==》 simpleName：String
     */
    @ApiModelProperty(value = "简称")
    private String simpleName;
    /**
     * 组织隶属关系:
     * 1、中央
     * 2、省（市、区）
     * 3、市（地、州、盟）
     * 4、县（市、区、旗）
     * 5、城市街道
     * 6、乡
     * 7、镇
     * 8、城市社区（居委会）
     * 9、乡镇社区（居委会）
     * 10、建制村
     * 11、农村社区
     * attache：int(2) ==》 attache：Integer
     */
    @ApiModelProperty(value = "组织隶属关系:1、中央2、省（市、区）3、市（地、州、盟）4、县（市、区、旗）5、城市街道 " +
            "6、乡 7、镇 8、城市社区（居委会）9、乡镇社区（居委会）10、建制村 11、农村社区")
    private String attache;
    /**
     * 书记
     * secretary：varchar(32) ==》 secretary：String
     */
    @ApiModelProperty(value = "书记")
    private String secretary;
    /**
     * 联系人
     * contact_name：varchar(32) ==》 contactName：String
     */
    @ApiModelProperty(value = "联系人")
    private String contactName;
    /**
     * 联系电话
     * phone：varchar(32) ==》 phone：String
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 建立日期
     * setup_time：datetime ==》 setupTime：Date
     */
    @ApiModelProperty(value = "建立日期")
    private Date setupTime;
    /**
     * 是否流动党组织
     * 1：是
     * 0：不是
     * flow：int(1) ==》 flow：Integer
     */
    @ApiModelProperty(value = "是否流动党组织1是，0不是")
    private Integer flow;

    /**
     * 查询出的上级组织名
     */
    @TableField(exist = false)
    private String supName;

    /**
     * 邮政编码
     * code：varchar(32) ==》 code：String
     */
    @ApiModelProperty(value = "邮政编码")
    private String code;

    /**
     * 换届时间
     * change_time：datetime ==》 changeTime：Date
     */
    @ApiModelProperty(value = "换届时间")
    private Date changeTime;

    /**
     * 指导组织
     * guide_org：varchar(255) ==》 guideOrg：String
     */
    @ApiModelProperty(value = "指导组织")
    private String guideOrg;

    /**
     * 上级组织名称
     * sup_org_name：varchar(64) ==》 supOrgName：String
     */
    @ApiModelProperty(value = "上级组织名称")
    private String supOrgName;

    /**
     * 换届次数
     * change_term_count：varchar(64) ==》 changeTermCount：String
     */
    @ApiModelProperty(value = "换届次数")
    private String changeTermCount;

    /**
     * 联系号码
     * telephone：varchar(32) ==》 telephone：String
     */
    @ApiModelProperty(value = "联系号码")
    private String telephone;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;


    public String getAttacheCh() {
        return AttacheEnum.getValueByCode(Integer.parseInt(this.attache));
    }
//
//    public String getOrgCategoryCh() {
//        return UtilDic.getChNameByCode(DictionaryBusinessEnum.ORG_CATEGORY.code,this.attache);
//    }
//
//    /**
//     * 建立日期格式处理
//     * @return
//     */
//    public String getSetupTimeCh() {
//        return UtilDic.getSpecificDate(this.setupTime);
//    }
//
//    /**
//     * 换届日期格式处理
//     * @return
//     */
//    public String getChangeTimeCh() {
//        return UtilDic.getSpecificDate(this.changeTime);
//    }

}