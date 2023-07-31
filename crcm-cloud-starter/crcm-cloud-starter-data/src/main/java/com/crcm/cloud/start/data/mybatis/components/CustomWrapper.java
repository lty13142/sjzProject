package com.crcm.cloud.start.data.mybatis.components;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @ClassName MapWrapperFactory
 * @Description 自定义的ObjectWrapper,通过useCamelCaseMapping来判断是否开启使用驼峰
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/7/24
 **/
public class CustomWrapper extends MapWrapper {

    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
        }
        return name;
    }
}
