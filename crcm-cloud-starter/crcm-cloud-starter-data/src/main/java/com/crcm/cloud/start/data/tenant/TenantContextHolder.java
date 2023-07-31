package com.crcm.cloud.start.data.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @ClassName TenantContextHolder
 * @Description 租户ID维护器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@UtilityClass
public class TenantContextHolder {

    private final ThreadLocal<Integer> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();


    /**
     * TTL 设置租户ID
     *
     * @param tenantId
     */
    public void setTenantId(Integer tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return
     */
    public Integer getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}