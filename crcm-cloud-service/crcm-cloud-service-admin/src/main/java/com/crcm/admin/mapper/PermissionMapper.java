package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.entity.SysPermission;
import com.crcm.admin.model.vo.PermissionVO;
import com.crcm.admin.model.vo.SysRolePermissionVO;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface PermissionMapper extends BaseMapper<SysPermission> {


    PageT<SysRolePermissionVO> selectPermissionRole(@Param("page") PageT page, @Param("vo") PermissionVO vo);

    List<SysRolePermissionVO> selectWithoutPermissionRole(@Param("roleIds") List<Long> roleIds);

    int deleteOldPermission(Map map);

}
