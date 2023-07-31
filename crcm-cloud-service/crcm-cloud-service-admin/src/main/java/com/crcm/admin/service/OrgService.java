package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysOrg;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;

import java.io.Serializable;
import java.util.List;


public interface OrgService extends IService<SysOrg> {

    int saveOrg(SysOrg t);

    int updateOrg(SysOrg t);

    int deleteById(Serializable id);

    SysOrg findById(Serializable id);

    PageT<SysOrg> findPage(PageT<SysOrg> page, SysOrg t);

    List<TreeView> findTree(SysOrg t);

    List<SysOrg> findList(SysOrg t);

    List<TreeView> findTreeOrg(SysOrg t);

    /**
     * 查询负责部门
     *
     * @return 负责部门集合
     */
    List<SysOrg> findResponsibilityOrg();
}
