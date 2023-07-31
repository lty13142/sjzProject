package com.zsgf.platform.controller.waste;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.waste.WasteBaseInfoDTO;
import com.zsgf.platform.model.entity.waste.WasteBaseInfo;
import com.zsgf.platform.service.waste.WasteBaseInfoService;
import com.zsgf.platform.vo.WasteBaseInfoTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危废名录Controller
 *
 * @author gzl
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteBaseInfo")
@Api(tags = "危废名录")
public class WasteBaseInfoController extends BaseController {


    private final WasteBaseInfoService wasteBaseInfoService;

    /**
     * 新增危废名录
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危废名录")
    @SysLog(title = "新增危废名录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteBaseInfo wasteBaseInfo) {
        return RestResult.succeed(wasteBaseInfoService.saveWasteBaseInfo(wasteBaseInfo));
    }

    /**
     * 修改危废名录
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危废名录")
    @SysLog(title = "修改危废名录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteBaseInfo wasteBaseInfo) {
        return RestResult.succeed(wasteBaseInfoService.updateWasteBaseInfo(wasteBaseInfo));
    }

    /**
     * 删除危废名录
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危废名录")
    @SysLog(title = "删除危废名录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteBaseInfoService.deleteWasteBaseInfo(id));
    }


    /**
     * 分页查询危废名录
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危废名录")
    public RestResult<PageT<WasteBaseInfo>> getPage(PageT<WasteBaseInfo> page, WasteBaseInfoDTO wasteBaseInfo) {
        return RestResult.succeed(wasteBaseInfoService.findWasteBaseInfoPage(page, wasteBaseInfo));
    }

    /**
     * 查询危废名录列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危废名录列表")
    public RestResult<List<WasteBaseInfo>> getList(WasteBaseInfoDTO wasteBaseInfo) {
        return RestResult.succeed(wasteBaseInfoService.findWasteBaseInfoList(wasteBaseInfo));
    }

    /**
     * 获取危废名录详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危废名录详细信息")
    public RestResult<WasteBaseInfo> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteBaseInfoService.findWasteBaseInfoById(id));
    }

    /**
     * 查询危废名录列表
     */
    @GetMapping("/wasteTree")
    @ApiOperation(value = "查询危废名录树")
    public RestResult<List<WasteBaseInfoTreeVo>> getWasteTree(WasteBaseInfoDTO wasteBaseInfo) {
        return RestResult.succeed(wasteBaseInfoService.findWasteTree(wasteBaseInfo));
    }
}
