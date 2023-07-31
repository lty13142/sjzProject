package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.core.exception.SystemException;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.mapper.PartyDuesMapper;
import com.sjz.partbuilding.mapper.PartyPaymentsMapper;
import com.sjz.partbuilding.model.entity.PartyDues;
import com.sjz.partbuilding.model.vo.PartyDuesExportVo;
import com.sjz.partbuilding.model.vo.PartyDuesVo;
import com.sjz.partbuilding.model.vo.PartyPaymentsVo;
import com.sjz.partbuilding.service.PartyDuesService;
//import com.zzyt.common.bean.ExcelVo;
//import com.zzyt.common.utils.UtilExcel;
//import com.zzyt.common.utils.UtilLawDate;
//import com.zzyt.core.basic.beans.UserContext;
//import com.zzyt.core.common.utils.UtilDate;
//import com.zzyt.model.entity.system.org.Org;
//import com.zzyt.model.entity.system.user.User;
//import com.zzyt.model.entity.system.user.UserDetail;
//import com.zzyt.model.entity.zhdj.PartyDuesTemplate;
//import com.zzyt.model.entity.zhdj.PartyOrg;
//import com.zzyt.service.system.org.OrgService;
//import com.zzyt.service.system.role.RoleService;
//import com.zzyt.service.system.user.UserDetailService;
//import com.zzyt.service.system.user.UserService;
//import com.zzyt.service.zhdj.PartyDuesTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.jxls.common.Context;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PartyDuesServiceImpl extends ServiceImpl<PartyDuesMapper, PartyDues> implements PartyDuesService {

//    @Resource
//    private OrgService orgService;
//    @Resource
//    private UserService userService;
//    @Resource
//    private UserDetailService userDetailService;
//    @Resource
//    private RoleService roleService;
    @Resource
    private PartyPaymentsMapper partyPaymentsMapper;
