package com.crcm.file.config;

import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 统一异常处理
 * @Author GZL
 * @Date 2023/3/28 11:24
 * @Version 1.0
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public RestResult<String> handleException(CustomException e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResult<String> exceptionHandler(Exception e) {
        log.error("请求异常：", e);
        return RestResult.failed("请求出错，请联系运维人员");
    }
}
