package com.crcm.cloud.start.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tianyu
 * redis分布式锁连接配置
 * @date 2023/4/28 11:24
 */
@Configuration
@Slf4j
public class RedissonConfig {


    private static final String ADDRESS_PREFIX = "redis://";

    private final RedisProperties redisProperties;

    @Autowired
    public RedissonConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean(value = "singleRedissonClient")
    public RedissonClient singleRedissonClient() {
        Config config = new Config();
        String url = ADDRESS_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(url);
        if (StringUtils.hasText(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec());
        log.info("----------- 创建redisson单节点连接 ----------");
        return Redisson.create(config);
    }

    @Bean(value = "clusterRedissonClient")
    public RedissonClient clusterRedissonClient() {
        List<String> clusterNodes = new ArrayList<>();
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        if (null != cluster) {
            List<String> nodes = cluster.getNodes();
            for (String node : nodes) {
                clusterNodes.add(ADDRESS_PREFIX + node);
            }
            Config config = new Config();
            ClusterServersConfig serversConfig = config.useClusterServers()
                    .addNodeAddress(clusterNodes.stream().toArray(String[]::new));
            if (StringUtils.hasText(redisProperties.getPassword())) {
                serversConfig.setPassword(redisProperties.getPassword());
            }
            log.info("----------- 创建redisson集群连接 ----------");
            return Redisson.create(config);
        }
        return null;
    }

}
