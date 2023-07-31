package com.crcm.core.utils;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crcm.core.response.RestResult;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName UtilResponse
 * @Description 响应工具
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public class ResponseUtil {
    public ResponseUtil() {
    }

    public static void writeResult(HttpServletResponse response, Object data, String msg) {
        RestResult result = new RestResult();
        result.setCode(HttpStatus.HTTP_OK);
        result.setData(data);
        result.setMessage(msg);
        result.setSuccess(true);
        write(response, result);
    }

    public static void writeSuccessResult(HttpServletResponse response) {
        RestResult result = new RestResult();
        result.setCode(HttpStatus.HTTP_OK);
        result.setData((Object) null);
        result.setMessage("成功");
        result.setSuccess(true);
        write(response, result);
    }

    public static void writeSuccessResult(HttpServletResponse response,Object data) {
        RestResult result = new RestResult();
        result.setCode(HttpStatus.HTTP_OK);
        result.setData(data);
        result.setMessage("成功");
        result.setSuccess(true);
        write(response, result);
    }

    public static void writeFailureResult(HttpServletResponse response, Integer code, String msg) {
        RestResult result = new RestResult();
        result.setCode(code);
        result.setData((Object) null);
        result.setMessage(msg);
        result.setSuccess(false);
        write(response, result);
    }

    public static void write(HttpServletResponse response, Object obj) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero}));
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            if (null != writer) {
                writer.flush();
                writer.close();
            }

        }

    }
}
