package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.KeyWord;
import com.sjz.partbuilding.service.KeyWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 关键词controller
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/20 10:21
 */
@Api(tags = {"关键词接口"})
@RestController
@RequestMapping("/keyWord")
public class KeyWordController extends BaseController {

    @Resource
    private KeyWordService keyWordService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增关键词", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody KeyWord keyWord){
        return RestResult.succeed( keyWordService.saveKeyWordByNameStr(keyWord));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改关键词", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody KeyWord keyWord){
        keyWordService.updateKeyWord(keyWord);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除关键词", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody KeyWord keyWord){
        keyWordService.deleteById(keyWord.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(keyWordService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(KeyWord keyWord){
        return RestResult.succeed(keyWordService.findList(keyWord));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(KeyWord keyWord, PageVO pageVo){
        return RestResult.succeed(keyWordService.findPage(UtilPage.initPage(pageVo),keyWord));
    }

}

