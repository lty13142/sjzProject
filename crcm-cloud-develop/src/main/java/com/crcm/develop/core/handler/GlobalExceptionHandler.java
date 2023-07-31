package com.crcm.develop.core.handler;

import com.crcm.develop.common.exception.BaseException;
import com.crcm.develop.common.exception.CustomException;
import com.crcm.develop.core.base.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 *
 * @author zzyt
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 基础异常
     */
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public RestResult baseException(BaseException e) {
        return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 业务异常
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public RestResult businessException(CustomException e) {
        if (e.getCode() == null) {
            return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return RestResult.failed(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResult handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public RestResult handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return RestResult.failed(HttpStatus.FORBIDDEN.value(), "没有权限，请联系管理员授权");
    }

    @ResponseBody
    @ExceptionHandler(AccountExpiredException.class)
    public RestResult handleAccountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public RestResult handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RestResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public RestResult validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    /**
     * 自定义验证异常
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    /**
     * 演示模式异常
     */
//    @ExceptionHandler(DemoModeException.class)
//    public RestResult demoModeException(DemoModeException e)
//    {
//        return RestResult.failed("演示模式，不允许操作");
//    }
}
