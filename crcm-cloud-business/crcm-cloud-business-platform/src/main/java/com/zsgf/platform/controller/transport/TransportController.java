package com.zsgf.platform.controller.transport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.transport.Transport;
import com.zsgf.platform.service.transport.TransportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 废物运输信息Controller
 *
 * @author zzyt
 * @date 2023-02-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/transport")
@Api(tags = "废物运输信息")
public class TransportController extends BaseController {


    private final TransportService transportService;

    /**
     * 新增废物运输信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增废物运输信息")
    @SysLog(title = "新增废物运输信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody Transport transport) {
        return RestResult.succeed(transportService.saveTransport(transport));
    }

    /**
     * 修改废物运输信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改废物运输信息")
    @SysLog(title = "修改废物运输信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody Transport transport) {
        return RestResult.succeed(transportService.updateTransport(transport));
    }

    /**
     * 删除废物运输信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除废物运输信息")
    @SysLog(title = "删除废物运输信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(transportService.deleteTransport(id));
    }


    /**
     * 分页查询废物运输信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询废物运输信息")
    public RestResult<PageT<Transport>> getPage(PageT<Transport> page, Transport transport) {
        return RestResult.succeed(transportService.findTransportPage(page, transport));
    }

    /**
     * 查询废物运输信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询废物运输信息列表")
    public RestResult<List<Transport>> getList(Transport transport) {
        return RestResult.succeed(transportService.findTransportList(transport));
    }

    /**
     * 获取废物运输信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取废物运输信息详细信息")
    public RestResult<Transport> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(transportService.findTransportById(id));
    }
}
