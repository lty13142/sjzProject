package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.entity.RegionExamine;
import com.sjz.evaluations.service.RegionExamineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 区域考核实体Controller
 *
 * @author zzyt
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/regionExamine")
@Api(tags = "区域考核实体")
public class RegionExamineController extends BaseController {

    @Autowired
    private RegionExamineService regionExamineService;

    /**
     * 新增区域考核实体
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增区域考核实体")
    @SysLog(title = "新增区域考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody RegionExamine RegionExamine) {
        return RestResult.succeed(regionExamineService.saveRegionExamine(RegionExamine));
    }

    /**
     * 修改区域考核实体
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改区域考核实体")
    @SysLog(title = "修改区域考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@Validated @RequestBody RegionExamine RegionExamine) {
        return RestResult.succeed(regionExamineService.updateRegionExamine(RegionExamine));
    }

    /**
     * 删除区域考核实体
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除区域考核实体")
    @SysLog(title = "删除区域考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(regionExamineService.deleteRegionExamine(id));
    }

    /**
     * 获取区域考核实体详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取区域考核实体详细信息")
    public RestResult getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(regionExamineService.findRegionExamineById(id));
    }

    /**
     * 查询区域考核实体列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询区域考核实体列表")
    public RestResult getList(RegionExamine RegionExamine) {
        return RestResult.succeed(regionExamineService.findRegionExamineList(RegionExamine));
    }

    /**
     * 分页查询区域考核实体
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询区域考核实体")
    public RestResult getPage(PageT page, RegionExamine RegionExamine) {
        return RestResult.succeed(regionExamineService.findRegionExaminePage(page, RegionExamine));
    }
}
