package com.crcm.auth.service;

import com.crcm.auth.extend.GenerateAuthorizationCode;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/**
 * @ClassName MyJdbcAuthorizationCodeService
 * @Description 授权码存取在数据库
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class CustomJdbcAuthorizationCodeService extends JdbcAuthorizationCodeServices {
    public CustomJdbcAuthorizationCodeService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写生成code
     *
     * @param authentication 认证信息
     * @return java.lang.String
     */
    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String code = GenerateAuthorizationCode.generatorThreadLocal.get().generate();
        store(code, authentication);
        return code;
    }
}
