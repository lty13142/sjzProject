package com.crcm.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.PermissionMapper;
import com.crcm.admin.model.entity.SysMenu;
import com.crcm.admin.model.entity.SysPermission;
import com.crcm.admin.model.vo.AddPermissionVO;
import com.crcm.admin.model.vo.PermissionVO;
import com.crcm.admin.model.vo.SysRolePermissionVO;
import com.crcm.admin.service.MenuService;
import com.crcm.admin.service.PermissionService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, SysPermission> implements PermissionService {

    @Override
    public Map<String, List<Long>> findRoleMenus(String roleId) {
        LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<>();
        query.eq(SysPermission::getRoleId, roleId)
                .eq(SysPermission::getType, SystemConstant.PERMISSION_TYPE.MENU);
        return getPermissionIdByCheck(this.list(query));
    }

    @Override
    public List<Long> findPermissionRoles(Long permissionId, Integer type) {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPermission::getPermissionId, permissionId).eq(null != type, SysPermission::getType, type);
        List<SysPermission> list = this.list(wrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.stream().map(SysPermission::getRoleId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public PageT<SysRolePermissionVO> findPermissionRole(PageT<SysRolePermissionVO> page, PermissionVO vo) {
        return this.baseMapper.selectPermissionRole(page, vo);
    }

    @Override
    public List<SysRolePermissionVO> findWithoutPermissionRole(PermissionVO vo) {
        List<Long> roleIds = findPermissionRoles(vo.getPermissionId(), vo.getType());
        return this.baseMapper.selectWithoutPermissionRole(roleIds);
    }

    @Override
    public boolean addPermission(AddPermissionVO vo) {
        List<SysPermission> permissions = new ArrayList<>();
        Arrays.stream(vo.getRoleIds().split(",")).forEach(id ->
                permissions.add(new SysPermission(Long.parseLong(id), Long.parseLong(vo.getPermissionId()), vo.getType())));
        return this.saveBatch(permissions);
    }

    @Override
    public boolean removePermission(AddPermissionVO vo) {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPermission::getPermissionId, vo.getPermissionId())
                .eq(SysPermission::getType, vo.getType())
                .in(SysPermission::getRoleId, Arrays.asList(vo.getRoleIds().split(",")));
        return this.remove(wrapper);
    }

    /**
     * 根据角色id删除权限
     *
     * @Author GZL
     * @Date 2023/2/24 17:39
     * @Param id 角色id
     **/
    @Override
    public void removePermissionByRoleId(Serializable roleId) {
        LambdaUpdateWrapper<SysPermission> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysPermission::getRoleId, roleId)
                .eq(SysPermission::getType, SystemConstant.PERMISSION_TYPE.MENU);
        this.remove(wrapper);
    }

    /**
     * 根据菜单id删除权限
     *
     * @Author GZL
     * @Date 2023/2/24 17:39
     * @Param id 菜单id
     **/

    @Override
    public void removePermissionByMenuId(Serializable menuId) {
        LambdaUpdateWrapper<SysPermission> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysPermission::getPermissionId, menuId)
                .eq(SysPermission::getType, SystemConstant.PERMISSION_TYPE.MENU);
        this.remove(wrapper);
    }

    @Override
    public List<Long> findRoleResources(String roleId) {
        LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<>();
        query.eq(SysPermission::getRoleId, roleId)
                .eq(SysPermission::getType, SystemConstant.PERMISSION_TYPE.RESOURCE);
        return getPermissionId(this.list(query));
    }

    private List<Long> getPermissionId(List<SysPermission> permissions) {
        if (CollectionUtil.isEmpty(permissions)) {
            return new ArrayList<>();
        }
        List<Long> ids = new ArrayList<>();
        permissions.forEach(p -> ids.add(p.getPermissionId()));
        return ids;
    }

    @Override
    public boolean setRoleResources(PermissionVO t) {
        return updatePermission(t.getRoleId(), t.getIds(), t.getHalfCheckIds(), SystemConstant.PERMISSION_TYPE.RESOURCE);
    }

    private boolean updatePermission(Long roleId, String ids, String halfCheckIds, Integer type) {
        if (StringUtils.isNotBlank(ids)) {
            // 删除旧的资源权限
            LambdaUpdateWrapper<SysPermission> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(SysPermission::getRoleId, roleId)
                    .eq(SysPermission::getType, type);
            this.remove(wrapper);
            // 保存新的功能权限信息
            List<SysPermission> permissions = new ArrayList<>();
            Arrays.stream(ids.split(",")).forEach(id ->
                    permissions.add(new SysPermission(roleId, Long.parseLong(id), SystemConstant.YES_OR_NO.YES, type)));
            if (StringUtils.isNotBlank(halfCheckIds)) {
                Arrays.stream(halfCheckIds.split(",")).forEach(id ->
                        permissions.add(new SysPermission(roleId, Long.parseLong(id), SystemConstant.YES_OR_NO.NO, type)));
            }
            return this.saveBatch(permissions);
        }
        return false;
    }

    private boolean updateMenuPermission(Long roleId, String ids, String halfCheckIds, Integer type, Integer menuBelong) {
        // 删除旧的资源权限
        Map<String,Object> map = new HashMap<>();
        map.put("roleId",roleId);
        map.put("type",type);
        map.put("menuBelong",menuBelong);
        this.baseMapper.deleteOldPermission(map);
        if (StringUtils.isNotBlank(ids)) {
            // 保存新的功能权限信息
            List<SysPermission> permissions = new ArrayList<>();
            Arrays.stream(ids.split(",")).forEach(id ->
                    permissions.add(new SysPermission(roleId, Long.parseLong(id), SystemConstant.YES_OR_NO.YES, type)));
            if (StringUtils.isNotBlank(halfCheckIds)) {
                Arrays.stream(halfCheckIds.split(",")).forEach(id ->
                        permissions.add(new SysPermission(roleId, Long.parseLong(id), SystemConstant.YES_OR_NO.NO, type)));
            }
            return this.saveBatch(permissions);
        }
        return false;
    }

    private boolean addPermission(Long roleId, List<String> ids, Integer type) {
        LambdaUpdateWrapper<SysPermission> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysPermission::getRoleId, roleId).eq(SysPermission::getType, type).in(SysPermission::getPermissionId, ids);
        List<SysPermission> olds = this.list(wrapper);
        List<Long> idList = olds.stream().map(SysPermission::getPermissionId).collect(Collectors.toList());
        List<SysPermission> permissions = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(olds)) {
            ids.stream().filter(id -> !idList.contains(Long.parseLong(id)))
                    .forEach(id -> permissions.add(new SysPermission(roleId, Long.parseLong(id), type)));
            return this.saveBatch(permissions);
        }
        return false;
    }

    @Override
    public boolean setRoleMenus(PermissionVO t) {
        // 修改菜单权限
        updateMenuPermission(t.getRoleId(), t.getIds(), t.getHalfCheckIds(), SystemConstant.PERMISSION_TYPE.MENU,t.getMenuBelong());
        // 根据按钮关联修改资源权限
        /*List<SysMenu> btns = menuService.findMenuByIds(Arrays.asList(t.getIds().split(",")), SystemConstant.MENU_TYPE.BUTTON);
        List<String> resourceIds = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(btns)) {
            btns.forEach(b -> {
                if (StringUtils.isNotBlank(b.getComponent())) {
                    resourceIds.add(b.getComponent());
                }
            });
            return addPermission(t.getRoleId(), resourceIds, SystemConstant.PERMISSION_TYPE.RESOURCE);
        }*/
        return true;
    }

    private Map<String, List<Long>> getPermissionIdByCheck(List<SysPermission> permissions) {
        Map<String, List<Long>> result = new HashMap<>();
        if (CollectionUtil.isEmpty(permissions)) {
            result.put("check", new ArrayList<>());
            result.put("halfCheck", new ArrayList<>());
            return result;
        }
        result.put("check", permissions.stream().filter(data -> Objects.equals(SystemConstant.YES_OR_NO.YES, data.getCheckFlag()))
                .map(SysPermission::getPermissionId).collect(Collectors.toList()));
        result.put("halfCheck", permissions.stream().filter(data -> Objects.equals(SystemConstant.YES_OR_NO.NO, data.getCheckFlag()))
                .map(SysPermission::getPermissionId).collect(Collectors.toList()));
        return result;
    }

}
