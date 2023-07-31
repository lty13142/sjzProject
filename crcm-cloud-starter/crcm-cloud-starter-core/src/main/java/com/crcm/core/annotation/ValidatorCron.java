package com.crcm.core.annotation;


import com.crcm.core.utils.CronValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName: ValidatorCron
 * @Description cron表达式验证
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Administrator
 * @Date 2020/1/7
 **/
@Documented
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CronValidator.class)
public @interface ValidatorCron {

    //出现错误返回的信息
    String message() default "cron表达式格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
