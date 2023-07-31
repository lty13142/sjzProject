package com.crcm.bpm.core.handler;

import com.crcm.bpm.core.exception.BpmException;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/10/30 16:19 <br>
 * @Author: <a>bot</a>
 */
@Slf4j
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public RestResult handleException(CustomException e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BpmException.class)
    @ResponseBody
    public RestResult handleException(BpmException e) {
        log.error(e.getBpmError().getMessage(), e);
        return RestResult.failed(e.getBpmError().getCode(), e.getBpmError().getMessage());
    }
}
