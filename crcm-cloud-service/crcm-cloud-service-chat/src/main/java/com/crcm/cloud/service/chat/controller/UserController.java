package com.crcm.cloud.service.chat.controller;

import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.service.chat.dto.UserLoginDTO;
import com.crcm.cloud.service.chat.dto.UserRegisterDTO;
import com.crcm.cloud.service.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public JSONObject login(@Validated @RequestBody UserLoginDTO dto) {
        return userService.login(dto);
    }

    @PostMapping("/register")
    public JSONObject register(@Validated @RequestBody UserRegisterDTO dto) {
        return userService.register(dto);
    }
}
