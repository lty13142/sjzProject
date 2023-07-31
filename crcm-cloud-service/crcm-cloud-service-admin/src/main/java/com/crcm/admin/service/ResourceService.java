package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysResource;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;

import java.io.Serializable;
import java.util.List;

public interface ResourceService extends IService<SysResource> {


    boolean update(SysResource sysResource);

    boolean deleteById(Serializable id);

    int realDelete(Serializable id);

    SysResource findById(Serializable id);

    List<SysResource> findList(SysResource t);

    PageT<SysResource> findPage(PageT<SysResource> page, SysResource t);

    boolean addResource(SysResource t);

    boolean addResourceList(SysResource t);

    List<TreeView> findTree(SysResource t);

    /**
     * 查询用户所拥有的资源
     *
     * @param userId 用户id
     * @return 用户所拥有的资源
     */
    List<SysResource> findUserResources(String userId);

    /**
     * 通过资源值查询资源
     *
     * @param value 值
     * @return 资源
     */
    SysResource findByValue(String value);

}
