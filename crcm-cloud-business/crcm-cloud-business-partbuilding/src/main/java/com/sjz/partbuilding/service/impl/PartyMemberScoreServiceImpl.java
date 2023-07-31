package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.mapper.PartyMemberScoreMapper;
import com.sjz.partbuilding.model.entity.PartyMemberScore;
import com.sjz.partbuilding.model.vo.PartyScoreVo;
import com.sjz.partbuilding.service.PartyMemberScoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.*;


@Slf4j
@Service
@Transactional
public class PartyMemberScoreServiceImpl extends ServiceImpl<PartyMemberScoreMapper, PartyMemberScore> implements PartyMemberScoreService {


    @Override
    public int savePartyMemberScore(PartyMemberScore partyMemberScore) {
        return this.baseMapper.insert(partyMemberScore);
    }

    @Override
    public int updatePartyMemberScore(PartyMemberScore partyMemberScore) {
        return this.baseMapper.updateById(partyMemberScore);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public PartyMemberScore findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<PartyMemberScore> findList(PartyMemberScore PartyMemberScore) {
        QueryWrapper<PartyMemberScore> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("deleted",0);
        queryWrapper.orderByDesc("create_time");
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<PartyMemberScore> findPage(Page page,PartyMemberScore PartyMemberScore) {
        QueryWrapper<PartyMemberScore> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(PartyMemberScore.getName())){
            queryWrapper.like("name",PartyMemberScore.getName());
        }
        if(StringUtils.isNotBlank(PartyMemberScore.getUserId())){
            queryWrapper.like("user_id",PartyMemberScore.getUserId());
        }
        queryWrapper.eq("deleted",0);
        queryWrapper.orderByDesc("create_time");
        IPage<PartyMemberScore> pagePartyMemberScore =this.baseMapper.selectPage(page,queryWrapper);
        return pagePartyMemberScore;
    }

    @Override
    public IPage<PartyScoreVo> getScorePage(Page page, PartyMemberScore partyMemberScore) {
        IPage<PartyScoreVo> pageList = this.baseMapper.findScorePage(page,partyMemberScore);
        return pageList;
    }
}
