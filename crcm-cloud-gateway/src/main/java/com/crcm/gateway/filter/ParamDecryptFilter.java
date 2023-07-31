package com.crcm.gateway.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.RSAUtils;
import com.crcm.gateway.config.RsaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

/**
 * @ClassName ParamDecryptFilter
 * @Description 网关参数解密过滤器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/16
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ParamDecryptFilter extends AbstractGatewayFilterFactory {
    private final RsaProperties rsaProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put("startTime", System.currentTimeMillis());
            // 未开启参数加密则跳过
            if (!rsaProperties.getEnable()) {
                return returnMono(chain, exchange);
            }
            if (exchange.getRequest().getMethod().equals(HttpMethod.POST)) {
                //重新构造request，参考ModifyRequestBodyGatewayFilterFactory
                ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
                MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
                //重点
                Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
                    //因为约定了终端传参的格式，所以只考虑json的情况，如果是表单传参，请自行发挥
                    if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType) || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(mediaType)) {
                        JSONObject jsonObject = JSON.parseObject(body, JSONObject.class);
                        String paramStr = jsonObject.getString("param");
                        String newBody;
                        try {
                            newBody = verifySignature(paramStr);
                        } catch (Exception e) {
                            return processError(e.getMessage());
                        }
                        return Mono.just(newBody);
                    }
                    return Mono.empty();
                });
                BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(exchange.getRequest().getHeaders());
                headers.remove("Content-Length");
                CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
                return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
                    ServerHttpRequest decorator = this.decorate(exchange, headers, outputMessage);
                    return returnMono(chain, exchange.mutate().request(decorator).build());
                }));
            } else {
                //GET 验签
                MultiValueMap<String, String> map = exchange.getRequest().getQueryParams();
                if (!CollectionUtil.isEmpty(map)) {
                    String paramStr = map.getFirst("param");
                    try {
                        verifySignature(paramStr);
                    } catch (Exception e) {
                        return processError(e.getMessage());
                    }
                }
                return returnMono(chain, exchange);
            }
        };
    }

    private Mono<Void> returnMono(GatewayFilterChain chain, ServerWebExchange exchange) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute("startTime");
            if (startTime != null) {
                long executeTime = (System.currentTimeMillis() - startTime);
                URI uri = exchange.getRequest().getURI();
                log.debug("请求地址:{},状态码:{},总耗时:{} 毫秒", uri.toString()
                        , Objects.requireNonNull(exchange.getResponse().getStatusCode()).value(), executeTime);
            }
        }));
    }

    private String verifySignature(String paramStr) throws Exception {
        log.debug("密文{}", paramStr);
        String dParamStr;
        try {
            dParamStr = RSAUtils.decryptDataOnJava(paramStr, rsaProperties.getPrivateKey());
        } catch (Exception e) {
            throw new CustomException("解密失败！");
        }
        log.debug("解密得到字符串{}", dParamStr);
        String signature = RSAUtils.sign(dParamStr.getBytes(), rsaProperties.getPrivateKey());
        log.debug("重新加密得到签名{}", signature);
        JSONObject jsonObject1 = JSON.parseObject(dParamStr);
        if (!jsonObject1.getString("signature").equals(signature)) {
            throw new CustomException("签名不匹配！");
        }
        return jsonObject1.toJSONString();
    }

    private Mono processError(String message) {
  /*exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
  return exchange.getResponse().setComplete();*/
        log.error(message);
        return Mono.error(new CustomException(message));
    }

    ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0L) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set("Transfer-Encoding", "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }


}
