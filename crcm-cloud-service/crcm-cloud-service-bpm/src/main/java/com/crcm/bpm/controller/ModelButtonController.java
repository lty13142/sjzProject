package com.crcm.bpm.controller;

import com.crcm.bpm.domain.entity.ModelButtonDO;
import com.crcm.bpm.service.ModelButtonService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 流程节点按钮图Controller
 *
 * @author zwj
 * @date 2020-11-02
 */
@RestController
@RequestMapping("/flow/modelButton")
public class ModelButtonController extends BaseController {
    @Autowired
    private ModelButtonService modelButtonService;

    /**
     * 新增
     */
    @SysLog(title = "新增流程节点按钮")
    @PostMapping("/save")
    public RestResult saveProcess(@RequestBody String modelButtonList) {
        modelButtonService.saveModelButtonDO(modelButtonList);
        return RestResult.succeed();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @SysLog(title = "根据id查询流程节点按钮")
    @GetMapping("/{id}")
    public RestResult findById(@PathVariable String id) {
        return RestResult.succeed(modelButtonService.findById(id));
    }

    /**
     * 修改
     */
    @SysLog(title = "修改流程节点按钮")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody ModelButtonDO modelButtonDO) {
        return RestResult.succeed(modelButtonService.updateModelButtonDO(modelButtonDO));
    }

    /**
     * 删除
     */
    @SysLog(title = "删除流程节点按钮")
    @PostMapping("/delete/{id}")
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(modelButtonService.deleteById(id));
    }

    /**
     * 查询列表
     */
    @SysLog(title = "查询列表流程节点按钮")
    @GetMapping("/list")
    public RestResult getList(ModelButtonDO modelButtonDO) {
        return RestResult.succeed(modelButtonService.findList(modelButtonDO));
    }

    /**
     * 获取审批节点按钮
     * @param nodeId
     * @param modelId
     * @return
     */
    @SysLog(title = "获取审批节点按钮")
    @GetMapping("/getButtonList")
    public RestResult getButtonList(@RequestParam("nodeId") String nodeId,
                                    @RequestParam("modelId") Long modelId) {
        return RestResult.succeed(modelButtonService.getButtonList(nodeId,modelId));
    }
}
