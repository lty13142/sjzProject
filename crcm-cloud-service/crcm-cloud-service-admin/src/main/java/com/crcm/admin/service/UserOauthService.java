package com.crcm.admin.service;

import com.crcm.admin.model.dto.weChat.ReqWeChatAppletLoginDTO;
import com.crcm.auth.api.dto.req.*;
import com.crcm.core.response.RestResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName IUserOauthService
 * @Description 
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
public interface UserOauthService {
    /**
     * 密码模式
     * 用户名/手机号 密码 （图片验证码） 登录
     *
     * @param username          用户名/手机号
     * @param password          密码
     * @param verificationCode  图片验证码
     * @param key               验证码唯一键
     * @param authType          扩展授权类型
     * @param authType          用户类型
     * @param clientId          客户端ID
     * @param clientSecret      客户端密钥
     * @param scope             应用授权作用域
     * @return com.mogu.starter.base.bean.Result
     */
    RestResult<Object> loginByUsernameAndPassword(String username,
                                                  String password,
                                                  String clientId,
                                                  String clientSecret,
                                                  String scope,
                                                  String authType,
                                                  String userType,
                                                  String verificationCode,
                                                  String key);

    RestResult<Object> loginByUsernameAndPasswordNoSecret(String username,
                                                  String password,
                                                  String clientId,
                                                  String clientSecret,
                                                  String scope,
                                                  String authType,
                                                  String userType,
                                                  String verificationCode,
                                                  String key);

    /**
     * 获取授权码
     *
     * @param response                   响应体
     * @param reqGetAuthorizationCodeDto 获取授权码 DTO
     * @throws IOException               io异常
     */
    void getAuthorizationCode(HttpServletResponse response, ReqGetAuthorizationCodeDTO reqGetAuthorizationCodeDto) throws IOException;

    /**
     * 授权码获取token
     * @param reqGetTokenByAuthorizationCodeDto 授权码获取token DTO
     * @return org.surge.oauth2common.model.Result
     */
    RestResult getTokenByAuthorizationCode(ReqGetTokenByAuthorizationCodeDTO reqGetTokenByAuthorizationCodeDto);

    /**
     * 刷新token
     * reqRefreshTokenDTO 刷新tokenDTO
     * @return org.surge.oauth2common.model.Result
     */
    RestResult refreshToken(ReqRefreshTokenDTO reqRefreshTokenDTO);

    /**
     * 手机号 + 验证码登录
     * @param reqPhoneLoginDTO  手机号验证码登录DTO
     * @return org.surge.oauth2common.model.Result
     */
    RestResult loginByPhone(ReqPhoneLoginDTO reqPhoneLoginDTO);

    /**
     * 用户注册
     * @param phoneCode    短信验证码
     * @param phoneNumber  手机号
     * @param password     密码
     * @param shareCode    分享码
     * @return org.surge.oauth2common.model.Result
     */
    RestResult userRegister(String phoneCode, String phoneNumber, String password, String shareCode);

    /**
     * 注册成功直接颁发令牌
     *
     * @param phoneNumber   用户手机号
     * @param clientId      客户端ID
     * @param clientSecret  客户端密钥
     * @param scope         授权作用域
     * @return org.surge.oauth2common.model.Result
     */
    RestResult getTokenByUserRegister(String phoneNumber, String clientId, String clientSecret, String scope);

    /**
     * 微信授权登录
     * @param reqWeChatLoginDTO 微信授权登录DTO
     * @return org.surge.oauth2common.model.Result
     */
    RestResult loginByWeChat(ReqWeChatAppletLoginDTO reqWeChatLoginDTO);

    /**
     * 登出
     * @param token 用户token
     * @return org.surge.oauth2common.model.Result
     */
    RestResult loginOut(String token);
}
