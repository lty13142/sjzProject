package com.crcm.gateway.filter;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.core.utils.RestClientUtil;
import com.crcm.gateway.config.AuthException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;

/**
 * @ClassName Oauth2AuthorizationFilter
 * @Description oauth2 统一认证插件
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/7/4
 **/
@Slf4j
@Component
public class Oauth2AuthorizationFilter extends AbstractGatewayFilterFactory<Oauth2AuthorizationFilter.Config> {

    /**
     * 默认token请求头
     */
    private static final String DEFAULT_TOKEN_HEADER = "Authorization";
    private static final Integer DEFAULT__SUCCESS_CODE = 200;
    private static final String DEFAULT_DATA_KEY = "data";
    private static final String DEFAULT_REQ_TOKEN_KEY = "token";
    /**
     * 默认token前缀
     */
    private static final String DEFAULT_TOKEN_PREFIX = "Bearer_";

    private final static AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // 白名单不进行验证
            if (StrUtil.isNotBlank(config.getIgnoreUrls())) {
                for (String url : config.getIgnoreUrls().split(",")) {
                    if (ANT_PATH_MATCHER.match(url, request.getURI().getPath())) {
                        return chain.filter(exchange);
                    }
                }
            }
            String token = getToken(request, StrUtil.nullToDefault(config.getHeader(), DEFAULT_TOKEN_HEADER));
            Assert.isTrue(StrUtil.isNotBlank(token), () -> new AuthException(HttpStatus.UNAUTHORIZED.value(), "接口需要登陆后才能访问！"));

            if (StrUtil.isNotBlank(config.getPrefix())) {
                Assert.isTrue(token.startsWith(config.getPrefix()), () -> new AuthException(HttpStatus.UNAUTHORIZED.value(), "令牌不合法！"));
                token = token.replace(config.getPrefix(), "");
            }
            JSONObject userInfo = this.checkToken(config, token);
            ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
            for (String s : userInfo.keySet()) {
                Object val = userInfo.get(s);
                if (val instanceof String || val instanceof Number || val instanceof Boolean) {
                    mutate.header(s, String.valueOf(val));
                } else {
                    mutate.header(s, JSONObject.toJSONString(val));
                }
            }
            //给header里面添加 用户信息
            ServerHttpRequest tokenRequest = mutate.build();
            ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
            return chain.filter(build);
        };
    }

    private String getToken(ServerHttpRequest request, String tokenHeader) {
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(tokenHeader);
        if (StringUtils.isBlank(token)) {
            MultiValueMap<String, HttpCookie> cookies = request.getCookies();
            HttpCookie tokenCookie = cookies.getFirst(tokenHeader);
            token = tokenCookie == null ? "" : tokenCookie.getValue();
        }
        return token;
    }

    /**
     * 校验token，返回用户信息
     * @param config
     * @param token
     * @return
     */
    private JSONObject checkToken(Config config, String token) {
        // 启用权限缓存
//        if (config.enableCache) {
//
//        }
        Assert.notNull(config.getCheckTokenUri(), () -> new AuthException(HttpStatus.UNAUTHORIZED.value(), "Token校验URI不能为空！"));
        String dataKey = StrUtil.nullToDefault(config.getDataKey(), DEFAULT_DATA_KEY);
        Integer successCode = config.getSuccessCode() == null ? DEFAULT__SUCCESS_CODE : config.getSuccessCode();
        String reqTokenKey = StrUtil.nullToDefault(config.getReqTokenKey(), DEFAULT_REQ_TOKEN_KEY);
        HashMap<String, String> params = new HashMap<>();
        params.put(reqTokenKey, token);
        JSONObject result = RestClientUtil.doGet(config.getCheckTokenUri(), params, JSONObject.class);
        Assert.notNull(result, () -> new AuthException(HttpStatus.UNAUTHORIZED.value(), "鉴权失败，返回值不合法！"));
        String errorMsg = StrUtil.nullToDefault(result.getString("message"), JSON.toJSONString(result));
        Assert.isTrue(StrUtil.equals(result.getString("code"), String.valueOf(successCode)),
                () -> new AuthException(HttpStatus.UNAUTHORIZED.value(), errorMsg));
        return result.getJSONObject(dataKey);
    }


    @Data
    public static class Config {

        /**
         * token请求头
         */
        private String header;
        /**
         * 校验Token的地址
         */
        private String checkTokenUri;
        /**
         * token前缀
         */
        private String prefix;
        /**
         * 开发接口集合
         */
        private String ignoreUrls;
        /**
         * 是否启用权限缓存
         */
        private boolean enableCache;
        /**
         * 成功代码，用于判断是否鉴权成功
         */
        private Integer successCode;
        /**
         * 用户信息数据存放key
         */
        private String dataKey;
        /**
         * 鉴权时请求的token的key
         */
        private String reqTokenKey;
    }
}
