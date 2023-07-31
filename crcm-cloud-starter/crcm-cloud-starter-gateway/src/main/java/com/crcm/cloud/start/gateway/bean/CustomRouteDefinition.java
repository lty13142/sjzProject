package com.crcm.cloud.start.gateway.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName RouteDefinition
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/11
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomRouteDefinition extends RouteDefinition implements Serializable {
    private static final long serialVersionUID = 7811774178882208594L;

    /**
     * 路由名称
     */
    private String routeName;
}
