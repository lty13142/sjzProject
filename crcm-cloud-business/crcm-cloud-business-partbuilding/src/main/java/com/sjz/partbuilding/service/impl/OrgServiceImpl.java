package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.dto.TreeView;
import com.crcm.core.exception.SystemException;
import com.crcm.core.utils.TreeUtil;
import com.sjz.partbuilding.mapper.OrgEventMapper;
import com.sjz.partbuilding.mapper.OrgMapper;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.model.entity.OrgEventTotal;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.service.OrgService;
import com.sjz.partbuilding.service.UserDetailService;
import com.sjz.partbuilding.util.UtilTree;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author zzyt
 * @version 1.0
 * @date 2020/7/20 11:33
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {
    @Resource
    private UserDetailService userDetailService;

    @Resource
    private OrgEventMapper orgEventMapper;

    @Override
    public List<TreeView> findOrgUserTree(Org t) {
        List<TreeView> listView = this.baseMapper.findOrgUserTree(t);
        List<TreeView> list = UtilTree.listToTree(listView);
        return list;
    }

    @Override
    public List<TreeView> getPartyManagementByCompany() {
        List<TreeView> listView = this.baseMapper.findTree(null);
        return listView;
    }

//    @Resource
//    private UserMapper userMapper;
//
//    @Resource
//    private TeachingPlanMapper teachingPlanMapper;

//
//    @Resource
//    private UserDetailMapper userDetailMapper;
//

//
//    @Autowired
//    private UserRoleService userRoleService;
//
//    @Override
//    public Org findById(String id) {
//        Org org = this.baseMapper.selectById(id);
//        if (org != null && !"".equals(org)) {
//            orgToCh(org);
//        }
//        return org;
//    }
//
//
//    @Override
//    public IPage<Org> findPage(Page page, Org t) {
//        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNotNull("pid");
//        if (StringUtils.isNotBlank(t.getName())) {
//            queryWrapper.like("name", t.getName());
//        }
//        if (StringUtils.isNotBlank(t.getId())) {
//            queryWrapper.eq("id", t.getId()).or().eq("pid", t.getId());
//        }
//        queryWrapper.eq("deleted", 0);
//        queryWrapper.orderByAsc("type");
//        queryWrapper.orderByAsc("create_time");
//        IPage<Org> pageOrg = this.baseMapper.selectPage(page, queryWrapper);
//        for (Org record : pageOrg.getRecords()) {
//            orgToCh(record);
//        }
//        return pageOrg;
//    }
//
//    /**
//     * 为对象Ch附上字典名
//     *
//     * @param org
//     */
//    public void orgToCh(Org org) {
//        org.setTypeCh(UtilDic.getChNameByCode(DictionaryBusinessEnum.ORG_CATEGORY.code, org.getType()));
//        org.setBranchTypeCh(UtilDic.getChNameByCode(DictionaryBusinessEnum.BRANCH_TYPE.code, org.getBranchType() + ""));
//
//    }

    /**
     * 获取组织树
     *
     * @param t
     * @return
     */
    @Override
    public List<TreeView> findTree(Org t) {
        List<TreeView> listView = this.baseMapper.findTree(t);
        return TreeUtil.listToTree(listView);
    }
//
//    /**
//     * 获取组织TreeView列表
//     *
//     * @param t
//     * @return
//     */
//    @Override
//    public List<TreeView> findOrgTreeViewList(Org t) {
//        List<TreeView> listView = this.baseMapper.findTree(t);
//
//        return listView;
//    }
//
    @Override
    public List<Org> findList(Org t) {
        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(t.getCode())) {
            queryWrapper.eq("code", t.getCode());
        }
        if (StringUtils.isNotBlank(t.getName())) {
            queryWrapper.like("name", t.getName());
        }
        if (StringUtils.isNotBlank(t.getPid())) {
            queryWrapper.eq("pid", t.getPid());
        }
        if (StringUtils.isNotBlank(t.getType())) {
            queryWrapper.eq("type", t.getType());
        }
        if (StringUtils.isNotBlank(t.getNumber())) {
            queryWrapper.eq("number", t.getNumber());
        }
        if (StringUtils.isNotBlank(t.getId())) {
            queryWrapper.eq("id", t.getId());
        }
        if (StringUtils.isNotBlank(t.getCompanyId())) {
            queryWrapper.eq("company_id", t.getCompanyId());
        }
        if(null != t.getBranchType()){
            queryWrapper.eq("branch_type", t.getBranchType());
        }
        queryWrapper.orderByAsc("number");
        return this.baseMapper.selectList(queryWrapper);
    }
