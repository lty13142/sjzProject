package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.vo.OrgPersonVO;
import com.crcm.core.dto.TreeView;
import com.crcm.admin.model.entity.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrgMapper extends BaseMapper<SysOrg> {
    List<TreeView> findTree(SysOrg t);

    List<OrgPersonVO> findOrgs(OrgPersonVO orgPersonVo);

    List<OrgPersonVO> selectChildren(@Param("parentIds") List<String> parentIds);
}
