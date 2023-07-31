package com.crcm.admin.controller;

import java.util.List;

import com.crcm.cloud.start.sso.annation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.crcm.admin.model.entity.Agreement;
import com.crcm.admin.service.AgreementService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;

/**
 * 协议管理Controller
 * 
 * @author cb
 * @date 2023-06-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/agreement")
@Api(tags = "协议管理")
public class AgreementController extends BaseController {


    private final AgreementService agreementService;

    /**
     * 新增协议管理
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增协议管理")
    @SysLog(title = "新增协议管理", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody Agreement agreement) {
        return RestResult.succeed(agreementService.saveAgreement(agreement));
    }

    /**
     * 修改协议管理
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改协议管理")
    @SysLog(title = "修改协议管理", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody Agreement agreement) {
        return RestResult.succeed(agreementService.updateAgreement(agreement));
    }

    /**
     * 删除协议管理
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除协议管理")
    @SysLog(title = "删除协议管理", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(agreementService.deleteAgreement(id));
    }


    /**
     * 分页查询协议管理
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询协议管理")
    public RestResult<PageT<Agreement>> getPage(PageT<Agreement> page, Agreement agreement) {
        return RestResult.succeed(agreementService.findAgreementPage(page, agreement));
    }

    /**
     * 查询协议管理列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询协议管理列表")
    public RestResult<List<Agreement>> getList(Agreement agreement) {
        return RestResult.succeed(agreementService.findAgreementList(agreement));
    }

    /**
     * 获取协议管理详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取协议管理详细信息")
    public RestResult<Agreement> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(agreementService.findAgreementById(id));
    }

    /**
     * 通过类型获取最后更新协议管理
     */
    @Inner
    @GetMapping(value = "/getLastByType")
    @ApiOperation(value = "通过类型获取最后更新协议管理")
    public RestResult<Agreement> getLastByType(Agreement agreement) {
        return RestResult.succeed(agreementService.getLastByType(agreement));
    }

    /**
     * 通过类型获取协议管理列表
     */
    @GetMapping(value = "/getListByType")
    @ApiOperation(value = "通过类型获取协议管理列表")
    public RestResult<List<Agreement>> getListByType(Agreement agreement) {
        return RestResult.succeed(agreementService.getListByType(agreement));
    }
}
