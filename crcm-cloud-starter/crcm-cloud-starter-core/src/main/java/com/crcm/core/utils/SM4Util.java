package com.crcm.core.utils;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.commons.lang3.ObjectUtils;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName SM4Util
 * @Description SM4 加密
 * @Author GZL
 * @Date 2023/2/16 13:59
 * @Version 1.0
 **/
public class SM4Util {
    /**
     * key必须为16位
     */
    private final static String key = "sjzjgpt_20230403";

    private static SymmetricCrypto SM4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding", key.getBytes(StandardCharsets.UTF_8));

    /**
     * SM4 加密
     *
     * @param data
     * @return
     */
    public static String encryptBase64(String data) {
        if(ObjectUtils.isEmpty(data)){
            return "";
        }
        return SM4.encryptBase64(data);
    }

    /**
     * SM4 解密
     *
     * @param data
     * @return
     */
    public static String decryptStr(String data) {
        return SM4.decryptStr(data);
    }

}
