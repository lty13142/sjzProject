package com.crcm.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName MultiRequestBodyArgumentResolver
 * @Description Controller中方法接收多个JSON对象，要在WebMvcConfig里面注册才能使用
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/15 9:57
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiRequestBody {
    /**
     * 是否必须出现的参数
     */
    boolean required() default true;

    /**
     * 当value的值或者参数名不匹配时，是否允许解析最外层属性到该对象
     */
    boolean parseAllFields() default true;

    /**
     * 解析时用到的JSON的key
     */
    String value() default "";

}
