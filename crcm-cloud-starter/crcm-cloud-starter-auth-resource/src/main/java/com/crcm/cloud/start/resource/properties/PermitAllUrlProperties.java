package com.crcm.cloud.start.resource.properties;

import cn.hutool.core.util.ReUtil;
import com.crcm.security.annotation.Inner;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @ClassName PermitAllUrlProperties
 * @Description 资源服务器对外直接暴露URL,如果设置contex-path 要特殊处理
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/2
 **/
@Slf4j
@ConditionalOnExpression("!'${security.ignore.urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security.ignore")
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private ApplicationContext applicationContext;
    @Value("${server.servlet.context-path:''}")
    @Getter
    @Setter
    private String contextPath;
    @Getter
    @Setter
    private List<String> urls = new ArrayList<>();

    /**
     *  如果服务存在contextPath，将contextPath加在URL上
     * @return
     */
//    public List<String> getUrls() {
//        return urls.stream().map(url -> StrUtil.concat(true,contextPath,url)).collect(Collectors.toList());
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);
            // 获取方法上边的注解 替代path variable 为 *
            Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
            Optional.ofNullable(method).ifPresent(inner -> info.getPatternsCondition()
                    .getPatterns().forEach(url -> urls.add(ReUtil.replaceAll(url,PATTERN, "**"))));
            // 获取类上边的注解, 替代path variable 为 *
            Inner controller = AnnotationUtils.getAnnotation(handlerMethod.getBeanType(), Inner.class);
            Optional.ofNullable(controller).ifPresent(inner -> info.getPatternsCondition()
                    .getPatterns().forEach(url -> urls.add((ReUtil.replaceAll(url,PATTERN,"**")))));
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
