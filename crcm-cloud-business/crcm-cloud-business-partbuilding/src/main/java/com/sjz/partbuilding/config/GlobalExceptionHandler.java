package com.sjz.partbuilding.config;

import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 统一异常处理
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/25
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult<String> validExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException -> 参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public RestResult<String> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage());
        return RestResult.failed("相关信息已存在，请勿重复添加");
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageConversionException.class)
    public RestResult<String> parameterTypeException(HttpMessageConversionException e) {
        log.error("参数校验异常", e);
        return RestResult.failed("参数数据类型有误");
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RestResult<String> parameterFormatException(HttpMessageNotReadableException e) {
        log.error("参数数据格式有误", e);
        return RestResult.failed("参数数据格式有误");
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public RestResult<String> parameterFormatException(BindException e) {
        log.error("参数有误", e);
        return RestResult.failed("参数有误");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResult<String> exceptionHandler(Exception e) {
        log.error("请求异常：", e);
        return RestResult.failed("请求出错，请联系运维人员");
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return RestResult 异常结果
     */
    private RestResult<String> wrapperBindingResult(BindingResult bindingResult) {
        // 构建返回消息
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return RestResult.failed(ResultCode.FIND_WITH_NULL_ID.getCode(), msg.substring(2));
    }

}
