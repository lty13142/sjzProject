package com.crcm.starter.cloud.swagger.properties;

import com.github.xiaoymin.knife4j.spring.model.docket.Knife4jDocketInfo;
import lombok.Data;
import springfox.documentation.service.Contact;

import java.util.Map;

/**
 * @ClassName ApiInfoProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
@Data
public class ApiInfoProperties {
    private String title;
    private String description;
    private String email;
    private String url;
    private Contact contact;
    private String version;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private Map<String, Knife4jDocketInfo> group;
}
