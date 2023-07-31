package com.crcm.auth.extend;

import com.crcm.security.bean.UserAccount;
import com.crcm.core.constant.Oauth2Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName AbstractPrepareIntegrationAuthenticator
 * @Description 集成认证-认证器抽象类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
public abstract class AbstractPrepareIntegrationAuthenticator implements IntegrationAuthenticator {

    /**
     * 预处理打印认证方式
     *
     * @param entity 集成认证实体
     */
    @Override
    public void prepare(IntegrationAuthenticationEntity entity) {
        log.info("IntegrationAuthenticator complete this is authType -> {}", authTypeHandle(entity.getAuthType()));
    }

    /**
     * 认证结束后执行
     *
     * @param entity 集成认证实体
     */
    @Override
    public void complete(IntegrationAuthenticationEntity entity) {
        log.info("IntegrationAuthenticator complete this is authType -> {}", authTypeHandle(entity.getAuthType()));
    }

    /**
     * 集成认证中除用户名密码不进行密码查询与校验
     *
     * @param userAccount 用户账户对象
     */
    void setEmptyPassword(UserAccount userAccount) {
        userAccount.setPassword("");
    }

    /**
     * 认证类型处理,返回实际的认证类型
     *
     * @param authType
     * @return
     */
    private String authTypeHandle(String authType) {
        return StringUtils.isEmpty(authType) ? Oauth2Constants.GRANT_MODE_PASSWORD : authType;
    }
}
