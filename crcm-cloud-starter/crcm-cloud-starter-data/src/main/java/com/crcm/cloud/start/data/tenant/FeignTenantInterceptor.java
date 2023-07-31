package com.crcm.cloud.start.data.tenant;

import com.crcm.core.constant.SystemBaseConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FeignTenantInterceptor
 * @Description feign请求拦截器，添加租户header
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@Slf4j
public class FeignTenantInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (TenantContextHolder.getTenantId() == null) {
            log.error("TTL 中的 租户ID为空，feign拦截器 >> 增强失败");
            return;
        }
        requestTemplate.header(SystemBaseConstants.TENANT_HEADER, TenantContextHolder.getTenantId().toString());
    }
}
