package com.sjz.evaluations.service;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.evaluations.model.entity.GrRewardPunishment;
import com.sjz.evaluations.model.vo.RewardPunishmentVo;

import java.util.List;
import java.util.Map;


/**
 * 奖惩事项Service接口
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
public interface GrRewardPunishmentService extends IService<GrRewardPunishment>{

    /**
     * 新增奖惩事项
     * 
     * @param grRewardPunishment 奖惩事项
     * @return 结果
     */
    boolean saveGrRewardPunishment(GrRewardPunishment grRewardPunishment);

    /**
     * 修改奖惩事项
     * 
     * @param grRewardPunishment 奖惩事项
     * @return 结果
     */
    boolean updateGrRewardPunishment(GrRewardPunishment grRewardPunishment);

    /**
     * 删除奖惩事项信息
     * 
     * @param name 奖惩事项ID
     * @return 结果
     */
    boolean deleteGrRewardPunishment(String name);

    /**
     * 查询奖惩事项
     *
     * @param name 奖惩事项ID
     * @return 奖惩事项
     */
    GrRewardPunishment findGrRewardPunishmentById(String name);

    /**
     * 查询奖惩事项列表
     *
     * @param grRewardPunishment 奖惩事项
     * @return 奖惩事项集合
     */
    List<GrRewardPunishment> findGrRewardPunishmentList(GrRewardPunishment grRewardPunishment);

    /**
     * 分页查询奖惩事项列表
     * @param page 分页参数
     * @param grRewardPunishment 奖惩事项
     * @return 奖惩事项集合
     */
    PageT<GrRewardPunishment> findGrRewardPunishmentPage(PageT<GrRewardPunishment> page, GrRewardPunishment grRewardPunishment);

    List< Map<String,Object>> penaltyStatistics();
}
