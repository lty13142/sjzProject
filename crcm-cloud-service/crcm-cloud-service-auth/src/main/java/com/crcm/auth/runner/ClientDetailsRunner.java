package com.crcm.auth.runner;

import com.crcm.auth.service.impl.RedisClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName ClientDetailsRunner
 * @Description 系统客户端初始化器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/29
 **/
@Component
@RequiredArgsConstructor
public class ClientDetailsRunner implements ApplicationRunner {
    private final RedisClientDetailsService redisClientDetailsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisClientDetailsService.loadClientData();
    }
}
