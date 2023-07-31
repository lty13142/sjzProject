package com.crcm.auth.service.impl;

import com.crcm.auth.extend.IntegrationAuthenticationContext;
import com.crcm.auth.extend.IntegrationAuthenticationEntity;
import com.crcm.auth.extend.IntegrationAuthenticator;
import com.crcm.auth.service.UserAccountService;
import com.crcm.core.constant.enums.UserType;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.core.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserLoginServiceImpl
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Component
public class UserLoginServiceImpl implements UserDetailsService {
    /**
     * 扩展集成验证器
     */
    private final List<IntegrationAuthenticator> authenticators;
    /**
     * 用户账户Service
     */
    private final UserAccountService userAccountService;

    @Autowired(required = false)
    public UserLoginServiceImpl(List<IntegrationAuthenticator> authenticators, UserAccountService userAccountService) {
        this.authenticators = authenticators;
        this.userAccountService = userAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthenticationEntity entity = IntegrationAuthenticationContext.get();
        UserAccount userAccount;
        // 集成认证实体为空时为授权码或隐式模式
        if (null == entity){
            userAccount = userAccountService.selectByUserName(username, UserType.ADMIN.getValue());
            if(null == userAccount){
                throw new UsernameNotFoundException(Oauth2ExceptionEnum.USER_NOT_FOUND.getMsg());
            }
            // 验证用户可用性
            userAccountService.validateUser(userAccount);
            // 组合用户账户对象与角色权限
            return userAccountService.composeUserAccountAndAuthority(userAccount);
        }

        // 如果用户名为空时则为刷新token 此时赋值username
        if(StringUtils.isEmpty(entity.getAuthParameter(AuthConstants.USERNAME_PARAMETER_NAME))){
            entity.getAuthParameters().put(AuthConstants.USERNAME_PARAMETER_NAME,new String[]{username});
        }
        // 选取合适的控制器认证
        userAccount = this.authenticate(entity);
        if (null == userAccount){
            throw new UsernameNotFoundException(Oauth2ExceptionEnum.USER_NOT_FOUND.getMsg());
        }
        return userAccount;
    }

    /**
     * 选取合适的控制器认证
     * @param entity 集成认证实体
     * @return UserAccount
     */
    private UserAccount authenticate(IntegrationAuthenticationEntity entity) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(entity)) {
                    return authenticator.authenticate(entity);
                }
            }
        }
        return null;
    }
}
