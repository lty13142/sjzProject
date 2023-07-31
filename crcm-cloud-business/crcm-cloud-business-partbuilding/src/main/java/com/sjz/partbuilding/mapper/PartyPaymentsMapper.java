package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.PartyPayments;
import com.sjz.partbuilding.model.vo.PartyPaymentsVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface PartyPaymentsMapper extends BaseMapper<PartyPayments> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 获取当前结余
     * @param partyPayments
     * @return
     */
    BigDecimal getBalance(PartyPayments partyPayments);

    /**
     * 分页查询
     * @param page
     * @param partyPayments
     * @return
     */
    IPage<PartyPaymentsVo> getPage(@Param("page") Page page, @Param("t") PartyPaymentsVo partyPayments);

    /**
     * 查询收支明细
     * @param partyPayments
     * @return
     */
    List<PartyPaymentsVo> getPage(@Param("t") PartyPaymentsVo partyPayments);
    /**
     * 统计当年收支
     */
    BigDecimal findPaymentsByYear(@Param("t") PartyPaymentsVo partyPaymentsVo);

    /**
     * 统计该组织及子组织汇总当年收支
     * @param paymentsVo
     * @return
     */
    BigDecimal findPaymentsByYear2(@Param("t") PartyPaymentsVo paymentsVo, @Param("ids")ArrayList<String> ids);

    /**
     * 统计当年每月收入支出
     * @param partyPaymentsVo
     * @return
     */
    List<Map> getPaymentsByMonth(@Param("t") PartyPaymentsVo partyPaymentsVo);

    /**
     * 首页查询年度党建经费列表
     * @param page
     * @param partyPayments
     * @return
     */
    IPage<PartyPaymentsVo> findPartyPaymentsPage(@Param("page") Page page, @Param("t") PartyPaymentsVo partyPayments);


}
