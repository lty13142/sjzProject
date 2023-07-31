package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUserRole;
import com.crcm.admin.model.vo.AddUserRoleVO;
import com.crcm.admin.model.vo.UserRoleEditVO;
import com.crcm.admin.model.vo.UserRoleVO;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.util.List;

public interface UserRoleService extends IService<SysUserRole> {

    int saveUserRole(SysUserRole t);

    Page<SysUserRole> findPage(Page<SysUserRole> page, SysUserRole t);

    /**
     * 查询用户角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> findUserRoles(String userId);

    /**
     * 分配用户角色
     *
     * @param t 分配信息
     */
    void distribution(UserRoleEditVO t);

    /**
     * 获取拥有角色授权的用户
     *
     * @param roleId 角色id
     * @return 拥有角色授权的用户集合
     */
    List<UserRoleVO> findRoleUsers(Long roleId);

    /**
     * 分页获取拥有角色授权的用户
     *
     * @param roleId 角色id
     * @return 拥有角色授权的用户集合
     */
    PageT<UserRoleVO> findPageRoleUsers(PageT<UserRoleVO> page, Long roleId);

    /**
     * 分页查询未授权该角色的用户
     *
     * @param page 分页信息
     * @return 未授权该角色的用户
     */
    PageT<UserRoleVO> findWithoutRoleUsers(PageT<UserRoleVO> page, UserRoleVO vo);

    /**
     * 添加用户角色
     *
     * @param vo 添加用户角色信息
     */
    void addUserRole(AddUserRoleVO vo);

    /**
     * 移除用户角色
     *
     * @param vo 删除用户角色信息
     */
    void removeUserRole(AddUserRoleVO vo);

}
