package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.domain.vo.ModelDeployVo;
import com.crcm.bpm.domain.vo.ProcessSaveVo;
import com.crcm.bpm.service.ModelService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.security.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 流程模型图Controller
 *
 * @author zzyt
 * @date 2020-08-18
 */
@RestController
@RequestMapping("/flow/model")
public class ModelController extends BaseController {
    @Autowired
    private ModelService modelService;

    /**
     * 新增流程模型图
     */
    @SysLog(title = "新增流程模型图")
    @PostMapping("/save")
    public RestResult save(@RequestBody ModelDO modelDo) {
        return RestResult.succeed(modelService.saveModel(modelDo));
    }

    @SysLog(title = "修改保存流程Xml，Svg")
    @PostMapping("/save/process")
    public RestResult saveProcess(@RequestBody ProcessSaveVo vo) {
        modelService.saveProcess(vo);
        return RestResult.succeed("模型保存成功");
    }

    /**
     * 创建一个新的版本
     */
    @SysLog(title = "创建一个新的版本")
    @PostMapping("/newVersion")
    public RestResult newVersion(@RequestBody ModelDO modelDo) {
        return RestResult.succeed(modelService.createNewVersion(modelDo));
    }

    /**
     * 修改流程模型图
     */
    @SysLog(title = "修改流程模型图")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody ModelDO modelDo) {
        return RestResult.succeed(modelService.updateModel(modelDo));
    }

    /**
     * 删除流程模型图
     */
    @SysLog(title = "删除流程模型图")
    @PostMapping("/delete")
    public RestResult delete(@RequestBody ModelDO modelDo) {
        return RestResult.succeed(modelService.deleteModelById(modelDo.getId()));
    }


    /**
     * 分页查询流程模型图
     */
    @SysLog(title = "分页查询流程模型图")
    @GetMapping("/page")
    public RestResult getPage(Page page, ModelDO modelDo) {
        return RestResult.succeed(modelService.findModelPage(page, modelDo));
    }

    /**
     * 分页查询流程模型图
     */
    @SysLog(title = "分页查询流程模型图")
    @GetMapping("/page/main")
    public RestResult getMainPage(Page page, ModelDO modelDo) {
        modelDo.setMainVersion(BpmConstant.MAIN_VERSION);
        modelDo.setCompanyId(modelDo.getCompanyId() == null ? Objects.requireNonNull(SecurityUtil.getCurrentUser()).getComId() : modelDo.getCompanyId());
        return RestResult.succeed(modelService.findModelPage(page, modelDo));
    }

    /**
     * 分页查询未使用的已发布的流程模型图
     */
    @SysLog(title = "分页查询未使用的已发布的流程模型图")
    @GetMapping("/page/main/unUse")
    public RestResult getMainPageUnUse(Page page, ModelDO modelDo) {
        modelDo.setMainVersion(BpmConstant.MAIN_VERSION);
        return RestResult.succeed(modelService.getMainPageUnUse(page, modelDo));
    }


    /**
     * 查询所有生效流程
     */
    @GetMapping("/page/main/process")
    public RestResult getMainPageProcess(Page page, ModelDO modelDo) {
        modelDo.setMainVersion(BpmConstant.MAIN_VERSION);
        return RestResult.succeed(modelService.getMainPageProcess(page, modelDo));
    }

    /**
     * 查询流程模型图列表
     */
    @SysLog(title = "查询流程模型图列表")
    @GetMapping("/list")
    public RestResult getList(ModelDO modelDo) {
        return RestResult.succeed(modelService.findModelList(modelDo));
    }

    /**
     * 获取流程模型图详细信息
     */
    @SysLog(title = "获取流程模型图详细信息")
    @GetMapping(value = "/{id}")
    public RestResult getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(modelService.findModelById(id));
    }

    /**
     * 发布模型
     *
     * @param vo
     * @return
     */
    @SysLog(title = "发布模型")
    @ApiOperation(value = "发布模型", notes = "发布模型", produces = "application/json")
    @PostMapping("/publish")
    public RestResult deploy(@RequestBody ModelDeployVo vo) {
        modelService.processPublish(vo);
        return RestResult.succeed();
    }

    /**
     * 设置模型主版本
     *
     * @param vo
     * @return
     */
    @SysLog(title = "设置模型主版本")
    @ApiOperation(value = "设置模型主版本", notes = "设置模型主版本", produces = "application/json")
    @PostMapping("/setMainVersion")
    public RestResult setMainVersion(@RequestBody ModelDeployVo vo) {
        modelService.setMainVersion(vo.getModelId());
        return RestResult.succeed();
    }

    /**
     * 查询流程模型图列表
     */
    @SysLog(title = "查询流程模型图列表")
    @GetMapping("/preview/{modelId}")
    public void preview(@PathVariable String modelId) {
        ModelDO model = modelService.getById(modelId);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(model.getProcessSvg());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
