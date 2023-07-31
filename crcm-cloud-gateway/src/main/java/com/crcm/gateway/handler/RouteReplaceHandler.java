package com.crcm.gateway.handler;

import com.crcm.core.response.RestResult;
import com.crcm.gateway.config.SentinelLimitRateConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @ClassName RouteReplaceHandler
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/9
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RouteReplaceHandler implements HandlerFunction<ServerResponse> {

    private final ApplicationEventPublisher publisher;

    @Resource
    private SentinelLimitRateConfig sentinelLimitRateConfig;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        //刷新限流规则
        sentinelLimitRateConfig.initGatewayRules();
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        RestResult.succeed("路由刷新成功!")
                ));
    }
}
