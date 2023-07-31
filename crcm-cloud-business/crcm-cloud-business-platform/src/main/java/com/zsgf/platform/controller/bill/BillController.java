package com.zsgf.platform.controller.bill;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.bill.Bill;
import com.zsgf.platform.service.bill.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电子联单Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bill")
@Api(tags = "电子联单")
public class BillController extends BaseController {


    private final BillService billService;

    /**
     * 新增电子联单
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增电子联单")
    @SysLog(title = "新增电子联单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody Bill bill) {
        return RestResult.succeed(billService.saveBill(bill));
    }

    /**
     * 修改电子联单
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改电子联单")
    @SysLog(title = "修改电子联单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody Bill bill) {
        return RestResult.succeed(billService.updateBill(bill));
    }

    /**
     * 删除电子联单
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除电子联单")
    @SysLog(title = "删除电子联单", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(billService.deleteBill(id));
    }


    /**
     * 分页查询电子联单
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询电子联单")
    public RestResult<PageT<Bill>> getPage(PageT<Bill> page, Bill bill) {
        return RestResult.succeed(billService.findBillPage(page, bill));
    }

    /**
     * 查询电子联单列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询电子联单列表")
    public RestResult<List<Bill>> getList(Bill bill) {
        return RestResult.succeed(billService.findBillList(bill));
    }

    /**
     * 获取电子联单详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取电子联单详细信息")
    public RestResult<Bill> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(billService.findBillById(id));
    }
}
