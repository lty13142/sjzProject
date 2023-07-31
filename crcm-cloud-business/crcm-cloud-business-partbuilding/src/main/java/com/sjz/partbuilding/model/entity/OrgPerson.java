package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>Title:组织人员关系表 </p>
 * <p>Description:组织人员关系表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-11-06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("组织人员关系表")
@TableName("dj_org_person")
public class OrgPerson extends BaseEntity implements Serializable{
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
     * 上级人员id
     * pid：varchar(32) ==》 pid：String
     */
    @ApiModelProperty(value = "上级人员id")
    private String pid;
    /**
     * 上级人员名称
     * p_name：varchar(32) ==》 pName：String
     */
    @ApiModelProperty(value = "上级人员名称")
    private String pName;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "数据类型")
    private String type;

    @ApiModelProperty(value = "小组类型")
    private String groupType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;

}