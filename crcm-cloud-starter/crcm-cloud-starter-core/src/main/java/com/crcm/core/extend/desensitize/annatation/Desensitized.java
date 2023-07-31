package com.crcm.core.extend.desensitize.annatation;

import com.crcm.core.extend.desensitize.DesensitizedJsonSerializer;
import com.crcm.core.extend.desensitize.enums.DesensitizedType;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

/**
 * @ClassName Desensitized
 * @Description 数据脱敏注解
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/8/8
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizedJsonSerializer.class)
public @interface Desensitized {

    DesensitizedType strategy() default DesensitizedType.NONE;

    /**
     * 是否使用dfa算法
     * @return
     */
    boolean useDFA() default false;

    /**
     * dfa敏感字符替换，默认替换成 "*"
     * @return
     */
    String dfaReplaceChar() default "*";


    /**
     * dfa敏感字符替换次数
     * @return
     */
    int dfaReplaceCharRepeatCount() default 1;

}
