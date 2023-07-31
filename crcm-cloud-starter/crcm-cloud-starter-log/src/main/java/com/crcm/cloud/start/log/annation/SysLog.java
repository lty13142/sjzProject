package com.crcm.cloud.start.log.annation;

import com.crcm.core.constant.enums.BusinessType;

import java.lang.annotation.*;

/**
 * @ClassName SysLog
 * @Description 系统日志注解
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     * @return {String}
     */
    String title();

    /**
     * 服务ID
     * @return
     */
    String serviceId() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;


}
