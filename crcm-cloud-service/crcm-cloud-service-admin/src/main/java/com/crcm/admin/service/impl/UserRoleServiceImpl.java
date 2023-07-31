package com.crcm.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.UserRoleMapper;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUserRole;
import com.crcm.admin.model.vo.AddUserRoleVO;
import com.crcm.admin.model.vo.UserRoleEditVO;
import com.crcm.admin.model.vo.UserRoleVO;
import com.crcm.admin.service.UserRoleService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, SysUserRole> implements UserRoleService {

    @Override
    public int saveUserRole(SysUserRole t) {
        return this.baseMapper.insert(t);
    }

    @Override
    public Page<SysUserRole> findPage(Page<SysUserRole> page, SysUserRole t) {
        return this.page(page, null);
    }

    @Override
    public List<SysRole> findUserRoles(String userId) {
        return this.baseMapper.selectUserRoles(userId);
    }

    @Override
    public List<UserRoleVO> findRoleUsers(Long roleId) {
        PageT<UserRoleVO> page = this.baseMapper.selectRoleUsers(null, roleId);
        return page.getRecords();
    }

    @Override
    public PageT<UserRoleVO> findPageRoleUsers(PageT<UserRoleVO> page, Long roleId) {
        return this.baseMapper.selectRoleUsers(page, roleId);
    }

    @Override
    public PageT<UserRoleVO> findWithoutRoleUsers(PageT<UserRoleVO> page, UserRoleVO vo) {
        List<String> userIds = getUserIds(vo.getRoleId());
        vo.setUserIds(userIds);
        return this.baseMapper.selectWithoutRoleUsers(page, vo);
    }

    @Override
    public void addUserRole(AddUserRoleVO vo) {
        List<SysUserRole> userRoles = new ArrayList<>();
        if (StringUtils.isNotBlank(vo.getUserIds())) {
            Arrays.stream(vo.getUserIds().split(",")).forEach(uid -> userRoles.add(new SysUserRole(uid, vo.getRoleId())));
            this.saveBatch(userRoles);
        }
    }

    @Override
    public void removeUserRole(AddUserRoleVO vo) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getRoleId, vo.getRoleId()).in(SysUserRole::getUserId, Arrays.asList(vo.getUserIds().split(",")));
        this.remove(wrapper);
    }

    private List<String> getUserIds(Long roleId) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getRoleId, roleId);
        List<SysUserRole> list = this.list(wrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 用户角色分配
     *
     * @param t 用户角色分配信息
     */
    @Override
    public void distribution(UserRoleEditVO t) {
        //删除旧的系统用户角色数据
        this.baseMapper.deleteUserRole(t.getUserId());
        // 保存新的用户角色数据
        if (StringUtils.isNotBlank(t.getRoleIds())) {
            ArrayList<SysUserRole> list = new ArrayList<>();
            for (String roleId : t.getRoleIds().split(",")) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(t.getUserId());
                sysUserRole.setRoleId(Long.parseLong(roleId));
                list.add(sysUserRole);
            }
            this.saveBatch(list);
        }
    }

}
