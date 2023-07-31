package com.zsgf.platform.security;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.utils.SM4Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 数据返回加密
 */
@Log4j2
@ControllerAdvice(basePackages = "com.zsgf.platform.controller")
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpHeaders headers = request.getHeaders();
        List<String> fromList = headers.get(AuthConstants.FROM);
        if (CollectionUtil.isNotEmpty(fromList)) {
            Iterator<String> iterator = fromList.iterator();
            while (iterator.hasNext()){
                String item = iterator.next();
                if (Objects.equals(item,AuthConstants.FROM_IN)){
                    return body;
                }
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        String data = SM4Util.encryptBase64(jsonObject.getString("data"));
        jsonObject.put("data",data);
        return jsonObject;
    }
}
