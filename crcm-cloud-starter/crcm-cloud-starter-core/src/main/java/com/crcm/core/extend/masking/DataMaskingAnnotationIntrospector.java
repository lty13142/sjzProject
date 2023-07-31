package com.crcm.core.extend.masking;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DataMaskingAnnotationIntrospector
 * @Description 自定义AnnotationIntrospector，适配我们自定义注解返回相应的Serializer
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/7/19
 **/
@Slf4j
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {
    @Override
    public Object findSerializer(Annotated am) {
        DataMasking annotation = am.getAnnotation(DataMasking.class);
        if (annotation != null) {
            return new DataMaskingSerializer(annotation.maskFunc().operation());
        }
        return null;
    }
}
