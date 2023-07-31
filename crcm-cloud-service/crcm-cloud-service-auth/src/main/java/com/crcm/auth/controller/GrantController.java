package com.crcm.auth.controller;

import com.crcm.core.constant.AuthConstants;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * @ClassName GrantController
 * @Description 自定义授权Controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Controller
@SessionAttributes("authorizationRequest")
public class GrantController {
    /**
     * 自定义授权页面
     *
     * @param param 认证参数应该都在这里
     * @param model model
     * @return java.lang.String
     * @author qipp
     * @date 2020/1/16 12:33
     */
    @RequestMapping(AuthConstants.CONFIRM_ACCESS)
    public String getAccessConfirmation(Map<String, Object> param, Model model) {

        AuthorizationRequest authorizationRequest = (AuthorizationRequest) param.get("authorizationRequest");
        if (authorizationRequest == null) {
            return "redirect:" + AuthConstants.LOGIN_PAGE;
        }
        model.addAttribute("scopes", authorizationRequest.getScope());
        model.addAttribute("clientId", authorizationRequest.getClientId());
        return "grant";
    }

}
