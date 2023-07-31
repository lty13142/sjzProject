package com.crcm.cloud.start.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ClassName SecurityUserController
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/23
 **/
@Slf4j
@RestController
public class SecurityUserController {

    @GetMapping("token/user")
    public Object user1(Principal principal) {
        log.info("principal: {}",principal);
        return principal;
    }
}
