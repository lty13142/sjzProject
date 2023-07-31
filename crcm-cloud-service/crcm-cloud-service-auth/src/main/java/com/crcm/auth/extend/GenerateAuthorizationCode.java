package com.crcm.auth.extend;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

/**
 * @ClassName GenerateAuthorizationCode
 * @Description 设置为线程安全的随机数
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class GenerateAuthorizationCode {
    /**
     * 设置为线程安全
     */
    public static ThreadLocal<RandomValueStringGenerator> generatorThreadLocal = ThreadLocal.withInitial(() -> new RandomValueStringGenerator(12));
}