//
//    @Override
//    public List<TreeView> findTreeOrg(Org t) {
//        //查询当前用户是否为超级管理员
//        UserInfo current = UserContext.current();
//        UserRole userRole = userRoleService.findByUserId(current.getUserId());
//        User userInfo = userService.findById(current.getUserId());
//        Org orgInfo = this.findById(userInfo.getOrgId());
//        List<TreeView> listOrg = new ArrayList<TreeView>();
//        /*这里branchType接收参数是int类型，由于要查0,1的类型，所以约定branchType == 10*/
//        List<TreeView> listView = new ArrayList<>();
//        if(t.getBranchType() == 10){
//            listView = this.baseMapper.findTreeOrgTen(t);
//        }else{
//            listView = this.baseMapper.findTreeOrg(t);
//        }
//        if (!"cb3d60c8d8083cef8bd1f78b540d145a".equals(userRole.getRoleId())) {
//            for (TreeView org:listView) {
//                Org orgBean = this.findById(org.getId());
//                if(StringUtils.isBlank(org.getPid()) || orgInfo.getId().equals(org.getId()) || orgInfo.getCompanyId().equals(orgBean.getCompanyId())){
//                    listOrg.add(org);
//                }
//            }
//            return UtilTree.listToTree(listOrg);
//        }
//        return UtilTree.listToTree(listView);
//    }
//
//
//    /**
//     * 查询组织及其下的人员
//     *
//     * @param t
//     * @return
//     */
//    @Override
//    public List<OrgPersonVo> findOrgPersonTree(OrgPersonVo t) {
//        // 获取指定的组织
//        List<OrgPersonVo> orgList = this.baseMapper.findOrgs(t);
//        // 获取指定的组织下面的所有子组织
//        List<OrgPersonVo> childrenOrg = getChildrenOrg(orgList);
//        // 把查出来的所有组织加上去
//        orgList.addAll(childrenOrg);
//
//        List<String> orgIds = new ArrayList<>();
//        for (OrgPersonVo org : orgList) {
//            orgIds.add(org.getId());
//        }
//        if (orgIds.size() > 0) {
//            //排除掉登录人
//            UserInfo current = UserContext.current();
//            List<UserDetailVo> userDetailList = userDetailMapper.selectPersonByOrgIds(orgIds, current.getUserId());
//            HashMap<String, List<UserDetail>> personMap = new HashMap<>(16);
//            for (UserDetailVo person : userDetailList) {
//                if (personMap.get(person.getOrgId()) == null) {
//                    List<UserDetail> list = new ArrayList<>();
//                    list.add(person);
//                    personMap.put(person.getOrgId(), list);
//                } else {
//                    List<UserDetail> list = personMap.get(person.getOrgId());
//                    list.add(person);
//                    personMap.put(person.getOrgId(), list);
//                }
//            }
//            for (OrgPersonVo org : orgList) {
//                org.setUserDetails(personMap.get(org.getId()));
//            }
//        }
//        List<OrgPersonVo> orgPersonVos = listToTree(orgList);
//        return orgPersonVos;
//    }
//
//
//    private List<OrgPersonVo> listToTree(List<OrgPersonVo> list) {
//        ArrayList<OrgPersonVo> listOrg = new ArrayList<>();
//        List<String> topOrgIds = getTopOrgIds(list);
//        for (OrgPersonVo orgPerson : list) {
//            if (topOrgIds.contains(orgPerson.getId())) {
//                OrgPersonVo orgPersonVo = findChildren(orgPerson, list);
//                if (!Objects.isNull(orgPersonVo)) {
//                    listOrg.add(orgPersonVo);
//                }
//            }
//        }
//        return listOrg;
//    }
//
//    private List<String> getTopOrgIds(List<OrgPersonVo> list) {
//        ArrayList<String> ids = new ArrayList<>();
//        ArrayList<String> topOrgIds = new ArrayList<>();
//        for (OrgPersonVo orgPersonVo : list) {
//            ids.add(orgPersonVo.getId());
//        }
//        for (OrgPersonVo orgPersonVo : list) {
//            if (!ids.contains(orgPersonVo.getPid())) {
//                topOrgIds.add(orgPersonVo.getId());
//            }
//        }
//        return topOrgIds;
//    }
//
//    private OrgPersonVo findChildren(OrgPersonVo orgPerson, List<OrgPersonVo> list) {
//        for (OrgPersonVo orgPersonVo : list) {
//            if (orgPerson.getId().equals(orgPersonVo.getPid())) {
//                if (orgPerson.getChildren() == null) {
//                    orgPerson.setChildren(new ArrayList<>());
//                }
//                orgPerson.getChildren().add(findChildren(orgPersonVo, list));
//            }
//        }
//        return orgPerson;
//    }
//
//    public List<OrgPersonVo> getChildrenOrg(List<OrgPersonVo> orgList) {
//        List<String> parentIds = new ArrayList<>();
//        List<OrgPersonVo> childrenOrg = new ArrayList<>();
//        for (OrgPersonVo orgPersonVo : orgList) {
//            parentIds.add(orgPersonVo.getId());
//        }
//
//        List<OrgPersonVo> children = this.baseMapper.selectChildren(parentIds);
//        for (OrgPersonVo child : children) {
//            childrenOrg.add(child);
//        }
//        if (!children.isEmpty()) {
//            childrenOrg.addAll(getChildrenOrg(children));
//        }
//
//        return childrenOrg;
//    }
//
//
//    /**
//     * 根据上级组织id得到组织编号
//     *
//     * @param id 上级组织id
//     * @return
//     */
//    @Override
//    public String getOrgNumberByPid(String id, String updateId) {
//        //初始化编号
//        String initializeNumber = "01";
//        //id为空则查出最高层级的编号
//        if (id == null || "".equals(id)) {
//            //获取最高层级最大的编号组织
//            Org org = this.baseMapper.getSuperOrgNumber();
//            //为空则表示一条数据都没有
//            if (org == null || "".equals(org)) {
//                return initializeNumber;
//            }
//            String pNumber = UtilCommon.judgeNumber(Integer.valueOf(org.getNumber()));
//            //构建返回值
//            return pNumber;
//        }
//        //获取上级组织对象
//        Org pOrg = this.baseMapper.selectById(id);
//        if (pOrg == null || "".equals(pOrg)) {
//            return null;
//        }
//        //获得上级组织下的所有组织
//        List<Org> orgList = this.baseMapper.getOrgByPNumber(id);
//        if (orgList.size() < 1) {
//            return pOrg.getNumber() + initializeNumber;
//        }
//        //修改时重复选取当前组织时
//        if (updateId != null && !"".equals(updateId)) {
//            for (Org org : orgList) {
//                if (updateId.equals(org.getId())) {
//                    return org.getNumber();
//                }
//            }
//        }
//        String getNumber = orgList.get(0).getNumber();
//        //根据统计数算出截取多少位字符串
//        int subSize = UtilCommon.getOrgNumberSubSize(orgList.size());
//        //获取最后编号
//        String number = getNumber.substring(getNumber.length() - subSize);
//        Integer numberInt = Integer.valueOf(number);
//        //比10小则加上字符串0
//        number = UtilCommon.judgeNumber(numberInt);
//        return pOrg.getNumber() + number;
//    }
//
//
//    /**
//     * 判断是否是地属支部
//     *
//     * @param id 组织id
//     * @return
//     */
//    @Override
//    public boolean judgeDirectlyBranchById(String id) {
//        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("number", StatusCode.GROUND_GENUS_BRANCH_NUMBER.code);
//        //获取地属支部
//        Org org = this.baseMapper.selectOne(queryWrapper);
//        //判断
//        if (org != null) {
//            if (org.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * 根据当前登录人获取党组织信息
//     *
//     * @return
//     */
//    @Override
//    public Org getOrgByCurrentLoginPerson() {
//        //获取当前登录人
//        String username = UserContext.current().getUsername();
//        //根据用户名获取党组织信息
//        Org org = this.baseMapper.getOrgByCurrentLoginPerson(username);
//        return org;
//    }

