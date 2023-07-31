package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.entity.ReportColumnDO;
import com.crcm.bpm.service.ReportColumnService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = {"配置统计表格"})
@RestController
@RequestMapping("/flow/reportColumn")
public class ReportColumnController extends BaseController {

    @Autowired
    private ReportColumnService reportColumnService;


    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody List<ReportColumnDO> reportColumn){
        reportColumnService.saveHistory(reportColumn);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody List<ReportColumnDO> reportColumn){
        reportColumnService.updateHistory(reportColumn);
        return RestResult.succeed();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据流程号删除")
    public RestResult delete(@PathVariable("id") String processKey){
        reportColumnService.deleteById(processKey);
        return RestResult.succeed();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过流程号查询详情")
    public RestResult getById(@PathVariable("id") String filed){
        return RestResult.succeed(reportColumnService.findById(filed));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(ReportColumnDO reportColumn){
        return RestResult.succeed(reportColumnService.findList(reportColumn));
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(ReportColumnDO reportColumn, Page page){
        return RestResult.succeed(reportColumnService.findPage(page, reportColumn));
    }


    @GetMapping("/treeReportColumn")
    @ApiOperation(value = "查询树")
    public RestResult treeReportColumn(){
            return RestResult.succeed(reportColumnService.treeReportColumn());
    }

    @GetMapping("/searchPage")
    @ApiOperation(value = "根据模板KEY分页查询")
    public RestResult searchPage(ReportColumnDO reportColumn, Page page){
        return RestResult.succeed(reportColumnService.searchPage(page, reportColumn));
    }


    @PostMapping("/downloadBatchByIds")
    @ApiOperation(value = "批量下载文件", notes = "批量下载文件")
    public void downloadBatchByIds(ReportColumnDO reportColumn, HttpServletResponse response) {
       //返回不带分页得数据
        reportColumnService.downloadBatchByIds(reportColumn,response);
    }

}
