package com.zsgf.platform.controller.sheets;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.sheets.EquipmentSheetDetail;
import com.zsgf.platform.service.sheets.EquipmentSheetDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备确认单详情Controller
 *
 * @author gzl
 * @date 2023-03-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/equipmentSheetDetail")
@Api(tags = "设备确认单详情")
public class EquipmentSheetDetailController extends BaseController {


    private final EquipmentSheetDetailService equipmentSheetDetailService;

    /**
     * 新增设备确认单详情
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增设备确认单详情")
    @SysLog(title = "新增设备确认单详情", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody EquipmentSheetDetail equipmentSheetDetail) {
        return RestResult.succeed(equipmentSheetDetailService.saveEquipmentSheetDetail(equipmentSheetDetail));
    }

    /**
     * 修改设备确认单详情
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改设备确认单详情")
    @SysLog(title = "修改设备确认单详情", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody EquipmentSheetDetail equipmentSheetDetail) {
        return RestResult.succeed(equipmentSheetDetailService.updateEquipmentSheetDetail(equipmentSheetDetail));
    }

    /**
     * 删除设备确认单详情
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除设备确认单详情")
    @SysLog(title = "删除设备确认单详情", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") Long id) {
        return RestResult.succeed(equipmentSheetDetailService.deleteEquipmentSheetDetail(id));
    }


    /**
     * 分页查询设备确认单详情
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询设备确认单详情")
    public RestResult<PageT<EquipmentSheetDetail>> getPage(PageT<EquipmentSheetDetail> page, EquipmentSheetDetail equipmentSheetDetail) {
        return RestResult.succeed(equipmentSheetDetailService.findEquipmentSheetDetailPage(page, equipmentSheetDetail));
    }

    /**
     * 查询设备确认单详情列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询设备确认单详情列表")
    public RestResult<List<EquipmentSheetDetail>> getList(EquipmentSheetDetail equipmentSheetDetail) {
        return RestResult.succeed(equipmentSheetDetailService.findEquipmentSheetDetailList(equipmentSheetDetail));
    }

    /**
     * 获取设备确认单详情详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取设备确认单详情详细信息")
    public RestResult<EquipmentSheetDetail> getInfo(@PathVariable("id") Long id) {
        return RestResult.succeed(equipmentSheetDetailService.findEquipmentSheetDetailById(id));
    }
}