//    /**
//     * 根据当前登录人获取组织及其附属ids
//     *
//     * @return
//     */
//    @Override
//    public List<String> getBranchOrgIdsByCurrentLogin() {
//        Company company = this.getNumberByCurrentLogin();
//        if (company == null || "".equals(company)) {
//            return null;
//        }
//        //根据company编号获取到所有org信息
//        List<String> ids = this.baseMapper.getOrgIdsByCompanyNumber(company.getNumber());
//        return ids;
//    }
//
//    /**
//     * 根据当前登录人获取组织及其附属org对象
//     *
//     * @return
//     */
//    @Override
//    public List<TreeView> getBranchOrgTreeListByCurrentLogin() {
//        Company company = this.getNumberByCurrentLogin();
//        if (company == null || "".equals(company)) {
//            return null;
//        }
//        //根据company编号获取到所有org信息
//        List<TreeView> orgList = this.baseMapper.getBranchOrgTreeListByCurrentLogin(company.getNumber());
//        for (TreeView view : orgList) {
//            if (company.getNumber().equals(view.getIcon())) {
//                view.setPid("");
//            }
//        }
//        return orgList;
//    }
//
//    /**
//     * 根据当前登录人获取公司信息
//     *
//     * @return
//     */
//    @Override
//    public Object getNumberByCurrentLogin() {
////        //获取当前登录人信息
////        UserInfo current = UserContext.current();
////        //根据当前登录人的orgId获取公司信息
////        Company company = companyMapper.getCompanyByCurrentLoginUsername(current.getUsername());
////        return company;
//        return null;
//    }
//
//    /**
//     * 获取首页党组织类型占比
//     *
//     * @return
//     */
//    @Override
//    public List<Org> getLoginPartyOrg() {
//        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("id", "name", "number");
//        queryWrapper.isNotNull("pid");
//        List<Org> orgList = this.baseMapper.selectList(queryWrapper);
//        return orgList;
//    }
//

    /**
     * 获取支部管理党组织树
     *
     * @param org 查询条件
     * @return
     */
    @Override
    public List<Org> getBranchManagementTree(Org org) {
//        List<String> ids = this.getBranchOrgIdsByCurrentLogin();
        UserDetail userDetail = userDetailService.findByUserId(SecurityUtil.getCurrentUserId());
        List<String> ids = null;
        if(userDetail!=null){
            ids = this.baseMapper.getOrgidsByPid(userDetail.getOrgId());
            ids.add(userDetail.getOrgId());
        }
        List<Org> list = this.baseMapper.getBranchManagementTree(org, ids);
        if (list.size() > 0) {
            list.get(0).setPid("");
        }
        List<Org> orgList = toOrgTree(list);
        return orgList;
    }
