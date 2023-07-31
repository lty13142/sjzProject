package com.crcm.cloud.start.gateway.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName RouteDefinitionDTO
 * @Description 路由定义数据传输
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/9
 **/
@Data
public class RouteDefinitionDTO implements Serializable {
    /**
     * 路由名称
     */
    private String routeName;
    /**
     * 路由ID
     */
    private String id;
    /**
     * 路由断言
     */
    private String predicates;
    /**
     * 路由过滤器
     */
    private String filters;
    /**
     * 路由地址
     */
    private @NotNull String uri;
    /**
     * 元数据
     */
    private Map<String, Object> metadata;
    /**
     * 路由排序
     */
    private Integer order;

    /***
     * 限流
     */
    private String sentinelLimitRate;

    /***
     * 熔断
     */
    private String sentinelDegrade;
}
