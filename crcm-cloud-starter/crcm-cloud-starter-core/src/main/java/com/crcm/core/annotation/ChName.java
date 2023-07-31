package com.crcm.core.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: ConstantName
 * @Description 自定义常量注解，用于为常量添加中文名称
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Administrator
 * @Date 2019/12/13
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})  //注解声明在常量和类之上
@Documented
public @interface ChName {
    String value();
}
