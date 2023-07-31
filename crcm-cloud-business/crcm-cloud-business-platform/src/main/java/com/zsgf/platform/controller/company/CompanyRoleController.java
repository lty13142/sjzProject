package com.zsgf.platform.controller.company;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.company.CompanyRole;
import com.zsgf.platform.service.company.CompanyRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 固废企业信息_企业角色类型Controller
 *
 * @author gzl
 * @date 2023-02-10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/companyRole")
@Api(tags = "固废企业信息_企业角色类型")
public class CompanyRoleController extends BaseController {


    private final CompanyRoleService companyRoleService;

    /**
     * 新增固废企业信息_企业角色类型
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增固废企业信息_企业角色类型")
    @SysLog(title = "新增固废企业信息_企业角色类型", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody CompanyRole companyRole) {
        return RestResult.succeed(companyRoleService.saveCompanyRole(companyRole));
    }

    /**
     * 修改固废企业信息_企业角色类型
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改固废企业信息_企业角色类型")
    @SysLog(title = "修改固废企业信息_企业角色类型", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody CompanyRole companyRole) {
        return RestResult.succeed(companyRoleService.updateCompanyRole(companyRole));
    }

    /**
     * 删除固废企业信息_企业角色类型
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除固废企业信息_企业角色类型")
    @SysLog(title = "删除固废企业信息_企业角色类型", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(companyRoleService.deleteCompanyRole(id));
    }


    /**
     * 分页查询固废企业信息_企业角色类型
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询固废企业信息_企业角色类型")
    public RestResult<PageT<CompanyRole>> getPage(PageT<CompanyRole> page, CompanyRole companyRole) {
        return RestResult.succeed(companyRoleService.findCompanyRolePage(page, companyRole));
    }

    /**
     * 查询固废企业信息_企业角色类型列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询固废企业信息_企业角色类型列表")
    public RestResult<List<CompanyRole>> getList(CompanyRole companyRole) {
        return RestResult.succeed(companyRoleService.findCompanyRoleList(companyRole));
    }

    /**
     * 获取固废企业信息_企业角色类型详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取固废企业信息_企业角色类型详细信息")
    public RestResult<CompanyRole> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(companyRoleService.findCompanyRoleById(id));
    }
}
