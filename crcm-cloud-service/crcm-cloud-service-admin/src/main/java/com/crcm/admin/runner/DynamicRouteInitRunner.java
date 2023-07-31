
package com.crcm.admin.runner;

import com.crcm.admin.service.SysGatewayRouteService;
import com.crcm.cloud.start.gateway.dto.RouteDefinitionDTO;
import com.crcm.cloud.start.gateway.support.DynamicRouteInitEvent;
import com.crcm.core.constant.SystemBaseConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 * 容器启动后保存配置文件里面的路由信息到Redis
 * @author ZZYT
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicRouteInitRunner {
	private final RedisTemplate redisTemplate;

	private final SysGatewayRouteService routeConfService;

	@Async
	@Order
	@EventListener({WebServerInitializedEvent.class, DynamicRouteInitEvent.class})
	public void initRoute() {
		Boolean result = redisTemplate.delete(SystemBaseConstants.ROUTE_KEY);
		log.info("初始化网关路由 {} ", result);

		routeConfService.routes().forEach(route -> {
			RouteDefinitionDTO dto = new RouteDefinitionDTO();
			dto.setRouteName(route.getRouteName());
			dto.setId(route.getRouteCode());
			dto.setFilters(route.getRouteFilters());
			dto.setPredicates(route.getRoutePredicates());
			dto.setUri(route.getRouteUri());
			dto.setOrder(route.getRouteOrder());
			log.info("加载路由ID：{},{}", route.getRouteCode(), dto);
			redisTemplate.opsForHash().put(SystemBaseConstants.ROUTE_KEY, route.getRouteCode(), dto);
		});
		log.info("初始化网关路由结束 ");
	}
}
