package com.crcm.bpm.core.config;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.eventregistry.spring.SpringEventRegistryEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName ProcessEngineConfig
 * @Description：flowable 流程引擎配置
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/8/17/9:18
 **/
@Slf4j
@Configuration
public class ProcessEngineConfig {


    @Bean(name="flowableDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.flowable" )
    public DataSource flowableDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * spring-flowable 流程引擎配置
     * @return
     */
    @Bean
    @Primary
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSourceTransactionManager transactionManager) {
        SpringProcessEngineConfiguration conf = new SpringProcessEngineConfiguration();
        // 设置独立数据源
        conf.setDataSource(flowableDataSource());
        conf.setTransactionManager(transactionManager);
        conf.setDisableIdmEngine(true);
        conf.setEnableEventDispatcher(true);
        //不添加此项配置，在没创建表时，会抛出FlowableWrongDbException异常
        conf.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return conf;
    }

    /**
     * 事件服务引擎配置
     * @param transactionManager
     * @return
     */
    @Bean
    @Primary
    public SpringEventRegistryEngineConfiguration springEventRegistryEngineConfiguration(DataSourceTransactionManager transactionManager) {
        SpringEventRegistryEngineConfiguration conf = new SpringEventRegistryEngineConfiguration();
        // 设置独立数据源
        conf.setDataSource(flowableDataSource());
        conf.setTransactionManager(transactionManager);
        //conf.setDisableIdmEngine(true);
        conf.setEnableEventDispatcher(true);
        //不添加此项配置，在没创建表时，会抛出FlowableWrongDbException异常
        conf.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return conf;
    }

    /**
     * spring-flowable-app 流程引擎配置,没有该配置可能会导致表生成到主数据库
     * @return
     */
//    @Bean
//    @Primary
//    public SpringAppEngineConfiguration springAppEngineConfiguration() {
//        SpringAppEngineConfiguration conf = new SpringAppEngineConfiguration();
//        // 设置独立数据源
//        conf.setJdbcUrl(flowableDataSource.getUrl())
//                .setJdbcUsername(flowableDataSource.getUsername())
//                .setJdbcPassword(flowableDataSource.getPassword())
//                .setJdbcDriver(flowableDataSource.getDriverClassName());
//        conf.setTransactionManager(platformTransactionManager);
//       // conf.setDisableIdmEngine(true);
//        //不添加此项配置，在没创建表时，会抛出FlowableWrongDbException异常
//        conf.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        conf.setEnableEventDispatcher(true);
//        return conf;
//    }




}
