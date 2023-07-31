package com.zsgf.platform.controller.wasteProduce;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceOut;
import com.zsgf.platform.service.wasteProduce.WasteProduceOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产废出库信息Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteProduceOut")
@Api(tags = "产废出库信息")
public class WasteProduceOutController extends BaseController {


    private final WasteProduceOutService wasteProduceOutService;

    /**
     * 新增产废出库信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增产废出库信息")
    @SysLog(title = "新增产废出库信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteProduceOut wasteProduceOut) {
        return RestResult.succeed(wasteProduceOutService.saveWasteProduceOut(wasteProduceOut));
    }

    /**
     * 修改产废出库信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改产废出库信息")
    @SysLog(title = "修改产废出库信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteProduceOut wasteProduceOut) {
        return RestResult.succeed(wasteProduceOutService.updateWasteProduceOut(wasteProduceOut));
    }

    /**
     * 删除产废出库信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除产废出库信息")
    @SysLog(title = "删除产废出库信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteProduceOutService.deleteWasteProduceOut(id));
    }


    /**
     * 分页查询产废出库信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询产废出库信息")
    public RestResult<PageT<WasteProduceOut>> getPage(PageT<WasteProduceOut> page, WasteProduceOut wasteProduceOut) {
        return RestResult.succeed(wasteProduceOutService.findWasteProduceOutPage(page, wasteProduceOut));
    }

    /**
     * 查询产废出库信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询产废出库信息列表")
    public RestResult<List<WasteProduceOut>> getList(WasteProduceOut wasteProduceOut) {
        return RestResult.succeed(wasteProduceOutService.findWasteProduceOutList(wasteProduceOut));
    }

    /**
     * 获取产废出库信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取产废出库信息详细信息")
    public RestResult<WasteProduceOut> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteProduceOutService.findWasteProduceOutById(id));
    }
}
