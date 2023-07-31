package com.crcm.admin.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName UserInfoVo
 * @Description 前端用户信息
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/4/30 13:38
 **/
@Data
@ApiModel(value = "系统用户信息")
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = -5763477450421517616L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色列表")
    private List<String> roles;
    /**
     * 菜单
     */
    @ApiModelProperty(value = "菜单列表")
    private Map<String, Object> menus;
    /**
     * 介绍
     */
    @ApiModelProperty(value = "用户简介")
    private String comment;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String id;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * email地址
     */
    @ApiModelProperty(value = "email地址")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 用户类型（0--监管账号 1--企业账号）
     */
    @ApiModelProperty(value = "用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）")
    private Integer userType;
    /**
     * 用户详细类型(字典：USER_DETAIL_TYPE)
     */
    @ApiModelProperty(value = "用户详细类型(字典：USER_DETAIL_TYPE)")
    private Integer userDetailType;
    /**
     * 是否是管理员
     */
    @TableField(exist = false)
    private int isManager;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getUserTypeCh() {
        return UtilDic.getDictName(SystemConstant.USER_TYPE.CODE, this.userType + "");
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getUserDetailTypeCh() {
        return UtilDic.getDictName(Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, this.userType) ?
                SystemConstant.ENTERPRISE_USER_TYPE.CODE : SystemConstant.SUPERVISE_USER_TYPE.CODE, this.getUserDetailType() + "");
    }
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
     * partUserId
     */
    @ApiModelProperty(value = "党建人员id）")
    private String partUserId;
}
