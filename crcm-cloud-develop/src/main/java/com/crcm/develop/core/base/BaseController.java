package com.crcm.develop.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName BaseController
 * @Description 基础controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/3/30
 **/
public class BaseController {
    private static String oOoOoOoo0oo0 = "success";
    private static String o0oOo0ooOooO = "msg";
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    private PropertyFilter jdField_oOoOoOooOooO_of_type_ComAlibabaFastjsonSerializerPropertyFilter = new PropertyFilter() {
        @Override
        public final boolean apply(Object paramObject1, String paramString, Object paramObject2) {
            return !paramString.equals("pcStateManager") && !paramString.equals("pcDetachedState");
        }
    };

    public BaseController() {
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    public HttpSession getSession() {
        return this.session;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void write(String param) {
        try {
            PrintWriter printWriter = this.response.getWriter();
            printWriter.write(param);
            printWriter.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private void write(Object paramObject) {
        if (paramObject == null) {
            this.write("{}");
        } else {
            this.write(JSON.toJSONString(paramObject, this.jdField_oOoOoOooOooO_of_type_ComAlibabaFastjsonSerializerPropertyFilter, new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero}));
        }
    }
}
