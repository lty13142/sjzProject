package com.crcm.cloud.start.gateway.filter;

import com.crcm.core.constant.SystemBaseConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName GatewayRequestFilter
 * @Description 网关限定过滤器，限制服务只能接收从网关来的请求
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/10
 **/
@Component
@Order(0)
public class GatewayRequestFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        byte[] token = Base64Utils.encode((SystemBaseConstants.GATEWAY_TOKEN_VALUE).getBytes());
        String[] headerValues = {new String(token)};
        ServerHttpRequest build = exchange.getRequest()
                .mutate()
                .header(SystemBaseConstants.GATEWAY_TOKEN_HEADER, headerValues)
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }

}