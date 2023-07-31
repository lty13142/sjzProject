package com.crcm.bpm.controller;

import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.service.FormDataService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"流程表单数据接口"})
@RestController
@RequestMapping("/flow/formData")
public class FormDataController extends BaseController {

    @Autowired
    private FormDataService formDataService;

    @SysLog(title = "根据申请编号获取表单数据")
    @ApiOperation(value = "根据申请编号获取表单数据", notes = "获取表单数据", produces = "application/json")
    @GetMapping("/getFormDataByApplyId")
    public RestResult getFormDataByApplyId(Long applyId, String nodeId, Long modelId) {

        BestBpmAsset.isAssetEmpty(applyId);
        return RestResult.succeed(formDataService.getFormDataByApplyId(applyId, nodeId, modelId));
    }

    @SysLog(title = "根据流程实例编号获取表单数据")
    @ApiOperation(value = "根据流程实例编号获取表单数据", notes = "获取表单数据", produces = "application/json")
    @GetMapping("/getFormDataByProcInstId/{procInstId}")
    public RestResult getFormDataByApplyId(@ApiParam(required = true, name = "申请编号", value = "procInstId", example = "1") @PathVariable("procInstId") String procInstId) {
        BestBpmAsset.isAssetEmpty(procInstId);
        return RestResult.succeed(formDataService.getFormDataByProcInstId(procInstId));
    }

}

