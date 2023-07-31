package com.zsgf.platform.controller.study;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.study.DocumentInfoDTO;
import com.zsgf.platform.model.entity.study.DocumentInfo;
import com.zsgf.platform.service.study.DocumentInfoService;
import com.zsgf.platform.vo.study.DocumentInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文档信息Controller
 *
 * @author gzl
 * @date 2023-03-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/documentInfo")
@Api(tags = "文档信息")
public class DocumentInfoController extends BaseController {


    private final DocumentInfoService documentInfoService;

    /**
     * 新增文档信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增文档信息")
    @SysLog(title = "新增文档信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody DocumentInfo documentInfo) {
        return RestResult.succeed(documentInfoService.saveDocumentInfo(documentInfo));
    }

    /**
     * 修改文档信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改文档信息")
    @SysLog(title = "修改文档信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody DocumentInfo documentInfo) {
        return RestResult.succeed(documentInfoService.updateDocumentInfo(documentInfo));
    }

    /**
     * 删除文档信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除文档信息")
    @SysLog(title = "删除文档信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(documentInfoService.deleteDocumentInfo(id));
    }


    /**
     * 分页查询文档信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询文档信息")
    public RestResult<PageT<DocumentInfoVo>> getPage(PageT<DocumentInfoVo> page, DocumentInfoDTO documentInfo) {
        return RestResult.succeed(documentInfoService.findDocumentInfoPage(page, documentInfo));
    }

    /**
     * 查询文档信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询文档信息列表")
    public RestResult<List<DocumentInfo>> getList(DocumentInfoDTO documentInfo) {
        return RestResult.succeed(documentInfoService.findDocumentInfoList(documentInfo));
    }

    /**
     * 获取文档信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取文档信息详细信息")
    public RestResult<DocumentInfo> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(documentInfoService.findDocumentInfoById(id));
    }
}
