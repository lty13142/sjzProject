package com.crcm.auth.extend;

import com.crcm.auth.service.UserAccountService;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.security.utils.BpwdEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName UsernamePasswordAuthenticator
 * @Description 用户名密码授权类型鉴权
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Component
public class UsernamePasswordAuthenticator extends AbstractPrepareIntegrationAuthenticator {

    /**
     * 用户名密码授权类型
     */
    private static final String AUTH_TYPE = "password";
    /**
     * 允许最大密码错误次数
     */
    private static Integer PASSWORD_ERROR_MAX_COUNT = 3;
    /**
     * 密码错误超出限定次数锁定时间
     */
    private static Long LOCK_TIME = 60 * 10L;
    /**
     * 用户账户Service
     */
    @Resource
    private UserAccountService userAccountService;

    @Resource
    private RedisService redisService;

    @Override
    public UserAccount authenticate(IntegrationAuthenticationEntity entity) {
        // 获取用户名密码
        String username = entity.getAuthParameter(AuthConstants.USERNAME_PARAMETER_NAME);
        String password = entity.getAuthParameter(AuthConstants.PASSWORD_PARAMETER_NAME);
        String userType = entity.getAuthParameter(AuthConstants.USER_TYPE);
        // 用户名或密码为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.USERNAME_OR_PASSWORD_NULL.getMsg());
        }
        // 获取密码错误次数
        String key = SystemBaseConstants.BASE_PATH + ":" + username;
        Integer passwordErrorCount = 0;
        if (redisService.hasKey(key)) {
            passwordErrorCount = (Integer) redisService.get(key);
        }
        // 设置密码错误超出限定次数锁定时间
        if (redisService.hasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY)
                && redisService.hHasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PASSWORD_ERROR_LOCK_TIME)) {
            Object lockTime = redisService.hget(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PASSWORD_ERROR_LOCK_TIME);
            LOCK_TIME = Objects.isNull(lockTime) ? LOCK_TIME : Long.parseLong(lockTime.toString());
        }
        // 设置密码错误限定次数
        if (redisService.hasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY)
                && redisService.hHasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PASSWORD_ERROR_LIMIT_COUNT)) {
            Object maxCount = redisService.hget(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PASSWORD_ERROR_LIMIT_COUNT);
            PASSWORD_ERROR_MAX_COUNT = Objects.isNull(maxCount) ? PASSWORD_ERROR_MAX_COUNT : Integer.parseInt(maxCount.toString());
        }
        if (passwordErrorCount >= PASSWORD_ERROR_MAX_COUNT) {
            throw new CustomException(ResultCode.LOGIN_FAIL_LIMITED);
        }
        UserAccount userAccount = userAccountService.selectByUserName(username, userType);
        if (null == userAccount) {
            throw new CustomException(ResultCode.USER_NO_FOUNT);
        }
        if (!userAccount.isEnabled()) {
            throw new CustomException(ResultCode.ACCOUNT_DISABLE);
        }
        // 验证密码正确性
        if (!BpwdEncoderUtil.matches(password, userAccount.getPassword())) {
            // 登录错误次数超过限制时，限定10分钟后才能登录，否则3小时后重置错误次数
            if (passwordErrorCount + 1 == PASSWORD_ERROR_MAX_COUNT) {
                redisService.set(key, passwordErrorCount + 1, LOCK_TIME);
            } else {
                redisService.set(key, passwordErrorCount + 1, 3 * 60 * 60L);
            }
            throw new CustomException(ResultCode.USERNAME_PASSWORD_ERROR);
        }
        // 验证用户可用性
        userAccountService.validateUser(userAccount);
        redisService.del(key);
        // 更新用户登录时间
        userAccountService.updateUserLoginTime(username);
        return userAccount;
    }

    /**
     * 认证类型为空时为用户名密码方式
     *
     * @param entity 集成认证实体
     * @return 结果
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        // 认证类型
        String authType = entity.getAuthType();
        return StringUtils.isEmpty(authType) || authType.equals(AUTH_TYPE);
    }
}
