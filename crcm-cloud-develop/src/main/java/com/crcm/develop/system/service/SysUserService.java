package com.crcm.develop.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.develop.system.entity.SysUser;
import com.crcm.develop.system.vo.UserInfoVo;

/**
 * 用户
 *
 * @author diaoy
 * @date 2020-03-31 18:47:48
 */
public interface SysUserService extends IService<SysUser> {

    int saveUser(SysUser sysUser);

    /**
     * 通过用户名加载用户
     * @param username
     * @return
     */
    SysUser selectUserByUserName(String username);

    UserInfoVo findUserInfo(String userName);
}
