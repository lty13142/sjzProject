package com.sjz.governance.controller.key;

import java.util.List;

import com.sjz.governance.model.dto.key.KeyVehicleManagementDTO;
import com.sjz.governance.model.vo.key.KeyVehicleManagementVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.sjz.governance.model.entity.key.KeyVehicleManagement;
import com.sjz.governance.service.key.KeyVehicleManagementService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;

/**
 * 综合治理_重点车辆管理Controller
 * 
 * @author pengl
 * @date 2023-04-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/keyVehicleManagement")
@Api(tags = "综合治理_重点车辆管理")
public class KeyVehicleManagementController extends BaseController {


    private final KeyVehicleManagementService keyVehicleManagementService;

    /**
     * 新增综合治理_重点车辆管理
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增综合治理_重点车辆管理")
    @SysLog(title = "新增综合治理_重点车辆管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody KeyVehicleManagement keyVehicleManagement) {
        return RestResult.succeed(keyVehicleManagementService.saveKeyVehicleManagement(keyVehicleManagement));
    }

    /**
     * 修改综合治理_重点车辆管理
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改综合治理_重点车辆管理")
    @SysLog(title = "修改综合治理_重点车辆管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody KeyVehicleManagement keyVehicleManagement) {
        return RestResult.succeed(keyVehicleManagementService.updateKeyVehicleManagement(keyVehicleManagement));
    }

    /**
     * 删除综合治理_重点车辆管理
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除综合治理_重点车辆管理")
    @SysLog(title = "删除综合治理_重点车辆管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") Integer id) {
        return RestResult.succeed(keyVehicleManagementService.deleteKeyVehicleManagement(id));
    }


    /**
     * 分页查询综合治理_重点车辆管理
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询综合治理_重点车辆管理")
    public RestResult<PageT<KeyVehicleManagementVO>> getPage(PageT<KeyVehicleManagementVO> page, KeyVehicleManagementDTO dto) {
        return RestResult.succeed(keyVehicleManagementService.findKeyVehicleManagementPage(page, dto));
    }

    /**
     * 查询综合治理_重点车辆管理列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询综合治理_重点车辆管理列表")
    public RestResult<List<KeyVehicleManagement>> getList(KeyVehicleManagement keyVehicleManagement) {
        return RestResult.succeed(keyVehicleManagementService.findKeyVehicleManagementList(keyVehicleManagement));
    }

    /**
     * 获取综合治理_重点车辆管理详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取综合治理_重点车辆管理详细信息")
    public RestResult<KeyVehicleManagement> getInfo(@PathVariable("id") Integer id) {
        return RestResult.succeed(keyVehicleManagementService.findKeyVehicleManagementById(id));
    }
}
