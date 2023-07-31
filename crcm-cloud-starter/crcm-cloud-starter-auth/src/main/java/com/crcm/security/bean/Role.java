package com.crcm.security.bean;

import com.crcm.core.constant.AuthConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName Role
 * @Description 角色
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority, Comparable<Role> {
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 授权标识
     */
    private String value;
    /**
     * 机构
     */
    private String organize;

    /**
     * 拼接角色
     * <p>ROLE_ + {@code this.organize} + _ + {@code this.value}</p>
     *
     * @return
     */
    @Override
    public String getAuthority() {
        return AuthConstants.ROLE_PREFIX + this.organize + "_" + this.value;
    }

    @Override
    public int compareTo(Role o) {
        // 判断id是否相同
        if (this.id.equals(o.getId())) {
            return 0;
        }
        return -1;
    }
}
