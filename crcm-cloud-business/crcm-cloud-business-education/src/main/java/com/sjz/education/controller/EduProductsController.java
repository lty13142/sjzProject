package com.sjz.education.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.entity.EduProducts;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.service.EduProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 商品管理controller
 * @author: sssccc
 * @create: 2023-04-03 16:04
 **/
@RestController
@RequestMapping("/products")
@Api(tags = "兑换商品管理")
public class EduProductsController extends BaseController {

    @Autowired
    private EduProductsService productsService;

    /**
     * 新增商品
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增商品")
    @SysLog(title = "新增商品", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduProducts t) {
        return RestResult.succeed(productsService.add(t));
    }

    /**
     * 修改商品
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改商品")
    @SysLog(title = "修改商品", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduProducts t) {
        return RestResult.succeed(productsService.edit(t));
    }

    /**
     * 根据id进行删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id进行删除")
    @SysLog(title = "根据id进行删除", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(productsService.deleteById(id));
    }

    /**
     * 批量删除商品
     */
    @PostMapping("/batchDelete")
    @ApiOperation(value = "批量删除商品")
    @SysLog(title = "批量删除商品", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult batchDelete(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(productsService.batchDelete(t.getIds()));
    }

    /**
     * 根据id查找
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查找")
    public RestResult getById(@PathVariable("id") String id) {
        return RestResult.succeed(productsService.getById(id));
    }

    /**
     * 获取商品列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "获取商品列表")
    public RestResult getList(EduProducts t) {
        return RestResult.succeed(productsService.getList(t));
    }

    /**
     * 获取商品分页
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "获取商品分页")
    public RestResult getPage(PageT page, EduProducts t) {
        return RestResult.succeed(productsService.getPage(page, t));
    }

    /**
     * 上架
     */
    @PostMapping("/grounding")
    @ApiOperation(value = "上架")
    @SysLog(title = "上架", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult grounding(@RequestBody EduProducts t) {
        return RestResult.succeed(productsService.grounding(t.getId()));
    }

    /**
     * 批量审核通过并上架
     */
    @PostMapping("/batchGrounding")
    @ApiOperation(value = "批量上架")
    @SysLog(title = "批量上架", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult batchGrounding(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(productsService.batchGrounding(t.getIds()));
    }

    /**
     * 下架
     */
    @PostMapping("/undercarriage")
    @ApiOperation(value = "下架")
    @SysLog(title = "下架", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult undercarriage(@RequestBody EduProducts t) {
        return RestResult.succeed(productsService.undercarriage(t.getId()));
    }

    /**
     * 批量下架
     */
    @PostMapping("/batchUndercarriage")
    @ApiOperation(value = "批量下架")
    @SysLog(title = "批量下架", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult batchUndercarriage(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(productsService.batchUndercarriage(t.getIds()));
    }
}
