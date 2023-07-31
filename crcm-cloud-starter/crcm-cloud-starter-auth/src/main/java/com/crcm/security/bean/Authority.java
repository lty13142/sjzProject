package com.crcm.security.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName Authority
 * @Description 用户权限
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority,Comparable<Authority> {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 键
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 组织机构
     */
    private String organize;

    @Override
    public String getAuthority() {
        return this.value;
    }

    @Override
    public int compareTo(Authority o) {
        //判断id是否相同
        if (this.id.equals(o.getId())) {
            return 0;
        }
        return -1;
    }
}
