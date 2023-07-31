package com.zsgf.platform.controller.lab;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.lab.WasteLabInventory;
import com.zsgf.platform.service.lab.WasteLabInventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 实验室台账库存Controller
 *
 * @author gzl
 * @date 2023-03-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteLabInventory")
@Api(tags = "实验室台账库存")
public class WasteLabInventoryController extends BaseController {


    private final WasteLabInventoryService wasteLabInventoryService;

    /**
     * 新增实验室台账库存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增实验室台账库存")
    @SysLog(title = "新增实验室台账库存", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteLabInventory wasteLabInventory) {
        return RestResult.succeed(wasteLabInventoryService.saveWasteLabInventory(wasteLabInventory));
    }

    /**
     * 修改实验室台账库存
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改实验室台账库存")
    @SysLog(title = "修改实验室台账库存", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteLabInventory wasteLabInventory) {
        return RestResult.succeed(wasteLabInventoryService.updateWasteLabInventory(wasteLabInventory));
    }

    /**
     * 删除实验室台账库存
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除实验室台账库存")
    @SysLog(title = "删除实验室台账库存", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteLabInventoryService.deleteWasteLabInventory(id));
    }


    /**
     * 分页查询实验室台账库存
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询实验室台账库存")
    public RestResult<PageT<WasteLabInventory>> getPage(PageT<WasteLabInventory> page, WasteLabInventory wasteLabInventory) {
        return RestResult.succeed(wasteLabInventoryService.findWasteLabInventoryPage(page, wasteLabInventory));
    }

    /**
     * 查询实验室台账库存列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询实验室台账库存列表")
    public RestResult<List<WasteLabInventory>> getList(WasteLabInventory wasteLabInventory) {
        return RestResult.succeed(wasteLabInventoryService.findWasteLabInventoryList(wasteLabInventory));
    }

    /**
     * 获取实验室台账库存详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取实验室台账库存详细信息")
    public RestResult<WasteLabInventory> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteLabInventoryService.findWasteLabInventoryById(id));
    }
}
