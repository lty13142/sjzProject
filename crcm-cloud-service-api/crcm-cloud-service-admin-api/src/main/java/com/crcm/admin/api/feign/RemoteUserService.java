package com.crcm.admin.api.feign;

import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.dto.res.UserBaseInfoVO;
import com.crcm.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName RemoteUserService
 * @Description 远程调用用户服务
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.ADMIN_SERVICE,
        fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping(value = "/user/account")
    RestResult<UserAccountDTO> getByUsername(@RequestParam("username") String username, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 通过手机号查询用户信息
     *
     * @param phoneNum
     * @param from
     * @return
     */
    @GetMapping("/user/phone")
    RestResult<UserAccountDTO> getByPhoneNumber(@RequestParam("phoneNum") String phoneNum, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 修改用户登录时间
     *
     * @Author GZL
     * @Date 2023/2/2 9:18
     * @Param userName 用户名
     **/
    @PostMapping("/user/updateLoginTime")
    void updateUserLoginTime(@RequestBody String userName);

    /**
     * 通过微信用户openId获取用户信息
     *
     * @param unionId 用户名
     * @return R
     */
    @GetMapping(value = "/user/getByOpenId")
    RestResult<UserAccountDTO> getByOpenId(@RequestParam("unionId") String unionId, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 登录用户信息获取-智慧党建
     *
     * @param code 用户名
     * @return R
     */
    @GetMapping(value = "/user/getDjInfo")
    RestResult<Object> getDjInfo(@RequestParam("code") String code, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 用户信息修改
     *
     * @param userInfoVo 用户相关信息
     * @param from
     * @return
     */
    @PostMapping("/user/editInfo")
    RestResult<Object> editUserInfo(@Valid @RequestBody UserBaseInfoVO userInfoVo, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 获取用户账号基本信息
     *
     * @param id 用户id
     * @param from
     * @return
     */
    @GetMapping("/user/getBaseInfo")
    RestResult<UserBaseInfoVO> getUserBaseInfo(@RequestParam("id") String id, @RequestHeader(AuthConstants.FROM) String from);

    @PostMapping("/user/getUserByOrgId")
    RestResult<UserBaseInfoVO> getUserByOrgId(@RequestBody UserBaseInfoVO userInfoVo, @RequestHeader(AuthConstants.FROM) String from);
}
