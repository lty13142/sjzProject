package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.dto.StatisticsDTO;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.model.entity.OrgEventCensus;
import com.sjz.partbuilding.model.vo.*;

import java.util.List;

public interface OrgEventService extends IService<OrgEvent> {

    int saveOrgEvent(OrgEvent orgEvent);

    int updateOrgEvent(OrgEvent orgEvent);

    int deleteById(String id);

    int realDelete(String id);

    OrgEventVo findById(String id);

    List<OrgEvent> findList(OrgEvent orgEvent);

    IPage<OrgEventVo> findPage(Page page, OrgEvent orgEvent);


    IPage<OrgEventVo> getMeets(Page page, String title);

    List<TypeVo> findTypeList(OrgEvent orgEvent);

    /**
     * 获取数据统计组织生活综合表
     *
     * @param orgEvent
     * @return
     */
    List<EventStatisticsVo> getStatisticsOrgEvent(OrgEvent orgEvent);

    IPage<OrgEvenVo> getFileOrgEvent(OrgEvent orgEvent, Page page);

    OrgMapVo findTypeMap();

    /**
     * 按月获取参训率
     *
     * @return
     */
    List<StatisticsDTO> getParticipationRateMonthly(String orgId);

    OrgEventCensus getOrgEventCensus(String userId);
}
