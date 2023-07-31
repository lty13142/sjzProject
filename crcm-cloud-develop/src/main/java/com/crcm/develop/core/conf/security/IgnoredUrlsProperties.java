package com.crcm.develop.core.conf.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IgnoredUrlsProperties
 * @Description 权限过滤白名单，放置application.yml中权限过滤的URL
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/5 17:52
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "security.ignored")
public class IgnoredUrlsProperties {

    private List<String> urls = new ArrayList<>();
}