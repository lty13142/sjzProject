package com.crcm.security.gateway;

import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.utils.ResponseUtil;
import com.crcm.core.response.RestResult;
import io.micrometer.core.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ServerProtectInterceptor
 * @Description 请求拦截器，用于校验请求是否经过网关
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/10
 **/
@Slf4j
public class ServerProtectInterceptor implements HandlerInterceptor {

    private CloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler){

        if (!properties.getGatewayFetchOnly()) {
            return true;
        }

        // 不限制feign内部调用的接口
        String FROM_INNER = request.getHeader(AuthConstants.FROM);
        if (StringUtils.equals(FROM_INNER,AuthConstants.FROM_IN)) {
            return true;
        }

        String token = request.getHeader(SystemBaseConstants.GATEWAY_TOKEN_HEADER);

        String gatewayToken = new String(Base64Utils.encode(SystemBaseConstants.GATEWAY_TOKEN_VALUE.getBytes()));
        log.info("-----------" + gatewayToken + "-----------");
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            RestResult<String> resultData = new RestResult<>();
            resultData.setSuccess(false);
            resultData.setCode(HttpServletResponse.SC_FORBIDDEN);
            resultData.setMessage("请通过网关访问资源");
            ResponseUtil.write(response,resultData);
            return false;
        }
    }

    public void setProperties(CloudSecurityProperties properties) {
        this.properties = properties;
    }
}
