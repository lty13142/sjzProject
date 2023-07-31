package com.crcm.develop.core.conf.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.crcm.develop.core.domain.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>Title:自定义公共字段自动填充配置 </p>
 * <p>Description:  自定义公共字段自动填充配置</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 中再云图科技有限公司</p>
 *
 * @author diaoyunnie 2019-4-16
 * @version 1.0
 */
@Component
public class MybatisMateHandler implements MetaObjectHandler {

    /**
     * 插入操作：自动填充创建人、创建时间和修改时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取需要被填充的字段
        //创建人
        Object createUser = getFieldValByName("createBy", metaObject);
        //填充字段
        if (createUser == null) {
            setFieldValByName("createBy", UserContext.currentUserName(), metaObject);
        }
        //创建时间
        Object createTime = getFieldValByName("createTime", metaObject);
        //填充字段
        if (createTime == null) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        //修改时间
        Object updateTime = getFieldValByName("updateTime", metaObject);
        //填充字段
        if (updateTime == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        //乐观锁
        Object version = getFieldValByName("version", metaObject);
        //填充字段
        if (version == null) {
            setFieldValByName("version", 0, metaObject);
        }
        //逻辑删除
        Object deleted = getFieldValByName("deleted", metaObject);
        //填充字段
        if (deleted == null) {
            setFieldValByName("deleted", 0, metaObject);
        }
    }

    /***
     * 修改操作：自动填充修改人和修改时间
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //获取需要被填充的字段
        //最后修改人
//        Object updateUser = getFieldValByName("updateBy", metaObject);
        //填充字段
//        if (updateUser == null) {
            setFieldValByName("updateBy", UserContext.currentUserName(), metaObject);
//        }
        //修改时间
//        Object updateTime = getFieldValByName("updateTime", metaObject);
        //填充字段
//        if (updateTime == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
//        }
    }


}
