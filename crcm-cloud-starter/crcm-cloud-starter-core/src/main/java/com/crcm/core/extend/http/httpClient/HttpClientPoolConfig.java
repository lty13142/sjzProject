package com.crcm.core.extend.http.httpClient;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @ClassName: HttpClientPoolConfig
 * @Description HTTP连接池配置
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Diaoy
 * @Date 2019/9/17
 **/
@Data
@ConfigurationProperties(prefix = "spring.http-client.pool")
public class HttpClientPoolConfig {

    /**
     * java配置的优先级低于yml配置；如果yml配置不存在，会采用java配置
     */
    /**
     * #连接池的最大连接数，0代表不限；如果取0，需要考虑连接泄露导致系统崩溃的后果
     */
    private int maxTotalConnect = 1000;
    /**
     * #每个路由的最大连接数,如果只调用一个地址,可以将其设置为最大连接数
     */
    private int maxConnectPerRoute = 200;
    /**
     * 指客户端和服务器建立连接的超时时间,ms , 最大约21秒,因为内部tcp在进行三次握手建立连接时,默认tcp超时时间是20秒
     */
    private int connectTimeout = 10 * 1000;
    /**
     * 指客户端从服务器读取数据包的间隔超时时间,不是总读取时间，默认30s
     */
    private int readTimeout = 30 * 1000;

    private String charset = "UTF-8";
    /**
     * 重试次数,默认2次
     */
    private int retryTimes = 2;
    /**
     * 从连接池获取连接的超时时间,不宜过长,单位ms
     */
    private int connectionRequestTimout = 200;
    /**
     * 针对不同的网址,长连接保持的存活时间,单位s,如果是频繁而持续的请求,可以设置小一点,不建议设置过大,避免大量无用连接占用内存资源
     */
    private Map<String, Integer> keepAliveTargetHost;
    /**
     * 长连接保持时间 单位s,不宜过长
     */
    private int keepAliveTime = 60;

}