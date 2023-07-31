package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.RoleMapper;
import com.crcm.admin.model.dto.SysRoleQueryDTO;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.service.PermissionService;
import com.crcm.admin.service.RoleService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {

    @Resource
    private PermissionService permissionService;

    @Override
    public String saveRole(SysRole t) {
        checkRole("add", t);
        //授权标识必须以ROLE开头，且必须大写
        if (StringUtils.isNotBlank(t.getValue())) {
            String authorizedSigns = StringUtils.trim(t.getValue());
            t.setValue(StringUtils.upperCase(authorizedSigns));
        }
        if (validRoleExist(t)) {
            return "授权标志已存在";
        }
        return this.save(t) ? null : "添加失败";
    }

    @Override
    public String updateRole(SysRole t) {
        checkRole("update", t);
        if (validRoleExist(t)) {
            return "授权标志已存在";
        }
        return this.updateById(t) ? null : "修改失败";
    }

    @Override
    public boolean deleteById(Serializable id) {
        SysRole role = new SysRole();
        role.setId((Long) id);
        checkRole("delete", role);
        boolean removeById = this.removeById(id);
        if(removeById){
            // 删除该角色拥有的权限
            permissionService.removePermissionByRoleId(id);
        }
        return removeById;
    }

    @Override
    public SysRole findById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public PageT<SysRole> findPageRole(PageT<SysRole> page, SysRoleQueryDTO t) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(t.getOrganize()), SysRole::getOrganize, t.getOrganize())
                .eq(null != t.getEnabled(), SysRole::getEnabled, t.getEnabled())
                .like(StringUtils.isNotBlank(t.getName()), SysRole::getName, t.getName())
                .like(StringUtils.isNotBlank(t.getValue()), SysRole::getValue, t.getValue());
        return this.page(page, queryWrapper);
    }


    @Override
    public List<SysRole> findList(SysRoleQueryDTO t) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(t.getOrganize()), SysRole::getOrganize, t.getOrganize())
                .eq(null != t.getEnabled(), SysRole::getEnabled, t.getEnabled());
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public SysRole findByValueAndOrganize(String value, String origanize) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getValue, value).eq(SysRole::getOrganize, origanize);
        return this.getOne(wrapper);
    }

	/**
     * 检测角色操作是否合法
     *
     * @param operate 操作
     * @param role    角色信息
     */
    private void checkRole(String operate, SysRole role) {
        switch (operate) {
            case "add":
                if ("ADMIN".equals(role.getValue())) {
                    throw new CustomException("系统管理员角色已存在");
                }
                break;
            case "update":
                if ("ADMIN".equals(role.getValue())) {
                    throw new CustomException("系统管理员不可修改");
                }
                break;
            case "delete":
                if (Objects.equals(role.getId(), 1L)) {
                    throw new CustomException("系统管理员不可删除");
                }
                break;
        }
    }

    /**
     * 验证角色是否存在
     *
     * @return 是否存在
     * @Author GZL
     * @Date 2023/2/8 15:32
     * @Param role 角色信息
     **/
    private boolean validRoleExist(SysRole role) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getValue, role.getValue());
        if (Objects.nonNull(role.getId())) {
            wrapper.ne(SysRole::getId, role.getId());
        }
        Integer count = this.baseMapper.selectCount(wrapper);
        return Objects.nonNull(count) && count > 0;
    }
}
