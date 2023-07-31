package com.sjz.education.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.crcm.admin.api.dto.res.RoleDTO;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 人员管理
 *
 * @author: sssccc
 * @TableName edu_personnel_management
 */
@TableName(value = "edu_personnel_management")
@Data
@Accessors(chain = true)
public class EduPersonnelManagement extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 我的积分
     */
    private Integer points;
    /**
     * 用户类型（字典：USER_TYPE）
     */
    private Integer userType;

    /**
     * 所属镇区行政区划代码
     */
    private String areaCode;

    /**
     * 所属镇区
     */
    private String area;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", required = true)
    @Size(max = 64, message = "用户姓名最多输入64个字符")
    private String nickName;

    @TableField(exist = false)
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户角色
     */
    @ApiModelProperty(value = "用户角色")
    private String roles;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    /**
     * 镇街代码
     * townCode：varchar(50) ==》 townCode：String
     */
    @ApiModelProperty(value = "镇街代码")
    private String townCode;
    /**
     * 城镇名称
     */
    private String townName;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 村庄代码
     * villageCode：varchar(50) ==》 villageCode：String
     */
    @ApiModelProperty(value = "村庄代码")
    private String villageCode;
    /**
     * 所属村庄名称
     */
    @ApiModelProperty(value = "所属村庄名称")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String villageName;

    /**
     * 岗位id
     */
    @ApiModelProperty(value = "岗位id")
    private String positionId;
    /**
     * 是否党建人员（1:否，2:是）
     */
    @ApiModelProperty(value = "是否党建人员（1:否，2:是）")
    private Integer isBuilding;
    /**
     * 版本号  乐观锁注解 每次修改操作都必须传入，version不一致则无法修改，修改后自增
     */
//    @Version
//    @TableField(fill = FieldFill.INSERT_UPDATE, update = "%s+1")
//    @ApiModelProperty(value = "版本号")
//    private Integer version;
    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    /**
     * 角色集合
     */
    @TableField(exist = false)
    private List<RoleDTO> roleLists;

    /**
     * 排名
     */
    @TableField(exist = false)
    private Integer ranking;

    /**
     * 历史总获得积分
     */
    @TableField(exist = false)
    private Integer earnPointsHistory;
    /**
     * 历史总兑换积分
     */
    @TableField(exist = false)
    private Integer exchangePointsHistory;
    /**
     * 今年总获得积分
     */
    @TableField(exist = false)
    private Integer earnPointsYear;
    /**
     * 今年总兑换积分
     */
    @TableField(exist = false)
    private Integer exchangePointsYear;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}