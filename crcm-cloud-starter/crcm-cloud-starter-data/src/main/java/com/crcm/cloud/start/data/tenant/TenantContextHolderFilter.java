package com.crcm.cloud.start.data.tenant;

import cn.hutool.core.util.StrUtil;
import com.crcm.core.constant.SystemBaseConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName TenantContextHolderFilter
 * @Description 租户过滤器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends GenericFilterBean {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String tenantId = request.getHeader(SystemBaseConstants.TENANT_HEADER);
        log.debug("获取header中的租户ID为:{}", tenantId);

        if (StrUtil.isNotBlank(tenantId)) {
            TenantContextHolder.setTenantId(Integer.parseInt(tenantId));
        } else {
            TenantContextHolder.setTenantId(SystemBaseConstants.TENANT_ID_1);
        }

        filterChain.doFilter(request, response);
        TenantContextHolder.clear();
    }
}
