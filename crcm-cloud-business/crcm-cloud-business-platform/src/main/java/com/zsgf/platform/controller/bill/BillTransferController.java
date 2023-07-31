package com.zsgf.platform.controller.bill;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.bill.BillTransfer;
import com.zsgf.platform.service.bill.BillTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电子联单运输信息Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/billTransfer")
@Api(tags = "电子联单运输信息")
public class BillTransferController extends BaseController {


    private final BillTransferService billTransferService;

    /**
     * 新增电子联单运输信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增电子联单运输信息")
    @SysLog(title = "新增电子联单运输信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BillTransfer billTransfer) {
        return RestResult.succeed(billTransferService.saveBillTransfer(billTransfer));
    }

    /**
     * 修改电子联单运输信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改电子联单运输信息")
    @SysLog(title = "修改电子联单运输信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BillTransfer billTransfer) {
        return RestResult.succeed(billTransferService.updateBillTransfer(billTransfer));
    }

    /**
     * 删除电子联单运输信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除电子联单运输信息")
    @SysLog(title = "删除电子联单运输信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(billTransferService.deleteBillTransfer(id));
    }


    /**
     * 分页查询电子联单运输信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询电子联单运输信息")
    public RestResult<PageT<BillTransfer>> getPage(PageT<BillTransfer> page, BillTransfer billTransfer) {
        return RestResult.succeed(billTransferService.findBillTransferPage(page, billTransfer));
    }

    /**
     * 查询电子联单运输信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询电子联单运输信息列表")
    public RestResult<List<BillTransfer>> getList(BillTransfer billTransfer) {
        return RestResult.succeed(billTransferService.findBillTransferList(billTransfer));
    }

    /**
     * 获取电子联单运输信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取电子联单运输信息详细信息")
    public RestResult<BillTransfer> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(billTransferService.findBillTransferById(id));
    }
}