//    @Resource
//    private PartyDuesTemplateService partyDuesTemplateService;
    @Override
    public int savePartyDues(PartyDues partyDues) {
        return this.baseMapper.insert(partyDues);
    }

    @Override
    public int updatePartyDues(PartyDues partyDues) {
        return this.baseMapper.updateById(partyDues);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public PartyDues findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List findList(PartyDues partyDues) {
        List<PartyDuesExportVo> res = new ArrayList();
        if (StringUtils.isEmpty(partyDues.getOrgId())) {
//            String orgId = this.userService.findById(UserContext.current().getUserId()).getOrgId();
//            partyDues.setOrgId(orgId);
        }
        /*partyDues.setPartyOrgId(orgId);
        PartyOrg partyOrg = userService.getPartyOrgByUserId("");
        if(partyOrg!=null){
            partyDues.setPartyOrgId(partyOrg.getId());
        }*/
//        if(partyOrg!=null){
//            partyDues.setPartyOrgId(partyOrg.getId());
//        }
//        if(!isAdmin){//普通用户
//            partyDues.setName(UserContext.currentUserName());
//        }
        //查询人员
        List<PartyDues> list = this.baseMapper.findDuesPerson(partyDues);
        for (PartyDues dues : list) {
//            List monthList = this.baseMapper.findMonthMoney(dues);
            dues.setOrgId(partyDues.getOrgId());
            PartyDuesExportVo partyDuesExportVo = this.baseMapper.findMonthMoney2(dues, partyDues.getYear());
            dues.setYear(partyDues.getYear());
            List<PartyDues> partyDuesByYearList = this.baseMapper.findPartyDuesByYear(dues);
            partyDuesExportVo.setPartyDuesList(partyDuesByYearList);
            if (partyDuesByYearList.size() > 0) {
                partyDuesExportVo.setRemark(partyDuesByYearList.get(partyDuesByYearList.size() - 1).getRemarks());
            }
            res.add(partyDuesExportVo);
        }
        return res;
    }

    @Override
    public IPage<PartyDues> findPage(Page page, PartyDues partyDues) {
        IPage<PartyDues> pagePartyDues = this.baseMapper.findPage(page, partyDues);
        return pagePartyDues;
    }

    @Override
    public List<PartyDues> findPartyDuesList(PartyDues partyDues) {
        List<PartyDues> partyDuesList = this.baseMapper.findPartyDuesList(partyDues);
        return partyDuesList;
    }

    @Override
    public List<PartyDues> findPageList(PartyDues partyDues) {
        QueryWrapper<PartyDues> queryWrapper = new QueryWrapper<>();
        //姓名
        if (partyDues.getName() != null && !"".equals(partyDues.getName())) {
            queryWrapper.like("name", partyDues.getName());
        }
        //月份
        if (partyDues.getMonthStr() != null && !"".equals(partyDues.getMonthStr())) {
            queryWrapper.likeRight("month", partyDues.getMonthStr());
        }
        //组织id
        if (partyDues.getOrgId() != null && !"".equals(partyDues.getOrgId())) {
            queryWrapper.eq("org_id", partyDues.getOrgId());
        }
        queryWrapper.orderByDesc("create_time");
        List<PartyDues> partyDuesList = this.baseMapper.selectList(queryWrapper);
        return partyDuesList;
    }

    /**
     * 解析Excel并保存到数据库
     *
     * @param file 新增字段
     * @return
     */
    @Override
    public String saveByExcel(MultipartFile file, PartyDues partyDues1) throws SystemException, ParseException {
//        String userId = UserContext.current().getUserId();
//        if (StringUtils.isNotEmpty(userId)) {
//            User user = userService.findById(userId);
//            //根据id获取组织对象
//            String partyOrgId = "", orgId = "", orgName = "";
//            if (user != null) {
//                Org org = orgService.findById(user.getOrgId());
//                PartyOrg partyOrg = userService.getPartyOrgByUserId(userId);
//                if (org != null) {
//                    orgId = org.getId();
//                    orgName = org.getName();
//                }
//                if (partyOrg != null) {
//                    partyOrgId = partyOrg.getId();
//                }
//            }
//            //根据路径和名称获取excel数据
//            List excelUtil = UtilExcel.analysisExcelByFile(file);

//                    POIUtil.excelUtil(dues.getPath(), dues.getFileName(), 2);
//            //获取年月
//            Date date = UtilLawDate.monthFormat.parse(dues.getMonthStr());
            //每一列循环
//            List<PartyDues> saveList = new ArrayList<>();
//            for (int i = 0; i < excelUtil.size(); i++) {
//                List temp = (List) excelUtil.get(i);
//                //组装党费对象
//                PartyDues partyDues = new PartyDues();
//                partyDues.setOrgId(orgId);
//                partyDues.setOrgName(orgName);
//                partyDues.setPartyOrgId(partyOrgId);
//                Date date = null;
//                try {
//                    date = UtilLawDate.monthFormat.parse(temp.get(4).toString());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                if (UtilLawDate.monthFormat.parse(partyDues1.getMonthStr()).compareTo(date)!=0){
//                    throw new SystemException(500,"请上传对应月内【缴费时间】党费数据");
//                }
//                if (null==temp.get(1).toString() || null==temp.get(2)  || null==temp.get(3)  || date == null) {
//                    continue;
//                }
//                if (StringUtils.isEmpty(temp.get(1).toString()) || StringUtils.isEmpty(temp.get(2).toString()) || StringUtils.isEmpty(temp.get(3).toString())) {
//                    continue;
//                }
//                partyDues.setMonth(date);
//                partyDues.setName(temp.get(1).toString());
//                partyDues.setPayable(new BigDecimal(temp.get(2).toString()));
//                partyDues.setPaidIn(new BigDecimal(temp.get(3).toString()));
//                partyDues.setPaymentTime(temp.get(4).toString());
//                partyDues.setRemarks(null==temp.get(5)?"":temp.get(5).toString());
//                //新增党费模板id
//                partyDues.setTemplateId(partyDues1.getTemplateId());
//                //获取选取月份人员数据
//                LambdaQueryWrapper<PartyDues> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//                lambdaQueryWrapper.eq(PartyDues::getMonth, date);
//                lambdaQueryWrapper.eq(PartyDues::getName, partyDues.getName());
//                lambdaQueryWrapper.eq(PartyDues::getOrgId, partyDues.getOrgId());
//                //获取选取月份人员数据
//                List<PartyDues> oldPartyDuesList = this.baseMapper.selectList(lambdaQueryWrapper);
//                if (oldPartyDuesList.size() <= 0) {
//                    saveList.add(partyDues);
//                }
//            }
//            this.saveBatch(saveList);
//            PartyDuesTemplate partyDuesTemplate = new PartyDuesTemplate();
//            partyDuesTemplate.setId(partyDues1.getTemplateId());
//            partyDuesTemplate.setStatus(1);
//            partyDuesTemplateService.updateById(partyDuesTemplate);
//        }
        return "保存成功";
    }

    /**
     * 获取首页党费统计图
     *
     * @return
     */
    @Override
    public Map<String, Object> getLoginPartyDues() {
        //获取当前月和前11月
//        String[] monthArrays = UtilLawDate.getLatest12Month();
//        List<Map<String, Object>> mapList = this.baseMapper.getMonthAndMoney(monthArrays[0]);
//        Map<String, Object> resultList = new HashMap<>(16);
//        Double[] moneyArrays = new Double[monthArrays.length];
//        for (int i = 0; i < monthArrays.length; i++) {
//            boolean flag = false;
//            for (Map<String, Object> map : mapList) {
//                if (monthArrays[i].equals(map.get("month"))) {
//                    moneyArrays[i] = Double.valueOf(map.get("money").toString());
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag == false) {
//                moneyArrays[i] = (0d);
//            }
//        }
        //组装结果返回集
//        resultList.put("monthArrays", monthArrays);
//        resultList.put("moneyArrays", moneyArrays);
//        return resultList;
        return Collections.emptyMap();
    }

    /**
     * 导出党费缴纳明细
     *
     * @param partyDues
     * @param response
     */
    @Override
    public void exportPartyDues(PartyDues partyDues, HttpServletResponse response) {
        List<PartyDuesExportVo> res = new ArrayList();
//        Boolean isAdmin = roleService.getRolePermissionsByUsername();
//        PartyOrg partyOrg = userService.getPartyOrgByUserId("");
//        if(partyOrg!=null){
//            partyDues.setPartyOrgId(partyOrg.getId());
//        }
//        if(!isAdmin){//普通用户
//            partyDues.setName(UserContext.currentUserName());
//        }
        //查询人员
        List<PartyDues> list = this.baseMapper.findDuesPerson(partyDues);
        for (PartyDues dues : list) {
            res.add(this.baseMapper.findMonthMoney(dues));
        }
        int row = 0;
        for (PartyDuesExportVo vo : res) {
            row = row + 1;
            vo.setRowNo(row);
        }
        //查询合计
        PartyDuesExportVo totalVo = this.baseMapper.findMonthMoney(null);
        totalVo.setRowNo(row + 1);
        totalVo.setName("合计");
        res.add(totalVo);
        ServletOutputStream outStream = null;

//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("年度党费缴纳明细表.xls");
//        excelVo.setTemplateName("年度党费缴纳明细表.xls");
//        Context context = new Context();
//        String title = "年度党费缴纳明细表";
//        context.putVar("title", title);
//        context.putVar("list", res);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 查询党费缴纳，党建费用收支统计+折线图
     *
     * @param dues
     * @return
     */
    @Override
    public Map<String, Object> getDuesPayments(PartyDues dues) {
        //查询年度党费缴纳，党建支出和收入
        Map<String, Object> res = new HashMap<>();
        Map map = new HashMap();
        //查询折线图
        Map map1 = new HashMap();
        //党费缴纳
        map.put("partyDues", this.baseMapper.findMoneyYear(dues));
        map1.put("partyDuesList", this.baseMapper.getDuesByMonth(dues));
        //党建收入
        PartyPaymentsVo paymentsVo = new PartyPaymentsVo();
        paymentsVo.setOrgId(dues.getOrgId());
        paymentsVo.setType(YTSystemConstant.PAYMENTS_TYPE.IN);
        map.put("partyIn", partyPaymentsMapper.findPaymentsByYear(paymentsVo));
        map1.put("partyInList", partyPaymentsMapper.getPaymentsByMonth(paymentsVo));
        //党建支出
        paymentsVo.setType(YTSystemConstant.PAYMENTS_TYPE.OUT);
        map.put("partyOut", partyPaymentsMapper.findPaymentsByYear(paymentsVo));
        map1.put("partyOutList", partyPaymentsMapper.getPaymentsByMonth(paymentsVo));

        paymentsVo.setOrgId(null);
//        String userId = UserContext.current().getUserId();
//        User user = userService.getById(userId);
//        //参数"0"代表查询党组织
//        ArrayList<String> ids = orgService.getIdsByOrgId(user.getOrgId(), "0");
//        map.put("totalPartyOut", partyPaymentsMapper.findPaymentsByYear2(paymentsVo, ids));
//        paymentsVo.setType(YTSystemConstant.PAYMENTS_TYPE.IN);
//        map.put("totalPartyIn", partyPaymentsMapper.findPaymentsByYear2(paymentsVo, ids));
//        res.put("totalObj", map);
//        res.put("list", map1);
        return res;
    }

    @Override
    public Map<String, Object> getPartySituation(PartyDues dues) {
        //查询党费情况
        Map<String, Object> res = new HashMap<>();
        Map map = new HashMap();
        //查询当前机构，当年党费情况--条件可变
        Map map1 = new HashMap();
        //党费收入
        map.put("partyDuesIn", this.baseMapper.findMoneyYear(dues));
        //党费支出
        PartyPaymentsVo paymentsVo = new PartyPaymentsVo();
        paymentsVo.setPartyOrgId(dues.getPartyOrgId());
        paymentsVo.setType(YTSystemConstant.PAYMENTS_TYPE.OUT);

        map.put("partyDuesOut", partyPaymentsMapper.findPaymentsByYear(paymentsVo));
        if (StringUtils.isBlank(dues.getYear())) {
//            dues.setYear(UtilDate.getDate());
//            paymentsVo.setYear(UtilDate.getDate());
        }
        map1.put("partyDuesIn", this.baseMapper.findMoneyYear(dues));
        map1.put("partyDuesOut", partyPaymentsMapper.findPaymentsByYear(paymentsVo));
        res.put("topObj", map);
        res.put("obj", map1);
        return res;
    }

    @Override
    public void exportPartyDuesTemplate(PartyDuesVo partyDues, HttpServletResponse response) {
//        List<PartyDuesExportTemplateDto> result = new ArrayList();
//        //查询人员
//        UserDetail userDetail = new UserDetail();
//        userDetail.setPartyType("0");
//        userDetail.setOrgId(partyDues.getOrgId());
//        List<UserDetail> statisticsPersonManagementList = userDetailService.getStatisticsPersonManagementList(userDetail);
//        int row = 0;
//        for (UserDetail userDetail1 : statisticsPersonManagementList) {
//            PartyDuesExportTemplateDto dto = new PartyDuesExportTemplateDto();
//            row = row + 1;
//            dto.setRowNum(row);
//            dto.setUserName(userDetail1.getName());
//            result.add(dto);
//        }
//        ServletOutputStream outStream = null;
//
//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("党费收缴明细模板.xls");
//        excelVo.setTemplateName("党费收缴明细模板.xls");
//        Context context = new Context();
//        String title = partyDues.getMonthNum() + "月党费收缴明细模板";
//        context.putVar("title", title);
//        context.putVar("list", result);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("title", title);
//        map.put("list", result);
//        excelVo.setContext(context);
//        try {
//            TemplateExportParams params = new TemplateExportParams("\\static\\excel\\党费收缴明细模板.xls");
//
//            Workbook workbook = ExcelExportUtil.exportExcel(params, context.get);
//
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        TemplateExportParams params = new TemplateExportParams("/excel/党费收缴明细模板.xls");
//        if (null!=params){
//           // List<Applys> list = ApplyService.queryAll();
//            Map<String, Object> map = new HashMap<String, Object>();
//            List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//            //查询人员
//            UserDetail userDetail = new UserDetail();
//            userDetail.setPartyType("0");
//            userDetail.setOrgId(partyDues.getOrgId());
//            List<UserDetail> statisticsPersonManagementList = userDetailService.getStatisticsPersonManagementList(userDetail);
//            int row = 0;
//            for (UserDetail userDetail1 : statisticsPersonManagementList) {
//                Map<String, String> lm = new HashMap<String, String>();
//                //put的键要跟excel模板中的名称一致
//                row = row + 1;
//                lm.put("id", row + "");
//                lm.put("userName",userDetail1.getName() );
//                lm.put("payInTime", partyDues.getYear()+"-"+partyDues.getMonthNum()+"-01");
//                listMap.add(lm);
//            }
//            map.put("maplist", listMap);
//            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//            ServletOutputStream out = null;
//            try {
//                //流的形式传输数据
//                response.setHeader("content-type","application/octet-stream");
//                //防止中文乱码
//                response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(partyDues.getYear()+"年-"+partyDues.getMonthNum()+"月党费收缴明细模板.xls","UTF-8"));
//                out=response.getOutputStream();
//                workbook.write(out);
//            }catch (IOException e){
//                e.printStackTrace();
//            }finally {
//                if (null!=out){
//                    try {
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }
}
