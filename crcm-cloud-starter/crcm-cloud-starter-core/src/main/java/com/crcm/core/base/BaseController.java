package com.crcm.core.base;

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
 * @Description 客户端数据响应基础类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
public class BaseController {
    private static String oOoOoOoo0oo0 = "success";
    private static String o0oOo0ooOooO = "msg";
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    public HttpServletResponse getResponse() {
        return response;
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,
                             HttpServletResponse response) {

        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 获取session
     *
     * @return HttpSession
     */
    public HttpSession getSession() {
        return this.session;
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return this.request;
    }


    /**
     * 向客户端返回字符串
     *
     * @param param
     */
    public void write(String param) {
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(param);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向客户端返回对象，可以是list/map/byte等
     *
     * @param paramObject
     */
    private void write(Object paramObject) {
        if (paramObject == null) {
            write("{}");
            return;
        }
        write(JSON
                .toJSONString(
                        paramObject,
                        this.jdField_oOoOoOooOooO_of_type_ComAlibabaFastjsonSerializerPropertyFilter,
                        new SerializerFeature[]{
                                SerializerFeature.WriteNullStringAsEmpty,
                                SerializerFeature.WriteNullBooleanAsFalse,
                                SerializerFeature.WriteNullNumberAsZero}));
    }


    private PropertyFilter jdField_oOoOoOooOooO_of_type_ComAlibabaFastjsonSerializerPropertyFilter = new PropertyFilter() {
        public final boolean apply(Object paramObject1, String paramString,
                                   Object paramObject2) {
            return ((!(paramString.equals("pcStateManager"))) && (!(paramString
                    .equals("pcDetachedState"))));
        }
    };
}
