package com.sjz.partbuilding.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.enums.YTSystemStatusEnum;
import com.sjz.partbuilding.mapper.PartyPaymentsMapper;
import com.sjz.partbuilding.model.entity.PartyPayments;
import com.sjz.partbuilding.model.vo.PartyPaymentsVo;
import com.sjz.partbuilding.model.vo.WarnVo;
import com.sjz.partbuilding.service.PartyPaymentsService;
//import com.zzyt.common.bean.ExcelVo;
//import com.zzyt.common.utils.UtilExcel;
//import com.zzyt.core.basic.beans.UserContext;
//import com.zzyt.model.entity.system.org.Org;
//import com.zzyt.model.entity.zhdj.PartyOrg;
//import com.zzyt.service.system.org.OrgService;
//import com.zzyt.service.system.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PartyPaymentsServiceImpl extends ServiceImpl<PartyPaymentsMapper, PartyPayments> implements PartyPaymentsService {

//    @Resource
//    private UserService userService;
//    @Resource
//    private OrgService orgService;

    @Override
    public WarnVo savePartyPayments(PartyPayments partyPayments) {
        WarnVo warnVo=new WarnVo();
        warnVo.setWarn("0");
        warnVo.setMsg("成功");
        if(StringUtils.isBlank(partyPayments.getId())){
//            String orgId = "",orgName = "",partyOrgId = "";
//            Org org = orgService.findById(userService.getCurrentOrgId());
//            if(org!=null){
//                orgId = org.getId();
//                orgName = org.getName();
//            }
//            PartyOrg partyOrg = userService.getPartyOrgByUserId(null);
//            if(partyOrg!=null){
//                partyOrgId = partyOrg.getId();
//            }
//            partyPayments.setOrgId(orgId);
//            partyPayments.setOrgName(orgName);
//            partyPayments.setPartyOrgId(partyOrgId);
            partyPayments.setBalance(calBalance(partyPayments));
            if(partyPayments.getBalance().intValue()<0){
                //throw new SystemException(YTSystemStatusEnum.PARTY_PAYMENTS_OUT.code,YTSystemStatusEnum.PARTY_PAYMENTS_OUT.desc);
               warnVo.setWarn("1");
               warnVo.setMsg(YTSystemStatusEnum.PARTY_PAYMENTS_OUT.desc);
               return warnVo;
            }
            this.baseMapper.insert(partyPayments);
        }else {
            PartyPayments payments = this.baseMapper.selectById(partyPayments.getId());
            BigDecimal balance = calBalance(payments);
            if(YTSystemConstant.PAYMENTS_TYPE.IN.equals(payments.getType())){//收入
                balance = balance.subtract(payments.getMoney()).subtract(payments.getMoney()).add(partyPayments.getMoney());
            }else if(YTSystemConstant.PAYMENTS_TYPE.OUT.equals(payments.getType())){
                balance = balance.add(payments.getMoney()).add(payments.getMoney()).subtract(partyPayments.getMoney());
            }
            partyPayments.setBalance(balance);
            this.baseMapper.updateById(partyPayments);
        }
        return warnVo;
    }

    private BigDecimal calBalance(PartyPayments partyPayments){
        BigDecimal balance = this.baseMapper.getBalance(partyPayments);
        if(balance==null){
            balance = BigDecimal.ZERO;
        }
        if(YTSystemConstant.PAYMENTS_TYPE.IN.equals(partyPayments.getType())){//收入
            balance = balance.add(partyPayments.getMoney());
        }
        if(YTSystemConstant.PAYMENTS_TYPE.OUT.equals(partyPayments.getType())){//支出
            balance = balance.subtract(partyPayments.getMoney());
        }

        return balance;
    }

    /**
     * 修改的收入或支出类型与原来的类型保持一致
     * @param partyPayments
     * @return
     */
    @Override
    public int updatePartyPayments(PartyPayments partyPayments) {
        PartyPayments oldPartyPayments = this.baseMapper.selectById(partyPayments.getId());
        BigDecimal balance = oldPartyPayments.getBalance();
        //修改后与修改前的差值
        BigDecimal value=partyPayments.getMoney().subtract(oldPartyPayments.getMoney());
        //如果修改某条经费的金额，那么这条数据之后新增的数据都要进行相应变化。
        QueryWrapper<PartyPayments> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("org_id",oldPartyPayments.getOrgId());
        queryWrapper.gt("create_time",oldPartyPayments.getCreateTime());
        List<PartyPayments> partyPaymentsList = this.baseMapper.selectList(queryWrapper);
        //如果修改的是收入类型
        if(YTSystemConstant.PAYMENTS_TYPE.IN.equals(oldPartyPayments.getType())){
            balance=balance.add(value);
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().add(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //如果修改的是支出类型
        if(YTSystemConstant.PAYMENTS_TYPE.OUT.equals(oldPartyPayments.getType())){
            balance=balance.subtract(value);
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().subtract(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //设置修改后的的结余
        partyPayments.setBalance(balance);
        return this.baseMapper.updateById(partyPayments);
    }

    /**
     * 修改的收入或支出类型与原来的类型相同或者不相同，都可以
     * @param partyPayments
     * @return
     */
    @Override
    public int updatePartyPayments2(PartyPayments partyPayments) {
        PartyPayments oldPartyPayments = this.baseMapper.selectById(partyPayments.getId());
        BigDecimal balance = oldPartyPayments.getBalance();
        //如果修改某条经费的金额，那么这条数据之后新增的数据（结余）都要进行相应变化。
        QueryWrapper<PartyPayments> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("org_id",oldPartyPayments.getOrgId());
        queryWrapper.gt("create_time",oldPartyPayments.getCreateTime());
        List<PartyPayments> partyPaymentsList = this.baseMapper.selectList(queryWrapper);
        //如果修改后是收入类型，原来也是收入类型
        if(YTSystemConstant.PAYMENTS_TYPE.IN.equals(partyPayments.getType())&&YTSystemConstant.PAYMENTS_TYPE.IN.equals(oldPartyPayments.getType())){
            BigDecimal value=partyPayments.getMoney().subtract(oldPartyPayments.getMoney());
            balance=balance.add(value);
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().add(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //如果修改后是收入类型，原来是支出类型
        if(YTSystemConstant.PAYMENTS_TYPE.IN.equals(partyPayments.getType())&&YTSystemConstant.PAYMENTS_TYPE.OUT.equals(oldPartyPayments.getType())){
            BigDecimal value=partyPayments.getMoney().add(oldPartyPayments.getMoney());
            balance=balance.add(value);
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().add(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //如果修改后是支出类型，原来是收入类型
        if(YTSystemConstant.PAYMENTS_TYPE.OUT.equals(partyPayments.getType())&&YTSystemConstant.PAYMENTS_TYPE.IN.equals(oldPartyPayments.getType())){
            BigDecimal value=partyPayments.getMoney().add(oldPartyPayments.getMoney());
            balance=balance.subtract(value);
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().subtract(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //如果修改后是支出类型，原来也是支出类型
        if(YTSystemConstant.PAYMENTS_TYPE.OUT.equals(partyPayments.getType())&&YTSystemConstant.PAYMENTS_TYPE.OUT.equals(oldPartyPayments.getType())){
            BigDecimal value=partyPayments.getMoney().subtract(oldPartyPayments.getMoney());
            balance=balance.subtract(value);
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().subtract(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //设置修改后的的结余
        partyPayments.setBalance(balance);
        return this.baseMapper.updateById(partyPayments);
    }

    @Override
    public int deleteById(String id) {
        PartyPayments oldPartyPayments = this.baseMapper.selectById(id);
        BigDecimal value=oldPartyPayments.getMoney();
        //如果删除某条经费，那么这条数据之后新增的数据（结余）都要进行相应变化。
        QueryWrapper<PartyPayments> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("org_id",oldPartyPayments.getOrgId());
        queryWrapper.gt("create_time",oldPartyPayments.getCreateTime());
        List<PartyPayments> partyPaymentsList = this.baseMapper.selectList(queryWrapper);
        //如果删除的是收入类型
        if(YTSystemConstant.PAYMENTS_TYPE.IN.equals(oldPartyPayments.getType())){
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().subtract(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }
        //如果删除的是支出类型
        if(YTSystemConstant.PAYMENTS_TYPE.OUT.equals(oldPartyPayments.getType())){
            if(CollUtil.isNotEmpty(partyPaymentsList)){
                for (PartyPayments payments : partyPaymentsList) {
                    payments.setBalance(payments.getBalance().add(value));
                    this.baseMapper.updateById(payments);
                }
            }
        }

        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public PartyPayments findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<PartyPayments> findList(PartyPayments partyPayments) {
        QueryWrapper<PartyPayments> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("org_id",partyPayments.getOrgId());
        if(StringUtils.isNotEmpty(partyPayments.getPaymentTime())){
            queryWrapper.eq("payment_time",partyPayments.getPaymentTime());
        }
        queryWrapper.orderByDesc("payment_time");
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<PartyPaymentsVo> findPage(Page page, PartyPaymentsVo partyPayments) {
        /*PartyOrg partyOrg = userService.getPartyOrgByUserId("");
        if(partyOrg!=null){
            partyPayments.setPartyOrgId(partyOrg.getId());
        }*/
//        String orgId = this.userService.findById(UserContext.current().getUserId()).getOrgId();
//        partyPayments.setOrgId(orgId);
        IPage<PartyPaymentsVo> pagePartyPayments =this.baseMapper.getPage(page,partyPayments);
        return pagePartyPayments;
    }

    /**
     * 首页党建经费分页查询
     * @param page
     * @param partyPayments
     * @return
     */
    @Override
    public IPage<PartyPaymentsVo> findPartyPaymentsPage(Page page, PartyPaymentsVo partyPayments) {
        IPage<PartyPaymentsVo> pagePartyPayments =this.baseMapper.findPartyPaymentsPage(page,partyPayments);
        return pagePartyPayments;
    }

    @Override
    public void exportPayments(PartyPaymentsVo vo, HttpServletResponse response) {
        /*PartyOrg partyOrg = userService.getPartyOrgByUserId("");
        if(partyOrg!=null){
            vo.setPartyOrgId(partyOrg.getId());
        }*/
        List<PartyPaymentsVo> list = this.baseMapper.getPage(vo);
        int row = 0;
        for (PartyPaymentsVo paymentsVo:list){
            row = row +1;
            paymentsVo.setRowNo(row);
        }

//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("支部党建经费收支明细表.xls");
//        excelVo.setTemplateName("支部党建经费收支明细表.xls");
//        Context context = new Context();
//        String title = "支部党建经费收支明细表";
//        context.putVar("title",title);
//        context.putVar("list",list);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
