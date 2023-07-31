package com.zsgf.platform.controller.wasteProduce;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceRecord;
import com.zsgf.platform.service.wasteProduce.WasteProduceRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产废产生台账Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteProduceRecord")
@Api(tags = "产废产生台账")
public class WasteProduceRecordController extends BaseController {


    private final WasteProduceRecordService wasteProduceRecordService;

    /**
     * 新增产废产生台账
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增产废产生台账")
    @SysLog(title = "新增产废产生台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteProduceRecord wasteProduceRecord) {
        return RestResult.succeed(wasteProduceRecordService.saveWasteProduceRecord(wasteProduceRecord));
    }

    /**
     * 修改产废产生台账
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改产废产生台账")
    @SysLog(title = "修改产废产生台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteProduceRecord wasteProduceRecord) {
        return RestResult.succeed(wasteProduceRecordService.updateWasteProduceRecord(wasteProduceRecord));
    }

    /**
     * 删除产废产生台账
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除产废产生台账")
    @SysLog(title = "删除产废产生台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteProduceRecordService.deleteWasteProduceRecord(id));
    }


    /**
     * 分页查询产废产生台账
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询产废产生台账")
    public RestResult<PageT<WasteProduceRecord>> getPage(PageT<WasteProduceRecord> page, WasteProduceRecord wasteProduceRecord) {
        return RestResult.succeed(wasteProduceRecordService.findWasteProduceRecordPage(page, wasteProduceRecord));
    }

    /**
     * 查询产废产生台账列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询产废产生台账列表")
    public RestResult<List<WasteProduceRecord>> getList(WasteProduceRecord wasteProduceRecord) {
        return RestResult.succeed(wasteProduceRecordService.findWasteProduceRecordList(wasteProduceRecord));
    }

    /**
     * 获取产废产生台账详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取产废产生台账详细信息")
    public RestResult<WasteProduceRecord> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteProduceRecordService.findWasteProduceRecordById(id));
    }
}
