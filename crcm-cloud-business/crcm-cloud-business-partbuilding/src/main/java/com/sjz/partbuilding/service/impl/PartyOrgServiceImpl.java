package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.exception.SystemException;
import com.sjz.partbuilding.constants.YTDicConstant;
import com.sjz.partbuilding.enums.DictionaryBusinessEnum;
import com.sjz.partbuilding.enums.StatusCode;
import com.sjz.partbuilding.enums.YTSystemStatusEnum;
import com.sjz.partbuilding.mapper.PartyOrgMapper;
import com.sjz.partbuilding.mapper.UserDetailMapper;
import com.sjz.partbuilding.mapper.WorkFeedbackMapper;
import com.sjz.partbuilding.model.entity.*;
import com.sjz.partbuilding.model.excel.ExcelVo;
import com.sjz.partbuilding.model.vo.*;
import com.sjz.partbuilding.service.*;
import com.sjz.partbuilding.util.UtilDic;
import com.sjz.partbuilding.util.UtilExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.jxls.common.Context;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 党组织详细信息serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 16:52
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PartyOrgServiceImpl extends ServiceImpl<PartyOrgMapper, PartyOrg> implements PartyOrgService {

    @Resource
    private OrgService orgService;

    @Resource
    private UserDetailMapper userDetailMapper;

    @Resource
    private WorkFeedbackMapper workFeedbackMapper;

    @Resource
    private OrgEventService orgEventService;

//    @Resource
//    private UserService userService;
//
//    @Resource
//    private UserMapper userMapper;

    @Resource
    private PartyDuesService partyDuesService;

    @Resource
    private UserDetailService userDetailService;

    @Resource
    private PartyPaymentsService partyPaymentsService;

//    @Resource
//    private CompanyService companyService;
//
//    @Resource
//    private LeaderService leaderService;
//
//    @Resource
//    private PeriodMapper periodMapper;
    /**
     * 新增
     *
     * @param partyOrg 党组织详细信息
     * @return
     */
    @Override
    public int savePartyOrg(PartyOrg partyOrg) {

        int flag;
        //partyOrg无id则为新增
        if (partyOrg.getId() == null || "".equals(partyOrg.getId())) {
            flag = this.baseMapper.insert(partyOrg);
        } else {
            //partyOrg有id则为修改
            flag = this.baseMapper.updateById(partyOrg);
        }
        return flag;
    }

    /**
     * 修改
     *
     * @param partyOrg 党组织详细信息
     * @return
     */
    @Override
    public int updatePartyOrg(PartyOrg partyOrg) {
        return this.baseMapper.updateById(partyOrg);
    }

    /**
     * 根据id删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    @Override
    public PartyOrg findById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询列表
     *
     * @param partyOrg 查询参数
     * @return
     */
    @Override
    public List<PartyOrg> findList(PartyOrg partyOrg) {
        QueryWrapper<PartyOrg> queryWrapper = new QueryWrapper<>();
        if (partyOrg.getOrgId() != null) {

        }
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param page     分页参数
     * @param partyOrg 查询参数
     * @return
     */
    @Override
    public IPage<PartyOrg> findPage(Page page, PartyOrg partyOrg) {
        QueryWrapper<PartyOrg> queryWrapper = new QueryWrapper<>();
        IPage<PartyOrg> pagePartyOrg = this.baseMapper.selectPage(page, queryWrapper);
        return pagePartyOrg;
    }

    /**
     * 根据orgId获取基本信息
     *
     * @param orgId 党组织id
     * @return
     */
    @Override
    public PartyOrg getBaseMessageByOrgId(String orgId, String pid) {
        //能查出信息
        List<PartyOrg> partyOrgList = this.baseMapper.getBaseMessageByOrgId(orgId);
        //获取上级党组织对象
//        Org org = orgService.getSuperOrgByPid(orgId);
//        //为空,未填写信息
//        if (partyOrgList.size() < 1) {
//            PartyOrg partyOrg = new PartyOrg();
//            //将组织组装到基本信息中
//            if (org != null && !"".equals(org)) {
//                partyOrg.setOrgId(orgId);
//                partyOrg.setSupOrg(org.getPid());
//                partyOrg.setSupName(org.getName());
//            }
//            return partyOrg;
//        } else {
//            //不为空则组装必要的数据返回
//            PartyOrg partyOrg = partyOrgList.get(0);
//            if (org != null && !"".equals(org)) {
//                partyOrg.setSupName(org.getName());
//            }
//            return partyOrg;
//        }
        return null;
    }

    /**
     * 获取数据统计党组织信息表
     *
     * @param partyOrg 查询条件
     * @return
     */
    @Override
    public List<PartyOrgVo> getStatisticsPartyOrg(PartyOrg partyOrg) {
        //获取数据统计党组织信息表
        PartyOrgVo partyOrgVo = this.baseMapper.getPartyOrgVoByOrgId(partyOrg);
        if (partyOrgVo == null || "".equals(partyOrgVo)) {
            return new ArrayList<>();
        }
        //获取上级组织
//        Org superOrg = orgService.getSuperOrgByPid(partyOrgVo.getOrgId());
        //partyOrgVo.setSupName(superOrg.getName());
        partyOrgVo.setSupName(partyOrgVo.getSupOrgName());
        //获取人员类别
        List<Map<String, Object>> loginPartyType = userDetailMapper.getLoginPartyType(partyOrgVo.getOrgId());
        for (Map<String, Object> map : loginPartyType) {
            //不存在则跳过本次循环
            if (!map.containsKey("partyType")) {
                continue;
            }
            //组装参数
            if (map.get("partyType").toString() != null && !"".equals(map.get("partyType").toString())) {
                if (StatusCode.PARTY_PERSON.code.equals(map.get("partyType").toString())) {
                    partyOrgVo.setPartyPerson(map.get("partyTypeCount").toString());
                } else if (StatusCode.PREPARE_PARTY.code.equals(map.get("partyType").toString())) {
                    partyOrgVo.setPrepareParty(map.get("partyTypeCount").toString());
                } else if (StatusCode.ENTRY_PARTY.code.equals(map.get("partyType").toString())) {
                    partyOrgVo.setEntryParty(map.get("partyTypeCount").toString());
                } else if (StatusCode.PREPARE_APPLY.code.equals(map.get("partyType").toString())) {
                    partyOrgVo.setPrepareApply(map.get("partyTypeCount").toString());
                }
            }
        }
        List<PartyOrgVo> partyOrgVoList = new ArrayList<>();
        partyOrgVoList.add(partyOrgVo);
        return partyOrgVoList;
    }

    @Override
    public PartyOrgVo getPartyOrgCompany(String orgId) {
        PartyOrgVo partyOrgVo = this.baseMapper.getPartyOrgCompany(orgId);
        if(partyOrgVo!=null){
            String orgCategory = partyOrgVo.getOrgCategory();
            orgCategory = UtilDic.getChNameByCode(DictionaryBusinessEnum.ORG_CATEGORY.code, orgCategory);
            partyOrgVo.setOrgCategory(orgCategory);
            partyOrgVo.setGOrgTotalMembers(0);
        }
        return partyOrgVo;
    }

    @Override
    public void exportWorkSupervise(WorkFeedBackVo vo, HttpServletResponse response) {
//        String userId = SecurityUtil.getCurrentUserId()
//        User user = userService.getById(userId);
//        if(user==null){
//            throw new SystemException(YTSystemStatusEnum.USER_NO_FOUNT.code, YTSystemStatusEnum.USER_NO_FOUNT.desc);
//        }
//        vo.setOrgId(user.getOrgId());
//        List<WorkFeedBackVo> list = getDataStatisticsWorkSupervision(vo);
//        int row = 0;
//        for (WorkFeedBackVo workFeedBackVo:list){
//            row = row +1;
//            workFeedBackVo.setRowNo(row);
//        }
//
//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("工作督办综合报表.xls");
//        excelVo.setTemplateName("工作督办综合报表.xls");
//        Context context = new Context();
//        String title = "工作督办综合报表 ";
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public List<WorkFeedBackVo> getDataStatisticsWorkSupervision(WorkFeedBackVo workFeedBackVo) {
        //获取组织集合
        //获取数据统计工作督办
        List<WorkFeedBackVo> workFeedBackVoList = workFeedbackMapper.getDataStatisticsWorkSupervisionList(workFeedBackVo);
        //初始化结果集
        List<WorkFeedBackVo> resultList = new ArrayList<>();
        int toComplete = 0;
        int notComplete = 0;
        int countComplete = 0;
        if (workFeedBackVoList.size() > 0) {
            WorkFeedBackVo wf = new WorkFeedBackVo();
            for (WorkFeedBackVo vo : workFeedBackVoList) {
                if (vo.getCompleteTime() != null) {
                    //完成数
                    countComplete++;
                    if (vo.getCloseTime().getTime() < vo.getCompleteTime().getTime()) {
                        //逾期
                        notComplete++;
                    } else if (vo.getCloseTime().getTime() > vo.getCompleteTime().getTime()) {
                        //未逾期
                        toComplete++;
                    }
                }
            }
            //组装数据
            wf.setOrgName(workFeedBackVoList.get(0).getOrgName());
            wf.setToComplete(toComplete+"");
            wf.setNotComplete(notComplete+"");
            wf.setCountComplete(countComplete+"");
            resultList.add(wf);
        }
        return resultList;
    }


    @Override
    public void exportOrganizationLife(EventStatisticsVo vo, HttpServletResponse response) {
//        String userId = UserContext.current().getUserId();
//        User user = userService.getById(userId);
//        if(user==null){
//            throw new SystemException(YTSystemStatusEnum.USER_NO_FOUNT.code, YTSystemStatusEnum.USER_NO_FOUNT.desc);
//        }
//        OrgEvent orgEvent=new OrgEvent();
//        orgEvent.setOrgId(user.getOrgId());
//        List<EventStatisticsVo> list = orgEventService.getStatisticsOrgEvent(orgEvent);
//        int row = 0;
//        for (EventStatisticsVo eventStatisticsVo:list){
//            row = row +1;
//            eventStatisticsVo.setRowNo(row);
//        }
//
//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("组织生活综合报表.xls");
//        excelVo.setTemplateName("组织生活综合报表.xls");
//        Context context = new Context();
//        String title = "组织生活综合报表 ";
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void exportPartyDues(PartyDues vo, HttpServletResponse response) {
//        String userId = UserContext.current().getUserId();
//        User user = userService.getById(userId);
//        if(user==null){
//            throw new SystemException(YTSystemStatusEnum.USER_NO_FOUNT.code, YTSystemStatusEnum.USER_NO_FOUNT.desc);
//        }
//        vo.setOrgId(user.getOrgId());
//        List<PartyDues> list = partyDuesService.findPartyDuesList(vo);
//        int row = 0;
//        for (PartyDues partyDues:list){
//            row = row +1;
//            partyDues.setId(row+"");
//        }
//
//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("党费综合报表.xls");
//        excelVo.setTemplateName("党费综合报表.xls");
//        Context context = new Context();
//        String title = "党费综合报表 ";
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void exportPartyOrgInfo(PartyOrgVo vo, HttpServletResponse response) {
        String userId = SecurityUtil.getCurrentUser().getUserId();
        LambdaQueryWrapper<UserDetail> detailWrapper = new LambdaQueryWrapper<>();
        detailWrapper.eq(UserDetail::getUserId, userId);
        UserDetail userDetail = userDetailMapper.findByUserId(userId);
        if(null == userDetail){
            throw new SystemException(YTSystemStatusEnum.USER_NO_FOUNT.code, YTSystemStatusEnum.USER_NO_FOUNT.desc);
        }
        PartyOrg partyOrg = new PartyOrg();
        partyOrg.setOrgId(userDetail.getOrgId());
        List<PartyOrgVo> partyOrgVoList = getStatisticsPartyOrg(partyOrg);
        int row = 0;
        for (PartyOrgVo partyOrgVo:partyOrgVoList){
            row = row +1;
            partyOrgVo.setRowNo(row);
        }

        ExcelVo excelVo = new ExcelVo();
        excelVo.setResponse(response);
        excelVo.setFileName("支部信息综合报表.xls");
        excelVo.setTemplateName("支部信息综合报表.xls");
        Context context = new Context();
        String title = "支部信息综合报表";
        context.putVar("title",title);
        context.putVar("list",partyOrgVoList);
        excelVo.setContext(context);
        try {
            UtilExcel.getInstance().exportExcelByStream(excelVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportPersonInfo(UserDetail vo, HttpServletResponse response) throws ParseException {
//        String userId = UserContext.current().getUserId();
//        User user = userService.getById(userId);
//        if(user==null){
//            throw new SystemException(YTSystemStatusEnum.USER_NO_FOUNT.code, YTSystemStatusEnum.USER_NO_FOUNT.desc);
//        }
//        vo.setOrgId(user.getOrgId());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        List<UserDetail> list = userDetailService.getStatisticsPersonManagementList(vo);
//        int row = 0;
//        for (UserDetail userDetail:list){
//            row = row +1;
//            userDetail.setId(row+"");
//            if("0".equals(userDetail.getSex())){
//                userDetail.setSex("男");
//            }else{
//                userDetail.setSex("女");
//            }
//            userDetail.setNation(UtilDic.getChNameByCode(YTDicConstant.NATION.CODE, userDetail.getNation()));
//            if (null!=userDetail.getShiftPartyTime()) {
//                String format = simpleDateFormat.format(userDetail.getShiftPartyTime());
//                userDetail.setShiftPartyTime(HSSFDateUtil.parseYYYYMMDDDate(format));
//            }
//        }
//
//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("人员信息综合报表.xls");
//        excelVo.setTemplateName("人员信息综合报表.xls");
//        Context context = new Context();
//        String title = "人员信息综合报表 ";
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void dowloadPersonInfo(UserDetail vo, HttpServletResponse response) {
//        List<UserDetail> list = userDetailService.getPersonManagementDowload(vo);
//        int row = 0;
//        for (UserDetail userDetail:list){
//            //名族映射成汉字
//            userDetail.setNation(UtilDic.getChNameByCode(YTDicConstant.NATION.CODE, userDetail.getNation()));
//            row = row +1;
//            userDetail.setId(row+"");
//        }
//        ExcelVo excelVo = new ExcelVo();
//        String title = "";
//        excelVo.setResponse(response);
//        if("0".equals(vo.getPartyType())){
//            excelVo.setFileName("党员信息列表.xls");
//            excelVo.setTemplateName("党员信息列表.xls");
//            title = "党员信息列表";
//        }else if("1".equals(vo.getPartyType())){
//            excelVo.setFileName("预备党员信息列表.xls");
//            excelVo.setTemplateName("党员信息列表.xls");
//            title = "预备党员信息列表";
//        }else if("2".equals(vo.getPartyType())){
//            excelVo.setFileName("入党积极分子信息列表.xls");
//            excelVo.setTemplateName("党员信息列表.xls");
//            title = "入党积极分子信息列表";
//        }
//        Context context = new Context();
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public void dowloadYearDues(PartyDues partyDues, HttpServletResponse response) {
        List<PartyDuesExportVo> list = partyDuesService.findList(partyDues);
        int row = 0;
        for (PartyDuesExportVo userDetail:list){
            row = row +1;
            userDetail.setRowNo(row);
        }
//        ExcelVo excelVo = new ExcelVo();
//        String title = "";
//        excelVo.setResponse(response);
//        excelVo.setFileName("党费收缴列表.xls");
//        excelVo.setTemplateName("年度党费缴纳明细表.xls");
//        title = "党费收缴列表";
//
//        Context context = new Context();
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void dowloadYearDuesInOut(PartyPaymentsVo partyPayments, HttpServletResponse response) {
        /*Page page = new Page();
        IPage<PartyPaymentsVo> partyPaymentsPage = partyPaymentsService.findPartyPaymentsPage(page, partyPayments);
        List<PartyPaymentsVo> list = partyPaymentsPage.getRecords();*/
        List<PartyPayments> partyPaymentsList = partyPaymentsService.findList(partyPayments);
        List<PartyPaymentsVo> list = new ArrayList<>();
        if(partyPaymentsList!=null&&partyPaymentsList.size()>0){
            for (PartyPayments payments : partyPaymentsList) {
                PartyPaymentsVo partyPaymentsVo=new PartyPaymentsVo();
                partyPaymentsVo.setPaymentTime(payments.getPaymentTime());
                partyPaymentsVo.setTitle(payments.getTitle());
                partyPaymentsVo.setBalance(payments.getBalance());
                //经费收入
                if("0".equals(payments.getType())){
                    partyPaymentsVo.setInMoney(payments.getMoney());
                }
                //经费支出
                if("1".equals(payments.getType())){
                    partyPaymentsVo.setOutMoney(payments.getMoney());
                }
                list.add(partyPaymentsVo);
            }
        }
        int row = 0;
        for (PartyPaymentsVo partyPaymentsVo:list){
            row = row +1;
            partyPaymentsVo.setRowNo(row);
        }
//        ExcelVo excelVo = new ExcelVo();
//        String title = "";
//        excelVo.setResponse(response);
//        excelVo.setFileName("支部党建经费收支明细表.xls");
//        excelVo.setTemplateName("支部党建经费收支明细列表.xls");
//        title = "支部党建经费收支明细表";
/*        if("0".equals(partyPayments.getType())){
            excelVo.setFileName("支部党建经费收入明细表.xls");
            excelVo.setTemplateName("支部党建经费收支明细列表.xls");
            title = "支部党建经费收入明细表";
        }else if("1".equals(partyPayments.getType())){
            excelVo.setFileName("支部党建经费支出明细表.xls");
            excelVo.setTemplateName("支部党建经费收支明细列表.xls");
            title = "支部党建经费支出明细表";
        }*/

//        Context context = new Context();
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void exportBranchInfo(String[] orgIds, HttpServletResponse httpServletResponse) {
//        List<PartyOrgVo> list = this.baseMapper.getPartyOrgCompany0(orgIds);
//        int row = 0;
//        for (PartyOrgVo partyOrgVo:list) {
//            row = row +1;
//            partyOrgVo.setRowNo(row);
//            if(partyOrgVo!=null){
//                String orgCategory = partyOrgVo.getOrgCategory();
//                orgCategory = UtilDic.getChNameByCode(DictionaryBusinessEnum.ORG_CATEGORY.code, orgCategory);
//                partyOrgVo.setOrgCategory(orgCategory);
//                QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//                queryWrapper.eq("g_org_id",partyOrgVo.getOrgId());
//                List<User> users = userMapper.selectList(queryWrapper);
//                if(users!=null){
//                    partyOrgVo.setGOrgTotalMembers(users.size());
//                }else {
//                    partyOrgVo.setGOrgTotalMembers(0);
//                }
//            }
//        }
//        ExcelVo excelVo = new ExcelVo();
//        String title = "";
//        excelVo.setResponse(httpServletResponse);
//        excelVo.setFileName("支部基本信息列表.xls");
//        excelVo.setTemplateName("支部基本信息列表.xls");
//        title = "支部基本信息列表";
//
//        Context context = new Context();
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public IPage<Map> getStandBook(IPage page, String orgId) {
        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(orgId)){
            /*Page page = new Page();
            page.setRecords(null);*/
            return null;
        }
//        PartyOrgVo vo = getPartyOrgCompany(orgId);
//        Org org = orgService.findById(orgId);
//        Company company = companyService.findById(org.getCompanyId());
//        Leader leader = new Leader();
//        leader.setOrgId(orgId);
//        leader.setDeleted(1);
//        List<LeaderVo> lederList = leaderService.findList(leader);
//        if (!ObjectUtils.isEmpty(vo)){
//            map.put("orgId",vo.getOrgId());
//            //党支部名称
//            map.put("fullName",vo.getFullName());
//            //所含公司
//            map.put("companyName",vo.getCompanyName());
//            //上级党组织
//            map.put("supOrgName",vo.getSupOrgName());
//            //本届支委会届数
//            map.put("changeTermCount",vo.getChangeTermCount()==null?0:vo.getChangeTermCount());
//            //本届支委会成立时间
//            Period period =null;
//            if (!StringUtils.isEmpty(orgId)) {
//                 period = periodMapper.selectByOrgId(orgId);
//            }
//            if (!ObjectUtils.isEmpty(period)&&!StringUtils.isEmpty(period.getStartTimeCh())) {
//                map.put("changeTime",period.getStartTimeCh());
//            }else {
//                map.put("changeTime", null);
//            }
//            //总公司批准时间
//            map.put("setupTime",vo.getSetupTimeCh());
//            //直接上一级公司
//            Company superCompany = companyService.findById(company.getPid());
//            if(!ObjectUtils.isEmpty(superCompany)){
//
//            map.put("superCompany",superCompany.getName());
//            }else{
//                map.put("superCompany","");
//            }
//            //联系人
//            map.put("contactName",vo.getContactName());
//            //联系方式
//            map.put("phone",vo.getPhone());
//            //填报时间
//            map.put("createTime",vo.getSetupTimeCh());
//            //支部委员
//            map.put("leaders",lederList);
//        }
//        if (map.size()>0) {
//            arrayList.add(map);
//            page.setRecords(arrayList);
//        }
        return page;
    }
}
