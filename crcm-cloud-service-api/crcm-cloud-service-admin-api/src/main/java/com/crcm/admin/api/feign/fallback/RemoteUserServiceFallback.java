package com.crcm.admin.api.feign.fallback;

import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.dto.res.UserBaseInfoVO;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.core.response.RestResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RemoteUserServiceFallback
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/5
 **/
@Slf4j
public class RemoteUserServiceFallback implements RemoteUserService {

    @Setter
    private Throwable cause;

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @param from     内外标志
     * @return R
     */
    @Override
    public RestResult<UserAccountDTO> getByUsername(String username, String from) {
        log.error("feign 查询用户信息失败:{}", username, cause);
        return null;
    }

    @Override
    public RestResult<UserAccountDTO> getByPhoneNumber(String phoneNum, String from) {
        log.error("feign 查询用户信息失败:{}", phoneNum, cause);
        return null;
    }

    @Override
    public void updateUserLoginTime(String userName) {
        log.error("feign 更新用户登录时间失败:{}", userName, cause);
    }

    @Override
    public RestResult<UserAccountDTO> getByOpenId(String unionId, String from) {
        log.error("feign 微信用户查询信息失败:{}", unionId, cause);
        return null;
    }

    @Override
    public RestResult<Object> getDjInfo(String code, String from) {
        return null;
    }

    @Override
    public RestResult<Object> editUserInfo(UserBaseInfoVO userInfoVo, String from) {
        log.error("feign 修改用户基本信息失败:{}", cause);
        return null;
    }

    @Override
    public RestResult<UserBaseInfoVO> getUserBaseInfo(String id, String from) {
        log.error("feign 获取用户基本信息失败:{}", cause);
        return null;
    }

    @Override
    public RestResult<UserBaseInfoVO> getUserByOrgId(UserBaseInfoVO userInfoVo, String from) {
        return null;
    }

}
