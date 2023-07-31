package com.crcm.cloud.start.sms.handler;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName AliyunSmsSender
 * @Description 阿里云短信发送器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/10
 **/
public class AliYunSmsSender implements SmsSender {

    private final String host;
    private final String accessKey;
    private final String secret;
    // 默认阿里云短信地址
    private static final String DEFAULT_HOST = "dysmsapi.aliyuncs.com";

    public AliYunSmsSender(String host, String accessKey, String secret) {
        this.host = StringUtils.isBlank(host) ? DEFAULT_HOST : host;
        this.accessKey = accessKey;
        this.secret = secret;
    }

    @Override
    public String sendSms(String phoneNum, String templateId, String content) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.accessKey, this.secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        String result = null;
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(this.host);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);
//        request.putQueryParameter("SignName", "阿里大于测试专用");
        request.putQueryParameter("TemplateCode", templateId);
        request.putQueryParameter("TemplateParam", content);
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = response.getData();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int sendSms(List<String> phoneNums, String templateId, String content) {
        int result = 0;
        if (!CollectionUtils.isEmpty(phoneNums)) {
            for (String phoneNum : phoneNums) {
                String s = this.sendSms(phoneNum, templateId, content);
            }
        }
        return result;
    }


}
