package com.crcm.cloud.start.data.mybatis.components;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * @ClassName MapWrapperFactory
 * @Description 自定义的包装类,通过hasWrapperFor判断参数不为空,并且类型是Map的时候才使用自己扩展的ObjectWrapper
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/7/24
 **/
public class MapWrapperFactory implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new CustomWrapper(metaObject, (Map) object);
    }
}
