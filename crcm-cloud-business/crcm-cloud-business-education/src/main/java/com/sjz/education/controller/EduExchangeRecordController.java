package com.sjz.education.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.dto.EduPointExchangeDTO;
import com.sjz.education.model.entity.EduExchangeRecord;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.service.EduExchangeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 积分兑换记录controller
 * @author: sssccc
 * @create: 2023-04-06 15:44
 **/
@RestController
@RequestMapping("/exchangeRecord")
@Api(tags = "积分兑换记录")
public class EduExchangeRecordController extends BaseController {

    @Autowired
    private EduExchangeRecordService exchangeRecordService;

    /**
     * 积分兑换新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "积分兑换新增")
    @SysLog(title = "积分兑换新增", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduExchangeRecord t) {
        return RestResult.succeed(exchangeRecordService.add(t));
    }

    /**
     * 积分兑换修改
     */
    @PostMapping("/edit")
    @ApiOperation(value = "积分兑换修改")
    @SysLog(title = "积分兑换修改", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduExchangeRecord t) {
        return RestResult.succeed(exchangeRecordService.edit(t));
    }

    /**
     * 根据id进行删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id进行删除")
    @SysLog(title = "根据id进行删除", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(exchangeRecordService.deleteById(id));
    }

    /**
     * 根据id查找
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查找")
    public RestResult getById(@PathVariable("id") String id) {
        return RestResult.succeed(exchangeRecordService.getById(id));
    }

    /**
     * 我的积分兑换记录
     */
    @GetMapping("/myList")
    @ApiOperation(value = "我的积分兑换记录")
    public RestResult myList(EduExchangeRecord t) {
        return RestResult.succeed(exchangeRecordService.getListByUser(t));
    }


    /**
     * 积分兑换记录列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "积分兑换记录列表")
    public RestResult getList(EduExchangeRecord t) {
        return RestResult.succeed(exchangeRecordService.getList(t));
    }

    /**
     * 积分兑换记录分页
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "积分兑换记录分页")
    public RestResult getPage(PageT page, EduExchangeRecord t) {
        return RestResult.succeed(exchangeRecordService.getPage(page, t));
    }

    /**
     * 核销
     */
    @PostMapping("/pass")
    @ApiOperation(value = "核销")
    @SysLog(title = "核销", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult pass(@RequestBody EduExchangeRecord t) {
        return RestResult.succeed(exchangeRecordService.pass(t.getId()));
    }

    /**
     * 批量核销
     */
    @PostMapping("/batchPass")
    @ApiOperation(value = "批量核销")
    @SysLog(title = "批量核销", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult batchPass(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(exchangeRecordService.batchPass(t));
    }

    /**
     * 积分兑换记录折线图(当月)
     */
    @GetMapping("/countByMonth")
    @ApiOperation(value = "积分兑换记录折线图(当月)")
    public RestResult countByMonth() {
        return RestResult.succeed(exchangeRecordService.countByMonth());
    }

    /**
     * 积分兑换记录折线图(当年)
     */
    @GetMapping("/countByYear")
    @ApiOperation(value = "积分兑换记录折线图(当年)")
    public RestResult countByYear() {
        return RestResult.succeed(exchangeRecordService.countByYear());
    }

    /**
     * 获取各村使用积分用户统计
     */
    @GetMapping("/countByVillage")
    @ApiOperation(value = "获取各村使用积分用户统计")
    public RestResult countByVillage() {
        return RestResult.succeed(exchangeRecordService.countByVillage());
    }

    /**
     * 按商品分类使用积分用户统计
     */
    @GetMapping("/countByProducts")
    @ApiOperation(value = "按商品分类使用积分用户统计")
    public RestResult countByProducts() {
        return RestResult.succeed(exchangeRecordService.countByProducts());
    }

    /**
     * 大屏-积分兑换
     */
    @GetMapping("/pointExchange")
    @ApiOperation(value = "大屏-积分兑换")
    public RestResult pointExchange(EduPointExchangeDTO dto) {
        return RestResult.succeed(exchangeRecordService.pointExchange(dto));
    }

    /**
     * 最近核销记录查询
     */
    @GetMapping("/getPassList")
    @ApiOperation(value = "我的积分兑换记录")
    public RestResult getPassList() {
        return RestResult.succeed(exchangeRecordService.getPassList());
    }

}
