package com.zsgf.platform.controller.collect;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.collect.CollectRecord;
import com.zsgf.platform.service.collect.CollectRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业收集台账Controller
 *
 * @author gzl
 * @date 2023-03-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/collectRecord")
@Api(tags = "企业收集台账")
public class CollectRecordController extends BaseController {


    private final CollectRecordService collectRecordService;

    /**
     * 新增企业收集台账
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增企业收集台账")
    @SysLog(title = "新增企业收集台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody CollectRecord collectRecord) {
        return RestResult.succeed(collectRecordService.saveCollectRecord(collectRecord));
    }

    /**
     * 修改企业收集台账
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改企业收集台账")
    @SysLog(title = "修改企业收集台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody CollectRecord collectRecord) {
        return RestResult.succeed(collectRecordService.updateCollectRecord(collectRecord));
    }

    /**
     * 删除企业收集台账
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除企业收集台账")
    @SysLog(title = "删除企业收集台账", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(collectRecordService.deleteCollectRecord(id));
    }


    /**
     * 分页查询企业收集台账
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询企业收集台账")
    public RestResult<PageT<CollectRecord>> getPage(PageT<CollectRecord> page, CollectRecord collectRecord) {
        return RestResult.succeed(collectRecordService.findCollectRecordPage(page, collectRecord));
    }

    /**
     * 查询企业收集台账列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询企业收集台账列表")
    public RestResult<List<CollectRecord>> getList(CollectRecord collectRecord) {
        return RestResult.succeed(collectRecordService.findCollectRecordList(collectRecord));
    }

    /**
     * 获取企业收集台账详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取企业收集台账详细信息")
    public RestResult<CollectRecord> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(collectRecordService.findCollectRecordById(id));
    }
}
