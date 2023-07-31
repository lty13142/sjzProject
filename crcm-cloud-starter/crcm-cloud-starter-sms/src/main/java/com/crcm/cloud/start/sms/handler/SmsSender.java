package com.crcm.cloud.start.sms.handler;

import java.util.List;

/**
 * @ClassName SmsSendTemplate
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/10
 **/
public interface SmsSender {


    /**
     * 发送短信
     *
     * @param phoneNum   电话号码
     * @param templateId 短信模板ID/CODE
     * @param content    短信内容
     * @return void
     */
    String sendSms(String phoneNum, String templateId, String content);

    /**
     * 批量发送短信
     *
     * @param phoneNums  电话号码集合
     * @param templateId 短信模板ID/CODE
     * @param content    短信内容
     * @return void
     */
    int sendSms(List<String> phoneNums, String templateId, String content);

}
