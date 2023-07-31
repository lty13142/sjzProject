package com.crcm.starter.cloud.swagger;

import com.crcm.starter.cloud.swagger.properties.ApiDocProperties;
import com.github.xiaoymin.knife4j.core.conf.GlobalConstants;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import com.github.xiaoymin.knife4j.spring.filter.SecurityBasicAuthFilter;
import com.github.xiaoymin.knife4j.spring.util.EnvironmentUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;

/**
 * @ClassName Knife4jConfigurationAdapter
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
@Slf4j
@EnableKnife4j
@EnableSwagger2WebMvc
@EnableConfigurationProperties({ApiDocProperties.class})
@RequiredArgsConstructor
public class Knife4jConfigurationAdapter {

    private final Environment environment;
    private final ApiDocProperties docProperties;

    private static final String[] DISABLE_PROFILES = {"prod"};

    /**
     * 当前激活的环境
     */
    @Value("spring.profiles.active:''")
    private String profiles;

    @Bean({"docsCorsFilter"})
    @ConditionalOnMissingBean({CorsFilter.class})
    @ConditionalOnProperty(
            name = {"docs.cors"},
            havingValue = "true"
    )
    public CorsFilter corsFilter() {
        log.info("init CorsFilter...");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfiguration.setMaxAge(GlobalConstants.CORS_MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    @Bean
    @ConditionalOnMissingBean({SecurityBasicAuthFilter.class})
    @ConditionalOnProperty(
            name = {"docs.basic.enable"},
            havingValue = "true"
    )
    public SecurityBasicAuthFilter securityBasicAuthFilter(ApiDocProperties apiDocProperties) {
        SecurityBasicAuthFilter securityBasicAuthFilter = null;
        if (apiDocProperties == null) {
            securityBasicAuthFilter = new SecurityBasicAuthFilter(EnvironmentUtils.resolveBool(this.environment, "docs.basic.enable",
                    Boolean.FALSE), EnvironmentUtils.resolveString(this.environment, "docs.basic.username", "admin"),
                    EnvironmentUtils.resolveString(this.environment, "docs.basic.password", "123321"));
        } else if (apiDocProperties.getBasic() == null) {
            securityBasicAuthFilter = new SecurityBasicAuthFilter(Boolean.FALSE, "admin", "123321");
        } else {
            securityBasicAuthFilter = new SecurityBasicAuthFilter(apiDocProperties.getBasic().isEnable(),
                    apiDocProperties.getBasic().getUsername(), apiDocProperties.getBasic().getPassword());
        }

        return securityBasicAuthFilter;
    }


/*    @Bean
    @Qualifier("swaggerDocketAutoRegistry")
    public DocjDocketAutoRegistry knife4jDocketAutoRegistry(Knife4jProperties knife4jProperties, OpenApiExtensionResolver openApiExtensionResolver) {
        return new DocjDocketAutoRegistry(knife4jProperties, openApiExtensionResolver);
    }*/

    /**
     * 创建Docket存入容器，Docket代表一个接口文档
     *
     * @return
     */
    @Bean
    public Docket defaultWebApiConfig() {
        boolean active = docProperties.isEnable();
        // 生产环境强制关闭，避免接口暴露
        if (active && Arrays.stream(DISABLE_PROFILES).anyMatch(e -> e.equals(profiles))) {
            active = false;
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(active)
                // 创建接口文档的具体信息
                .apiInfo(webApiInfo())
                // 创建选择器，控制哪些接口被加入文档
                .select()
                // 指定@ApiOperation标注的接口被加入文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    /**
     * 创建接口文档的具体信息，会显示在接口文档页面中
     *
     * @return
     */
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title(docProperties.getOpenapi().getTitle())
                // 文档简介
                .description(docProperties.getOpenapi().getDescription())
                // 版本
                .version(docProperties.getOpenapi().getVersion())
                // 联系人信息
                .contact(docProperties.getOpenapi().getContact())
                // 团队服务地址
                .termsOfServiceUrl(docProperties.getOpenapi().getTermsOfServiceUrl())
                // 版权
                .license(docProperties.getOpenapi().getLicense())
                // 版权地址
                .licenseUrl(docProperties.getOpenapi().getLicenseUrl())
                .build();
    }
}
