package com.crcm.cloud.start.redis.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RedisCacheKeyGenerator
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/22
 **/
public class RedisCacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object targetClass, Method method, Object... params) {
        // 这里可用HashMap
        Map<String,Object> map = new HashMap<String, Object>();
        Class targetClassClass = targetClass.getClass();
        // 类地址
        map.put("class",targetClassClass.toGenericString());
        // 方法名称
        map.put("methodName",method.getName());
        // 包名称
        map.put("package",targetClassClass.getPackage());
        // 参数列表
        for (int i = 0; i < params.length; i++) {
            map.put(String.valueOf(i),params[i]);
        }
        // 转为JSON字符串
        String jsonString = JSON.toJSONString(map);
        // 做SHA256 Hash计算，得到一个SHA256摘要作为Key
        return DigestUtils.sha256Hex(jsonString);
    }
}
