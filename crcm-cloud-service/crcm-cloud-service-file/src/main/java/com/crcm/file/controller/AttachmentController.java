package com.crcm.file.controller;


import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.annation.Inner;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.file.model.entity.Attachment;
import com.crcm.file.model.vo.AttachmentsSimpleVO;
import com.crcm.file.model.vo.AttachmentsVO;
import com.crcm.file.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = "附件表接口")
@RestController
@RequestMapping("/att")
public class AttachmentController extends BaseController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody Attachment t) {
        attachmentService.saveAttachments(t);
        return RestResult.succeed();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public RestResult update(@RequestBody Attachment t) {
        attachmentService.updateAttachments(t);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public RestResult delete(@RequestBody Attachment t) {
        attachmentService.deleteById(t.getId());
        return RestResult.succeed();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult<Attachment> getById(@PathVariable("id") String id) {
        return RestResult.succeed(attachmentService.findById(id));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult<PageT<Attachment>> getPage(Attachment t, PageT page) {
        return RestResult.succeed(attachmentService.findPage(page, t));
    }

    @GetMapping("/getAttachments")
    @ApiOperation(value = "通过ids查询多个附件")
    public RestResult<List<AttachmentsVO>> getAttachments(AttachmentsVO vo) {
        return RestResult.succeed(attachmentService.findAttachments(vo));
    }

    @GetMapping("/path/{id}")
    @ApiOperation(value = "通过附件ID查询附件路径")
    public RestResult<String> getFilePath(@PathVariable("id") String id) {
        return RestResult.succeed(attachmentService.findFilePath(id));
    }

    @Inner
    @GetMapping("/findFileListByIds/{ids}")
    public RestResult findFileListByIds(@PathVariable(value = "ids") String ids) {
        return RestResult.succeed(attachmentService.findFileListByIds(ids));
    }

    @GetMapping("/getAttachmentListByIds/{ids}")
    @ApiOperation(value = "通过ids查询多个附件")
    public RestResult<List<Map<String, String>>> getAttachmentListByIds(@PathVariable(value = "ids") String ids) {
        return RestResult.succeed(attachmentService.findAttachmentListByIds(ids));
    }
}


