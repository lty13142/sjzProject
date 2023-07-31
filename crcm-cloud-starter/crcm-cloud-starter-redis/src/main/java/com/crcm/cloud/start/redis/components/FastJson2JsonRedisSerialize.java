package com.crcm.cloud.start.redis.components;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @ClassName FastJson2JsonRedisSerialize
 * @Description fastjson序列化器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/4/29
 **/

public class FastJson2JsonRedisSerialize<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 使用安全模式，禁用AutoType
        ParserConfig.getGlobalInstance().setSafeMode(true);
        //如果遇到反序列化autoType is not support错误，请添加并修改一下包名到bean文件路径
        //ParserConfig.getGlobalInstance().addAccept("com.example.redisdemo.domain");
    }

    public FastJson2JsonRedisSerialize(Class clazz){
        super();
        this.clazz = clazz;
    }


    /**
     * 序列化
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0){
            return null;
        }
        String str = new String(bytes,DEFAULT_CHARSET);
        return (T) JSON.parseObject(str,clazz);
    }

}
