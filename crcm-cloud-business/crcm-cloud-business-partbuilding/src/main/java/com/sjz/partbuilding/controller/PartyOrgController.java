package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.PartyOrg;
import com.sjz.partbuilding.model.vo.PartyOrgVo;
import com.sjz.partbuilding.service.PartyOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;


/**
 * 党组织详细信息controller
 *
 * @author zzyt
 * @version 1.0
 */
@Api(tags = {"党组织详细信息表接口"})
@RestController
@RequestMapping("/partyOrg")
public class PartyOrgController extends BaseController {

    @Autowired
    private PartyOrgService partyOrgService;


    @GetMapping("/getStatisticsPartyOrg")
    @ApiOperation(value = "获取数据统计党组织信息表")
    public RestResult getStatisticsPartyOrg(PartyOrg partyOrg) {
        List<PartyOrgVo> partyOrgVoList = partyOrgService.getStatisticsPartyOrg(partyOrg);
        return RestResult.succeed(partyOrgVoList);
    }

    /**
     * 支部信息综合报表导出
     * @param vo
     * @return
     */
    @GetMapping("/exportPartyOrgInfo")
    public RestResult exportPartyOrgInfo(PartyOrgVo vo, HttpServletResponse httpServletResponse){
        partyOrgService.exportPartyOrgInfo(vo,httpServletResponse);
        return RestResult.succeed();
    }

    @GetMapping("/getPartyOrgCompany")
    @ApiOperation(value = "通过ID查询组织信息")
    public RestResult getPartyOrgCompany(String orgId){
        return RestResult.succeed(partyOrgService.getPartyOrgCompany(orgId));
    }

    /**
     * 组织台账
     * @param orgId
     * @return
     */
    @GetMapping("/getStandBook")
    @ApiOperation(value = "通过ID查询组织信息")
    public RestResult getStandBook(String orgId){
        PageVO page=new PageVO();
        return RestResult.succeed(partyOrgService.getStandBook(UtilPage.initPage(page),orgId));
    }
}

