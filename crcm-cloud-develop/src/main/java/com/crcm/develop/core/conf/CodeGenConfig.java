package com.crcm.develop.core.conf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取代码生成相关配置
 *
 * @author zzyt
 */
@Setter
@Getter
@ToString
@Component
@ConfigurationProperties(prefix = "gen")
@PropertySource(value = {"classpath:generator.properties" })
public class CodeGenConfig {
    /**
     * 作者
     */
    private String author;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 自动去除表前缀，默认是true
     */
    private boolean autoRemovePre;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    private String tablePrefix;

    /**
     * 不会被生成的公共字段
     */
    private List<String> publicColumn;

    private String baseController;
    private String baseEntity;
    private String resultClass;



}
