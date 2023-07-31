package com.crcm.auth.extend;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName IntegrationAuthenticationEntity
 * @Description 集成认证实体
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Data
public class IntegrationAuthenticationEntity {

    /**
     * 获取token扩展授权类型
     */
    private String authType;

    /**
     * 请求登录认证参数集合
     */
    private Map<String,String[]> authParameters;

    /**
     * 获取参数中的值
     * @param parameter 请求参数
     * @return
     */
    public String getAuthParameter(String parameter){
        String[] values = this.authParameters.get(parameter);
        if(values != null && values.length > 0){
            return values[0];
        }
        return null;
    }
}
