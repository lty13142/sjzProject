package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUserRole;
import com.crcm.admin.model.vo.UserRoleVO;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserRoleMapper extends BaseMapper<SysUserRole> {

    int deleteUserRole(String userId);

    List<SysRole> selectUserRoles(String userId);

    PageT<UserRoleVO> selectRoleUsers(@Param("page") PageT page, @Param("roleId") Long roleId);

    PageT<UserRoleVO> selectWithoutRoleUsers(@Param("page") PageT page, @Param("vo") UserRoleVO vo);

    List<Long> selectDjRoleList();
}
