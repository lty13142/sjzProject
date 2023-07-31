package com.crcm.cloud.start.log.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import com.crcm.core.utils.IPUtil;
import com.crcm.core.utils.SpringContextHolder;
import lombok.experimental.UtilityClass;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName SysLogUtils
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@UtilityClass
public class SysLogUtils {

    private static final String START_CHART = "at ";
    private static final String END_CHART = "\n";

    public ReqLogSaveDTO getSysLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        ReqLogSaveDTO sysLog = new ReqLogSaveDTO();

        sysLog.setType(LogTypeEnum.NORMAL.getType());
        sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setMethod(request.getMethod());
        sysLog.setRequestMethod(request.getMethod());
        sysLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
        sysLog.setServiceId(getClientId(request));
        sysLog.setCreateTime(LocalDateTime.now());
        try {
            String ip = IPUtil.getRequestIp(request);
            Ip2regionSearcher regionSearcher = SpringContextHolder.getBean(Ip2regionSearcher.class);
            IpInfo ipInfo = regionSearcher.memorySearch(ip);
            sysLog.setRequestIp(ip);
            sysLog.setRequestAddr(ipInfo.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysLog;
    }

    /**
     * 获取客户端
     *
     * @return clientId
     */
    private String getClientId(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            BasicAuthenticationConverter basicAuthenticationConverter = new BasicAuthenticationConverter();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = basicAuthenticationConverter
                    .convert(request);
            if (usernamePasswordAuthenticationToken != null) {
                return usernamePasswordAuthenticationToken.getName();
            }
        }
        return null;
    }

    /**
     * 获取异常中的堆栈信息
     *
     * @param ex
     * @return
     */
    public String getStackTrace(Throwable ex) {
        StringBuffer stackTrace = new StringBuffer("");
        stackTrace.append(ex.toString() + END_CHART);
        stackTrace.append("----------------------------");
        //通过Throwable获得堆栈信息
        StackTraceElement[] stackElements = ex.getStackTrace();
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                stackTrace.append(START_CHART + stackElements[i].toString() + END_CHART);
            }
        }
        return stackTrace.toString();
    }

}