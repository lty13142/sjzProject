package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.PartyDues;
import com.sjz.partbuilding.model.vo.PartyDuesVo;
import org.omg.CORBA.SystemException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface PartyDuesService extends IService<PartyDues> {

    int savePartyDues(PartyDues partyDues);

    int updatePartyDues(PartyDues partyDues);

    int deleteById(String id);

    int realDelete(String id);

    PartyDues findById(String id);

    List findList(PartyDues partyDues);

    IPage<PartyDues> findPage(Page page, PartyDues partyDues);

    List<PartyDues> findPartyDuesList(PartyDues partyDues);

    List<PartyDues> findPageList(PartyDues partyDues);

    /**
     * 解析Excel并保存到数据库
     *
     * @param file 新增字段
     * @return
     */
    String saveByExcel(MultipartFile file, PartyDues partyDues) throws SystemException, ParseException;

    /**
     * 获取首页党费统计图
     * @return
     */
    Map<String,Object> getLoginPartyDues();

    /**
     * 导出党费缴纳明细
     * @param dues
     * @param response
     */
    void exportPartyDues(PartyDues dues, HttpServletResponse response);

    /**
     * 查询党费缴纳，党建费用收支统计+折线图
     * @param dues
     * @return
     */
    Map<String,Object> getDuesPayments(PartyDues dues);

    /**
     * 查询党费情况
     * @param dues
     * @return
     */
    Map<String,Object> getPartySituation(PartyDues dues);

    void exportPartyDuesTemplate(PartyDuesVo vo, HttpServletResponse httpServletResponse);
}
