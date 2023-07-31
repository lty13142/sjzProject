package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductProcess;
import com.zsgf.platform.service.yearPlan.YearPlanProductProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_16产品生产工艺说明Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanProductProcess")
@Api(tags = "危险废物_管理计划_16产品生产工艺说明")
public class YearPlanProductProcessController extends BaseController {


    private final YearPlanProductProcessService yearPlanProductProcessService;

    /**
     * 新增危险废物_管理计划_16产品生产工艺说明
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_16产品生产工艺说明")
    @SysLog(title = "新增危险废物_管理计划_16产品生产工艺说明", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanProductProcess yearPlanProductProcess) {
        return RestResult.succeed(yearPlanProductProcessService.saveYearPlanProductProcess(yearPlanProductProcess));
    }

    /**
     * 修改危险废物_管理计划_16产品生产工艺说明
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_16产品生产工艺说明")
    @SysLog(title = "修改危险废物_管理计划_16产品生产工艺说明", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanProductProcess yearPlanProductProcess) {
        return RestResult.succeed(yearPlanProductProcessService.updateYearPlanProductProcess(yearPlanProductProcess));
    }

    /**
     * 删除危险废物_管理计划_16产品生产工艺说明
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_16产品生产工艺说明")
    @SysLog(title = "删除危险废物_管理计划_16产品生产工艺说明", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductProcessService.deleteYearPlanProductProcess(id));
    }


    /**
     * 分页查询危险废物_管理计划_16产品生产工艺说明
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_16产品生产工艺说明")
    public RestResult<PageT<YearPlanProductProcess>> getPage(PageT<YearPlanProductProcess> page, YearPlanProductProcess yearPlanProductProcess) {
        return RestResult.succeed(yearPlanProductProcessService.findYearPlanProductProcessPage(page, yearPlanProductProcess));
    }

    /**
     * 查询危险废物_管理计划_16产品生产工艺说明列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_16产品生产工艺说明列表")
    public RestResult<List<YearPlanProductProcess>> getList(YearPlanProductProcess yearPlanProductProcess) {
        return RestResult.succeed(yearPlanProductProcessService.findYearPlanProductProcessList(yearPlanProductProcess));
    }

    /**
     * 获取危险废物_管理计划_16产品生产工艺说明详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_16产品生产工艺说明详细信息")
    public RestResult<YearPlanProductProcess> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductProcessService.findYearPlanProductProcessById(id));
    }
}
