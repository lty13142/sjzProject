package com.zsgf.platform.controller.company;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.company.CompanyDevice;
import com.zsgf.platform.service.company.CompanyDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司设备信息Controller
 *
 * @author gzl
 * @date 2023-03-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/companyDevice")
@Api(tags = "公司设备信息")
public class CompanyDeviceController extends BaseController {


    private final CompanyDeviceService companyDeviceService;

    /**
     * 新增公司设备信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增公司设备信息")
    @SysLog(title = "新增公司设备信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody CompanyDevice companyDevice) {
        return RestResult.succeed(companyDeviceService.saveCompanyDevice(companyDevice));
    }

    /**
     * 修改公司设备信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改公司设备信息")
    @SysLog(title = "修改公司设备信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody CompanyDevice companyDevice) {
        return RestResult.succeed(companyDeviceService.updateCompanyDevice(companyDevice));
    }

    /**
     * 删除公司设备信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除公司设备信息")
    @SysLog(title = "删除公司设备信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(companyDeviceService.deleteCompanyDevice(id));
    }


    /**
     * 分页查询公司设备信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询公司设备信息")
    public RestResult<PageT<CompanyDevice>> getPage(PageT<CompanyDevice> page, CompanyDevice companyDevice) {
        return RestResult.succeed(companyDeviceService.findCompanyDevicePage(page, companyDevice));
    }

    /**
     * 查询公司设备信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询公司设备信息列表")
    public RestResult<List<CompanyDevice>> getList(CompanyDevice companyDevice) {
        return RestResult.succeed(companyDeviceService.findCompanyDeviceList(companyDevice));
    }

    /**
     * 获取公司设备信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取公司设备信息详细信息")
    public RestResult<CompanyDevice> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(companyDeviceService.findCompanyDeviceById(id));
    }
}
