package com.crcm.gateway.filter;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestGatewayFilterFactory
 * @Description 测试网关过滤器工厂
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/13
 **/
@Slf4j
@Component
public class TestFilter extends AbstractGatewayFilterFactory<TestFilter.Config> {


    public TestFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info(JSON.toJSONString(config));
            String url = exchange.getRequest().getPath().pathWithinApplication().value();
            log.info("请求URL:" + url);
            log.info("method:" + exchange.getRequest().getMethod());
            //获取header
            String userId = exchange.getRequest().getHeaders().getFirst("user-id");
            log.info("userId：" + userId);
            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String name;
        private String ak;
        private String sk;
    }
}
