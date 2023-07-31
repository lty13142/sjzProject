package com.crcm.bpm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.dto.request.ApplyReqDTO;
import com.crcm.bpm.domain.entity.ApplyDO;
import com.crcm.bpm.domain.vo.ApplyAddReqVO;
import com.crcm.bpm.service.ApplyService;
import com.crcm.bpm.utils.FormDataUtil;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = {"申请单管理接口"})
@RestController
@RequestMapping("/flow/apply")
public class ApplyController extends BaseController {

    @Autowired
    private ApplyService applyService;

    @SysLog(title = "新增流程申请")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody ApplyDO applyDO){
        applyService.saveApply(applyDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改流程申请")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody ApplyDO applyDO){
        applyService.updateApply(applyDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程申请")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id){
        applyService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程实例")
    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public RestResult delete(@RequestBody ApplyDO applyDO){
        applyService.deleteById(applyDO.getId() + "");
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询流程申请")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(applyService.findById(id));
    }

    @SysLog(title = "查询列表流程申请")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(ApplyDO applyDO){
        return RestResult.succeed(applyService.findList(applyDO));
    }

    @SysLog(title = "分页查询流程申请")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(ApplyDO applyDO, Page page){
        return RestResult.succeed(applyService.findPage(page, applyDO));
    }

    @SysLog(title = "发起流程")
    @ApiOperation(value = "发起流程", notes = "发起流程", produces = "application/json")
    @PostMapping("/startProcess")
    public RestResult startProcess(@Valid @RequestBody ApplyAddReqVO applyAddReqVO) {
        if (applyAddReqVO == null || StringUtils.isEmpty(applyAddReqVO.getProcessKey())) {
            return RestResult.failed("流程KEY不能为空！");
        }
        ApplyReqDTO applyAddReqDTO = BeanUtil.copyProperties(applyAddReqVO, ApplyReqDTO.class);
        if (applyAddReqVO.getData() != null && applyAddReqVO.getData().containsKey("formJson") && applyAddReqVO.getData().get("formJson") != null) {
            // 修正formJson格式属性值
            applyAddReqVO.getData().put("formJson", FormDataUtil.replaceFormAttribute(applyAddReqVO.getData().get("formJson").toString()));
        }
        applyAddReqDTO.setData(applyAddReqVO.getData());
        return RestResult.succeed(applyService.startProcess(applyAddReqDTO));
    }

    @SysLog(title = "保存草稿")
    @PostMapping("/saveDraft")
    @ApiOperation(value = "保存草稿")
    public RestResult saveDraft(@RequestBody ApplyAddReqVO applyAddReqVO){
        return RestResult.succeed(applyService.saveDraft(applyAddReqVO));
    }

    @SysLog(title = "获取流程发起节点信息")
    @ApiOperation(value = "获取流程发起节点信息", notes = "获取流程发起节点信息", produces = "application/json")
    @PostMapping("/getStartNodeInfo")
    public RestResult getStartNodeInfo(@Valid @RequestBody ApplyAddReqVO applyAddReqVO) {
        if (applyAddReqVO == null || StringUtils.isEmpty(applyAddReqVO.getProcessKey())) {
            return RestResult.failed("流程KEY不能为空！");
        }
        ApplyReqDTO applyAddReqDTO = BeanUtil.copyProperties(applyAddReqVO, ApplyReqDTO.class);
        if (applyAddReqVO.getData() != null && applyAddReqVO.getData().containsKey("formJson")  && applyAddReqVO.getData().get("formJson") != null) {
            // 修正formJson格式属性值
            applyAddReqVO.getData().put("formJson", FormDataUtil.replaceFormAttribute(applyAddReqVO.getData().get("formJson").toString()));
        }
        applyAddReqDTO.setData(applyAddReqVO.getData());
        return RestResult.succeed(applyService.getStartNodeInfo(applyAddReqDTO));
    }

    @SysLog(title = "查询关联流程")
    @GetMapping("/getForRelation")
    @ApiOperation(value = "查询关联流程")
    public RestResult getForRelation(ApplyDO applyDO){
        return RestResult.succeed(applyService.getForRelation(applyDO));
    }
}

