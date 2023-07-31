package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.core.dto.TreeView;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.vo.org.OrgPersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 部门组织Mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/21 17:36
 */
public interface OrgMapper extends BaseMapper<Org> {

    /**
     * 查询OrgTree树
     *
     * @param t
     * @return
     */
    List<TreeView> findTree(Org t);

    /**
     * 查询OrgTree树
     *
     * @param t
     * @return
     */
    List<TreeView> findTreeOrgTen(Org t);
    /**
     * 查询OrgTree树
     *
     * @param t
     * @return
     */
    List<TreeView> findTreeOrg(Org t);

    /**
     * 获取指定的组织
     *
     * @param orgPersonVo
     * @return
     */
    List<OrgPersonVo> findOrgs(OrgPersonVo orgPersonVo);

    /**
     * 查询OrgPersonVo树
     *
     * @param parentIds
     * @return
     */
    List<OrgPersonVo> selectChildren(@Param("parentIds") List<String> parentIds);

    /**
     * 根据上级id获取出最大编号Org
     *
     * @param id 上级id
     * @return
     */
    List<Org> getOrgByPNumber(String id);

    /**
     * 获取最高层级最大的编号组织
     *
     * @return
     */
    Org getSuperOrgNumber();

    /**
     * 根据用户名获取党组织信息
     *
     * @param username 用户名
     * @return
     */
    Org getOrgByCurrentLoginPerson(String username);

    /**
     * 根据company编号获取到所有org信息
     *
     * @param number 编号
     * @return
     */
    List<String> getOrgIdsByCompanyNumber(String number);

    /**
     * 根据company编号获取到org集合
     *
     * @param number 编号
     * @return
     */
    List<TreeView> getBranchOrgTreeListByCurrentLogin(String number);

    /**
     * 获取直属支部和地属支部
     *
     * @return
     */
    List<TreeView> getBranchList();

    /**
     * 获取支部管理党组织树
     *
     * @param org 查询条件
     * @param ids orgId集
     * @return
     */
    List<Org> getBranchManagementTree(@Param("org") Org org, @Param("ids") List<String> ids);

    /**
     * 根据当前登录人id获取组织
     *
     * @param userId 当前登录人id
     * @return
     */
    Org getOrgByUserId(@Param("userId") String userId);

    Map<String, Object> getLoginCount();
    Map<String, Object> getLoginCounts();
    Map<String, Object> getLoginCountz();

    /**
     * 查询当前组织及其子组织
     * @param org
     * @return
     */
    List<OrgPersonVo> findOrgTree(@Param("org")Org org);

    /**
     * 根据公司number查询当前组织及其子组织
     * @param number
     * @return
     */
    List<TreeView> findOrgTreeAndUsers(@Param("number")String number, @Param("org")Org org);

    Org findById(@Param("id") String id);

    List<TreeView> findOrgUserTree(Org t);

    List<String> getOrgidsByPid(@Param("pid") String pid);
}
