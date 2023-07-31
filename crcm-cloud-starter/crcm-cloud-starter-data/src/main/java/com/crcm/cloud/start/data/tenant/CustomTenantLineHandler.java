package com.crcm.cloud.start.data.tenant;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;

/**
 * @ClassName MybatisTenantInterceptor
 * @Description 自定义实现mybatis-plus租户维护器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@Slf4j
public class CustomTenantLineHandler implements TenantLineHandler {

    private TenantConfigProperties properties;

    public CustomTenantLineHandler(TenantConfigProperties properties) {
        this.properties = properties;
    }

    @Override
    public Expression getTenantId() {
        Integer tenantId = TenantContextHolder.getTenantId();
        log.debug("当前租户为 >> {}", tenantId);
        if (tenantId == null) {
            return new NullValue();
        }
        return new LongValue(tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return properties.getColumn();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        Integer tenantId = TenantContextHolder.getTenantId();
        // 租户中ID 为空，查询全部，不进行过滤
        if (tenantId == null) {
            return Boolean.TRUE;
        }
        return !properties.getTables().contains(tableName);
    }
}
