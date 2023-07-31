package com.zsgf.platform.controller.company;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.company.WasteInfoQueryDTO;
import com.zsgf.platform.model.entity.company.WasteInfo;
import com.zsgf.platform.service.company.WasteInfoService;
import com.zsgf.platform.vo.company.WasteInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业废物信息Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteInfo")
@Api(tags = "企业废物信息")
public class WasteInfoController extends BaseController {


    private final WasteInfoService wasteInfoService;

    /**
     * 新增企业废物信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增企业废物信息")
    @SysLog(title = "新增企业废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteInfo wasteInfo) {
        return RestResult.succeed(wasteInfoService.saveWasteInfo(wasteInfo));
    }

    /**
     * 修改企业废物信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改企业废物信息")
    @SysLog(title = "修改企业废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteInfo wasteInfo) {
        return RestResult.succeed(wasteInfoService.updateWasteInfo(wasteInfo));
    }

    /**
     * 删除企业废物信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除企业废物信息")
    @SysLog(title = "删除企业废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteInfoService.deleteWasteInfo(id));
    }


    /**
     * 分页查询企业废物信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询企业废物信息")
    public RestResult<PageT<WasteInfoVo>> getPage(PageT<WasteInfoVo> page, WasteInfoQueryDTO wasteInfo) {
        return RestResult.succeed(wasteInfoService.findWasteInfoPage(page, wasteInfo));
    }

    /**
     * 查询企业废物信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询企业废物信息列表")
    public RestResult<List<WasteInfo>> getList(WasteInfoQueryDTO wasteInfo) {
        return RestResult.succeed(wasteInfoService.findWasteInfoList(wasteInfo));
    }

    /**
     * 获取企业废物信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取企业废物信息详细信息")
    public RestResult<WasteInfo> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteInfoService.findWasteInfoById(id));
    }
}
