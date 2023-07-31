package com.zsgf.platform.controller.company;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.company.CompanyInfo;
import com.zsgf.platform.service.company.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/companyInfo")
@Api(tags = "省平台企业信息")
public class CompanyInfoController extends BaseController {


    private final CompanyInfoService companyInfoService;

    /**
     * 新增企业信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增企业信息")
    @SysLog(title = "新增企业信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody CompanyInfo companyInfo) {
        return RestResult.succeed(companyInfoService.saveCompanyInfo(companyInfo));
    }

    /**
     * 修改企业信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改企业信息")
    @SysLog(title = "修改企业信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody CompanyInfo companyInfo) {
        return RestResult.succeed(companyInfoService.updateCompanyInfo(companyInfo));
    }

    /**
     * 删除企业信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除企业信息")
    @SysLog(title = "删除企业信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(companyInfoService.deleteCompanyInfo(id));
    }


    /**
     * 分页查询企业信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询企业信息")
    public RestResult<PageT<CompanyInfo>> getPage(PageT<CompanyInfo> page, CompanyInfo companyInfo) {
        return RestResult.succeed(companyInfoService.findCompanyInfoPage(page, companyInfo));
    }

    /**
     * 查询企业信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询企业信息列表")
    public RestResult<List<CompanyInfo>> getList(CompanyInfo companyInfo) {
        return RestResult.succeed(companyInfoService.findCompanyInfoList(companyInfo));
    }

    /**
     * 获取企业信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取企业信息详细信息")
    public RestResult<CompanyInfo> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(companyInfoService.findCompanyInfoById(id));
    }
}
