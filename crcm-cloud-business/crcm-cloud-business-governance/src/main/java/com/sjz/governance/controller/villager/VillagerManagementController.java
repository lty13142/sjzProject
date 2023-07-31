package com.sjz.governance.controller.villager;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.echarts.EChartsIntegerBar;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.crcm.core.vo.echarts.EChartsIntegerPieTotal;
import com.sjz.governance.model.dto.villager.VillagerManagementDTO;
import com.sjz.governance.model.entity.villager.VillagerManagement;
import com.sjz.governance.model.vo.villager.VillagerManagementVo;
import com.sjz.governance.model.vo.villager.VillagerStaticsVo;
import com.sjz.governance.service.villager.VillagerManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 村民信息管理Controller
 *
 * @author guozhilin
 * @date 2023-04-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/villagerManagement")
@Api(tags = "村民信息管理")
public class VillagerManagementController extends BaseController {


    private final VillagerManagementService villagerManagementService;

    /**
     * 新增村民信息管理
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增村民信息管理")
    @SysLog(title = "新增村民信息管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody VillagerManagement villagerManagement) {
        return RestResult.succeed(villagerManagementService.saveVillagerManagement(villagerManagement));
    }

    /**
     * 修改村民信息管理
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改村民信息管理")
    @SysLog(title = "修改村民信息管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody VillagerManagement villagerManagement) {
        return RestResult.succeed(villagerManagementService.updateVillagerManagement(villagerManagement));
    }

    /**
     * 删除村民信息管理
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除村民信息管理")
    @SysLog(title = "删除村民信息管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(villagerManagementService.deleteVillagerManagement(id));
    }


    /**
     * 分页查询村民信息管理
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询村民信息管理")
    public RestResult<PageT<VillagerManagementVo>> getPage(PageT<VillagerManagementVo> page, VillagerManagementDTO villagerManagement) {
        return RestResult.succeed(villagerManagementService.findVillagerManagementPage(page, villagerManagement));
    }

    /**
     * 查询村民信息管理列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询村民信息管理列表")
    public RestResult<List<VillagerManagement>> getList(VillagerManagementDTO villagerManagement) {
        return RestResult.succeed(villagerManagementService.findVillagerManagementList(villagerManagement));
    }

    /**
     * 获取村民信息管理详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取村民信息管理详细信息")
    public RestResult<VillagerManagement> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(villagerManagementService.findVillagerManagementById(id));
    }

    /**
     * 导出村民信息
     */
    @GetMapping("/exportVillager")
    @ApiOperation(value = "导出村民信息")
    @SysLog(title = "导出村民信息", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.EXPORT)
    public void exportVillager(VillagerManagementDTO villagerManagement) {
        villagerManagementService.exportVillager(villagerManagement, request, response);
    }

    /**
     * 导出村民信息
     */
    @GetMapping("/exportModel")
    @ApiOperation(value = "导出模板下载")
    public void exportModel() {
        villagerManagementService.exportModel(request, response);
    }

    /**
     * 导入村民信息
     */
    @PostMapping("/importVillager")
    @ApiOperation(value = "导入村民信息")
    @SysLog(title = "导入村民信息", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.IMPORT)
    public RestResult<String> importVillager(@RequestParam MultipartFile file) {
        villagerManagementService.importVillager(file);
        return RestResult.succeed();
    }

    /**
     * 根据区域统计各类人员数量
     */
    @GetMapping("/getVillagerStatisticsByArea")
    @ApiOperation(value = "根据区域统计各类人员数量")
    public RestResult<List<EChartsIntegerPie>> getVillagerStatisticsByArea(VillagerManagementDTO dto) {
        return RestResult.succeed(villagerManagementService.getVillagerStatisticsByArea(dto));
    }

    /**
     * 根据类型统计人员数量
     */
    @GetMapping("/getVillagerStatisticsByType")
    @ApiOperation(value = "根据类型统计人员数量")
    public RestResult<EChartsIntegerPieTotal> getVillagerStatisticsByType() {
        return RestResult.succeed(villagerManagementService.getVillagerStatisticsByType());
    }

    /**
     * 村民信息统计-全镇村民综合人员统计
     */
    @GetMapping("/getStatisticsByVillageAndType")
    @ApiOperation(value = "村民信息统计-全镇村民综合人员统计")
    public RestResult<EChartsIntegerBar> getStatisticsByVillageAndType(String type) {
        return RestResult.succeed(villagerManagementService.getStatisticsByVillageAndType(type));
    }

    /**
     * 大屏村民信息统计-按人员结构统计
     */
    @GetMapping("/getStatisticsByStructure")
    @ApiOperation(value = "大屏村民信息统计-按人员结构统计")
    public RestResult<VillagerStaticsVo> getStatisticsByStructure() {
        return RestResult.succeed(villagerManagementService.getStatisticsByStructure());
    }


    /**
     * 批量删除村民信息管理
     */
    @PostMapping("/deleteBatch")
    @ApiOperation(value = "删除村民信息管理")
    @SysLog(title = "删除村民信息管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> deleteBatch(@RequestBody List<String> ids) {
        villagerManagementService.deleteBatch(ids);
        return RestResult.succeed();
    }


}
