package com.crcm.cloud.start.resource.config;

import com.crcm.core.constant.AuthConstants;
import com.crcm.cloud.start.resource.annatation.EnableProjectResourceServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @ClassName ProjectSecurityBeanDefinitionRegistrar
 * @Description 动态注入资源服务器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Slf4j
public class ProjectSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 根据注解值动态注入资源服务器的相关属性
     *
     * @param metadata 注解信息
     * @param registry 注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(AuthConstants.RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置，覆盖默认配置:" + AuthConstants.RESOURCE_SERVER_CONFIGURER);
            return;
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ProjectResourceServerConfigurerAdapter.class);
        MutablePropertyValues mpv = new MutablePropertyValues();
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
                EnableProjectResourceServer.class.getName());
        Object details = annotationAttributes.get("details");
        mpv.add("details", details);
        beanDefinition.setPropertyValues(mpv);
        registry.registerBeanDefinition(AuthConstants.RESOURCE_SERVER_CONFIGURER, beanDefinition);

    }
}