//
//    /**
//     * 根据id获取上级组织对象
//     *
//     * @param orgId 组织id
//     * @return
//     */
//    @Override
//    public Org getSuperOrgByPid(String orgId) {
//        Org org = this.baseMapper.selectById(orgId);
//        //获取上级组织
//        if (org == null || "".equals(org)) {
//            return null;
//        }
//        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", org.getPid());
//        Org superOrg = this.baseMapper.selectOne(queryWrapper);
////        if (superOrg.getPid() == null || "".equals(superOrg.getPid())) {
////            Org result = new Org();
////            result.setName(org.getGroundBranch());
////            return result;
////        }
//        return superOrg;
//    }
//
//
    /**
     * 查询案件案由 -> 递归
     *
     * @param list
     * @return
     */
    private List<Org> toOrgTree(List<Org> list) {
        //初始化树状结果返回值
        List<Org> treeList = new ArrayList<>();
        //获取最上层级id集合
        List<String> superList = toSuperList(list);
        for (Org org : list) {
            if (superList.contains(org.getId())) {
                //递归出最上层级下的所有子集合
                Org result = toChildren(org, list);
                //不为null则添加进结构数据中返回
                if (result != null && !"".equals(result)) {
                    treeList.add(org);
                }
            }
        }
        return treeList;
    }

    /**
     * 查询案件案由 -> 递归 -> 递归出最上层级下的所有子集合
     *
     * @param list
     * @return
     */
    private List<String> toSuperList(List<Org> list) {
        //创建储存所有ids的集合
        List<String> ids = new ArrayList<>();
        //创建ids返回集合
        List<String> superList = new ArrayList<>();
        for (Org org : list) {
            ids.add(org.getId());
        }
        for (Org org : list) {
            //循环出没有pid的对象并添加进返回集合中
            if (!ids.contains(org.getPid())) {
                superList.add(org.getId());
            }
        }
        return superList;
    }

    /**
     * 查询案件案由 -> 递归 -> 获取最上层级id集合
     *
     * @param org
     * @param orgList
     * @return
     */
    private Org toChildren(Org org, List<Org> orgList) {
        for (Org key : orgList) {
            //判断是否有子项
            if (org.getId().equals(key.getPid())) {
                //如果没有储存子项的子集合则给他创建一个
                if (org.getChildren() == null) {
                    org.setChildren(new ArrayList<>());
                }
                //进入递归算法,进入下一层及寻找所有子项并将他们添加进子集合
                org.getChildren().add(toChildren(key, orgList));
            }
        }
        return org;
    }
