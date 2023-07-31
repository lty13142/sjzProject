package com.crcm.gateway.filter;

import cn.hutool.core.date.StopWatch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @ClassName TimerFilter
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/13
 **/
@Component
@Slf4j
@AllArgsConstructor
public class TimerFilter extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            StopWatch timer = new StopWatch();
            timer.start(exchange.getRequest().getURI().getRawPath());

            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                                timer.stop();
                                log.info(timer.prettyPrint());
                            }
                    )
            );
        };
    }
}
