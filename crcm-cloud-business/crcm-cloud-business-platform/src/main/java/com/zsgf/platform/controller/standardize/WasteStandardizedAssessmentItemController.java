package com.zsgf.platform.controller.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDTO;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItem;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规范化考核大项Controller
 *
 * @author gzl
 * @date 2023-03-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteStandardizedAssessmentItem")
@Api(tags = "规范化考核大项")
public class WasteStandardizedAssessmentItemController extends BaseController {


    private final WasteStandardizedAssessmentItemService wasteStandardizedAssessmentItemService;

    /**
     * 新增规范化考核大项
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增规范化考核大项")
    @SysLog(title = "新增规范化考核大项", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteStandardizedAssessmentItem wasteStandardizedAssessmentItem) {
        return RestResult.succeed(wasteStandardizedAssessmentItemService.saveWasteStandardizedAssessmentItem(wasteStandardizedAssessmentItem));
    }

    /**
     * 修改规范化考核大项
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改规范化考核大项")
    @SysLog(title = "修改规范化考核大项", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteStandardizedAssessmentItem wasteStandardizedAssessmentItem) {
        return RestResult.succeed(wasteStandardizedAssessmentItemService.updateWasteStandardizedAssessmentItem(wasteStandardizedAssessmentItem));
    }

    /**
     * 删除规范化考核大项
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除规范化考核大项")
    @SysLog(title = "删除规范化考核大项", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentItemService.deleteWasteStandardizedAssessmentItem(id));
    }


    /**
     * 分页查询规范化考核大项
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询规范化考核大项")
    public RestResult<PageT<WasteStandardizedAssessmentItem>> getPage(PageT<WasteStandardizedAssessmentItem> page, WasteStandardizedAssessmentItemDTO wasteStandardizedAssessmentItem) {
        return RestResult.succeed(wasteStandardizedAssessmentItemService.findWasteStandardizedAssessmentItemPage(page, wasteStandardizedAssessmentItem));
    }

    /**
     * 查询规范化考核大项列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询规范化考核大项列表")
    public RestResult<List<WasteStandardizedAssessmentItem>> getList(WasteStandardizedAssessmentItemDTO wasteStandardizedAssessmentItem) {
        return RestResult.succeed(wasteStandardizedAssessmentItemService.findWasteStandardizedAssessmentItemList(wasteStandardizedAssessmentItem));
    }

    /**
     * 获取规范化考核大项详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取规范化考核大项详细信息")
    public RestResult<WasteStandardizedAssessmentItem> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentItemService.findWasteStandardizedAssessmentItemById(id));
    }
}
