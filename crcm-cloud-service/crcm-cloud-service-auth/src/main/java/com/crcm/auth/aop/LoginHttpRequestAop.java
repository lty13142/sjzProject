package com.crcm.auth.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.core.response.RestResult;
import com.crcm.security.bean.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName LoginHttpRequestAop
 * @Description oauth2登陆申请token请求切面
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/10/18
 **/
@Slf4j
@Component
@Aspect
public class LoginHttpRequestAop {

    @Autowired
    private TokenStore tokenStore;

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            Principal principal = (Principal) args[0];
            if (!(principal instanceof Authentication)) {
                throw new InsufficientAuthenticationException("There is no client authentication. Try adding an appropriate authentication filter.");
            }
            String clientId = this.getClientId(principal);
            Map<String, String> parameters = (Map<String, String>) args[1];
            String grantType = parameters.get(OAuth2Utils.GRANT_TYPE);
            //保存租户id
//            TenantContextHolder.setTenant(clientId);
            Object proceed = joinPoint.proceed();
            ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>) proceed;
            OAuth2AccessToken body = responseEntity.getBody();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(RestResult.succeed(body,"获取令牌成功！"));
        } finally {
//            TenantContextHolder.clear();
        }
    }

    private String getClientId(Principal principal) {
        Authentication client = (Authentication) principal;
        if (!client.isAuthenticated()) {
            throw new InsufficientAuthenticationException("The client is not authenticated.");
        }
        String clientId = client.getName();
        if (client instanceof OAuth2Authentication) {
            clientId = ((OAuth2Authentication) client).getOAuth2Request().getClientId();
        }
        return clientId;
    }

    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void loginAop(JoinPoint joinPoint, Object returnValue) {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String jsonString = null;
        try {
            jsonString = JSON.toJSONString(returnValue);
            JSONObject data = JSON.parseObject(jsonString, JSONObject.class);
            JSONObject accessToken = data.getObject("body", JSONObject.class);
            String token = accessToken.getString("value");
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
            OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
            UserAccount user = (UserAccount) oAuth2Authentication.getPrincipal();
            // 记录应用访问日志
            String msg = "用户： " + user.getUsername() + "于客户端：: " + oAuth2Request.getClientId() + "登陆，获取Token：" + token;
            log.info(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterThrowing(value = "pointCut()", throwing = "throwable")
    public void loginAfterThrowingAop(JoinPoint joinPoint, Throwable throwable) {
        log.error(throwable.getMessage());
    }

}
