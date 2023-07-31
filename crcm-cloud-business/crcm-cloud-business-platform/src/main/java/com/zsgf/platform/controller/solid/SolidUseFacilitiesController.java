package com.zsgf.platform.controller.solid;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.solid.SolidUseFacilities;
import com.zsgf.platform.service.solid.SolidUseFacilitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_综合利用设施信息Controller
 *
 * @author gzl
 * @date 2023-03-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/solidUseFacilities")
@Api(tags = "数据共享_一般工业固体废物_综合利用设施信息")
public class SolidUseFacilitiesController extends BaseController {


    private final SolidUseFacilitiesService solidUseFacilitiesService;

    /**
     * 新增数据共享_一般工业固体废物_综合利用设施信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增数据共享_一般工业固体废物_综合利用设施信息")
    @SysLog(title = "新增数据共享_一般工业固体废物_综合利用设施信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SolidUseFacilities solidUseFacilities) {
        return RestResult.succeed(solidUseFacilitiesService.saveSolidUseFacilities(solidUseFacilities));
    }

    /**
     * 修改数据共享_一般工业固体废物_综合利用设施信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改数据共享_一般工业固体废物_综合利用设施信息")
    @SysLog(title = "修改数据共享_一般工业固体废物_综合利用设施信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody SolidUseFacilities solidUseFacilities) {
        return RestResult.succeed(solidUseFacilitiesService.updateSolidUseFacilities(solidUseFacilities));
    }

    /**
     * 删除数据共享_一般工业固体废物_综合利用设施信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据共享_一般工业固体废物_综合利用设施信息")
    @SysLog(title = "删除数据共享_一般工业固体废物_综合利用设施信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(solidUseFacilitiesService.deleteSolidUseFacilities(id));
    }


    /**
     * 分页查询数据共享_一般工业固体废物_综合利用设施信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询数据共享_一般工业固体废物_综合利用设施信息")
    public RestResult<PageT<SolidUseFacilities>> getPage(PageT<SolidUseFacilities> page, SolidUseFacilities solidUseFacilities) {
        return RestResult.succeed(solidUseFacilitiesService.findSolidUseFacilitiesPage(page, solidUseFacilities));
    }

    /**
     * 查询数据共享_一般工业固体废物_综合利用设施信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询数据共享_一般工业固体废物_综合利用设施信息列表")
    public RestResult<List<SolidUseFacilities>> getList(SolidUseFacilities solidUseFacilities) {
        return RestResult.succeed(solidUseFacilitiesService.findSolidUseFacilitiesList(solidUseFacilities));
    }

    /**
     * 获取数据共享_一般工业固体废物_综合利用设施信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取数据共享_一般工业固体废物_综合利用设施信息详细信息")
    public RestResult<SolidUseFacilities> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(solidUseFacilitiesService.findSolidUseFacilitiesById(id));
    }
}
