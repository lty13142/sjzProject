package com.crcm.admin.controller;

import com.crcm.admin.model.dto.SysDictQueryDTO;
import com.crcm.admin.model.entity.SysDict;
import com.crcm.admin.service.DictService;
import com.crcm.admin.utils.UtilDic;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(tags = "字典内容接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
public class DictController extends BaseController {

    private final DictService dictService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增字典", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Object> save(@Valid @RequestBody SysDict t) {
        return RestResult.succeed(dictService.saveDict(t));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改字典", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Object> update(@Valid @RequestBody SysDict t) {
        dictService.updateDict(t);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除字典", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Object> delete(@RequestBody SysDict t) {
        dictService.deleteById(t.getId());
        return RestResult.succeed();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult<List<SysDict>> getList(SysDictQueryDTO t) {
        return RestResult.succeed(dictService.findList(t));
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult<SysDict> getById(String id) {
        return RestResult.succeed(dictService.findById(id));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult<PageT<SysDict>> getPage(SysDictQueryDTO t, PageT<SysDict> page) {
        return RestResult.succeed(dictService.findPage(page, t));
    }

    @GetMapping("/getDicByCode")
    @ApiOperation(value = "根据字典代码查询字典内容")
    public RestResult<List<SysDict>> getDicByCode(SysDictQueryDTO t) {
        return RestResult.succeed(dictService.findByDicCode(t));
    }

    @GetMapping("/getMaxSort")
    @ApiOperation(value = "查询对应字典最大排序值")
    public RestResult<Object> getMaxSort(String pid) {
        return RestResult.succeed(dictService.findMaxSort(pid));
    }


    @ApiOperation("根据字典代码获取字典数据")
    @GetMapping("/getByCode/{code}")
    public RestResult<List<Object>> getByCode(@PathVariable("code") String code) {
        return RestResult.succeed(UtilDic.getDicByCode(code));
    }


}

