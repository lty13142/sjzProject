package com.crcm.bpm.controller;


import com.crcm.bpm.service.ApproveOpinionService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审批意见表 前端控制器
 * </p>
 *
 * @author
 * @since 2020-11-23
 */
@Api(value = "ApproveOpinionController控制类", tags = "审批意见表的控制类")
@RestController
@RequestMapping("/approve-opinion")
public class ApproveOpinionController {

    @Autowired
    private ApproveOpinionService approveOpinionService;

    @SysLog(title = "获取流转意见")
    @ApiOperation("获取流转意见")
    @GetMapping("/getApproveOpinion")
    public RestResult getApproveOpinion(Long applyId){
        return RestResult.succeed(approveOpinionService.getApproveOpinion(applyId));
    }
}
