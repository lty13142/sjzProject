package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.PartyDues;
import com.sjz.partbuilding.model.entity.PartyOrg;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.EventStatisticsVo;
import com.sjz.partbuilding.model.vo.PartyOrgVo;
import com.sjz.partbuilding.model.vo.PartyPaymentsVo;
import com.sjz.partbuilding.model.vo.WorkFeedBackVo;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 党组织详细信息service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 16:52
 */
public interface PartyOrgService extends IService<PartyOrg> {

    /**
     * 新增
     *
     * @param partyOrg 党组织详细信息
     * @return
     */
    int savePartyOrg(PartyOrg partyOrg);

    /**
     * 修改
     *
     * @param partyOrg 党组织详细信息
     * @return
     */
    int updatePartyOrg(PartyOrg partyOrg);

    /**
     * 根据id删除
     *
     * @param id 删除条件
     * @return
     */
    int deleteById(String id);

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    int realDelete(String id);

    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    PartyOrg findById(String id);

    /**
     * 查询列表
     *
     * @param partyOrg 查询参数
     * @return
     */
    List<PartyOrg> findList(PartyOrg partyOrg);

    /**
     * 分页查询
     *
     * @param page     分页参数
     * @param partyOrg 查询参数
     * @return
     */
    IPage<PartyOrg> findPage(Page page, PartyOrg partyOrg);

    /**
     * 根据orgId获取基本信息
     *
     * @param orgId 党组织id
     * @return
     */
    PartyOrg getBaseMessageByOrgId(String orgId, String pid);

    /**
     * 获取数据统计党组织信息表
     *
     * @param partyOrg 查询条件
     * @return
     */
    List<PartyOrgVo> getStatisticsPartyOrg(PartyOrg partyOrg);

    /**
     * 通过ID查询组织信息
     *
     * @param orgId
     * @return
     */
    PartyOrgVo getPartyOrgCompany(String orgId);

    /**
     * 工作督办导出
     *
     * @param vo
     * @param response
     */
    void exportWorkSupervise(WorkFeedBackVo vo, HttpServletResponse response);

    /**
     * 组织生活导出
     *
     * @param vo
     * @param response
     */
    void exportOrganizationLife(EventStatisticsVo vo, HttpServletResponse response);

    /**
     * 党费综合导出
     *
     * @param vo
     * @param response
     */
    void exportPartyDues(PartyDues vo, HttpServletResponse response);

    /**
     * 支部信息综合导出
     *
     * @param vo
     * @param httpServletResponse
     */
    void exportPartyOrgInfo(PartyOrgVo vo, HttpServletResponse httpServletResponse);

    /**
     * 人员信息综合导出
     *
     * @param vo
     * @param httpServletResponse
     */
    void exportPersonInfo(UserDetail vo, HttpServletResponse httpServletResponse) throws ParseException;

    /**
     * 党员信息下载
     *
     * @param vo
     * @param httpServletResponse
     */
    void dowloadPersonInfo(UserDetail vo, HttpServletResponse httpServletResponse);

    /**
     * 年度党费收缴明细下载
     *
     * @param partyDues
     * @param httpServletResponse
     */
    void dowloadYearDues(PartyDues partyDues, HttpServletResponse httpServletResponse);

    /**
     * 年度党费收缴明细下载
     *
     * @param partyPayments
     * @param httpServletResponse
     */
    void dowloadYearDuesInOut(PartyPaymentsVo partyPayments, HttpServletResponse httpServletResponse);

    /**
     * 支部基本信息导出
     *
     * @param orgId
     * @param httpServletResponse
     */
    void exportBranchInfo(String[] orgIds, HttpServletResponse httpServletResponse);

    IPage<Map> getStandBook(IPage page, String orgId);
}
