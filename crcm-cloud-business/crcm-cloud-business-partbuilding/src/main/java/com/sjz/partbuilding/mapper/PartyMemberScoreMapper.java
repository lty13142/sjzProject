package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.PartyMemberScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.partbuilding.model.vo.PartyScoreVo;
import org.apache.ibatis.annotations.Param;


public interface PartyMemberScoreMapper extends BaseMapper<PartyMemberScore> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 积分排名查询
     * @param page
     * @param partyMemberScore
     * @return
     */
    IPage<PartyScoreVo> findScorePage(@Param("page") Page page,@Param("t") PartyMemberScore partyMemberScore);
}
