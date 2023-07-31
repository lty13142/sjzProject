package com.crcm.auth.extend;

import com.crcm.auth.service.UserAccountService;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.core.constant.AuthConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsCodeAuthenticator
 * @Description 手机号短信验证码鉴权
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Component
@RequiredArgsConstructor
public class SmsCodeAuthenticator extends AbstractPrepareIntegrationAuthenticator {
    /**
     * 手机号短信验证码授权类型
     */
    private static final String AUTH_TYPE = "sms";
    /**
     * 用户账户Service
     */
    private final UserAccountService userAccountService;


    @Override
    public UserAccount authenticate(IntegrationAuthenticationEntity entity) {
        // 手机号
        String phone = entity.getAuthParameter(AuthConstants.AUTH_PHONE);
        // 获取用户类型
        String userType = entity.getAuthParameter(AuthConstants.USER_TYPE);
        if (StringUtils.isEmpty(phone)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.PHONE_NULL.getMsg());
        }
        // todo 验证手机号已注册

        // 验证码
        String code = entity.getAuthParameter(AuthConstants.AUTH_CODE);
        if (StringUtils.isEmpty(code)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.PHONE_VERIFICATION_CODE_NULL.getMsg());
        }

        // todo 验证验证码
        //测试代码块，所以将验证码定为：1234
        if (!"1234".equals(code)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.PHONE_VERIFICATION_CODE_ERR_OR_EXPIRE.getMsg());
        }

        UserAccount userAccount = userAccountService.selectByPhone(phone, userType);
        if (null == userAccount) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.USER_NOT_FOUND.getMsg());
        }
        // 集成认证中除用户名密码不进行密码查询与校验
        setEmptyPassword(userAccount);
        return userAccount;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}
