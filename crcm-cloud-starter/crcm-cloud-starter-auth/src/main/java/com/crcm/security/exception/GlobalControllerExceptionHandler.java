package com.crcm.security.exception;

import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
@RestControllerAdvice
public class GlobalControllerExceptionHandler implements ResponseBodyAdvice {
    /**
     * 系统异常处理
     *
     * @param e 异常对象
     * @see ExceptionHandler
     */
    @ResponseBody
    @ExceptionHandler({Exception.class, Throwable.class})
    public RestResult errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
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
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public RestResult runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

        RestResult result = RestResult.failed(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg() + e.getMessage());
        log.error(result.toString(), e);
        // 获取请求URI
        this.getRequestURI(request);
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
    @ResponseBody
    @ExceptionHandler(BindException.class)
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
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
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
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public RestResult customErrorHandler(HttpServletRequest request, HttpServletResponse response, CustomException e) {
        RestResult result = RestResult.failed(ResultCode.ERROR.getCode(), e.getLocalizedMessage());
        log.error(result.toString(), e);
        // 获取请求URI
        this.getRequestURI(request);
        return result;
    }


    /**
     * 系统自定义异常处理
     *
     * @param e 异常对象
     * @return RestResult
     * @author qipp
     * @date 2020/5/15 14:25
     */
    @ResponseBody
    @ExceptionHandler(BusinessCommonException.class)
    public RestResult businessCommonExceptionHandler(HttpServletRequest request, HttpServletResponse response, BusinessCommonException e) {
        RestResult result = RestResult.failed(e.getErrorCode(), e.getAdditionMessage());
        log.error(result.toString(), e);
        // 获取请求URI
        this.getRequestURI(request);
        return result;
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
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
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
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RestResult handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractAccessDecisionManager.accessDenied",
                e.getMessage());
        // 获取请求URI
        this.getRequestURI(request);
        log.error("拒绝授权异常信息 ex={}", msg);
        return RestResult.failed(cn.hutool.http.HttpStatus.HTTP_FORBIDDEN, e.getLocalizedMessage());
    }

    /**
     * 获取请求URI
     *
     * @param request
     * @return void
     * @author qipp
     * @date 2020/5/2 21:32
     */
    private void getRequestURI(HttpServletRequest request) {
        // 获取请求路径
        String requestUrl = request.getRequestURI();
        log.error("获取请求URI -> {}", requestUrl);
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


    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 统一数据处理
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //String类型返回会发生类型转换异常，额外处理
        if (o instanceof String) {
            try {
                serverHttpResponse.getHeaders().set("Content-Type", "application/json;charset=utf-8");
                return objectMapper.writeValueAsString(RestResult.succeed(o));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (o instanceof RestResult) {
            //原有部分接口已经使用RestResponse包装过，防止重复包装。
            return o;
        }
        //统一数据包装，包括Oauth2的正常数据（Oauth2的异常数据已经直接），Oauth2的异常数据已经直接httpServletResponse.getWriter().write()不需要处理
//        return RestResult.succeed(o);
        return o;

    }


}
