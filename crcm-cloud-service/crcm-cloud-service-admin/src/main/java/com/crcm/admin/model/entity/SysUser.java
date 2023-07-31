package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName SysUserAccount
 * @Description 系统用户账户
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@ApiModel("用户表")
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 用户名
     * username：varchar(64) ==》 username：String
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 用户密码
     * password：varchar(255) ==》 password：String
     */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 电话号码
     * phone：varchar(32) ==》 phone：String
     */
    @ApiModelProperty(value = "电话号码")
    private String phone;
    /**
     * 邮箱地址
     * email：varchar(255) ==》 email：String
     */
    @ApiModelProperty(value = "邮箱地址")
    private String email;

    /**
     * 是否启用
     * enabled：tinyint(1) ==》 enabled：String
     */
    @ApiModelProperty(value = "是否启用 1:启用 0:未启用")
    private Integer enabled;
    /**
     * 是否锁定
     * locked：tinyint(1) ==》 locked：String
     */
    @ApiModelProperty(value = "是否锁定 1:锁定 0:未锁定 ")
    private Integer locked;
    /**
     * 是否过期
     * expired：tinyint(1) ==》 expired：String
     */
    @ApiModelProperty(value = "是否过期 1:过期 0:未过期 ")
    private Integer expired;
    /**
     * 昵称
     * nick_name: varchar(64) ==》nickName: String
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 用户头像
     * avatar：varchar(255) ==》 avatar：String
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    /**
     * 用户简介
     * comment：varchar(255) ==》 comment：String
     */
    @ApiModelProperty(value = "用户简介")
    private String comment;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /***
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * orgId
     * org_id：int ==》 orgId：Long
     */
    @ApiModelProperty(value = "所属组织ID")
    private Long orgId;
    /**
     * 用户类型（0--市局账号 1--镇区账号 2--企业账号）
     * password：varchar(255) ==》 password：String
     */
    @ApiModelProperty(value = "用户类型（0--市局账号 1--镇区账号 2--企业账号）")
    private String userType;
    /**
     * 用户详细类型(字典：USER_DETAIL_TYPE)
     */
    @ApiModelProperty(value = "用户详细类型(字典：USER_DETAIL_TYPE)")
    private Integer userDetailType;
    /**
     * 所属镇区行政区划代码
     */
    @ApiModelProperty(value = "所属镇区行政区划代码")
    private String areaCode;
    /**
     * 所属镇区
     * area：varchar(255) ==》 area：String
     */
    @ApiModelProperty(value = "所属镇区")
    private String area;
    /**
     * 原始密码
     * memo：varchar(100) ==》 memo：String
     */
    @ApiModelProperty(value = "原始密码")
    private String memo;

    /**
     * 最后登录时间
     * last_login_time：datetime ==》 lastLoginTime：LocalDateTime
     */
    @ApiModelProperty(value = "最新登录时间")
    private LocalDateTime lastLoginTime;
    /**
     * 最后登录时间
     * last_active_time：datetime ==》 lastActiveTime：LocalDateTime
     */
    @ApiModelProperty(value = "最新激活时间")
    private LocalDateTime lastActiveTime;
    /**
     * 最新密码修改时间
     * last_update_pwd_time：datetime ==》 lastUpdatePwdTime：LocalDateTime
     */
    @ApiModelProperty(value = "最新密码修改时间")
    private LocalDateTime lastUpdatePwdTime;

    /**
     * 镇街代码
     * townCode：varchar(50) ==》 townCode：String
     */
    @ApiModelProperty(value = "镇街代码")
    private String townCode;

    /**
     * 村庄代码
     * villageCode：varchar(50) ==》 villageCode：String
     */
    @ApiModelProperty(value = "村庄代码")
    private String villageCode;

    /**
     * 岗位id
     * positionId：varchar(50) ==》 positionId：String
     */
    @ApiModelProperty(value = "岗位id")
    private String positionId;

    @ApiModelProperty(value = "是否党建人员（1:否，2:是）")
    private Integer isBuilding;

    /**
     * openId
     */
    @ApiModelProperty(value = "微信用户id）")
    private String openId;
    /**
     * partUserId
     */
    @ApiModelProperty(value = "党员信息ID")
    private String partUserId;
    /**
     * selectedCode
     */
    @ApiModelProperty(value = "选中的区域code")
    private String selectedCode;

}