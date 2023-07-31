package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.sjz.partbuilding.enums.YTSystemStatusEnum;
import com.sjz.partbuilding.mapper.LeaderMapper;
import com.sjz.partbuilding.mapper.UserDetailMapper;
import com.sjz.partbuilding.model.entity.Leader;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.vo.LeaderVo;
import com.sjz.partbuilding.model.vo.UserDetailVo;
import com.sjz.partbuilding.model.vo.WarnVo;
import com.sjz.partbuilding.service.LeaderService;
import com.sjz.partbuilding.service.OrgService;
import com.sjz.partbuilding.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 领导班子serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 16:47
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LeaderServiceImpl extends ServiceImpl<LeaderMapper, Leader> implements LeaderService {

    @Resource
    private OrgService orgService;

    @Resource
    private UserDetailService userDetailService;

    @Resource
    private UserDetailMapper userDetailMapper;

    /**
     * 新增
     *
     * @param leader 领导班子
     * @return
     */
    @Override
    public WarnVo saveLeader(Leader leader) {
        List<LeaderVo> list = this.baseMapper.getList(leader);
        WarnVo warnVo=new WarnVo();
        warnVo.setWarn("0");
        warnVo.setMsg("成功");
        if(list!=null&&list.size()>0){
            for (LeaderVo leaderVo : list) {
                if(leaderVo.getUserId().equals(leader.getUserId())){
                    warnVo.setWarn("1");
                    warnVo.setMsg(YTSystemStatusEnum.LEADER_EXISTS.desc);
                    return warnVo;
                }
            }
        }
        this.baseMapper.insert(leader);
        //修改党支部或团支部班子成员用户详情
        if(!"3".equals(leader.getBranchType())){
            UserDetailVo detail = userDetailService.getDetail(leader.getUserId());
            if(detail!=null){
                detail.setPartyPosts(leader.getPostId());
                userDetailMapper.updateById(detail);
            }
        }
        return warnVo;
    }

    /**
     * 修改
     *
     * @param leader 领导班子
     * @return
     */
    @Override
    public WarnVo updateLeader(Leader leader) {
        List<LeaderVo> list = this.baseMapper.getList(leader);
        WarnVo warnVo=new WarnVo();
        warnVo.setWarn("0");
        warnVo.setMsg("成功");
        if(list!=null&&list.size()>0){
            for (LeaderVo leaderVo : list) {
                if(leaderVo.getId().equals(leader.getId())&&leaderVo.getUserId().equals(leader.getUserId())){
                    this.baseMapper.updateById(leader);
                    //修改党支部或团支部班子成员用户详情
                    if(!"3".equals(leader.getBranchType())){
                        UserDetailVo detail = userDetailService.getDetail(leader.getUserId());
                        if(detail!=null){
                            detail.setPartyPosts(leader.getPostId());
                            userDetailMapper.updateById(detail);
                        }
                    }
                    return warnVo;
                }
            }
            for (LeaderVo leaderVo : list) {
                if(leaderVo.getUserId().equals(leader.getUserId())){
                    warnVo.setWarn("1");
                    warnVo.setMsg(YTSystemStatusEnum.LEADER_EXISTS.desc);
                    return warnVo;
                }
            }
        }
        this.baseMapper.updateById(leader);
        //修改党支部或团支部班子成员用户详情
        if(!"3".equals(leader.getBranchType())){
            UserDetailVo detail = userDetailService.getDetail(leader.getUserId());
            if(detail!=null){
                detail.setPartyPosts(leader.getPostId());
                userDetailMapper.updateById(detail);
            }
        }
        return warnVo;
    }

    @Override
    public int deleteByUseridAndOrgid(String userId,String orgId) {
        return this.baseMapper.deleteByUseridAndOrgid(userId,orgId);
    }

    /**
     * 根据id删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    @Override
    public Leader findById(String id) {
        Leader leader = this.baseMapper.selectById(id);
        if (leader != null && !"".equals(leader)) {
            Org org = orgService.getById(leader.getOrgId());
            if (org != null && !"".equals(org)) {
                leader.setOrgName(org.getName());
            }
        }
        return leader;
    }

    /**
     * 查询列表
     *
     * @param leader 查询条件
     * @return
     */
    @Override
    public List<LeaderVo> findList(Leader leader) {
        return this.baseMapper.getList(leader);
    }

    /**
     * 分页条件
     *
     * @param page   分页参数
     * @param leader 查询条件
     * @return
     */
    @Override
    public IPage<Leader> findPage(Page page, Leader leader) {
        if(StringUtils.isEmpty(leader.getOrgId())){
            return null;
        }
        //获取领导班子集合
        List<Leader> leaderList = this.baseMapper.findPage(page, leader);
        if(leaderList!=null&&leaderList.size()>0){
            for (Leader leader1 : leaderList) {
                UserDetailVo userDetailVo = userDetailService.getDetail(leader1.getUserId());
                if(userDetailVo!=null){
                    leader1.setFacePic(userDetailVo.getFacePic());
                }
            }
        }
        page.setRecords(leaderList);
        return page;
    }

    /**
     * 查询当前登录人是否是当前组织支委会成员
     * @param leader
     * @return
     */
    @Override
    public Map getLeaderByOrgId(Leader leader) {
        HashMap<String, String> map = new HashMap<>();
        map.put("isLeader","0");
        String userId = SecurityUtil.getCurrentUser().getUserId();
        String orgId = leader.getOrgId();
        if(StringUtils.isNotEmpty(orgId)){
            List<LeaderVo> list = this.baseMapper.getList(leader);
            if(list!=null&&list.size()>0){
                for (LeaderVo leaderVo : list) {
                    if(StringUtils.isNotEmpty(leaderVo.getUserId())&&leaderVo.getUserId().equals(userId)){
                        map.put("isLeader","1");
                    }
                }
            }
        }
        return map;
    }
}
