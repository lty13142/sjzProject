package com.crcm.gateway.handler;

import com.crcm.core.response.RestResult;
import com.crcm.gateway.config.RsaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @ClassName RsaPublicKeyHandler
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/15
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RsaPublicKeyHandler implements HandlerFunction<ServerResponse> {

    private static String publicKey;
    private final RsaProperties properties;

    @Autowired
    @PostConstruct
    private void init() {
        publicKey = properties.getPublicKey();
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        RestResult.succeed(publicKey)
                ));
    }
}
