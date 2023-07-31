package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.dto.VillageExamineSaveDTO;
import com.sjz.evaluations.model.entity.VillageExamine;
import com.sjz.evaluations.model.vo.AssessmentStatics;
import com.sjz.evaluations.service.VillageExamineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 村级考核实体Controller
 *
 * @author zzyt
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/villageExamine")
@Api(tags = "村级考核实体")
public class VillageExamineController extends BaseController {

    @Autowired
    private VillageExamineService villageExamineService;

    /**
     * 新增村级考核实体
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增村级考核实体")
    @SysLog(title = "新增村级考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody VillageExamine villageExamine) {
        return RestResult.succeed(villageExamineService.saveVillageExamine(villageExamine));
    }

    /**
     * 新增村级考核实体
     */
    @PostMapping("/saveBatch")
    @ApiOperation(value = "批量新增村级考核实体")
    @SysLog(title = "批量新增村级考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody VillageExamineSaveDTO villageExamineSaveDTO) {
        return RestResult.succeed(villageExamineService.saveVillageExamineBatch(villageExamineSaveDTO));
    }

    /**
     * 修改村级考核实体
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改村级考核实体")
    @SysLog(title = "修改村级考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@Validated @RequestBody VillageExamineSaveDTO villageExamineSaveDTO ) {
        return RestResult.succeed(villageExamineService.updateVillageExamine(villageExamineSaveDTO));
    }

    /**
     * 删除村级考核实体
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除村级考核实体")
    @SysLog(title = "删除村级考核实体", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(villageExamineService.deleteVillageExamine(id));
    }

    /**
     * 获取村级考核实体详细信息
     */
    @GetMapping(value = "/getById")
    @ApiOperation(value = "获取村级考核实体详细信息")
    public RestResult getInfo(String id) {
        return RestResult.succeed(villageExamineService.findVillageExamineById(id));
    }


    /**
     * 查询村级考核实体列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询村级考核实体列表")
    public RestResult getList(VillageExamine villageExamine) {
        return RestResult.succeed(villageExamineService.findVillageExamineList(villageExamine));
    }

    /**
     * 分页查询村级考核实体
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询村级考核实体")
    public RestResult getPage(PageT page, VillageExamine villageExamine) {
        return RestResult.succeed(villageExamineService.findVillageExaminePage(page, villageExamine));
    }

    @GetMapping(value = "/getVillageByRegionId")
    @ApiOperation(value = "根据指标id和管区考核id查询村级考核")
    public RestResult getVillageByRegionId(String indicatorsId, String regionId) {
        return RestResult.succeed(villageExamineService.findVillageByRegionId(indicatorsId,regionId));
    }

    @PostMapping(value = "/submitRegionScore")
    @ApiOperation(value = "管区提交对村级的核实")
    @SysLog(title = "管区提交对村级的核实", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult submitRegionScore(@RequestBody VillageExamine villageExamine) {
        return RestResult.succeed(villageExamineService.submitRegionScore(villageExamine));
    }

    @PostMapping(value = "/submitTownScore")
    @ApiOperation(value = "镇级提交对管区的核实")
    @SysLog(title = "镇级提交对管区的核实", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult submitTownScore(@RequestBody VillageExamine villageExamine) {
        return RestResult.succeed(villageExamineService.submitTownScore(villageExamine));
    }

    @GetMapping(value = "/assessmentStatics")
    @ApiOperation(value = "大屏-考核完成情况排名")
    public RestResult<List<AssessmentStatics>> assessmentStatics(){
        return RestResult.succeed(villageExamineService.assessmentStatics());
    }


}
