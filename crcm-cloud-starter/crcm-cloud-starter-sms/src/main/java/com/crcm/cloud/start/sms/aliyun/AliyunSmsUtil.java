package com.crcm.cloud.start.sms.aliyun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName AliyunSmsUtil
 * @Description 阿里云短信发送工具
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/31
 **/
@Component
public class AliyunSmsUtil {
    /* 短信API产品名称（短信产品名固定，无需修改） */
    private static final String product = "Dysmsapi";

    /* 短信API产品域名，接口地址固定，无需修改 */
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static AliyunSmsProperties smsProperties;

    @Autowired
    public AliyunSmsUtil(AliyunSmsProperties aliyunSmsProperties) {
        AliyunSmsUtil.smsProperties = aliyunSmsProperties;
    }

    /**
     * 发送验证码
     *
     * @param phoneNum     手机号
     * @param templateCode 模板代码
     * @param code         验证码
     */
    public static void sendSmsCode(String phoneNum, String templateCode, String code) {
        String templateParam = "{\"code\":\"" + code + "\"}";
        sendSms(phoneNum, templateCode, templateParam);
    }

    /**
     * 发送短信
     *
     * @param phoneNum      手机号
     * @param templateCode  模板代码
     * @param TemplateParam 短信内容
     */
    public static void sendSms(String phoneNum, String templateCode, String TemplateParam) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsProperties.getAccessKey(), smsProperties.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);
//        request.putQueryParameter("SignName", "阿里大于测试专用");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", TemplateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
