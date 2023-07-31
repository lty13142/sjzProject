package com.zsgf.platform.controller.company;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.company.HazardousChemicals;
import com.zsgf.platform.service.company.HazardousChemicalsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险化学品备案信息Controller
 *
 * @author gzl
 * @date 2023-03-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/hazardousChemicals")
@Api(tags = "危险化学品备案信息")
public class HazardousChemicalsController extends BaseController {


    private final HazardousChemicalsService hazardousChemicalsService;

    /**
     * 新增危险化学品备案信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险化学品备案信息")
    @SysLog(title = "新增危险化学品备案信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody HazardousChemicals hazardousChemicals) {
        return RestResult.succeed(hazardousChemicalsService.saveHazardousChemicals(hazardousChemicals));
    }

    /**
     * 修改危险化学品备案信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险化学品备案信息")
    @SysLog(title = "修改危险化学品备案信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody HazardousChemicals hazardousChemicals) {
        return RestResult.succeed(hazardousChemicalsService.updateHazardousChemicals(hazardousChemicals));
    }

    /**
     * 删除危险化学品备案信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险化学品备案信息")
    @SysLog(title = "删除危险化学品备案信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(hazardousChemicalsService.deleteHazardousChemicals(id));
    }


    /**
     * 分页查询危险化学品备案信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险化学品备案信息")
    public RestResult<PageT<HazardousChemicals>> getPage(PageT<HazardousChemicals> page, HazardousChemicals hazardousChemicals) {
        return RestResult.succeed(hazardousChemicalsService.findHazardousChemicalsPage(page, hazardousChemicals));
    }

    /**
     * 查询危险化学品备案信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险化学品备案信息列表")
    public RestResult<List<HazardousChemicals>> getList(HazardousChemicals hazardousChemicals) {
        return RestResult.succeed(hazardousChemicalsService.findHazardousChemicalsList(hazardousChemicals));
    }

    /**
     * 获取危险化学品备案信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险化学品备案信息详细信息")
    public RestResult<HazardousChemicals> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(hazardousChemicalsService.findHazardousChemicalsById(id));
    }
}
