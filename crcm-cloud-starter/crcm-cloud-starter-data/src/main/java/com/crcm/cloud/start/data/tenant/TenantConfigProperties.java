package com.crcm.cloud.start.data.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TenantConfigProperties
 * @Description 多租户配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "mybatis-plus.tenant")
public class TenantConfigProperties {
    /**
     * 维护租户列名称
     */
    private String column = "tenant_id";

    /**
     * 多租户的数据表集合
     */
    private List<String> tables = new ArrayList<>();
}
