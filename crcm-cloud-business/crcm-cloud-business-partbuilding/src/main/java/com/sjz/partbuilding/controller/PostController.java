package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.Post;
import com.sjz.partbuilding.model.vo.PostTreeVo;
import com.sjz.partbuilding.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 党内职务controller
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 17:03
 */
@Api(tags = {"党内职务表接口"})
@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource
    private PostService postService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增党内职务", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody Post post){
        postService.savePost(post);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改党内职务", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody Post post){
        postService.updatePost(post);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除党内职务", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody Post post){
        postService.deleteById(post.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(postService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(Post post){
        return RestResult.succeed(postService.findList(post));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(Post post, PageVO pageVo){
        return RestResult.succeed(postService.findPage(UtilPage.initPage(pageVo),post));
    }

    @GetMapping("/getPersonOrgTree")
    @ApiOperation(value = "获取人员组织树")
    public RestResult getPersonOrgTree(String orgId) {
        List<PostTreeVo> postTree = postService.getPersonOrgTree(orgId);
        return RestResult.succeed(postTree);
    }

}

