package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.entity.GrRewardPunishment;
import com.sjz.evaluations.model.vo.RewardPunishmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 奖惩事项Mapper接口
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
public interface GrRewardPunishmentMapper extends BaseMapper<GrRewardPunishment> {


    PageT<GrRewardPunishment> findGrRewardPunishmentPage(PageT<GrRewardPunishment> page, @Param("gr") GrRewardPunishment grRewardPunishment);

    List<RewardPunishmentVo> penaltyStatistics();
}
