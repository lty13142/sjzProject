package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.dto.SysRoleQueryDTO;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.io.Serializable;
import java.util.List;

public interface RoleService extends IService<SysRole> {

    String saveRole(SysRole t);

    String updateRole(SysRole t);

    boolean deleteById(Serializable id);

    SysRole findById(Serializable id);

    PageT<SysRole> findPageRole(PageT<SysRole> page, SysRoleQueryDTO t);

    List<SysRole> findList(SysRoleQueryDTO t);

    /**
     * 通过值和组织查询角色
     *
     * @param value     角色值
     * @param origanize 组织
     * @return 角色
     */
    SysRole findByValueAndOrganize(String value, String origanize);
}
