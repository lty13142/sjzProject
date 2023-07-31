package com.zsgf.platform.controller.businessLicense;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseMainEquipment;
import com.zsgf.platform.service.businessLicense.BusinessLicenseMainEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营许可证_03主要设备装置情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessLicenseMainEquipment")
@Api(tags = "危险废物_经营许可证_03主要设备装置情况")
public class BusinessLicenseMainEquipmentController extends BaseController {


    private final BusinessLicenseMainEquipmentService businessLicenseMainEquipmentService;

    /**
     * 新增危险废物_经营许可证_03主要设备装置情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营许可证_03主要设备装置情况")
    @SysLog(title = "新增危险废物_经营许可证_03主要设备装置情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        return RestResult.succeed(businessLicenseMainEquipmentService.saveBusinessLicenseMainEquipment(businessLicenseMainEquipment));
    }

    /**
     * 修改危险废物_经营许可证_03主要设备装置情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营许可证_03主要设备装置情况")
    @SysLog(title = "修改危险废物_经营许可证_03主要设备装置情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        return RestResult.succeed(businessLicenseMainEquipmentService.updateBusinessLicenseMainEquipment(businessLicenseMainEquipment));
    }

    /**
     * 删除危险废物_经营许可证_03主要设备装置情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营许可证_03主要设备装置情况")
    @SysLog(title = "删除危险废物_经营许可证_03主要设备装置情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessLicenseMainEquipmentService.deleteBusinessLicenseMainEquipment(id));
    }


    /**
     * 分页查询危险废物_经营许可证_03主要设备装置情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营许可证_03主要设备装置情况")
    public RestResult<PageT<BusinessLicenseMainEquipment>> getPage(PageT<BusinessLicenseMainEquipment> page, BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        return RestResult.succeed(businessLicenseMainEquipmentService.findBusinessLicenseMainEquipmentPage(page, businessLicenseMainEquipment));
    }

    /**
     * 查询危险废物_经营许可证_03主要设备装置情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营许可证_03主要设备装置情况列表")
    public RestResult<List<BusinessLicenseMainEquipment>> getList(BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        return RestResult.succeed(businessLicenseMainEquipmentService.findBusinessLicenseMainEquipmentList(businessLicenseMainEquipment));
    }

    /**
     * 获取危险废物_经营许可证_03主要设备装置情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营许可证_03主要设备装置情况详细信息")
    public RestResult<BusinessLicenseMainEquipment> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessLicenseMainEquipmentService.findBusinessLicenseMainEquipmentById(id));
    }
}
