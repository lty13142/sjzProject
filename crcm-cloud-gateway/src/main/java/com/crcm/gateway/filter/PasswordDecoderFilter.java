
package com.crcm.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.utils.RSAUtils;
import com.crcm.gateway.config.RsaProperties;
import io.netty.buffer.ByteBufAllocator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 密码解密工具类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {
    private static final String PASSWORD = "password";
    private static final String[] LOGIN_URLS = {AuthConstants.OAUTH_TOKEN_URL, "/user/login"};

    private final RsaProperties rsaProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!isLogin(request.getURI().getPath())) {
                return chain.filter(exchange);
            }
            if (RequestMethod.GET.equals(request.getMethodValue())) {
                URI uri = exchange.getRequest().getURI();
                String queryParam = uri.getRawQuery();
                Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);
                String password = paramMap.get(PASSWORD);
                if (StrUtil.isNotBlank(password) && this.isBase64(password)) {
                    try {
                        password = RSAUtils.decryptDataOnJava(password, rsaProperties.getPrivateKey());
                    } catch (Exception e) {
                        log.error("密码解密失败:{},{}", password, e.getMessage());
//					return Mono.error(e);
                    }
                    paramMap.put(PASSWORD, password.trim());
                }

                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(HttpUtil.toParams(paramMap))
                        .build(true)
                        .toUri();
                ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
                return chain.filter(exchange.mutate().request(newRequest).build());
            } else {
                //从请求里获取Post请求体
                String bodyStr = resolveBodyFromRequest(request);
                //TODO 得到Post请求的请求参数后，做你想做的事

                //下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
                URI uri = request.getURI();
                ServerHttpRequest newRequest = request.mutate().uri(uri).build();
                Flux<DataBuffer> bodyFlux = null;
                try {
                    DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
                    bodyFlux = Flux.just(bodyDataBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Flux<DataBuffer> finalBodyFlux = bodyFlux;
                newRequest = new ServerHttpRequestDecorator(newRequest) {
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return finalBodyFlux;
                    }
                };
                //封装request，传给下一级
                return chain.filter(exchange.mutate().request(newRequest).build());
            }


        };
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();

        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    /**
     * 判断是否是Base64,对于特定密码无法判断
     *
     * @param str
     * @return
     */
    private boolean isBase64(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.equals(Base64.encode(Base64.decode(str)));
        }
        return false;
    }

    /**
     * @param url
     * @return
     */
    private boolean isLogin(String url) {
        for (String loginUrl : LOGIN_URLS) {
            if (StrUtil.containsAnyIgnoreCase(url, loginUrl)) {
                return true;
            }
        }
        return false;
    }
}
