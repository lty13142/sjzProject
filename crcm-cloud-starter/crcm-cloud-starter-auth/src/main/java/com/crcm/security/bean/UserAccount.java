package com.crcm.security.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName SecurityUser
 * @Description 权限用户信息,可根据需要自行增加
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/5/14
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements UserDetails, Serializable {
    private static final long serialVersionUID = 498532063070863063L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话号
     */
    private String phone;
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 账号未过期
     */
    private boolean accountNonExpired;
    /**
     * 账号未锁定
     */
    private boolean accountNonLocked;
    /**
     * 凭证未过期
     */
    private boolean credentialsNonExpired;
    /**
     * 权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 组织ID
     */
    private Long orgId;

    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 公司ID
     */
    private String comId;
    /**
     * 公司ID
     */
    private String comName;
    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 多部门ID
     */
    private List<String> deptList;
    /**
     * 单部门ID
     */
    private String deptId;

    /**
     * 用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）
     */
    @ApiModelProperty(value = "用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）")
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
    private String areaCode;
    /**
     * 所属镇区
     */
    @ApiModelProperty(value = "所属镇区")
    private String area;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private String companyId;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "村庄代码")
    private String villageCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}

