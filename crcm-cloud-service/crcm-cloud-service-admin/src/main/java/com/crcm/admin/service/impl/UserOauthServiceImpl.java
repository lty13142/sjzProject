package com.crcm.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.crcm.admin.api.constants.RefreshTokenType;
import com.crcm.admin.api.dto.req.ReqUserInfoInitializeDTO;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.constants.UserConstant;
import com.crcm.admin.model.dto.weChat.ReqWeChatAppletLoginDTO;
import com.crcm.admin.model.dto.weChat.ReqUserRegisterDTO;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUser;
import com.crcm.admin.model.entity.SysUserDetail;
import com.crcm.admin.model.entity.SysUserRole;
import com.crcm.admin.model.entity.weChat.WeChatConfig;
import com.crcm.admin.service.*;
import com.crcm.auth.api.constant.AuthTypeConstant;
import com.crcm.auth.api.dto.req.*;
import com.crcm.auth.api.feign.RemoteOauthService;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import com.crcm.cloud.start.sso.utils.BpwdEncoderUtil;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.Oauth2Constants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.Assert;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.SM4Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserOauthServiceImpl
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserOauthServiceImpl implements UserOauthService {

    /**
     * 是否存在token(授权接口并且授权成功)
     */
    private static final String ACCESS_TOKEN = "access_token";

    /**
     * 接口请求成功标志
     */
    private static final String SUCCESS_FLAG = "success";

    /**
     * JSON中刷新令牌键{@value}
     */
    private static final String REFRESH_TOKEN_KEY = "refresh_token";

    /**
     * 认证服务远程调用Client
     */
    private final RemoteOauthService oauthService;
    /**
     * 用户服务
     */
    private final UserService userService;
    /**
     * 角色服务
     */
    private final RoleService roleService;
    /**
     * 用户角色服务
     */
    private final UserRoleService userRoleService;
    /**
     * 用户详情服务
     */
    private final UserDetailService userDetailService;
    /**
     * 雪花算法
     */
    @Resource
    private Snowflake snowflake;

    /**
     * redis
     */
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private WeChatConfig weChatConfig;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 单体redis注入-》SingleRedissonClient
     * 集群redis注入-》ClusterRedissonClient
     */
    @Resource
    private RedissonClient singleRedissonClient;

    @Resource
	private Oauth2Properties oauth2Properties;

    /**
     * 调用认证服务密码模式授权认证
     *
     * @param username         用户名/手机号
     * @param password         密码
     * @param verificationCode 图片验证码
     * @param key              验证码唯一键
     * @param authType         扩展授权类型
     * @param clientId         客户端ID
     * @param clientSecret     客户端密钥
     * @param scope            应用授权作用域
     * @return
     */
    @Override
    public RestResult<Object> loginByUsernameAndPassword(String username,
                                                         String password,
                                                         String clientId,
                                                         String clientSecret,
                                                         String scope,
                                                         String authType,
                                                         String userType,
                                                         String verificationCode,
                                                         String key) {
        username = StringUtils.isNotBlank(username) ? SM4Util.decryptStr(username) : username;
        password = StringUtils.isNotBlank(password) ? SM4Util.decryptStr(password) : password;
        // 验证码不为空为用户名密码验证码
        if (StringUtils.isNotBlank(verificationCode)) {
            log.info("密码模式（用户名密码验证码） authType -> {}, username -> {}, clientId -> {}, verificationCode -> {}, key -> {}, scope -> {} "
                    , authType, username, clientId, verificationCode, key, scope);
        } else {
            log.info("密码模式（用户名密码） authType -> {}, username -> {}, clientId -> {}, scope -> {} "
                    , authType, username, clientId, scope);
        }
        // 认证服务返回结果
        JSONObject result = oauthService.getToken(
                Oauth2Constants.GRANT_MODE_PASSWORD,
                username,
                password,
                clientId,
                clientSecret,
                scope,
                authType,
                userType,
                verificationCode,
                key);

        // 转换为Result对象
        return makeJsonToResult(result, "登陆成功！");
    }

    @Override
    public RestResult<Object> loginByUsernameAndPasswordNoSecret(String username, String password, String clientId, String clientSecret, String scope, String authType, String userType, String verificationCode, String key) {
        // 验证码不为空为用户名密码验证码
        if (StringUtils.isNotBlank(verificationCode)) {
            log.info("密码模式（用户名密码验证码） authType -> {}, username -> {}, clientId -> {}, verificationCode -> {}, key -> {}, scope -> {} "
                    , authType, username, clientId, verificationCode, key, scope);
        } else {
            log.info("密码模式（用户名密码） authType -> {}, username -> {}, clientId -> {}, scope -> {} "
                    , authType, username, clientId, scope);
        }
        // 认证服务返回结果
        JSONObject result = oauthService.getToken(
                Oauth2Constants.GRANT_MODE_PASSWORD,
                username,
                password,
                clientId,
                clientSecret,
                scope,
                authType,
                userType,
                verificationCode,
                key);

        // 转换为Result对象
        return makeJsonToResult(result, "登陆成功！");
    }

    /**
     * 调用认证服务授权码模式获取授权码
     *
     * @param response                   响应体
     * @param reqGetAuthorizationCodeDto 获取授权码 DTO
     * @throws IOException
     */
    @Override
    public void getAuthorizationCode(HttpServletResponse response, ReqGetAuthorizationCodeDTO reqGetAuthorizationCodeDto) throws IOException {
        // code 请求路径 后续使用网关路径
        String url = "http://localhost:8868/oauth/authorize?client_id=CLIENT_ID&response_type=code&redirect_uri=REDIRECT_URI";
        url = url.replace("CLIENT_ID", reqGetAuthorizationCodeDto.getClientId())
                .replace("REDIRECT_URI", reqGetAuthorizationCodeDto.getRedirectUri());

        // scope参数存在
        String scope = reqGetAuthorizationCodeDto.getScope();
        if (StringUtils.isNotBlank(scope)) {
            url = url.concat("&scope=").concat(scope);
        }
        // state参数存在
        String state = reqGetAuthorizationCodeDto.getState();
        if (StringUtils.isNotBlank(state)) {
            url = url.concat("&state=").concat(state);
        }

        log.info("获取code url -> {}", url);
        response.sendRedirect(url);
    }

    /**
     * 调用认证服务授权码模式授权认证
     *
     * @param reqGetTokenByAuthorizationCodeDto 授权码获取token DTO
     * @return
     */
    @Override
    public RestResult getTokenByAuthorizationCode(ReqGetTokenByAuthorizationCodeDTO reqGetTokenByAuthorizationCodeDto) {
        // 授权码
        String code = reqGetTokenByAuthorizationCodeDto.getCode();
        // 重定向URL
        String redirectUri = reqGetTokenByAuthorizationCodeDto.getRedirectUri();
        // 客户端ID
        String clientId = reqGetTokenByAuthorizationCodeDto.getClientId();
        // 客户端密钥
        String clientSecret = reqGetTokenByAuthorizationCodeDto.getClientSecret();


        log.info("授权码模式 code -> {}, redirect_uri -> {}, client_id -> {}", code, redirectUri, clientId);

        // 认证服务返回结果
        JSONObject result = oauthService.getTokenByAuthorizationCode(
                Oauth2Constants.GRANT_MODE_AUTHORIZATION_CODE,
                code,
                redirectUri,
                clientId, clientSecret);

        // 转换为Result对象
        return makeJsonToResult(result, "登陆成功！");
    }

    /**
     * 调用认证服务刷新令牌
     * <p>刷新token类别  1,只返回刷新的token 2,返回刷新的token及refreshToken</p>
     *
     * @param reqRefreshTokenDTO
     * @return
     */
    @Override
    public RestResult refreshToken(ReqRefreshTokenDTO reqRefreshTokenDTO) {
        // 认证服务返回结果
        JSONObject jsonObject = oauthService.getTokenByRefreshToken(
                Oauth2Constants.GRANT_MODE_REFRESH_TOKEN,
                reqRefreshTokenDTO.getRefreshToken(),
                reqRefreshTokenDTO.getClientId(),
                reqRefreshTokenDTO.getClientSecret(),
                reqRefreshTokenDTO.getScope()
        );

        // 刷新token类型为只返回token
        if (RefreshTokenType.ONLY_RETURN_TOKEN.getType().equals(reqRefreshTokenDTO.getType())) {
            // 请求成功
            if (jsonObject.containsKey(REFRESH_TOKEN_KEY)) {
                Object o = jsonObject.remove(REFRESH_TOKEN_KEY);
                log.info(o.toString());
            }
        }
        // 转换为Result对象
        return makeJsonToResult(jsonObject, "登陆成功！");
    }

    /**
     * 调用认证服务密码模式(手机号 + 验证码)授权认证
     *
     * @param reqPhoneLoginDTO 手机号验证码登录DTO
     * @return
     */
    @Override
    public RestResult loginByPhone(ReqPhoneLoginDTO reqPhoneLoginDTO) {
        // 客户端ID
        String clientId = reqPhoneLoginDTO.getClientId();
        // 客户端密钥
        String clientSecret = reqPhoneLoginDTO.getClientSecret();
        // 手机号
        String phoneNumber = reqPhoneLoginDTO.getPhoneNumber();
        // 短信验证码
        String phoneCode = reqPhoneLoginDTO.getPhoneCode();
        // 授权作用域
        String scope = reqPhoneLoginDTO.getScope();
        // 授权类型
        String authType = reqPhoneLoginDTO.getAuthType();

        log.info("密码模式（手机号验证码） authType -> {}, phoneNumber -> {},  clientId -> {}, scope -> {} "
                , authType, phoneNumber, clientId, scope);

        // 认证服务返回结果
        JSONObject result = oauthService.getTokenByPhone(
                Oauth2Constants.GRANT_MODE_PASSWORD,
                authType,
                phoneNumber,
                phoneCode,
                clientId,
                clientSecret,
                scope);

        // 转换为Result对象
        return makeJsonToResult(result, "登陆成功！");
    }

    /**
     * 用户注册
     *
     * @param phoneCode   短信验证码
     * @param phoneNumber 手机号
     * @param password    密码
     * @param shareCode   分享码
     * @return
     */
    @Override
    public RestResult userRegister(String phoneCode, String phoneNumber, String password, String shareCode) {
        // todo 验证短信验证码是否有效
        //测试代码块，所以将验证码定为：1234
        if (!"1234".equals(phoneCode)) {
            return RestResult.failed(ResultCode.SMS_CODE_BAD.getCode(), ResultCode.SMS_CODE_BAD.getMsg());
        }

        // 查询电话号是否被使用
        // 判断电话号是否注册过
        UserAccountDTO account = userService.findUserByPhone(phoneNumber);
        if (null != account) {
            return RestResult.failed(ResultCode.PHONE_ALREADY_USE.getCode(), ResultCode.PHONE_ALREADY_USE.getMsg());
        }

        // 初始化用户账户
        return addUserAccount(UUID.randomUUID().toString(), phoneNumber, password, shareCode, null);
    }

    private RestResult addUserAccount(String accountId,
                                      String phoneNumber,
                                      String password,
                                      String shareCode,
                                      ReqUserInfoInitializeDTO reqUserInfoInitializeDTO) {
        // 添加用户
        SysUser userAccount = new SysUser();
        // 用户账户ID
        userAccount.setId(accountId);
        // 默认生成用户名
        String username = "用户" + snowflake.nextId();
        userAccount.setUsername(username);
        // 手机号
        userAccount.setPhone(phoneNumber);
        // 昵称
        userAccount.setNickName(reqUserInfoInitializeDTO.getNickname());
        // 头像
        userAccount.setAvatar(reqUserInfoInitializeDTO.getHeadPortrait());
        // 不为空时设置密码
        if (StringUtils.isNotBlank(password)) {
            userAccount.setPassword(BpwdEncoderUtil.bCryptPassword(password));
        }
        // 注册时间
        userAccount.setCreateTime(LocalDateTime.now());
        // 新增用户
        if (userService.save(userAccount)) {
            // 用户账户ID
            log.info("新增用户id -> {}", accountId);
            // 新增用户角色
            SysUserRole userRole = new SysUserRole();
            SysRole role = roleService.findByValueAndOrganize("USER", "SYSTEM");
            if (role != null) {
                userRole.setRoleId(role.getId());
                userRole.setUserId(accountId);
                userRoleService.save(userRole);
                log.info("新增用户角色关系 用户id -> {}, 角色 -> {}", accountId, "USER");
            }
            // 初始化用户详情信息
            SysUserDetail userDetail = new SysUserDetail();
            userDetail.setUserId(accountId);
            userDetail.setBirthday(reqUserInfoInitializeDTO.getBirthday());
            userDetail.setSex(reqUserInfoInitializeDTO.getSex());
            userDetailService.save(userDetail);
            log.info("新增用户信息 -> {}", userDetail);
        }
        return RestResult.succeed();
    }

    /**
     * 微信授权登录注册用户
     * @param dto
     * @return
     */
    private RestResult addWeChatUserAccount(ReqUserRegisterDTO dto) {
        // 添加用户
        SysUser userAccount = new SysUser();
        // 用户账户ID
        userAccount.setId(UUID.randomUUID().toString());
        userAccount.setOpenId(dto.getOpenId());
        // 默认生成用户名
        String username = "用户" + snowflake.nextId();
        userAccount.setUsername(username);
        // 手机号
        userAccount.setPhone(dto.getPhoneNum());
        // 昵称
        userAccount.setNickName(dto.getNickName());
        // 头像
        userAccount.setAvatar(dto.getHeadPortrait());
        // 锁定状态
        userAccount.setLocked(SystemConstant.LOCK_STATUS.UN_LOCK);
        userAccount.setPassword(dto.getPassword());
        // 是否过期
        userAccount.setExpired(0);
        // 注册时间
        userAccount.setCreateTime(LocalDateTime.now());
        // 默认为非党建用户
        userAccount.setIsBuilding(1);
        // 新增用户
        if (userService.save(userAccount)) {
            // 用户账户ID
            log.info("新增用户id -> {}", userAccount.getId());
            // 新增用户角色
            SysUserRole userRole = new SysUserRole();
            SysRole role = roleService.findByValueAndOrganize("CM", "SYSTEM");
            if (role != null) {
                userRole.setRoleId(role.getId());
                userRole.setUserId(userAccount.getId());
                userRoleService.save(userRole);
                log.info("新增用户角色关系 用户id -> {}, 角色 -> {}", userAccount.getId(), "CM");
            }
            // 初始化用户详情信息
            SysUserDetail userDetail = new SysUserDetail();
            userDetail.setUserId(userAccount.getId());
            userDetail.setBirthday(dto.getBirthday());
            userDetail.setSex(dto.getSex());
            userDetailService.save(userDetail);
            log.info("新增用户信息 -> {}", userDetail);
        }
        return RestResult.succeed();
    }

    /**
     * 调用认证服务密码模式(手机号)授权认证
     *
     * @param phoneNumber  用户手机号
     * @param clientId     客户端ID
     * @param clientSecret 客户端密钥
     * @param scope        授权作用域
     * @return
     */
    @Override
    public RestResult getTokenByUserRegister(String phoneNumber, String clientId, String clientSecret, String scope) {
        log.info("密码模式（手机号） authType -> {}, phoneNumber -> {},  clientId -> {}, scope -> {} "
                , Oauth2Constants.GRANT_MODE_REGISTER, phoneNumber, clientId, scope);

        // 认证服务返回结果
        JSONObject result = oauthService.getTokenByPhone(
                Oauth2Constants.GRANT_MODE_PASSWORD,
                Oauth2Constants.GRANT_MODE_REGISTER,
                phoneNumber,
                null,
                clientId,
                clientSecret,
                scope);

        // 转换为Result对象
        return makeJsonToResult(result, "登陆成功！");
    }

    /**
     * 微信小程序登录
     *
     * @param reqWeChatLoginDTO 微信小程序登录DTO
     * @return
     */
    @Override
    public RestResult loginByWeChat(ReqWeChatAppletLoginDTO reqWeChatLoginDTO) {
        log.info("微信小程序登录请求参数:{}", JSONObject.toJSONString(reqWeChatLoginDTO));
        //获取accessToken
        String accessToken = this.getWeChatAccessToken();
        Assert.isEmpty(accessToken, "微信参数异常，请求微信服务器失败，请重试");

        //通过loginCode获取微信授权信息 -- 注意：在开发阶段，如果不是小程序开发组成员，微信服务器会直接返回null
        JSONObject authInfo = this.getWeChatAuthByCode(reqWeChatLoginDTO.getLoginCode());
        Assert.isEmpty(authInfo, "微信参数异常，open_id为空，请重试");

        //获取openId
        String openId = authInfo.get("openid").toString();
        //通过openId获取系统中的用户
        SysUser user = userService.findUserByOpenId(openId);
        //请求登录用户不为空,直接登录并返回
        if (ObjectUtils.isNotNull(user)) {
            //账号是否被封号
            isLocked(user);
            //获取token
            JSONObject tokenByWeChat = oauthService.getTokenByWeChat(
                    Oauth2Constants.GRANT_MODE_PASSWORD,
                    AuthTypeConstant.LOGIN_TYPE_WECHAT,
                    openId,
                    oauth2Properties.getClientId(),
                    oauth2Properties.getClientSecret(),
                    oauth2Properties.getScope());
            return makeJsonToResult(tokenByWeChat, "登录成功！");
        }

        log.debug("openId：{} 的用户首次使用微信小程序登录", openId);

        //通过code、解密串、解密向量请求获取微信授权信息
        String phoneNumber = this.getWeChatPhoneNumber(reqWeChatLoginDTO, accessToken);
        Assert.isEmpty(phoneNumber, "微信参数异常，获取微信绑定手机号失败，请重试");

        //注册用户基本信息
        ReqUserRegisterDTO registerDTO = new ReqUserRegisterDTO();
        //设置默认密码（认证时直接重置密码）
        registerDTO.setPassword(BpwdEncoderUtil.bCryptPassword(SystemBaseConstants.SYSTEM_DEFAULT_PASSWORD_VALUE));
        registerDTO.setPhoneNum(phoneNumber);
        registerDTO.setShareCode(null);
        registerDTO.setOpenId(openId);
        registerDTO.setNickName("微信用户");
        addWeChatUserAccount(registerDTO);

        //获取token并返回登录信息
        return makeJsonToResult(oauthService.getTokenByWeChat(
                Oauth2Constants.GRANT_MODE_PASSWORD,
                AuthTypeConstant.LOGIN_TYPE_WECHAT,
                openId,
                oauth2Properties.getClientId(),
                oauth2Properties.getClientSecret(),
		        oauth2Properties.getScope()), "登录成功！");
    }

    @Override
    public RestResult loginOut(String token) {
        if (StringUtils.isBlank(token)) {
            token = SecurityUtil.getCurrentTokenValue();
        }
        JSONObject result = oauthService.logout(token);
        return makeJsonToResult(result, "登出成功！");
    }

    /**
     * 处理认证服务返回结果为系统返回结果Result
     *
     * @param result 认证服务返回结果
     * @return org.surge.oauth2common.model.Result
     * @author qipp
     * @date 2020/5/6 10:34
     */
    private RestResult<Object> makeJsonToResult(JSONObject result, String msg) {
        // 是否为RestResult对象
        if (result.containsKey(SUCCESS_FLAG)) {
            return result.toJavaObject(RestResult.class);
        }
        log.error(result.toJSONString());
        return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(),result.toJSONString());
    }

    /**
     * 获取微信accessToken
     * access_token的有限期在两个小时，并且重复获取会导致之前获取的access_token失效
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET
     *
     * @return
     */
    private String getWeChatAccessToken() {
        //从缓存中获取accessToken，获取到直接返回
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        String accessToken = (String) redisTemplate.boundValueOps(SystemBaseConstants.WECHAT_ACCESS_TOKEN_KEY).get();
        if (!StringUtils.isBlank(accessToken)) {
            return accessToken;
        }

        //组装获取accessToken的URL
        StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=")
                .append(weChatConfig.getApplet().getAppId())
                .append("&secret=")
                .append(weChatConfig.getApplet().getAppSecret());

        ResponseEntity<String> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, String.class);
        //获取请求返回实体并解析
        String body = responseEntity.getBody();
        JSONObject accessTokenJSON = JSONObject.parseObject(body);

        //异常信息,统一外层处理
        if (accessTokenJSON.containsKey("errcode")) {
            log.error("微信授权登录通过code获取微信授权信息发生异常，异常状态码为：{}，异常信息为：{}",
                    accessTokenJSON.getString("errcode"), accessTokenJSON.getString("errmsg"));
            return null;
        }
        String saveAccessToken = accessTokenJSON.get("access_token").toString();

        //此处会发生线程安全问题，两个用户同时请求到了过期的缓存，后执行获取accessToken的线程，会导致先获取accessToken的线程中的token失效，导致先获取的线程在后续的操作中发生异常
        //并且后续可能会是集群发布服务,所以在集群环境中,采取分布式锁来进行隔离
        RReadWriteLock readWriteLock = singleRedissonClient.getReadWriteLock("weChat_accessToken_lock");
        RLock rLock = readWriteLock.readLock();
        try {
            rLock.lock();
            return setAccessTokenFromRedis(saveAccessToken);
        } catch (Exception e) {
            log.error("分布式锁中设置accessToken失败，异常信息为：{}", e.getMessage());
            throw new CustomException("微信参数异常，请求微信服务器失败，请重试");
        } finally {
            rLock.unlock();
        }
    }

    private String setAccessTokenFromRedis(String saveAccessToken) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        String accessTokenLock = (String) redisTemplate.boundValueOps(SystemBaseConstants.WECHAT_ACCESS_TOKEN_KEY).get();
        if (!StringUtils.isBlank(accessTokenLock)) {
            return accessTokenLock;
        }
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.boundValueOps(SystemBaseConstants.WECHAT_ACCESS_TOKEN_KEY).set(saveAccessToken, 100, TimeUnit.MINUTES);
        return saveAccessToken;
    }

    /**
     * 通过code、解密串、解密向量请求获取微信授权信息
     * https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=ACCESS_TOKEN
     *
     * @param reqWeChatAppletLoginDTO code、解密串、解密向量
     * @param accessToken             微信token
     * @return
     */
    private String getWeChatPhoneNumber(ReqWeChatAppletLoginDTO reqWeChatAppletLoginDTO, String accessToken) {
        Map<String, String> map = new HashMap(16);
        map.put("code", reqWeChatAppletLoginDTO.getCode());
        //设置请求头和字符类型
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        //组装请求头和参数
        HttpEntity formEntity = new HttpEntity(map, headers);
        String str = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(str, formEntity, String.class);

        //获取请求返回实体并解析
        String body = responseEntity.getBody();
        JSONObject phoneNumberJSON = JSONObject.parseObject(body);

        //异常信息,统一外层处理
        if (!UserConstant.WECHAT_PHONE_NUMBER_SUCCESS_CODE.equals(phoneNumberJSON.get("errcode").toString())) {
            log.error("微信获取手机号发生异常，异常状态码为：{}，异常信息为：{}",
                    phoneNumberJSON.getString("errcode"), phoneNumberJSON.getString("errmsg"));
            return null;
        }
        JSONObject phoneInfo = (JSONObject) phoneNumberJSON.get("phone_info");
        return phoneInfo.get("phoneNumber").toString();

    }

    /**
     * 通过code获取微信授权信息
     * 请求链接：https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     *
     * @param code 请求凭证
     * @return
     */
    private JSONObject getWeChatAuthByCode(String code) {
        //组装获取微信授权信息的请求链接
        StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?appid=")
                .append(weChatConfig.getApplet().getAppId())
                .append("&secret=")
                .append(weChatConfig.getApplet().getAppSecret())
                .append("&js_code=")
                .append(code)
                .append("&grant_type=authorization_code");

        //发送请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, String.class);
        //获取请求返回实体
        String body = responseEntity.getBody();

        Assert.isEmpty(body, "获取微信授权信息异常");
        //解析为json类型数据
        JSONObject authInfo = JSONObject.parseObject(body);
        if (authInfo.containsKey("errcode")) {
            log.error("微信授权登录通过code获取微信授权信息发生异常，异常状态码为：{}，异常信息为：{}",
                    authInfo.getString("errcode"), authInfo.getString("errmsg"));
            return null;
        }
        return authInfo;
    }

    /**
     * 账号是否被封号
     *
     * @param user 用户信息
     */
    private void isLocked(SysUser user) {
        //相等则被封号，不允许登录
        if (Objects.equals(SystemConstant.LOCK_STATUS.LOCKED, user.getLocked())) {
            throw new CustomException("该账号已被锁定");
        }
    }


}
