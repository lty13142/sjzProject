package com.crcm.auth.extend;

import com.crcm.auth.service.UserAccountService;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.core.constant.AuthConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * @ClassName RefreshTokenAuthenticator
 * @Description 刷新token鉴权
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Component
@RequiredArgsConstructor
public class RefreshTokenAuthenticator extends AbstractPrepareIntegrationAuthenticator {
    /**
     * 刷新token
     */
    private static final String AUTH_TYPE = "refresh_token";
    /**
     * 用户账户Service
     */
    private final UserAccountService userAccountService;

    @Override
    public UserAccount authenticate(IntegrationAuthenticationEntity entity) {
        // 获取用户名
        String username = entity.getAuthParameter(AuthConstants.USERNAME_PARAMETER_NAME);
        // 获取用户名
        String userType = entity.getAuthParameter(AuthConstants.USER_TYPE);
        // 用户名为空
        if (username == null) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.USERNAME_NULL.getMsg());
        }
        // 根据用户名查询用户账户
        UserAccount userAccount = userAccountService.selectByUserName(username, userType);
        if (null == userAccount) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.USER_NOT_FOUND.getMsg());
        }
        // 验证用户可用性
        userAccountService.validateUser(userAccount);

        // 组合用户账户对象与角色权限
        return userAccountService.composeUserAccountAndAuthority(userAccount);
    }

    /**
     * 刷新token方式
     *
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}
