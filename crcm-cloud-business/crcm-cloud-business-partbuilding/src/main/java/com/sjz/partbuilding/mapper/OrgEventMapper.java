package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.dto.StatisticsDTO;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.model.vo.EventStatisticsVo;
import com.sjz.partbuilding.model.vo.OrgEvenVo;
import com.sjz.partbuilding.model.vo.OrgEventVo;
import com.sjz.partbuilding.model.vo.OrgEventsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrgEventMapper extends BaseMapper<OrgEvent> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 自定义page查询
     * @param page
     * @param orgEvent
     * @return
     */
    IPage<OrgEventVo> selectCustomerPage(Page page, @Param("vo") OrgEvent orgEvent);


    /**
     * 自定义单挑查询
     * @param id
     * @return
     */
    OrgEventVo selectCustomerById(@Param("id") String id);

    /**
     * 查询所有类型为会议的数据
     * @param page
     * @param title
     * @return
     */
    IPage<OrgEventVo> selectMeets(Page page, @Param("title") String title, @Param("orgId") String orgId);

    /**
     * 获取当前月和前11月的组织活动
     * @param orgEvent
     * @return
     */
    List<EventStatisticsVo> getMonthStatisticsOrgEvent(@Param("orgEvent") OrgEvent orgEvent);

    /**
     * @param orgEvent
     * @param page
     * @return
     */
    IPage<OrgEvenVo> selectLists(Page page, @Param("orgEvent") OrgEvent orgEvent);

    List<OrgEventsVo> findTypeMap();

    /**
     * 查询所有组织活动的活动时间和参训率
     *
     * @return
     */
    List<StatisticsDTO> getParticipationRate(@Param("orgId") String orgId);
}
