package com.crcm.core.extend.encrypt;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @ClassName SecurityParameter
 * @Description 参数加密注解
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/10
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface SecurityParameter {

    /**
     * 入参是否解密，默认解密
     */
    boolean inDecode() default true;

    /**
     * 出参是否加密，默认加密
     */
    boolean outEncode() default true;
}