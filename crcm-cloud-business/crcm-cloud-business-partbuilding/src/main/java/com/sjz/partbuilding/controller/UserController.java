package com.sjz.partbuilding.controller;

import cn.hutool.core.bean.BeanUtil;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.response.RestResult;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.model.vo.LoginMenuVo;
import com.sjz.partbuilding.model.vo.user.LoginOrgVo;
import com.sjz.partbuilding.model.vo.user.UserInfoVo;
import com.sjz.partbuilding.service.UserPartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户接口
 * @author zzyt
 * @version 1.0
 */
@Slf4j
@Api(tags = {"用户接口"})
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserPartService userPartService;

    @Autowired
    private RemoteUserService remoteUserService;


    @GetMapping("/getUsersByType")
    @ApiOperation("根据组织活动类型获取出席人员")
    public RestResult getUserByType(OrgEvent orgEvent, String orgPersonId) {
        return RestResult.succeed(userPartService.getUserByType(orgEvent,orgPersonId));
    }

    @GetMapping("/getCurrentLoginOrg")
    @ApiOperation(value = "获取当前登陆人的组织信息")
    public RestResult getCurrentLoginOrg() {
        LoginOrgVo loginOrgVo = userPartService.getCurrentLoginOrg(null);
        return RestResult.succeed(loginOrgVo);
    }

    @GetMapping("/getLoginInfo")
    @ApiOperation("控制台登录用户信息获取")
    public RestResult getLoginInfo(String token) {
        RestResult result = remoteUserService.getDjInfo("", AuthConstants.FROM_IN);

        UserInfoVo userInfo = null;//userPartService.findUserInfo(token,null);
//        //获取用户权限集合
//        List<MenuVo> menuList = userInfo.getMenus();
//        //初始化结果返回集
//        List<LoginMenuVo> loginMenuVoList = new ArrayList<>();
//        //组装结果集
//        for (MenuVo menuVo : menuList) {
//            LoginMenuVo loginMenuVo = new LoginMenuVo();
//            BeanUtil.copyProperties(menuVo, loginMenuVo);
//            //添加到结果返回集
//            loginMenuVoList.add(loginMenuVo);
//        }
        return RestResult.succeed();
    }

}

