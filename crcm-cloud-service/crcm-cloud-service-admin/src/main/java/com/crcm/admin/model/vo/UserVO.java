package com.crcm.admin.model.vo;

import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName UserVo
 * @Description 用户前台数据传输类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/4/26 17:44
 **/
@Data
@ApiModel(value = "系统用户信息")
public class UserVO implements Serializable {
    private static final long serialVersionUID = -2572435902556225204L;

    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private String phone;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用 0未启用，1启用，-1停用")
    private Integer enabled;
    /**
     * 是否锁定
     */
    @ApiModelProperty(value = "是否锁定")
    private Integer locked;
    /**
     * 是否过期
     */
    @ApiModelProperty(value = "是否过期")
    private Integer expired;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户姓名")
    private String nickName;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户简介")
    private String comment;

    @ApiModelProperty(value = "用户角色id")
    private String roleIds;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型（字典：USER_TYPE）")
    private Integer userType;
    /**
     * 用户详细类型(字典：USER_DETAIL_TYPE)
     */
    @ApiModelProperty(value = "用户详细类型(字典：USER_DETAIL_TYPE)")
    private Integer userDetailType;
    /**
     * 所属镇区行政区划代码
     */
    @ApiModelProperty(value = "所属镇区行政区划代码")
    @Size(max = 50, message = "所属镇区行政区划代码最多输入64个字符")
    private String areaCode;
    /**
     * 所属镇区
     */
    @ApiModelProperty(value = "所属镇区")
    private String area;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getEnabledCh() {
        return UtilDic.getDictName(SystemConstant.ENABLE_STATUS.CODE, this.enabled + "");
    }

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

    @ApiModelProperty(value = "组织id")
    private Integer orgId;
    /**
     * 党员信息ID
     */
    private String partUserId;
}
