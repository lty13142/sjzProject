package com.crcm.cloud.start.data.mybatis.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>Title:自定义公共字段自动填充配置 </p>
 * <p>Description:  自定义公共字段自动填充配置</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 中再云图科技有限公司</p>
 *
 * @author diaoyunnie 2020.8.18
 * @version 2.0
 */
@Component
public class MybatisMateHandler implements MetaObjectHandler {

    private static final String CREATE_BY_KEY = "createBy";
    private static final String CREATE_TIME_KEY = "createTime";
    private static final String UPDATE_BY_KEY = "updateBy";
    private static final String UPDATE_TIME_KEY = "updateTime";
    private static final String VERSION_KEY = "version";
    private static final String DELETED_KEY = "deleted";

    /**
     * 插入操作：自动填充创建人、创建时间和修改时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //自动填充创建人
        if (metaObject.hasSetter(CREATE_BY_KEY) && null == getFieldValByName(CREATE_BY_KEY, metaObject)) {
            try {
                setFieldValByName(CREATE_BY_KEY, this.getCurrentUsername(), metaObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //自动填充创建时间
        if (metaObject.hasSetter(CREATE_TIME_KEY) && null == getFieldValByName(CREATE_TIME_KEY, metaObject)) {
            setFieldValByName(CREATE_TIME_KEY, LocalDateTime.now(), metaObject);
        }
        //自动填充修改时间
        if (metaObject.hasSetter(UPDATE_TIME_KEY) && null == getFieldValByName(UPDATE_TIME_KEY, metaObject)) {
            setFieldValByName(UPDATE_TIME_KEY, LocalDateTime.now(), metaObject);
        }
        //初始化乐观锁
        if (metaObject.hasSetter(VERSION_KEY) && null == getFieldValByName(VERSION_KEY, metaObject)) {
            setFieldValByName(VERSION_KEY, 0, metaObject);
        }
        //初始化逻辑删除
        if (metaObject.hasSetter(DELETED_KEY) && null == getFieldValByName(DELETED_KEY, metaObject)) {
            setFieldValByName(DELETED_KEY, 0, metaObject);
        }
    }

    /***
     * 修改操作：自动填充修改人和修改时间
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //自动填充修改人
        if (metaObject.hasSetter(UPDATE_BY_KEY)) {
            try {
                setFieldValByName(UPDATE_BY_KEY, this.getCurrentUsername(), metaObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //自动填充修改时间
        if (metaObject.hasSetter(UPDATE_TIME_KEY)) {
            setFieldValByName(UPDATE_TIME_KEY, LocalDateTime.now(), metaObject);
        }
    }


    /**
     * 获取当前用户名称
     *
     * @return String 用户名
     */
    private String getCurrentUsername() {
        try {
            Object principal = Objects.requireNonNull(getAuthentication()).getPrincipal();
            if (principal == null) {
                return null;
            }
            if (principal instanceof UserDetails) {
                JSONObject object = JSON.parseObject(JSON.toJSONString(principal));
                return object.getString("username");
            }
            return (String) principal;
        } catch (Exception e) {
        }
        return null;
    }

    private Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() != null) {
            return SecurityContextHolder.getContext().getAuthentication();
        }
        return null;
    }

}