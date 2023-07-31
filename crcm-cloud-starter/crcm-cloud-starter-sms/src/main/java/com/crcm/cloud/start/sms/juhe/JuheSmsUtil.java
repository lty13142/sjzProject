package com.crcm.cloud.start.sms.juhe;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Title:短信发送平台/p>
 * Description:短信发送平台，采用的是聚合短信接口
 * Copyright: Copyright (c) 2019
 * Company:
 *
 * @author zj 2019.4.23
 * @version 1.0
 */
@Component
public class JuheSmsUtil {
    // 聚合短信接口,详情见 https://www.juhe.cn/docs/api/id/54
    private static String juhe_url = "http://v.juhe.cn/sms/send";
    private static JuheSmsProperties smsProperties;

    @Autowired
    public JuheSmsUtil(JuheSmsProperties juheSmsProperties) {
        JuheSmsUtil.smsProperties = juheSmsProperties;
    }

    /**
     * 发送短信，采用的是聚合短信接口
     *
     * @param phone 电话
     * @param tplId 模板ID
     * @tplValue 短信内容
     */
    public static String sendSms(String phone, String tplId, String tplValue) {

        String juhe_url_param = "mobile=phone&tpl_id=tplId&tpl_value=tplValue&key=" + smsProperties.getKey();
        if (StringUtils.isBlank(phone) || phone.length() != 11) {
            return "phone is blank";
        }
        phone = phone.replace("<p>", "").replace("</p>", "");// 主要解决字典富文本配置中产生的p标签
        String param = juhe_url_param.replace("phone", phone);
        param = param.replace("tplId", tplId);
        // 判断是否输入内容
        if (StringUtils.isNotBlank(tplValue)) {
            param = param.replace("tplValue", "%23code%23%3D" + tplValue);
        } else {
            param = param.replace("tplValue", "");
        }
        String result = sendPost(juhe_url, param);
        System.out.println("发送短信到:" + phone + "  " + result + "  " + new Date());
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            if (param != null) {
                // 发送请求参数
                out.print(param);
            }

            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            result = new StringBuilder(e.getMessage());
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

}
