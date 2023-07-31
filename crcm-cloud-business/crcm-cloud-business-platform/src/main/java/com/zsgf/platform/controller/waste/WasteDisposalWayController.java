package com.zsgf.platform.controller.waste;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.waste.WasteDisposalWayDTO;
import com.zsgf.platform.model.entity.waste.WasteDisposalWay;
import com.zsgf.platform.service.waste.WasteDisposalWayService;
import com.zsgf.platform.vo.waste.WasteDisposalWayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 废物处置方式Controller
 *
 * @author gzl
 * @date 2023-03-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteDisposalWay")
@Api(tags = "废物处置方式")
public class WasteDisposalWayController extends BaseController {


    private final WasteDisposalWayService wasteDisposalWayService;

    /**
     * 新增废物处置方式
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增废物处置方式")
    @SysLog(title = "新增废物处置方式", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteDisposalWay wasteDisposalWay) {
        return RestResult.succeed(wasteDisposalWayService.saveWasteDisposalWay(wasteDisposalWay));
    }

    /**
     * 修改废物处置方式
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改废物处置方式")
    @SysLog(title = "修改废物处置方式", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteDisposalWay wasteDisposalWay) {
        return RestResult.succeed(wasteDisposalWayService.updateWasteDisposalWay(wasteDisposalWay));
    }

    /**
     * 删除废物处置方式
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除废物处置方式")
    @SysLog(title = "删除废物处置方式", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteDisposalWayService.deleteWasteDisposalWay(id));
    }


    /**
     * 分页查询废物处置方式
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询废物处置方式")
    public RestResult<PageT<WasteDisposalWayVo>> getPage(PageT<WasteDisposalWayVo> page, WasteDisposalWayDTO wasteDisposalWay) {
        return RestResult.succeed(wasteDisposalWayService.findWasteDisposalWayPage(page, wasteDisposalWay));
    }

    /**
     * 查询废物处置方式列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询废物处置方式列表")
    public RestResult<List<WasteDisposalWay>> getList(WasteDisposalWayDTO wasteDisposalWay) {
        return RestResult.succeed(wasteDisposalWayService.findWasteDisposalWayList(wasteDisposalWay));
    }

    /**
     * 获取废物处置方式详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取废物处置方式详细信息")
    public RestResult<WasteDisposalWay> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteDisposalWayService.findWasteDisposalWayById(id));
    }
}
