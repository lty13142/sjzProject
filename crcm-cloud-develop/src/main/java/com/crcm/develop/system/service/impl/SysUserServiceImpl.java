package com.crcm.develop.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.develop.common.exception.CustomException;
import com.crcm.develop.system.entity.SysUser;
import com.crcm.develop.system.mapper.SysUserMapper;
import com.crcm.develop.system.service.SysUserService;
import com.crcm.develop.system.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 *
 * @author diaoy
 * @date 2020-03-31 18:47:48
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public int saveUser(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            sysUser.setPassword("123456");
        }
        return this.baseMapper.insert(sysUser);
    }

    @Override
    public SysUser selectUserByUserName(String username) {
        List<SysUser> list = this.list(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername,username));
        if (list.size() == 0) {
            throw new CustomException("用户不存在！");
        }
        return list.get(0);
    }

    @Override
    public UserInfoVo findUserInfo(String userName) {
        SysUser user = this.selectUserByUserName(userName);
        if (null == user) {
            throw new CustomException("用户不存在!");
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtil.copyProperties(user, userInfoVo);
//        ArrayList<String> roles = new ArrayList<>();
//        ArrayList<String> roleIds = new ArrayList<>();
//        for (Role role : user.getRoles()) {
//            roles.add(role.getAuthorizedSigns());
//            roleIds.add(role.getId());
//        }
//        // 添加匿名角色
//        roles.add("anonymous");
//        MenuVo menuVo = new MenuVo();
//        menuVo.setRoleIds(roleIds);
//        List<MenuVo> menuVoList = new ArrayList<>();
//        if (roles.contains(YTBaseConstant.SUPER_ADMIN_USER_ROLE)) {
//            menuVoList = menuService.findSuperAdminMenuTree(menuVo);
//        } else {
//            menuVoList = menuService.findMenuTreeByRoleIds(menuVo);
//        }
//        userInfoVo.setRoles(roles);
//        userInfoVo.setMenus(menuVoList);
//        userInfoVo.setAvatar(attachmentsService.findFilePath(userInfoVo.getAvatar()));
        return userInfoVo;
    }
}
