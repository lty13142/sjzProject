package com.crcm.admin.controller;


import com.crcm.admin.model.entity.SysUserDetail;
import com.crcm.admin.model.vo.SysUserDetailVO;
import com.crcm.admin.service.UserDetailService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"用户详情接口"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/userDetail")
public class UserDetailController extends BaseController {
    private final UserDetailService userDetailService;

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult<Integer> edit(@RequestBody SysUserDetail sysUserDetail) {
        return RestResult.succeed(userDetailService.updateUserDetail(sysUserDetail));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查询用户详细信息")
    public RestResult<SysUserDetailVO> getDetail(@PathVariable("userId") String userId) {
        return RestResult.succeed(userDetailService.getDetail(userId));
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult<SysUserDetail> getById(String id) {
        return RestResult.succeed(userDetailService.findById(id));
    }

}

