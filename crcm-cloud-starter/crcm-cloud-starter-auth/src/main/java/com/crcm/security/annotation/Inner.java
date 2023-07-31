package com.crcm.security.annotation;

import java.lang.annotation.*;
/**
 * @ClassName Inner
 * @Description 内部服务注解，标识无需鉴权
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/2
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {
    /**
     * 是否AOP统一处理
     * @return
     */
    boolean value() default true;
}
