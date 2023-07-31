package com.zsgf.platform.controller.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDetailDTO;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItemDetail;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentItemDetailService;
import com.zsgf.platform.vo.standardize.WasteStandardizedAssessmentItemDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规范化考核小项Controller
 *
 * @author gzl
 * @date 2023-03-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteStandardizedAssessmentItemDetail")
@Api(tags = "规范化考核小项")
public class WasteStandardizedAssessmentItemDetailController extends BaseController {


    private final WasteStandardizedAssessmentItemDetailService wasteStandardizedAssessmentItemDetailService;

    /**
     * 新增规范化考核小项
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增规范化考核小项")
    @SysLog(title = "新增规范化考核小项", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteStandardizedAssessmentItemDetail wasteStandardizedAssessmentItemDetail) {
        return RestResult.succeed(wasteStandardizedAssessmentItemDetailService.saveWasteStandardizedAssessmentItemDetail(wasteStandardizedAssessmentItemDetail));
    }

    /**
     * 修改规范化考核小项
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改规范化考核小项")
    @SysLog(title = "修改规范化考核小项", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteStandardizedAssessmentItemDetail wasteStandardizedAssessmentItemDetail) {
        return RestResult.succeed(wasteStandardizedAssessmentItemDetailService.updateWasteStandardizedAssessmentItemDetail(wasteStandardizedAssessmentItemDetail));
    }

    /**
     * 删除规范化考核小项
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除规范化考核小项")
    @SysLog(title = "删除规范化考核小项", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentItemDetailService.deleteWasteStandardizedAssessmentItemDetail(id));
    }


    /**
     * 分页查询规范化考核小项
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询规范化考核小项")
    public RestResult<PageT<WasteStandardizedAssessmentItemDetailVo>> getPage(PageT<WasteStandardizedAssessmentItemDetailVo> page, WasteStandardizedAssessmentItemDetailDTO queryDTO) {
        return RestResult.succeed(wasteStandardizedAssessmentItemDetailService.findWasteStandardizedAssessmentItemDetailPage(page, queryDTO));
    }

    /**
     * 查询规范化考核小项列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询规范化考核小项列表")
    public RestResult<List<WasteStandardizedAssessmentItemDetailVo>> getList(WasteStandardizedAssessmentItemDetailDTO queryDTO) {
        return RestResult.succeed(wasteStandardizedAssessmentItemDetailService.findWasteStandardizedAssessmentItemDetailList(queryDTO));
    }

    /**
     * 获取规范化考核小项详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取规范化考核小项详细信息")
    public RestResult<WasteStandardizedAssessmentItemDetail> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentItemDetailService.findWasteStandardizedAssessmentItemDetailById(id));
    }
}
