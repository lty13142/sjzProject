package com.crcm.admin.controller.open;

import com.crcm.admin.api.dto.req.ReqRegisterDTO;
import com.crcm.admin.model.dto.req.UserLoginDTO;
import com.crcm.admin.model.dto.weChat.ReqWeChatAppletLoginDTO;
import com.crcm.admin.service.UserOauthService;
import com.crcm.auth.api.constant.AuthTypeConstant;
import com.crcm.auth.api.dto.req.*;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import com.crcm.core.constant.enums.UserType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @ClassName UserAuthController
 * @Description 用户授权认证
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Slf4j
@Api(tags = {"app: 用户授权认证相关", "不检查是否授权"})
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserLoginController {
    private final Oauth2Properties oauth2Properties;
    /**
     * 用户认证Service
     */
    @Resource
    private UserOauthService userOauthService;

    /**
     * 密码模式
     * <p>用户名 + 密码登录</p>
     *
     * @param dto 用户名密码登录DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "用户名 + 密码登录", responseReference = "返回token")
    @PostMapping(value = "/login")
    public RestResult<Object> loginByUsernameAndPassword(@Valid @RequestBody UserLoginDTO dto) {
        return userOauthService.loginByUsernameAndPassword(
                dto.getUsername(), dto.getPassword(),
                oauth2Properties.getClientId(), oauth2Properties.getClientSecret(), StringUtils.isBlank(dto.getScope()) ? "all" : dto.getScope(),
                AuthTypeConstant.LOGIN_TYPE_PASSWORD, UserType.ADMIN.getValue(), null, null);
    }

    /**
     * 密码模式
     * <p>用户名 + 密码登录  用户名密码数据不加密</p>
     *
     * @param dto 用户名密码登录DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "用户名 + 密码登录(用户名密码数据不加密！)", responseReference = "返回token")
    @PostMapping(value = "/login/NoSec")
    public RestResult<Object> loginByUsernameAndPasswordNoSecret(@Valid @RequestBody UserLoginDTO dto) {
        return userOauthService.loginByUsernameAndPasswordNoSecret(
                dto.getUsername(), dto.getPassword(),
                oauth2Properties.getClientId(), oauth2Properties.getClientSecret(), StringUtils.isBlank(dto.getScope()) ? "all" : dto.getScope(),
                AuthTypeConstant.LOGIN_TYPE_PASSWORD, UserType.ADMIN.getValue(), null, null);
    }

    /**
     * 密码模式
     * <p>用户名 + 密码 + 验证码登录</p>
     *
     * @param dto 用户名密码登录DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "用户名 + 密码 + 验证码登录", responseReference = "返回token")
    @PostMapping(value = "/login/code")
    public RestResult<Object> loginByUsernameAndPasswordAndVerificationCode(@Valid @RequestBody UserLoginDTO dto) {
        return userOauthService.loginByUsernameAndPassword(dto.getUsername(), dto.getPassword(),
                oauth2Properties.getClientId(), oauth2Properties.getClientSecret(), StringUtils.isBlank(dto.getScope()) ? "all" : dto.getScope(),
                AuthTypeConstant.LOGIN_TYPE_PASSWORD_AND_CODE, UserType.ADMIN.getValue(), dto.getCode(), dto.getKey());
    }

    /**
     * 获取授权码
     *
     * @param reqGetAuthorizationCodeDTO 获取授权码 DTO
     */
    @ApiOperation(value = "获取授权码", responseReference = "-")
    @GetMapping(value = "/getAuthCode")
    public void getAuthorizationCode(HttpServletResponse response, @Valid ReqGetAuthorizationCodeDTO reqGetAuthorizationCodeDTO) throws IOException {
        userOauthService.getAuthorizationCode(response, reqGetAuthorizationCodeDTO);
    }

    /**
     * 授权码获取token
     *
     * @param reqGetTokenByAuthorizationCodeDTO 授权码获取token DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "授权码获取token", responseReference = "返回token")
    @PostMapping(value = "/getTokenByAuthCode")
    public RestResult getTokenByAuthorizationCode(@Valid @RequestBody ReqGetTokenByAuthorizationCodeDTO reqGetTokenByAuthorizationCodeDTO) {
        return userOauthService.getTokenByAuthorizationCode(reqGetTokenByAuthorizationCodeDTO);
    }

    /**
     * 刷新token
     *
     * @param reqRefreshTokenDTO tokenDTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "刷新token", responseReference = "返回token")
    @PostMapping(value = "/refreshToken")
    public RestResult refreshToken(@Valid @RequestBody ReqRefreshTokenDTO reqRefreshTokenDTO) {
        return userOauthService.refreshToken(reqRefreshTokenDTO);
    }

    /**
     * 密码模式
     * <p>手机号 + 验证码登录</p>
     *
     * @param reqPhoneLoginDTO 用户名手机号验证码登录DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "手机号 + 验证码登录", responseReference = "返回token")
    @PostMapping(value = "/login/Phone")
    public RestResult loginByPhone(@Valid @RequestBody ReqPhoneLoginDTO reqPhoneLoginDTO) {
        return userOauthService.loginByPhone(reqPhoneLoginDTO);
    }

    /**
     * 用户注册
     *
     * @param reqRegisterDTO 用户注册DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "用户注册", responseReference = "返回token")
    @PostMapping(value = "/register")
    public RestResult userRegister(@Valid @RequestBody ReqRegisterDTO reqRegisterDTO) throws InterruptedException {
        // 手机号
        String phoneNumber = reqRegisterDTO.getPhoneNumber();
        // 注册
        RestResult result = userOauthService.userRegister(reqRegisterDTO.getPhoneCode(), phoneNumber, reqRegisterDTO.getPassword(), reqRegisterDTO.getShareCode());
        // 注册成功直接颁发令牌
        if (result.isSuccess()) {
            log.info("注册成功直接颁发令牌 phone -> {}", phoneNumber);
            Thread.sleep(500);
            return userOauthService.getTokenByUserRegister(phoneNumber, reqRegisterDTO.getClientId(), reqRegisterDTO.getClientSecret(), reqRegisterDTO.getScope());
        }
        return result;
    }

    /**
     * 微信授权登录
     *
     * @param reqWeChatLoginDTO 微信授权登录DTO
     * @return com.crcm.core.response.RestResult
     */
    @ApiOperation(value = "微信授权登录", responseReference = "返回token")
    @PostMapping(value = "/login/weChat")
    public RestResult loginByWeChat(@Valid @RequestBody ReqWeChatAppletLoginDTO reqWeChatLoginDTO) {
        return userOauthService.loginByWeChat(reqWeChatLoginDTO);
    }

    /**
     * 用户登出
     *
     * @return
     */
    @ApiOperation(value = "用户登出")
    @GetMapping(value = "/logout")
    public RestResult logout(String token) {
        return userOauthService.loginOut(token);
    }

}
