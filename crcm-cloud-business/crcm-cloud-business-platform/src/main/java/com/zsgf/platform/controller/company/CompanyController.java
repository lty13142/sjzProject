package com.zsgf.platform.controller.company;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.company.Company;
import com.zsgf.platform.service.company.CompanyService;
import com.zsgf.platform.vo.company.CompanySelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业基本信息Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/company")
@Api(tags = "企业基本信息")
public class CompanyController extends BaseController {


    private final CompanyService companyService;

    /**
     * 新增企业基本信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增企业基本信息")
    @SysLog(title = "新增企业基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody Company company) {
        return RestResult.succeed(companyService.saveCompany(company));
    }

    /**
     * 修改企业基本信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改企业基本信息")
    @SysLog(title = "修改企业基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody Company company) {
        return RestResult.succeed(companyService.updateCompany(company));
    }

    /**
     * 删除企业基本信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除企业基本信息")
    @SysLog(title = "删除企业基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(companyService.deleteCompany(id));
    }


    /**
     * 分页查询企业基本信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询企业基本信息")
    public RestResult<PageT<Company>> getPage(PageT<Company> page, Company company) {
        return RestResult.succeed(companyService.findCompanyPage(page, company));
    }

    /**
     * 查询企业基本信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询企业基本信息列表")
    public RestResult<List<Company>> getList(Company company) {
        return RestResult.succeed(companyService.findCompanyList(company));
    }

    /**
     * 查询企业基本信息列表
     */
    @GetMapping("/selectList")
    @ApiOperation(value = "查询企业基本信息列表")
    public RestResult<List<CompanySelectVo>> getSelectList(String companyLabel) {
        return RestResult.succeed(companyService.getSelectList(companyLabel));
    }

    /**
     * 获取企业基本信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取企业基本信息详细信息")
    public RestResult<Company> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(companyService.findCompanyById(id));
    }
}
