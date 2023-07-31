package com.sjz.education.controller;

import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.entity.EduPersonnelManagement;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.service.EduPersonnelManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 人员controller
 * @author: sssccc
 * @create: 2023-04-06 11:32
 **/
@RestController
@RequestMapping("/personnel")
@Api(tags = "人员管理")
public class EduPersonnelController extends BaseController {

    @Autowired
    private EduPersonnelManagementService personnelService;

    /**
     * 新增人员
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增人员")
    @SysLog(title = "新增人员", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduPersonnelManagement t) {
        return RestResult.succeed(personnelService.add(t));
    }

    /**
     * 编辑人员
     */
    @PostMapping("/edit")
    @ApiOperation(value = "编辑人员")
    @SysLog(title = "编辑人员", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduPersonnelManagement t) {
        return RestResult.succeed(personnelService.edit(t));
    }

    /**
     * 删除人员
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除人员")
    @SysLog(title = "删除人员", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(personnelService.deleteById(id));
    }

    /**
     * 获取我的信息
     */
    @GetMapping("/getInfo")
    @ApiOperation(value = "获取我的信息")
    public RestResult getInfo() {
        return RestResult.succeed(personnelService.findByUser());
    }

    /**
     * 根据id查询
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查询")
    public RestResult getById(@PathVariable("id") String id) {
        return RestResult.succeed(personnelService.findById(id));
    }

    /**
     * 获取人员列表
     */
    @GetMapping("getList")
    @ApiOperation(value = "获取人员列表")
    public RestResult getList() {
        return RestResult.succeed(personnelService.getList());
    }

    /**
     * 积分排名，不按村做隔离
     */
    @GetMapping("/ranking")
    @ApiOperation(value = "积分排名")
    public RestResult ranking() {
        return RestResult.succeed(personnelService.ranking());
    }

    /**
     * 积分排名，按村做隔离
     */
    @GetMapping("/rankingByVillage")
    @ApiOperation(value = "积分排名")
    public RestResult rankingByVillage() {
        return RestResult.succeed(personnelService.rankingByVillage());
    }


    /**
     * 我的积分排名
     */
    @GetMapping("/myRanking")
    @ApiOperation(value = "我的积分排名")
    public RestResult myRanking() {
        return RestResult.succeed(personnelService.myRanking());
    }

    /**
     * 我的积分获取支出汇总
     */
    @GetMapping("/myRecord")
    @ApiOperation(value = "我的积分获取支出汇总")
    public RestResult myRecord(BaseQueryVO t) {
        return RestResult.succeed(personnelService.myRecord(t));
    }

}
