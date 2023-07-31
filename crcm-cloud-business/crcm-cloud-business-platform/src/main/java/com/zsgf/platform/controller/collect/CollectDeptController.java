package com.zsgf.platform.controller.collect;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.collect.CollectDept;
import com.zsgf.platform.service.collect.CollectDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业收集部门信息Controller
 *
 * @author gzl
 * @date 2023-03-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/collectDept")
@Api(tags = "企业收集部门信息")
public class CollectDeptController extends BaseController {


    private final CollectDeptService collectDeptService;

    /**
     * 新增企业收集部门信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增企业收集部门信息")
    @SysLog(title = "新增企业收集部门信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody CollectDept collectDept) {
        return RestResult.succeed(collectDeptService.saveCollectDept(collectDept));
    }

    /**
     * 修改企业收集部门信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改企业收集部门信息")
    @SysLog(title = "修改企业收集部门信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody CollectDept collectDept) {
        return RestResult.succeed(collectDeptService.updateCollectDept(collectDept));
    }

    /**
     * 删除企业收集部门信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除企业收集部门信息")
    @SysLog(title = "删除企业收集部门信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(collectDeptService.deleteCollectDept(id));
    }


    /**
     * 分页查询企业收集部门信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询企业收集部门信息")
    public RestResult<PageT<CollectDept>> getPage(PageT<CollectDept> page, CollectDept collectDept) {
        return RestResult.succeed(collectDeptService.findCollectDeptPage(page, collectDept));
    }

    /**
     * 查询企业收集部门列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询企业收集部门信息列表")
    public RestResult<List<CollectDept>> getList(CollectDept collectDept) {
        return RestResult.succeed(collectDeptService.findCollectDeptList(collectDept));
    }

    /**
     * 获取企业收集部门详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取企业收集部门信息详细信息")
    public RestResult<CollectDept> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(collectDeptService.findCollectDeptById(id));
    }
}
