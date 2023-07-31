package com.crcm.cloud.start.gateway.support;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.start.gateway.bean.CustomRouteDefinition;
import com.crcm.cloud.start.gateway.dto.RouteDefinitionDTO;
import com.crcm.core.constant.SystemBaseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName RedisRouteDefinitionWriter
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/8
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private final RedisTemplate redisTemplate;

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            CustomRouteDefinition vo = new CustomRouteDefinition();
            BeanUtils.copyProperties(r, vo);
            log.info("保存路由信息{}", vo);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForHash().put(SystemBaseConstants.ROUTE_KEY, r.getId(), vo);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        routeId.subscribe(id -> {
            log.info("删除路由信息{}", id);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForHash().delete(SystemBaseConstants.ROUTE_KEY, id);
        });
        return Mono.empty();
    }


    /**
     * 动态路由入口
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionDTO.class));
        List<RouteDefinitionDTO> routes = redisTemplate.opsForHash().values(SystemBaseConstants.ROUTE_KEY);
        List<RouteDefinition> definitionList = new ArrayList<>();
        routes.forEach(route -> {
            try {
                CustomRouteDefinition routeDefinition = new CustomRouteDefinition();
                routeDefinition.setUri(URI.create(route.getUri()));
                routeDefinition.setId(route.getId());
                routeDefinition.setOrder(route.getOrder());
                routeDefinition.setFilters(formatFilters(route.getFilters()));
                routeDefinition.setPredicates(formatRoute(route.getPredicates()));
                routeDefinition.setMetadata(MapUtil.emptyIfNull(route.getMetadata()));
                routeDefinition.setRouteName(route.getRouteName());
                definitionList.add(routeDefinition);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        log.debug("加载系统路由条数： {}， 实例为：{}", definitionList.size(), definitionList);
        return Flux.fromIterable(definitionList);
    }

    private List<PredicateDefinition> formatRoute(String predicatesStr) {
        List<PredicateDefinition> routeDefinitions = new ArrayList<>();
        List<JSONObject> predicateList = JSON.parseArray(predicatesStr, JSONObject.class);
        if (CollectionUtil.isNotEmpty(predicateList)) {
            predicateList.forEach(p -> {
                String args = p.getString("args");
                if (JSONUtil.isJson(args)) {
                    routeDefinitions.add(JSON.toJavaObject(p, PredicateDefinition.class));
                } else {
                    String predicates = p.getString("name") + "=" + p.getString("args");
                    routeDefinitions.add(new PredicateDefinition(predicates));
                }

            });
        }
        return routeDefinitions;
    }

    private List<FilterDefinition> formatFilters(String filtersStr) {
        List<FilterDefinition> filterDefinitions = new ArrayList<>();
        List<JSONObject> filterList = JSON.parseArray(filtersStr, JSONObject.class);
        if (CollectionUtil.isNotEmpty(filterList)) {
            CollectionUtil.sort(filterList, Comparator.comparing(o -> Convert.toInt(o.get("order"), 0)));
            filterList.forEach(f -> {
                String args = f.getString("args");
                if (JSONUtil.isJson(args)) {
                    filterDefinitions.add(JSON.toJavaObject(f, FilterDefinition.class));
                } else {
                    String predicates = f.getString("name") + "=" + f.getString("args");
                    filterDefinitions.add(new FilterDefinition(predicates));
                }
            });
        }

        return filterDefinitions;
    }
}
