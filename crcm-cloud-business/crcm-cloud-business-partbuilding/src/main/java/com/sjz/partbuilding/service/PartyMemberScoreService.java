package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.PartyMemberScore;
import com.sjz.partbuilding.model.vo.PartyScoreVo;

import java.util.List;

public interface PartyMemberScoreService extends IService<PartyMemberScore> {

    int savePartyMemberScore(PartyMemberScore PartyMemberScore);

    int updatePartyMemberScore(PartyMemberScore PartyMemberScore);

    int deleteById(String id);

    int realDelete(String id);

    PartyMemberScore findById(String id);

    List<PartyMemberScore> findList(PartyMemberScore PartyMemberScore);

    IPage<PartyMemberScore> findPage(Page page,PartyMemberScore PartyMemberScore);

    IPage<PartyScoreVo> getScorePage(Page page, PartyMemberScore PartyMemberScore);
}
