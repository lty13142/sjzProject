package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.PartyPayments;
import com.sjz.partbuilding.model.vo.PartyPaymentsVo;
import com.sjz.partbuilding.model.vo.WarnVo;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PartyPaymentsService extends IService<PartyPayments> {

    WarnVo savePartyPayments(PartyPayments partyPayments);

    int updatePartyPayments(PartyPayments partyPayments);

    int updatePartyPayments2(PartyPayments partyPayments);

    int deleteById(String id);

    int realDelete(String id);

    PartyPayments findById(String id);

    List<PartyPayments> findList(PartyPayments partyPayments);

    IPage<PartyPaymentsVo> findPage(Page page, PartyPaymentsVo partyPayments);

    void exportPayments(PartyPaymentsVo vo, HttpServletResponse response);

    /**
     * 首页党建经费分页查询
     * @param initPage
     * @param partyPayments
     * @return
     */
    IPage<PartyPaymentsVo> findPartyPaymentsPage(Page initPage, PartyPaymentsVo partyPayments);
}
