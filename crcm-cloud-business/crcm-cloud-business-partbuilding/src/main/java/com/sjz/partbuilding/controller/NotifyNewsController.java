package com.sjz.partbuilding.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.NotifyNews;
import com.sjz.partbuilding.model.vo.LoginKeyWordVo;
import com.sjz.partbuilding.model.vo.NotifyNewsVo;
import com.sjz.partbuilding.service.NotifyNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 通知新闻controller
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/9/22 14:10
 */
@Api(tags = {"通知新闻接口"})
@RestController
@RequestMapping("/notifyNews")
public class NotifyNewsController extends BaseController {

    @Resource
    private NotifyNewsService notifyNewsService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增通知新闻", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody NotifyNewsVo notifyNewsVo) {
        notifyNewsService.saveNotifyNews(notifyNewsVo);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改通知新闻", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody NotifyNewsVo notifyNewsVo) {
        notifyNewsService.updateNotifyNews(notifyNewsVo);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除通知新闻", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody NotifyNews notifyNews) {
        notifyNewsService.deleteById(notifyNews.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id) {
        return RestResult.succeed(notifyNewsService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(NotifyNews notifyNews) {
        return RestResult.succeed(notifyNewsService.findList(notifyNews));
    }


    @GetMapping("/getSendPage")
    @ApiOperation(value = "新闻/通知管理分页查询")
    public RestResult getPage(NotifyNewsVo notifyNewsVo, PageVO pageVo) {
        return RestResult.succeed(notifyNewsService.getSendPage(UtilPage.initPage(pageVo), notifyNewsVo));
    }

    @GetMapping("/getAcceptPage")
    @ApiOperation(value = "新闻/通知分页查询")
    public RestResult getAcceptPage(NotifyNewsVo notifyNewsVo, PageVO pageVo) {
        return RestResult.succeed(notifyNewsService.getAcceptPage(UtilPage.initPage(pageVo), notifyNewsVo));
    }

    @GetMapping("/getLoginNews")
    @ApiOperation(value = "获取大屏首页新闻")
    public RestResult getLoginNews(NotifyNews notifyNews) {
        IPage<NotifyNewsVo> iPage = notifyNewsService.getLoginNews(notifyNews);
        return RestResult.succeed(iPage);
    }

    @GetMapping("/getNotifyByKeyWordId")
    @ApiOperation(value = "根据关键词id获取新闻或公告id")
    public RestResult getNotifyByKeyWordId(String id) {
        LoginKeyWordVo loginKeyWordVo = notifyNewsService.getNotifyByKeyWordId(id);
        return RestResult.succeed(loginKeyWordVo);
    }

    @GetMapping("/getCoverNews")
    @ApiOperation(value = "获取新闻封面轮播图")
    public RestResult getCoverNews(NotifyNewsVo notifyNewsVo) {
        return RestResult.succeed(notifyNewsService.getCoverNews(notifyNewsVo));
    }
}

