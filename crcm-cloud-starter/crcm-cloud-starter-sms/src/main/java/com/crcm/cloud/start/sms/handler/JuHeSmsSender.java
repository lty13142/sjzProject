package com.crcm.cloud.start.sms.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

/**
 * @ClassName JuHeSmsSender
 * @Description 聚合短信发送器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/10
 **/
@Slf4j
public class JuHeSmsSender implements SmsSender {

    private final String host;
    private final String key;

    // 聚合短信接口,详情见 https://www.juhe.cn/docs/api/id/54
    private static final String DEFAULT_HOST = "http://v.juhe.cn/sms/send";

    public JuHeSmsSender(String host, String key) {
        this.host = StringUtils.isBlank(host) ? DEFAULT_HOST : host;
        this.key = key;
    }

    @Override
    public String sendSms(String phoneNum, String templateId, String content) {
        String result = null;
        if (StringUtils.isNotBlank(phoneNum)) {
            String juheUrlParam = "mobile=phone&tpl_id=tplId&tpl_value=tplValue&key=" + this.key;
            // 主要解决字典富文本配置中产生的p标签
            phoneNum = phoneNum.replace("<p>", "").replace("</p>", "");
            String param = juheUrlParam.replace("phone", phoneNum);
            param = param.replace("tplId", templateId);
            // 判断是否输入内容
            if (StringUtils.isNotBlank(content)) {
                param = param.replace("tplValue", "%23code%23%3D" + content);
            } else {
                param = param.replace("tplValue", "");
            }
            result = sendPost(this.host, param);
        }
        log.info("发送短信到:" + phoneNum + "  " + result + "  " + new Date());
        return result;
    }

    @Override
    public int sendSms(List<String> phoneNums, String templateId, String content) {
        return 0;
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
