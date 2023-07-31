package com.crcm.develop.core.conf.security.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName SecurityRole
 * @Description 权限角色信息
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/5/14
 **/
@Data
@Builder
public class SecurityRole implements GrantedAuthority {
    /**
     * 角色ID
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 授权标识
     */
    private String authorizedSigns;
    /**
     * 角色类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String getAuthority() {
        return this.authorizedSigns;
    }
}
