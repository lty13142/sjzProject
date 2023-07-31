package com.crcm.starter.cloud.swagger.components;

import com.github.xiaoymin.knife4j.core.enums.AnnotationClassEnums;
import com.github.xiaoymin.knife4j.core.enums.ApiRuleEnums;
import com.github.xiaoymin.knife4j.core.enums.PathRuleEnums;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.github.xiaoymin.knife4j.core.util.CommonUtils;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jInfoProperties;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.github.xiaoymin.knife4j.spring.model.docket.Knife4jDocketInfo;
import com.github.xiaoymin.knife4j.spring.util.RequestHandlerSelectorUtils;
import com.github.xiaoymin.knife4j.spring.util.SecurityDocketUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * @ClassName DocjDocketAutoRegistry
 * @Description Knife4j 接口分组自动注册bean
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
public class DocjDocketAutoRegistry implements BeanFactoryAware, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(com.github.xiaoymin.knife4j.spring.common.bean.Knife4jDocketAutoRegistry.class);
    private final Knife4jProperties knife4jProperties;
    private final OpenApiExtensionResolver openApiExtensionResolver;
    private BeanFactory beanFactory;

    public DocjDocketAutoRegistry(Knife4jProperties knife4jProperties, OpenApiExtensionResolver openApiExtensionResolver) {
        this.knife4jProperties = knife4jProperties;
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void afterPropertiesSet() throws Exception {
        Knife4jInfoProperties info = this.knife4jProperties.getOpenapi();
        if (info != null && CollectionUtils.isNotEmpty(info.getGroup())) {
            log.debug("Initialize docket information.");
            BeanDefinitionRegistry beanRegistry = (BeanDefinitionRegistry)this.beanFactory;
            ApiInfo apiInfo = (new ApiInfoBuilder()).title(info.getTitle()).description(info.getDescription()).version(info.getVersion()).license(info.getLicense()).licenseUrl(info.getLicenseUrl()).termsOfServiceUrl(info.getTermsOfServiceUrl()).contact(new Contact(info.getConcat(), info.getUrl(), info.getEmail())).build();

            String groupName;
            Docket docketBean;
            for(Iterator var4 = info.getGroup().entrySet().iterator(); var4.hasNext(); docketBean.extensions(this.openApiExtensionResolver.buildExtensions(groupName))) {
                Map.Entry<String, Knife4jDocketInfo> map = (Map.Entry)var4.next();
                String beanName = CommonUtils.getRandomBeanName((String)map.getKey());
                Knife4jDocketInfo docketInfo = (Knife4jDocketInfo)map.getValue();
                groupName = StrUtil.isNotBlank(docketInfo.getGroupName()) ? docketInfo.getGroupName() : (String)map.getKey();
                log.debug("Auto register Docket Bean,name:{}", beanName);
                BeanDefinition docketBeanDefinition = new GenericBeanDefinition();
                docketBeanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, DocumentationType.SWAGGER_2);
                docketBeanDefinition.setBeanClassName(Docket.class.getName());
                docketBeanDefinition.setRole(1);
                beanRegistry.registerBeanDefinition(beanName, docketBeanDefinition);
                docketBean = (Docket)this.beanFactory.getBean(beanName);
                docketBean.groupName(groupName).apiInfo(apiInfo);
                Predicate<RequestHandler> apiPredicate = RequestHandlerSelectors.any();
                Predicate<String> pathPredicate = PathSelectors.any();
                if (CollectionUtils.isNotEmpty(docketInfo.getApiRuleResources())) {
                    if (docketInfo.getApiRule() == ApiRuleEnums.PACKAGE) {
                        apiPredicate = RequestHandlerSelectorUtils.multiplePackage((String[])docketInfo.getApiRuleResources().toArray(new String[0]));
                    } else if (docketInfo.getApiRule() == ApiRuleEnums.ANNOTATION) {
                        List<String> annotationClass = AnnotationClassEnums.resolveResources(docketInfo.getApiRuleResources());
                        apiPredicate = RequestHandlerSelectorUtils.multipleAnnotations(annotationClass);
                    }
                }

                if (CollectionUtils.isNotEmpty(docketInfo.getPathRuleResources())) {
                    if (docketInfo.getPathRule() == PathRuleEnums.ANT) {
                        pathPredicate = RequestHandlerSelectorUtils.multipleAntPath(docketInfo.getPathRuleResources());
                    } else if (docketInfo.getPathRule() == PathRuleEnums.REGEX) {
                        pathPredicate = RequestHandlerSelectorUtils.multipleRegexPath(docketInfo.getPathRuleResources());
                    }
                }

                docketBean.select().apis(apiPredicate).paths(pathPredicate).build();
                if (docketInfo.getOauth2() != null) {
                    SecurityDocketUtils.configOAuth2(docketBean, docketInfo.getOauth2());
                }

                if (CollectionUtils.isNotEmpty(docketInfo.getBasicAuths())) {
                    SecurityDocketUtils.configCustomAuth(docketBean, docketInfo.getBasicAuths());
                }
            }
        }

    }
}
