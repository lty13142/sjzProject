package com.crcm.auth.controller;

import com.crcm.core.constant.AuthConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName AuthController
 * @Description 授权码登录Controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Controller
public class AuthController {
    /**
     * 授权码登录页跳转
     *
     * @return java.lang.String
     * @author qipp
     * @date 2020/1/16 11:13
     */
    @RequestMapping(AuthConstants.LOGIN_PAGE)
    public String loginPage(Model model) {
        model.addAttribute("loginProcessUrl", AuthConstants.LOGIN_PROCESS_URL);
        return "login";
    }
}
