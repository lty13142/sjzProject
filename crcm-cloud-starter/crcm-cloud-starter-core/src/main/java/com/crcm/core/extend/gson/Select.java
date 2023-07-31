package com.crcm.core.extend.gson;

import java.lang.annotation.*;

/**
 * @ClassName Select
 * @Description 通过层级选择获取想要获得的字段内容
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/17
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Select {
    String value();
}
