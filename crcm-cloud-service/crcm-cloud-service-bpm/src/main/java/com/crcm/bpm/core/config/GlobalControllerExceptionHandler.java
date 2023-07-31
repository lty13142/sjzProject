package com.crcm.bpm.core.config;

import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalControllerExceptionHandler
 * @Description 全局异常处理器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/13
 **/
@Slf4j
@ControllerAdvice
@SuppressWarnings("all")
public class GlobalControllerExceptionHandler {


    /**
     * 系统异常处理
     *
     * @param e 异常对象
     * @see ExceptionHandler
     */
    @ExceptionHandler({Exception.class, Throwable.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        RestResult result = RestResult.failed(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg() + e.getMessage());
        log.error(result.toString(), e);
        // 获取请求URI
        this.getRequestURI(request);
        return result;
    }

    /**
     * 运行时异常处理
     *
     * @param e 异常对象
     * @see ExceptionHandler
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RestResult runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, RuntimeException e) {

        RestResult result = RestResult.failed(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg());
        log.error(result.toString(), e);
        // 获取请求URI
        this.getRequestURI(request);
        return result;
    }

    /**
     * 捕捉HttpClient 调用异常
     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(HttpClientErrorException.class)
//    public RestResult handleHttpClientError(HttpServletRequest request, HttpClientErrorException e) {
//        log.error("HttpClientErrorException -> HttpClient调用异常", e);
//        RestResult result = RestResult.failed(e.getRawStatusCode(), e.getMessage());
//        return result;
//    }

    /**
     * 捕捉404异常,这个方法只在配置
     * spring.mvc.throw-exception-if-no-handler-found=true来后起作用
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
@ExceptionHandler(NoHandlerFoundException.class)
    public RestResult handleNoHandlerFound(HttpServletRequest request, NoHandlerFoundException e) {
        log.error("NoHandlerFoundException -> 404异常", e);
        String requestURI = getRequestURI(request);
        RestResult result = RestResult.failed(HttpStatus.NOT_FOUND.value(), "请求地址：" + requestURI + " 不存在！");
        return result;
    }


    /**
     * 参数校验异常
     * <p> 表单提交时发生的参数绑定异常</p>
     *
     * @param e 异常对象
     * @return RestResult   返回类型
     * @throws
     * @see javax.validation.constraints.NotBlank
     * @see javax.validation.constraints.NotNull
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RestResult errorBindHandler(BindException e) {
        log.error("BindException -> 参数校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }


    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * <p> 表单提交时Content-Type为 “application / x-www-form-urlencoded”。
     * 因此，Spring将数据解释为Web表单数据（而不是JSON）。 Spring使用FormHttpMessageConverter将POST主体转换为域对象，并导致BindException。
     * 我们想要的是Spring将POST数据视为JSON，并使用MappingJackson2HttpMessageConverter将POST正文解析为对象。
     * </p>
     *
     * @param e 异常对象
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException -> 参数绑定校验异常", e);
        // 获取请求URI
        this.getRequestURI(request);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 系统自定义异常处理
     *
     * @param e 异常对象
     * @return BaseHttpParamsResp    返回类型
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RestResult customErrorHandler(HttpServletRequest request, HttpServletResponse response, CustomException e) {
        return RestResult.failed(ResultCode.ERROR.getCode(), e.getMessage());
    }

    /**
     * 系统自定义异常处理
     *
     * @param e 异常对象
     * @return BaseHttpParamsResp    返回类型
     */
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RestResult authErrorHandler(HttpServletRequest request, HttpServletResponse response, AuthException e) {
        return RestResult.failed(ResultCode.ERROR.getCode(), e.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResult handleHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        // 获取请求URI
        this.getRequestURI(request);
        log.error("Token异常信息 ex={}", msg, e);
        return RestResult.failed(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage());
    }


    /**
     * 处理业务校验过程中碰到的非法参数异常 该异常基本由{@link Assert}抛出
     *
     * @param exception 参数校验异常
     * @return API返回结果对象包装后的错误输出结果
     * @see Assert#hasLength(String, String)
     * @see Assert#hasText(String, String)
     * @see Assert#isTrue(boolean, String)
     * @see Assert#isNull(Object, String)
     * @see Assert#notNull(Object, String)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResult handleIllegalArgumentException(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException exception) {
        // 获取请求URI
        this.getRequestURI(request);
        log.error("非法参数,ex = {}", exception.getMessage(), exception);
        return RestResult.failed(cn.hutool.http.HttpStatus.HTTP_BAD_REQUEST, exception.getMessage());
    }


    /**
     * AccessDeniedException
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResult handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractAccessDecisionManager.accessDenied",
                e.getMessage());
        // 获取请求URI
        this.getRequestURI(request);
        log.error("拒绝授权异常信息 ex={}", msg, e);
        return RestResult.failed(cn.hutool.http.HttpStatus.HTTP_FORBIDDEN, e.getLocalizedMessage());
    }

    /**
     * InvalidTokenException
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResult handleInvalidTokenException(HttpServletRequest request, HttpServletResponse response, InvalidTokenException e) {
        String msg = e.getMessage();
        // 获取请求URI
        this.getRequestURI(request);
        log.error("Token异常信息 ex={}", msg, e);
        return RestResult.failed(HttpStatus.UNAUTHORIZED.value(), e.getLocalizedMessage());
    }


    /**
     * 获取请求URI
     *
     * @param request
     * @return void
     * @author qipp
     * @date 2020/5/2 21:32
     */
    private String getRequestURI(HttpServletRequest request) {
        // 获取请求路径
        String requestUrl = request.getRequestURI();
        log.error("获取请求URI -> {}", requestUrl);
        return requestUrl;
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return RestResult 异常结果
     */
    private RestResult wrapperBindingResult(BindingResult bindingResult) {
        // 构建返回消息
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return RestResult.failed(ResultCode.ERROR.getCode(), msg.substring(2));
    }


}
