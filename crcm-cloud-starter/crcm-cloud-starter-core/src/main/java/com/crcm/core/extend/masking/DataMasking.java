package com.crcm.core.extend.masking;

import java.lang.annotation.*;

/**
 * @ClassName DataMasking
 * @Description 数据脱敏注解
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/7/19
 **/
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {

    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;

}
