package com.crcm.auth.service;

import com.crcm.security.bean.UserAccount;

/**
 * @ClassName UserAccountService
 * @Description 用户账户服务
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public interface UserAccountService {
    /**
     * 根据用户名或手机号查询用户账户
     *
     * @param username 用户名或手机号
     * @return UserAccount
     */
    UserAccount selectByUserName(String username, String userType);

    /**
     * 根据手机号查询用户账户
     *
     * @param phone 手机号
     * @return UserAccount
     */
    UserAccount selectByPhone(String phone, String userType);

    /**
     * 组合用户账户对象与角色权限
     *
     * @param userAccount 用户账户对象
     * @return UserAccount
     */
    UserAccount composeUserAccountAndAuthority(UserAccount userAccount);

    /**
     * 验证用户是否可用
     *
     * @param userAccount 用户账户对象
     */
    void validateUser(UserAccount userAccount);

    /**
     * 根据微信unionId查询用户
     *
     * @param unionId 微信Id
     * @return UserAccount
     */
    UserAccount selectWeChatUnionId(String unionId);

    /**
     * 修改用户登录时间
     * @Author GZL
     * @Date 2023/2/2 9:18
     * @Param userName 用户名
     **/
    void updateUserLoginTime(String userName);
}
