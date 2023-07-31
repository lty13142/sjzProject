package com.crcm.auth.extend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName VerificationCodeAuthenticator
 * @Description 用户名密码+ 验证码方式鉴权
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Component
public class VerificationCodeAuthenticator extends UsernamePasswordAuthenticator {

    /**
     * 用户名密码+ 验证码授权类型
     */
    private final static String AUTH_TYPE = "verification";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户名密码+ 验证码方式进行认证
     *
     * @param entity 登录信息
     * @return 结果
     */
    @Override
    public UserAccount authenticate(IntegrationAuthenticationEntity entity) {
        // 获取验证码
        String verificationCode = entity.getAuthParameter(AuthConstants.VERIFICATION_CODE_COMMON);
        if (StringUtils.isEmpty(verificationCode)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.VERIFICATION_CODE_EMPTY.getMsg());
        }
        // 获取唯一key
        String key = entity.getAuthParameter(AuthConstants.VERIFICATION_CODE_KEY_PARAMETER_NAME);
        if (StringUtils.isEmpty(key)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.VERIFICATION_CODE_UNICODE_KEY_EMPTY.getMsg());
        }
        // redis中获取验证码
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        Object codeData = redisTemplate.opsForValue().get(SystemBaseConstants.VERIFICATION_CODE + ":" + key);

        if (Objects.isNull(codeData)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.VERIFICATION_CODE_ERR.getMsg());
        }
//        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(codeData), JSONObject.class);
//        String code = jsonObject.getString("code");
        String code = (String) codeData;
        // 是否输入正确
        if (!code.toString().equalsIgnoreCase(verificationCode)) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.VERIFICATION_CODE_ERR.getMsg());
        }
        // 删除验证码
        redisTemplate.delete(SystemBaseConstants.VERIFICATION_CODE + ":" + key);
        return super.authenticate(entity);
    }

    /**
     * 认证类型为空时为用户名密码+ 验证码方式
     *
     * @param entity 集成认证实体
     * @return 结果
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}
