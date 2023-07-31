package com.zsgf.platform.controller.businessLicense;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.businessLicense.BusinessLicenseDTO;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicense;
import com.zsgf.platform.service.businessLicense.BusinessLicenseService;
import com.zsgf.platform.vo.businessLicense.BusinessLicenseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营许可证_01基本信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessLicense")
@Api(tags = "危险废物_经营许可证_01基本信息")
public class BusinessLicenseController extends BaseController {


    private final BusinessLicenseService businessLicenseService;

    /**
     * 新增危险废物_经营许可证_01基本信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营许可证_01基本信息")
    @SysLog(title = "新增危险废物_经营许可证_01基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessLicense businessLicense) {
        return RestResult.succeed(businessLicenseService.saveBusinessLicense(businessLicense));
    }

    /**
     * 修改危险废物_经营许可证_01基本信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营许可证_01基本信息")
    @SysLog(title = "修改危险废物_经营许可证_01基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessLicense businessLicense) {
        return RestResult.succeed(businessLicenseService.updateBusinessLicense(businessLicense));
    }

    /**
     * 删除危险废物_经营许可证_01基本信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营许可证_01基本信息")
    @SysLog(title = "删除危险废物_经营许可证_01基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessLicenseService.deleteBusinessLicense(id));
    }


    /**
     * 分页查询危险废物_经营许可证_01基本信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营许可证_01基本信息")
    public RestResult<PageT<BusinessLicenseVo>> getPage(PageT<BusinessLicenseVo> page, BusinessLicenseDTO businessLicense) {
        return RestResult.succeed(businessLicenseService.findBusinessLicensePage(page, businessLicense));
    }

    /**
     * 查询危险废物_经营许可证_01基本信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营许可证_01基本信息列表")
    public RestResult<List<BusinessLicense>> getList(BusinessLicenseDTO businessLicense) {
        return RestResult.succeed(businessLicenseService.findBusinessLicenseList(businessLicense));
    }

    /**
     * 获取危险废物_经营许可证_01基本信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营许可证_01基本信息详细信息")
    public RestResult<BusinessLicense> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessLicenseService.findBusinessLicenseById(id));
    }
}
