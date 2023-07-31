package com.zsgf.platform.controller.businessLicense;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseOperatingFacilities;
import com.zsgf.platform.service.businessLicense.BusinessLicenseOperatingFacilitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营许可证_02经营设施信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessLicenseOperatingFacilities")
@Api(tags = "危险废物_经营许可证_02经营设施信息")
public class BusinessLicenseOperatingFacilitiesController extends BaseController {


    private final BusinessLicenseOperatingFacilitiesService businessLicenseOperatingFacilitiesService;

    /**
     * 新增危险废物_经营许可证_02经营设施信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营许可证_02经营设施信息")
    @SysLog(title = "新增危险废物_经营许可证_02经营设施信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        return RestResult.succeed(businessLicenseOperatingFacilitiesService.saveBusinessLicenseOperatingFacilities(businessLicenseOperatingFacilities));
    }

    /**
     * 修改危险废物_经营许可证_02经营设施信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营许可证_02经营设施信息")
    @SysLog(title = "修改危险废物_经营许可证_02经营设施信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        return RestResult.succeed(businessLicenseOperatingFacilitiesService.updateBusinessLicenseOperatingFacilities(businessLicenseOperatingFacilities));
    }

    /**
     * 删除危险废物_经营许可证_02经营设施信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营许可证_02经营设施信息")
    @SysLog(title = "删除危险废物_经营许可证_02经营设施信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessLicenseOperatingFacilitiesService.deleteBusinessLicenseOperatingFacilities(id));
    }


    /**
     * 分页查询危险废物_经营许可证_02经营设施信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营许可证_02经营设施信息")
    public RestResult<PageT<BusinessLicenseOperatingFacilities>> getPage(PageT<BusinessLicenseOperatingFacilities> page, BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        return RestResult.succeed(businessLicenseOperatingFacilitiesService.findBusinessLicenseOperatingFacilitiesPage(page, businessLicenseOperatingFacilities));
    }

    /**
     * 查询危险废物_经营许可证_02经营设施信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营许可证_02经营设施信息列表")
    public RestResult<List<BusinessLicenseOperatingFacilities>> getList(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        return RestResult.succeed(businessLicenseOperatingFacilitiesService.findBusinessLicenseOperatingFacilitiesList(businessLicenseOperatingFacilities));
    }

    /**
     * 获取危险废物_经营许可证_02经营设施信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营许可证_02经营设施信息详细信息")
    public RestResult<BusinessLicenseOperatingFacilities> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessLicenseOperatingFacilitiesService.findBusinessLicenseOperatingFacilitiesById(id));
    }
}
