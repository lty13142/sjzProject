package com.sjz.partbuilding.service.impl;

import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.dto.TreeView;
import com.sjz.partbuilding.mapper.LeaderMapper;
import com.sjz.partbuilding.mapper.OrgMapper;
import com.sjz.partbuilding.mapper.UserDetailMapper;
import com.sjz.partbuilding.model.entity.Leader;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.LeaderVo;
import com.sjz.partbuilding.model.vo.user.LoginOrgVo;
import com.sjz.partbuilding.service.UserDetailService;
import com.sjz.partbuilding.service.UserPartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserPartServiceImpl implements UserPartService {

    @Resource
    private UserDetailMapper userDetailMapper;
    @Resource
    private OrgMapper orgMapper;
    @Resource
    private LeaderMapper leaderMapper;
    @Autowired
    private UserDetailService userDetailService;


    @Override
    public List<TreeView> getUserByType(OrgEvent orgEvent, String orgPersonId) {
        List<TreeView> list=new ArrayList<>();
        String userId = SecurityUtil.getCurrentUser().getUserId();
        UserDetail user = userDetailMapper.findByUserId(userId);
        if(user!=null){
            String orgId = user.getOrgId();
            Org org = orgMapper.selectById(orgId);
            TreeView treeView=new TreeView();
            treeView.setId(orgId);
            treeView.setLabel(org.getName());
            treeView.setPid("");
            treeView.setType("org");
            List<TreeView> userList = userDetailMapper.findUsersByOrgId(orgId);
            for (TreeView view : userList) {
                view.setType("user");
            }
            treeView.setChildren(userList);
            list.add(treeView);
        }
//        //"0"代表党员大会
//        if("0".equals(orgEvent.getType())){
//            TreeView treeView=new TreeView();
//            treeView.setId(orgId);
//            treeView.setLabel(org.getName());
//            treeView.setPid("");
//            treeView.setType("org");
//            List<TreeView> userList = userDetailMapper.findUsersByOrgId(orgId);
//            for (TreeView view : userList) {
//                view.setType("user");
//            }
//            treeView.setChildren(userList);
//            list.add(treeView);
//        }
//        //"1"代表支委会
//        else if("1".equals(orgEvent.getType())){
//            TreeView treeView=new TreeView();
//            treeView.setId(orgId);
//            treeView.setLabel("班子成员");
//            treeView.setPid("");
//            treeView.setType("org");
//            List<TreeView> userList=new ArrayList<>();
//            Leader leader=new Leader();
//            leader.setOrgId(orgId);
//            List<LeaderVo> leaderVoList = leaderMapper.getList(leader);
//            for (LeaderVo leaderVo : leaderVoList) {
//                TreeView view=new TreeView();
//                view.setId(leaderVo.getUserId());
//                view.setLabel(leaderVo.getUserName());
//                view.setPid(leaderVo.getOrgId());
//                view.setType("user");
//                userList.add(view);
//            }
//            treeView.setChildren(userList);
//            list.add(treeView);
//        }
//        else if("2".equals(orgEvent.getType())){
//            OrgPerson orgPerson = orgPersonMapper.selectById(orgPersonId);
//            if(orgPerson==null){
//                return new ArrayList<>();
//            }
//            TreeView treeView=new TreeView();
//            treeView.setId(orgPersonId);
//            treeView.setLabel(orgPerson.getName());
//            treeView.setPid("");
//            treeView.setType("orgPerson");
//            List<TreeView> userList=new ArrayList<>();
//            List<TreeView> groupLeader = orgPersonMapper.getPartyGroupUsers(orgPersonId, orgId);
//            if(groupLeader.size()<1){
//                return new ArrayList<>();
//            }
//            TreeView treeView1 = groupLeader.get(0);
//            treeView1.setType("user");
//            userList.add(treeView1);
//            List<TreeView> groupUsers = orgPersonMapper.getPartyGroupUsers(treeView1.getIcon(), orgId);
//            for (TreeView groupUser : groupUsers) {
//                groupUser.setType("user");
//                userList.add(groupUser);
//            }
//            treeView.setChildren(userList);
//            list.add(treeView);
//        }
        return list;
    }

    @Override
    public LoginOrgVo getCurrentLoginOrg(String OrgId) {
        LoginOrgVo loginOrgVo = new LoginOrgVo();
        //获取当前登录人组织信息
        if (StringUtils.isEmpty(OrgId)) {
            UserDetail userDetail = userDetailService.findByUserId(SecurityUtil.getCurrentUser().getUserId());
            if(userDetail!=null){
                OrgId = userDetail.getOrgId();
            }
        }
        Org org = orgMapper.findById(OrgId);
        if(!ObjectUtils.isEmpty(org)) {
            loginOrgVo.setOrgName(org.getName());
            loginOrgVo.setCompanyId(org.getCompanyId());
            loginOrgVo.setOrgId(org.getId());
            loginOrgVo.setType(org.getType());
        }else {
            loginOrgVo.setOrgType(true);
        }
        if (loginOrgVo != null && !"".equals(loginOrgVo)) {
            loginOrgVo.setOrgType(this.toOrgType(loginOrgVo.getType()));
        }
        //按要求写死部分
        loginOrgVo.setOrgType(true);
//        if (loginOrgVo.getOrgName().contains("党支部")||loginOrgVo.getOrgName().contains("团支部")){
//            loginOrgVo.setSubPartyName(loginOrgVo.getOrgName().substring((loginOrgVo.getOrgName().length())-3));
//            loginOrgVo.setOrgName(loginOrgVo.getOrgName().substring(0,(loginOrgVo.getOrgName().length())-3));
//        }
        return loginOrgVo;
    }

    @Override
    public boolean toOrgType(String orgType) {
        //初始化党委和党总支
        String partyCommittee = "0";
        String generalPartyBranch = "1";
        if (partyCommittee.equals(orgType) || generalPartyBranch.equals(orgType)) {
            return true;
        } else {
            return false;
        }
    }
}
