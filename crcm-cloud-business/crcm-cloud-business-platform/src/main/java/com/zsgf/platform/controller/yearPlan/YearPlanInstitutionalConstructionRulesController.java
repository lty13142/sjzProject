package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanInstitutionalConstructionRules;
import com.zsgf.platform.service.yearPlan.YearPlanInstitutionalConstructionRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_12制度建设规章制度情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanInstitutionalConstructionRules")
@Api(tags = "危险废物_管理计划_12制度建设规章制度情况")
public class YearPlanInstitutionalConstructionRulesController extends BaseController {


    private final YearPlanInstitutionalConstructionRulesService yearPlanInstitutionalConstructionRulesService;

    /**
     * 新增危险废物_管理计划_12制度建设规章制度情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_12制度建设规章制度情况")
    @SysLog(title = "新增危险废物_管理计划_12制度建设规章制度情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        return RestResult.succeed(yearPlanInstitutionalConstructionRulesService.saveYearPlanInstitutionalConstructionRules(yearPlanInstitutionalConstructionRules));
    }

    /**
     * 修改危险废物_管理计划_12制度建设规章制度情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_12制度建设规章制度情况")
    @SysLog(title = "修改危险废物_管理计划_12制度建设规章制度情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        return RestResult.succeed(yearPlanInstitutionalConstructionRulesService.updateYearPlanInstitutionalConstructionRules(yearPlanInstitutionalConstructionRules));
    }

    /**
     * 删除危险废物_管理计划_12制度建设规章制度情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_12制度建设规章制度情况")
    @SysLog(title = "删除危险废物_管理计划_12制度建设规章制度情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanInstitutionalConstructionRulesService.deleteYearPlanInstitutionalConstructionRules(id));
    }


    /**
     * 分页查询危险废物_管理计划_12制度建设规章制度情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_12制度建设规章制度情况")
    public RestResult<PageT<YearPlanInstitutionalConstructionRules>> getPage(PageT<YearPlanInstitutionalConstructionRules> page, YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        return RestResult.succeed(yearPlanInstitutionalConstructionRulesService.findYearPlanInstitutionalConstructionRulesPage(page, yearPlanInstitutionalConstructionRules));
    }

    /**
     * 查询危险废物_管理计划_12制度建设规章制度情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_12制度建设规章制度情况列表")
    public RestResult<List<YearPlanInstitutionalConstructionRules>> getList(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        return RestResult.succeed(yearPlanInstitutionalConstructionRulesService.findYearPlanInstitutionalConstructionRulesList(yearPlanInstitutionalConstructionRules));
    }

    /**
     * 获取危险废物_管理计划_12制度建设规章制度情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_12制度建设规章制度情况详细信息")
    public RestResult<YearPlanInstitutionalConstructionRules> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanInstitutionalConstructionRulesService.findYearPlanInstitutionalConstructionRulesById(id));
    }
}
