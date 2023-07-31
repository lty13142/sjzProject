package com.zsgf.platform.controller.sheets;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.sheets.EquipmentSheetDTO;
import com.zsgf.platform.dto.sheets.EquipmentSheetUploadDTO;
import com.zsgf.platform.model.entity.sheets.EquipmentSheet;
import com.zsgf.platform.service.sheets.EquipmentSheetService;
import com.zsgf.platform.vo.sheets.EquipmentSheetFormWorkVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备确认单Controller
 *
 * @author gzl
 * @date 2023-03-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/equipmentSheet")
@Api(tags = "设备确认单")
public class EquipmentSheetController extends BaseController {


    private final EquipmentSheetService equipmentSheetService;

    /**
     * 新增设备确认单
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增设备确认单")
    @SysLog(title = "新增设备确认单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody EquipmentSheet equipmentSheet) {
        return RestResult.succeed(equipmentSheetService.saveEquipmentSheet(equipmentSheet));
    }

    /**
     * 修改设备确认单
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改设备确认单")
    @SysLog(title = "修改设备确认单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody EquipmentSheet equipmentSheet) {
        return RestResult.succeed(equipmentSheetService.updateEquipmentSheet(equipmentSheet));
    }

    /**
     * 删除设备确认单
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除设备确认单")
    @SysLog(title = "删除设备确认单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") Long id) {
        return RestResult.succeed(equipmentSheetService.deleteEquipmentSheet(id));
    }

    /**
     * 上传设备确认单
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传设备确认单")
    @SysLog(title = "上传设备确认单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> upload(@Validated @RequestBody EquipmentSheetUploadDTO equipmentSheet) {
        return RestResult.succeed(equipmentSheetService.upload(equipmentSheet));
    }


    /**
     * 分页查询设备确认单
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询设备确认单")
    public RestResult<PageT<EquipmentSheet>> getPage(PageT<EquipmentSheet> page, EquipmentSheetDTO equipmentSheet) {
        return RestResult.succeed(equipmentSheetService.findEquipmentSheetPage(page, equipmentSheet));
    }

    /**
     * 查询设备确认单列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询设备确认单列表")
    public RestResult<List<EquipmentSheet>> getList(EquipmentSheetDTO equipmentSheet) {
        return RestResult.succeed(equipmentSheetService.findEquipmentSheetList(equipmentSheet));
    }

    /**
     * 获取设备确认单详细信息
     */
    @GetMapping(value = "/getInfo")
    @ApiOperation(value = "获取设备确认单详细信息")
    public RestResult<EquipmentSheetFormWorkVo> getInfo() {
        return RestResult.succeed(equipmentSheetService.getInfo());
    }

    /**
     * 获取设备确认单详细信息
     */
    @GetMapping(value = "/uploadStatus")
    @ApiOperation(value = "获取设备确认单上传状态")
    public RestResult<Boolean> getUploadStatus() {
        return RestResult.succeed(equipmentSheetService.getUploadStatus());
    }
}
