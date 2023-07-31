package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.dto.res.ResourceDTO;
import com.crcm.admin.model.entity.SysResource;
import com.crcm.core.dto.TreeView;

import java.util.List;


public interface ResourceMapper extends BaseMapper<SysResource> {

    List<TreeView> findTree(SysResource sysResource);

    int realDelete(String id);

    List<SysResource> selectUserResources(String userId);

    List<ResourceDTO> selectAllEnabledResource();
}
