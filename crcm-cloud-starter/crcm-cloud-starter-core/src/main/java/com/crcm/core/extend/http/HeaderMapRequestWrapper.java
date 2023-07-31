package com.crcm.core.extend.http;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @ClassName HeaderMapRequestWrapper
 * @Description 自定义可修改header的requestWrapper
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/29
 **/
@Slf4j
public class HeaderMapRequestWrapper  extends HttpServletRequestWrapper {
    /**
     * construct a wrapper for this request
     *
     * @param request
     */
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private Map<String, String> headerMap = new HashMap<>();

    /**
     * add a header with given name and value
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        log.debug("getHeader --->{}",name);
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }

    /**
     * get the Header names
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : headerMap.keySet()) {
            names.add(name);
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        log.debug("getHeaders --->>>>>>{}",name);
        List<String> values = Collections.list(super.getHeaders(name));
        log.debug("getHeaders --->>>>>>{}",values);
        if (headerMap.containsKey(name)) {
            log.debug("getHeaders --->{}",headerMap.get(name));
            values = Arrays.asList(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }
}
