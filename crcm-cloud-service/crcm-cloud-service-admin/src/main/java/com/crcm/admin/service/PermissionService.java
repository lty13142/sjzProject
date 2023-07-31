package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysPermission;
import com.crcm.admin.model.vo.AddPermissionVO;
import com.crcm.admin.model.vo.PermissionVO;
import com.crcm.admin.model.vo.SysRolePermissionVO;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PermissionService extends IService<SysPermission> {

    /**
     * 查询角色资源
     *
     * @param roleId 角色id
     * @return 资源id列表
     */
    List<Long> findRoleResources(String roleId);

    /**
     * 设置角色资源（数据权限）
     *
     * @param t 参数
     */
    boolean setRoleResources(PermissionVO t);

    /**
     * 设置角色菜单权限
     *
     * @param t 参数
     */
    boolean setRoleMenus(PermissionVO t);

    /**
     * 查询角色菜单
     *
     * @param roleId 角色id
     * @return 菜单id列表
     */
    Map<String, List<Long>> findRoleMenus(String roleId);

    /**
     * 查询具有该权限的角色
     *
     * @param permissionId 权限ID
     * @param type         权限类型
     * @return 角色id列表
     */
    List<Long> findPermissionRoles(Long permissionId, Integer type);

    /**
     * 分页查询具有资源的角色
     *
     * @param page 分页信息
     * @return 结果
     */
    PageT<SysRolePermissionVO> findPermissionRole(PageT<SysRolePermissionVO> page, PermissionVO vo);

    /**
     * 查询未授权该资源的角色
     *
     * @return 资源角色列表
     */
    List<SysRolePermissionVO> findWithoutPermissionRole(PermissionVO vo);

    /**
     * 添加资源角色授权
     *
     * @param vo 资源角色授权
     */
    boolean addPermission(AddPermissionVO vo);

    /**
     * 移除授权
     *
     * @param vo 资源角色授权
     * @return 结果
     */
    boolean removePermission(AddPermissionVO vo);

    /**
     * 根据角色id删除权限
     *
     * @Author GZL
     * @Date 2023/2/24 17:39
     * @Param id 角色id
     **/
    void removePermissionByRoleId(Serializable roleId);

    /**
     * 根据菜单id删除权限
     *
     * @Author GZL
     * @Date 2023/2/24 17:39
     * @Param id 菜单id
     **/
    void removePermissionByMenuId(Serializable menuId);
}
