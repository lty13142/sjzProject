package com.crcm.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;

/**
 * @ClassName JwtAuthorizationFilter
 * @Description JWT权限过滤器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/14
 **/
@Slf4j
@Component
public class JwtAuthorizationFilter extends AbstractGatewayFilterFactory<JwtAuthorizationFilter.Config> {

    /**
     * 默认token请求头
     */
    private static final String DEFAULT_TOKEN_HEADER = "Authorization";
    /**
     * 默认token前缀
     */
    private static final String DEFAULT_TOKEN_PREFIX = "Bearer_";

    private final static AntPathMatcher antPathMatcher = new AntPathMatcher();

    public JwtAuthorizationFilter() {
        super(JwtAuthorizationFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//
//            // 白名单不进行验证
//            if (StrUtil.isNotBlank(config.getIgnoreUrls())) {
//                for (String url : config.getIgnoreUrls().split(",")) {
//                    if (antPathMatcher.match(url, request.getURI().getPath())) {
//                        return chain.filter(exchange);
//                    }
//                }
//            }
//            String token = getJwtToken(request, StrUtil.nullToDefault(config.getHeader(), DEFAULT_TOKEN_HEADER));
//
//            Assert.isTrue(StrUtil.isNotBlank(token), () -> new TokenException(HttpStatus.UNAUTHORIZED.value(), "请登录后再进行请求！"));
//
//            if (StrUtil.isNotBlank(config.getPrefix())) {
//                Assert.isTrue(token.startsWith(config.getPrefix()), () -> new TokenException(HttpStatus.UNAUTHORIZED.value(), "token不合法！"));
//                token = token.replace(config.getPrefix(), "");
//            }
//            Claims claims = JWTUtils.parseJwt(token, config.getSecret());
//            Assert.notNull(claims, () -> new TokenException(HttpStatus.UNAUTHORIZED.value(), "登录过期，请重新登陆！"));
//
//            //给header里面添加 JWT解析出的权限信息
//            String base64 = Base64Utils.encodeToUrlSafeString(getParamsStr(claims).getBytes());
//            ServerHttpRequest tokenRequest = exchange.getRequest().mutate().header("json-token", base64).build();
//            ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
//            return chain.filter(build);
            return chain.filter(exchange);
        };
    }

//    private String getParamsStr(Claims claims) {
//        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
//        filter.getExcludes().add("iss");
//        filter.getExcludes().add("aud");
//        filter.getExcludes().add("exp");
//        filter.getExcludes().add("nbl");
//       return JSON.toJSONString(claims,filter);
//    }


    private String getJwtToken(ServerHttpRequest request, String tokenHeader) {
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(tokenHeader);
        if (StringUtils.isBlank(token)) {
            MultiValueMap<String, HttpCookie> cookies = request.getCookies();
            HttpCookie tokenCookie = cookies.getFirst(tokenHeader);
            token = tokenCookie == null ? "" : tokenCookie.getValue();
        }
        return token;
    }

    @Data
    public static class Config {

        /**
         * 加密密码
         */
        private String secret;
        /**
         * token请求头
         */
        private String header;
        /**
         * token前缀
         */
        private String prefix;
        /**
         * 开发接口集合
         */
        private String ignoreUrls;
    }
}
