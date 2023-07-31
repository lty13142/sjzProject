package com.crcm.bpm.controller;

import cn.hutool.http.HttpStatus;
import com.crcm.core.exception.CustomException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 过滤器异常抛出
 * @author zwj
 */
@RequestMapping("/bpmFilter")
@RestController
public class BpmFilterController {

    @RequestMapping("/exception")
    public void verifyException() {
        // 此处构造一个合适的异常并抛出即可
        throw new CustomException(HttpStatus.HTTP_FORBIDDEN, "您没有此功能权限！");

    }
}
