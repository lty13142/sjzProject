
package com.crcm.gateway.config;

import com.crcm.gateway.handler.HystrixFallbackHandler;
import com.crcm.gateway.handler.ImageCodeHandler;
import com.crcm.gateway.handler.RouteReplaceHandler;
import com.crcm.gateway.handler.RsaPublicKeyHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * 路由配置信息
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfiguration {
    private final HystrixFallbackHandler hystrixFallbackHandler;
    private final ImageCodeHandler imageCodeHandler;
    private final RsaPublicKeyHandler rsaPublicKeyHandler;

    private final RouteReplaceHandler routeReplaceHandler;
    @Value("${server.servlet.context-path:}")
    private String contentPath;

    @Bean
    public RouterFunction routerFunction() {
        String basePath = StringUtils.isBlank(contentPath) ? "" : contentPath;
        return RouterFunctions.route(
                        RequestPredicates.path(basePath + "/fallback")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET(basePath + "/code")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler)
                .andRoute(RequestPredicates.GET(basePath + "/pubkey")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), rsaPublicKeyHandler)
                .andRoute(RequestPredicates.GET(basePath + "/replaceRoute")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), routeReplaceHandler)
                ;

    }

}
