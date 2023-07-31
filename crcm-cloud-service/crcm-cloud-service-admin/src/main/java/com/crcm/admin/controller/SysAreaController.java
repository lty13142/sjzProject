package com.crcm.admin.controller;

import com.crcm.admin.model.dto.AreaCoordinatesDTO;
import com.crcm.admin.model.dto.AreaDTO;
import com.crcm.admin.model.entity.SysArea;
import com.crcm.admin.service.SysAreaService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.cloud.start.sso.annation.Inner;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.dto.TreeView;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 区域Controller
 * 
 * @author cb
 * @date 2023-04-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/area")
@Api(tags = "区域")
public class SysAreaController extends BaseController {


    private final SysAreaService sysAreaService;

    /**
     * 新增区域
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增区域")
    @SysLog(title = "新增区域", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SysArea area) {
        String result = sysAreaService.saveArea(area);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    /**
     * 修改区域
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改区域")
    @SysLog(title = "修改区域", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody SysArea area) {
        String result = sysAreaService.updateArea(area);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    /**
     * 删除区域
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除区域")
    @SysLog(title = "删除区域", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Integer> delete(@PathVariable("id") String id) {
        return RestResult.succeed(sysAreaService.deleteArea(id));
    }


    /**
     * 分页查询区域
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询区域")
    public RestResult<PageT<SysArea>> getPage(PageT<SysArea> page, AreaDTO area) {
        return RestResult.succeed(sysAreaService.findAreaPage(page, area));
    }

    /**
     * 查询区域列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询区域列表")
    public RestResult<List<SysArea>> getList(SysArea area) {
        return RestResult.succeed(sysAreaService.findAreaList(area));
    }

    /**
     * 获取区域详细信息
     */
    @Inner
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取区域详细信息")
    public RestResult<SysArea> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(sysAreaService.findAreaById(id));
    }

    /**
     * 通过code获取区域详细信息
     */
    @GetMapping(value = "/getInfoByCode")
    @ApiOperation(value = "获取区域详细信息")
    public RestResult<SysArea> getInfoByCode(String code) {
        return RestResult.succeed(sysAreaService.findAreaByCode(code));
    }

    @Inner
    @GetMapping(value = "/findAreaByType/{type}")
    @ApiOperation(value = "根据type查询区域")
    public RestResult<List<SysArea>> findAreaByType(@PathVariable("type") String type) {
        return RestResult.succeed(sysAreaService.findAreaByType(type));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "查询组织区域树")
    public RestResult<List<TreeView>> getTree(AreaDTO t) {
        return RestResult.succeed(sysAreaService.findTree(t));
    }

    /**
     * 根据名称获取区域详细信息
     */
    @GetMapping(value = "/getInfoByName")
    @ApiOperation(value = "根据名称获取区域详细信息")
    public RestResult<SysArea> getInfoByName(String name) {
        return RestResult.succeed(sysAreaService.getInfoByName(name));
    }

    /**
     * 根据名称修改区域坐标
     */
    @PostMapping("/updateCoordinates")
    @ApiOperation(value = "根据名称修改区域坐标")
    @SysLog(title = "根据名称修改区域坐标", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult add(@RequestBody List<AreaCoordinatesDTO> area) {
        sysAreaService.updateCoordinates(area);
        return RestResult.succeed();
    }


    @Inner
    @GetMapping("/getSubsetByPid")
    @ApiOperation(value = "根据管区ID查询村")
    public RestResult<List<SysArea>> getSubsetByPid(String pid){
        return RestResult.succeed(sysAreaService.getSubsetByPid(pid));
    }

    @Inner
    @GetMapping("/getAreaListById")
    @ApiOperation(value = "根据ID查询当前村，是管区返回所有村，是镇为空")
    public RestResult<List<SysArea>> getAreaListById(@RequestParam("id")String id){
        return RestResult.succeed(sysAreaService.getAreaListById(id));
    }

    @GetMapping(value = "/getByParentCode")
    @ApiOperation(value = "根据pCode查询下属区域")
    public RestResult getByParentCode(String pCode) {
        return RestResult.succeed(sysAreaService.getByParentCode(pCode));
    }

    @GetMapping(value = "/getVillageAsc")
    @ApiOperation(value = "/查询所有村级区域数据，按照管区CODE的ASC顺序来ASC")
    public RestResult getVillageAsc(@RequestParam(value = "regionCode",required = false)String regionCode) {
        return RestResult.succeed(sysAreaService.findVillageAsc(regionCode));
    }

    @GetMapping(value = "/getRegionAsc")
    @ApiOperation(value = "/查询所有管区区域数据，按照管区CODE的ASC顺序")
    public RestResult getRegionAsc(@RequestParam(value = "regionCode",required = false)String regionCode) {
        return RestResult.succeed(sysAreaService.findRegionAsc(regionCode));
    }
}
