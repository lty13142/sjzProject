package com.crcm.bpm.filter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 通过url过滤功能权限
 *
 * @author zzyt
 **/

public class UrlFilter implements Filter {

//    @Resource
//    private FeignAdminService feignAdminService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        List<Map<String, Object>> list = (List<Map<String, Object>>) feignAdminService.findByApiUrl(httpRequest.getRequestURI()).getData();
//        if (CollUtil.isNotEmpty(list)) {
//            checkPermissions(request, response, httpRequest.getRequestURI(),chain);
//        }else{
//            chain.doFilter(request, response);
//        }
        //TODO 对接鉴权接口
        chain.doFilter(request, response);
    }

//    private void checkPermissions(ServletRequest request, ServletResponse response, String newPath, FilterChain chain) throws ServletException, IOException {
//        Object data = feignAdminService.findCurrentEmpByPageMenu(null, newPath).getData();
//        if (data.equals(0)) {
//            request.getRequestDispatcher("/bpmFilter/exception").forward(request, response);
//        }else if(data.equals(1)){
//            chain.doFilter(request, response);
//        }
//    }
}
