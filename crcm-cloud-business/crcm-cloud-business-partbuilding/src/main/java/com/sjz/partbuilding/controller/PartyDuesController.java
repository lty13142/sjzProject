package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.PartyDues;
import com.sjz.partbuilding.model.vo.PartyDuesVo;
import com.sjz.partbuilding.service.PartyDuesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;


@Api(tags = {"党费管理接口"})
@RestController
@RequestMapping("/partyDues")
public class PartyDuesController extends BaseController {

    @Resource
    private PartyDuesService partyDuesService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增党费管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody PartyDues partyDues) {
        partyDuesService.savePartyDues(partyDues);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改党费管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody PartyDues partyDues) {
        partyDuesService.updatePartyDues(partyDues);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除党费管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody PartyDues partyDues) {
        partyDuesService.deleteById(partyDues.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id) {
        return RestResult.succeed(partyDuesService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(PartyDues partyDues) {
        return RestResult.succeed(partyDuesService.findList(partyDues));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(PartyDues partyDues, PageVO pageVo) {
        return RestResult.succeed(partyDuesService.findPage(UtilPage.initPage(pageVo), partyDues));
    }

    /**
     * 解析Excel并保存到数据库
     *
     * @return
     */
    @PostMapping("/saveByExcel")
    @SysLog(title = "导入党费管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.IMPORT)
    public RestResult saveByExcel(@RequestBody MultipartFile file, PartyDues partyDues) throws ParseException {
        String result = partyDuesService.saveByExcel(file, partyDues);
        if ("no".equals(result)) {
            return RestResult.failed("导入失败");
        } else {
            return RestResult.succeed(result);
        }
    }

    @GetMapping("/getLoginPartyDues")
    @ApiOperation(value = "获取首页党费统计图")
    public RestResult getLoginPartyDues() {
        Map<String, Object> arrays = partyDuesService.getLoginPartyDues();
        return RestResult.succeed(arrays);
    }

    /**
     * 导出党费缴纳明细
     *
     * @param vo
     * @return
     */
    @GetMapping("/exportPartyDues")
    public RestResult exportPartyDues(PartyDues vo, HttpServletResponse httpServletResponse) {
        partyDuesService.exportPartyDues(vo, httpServletResponse);
//        return RestResult.succeed("导出成功");
        return null;
    }

    /**
     * 导出党费缴纳模板
     *
     * @param vo
     * @return
     */
    @GetMapping("/exportPartyDuesTemplate")
    public RestResult exportPartyDuesTemplate(PartyDuesVo vo, HttpServletResponse httpServletResponse) {
        partyDuesService.exportPartyDuesTemplate(vo, httpServletResponse);
//        return RestResult.succeed("导出成功");
        return null;
    }

    /**
     * 查询党费缴纳，党建费用收支统计+折线图
     *
     * @param dues
     * @return
     */
    @GetMapping("/getDuesPayments")
    public RestResult getDuesPayments(PartyDues dues) {
        return RestResult.succeed(partyDuesService.getDuesPayments(dues));
    }

    /**
     * 查询党费情况
     *
     * @param dues
     * @return
     */
    @GetMapping("/getPartySituation")
    public RestResult getPartySituation(PartyDues dues) {
        return RestResult.succeed(partyDuesService.getPartySituation(dues));
    }
}

