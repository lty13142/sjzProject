package com.crcm.admin.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName: UserBaseInfoVo
 * @Description 用户基础信息表，不包含敏感信息
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/11/28
 **/
@Data
@ApiModel(value = "用户基础信息")
public class UserBaseInfoVO implements Serializable {

    private static final long serialVersionUID = -5763477450421517616L;
    /**
     * 用户名
     */
    @NotBlank(message = "用户账号不能为空")
    @ApiModelProperty(value = "用户账号", required = true)
    @Size(max = 64, message = "用户账号最多输入64个字符")
    private String username;
    /**
     * 用户简介
     */
    @ApiModelProperty(value = "用户简介")
    @Size(max = 255, message = "用户简介最多输入255个字符")
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
    @ApiModelProperty(value = "用户姓名", required = true)
    @Size(max = 64, message = "用户姓名最多输入64个字符")
    private String nickName;
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    @Pattern(regexp = "^$|(^(\\d{3,4}-)?\\d{3,10})$|^(1\\d{10}$)", message = "电话号码格式不合法")
    private String phone;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    @Pattern(regexp = "^$|^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.(?:[a-zA-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)$", message = "邮箱地址不合法")
    private String email;
    /**
     * 用户类型（1--监管账号 0--企业账号，字典：USER_TYPE）
     */
    @ApiModelProperty(value = "用户类型（1--监管账号 0--企业账号，字典：USER_TYPE）")
    private Integer userType;
    /**
     * 所属镇区
     */
    @ApiModelProperty(value = "所属镇区行政区划代码")
    @Size(max = 50, message = "所属镇区行政区划代码最多输入64个字符")
    private String areaCode;
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

    @ApiModelProperty(value = "所属组织ID")
    private Long orgId;

    /**
     * 所属镇区
     */
    private String area;

}
