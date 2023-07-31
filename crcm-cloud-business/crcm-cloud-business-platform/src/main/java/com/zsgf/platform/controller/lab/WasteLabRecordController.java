package com.zsgf.platform.controller.lab;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.lab.WasteLabRecord;
import com.zsgf.platform.service.lab.WasteLabRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 实验室台账Controller
 *
 * @author gzl
 * @date 2023-03-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteLabRecord")
@Api(tags = "实验室台账")
public class WasteLabRecordController extends BaseController {


    private final WasteLabRecordService wasteLabRecordService;

    /**
     * 新增实验室台账
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增实验室台账")
    @SysLog(title = "新增实验室台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteLabRecord wasteLabRecord) {
        return RestResult.succeed(wasteLabRecordService.saveWasteLabRecord(wasteLabRecord));
    }

    /**
     * 修改实验室台账
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改实验室台账")
    @SysLog(title = "修改实验室台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteLabRecord wasteLabRecord) {
        return RestResult.succeed(wasteLabRecordService.updateWasteLabRecord(wasteLabRecord));
    }

    /**
     * 删除实验室台账
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除实验室台账")
    @SysLog(title = "删除实验室台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteLabRecordService.deleteWasteLabRecord(id));
    }


    /**
     * 分页查询实验室台账
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询实验室台账")
    public RestResult<PageT<WasteLabRecord>> getPage(PageT<WasteLabRecord> page, WasteLabRecord wasteLabRecord) {
        return RestResult.succeed(wasteLabRecordService.findWasteLabRecordPage(page, wasteLabRecord));
    }

    /**
     * 查询实验室台账列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询实验室台账列表")
    public RestResult<List<WasteLabRecord>> getList(WasteLabRecord wasteLabRecord) {
        return RestResult.succeed(wasteLabRecordService.findWasteLabRecordList(wasteLabRecord));
    }

    /**
     * 获取实验室台账详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取实验室台账详细信息")
    public RestResult<WasteLabRecord> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteLabRecordService.findWasteLabRecordById(id));
    }
}
