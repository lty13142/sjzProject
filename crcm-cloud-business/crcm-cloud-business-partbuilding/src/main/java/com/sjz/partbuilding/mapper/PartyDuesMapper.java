package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.PartyDues;
import com.sjz.partbuilding.model.vo.PartyDuesExportVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface PartyDuesMapper extends BaseMapper<PartyDues> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);


    /**
     * 获取当前月和前11月的党费数据
     *
     * @param month
     * @return
     */
    List<Map<String, Object>> getMonthAndMoney(@Param("month") String month);

    /**
     * 获取查询的人员姓名
     * @param partyDues
     * @return
     */
    List<PartyDues> findDuesPerson(PartyDues partyDues);

    /**
     * 获取人员当年每月缴费情况
     * @param partyDues
     * @return
     */
    PartyDuesExportVo findMonthMoney(PartyDues partyDues);

    /**
     * 获取人员当年每月缴费情况2
     * @param partyDues
     * @return
     */
    PartyDuesExportVo findMonthMoney2(@Param("partyDues") PartyDues partyDues, @Param("years") String year);

    /**
     * 获取人员当年累计缴费
     * @param partyDues
     * @return
     */
    BigDecimal findMoneyYear(PartyDues partyDues);

    /**
     * 统计当年每月缴费
     * @param dues
     * @return
     */
    List<Map> getDuesByMonth(PartyDues dues);

    /**
     * 根据用户姓名和年份查询党费明细
     * @param dues
     */
    List<PartyDues> findPartyDuesByYear(PartyDues dues);

    /**
     * 分页条件查询
     * @param page
     * @param partyDues
     * @return
     */
    IPage<PartyDues> findPage(@Param("page") Page page, @Param("partyDues") PartyDues partyDues);

    /**
     * 条件查询列表
     * @param partyDues
     * @return
     */
    List<PartyDues> findPartyDuesList(@Param("partyDues") PartyDues partyDues);
}
