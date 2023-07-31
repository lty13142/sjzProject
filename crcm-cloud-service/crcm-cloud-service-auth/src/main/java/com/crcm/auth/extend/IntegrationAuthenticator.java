package com.crcm.auth.extend;

import com.crcm.security.bean.UserAccount;

/**
 * @ClassName IntegrationAuthenticator
 * @Description 扩展集成验证器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public interface IntegrationAuthenticator {

    /**
     *  处理集成认证
     * @param entity 集成认证实体
     * @return
     */
    UserAccount authenticate(IntegrationAuthenticationEntity entity);

    /**
     * 预处理（准备）
     * @param entity 集成认证实体
     */
    void prepare(IntegrationAuthenticationEntity entity);

    /**
     * 判断是否支持集成认证类型
     * @param entity 集成认证实体
     * @return
     */
    boolean support(IntegrationAuthenticationEntity entity);

    /**
     * 认证结束后执行
     * @param entity 集成认证实体
     */
    void complete(IntegrationAuthenticationEntity entity);
}
