package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.core.dto.TreeView;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.entity.OrgEventTotal;
import com.sjz.partbuilding.model.vo.org.OrgListCountVo;
import com.sjz.partbuilding.model.vo.org.OrgPersonVo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * 部门组织表
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/10 10:06
 */
public interface OrgService extends IService<Org> {

    List<TreeView> findOrgUserTree(Org t);

//    /**
//     * 根据id查询
//     *
//     * @param id
//     * @return
//     */
//    Org findById(String id);
//
//    /**
//     * 分页查询
//     *
//     * @param pageT
//     * @param t
//     * @return
//     */
//    IPage<Org> findPage(Page<Org> pageT, Org t);
//
    /**
     * 查询组织树
     *
     * @param t
     * @return
     */
    List<TreeView> findTree(Org t);
//
//    /**
//     * 获取组织TreeView列表
//     *
//     * @param t
//     * @return
//     */
//    public List<TreeView> findOrgTreeViewList(Org t);
//
    /**
     * 查询列表
     *
     * @param t
     * @return
     */
    List<Org> findList(Org t);
//
//    /**
//     * 查询类型为组织的树
//     *
//     * @param t
//     * @return
//     */
//    List<TreeView> findTreeOrg(Org t);
//
//    /**
//     * 查询组织及其下的人员
//     *
//     * @param t
//     * @return
//     */
//    List<OrgPersonVo> findOrgPersonTree(OrgPersonVo t);
//
//
//    /**
//     * 根据上级组织id得到组织编号
//     *
//     * @param id       上级组织id
//     * @param updateId 修改行的id
//     * @return
//     */
//    String getOrgNumberByPid(String id, String updateId);
//
//    /**
//     * 判断是否是地属支部
//     *
//     * @param id 组织id
//     * @return
//     */
//    boolean judgeDirectlyBranchById(String id);
//
//    /**
//     * 根据当前登录人获取党组织信息
//     *
//     * @return
//     */
//    Org getOrgByCurrentLoginPerson();
//
//    /**
//     * 根据当前登录人获取组织及其附属ids
//     *
//     * @return
//     */
//    List<String> getBranchOrgIdsByCurrentLogin();
//
//    /**
//     * 根据当前登录人获取组织及其附属org对象
//     *
//     * @return
//     */
//    List<TreeView> getBranchOrgTreeListByCurrentLogin();
//
    /**
     * 获取支部管理党组织树
     *
     * @param org 查询条件
     * @return
     */
    List<Org> getBranchManagementTree(Org org);
//
//    /**
//     * 根据id获取上级组织对象
//     *
//     * @param orgId 组织id
//     * @return
//     */
//    Org getSuperOrgByPid(String orgId);
//
//    /**
//     * 根据当前登录人获取公司信息
//     *
//     * @return
//     */
//    Object getNumberByCurrentLogin();
//
//    /**
//     * 获取首页党组织类型占比
//     *
//     * @return
//     */
//    List<Org> getLoginPartyOrg();
//
//    /**
//     * 根据当前登录人获取组织层级结构
//     *
//     * @return
//     */
//    List<Org> getOrgHierarchyByCurrentLogin();
//
//    /**
//     * 获取下层组织
//     *
//     * @param ids
//     * @return
//     */
//    List<TreeView> getLowerLevelOrg(List<String> ids);
//
//    /**
//     * 获取当前登录人党组织是否为党委
//     *
//     * @param userId
//     * @return
//     */
//    String findIsNotPartyCommittee(String userId);
//
//    /**
//     * 获取大屏组织列表
//     *
//     * @return
//     */
//    List<Org> getLoginOrgList();
//
//    /**
//     * 根据工作部署
//     *
//     * @param issueRange 发布范围ids
//     * @return
//     */
//    List<Org> workDeploymentIssueRange(String issueRange);
//
//    OrgListCountVo getLoginCount();
//
    /**
     * 获取登陆人组织信息
     * @return
     */
    List<OrgEventTotal> getOrgInfo(String orgId);
//
//    /**
//     * 查询组织树及其下的人员
//     * @param t
//     * @return
//     */
//    OrgPersonVo getOrgTreeAndUsers(OrgPersonVo t);
//
//    /**
//     * 根据公司层级关系查询组织树及其下的人员
//     * @param t
//     * @return
//     */
//    TreeView findOrgTreeAndUsers(OrgPersonVo t);
//    /**
//     * 根据公司id统计所属组织的人员
//    */
//    Integer  CountUserByOrgId(HashSet<String> orgIds);

    Org getBranchManagement();
//
//    /**
//     * 获取当前组织的子组织id集合
//     * @param orgId
//     * @param type 0党组织 1团组织 2工会
//     * @return
//     */
//    ArrayList<String> getIdsByOrgId(String orgId,String type);
//
//    List<Org> findListByCompanyId(String id);

    List<TreeView> getPartyManagementByCompany();
}
