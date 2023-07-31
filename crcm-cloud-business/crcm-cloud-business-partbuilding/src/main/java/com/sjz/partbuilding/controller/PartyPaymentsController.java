package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.PartyPayments;
import com.sjz.partbuilding.model.vo.PartyPaymentsVo;
import com.sjz.partbuilding.service.PartyPaymentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Api(tags = {"党建经费收支表接口"})
@RestController
@RequestMapping("/partyPayments")
public class PartyPaymentsController extends BaseController {

    @Autowired
    private PartyPaymentsService partyPaymentsService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增党建经费收支", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody PartyPayments partyPayments){
        return RestResult.succeed(partyPaymentsService.savePartyPayments(partyPayments));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改党建经费收支", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody PartyPayments partyPayments){
        //partyPaymentsService.updatePartyPayments(partyPayments);
        partyPaymentsService.updatePartyPayments2(partyPayments);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除党建经费收支", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody PartyPayments partyPayments){
        partyPaymentsService.deleteById(partyPayments.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(partyPaymentsService.findById(id));
    }


    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(PartyPayments partyPayments){
        return RestResult.succeed(partyPaymentsService.findList(partyPayments));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(PartyPaymentsVo partyPayments, PageVO pageVo){
        return RestResult.succeed(partyPaymentsService.findPage(UtilPage.initPage(pageVo),partyPayments));
    }

    @GetMapping("/findPage")
    @ApiOperation(value = "首页党建经费分页查询")
    public RestResult findPage(PartyPaymentsVo partyPayments, PageVO pageVo){
        return RestResult.succeed(partyPaymentsService.findPartyPaymentsPage(UtilPage.initPage(pageVo),partyPayments));
    }


    /**
     * 导出党建收入支出明细
     * @param vo
     * @return
     */
    @GetMapping("/exportPayments")
    public RestResult exportPayments(PartyPaymentsVo vo, HttpServletResponse httpServletResponse){
        partyPaymentsService.exportPayments(vo,httpServletResponse);
        return null;
    }
}

