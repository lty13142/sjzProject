package com.crcm.cloud.start.data.annatation;

import com.crcm.cloud.start.data.mybatis.config.MybatisAutoConfigurationAdapter;
import com.crcm.cloud.start.data.mybatis.handler.MybatisMateHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableProjectMybatis
 * @Description mybatis配置启动器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/22
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MybatisAutoConfigurationAdapter.class, MybatisMateHandler.class})
public @interface EnableProjectMybatis {
}
