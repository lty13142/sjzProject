package com.crcm.core.extend.desensitize;

import com.crcm.core.extend.desensitize.annatation.Desensitized;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;

/**
 * @ClassName DesensitizedJsonSerializer
 * @Description 数据脱敏序列化器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/8/8
 **/
public class DesensitizedJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Desensitized desensitized;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DesensitizedUtils.desensitized(s, desensitized.strategy()));

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {

        desensitized = beanProperty.getAnnotation(Desensitized.class);

        if (!ObjectUtils.isEmpty(desensitized) && String.class.isAssignableFrom(beanProperty.getType().getRawClass())) {
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
}

