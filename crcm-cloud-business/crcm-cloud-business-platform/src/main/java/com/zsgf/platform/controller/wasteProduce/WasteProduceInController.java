package com.zsgf.platform.controller.wasteProduce;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceIn;
import com.zsgf.platform.service.wasteProduce.WasteProduceInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产废入库信息Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteProduceIn")
@Api(tags = "产废入库信息")
public class WasteProduceInController extends BaseController {


    private final WasteProduceInService wasteProduceInService;

    /**
     * 新增产废入库信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增产废入库信息")
    @SysLog(title = "新增产废入库信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteProduceIn wasteProduceIn) {
        return RestResult.succeed(wasteProduceInService.saveWasteProduceIn(wasteProduceIn));
    }

    /**
     * 修改产废入库信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改产废入库信息")
    @SysLog(title = "修改产废入库信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteProduceIn wasteProduceIn) {
        return RestResult.succeed(wasteProduceInService.updateWasteProduceIn(wasteProduceIn));
    }

    /**
     * 删除产废入库信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除产废入库信息")
    @SysLog(title = "删除产废入库信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteProduceInService.deleteWasteProduceIn(id));
    }


    /**
     * 分页查询产废入库信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询产废入库信息")
    public RestResult<PageT<WasteProduceIn>> getPage(PageT<WasteProduceIn> page, WasteProduceIn wasteProduceIn) {
        return RestResult.succeed(wasteProduceInService.findWasteProduceInPage(page, wasteProduceIn));
    }

    /**
     * 查询产废入库信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询产废入库信息列表")
    public RestResult<List<WasteProduceIn>> getList(WasteProduceIn wasteProduceIn) {
        return RestResult.succeed(wasteProduceInService.findWasteProduceInList(wasteProduceIn));
    }

    /**
     * 获取产废入库信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取产废入库信息详细信息")
    public RestResult<WasteProduceIn> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteProduceInService.findWasteProduceInById(id));
    }

    /**
     * 产废批量入库
     */
    @PostMapping("/saveBatch")
    @ApiOperation(value = "产废批量入库")
    @SysLog(title = "产废批量入库", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> saveBatch(@RequestBody List<WasteProduceIn> wasteProduceIn) {
        return RestResult.succeed(wasteProduceInService.saveBatch(wasteProduceIn));
    }
}
