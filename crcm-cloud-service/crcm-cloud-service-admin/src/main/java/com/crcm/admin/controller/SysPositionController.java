package com.crcm.admin.controller;


import com.crcm.admin.model.vo.SysPositionVo;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.annation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.crcm.admin.model.entity.SysPosition;
import com.crcm.admin.service.SysPositionService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;

import java.util.List;

/**
 * 岗位Controller
 * 
 * @author cb
 * @date 2023-04-06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/position")
@Api(tags = "岗位")
public class SysPositionController extends BaseController {


    private final SysPositionService sysPositionService;

    /**
     * 新增岗位
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增岗位")
    @SysLog(title = "新增岗位", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SysPosition position) {
        return RestResult.succeed(sysPositionService.savePosition(position));
    }

    /**
     * 修改岗位
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改岗位")
    @SysLog(title = "修改岗位", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody SysPosition position) {
        return RestResult.succeed(sysPositionService.updatePosition(position));
    }

    /**
     * 删除岗位
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除岗位")
    @SysLog(title = "删除岗位", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(sysPositionService.deletePosition(id));
    }


    /**
     * 分页查询岗位
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询岗位")
    public RestResult<PageT<SysPosition>> getPage(PageT<SysPosition> page, SysPosition position) {
        return RestResult.succeed(sysPositionService.findPositionPage(page, position));
    }

    /**
     * 分页查询岗位
     */
    @GetMapping("/pageVo")
    @ApiOperation(value = "分页查询岗位VO")
    public RestResult<PageT<SysPositionVo>> getPageVo(PageT<SysPositionVo> page, SysPositionVo position) {
        return RestResult.succeed(sysPositionService.findPositionPageVo(page, position));
    }


    /**
     * 查询岗位列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询岗位列表")
    public RestResult<List<SysPosition>> getList(SysPosition position) {
        return RestResult.succeed(sysPositionService.findPositionList(position));
    }

    /**
     * 获取岗位详细信息
     */
    @Inner
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取岗位详细信息")
    public RestResult<SysPosition> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(sysPositionService.findPositionById(id));
    }
}
