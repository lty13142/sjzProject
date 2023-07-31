package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.sjz.partbuilding.mapper.OrgPersonMapper;
import com.sjz.partbuilding.model.entity.*;
import com.sjz.partbuilding.model.vo.LeaderVo;
import com.sjz.partbuilding.model.vo.PersonTreeVo;
import com.sjz.partbuilding.model.vo.TreeViewVo;
import com.sjz.partbuilding.model.vo.UserDetailVo;
import com.sjz.partbuilding.service.*;
import com.sjz.partbuilding.util.UtilTreeEx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
@Transactional
public class OrgPersonServiceImpl extends ServiceImpl<OrgPersonMapper, OrgPerson> implements OrgPersonService {

//    @Resource
//    private UserService userService;
//
//    @Resource
//    private UserMapper userMapper;

    @Resource
    private OrgService orgService;

    @Resource
    private UserDetailService userDetailService;

    @Resource
    private LeaderService leaderService;

    @Resource
    private AttachmentsService attachmentsService;

    @Override
    public int saveOrgPerson(OrgPerson orgPerson) {
        if(StringUtils.isNotEmpty(orgPerson.getUserId())){
//            User user = userService.findById(orgPerson.getUserId());
//            orgPerson.setName(user.getUsername());
//            orgPerson.setUserName(user.getUsername());

           /* QueryWrapper<OrgPerson> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("org_id",orgPerson.getOrgId());
            queryWrapper.eq("user_id",orgPerson.getUserId());
            List<OrgPerson> list = this.baseMapper.selectList(queryWrapper);
            if(list.size()>0){
                return 0;
            }*/
        }

        if(StringUtils.isNotEmpty(orgPerson.getPid())){

            OrgPerson person = this.baseMapper.selectById(orgPerson.getPid());
            orgPerson.setPName(person.getName());
        }
        Org org = orgService.getById(orgPerson.getOrgId());
        orgPerson.setOrgName(org.getName());

        if(StringUtils.isNotEmpty(orgPerson.getId())){
            return this.baseMapper.updateById(orgPerson);
        }
        return this.baseMapper.insert(orgPerson);
    }

    @Override
    public int updateOrgPerson(OrgPerson orgPerson) {
        return this.baseMapper.updateById(orgPerson);
    }

    @Override
    public int deleteById(String id) {
        //如果是组长，删除整个组
        OrgPerson person = this.baseMapper.selectById(id);
        QueryWrapper<OrgPerson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",person.getId());
        this.baseMapper.delete(queryWrapper);
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public OrgPerson findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<OrgPerson> findList(OrgPerson orgPerson) {
        QueryWrapper<OrgPerson> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(orgPerson.getOrgId())){
            queryWrapper.eq("org_id",orgPerson.getOrgId());
        }
        if(StringUtils.isNotEmpty(orgPerson.getType())){
            queryWrapper.eq("type",orgPerson.getType());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<OrgPerson> findPage(Page page, OrgPerson orgPerson) {
        if(StringUtils.isEmpty(orgPerson.getOrgId())){
//            List<String> ids = orgService.getBranchOrgIdsByCurrentLogin();
//            String orgId = "";
//            for (String str:ids) {
//                orgId = orgId + str + ",";
//            }
//            orgId = orgId.substring(0, orgId.lastIndexOf(','));
//            orgPerson.setOrgId(orgId);
        }
        IPage<OrgPerson> pageOrgPerson =this.baseMapper.findPage(page,orgPerson);
        return pageOrgPerson;
    }

    @Override
    public PersonTreeVo getOrgPersonTree(OrgPerson orgPerson) {
        PersonTreeVo treeVo = new PersonTreeVo();
        //查询班子成员
        Leader leader = new Leader();
        leader.setOrgId(orgPerson.getOrgId());
        List<LeaderVo> list = leaderService.findList(leader);

        for (LeaderVo vo:list) {
            vo.setLabel(vo.getUserName());
            // 获取头像
            UserDetailVo detail = userDetailService.getDetail(vo.getUserId());
            if (null != detail) {
                vo.setFacePic(detail.getFacePic());
                if (StringUtils.isNotBlank(detail.getFacePicPath())) {
                    // 由于数据库有存附件id的，也有直接存路径的，加判断
                    if (detail.getFacePicPath().startsWith("http")) {
                        vo.setAvatar(detail.getFacePicPath());
                    } else {
                        Attachments attachments = attachmentsService.getById(detail.getFacePic());
                        vo.setAvatar(attachments.getMinioPath());
                    }
                }
            }
        }

        //查询组织人员树
        List<TreeViewVo> listTree = this.baseMapper.selectTreeView(orgPerson);
        if(!listTree.isEmpty()){
            listTree = UtilTreeEx.listToTree(listTree);
        }else {
            listTree = userDetailService.findUsersByOrgId2(orgPerson.getOrgId());
            if(listTree!=null && !listTree.isEmpty()){
                Iterator<TreeViewVo> iterator1 = listTree.iterator();
                while (iterator1.hasNext()){
                    TreeViewVo treeViewVo = iterator1.next();
                    for (LeaderVo leaderVo : list) {
                            if(treeViewVo.getUserId().equals(leaderVo.getUserId())){
                                iterator1.remove();
                            }
                        }
                    treeViewVo.setType("2");

                }
            }
        }


        Map<String,Object> childMap = new HashMap<>();
        childMap.put("label","班子成员");
        childMap.put("personList",list);
        childMap.put("children",listTree);

        Org org = orgService.getById(orgPerson.getOrgId());
        treeVo.setLabel(org.getName());
        List list1 = new ArrayList();
        list1.add(childMap);
        treeVo.setChildren(list1);

        return treeVo;
    }

    @Override
    public List<OrgPerson> getPartyGroup() {
        String userId = SecurityUtil.getCurrentUserId();
        UserDetail userDetail = userDetailService.getById(userId);
        List<OrgPerson> list=this.baseMapper.getPartyGroup(userDetail.getOrgId());
        return list;
    }
}
