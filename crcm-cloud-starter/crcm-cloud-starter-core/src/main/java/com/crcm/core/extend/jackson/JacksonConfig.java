package com.crcm.core.extend.jackson;

import cn.hutool.core.date.DateUtil;
import com.crcm.core.extend.masking.DataMaskingAnnotationIntrospector;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName JacksonConfig
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/4/13
 **/
@Configuration
public class JacksonConfig {

    /**
     * DateTime格式化字符串
     */
    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * Date格式化字符串
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * Time格式化字符串
     */
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(objectMapper());
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 不序列化null的属性
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        // 反序列化时忽略不存在的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 反序列化时将空字符串转为NULL
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);

        JavaTimeModule module = new JavaTimeModule();
        //日期序列化、反序列化
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN)));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN)));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN)));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN)));
        objectMapper.registerModule(module).registerModule(new ParameterNamesModule());
        // 设置日期格式化
        objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_PATTERN){
            @Override
            public Date parse(String source) throws ParseException {
                return DateUtil.parse(source);
            }
        });

        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object paramT, JsonGenerator gen,
                                  SerializerProvider paramSerializerProvider) throws IOException {
                String fieldName = gen.getOutputContext().getCurrentName();
                try {
                    //反射获取字段类型
                    Field field = gen.getCurrentValue().getClass().getDeclaredField(fieldName);
                    if (Objects.equals(field.getType(), String.class)) {
                        //字符串型空值""
                        gen.writeString("");
                        return;
                    } else if (Objects.equals(field.getType(), List.class)) {
                        //列表型空值返回[]
                        gen.writeStartArray();
                        gen.writeEndArray();
                        return;
                    } else if (Objects.equals(field.getType(), Map.class)) {
                        //map型空值返回{}
                        gen.writeStartObject();
                        gen.writeEndObject();
                        return;
                    }
                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
                }
                //默认返回""
                gen.writeString("");
            }
        });

        // 添加数据脱敏注解支持
        AnnotationIntrospector ai = objectMapper.getSerializationConfig().getAnnotationIntrospector();
        AnnotationIntrospector newAi = AnnotationIntrospectorPair.pair(ai, new DataMaskingAnnotationIntrospector());
        objectMapper.setAnnotationIntrospector(newAi);

        return objectMapper;
    }
}
