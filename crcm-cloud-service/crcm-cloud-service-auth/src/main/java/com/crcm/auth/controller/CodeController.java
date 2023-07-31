package com.crcm.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CodeController
 * @Description 授权码Controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Slf4j
@RestController
public class CodeController {
    /**
     * 拿到code
     * <p>隐藏模式下返回token到返回code的控制器上，但是获取不到</p>
     * <p>
     * 令牌的位置是 URL 锚点（fragment），而不是查询字符串（querystring），
     * 这是因为 OAuth 2.0 允许跳转网址是 HTTP 协议，因此存在"中间人攻击"的风险，
     * 而浏览器跳转时，锚点不会发到服务器，就减少了泄漏令牌的风险。
     * </p>
     *
     * @param code 一次性获取token码
     * @return java.lang.String
     * @author qipp
     * @date 2020/1/15 10:16
     */
    @GetMapping("/authorize/code")
    public String save(@RequestParam(value = "code", required = false) String code) {
        log.info("code ->" + code);
        return code;
    }
}
