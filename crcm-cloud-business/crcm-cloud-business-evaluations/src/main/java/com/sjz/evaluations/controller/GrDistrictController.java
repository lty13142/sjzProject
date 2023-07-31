package com.sjz.evaluations.controller;

import java.util.List;

import com.sjz.evaluations.model.entity.GrDistrict;
import com.sjz.evaluations.service.GrDistrictService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;

/**
 * 管区Controller
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/district")
@Api(tags = "管区")
public class GrDistrictController extends BaseController {


    private final GrDistrictService grDistrictService;

    /**
     * 新增管区
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增管区")
    @SysLog(title = "新增管区", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody GrDistrict grDistrict) {
        return RestResult.succeed(grDistrictService.saveGrDistrict(grDistrict));
    }

    /**
     * 修改管区
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改管区")
    @SysLog(title = "修改管区", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody GrDistrict grDistrict) {
        return RestResult.succeed(grDistrictService.updateGrDistrict(grDistrict));
    }

    /**
     * 删除管区
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除管区")
    @SysLog(title = "删除管区", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(grDistrictService.deleteGrDistrict(id));
    }


    /**
     * 分页查询管区
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询管区")
    public RestResult<PageT<GrDistrict>> getPage(PageT<GrDistrict> page, GrDistrict grDistrict) {
        return RestResult.succeed(grDistrictService.findGrDistrictPage(page, grDistrict));
    }

    /**
     * 查询管区列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询管区列表")
    public RestResult<List<GrDistrict>> getList(GrDistrict grDistrict) {
        return RestResult.succeed(grDistrictService.findGrDistrictList(grDistrict));
    }

    /**
     * 获取管区详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取管区详细信息")
    public RestResult<GrDistrict> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(grDistrictService.findGrDistrictById(id));
    }
}
