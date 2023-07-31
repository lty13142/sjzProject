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
 * @ClassName WeChatAuthenticator
 * @Description 微信授权登录鉴权
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Component
@RequiredArgsConstructor
public class WeChatAuthenticator extends AbstractPrepareIntegrationAuthenticator{

    /**
     * 注册直接颁发令牌鉴权授权类型
     */
    private  static final String AUTH_TYPE = "weChat";

    /**
     * 用户账户Service
     */
    private final UserAccountService userAccountService;

    /**
     * 根据手机号验证码方式进行认证
     * <p>认证前须判断手机号存在而后再进行验证码验证</p>
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public UserAccount authenticate(IntegrationAuthenticationEntity entity) {
        // 微信unionId
        String unionId = entity.getAuthParameter(AuthConstants.AUTH_WECHAT_UNIONID);
        if(StringUtils.isEmpty(unionId)){
            throw new OAuth2Exception(Oauth2ExceptionEnum.UNIONID_EMPTY.getMsg());
        }

        // 根据微信unionId查询用户
        UserAccount userAccount = userAccountService.selectWeChatUnionId(unionId);
        if(null == userAccount){
            throw new OAuth2Exception(Oauth2ExceptionEnum.USER_NOT_FOUND.getMsg());
        }
        // 集成认证中除用户名密码不进行密码查询与校验
        setEmptyPassword(userAccount);
        return userAccount;
    }

    /**
     * 微信认证方式
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}