//
//    /**
//     * 根据当前登录人获取组织层级结构
//     *
//     * @return
//     */
//    @Override
//    public List<Org> getOrgHierarchyByCurrentLogin() {
//        //获取当前登录人
//        String userId = UserContext.current().getUserId();
//        User user = userService.findById(userId);
//        if (user == null || "".equals(user)) {
//            //当前用户不存在
//            throw new SystemException(0, "");
//        }
//        //根据当前登录人的org_id获取他的组织层级
//        Org org = this.baseMapper.selectById(user.getOrgId());
//        //获取上级组织做判断
//        Org superOrg = findOrgByPid(org.getPid());
//        if (superOrg == null || "".equals(superOrg)) {
//            //当前用户组织信息有误
//            throw new SystemException(0, "");
//        }
//        //判断当前登陆人所在组织位于组织层级哪一位置
//        if (org.getBranchType() == 1) {
//            //1.地属支部只能查看自己党组织
//            //getById();
//            List<Org> orgList = new ArrayList<>();
//            orgList.add(org);
//            return orgList;
//        } else if (superOrg.getPid() == null || "".equals(superOrg.getPid())) {
//            //2.一级组织查看所有党组织
//            QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//            List<Org> list = this.baseMapper.selectList(queryWrapper);
//            List<Org> orgList = toOrgTree(list);
//            return orgList;
//        } else {
//            //3.一级党组织除外的直属党组织可以查看下级所有直属党组织
//            //getBranchManagementTree();->按公司层级
//            QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//            queryWrapper.likeRight("number", org.getNumber());
//            List<Org> list = this.baseMapper.selectList(queryWrapper);
//            List<Org> orgList = toOrgTree(list);
//            return orgList;
//        }
//    }
//
//    /**
//     * 获取下层组织
//     *
//     * @param ids
//     * @return
//     */
//    @Override
//    public List<TreeView> getLowerLevelOrg(List<String> ids) {
//        //下级组织
//        return null;
//    }
//
//    /**
//     * 根据pid查询组织
//     *
//     * @param pid 上级id
//     * @return
//     */
//    public Org findOrgByPid(String pid) {
//        //构建条件构造器
//        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
//        //pid
//        queryWrapper.eq("id", pid);
//        //查询组织
//        Org superOrg = this.baseMapper.selectOne(queryWrapper);
//        return superOrg;
//    }
//
//    /**
//     * 获取当前登录人党组织是否为党委
//     *
//     * @param userId 当前登录人id
//     * @return
//     */
//    @Override
//    public String findIsNotPartyCommittee(String userId) {
//        //获取当前登录人党组织是否为党委
//        Org org = this.baseMapper.getOrgByUserId(userId);
//        //获取上级组织做判断
//        Org superOrg = findOrgByPid(org.getPid());
//        //为空则为党委,不为空则为党组织
//        if (superOrg.getPid() != null && !"".equals(superOrg.getPid())) {
//            return org.getId();
//        }
//        return "";
//    }
//
//    /**
//     * 获取大屏组织列表
//     *
//     * @return
//     */
//    @Override
//    public List<Org> getLoginOrgList() {
//        LambdaQueryWrapper<Org> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        //排除直属地属
//        lambdaQueryWrapper.isNotNull(Org::getPid);
//        //只查询党支部
//        lambdaQueryWrapper.eq(Org::getType, StatusCode.PARTY_BRANCH.code);
//        //查询字段
//        lambdaQueryWrapper.select(Org::getId, Org::getName, Org::getPid, Org::getCompanyId, Org::getNumber, Org::getType);
//        lambdaQueryWrapper.orderByDesc(Org::getVersion);
//        //查询列表
//        List<Org> orgList = this.baseMapper.selectList(lambdaQueryWrapper);
//        for (Org org : orgList) {
//            org.setOrgType(userService.toOrgType(org.getType()));
//        }
//        return orgList;
//    }
//
//
//    /**
//     * 根据工作部署
//     *
//     * @param issueRange 发布范围ids
//     * @return
//     */
//    @Override
//    public List<Org> workDeploymentIssueRange(String issueRange) {
//        //为空
//        if (issueRange == null || "".equals(issueRange)) {
//            return new ArrayList<>();
//        }
//        String[] issueRangeArray = issueRange.split(",");
//        LambdaQueryWrapper<Org> queryWrapper = new LambdaQueryWrapper<>();
//        //发布范围
//        queryWrapper.in(Org::getId,issueRangeArray);
//        //查询字段
//        queryWrapper.select(Org::getId,Org::getName);
//        List<Org> orgList = this.baseMapper.selectList(queryWrapper);
//        return orgList;
//    }
//
//    @Override
//    public OrgListCountVo getLoginCount() {
//        OrgListCountVo ov=new OrgListCountVo();
//        //总之
//      Map<String, Object> loginCount = this.baseMapper.getLoginCount();
//      ov.setTotal((Long) loginCount.get("count"));
//        Map<String, Object> loginCounts = this.baseMapper.getLoginCounts();
//        ov.setZhishuNum((Long) loginCounts.get("count"));
//        Map<String, Object> loginCountz = this.baseMapper.getLoginCountz();
//        ov.setDishuNum((Long) loginCountz.get("count"));
//        return ov;
//    }

    /**
     * 获取三会一课信息
     * @return
     */
    @Override
    public List<OrgEventTotal> getOrgInfo(String orgId) {
//        //获取当前登录人
//        String userId = UserContext.current().getUserId();
//        User user = userService.findById(userId);
//        if (user == null) {
//            //当前用户不存在
//            throw new SystemException(0, "");
//        }
//        //根据当前登录人的org_id获取他的组织信息
        Org org = this.baseMapper.selectById(orgId);

        List<OrgEventTotal> list=new ArrayList<>();
        Integer total=0;
        for (int i = 0; i < 3; i++) {
            OrgEventTotal orgEventTotal=new OrgEventTotal();
            orgEventTotal.setBranchType(org.getBranchType());
            orgEventTotal.setType(String.valueOf(i));
            List<OrgEvent> orgEventList = getOrgEventListByTypeOrOrgId(String.valueOf(i), org.getId());
            if(i==0){
                orgEventTotal.setName("党员大会");
            }else if(i==1){
                orgEventTotal.setName("支委会");
            }else if(i==2){
                orgEventTotal.setName("党小组会");
            }else if(i==3){
                orgEventTotal.setName("党课");
            }
            if(orgEventList==null){
                orgEventTotal.setCount(0);
            }else {
                orgEventTotal.setCount(orgEventList.size());
            }
            list.add(orgEventTotal);
            total+=orgEventTotal.getCount();
        }
        OrgEventTotal orgEventTotal4=new OrgEventTotal();
        //orgEventTotal4.setName("学习教案");
        orgEventTotal4.setName("党课");
        orgEventTotal4.setType("3");
        orgEventTotal4.setBranchType(org.getBranchType());

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
//        QueryWrapper<TeachingPlan> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("org_id", org.getId());
//        queryWrapper.like("create_time",String.valueOf(year));
//        queryWrapper.eq("deleted",0);
//        List<TeachingPlan> teachingPlanList = teachingPlanMapper.selectList(queryWrapper);
//        if(teachingPlanList==null){
//            orgEventTotal4.setCount(0);
//        }else {
//            orgEventTotal4.setCount(teachingPlanList.size());
//        }
//        list.add(orgEventTotal4);
//        total+=orgEventTotal4.getCount();

        for (OrgEventTotal orgEventTotal : list) {
            if(total==0){
                orgEventTotal.setPercent(0);
            }else {
                BigDecimal bdTotal = new BigDecimal(String.valueOf(total));
                BigDecimal bd = new BigDecimal(String.valueOf(orgEventTotal.getCount()));
                BigDecimal percent = bd.divide(bdTotal, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
                orgEventTotal.setPercent(percent.intValue());
            }
        }
        return list;
    }
//
    /**
     * 查询三会一课列表
     * @param type
     * @param orgId
     * @return
     */
    public List<OrgEvent> getOrgEventListByTypeOrOrgId(String type,String orgId){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        QueryWrapper<OrgEvent> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("org_id",orgId);
        queryWrapper.like("event_time",String.valueOf(year));
        queryWrapper.eq("type",type);
        queryWrapper.eq("deleted",0);
        return orgEventMapper.selectList(queryWrapper);
    }
//
//    /**
//     * 查询组织树及其下的人员
//     * @param t
//     * @return
//     */
//    @Override
//    public OrgPersonVo getOrgTreeAndUsers(OrgPersonVo t) {
//        OrgPersonVo vo=null;
//        String orgId = t.getId();
//        if(StringUtils.isEmpty(orgId)){
//            String userId = UserContext.current().getUserId();
//            User user = userService.getById(userId);
//            orgId=user.getOrgId();
//        }
//        Org org = this.baseMapper.selectById(orgId);
//        //查询当前组织及其子组织
//        List<OrgPersonVo> orgPersonVoList=this.baseMapper.findOrgTree(org);
//        //根据组织id查询所属成员
//        for (OrgPersonVo orgPersonVo : orgPersonVoList) {
//            UserDetail userDetail=new UserDetail();
//            userDetail.setOrgId(orgPersonVo.getId());
//            List<UserDetail> userDetailList = userDetailMapper.getPersonByOrgId(userDetail);
//            orgPersonVo.setUserDetails(userDetailList);
//            if(orgPersonVo.getId().equals(orgId)){
//                vo=orgPersonVo;
//            }
//        }
//        //组装组织树
//        vo = getOrgPersonVoListToTree(vo, orgPersonVoList);
//
//        return vo;
//    }
//
//    public OrgPersonVo getOrgPersonVoListToTree(OrgPersonVo parentVo,List<OrgPersonVo> list){
//        List<OrgPersonVo> children=new ArrayList<>();
//        if(list!=null&&list.size()>0){
//            for (OrgPersonVo orgPersonVo : list) {
//                if(orgPersonVo.getPid().equals(parentVo.getId())){
//                    children.add(getOrgPersonVoListToTree(orgPersonVo,list));
//                }
//            }
//        }
//        parentVo.setChildren(children);
//        return parentVo;
//    }
//
//    /**
//     * 根据公司层级关系查询组织树及其下的人员
//     * @param t
//     * @return
//     */
//    @Override
//    public TreeView findOrgTreeAndUsers(OrgPersonVo t) {
//        TreeView treeView=new TreeView();
//        String orgId = t.getId();
//        if(StringUtils.isEmpty(orgId)){
//            String userId = UserContext.current().getUserId();
//            User user = userService.getById(userId);
//            orgId=user.getOrgId();
//        }
//        Org org = this.baseMapper.selectById(orgId);
//        Company company = companyMapper.selectById(org.getCompanyId());
//        if(company==null){
//            return treeView;
//        }
//        //查询当前组织及其子组织
//        List<TreeView> orgList=this.baseMapper.findOrgTreeAndUsers(company.getNumber(),org);
//        //根据组织id查询所属成员
//        for (TreeView view : orgList) {
//            if(view.getPid()==null){
//                view.setPid("");
//            }
//            view.setType("org");
//            List<TreeView> children = userMapper.findUsersByOrgId(view.getIcon());
//            for (TreeView child : children) {
//                child.setType("user");
//            }
//            view.setChildren(children);
//            if(view.getIcon().equals(orgId)){
//                treeView=view;
//            }
//        }
//        getTreeViewListToTree(treeView,orgList);
//        return treeView;
//    }
//
//    @Override
//    public Integer CountUserByOrgId(HashSet<String> orgIds) {
//        return userMapper.CountUserByOrgId(orgIds);
//    }
//
    @Override
    public Org getBranchManagement() {
        List<Org> orgList=new ArrayList<>();
        //中国再生资源有限公司党委
        LambdaQueryWrapper<Org> org=new LambdaQueryWrapper<>();
        org.eq(Org::getBranchType,"0");
        Org org1 = baseMapper.selectOne(org);
        //添加云图支部
        LambdaQueryWrapper<Org> orgChined=new LambdaQueryWrapper<>();
        orgChined.eq(Org::getBranchType,"1");
        List<Org> zb = baseMapper.selectList(orgChined);
        org1.setChildren(zb);
        return org1;
    }
//
//    public TreeView getTreeViewListToTree(TreeView parentView,List<TreeView> list){
//        List<TreeView> children = parentView.getChildren();
//        if(list!=null&&list.size()>0){
//            for (TreeView treeView : list) {
//                if(treeView.getPid().equals(parentView.getId())){
//                    children.add(getTreeViewListToTree(treeView,list));
//                }
//            }
//        }
//        return parentView;
//    }
//
//    /**
//     * 获取当前组织的子组织id集合(党支部)
//     * @param orgId 组织id
//     * @param type 0党组织 1团组织 2工会
//     * @return
//     */
//    @Override
//    public ArrayList<String> getIdsByOrgId(String orgId, String type){
//        Org org = this.baseMapper.selectById(orgId);
//        Company company = companyMapper.selectById(org.getCompanyId());
//        if(company==null){
//            throw new SystemException(YTSystemStatusEnum.COMPANY_IS_NULL.code, YTSystemStatusEnum.COMPANY_IS_NULL.desc);
//        }
//        //根据公司id获取子级公司所绑定的组织id
//        ArrayList<String> ids=companyMapper.getOrgIdsByCompanyNumber(company.getNumber(),type);
//        return ids;
//    }
//
//    @Override
//    public List<Org> findListByCompanyId(String id) {
//        QueryWrapper<Org> wrapper = new QueryWrapper<>();
//        wrapper.eq("company_id",id);
//        return  this.baseMapper.selectList(wrapper);
//    }
}
