package com.crcm.develop.system.controller;

import com.crcm.develop.core.base.BaseController;
import com.crcm.develop.core.base.RestResult;
import com.crcm.develop.core.conf.security.domain.LoginBody;
import com.crcm.develop.core.conf.security.service.SysLoginService;
import com.crcm.develop.core.conf.security.service.TokenService;
import com.crcm.develop.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 登录验证
 *
 * @author zzyt
 */
@RestController
public class SysLoginController extends BaseController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService userService;

    /**
     * 登录方法
     *
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public RestResult login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(),
                loginBody.getUuid());
        HashMap<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("msg","登陆成功！");
        return RestResult.succeed(result);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
//    @GetMapping("/getRouters")
//    public RestResult getRouters() {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        // 用户信息
//        SysUser user = loginUser.getUser();
//        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
//        return RestResult.succeed(menuService.buildMenus(menus));
//    }
}
