package com.crcm.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @ClassName ReloadMessageConfig
 * @Description security 默认提示包语言配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/4
 **/
//@Configuration
public class ReloadMessageConfig {
    @Bean //加载中文认证提示信息
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //加载org/springframework/security包下的中文提示信息 配置文件
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return messageSource;
    }
}
