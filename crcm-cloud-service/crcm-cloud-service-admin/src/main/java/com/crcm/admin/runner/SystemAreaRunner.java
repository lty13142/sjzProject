package com.crcm.admin.runner;

import com.crcm.admin.config.event.SystemAreaRefreshEvent;
import com.crcm.admin.model.entity.SysArea;
import com.crcm.admin.service.SysAreaService;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @PackageName: com.crcm.admin.runner
 * @ClassName: SystemAreaRunner
 * @Author: cb
 * @Date: 2023-04-04 18:20
 * @Description:
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SystemAreaRunner {

	private final RedisService redisService;
	private final SysAreaService areaService;

	@Async
	@Order
	@EventListener({WebServerInitializedEvent.class, SystemAreaRefreshEvent.class})
	public void initDict() {
		long start = System.currentTimeMillis();
		Map<String,Object> resultMap = new HashMap<>();
		log.info("初始化系统区域缓存");

		List<SysArea> allArea = areaService.findAreas();
		if (CollectionUtils.isNotEmpty(allArea)){
			// 数据处理
			Map<String, List<SysArea>> collect = allArea.stream().collect(Collectors.groupingBy(SysArea::getType));
			collect.keySet().forEach(key->{
				resultMap.put(key,collect.get(key));
			});
		}
		redisService.del(SystemBaseConstants.REDIS_AREA_DATA);
		redisService.hmset(SystemBaseConstants.REDIS_AREA_DATA, resultMap);
		log.info("区域缓存加载完成,用时{}毫秒", (System.currentTimeMillis() - start));
	}
}
