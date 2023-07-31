package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.Announcement;
import com.sjz.partbuilding.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"公告接口"})
@RestController
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增公告", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody Announcement announcement){
        announcementService.saveAnnouncement(announcement);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改公告", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody Announcement announcement){
        announcementService.updateAnnouncement(announcement);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除公告", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody Announcement announcement){
        announcementService.deleteById(announcement.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(announcementService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(Announcement announcement){
        return RestResult.succeed(announcementService.findList(announcement));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(Announcement announcement, PageVO pageVo){
        return RestResult.succeed(announcementService.findPage(UtilPage.initPage(pageVo),announcement));
    }

    @GetMapping("/getCreatePage")
    @ApiOperation(value = "分页查询")
    public RestResult getCreatePage(Announcement announcement,PageVO pageVo){
        return RestResult.succeed(announcementService.findCreatePage(UtilPage.initPage(pageVo),announcement));
    }

}

