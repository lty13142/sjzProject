package com.crcm.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.start.gateway.dto.RouteDefinitionDTO;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.gateway.handler.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 限流规则配置类
 *
 * @author ezhang
 */

@Configuration
public class SentinelLimitRateConfig {

    @Resource
    private RedisTemplate redisTemplate;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
        return new SentinelFallbackHandler();
    }

    @Resource
    @Order(-1)
    private SentinelGatewayFilter sentinelGatewayFilter;

    @PostConstruct
    public void doInit() {
        // 加载网关限流规则
        initGatewayRules();
    }

    /**
     * 网关限流规则
     */
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        //获取路由
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionDTO.class));
        List<RouteDefinitionDTO> routes = redisTemplate.opsForHash().values(SystemBaseConstants.ROUTE_KEY);
        //设置默认值（先暂定1s中100请求）
        int defaultCount = 100;
        int defaultIntervalSec = 1;
        routes.forEach(route->{
            if(route.getSentinelLimitRate()==null){
                return;
            }
            JSONArray sentinelLimitRateList = JSONObject.parseObject(route.getSentinelLimitRate(), JSONArray.class);
            Integer count=defaultCount;
            Integer intervalSec = defaultIntervalSec;
            for (Object sentinelLimitRate : sentinelLimitRateList) {
                JSONObject sentinelLimitRateObj = (JSONObject) sentinelLimitRate;

                switch (sentinelLimitRateObj.getString("key")){
                    case "count":
                        count = sentinelLimitRateObj.getInteger("val");
                        break;
                    case "intervalSec":
                        intervalSec = sentinelLimitRateObj.getInteger("val");
                        break;
                }
            }
            rules.add(new GatewayFlowRule(route.getId())
                    .setCount(count)    // 限流阈值
                    .setIntervalSec(intervalSec));   // 统计时间窗口，单位是秒，默认是 1 秒
        });
        // 加载网关限流规则
        GatewayRuleManager.loadRules(rules);
    }
}
