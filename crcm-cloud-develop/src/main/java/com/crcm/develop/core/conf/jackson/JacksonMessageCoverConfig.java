package com.crcm.develop.core.conf.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * @ClassName: JacksonMessageCoverConfig
 * @Description Jackson消息转换器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/6/11
 **/
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonMessageCoverConfig {
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.locale(Locale.CHINA)
					.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
					.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN)
					//序列化时的命名策略——驼峰命名法
					.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
					// 避免long类型序列化是精度损失
//					.serializerByType(Long.class, ToStringSerializer.instance)
//					.serializerByType(Long.TYPE, ToStringSerializer.instance);

			// 时间类序列化
			builder.modules(new ProjectJavaTimeModule());
		};
	}


	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
				.withSerializerModifier(new MyBeanSerializerModifier()));
		// 将String类型的pojo参数转换成null
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		SimpleModule module = new SimpleModule();
		module.addDeserializer(String.class, new StdDeserializer<String>(String.class) {
			@Override
			public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				String result = StringDeserializer.instance.deserialize(p, ctxt);
				if (StringUtils.isEmpty(result)) {
					return null;
				}
				return result;
			}
		});
		objectMapper.registerModule(module);
		return objectMapper;
	}

	/**
	 * 空数组值序列化处理
	 */
	public static class MyNullArrayJsonSerializer extends JsonSerializer {
		@Override
		public void serialize(Object value, JsonGenerator generator, SerializerProvider provider) throws IOException {
			if (value == null) {
				generator.writeStartArray();
				generator.writeEndArray();
			}
		}
	}


	/**
	 * 空字符串序列化处理
	 */
	public static class MyNullJsonSerializer extends JsonSerializer {
		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			jsonGenerator.writeString("");
		}
	}

	public static class MyBeanSerializerModifier extends BeanSerializerModifier {
		// 数组,集合类型 null -> []
		private JsonSerializer nullArrayJsonSerializer = new MyNullArrayJsonSerializer();
		// 字符串等类型 null -> ""
		private JsonSerializer nullJsonSerializer = new MyNullJsonSerializer();

		@Override
		public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
														 List beanProperties) {
			for (Object beanProperty : beanProperties) {
				BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
				//判断字段的类型，如果是array，list，set则注册nullSerializer
				if (isArrayType(writer)) {
					writer.assignNullSerializer(this.nullArrayJsonSerializer);
				} else {
					writer.assignNullSerializer(this.nullJsonSerializer);
				}
			}
			return beanProperties;
		}

		boolean isArrayType(BeanPropertyWriter writer) {
			Class clazz = writer.getPropertyType();
			return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);
		}
	}

}
