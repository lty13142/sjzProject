package com.zsgf.platform.controller.business;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.business.WasteBusinessInOut;
import com.zsgf.platform.service.business.WasteBusinessInOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 经营出入场记录Controller
 *
 * @author gzl
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteBusinessInOut")
@Api(tags = "经营出入场记录")
public class WasteBusinessInOutController extends BaseController {


    private final WasteBusinessInOutService wasteBusinessInOutService;

    /**
     * 新增经营出入场记录
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增经营出入场记录")
    @SysLog(title = "新增经营出入场记录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteBusinessInOut wasteBusinessInOut) {
        return RestResult.succeed(wasteBusinessInOutService.saveWasteBusinessInOut(wasteBusinessInOut));
    }

    /**
     * 修改经营出入场记录
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改经营出入场记录")
    @SysLog(title = "修改经营出入场记录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteBusinessInOut wasteBusinessInOut) {
        return RestResult.succeed(wasteBusinessInOutService.updateWasteBusinessInOut(wasteBusinessInOut));
    }

    /**
     * 删除经营出入场记录
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除经营出入场记录")
    @SysLog(title = "删除经营出入场记录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteBusinessInOutService.deleteWasteBusinessInOut(id));
    }


    /**
     * 分页查询经营出入场记录
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询经营出入场记录")
    public RestResult<PageT<WasteBusinessInOut>> getPage(PageT<WasteBusinessInOut> page, WasteBusinessInOut wasteBusinessInOut) {
        return RestResult.succeed(wasteBusinessInOutService.findWasteBusinessInOutPage(page, wasteBusinessInOut));
    }

    /**
     * 查询经营出入场记录列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询经营出入场记录列表")
    public RestResult<List<WasteBusinessInOut>> getList(WasteBusinessInOut wasteBusinessInOut) {
        return RestResult.succeed(wasteBusinessInOutService.findWasteBusinessInOutList(wasteBusinessInOut));
    }

    /**
     * 获取经营出入场记录详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取经营出入场记录详细信息")
    public RestResult<WasteBusinessInOut> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteBusinessInOutService.findWasteBusinessInOutById(id));
    }
}
