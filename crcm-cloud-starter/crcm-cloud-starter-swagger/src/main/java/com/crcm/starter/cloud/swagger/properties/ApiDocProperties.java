package com.crcm.starter.cloud.swagger.properties;

import com.github.xiaoymin.knife4j.core.model.MarkdownProperty;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jHttpBasic;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jInfoProperties;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jSetting;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

import java.util.List;

/**
 * @ClassName SwaggerDocProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
@Data
@ConfigurationProperties(prefix = "docs")
public class ApiDocProperties {

    private boolean enable = false;
    private ApiInfoProperties openapi;
    private boolean cors = false;
    private ApiDocHttpBasic basic;
    private boolean production = false;
    private ApiDocSetting setting;
    private List<MarkdownProperty> documents;

}
