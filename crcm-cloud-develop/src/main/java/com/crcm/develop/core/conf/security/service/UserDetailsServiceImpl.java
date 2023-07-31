package com.crcm.develop.core.conf.security.service;

import com.crcm.develop.core.conf.security.domain.SecurityUserDetails;
import com.crcm.develop.system.entity.SysUser;
import com.crcm.develop.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author zzyt
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }


        return createSecurityUser(user);
    }

    public UserDetails createSecurityUser(SysUser user) {
        SecurityUserDetails userDetails = SecurityUserDetails.builder()
                .id(String.valueOf(user.getId()))
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        return userDetails;
    }
}
