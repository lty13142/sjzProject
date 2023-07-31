package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.vo.AttachmentsVo;
import com.sjz.partbuilding.service.AttachmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 附件表接口
 * @author zzyt
 * @version 1.0
 * @date 2020/7/8 9:29
 */
@Api(tags = {"附件表接口"})
@RestController
@RequestMapping("/attachments")
public class AttachmentsController extends BaseController {

    @Autowired
    private AttachmentsService attachmentsService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增附件", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody Attachments t) {
        attachmentsService.saveAttachments(t);
        return RestResult.succeed();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改附件", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult update(@RequestBody Attachments t) {
        attachmentsService.updateAttachments(t);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "刪除附件", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody Attachments t) {
        attachmentsService.deleteById(t.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id) {
        return RestResult.succeed(attachmentsService.findById(id));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(Attachments t, PageVO pageVo) {
        return RestResult.succeed(attachmentsService.findPage(UtilPage.initPage(pageVo), t));
    }

    @GetMapping("/getAttachments")
    @ApiOperation(value = "通过ids查询多个附件")
    public RestResult getAttachments(AttachmentsVo vo) {
        return RestResult.succeed(attachmentsService.findAttachments(vo));
    }

    @GetMapping("/getFilePath")
    @ApiOperation(value = "通过附件ID查询附件路径")
    public RestResult getFilePath(String attachmentId) {
        return RestResult.succeed(attachmentsService.findFilePath(attachmentId));
    }

    @GetMapping("getMinIoByAttId")
    @ApiOperation(value = "根据attId获取minIO附件信息")
    public RestResult getMinIoByAttId(String attachmentId){
        Attachments attachments = attachmentsService.getMinIoByAttId(attachmentId);
        return RestResult.succeed(attachments);
    }

    @GetMapping("getMinIoListByAttIds")
    @ApiOperation(value = "通过attIds获取minIO附件信息集")
    public RestResult getMinIoListByAttIds(String attachmentIds) {
        List<Attachments> attList = attachmentsService.getMinIoListByAttIds(attachmentIds);
        return RestResult.succeed(attList);
    }



}

