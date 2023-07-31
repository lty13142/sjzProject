package com.crcm.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName UserDataVo
 * @Description 用户新增、修改数据类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/4/13
 **/
@Data
public class UserDataVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "ID")
    private String id;
    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空")
    @Size(max = 64, message = "用户账号最多输入64个字符")
    @ApiModelProperty(value = "用户账号", required = true)
    private String username;
    /**
     * 用户密码
     */
    @Size(min = 8, max = 64, message = "用户密码可输入8~64个字符")
    private String password;
    /**
     * 用户简介
     */
    @Size(max = 255, message = "用户简介最多输入255个字符")
    @ApiModelProperty(value = "用户简介")
    private String comment;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", required = true)
    @Size(max = 64, message = "用户姓名最多输入64个字符")
    private String nickName;
    /**
     * 是否启用 0未启用，1启用，-1停用
     */
    @ApiModelProperty(value = "是否启用 0未启用，1启用，-1停用，字典：USER_TYPE")
    private Integer enabled;
    /**
     * 用户角色
     */
    @ApiModelProperty(value = "用户角色")
    private String roles;
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    @Pattern(regexp = "(^(\\d{3,4}-)?\\d{3,10})$|^(1\\d{10}$)", message = "电话号码格式不合法")
    private String phone;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    @Pattern(regexp = "^$|^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.(?:[a-zA-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)$", message = "邮箱地址不合法")
    private String email;
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID", hidden = true)
    private Long orgId;
    /**
     * 用户类型（1--监管账号 0--企业账号，字典：USER_TYPE）
     */
    @ApiModelProperty(value = "用户类型（1--监管账号 0--企业账号，字典：USER_TYPE）")
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
    @ApiModelProperty(value = "所属镇区", hidden = true)
    private String area;

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
    /**
     * selectedCode
     */
    @ApiModelProperty(value = "选中的区域code")
    private String selectedCode;
}
