package com.sjz.governance.controller.key;

import java.util.List;

import com.sjz.governance.model.dto.key.KeyPersonManagementDTO;
import com.sjz.governance.model.vo.key.KeyPersonManagementVO;
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
import com.sjz.governance.model.entity.key.KeyPersonManagement;
import com.sjz.governance.service.key.KeyPersonManagementService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;

/**
 * 综合治理_重点人员管理Controller
 * 
 * @author pengl
 * @date 2023-04-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/keyPersonManagement")
@Api(tags = "综合治理_重点人员管理")
public class KeyPersonManagementController extends BaseController {


    private final KeyPersonManagementService keyPersonManagementService;

    /**
     * 综合治理_创建人脸数据库
     */
    @PostMapping("/createFaceDatabase")
    @ApiOperation(value = "综合治理_创建人脸数据库")
    @SysLog(title = "综合治理_创建人脸数据库", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<String> createFaceDatabase(@Validated @RequestBody KeyPersonManagementDTO dto) {
        return RestResult.succeed(keyPersonManagementService.createFaceDatabase(dto));
    }

    /**
     * 新增综合治理_重点人员管理
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增综合治理_重点人员管理")
    @SysLog(title = "新增综合治理_重点人员管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody KeyPersonManagementDTO dto) {
        return RestResult.succeed(keyPersonManagementService.saveKeyPersonManagement(dto));
    }

    /**
     * 修改综合治理_重点人员管理
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改综合治理_重点人员管理")
    @SysLog(title = "修改综合治理_重点人员管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody KeyPersonManagementDTO dto) {
        return RestResult.succeed(keyPersonManagementService.updateKeyPersonManagement(dto));
    }

    /**
     * 删除综合治理_重点人员管理
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除综合治理_重点人员管理")
    @SysLog(title = "删除综合治理_重点人员管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") Integer id) {
        return RestResult.succeed(keyPersonManagementService.deleteKeyPersonManagement(id));
    }


    /**
     * 分页查询综合治理_重点人员管理
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询综合治理_重点人员管理")
    public RestResult<PageT<KeyPersonManagementVO>> getPage(PageT<KeyPersonManagementVO> page, KeyPersonManagementDTO dto) {
        return RestResult.succeed(keyPersonManagementService.findKeyPersonManagementPage(page, dto));
    }

    /**
     * 查询综合治理_重点人员管理列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询综合治理_重点人员管理列表")
    public RestResult<List<KeyPersonManagement>> getList(KeyPersonManagement keyPersonManagement) {
        return RestResult.succeed(keyPersonManagementService.findKeyPersonManagementList(keyPersonManagement));
    }

    /**
     * 获取综合治理_重点人员管理详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取综合治理_重点人员管理详细信息")
    public RestResult<KeyPersonManagement> getInfo(@PathVariable("id") Integer id) {
        return RestResult.succeed(keyPersonManagementService.findKeyPersonManagementById(id));
    }
}
