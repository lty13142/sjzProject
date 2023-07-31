package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.core.dto.TreeView;
import com.sjz.partbuilding.model.entity.OrgPerson;
import com.sjz.partbuilding.model.vo.TreeViewVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrgPersonMapper extends BaseMapper<OrgPerson> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 分页查询
     * @param page
     * @param orgPerson
     * @return
     */
    IPage<OrgPerson> findPage(@Param("page") Page page, @Param("t") OrgPerson orgPerson);

    /**
     * 查询机构人员树
     * @param orgPerson
     * @return
     */
    List<TreeView> selectTree(OrgPerson orgPerson);

    /**
     * 查询机构人员树
     * @param orgPerson
     * @return
     */
    List<TreeViewVo> selectTreeView(OrgPerson orgPerson);

    /**
     * 查询党小组
     * @param orgId
     * @return
     */
    List<OrgPerson> getPartyGroup(@Param("orgId")String orgId);

    /**
     * 查询党小组成员
     * @param orgId
     * @return
     */
    List<TreeView> getPartyGroupUsers(@Param("id")String id, @Param("orgId")String orgId);
}
