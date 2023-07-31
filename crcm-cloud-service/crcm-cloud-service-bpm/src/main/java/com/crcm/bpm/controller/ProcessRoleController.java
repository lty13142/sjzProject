package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.entity.ProcessRoleDo;
import com.crcm.bpm.domain.vo.ProcessRoleVO;
import com.crcm.bpm.service.ProcessRoleService;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zwj
 * @date 2020-10-28
 */
@Api(tags = {"流程角色关联表接口"})
@RestController
@RequestMapping("/flow/processRole")
public class ProcessRoleController extends BaseController {

    @Autowired
    private ProcessRoleService processRoleService;

    @SysLog(title = "新增流程角色关联")
    @PostMapping("/saveProcessRole")
    @ApiOperation(value = "新增")
    public RestResult saveProcessRole(@RequestBody ProcessRoleVO processRoleVO) {
        processRoleService.saveProcessRole(processRoleVO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改流程角色关联")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody ProcessRoleDo processRoleDo) {
        processRoleService.updateProcessRole(processRoleDo);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程角色关联")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id) {
        processRoleService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询流程角色关联")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id) {
        return RestResult.succeed(processRoleService.findById(id));
    }

    @SysLog(title = "查询列表流程角色关联")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(ProcessRoleDo processRoleDo) {
        return RestResult.succeed(processRoleService.findList(processRoleDo));
    }

    @SysLog(title = "分页查询流程角色关联")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(ProcessRoleDo processRoleDo, Page page) {
        return RestResult.succeed(processRoleService.findPage(page, processRoleDo));
    }

    @SysLog(title = "根据公司id和角色id查询角色具备的流程表权限")
    @GetMapping("/findProcessIds")
    @ApiOperation(value = "根据公司id和角色id查询角色具备的流程表权限")
    public RestResult findProcessIds(ProcessRoleDo processRoleDo) {
        return RestResult.succeed(processRoleService.findProcessIds(processRoleDo));
    }
}

