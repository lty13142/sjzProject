package com.crcm.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.auth.service.UserAccountService;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.UserType;
import com.crcm.core.response.RestResult;
import com.crcm.security.bean.Authority;
import com.crcm.security.bean.Role;
import com.crcm.security.bean.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;

/**
 * @ClassName UserAccountServiceImpl
 * @Description 用户账户服务实现
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    /**
     * 远程调用用户管理服务
     */
    private final RemoteUserService remoteUserService;

    @Override
    public UserAccount selectByUserName(String username, String userType) {
        RestResult<UserAccountDTO> result = null;
        if (UserType.ADMIN.getValue().equals(userType)) {
            // 后台管理用户
            result = remoteUserService.getByUsername(username, AuthConstants.FROM_IN);
        } else if (UserType.ENTERPRISE.getValue().equals(userType)) {
            // 企业用户
//            result = userAccountService.selectByUserName(username);
        } else if (UserType.SUPPLIER.getValue().equals(userType)) {
            // 供应商用户
//            result = userAccountService.selectByUserName(username);
        } else if (UserType.SERVICES.getValue().equals(userType)) {
            // 服务商用户
//            result = userAccountService.selectByUserName(username);
        }

        if (result != null && result.isSuccess() && result.getData() != null) {
            return caseToUserAccount(result.getData());
        }
        return null;
    }

    @Override
    public UserAccount selectByPhone(String phoneNum, String userType) {
        RestResult<UserAccountDTO> result = remoteUserService.getByPhoneNumber(phoneNum, AuthConstants.FROM_IN);
        if (result.isSuccess() && result.getData() != null) {
            return caseToUserAccount(result.getData());
        }
        return null;
    }

    @Override
    public UserAccount composeUserAccountAndAuthority(UserAccount userAccount) {
//        remoteUserService.getUserAuthority(userAccount.getUsername(),AuthConstants.FROM_IN);
        return userAccount;
    }

    @Override
    public void validateUser(UserAccount userAccount) {

    }

    @Override
    public UserAccount selectWeChatUnionId(String unionId) {
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        RestResult<UserAccountDTO> result = remoteUserService.getByOpenId(unionId, AuthConstants.FROM_IN);
        if (!Objects.equals(HttpStatus.HTTP_OK,result.getCode())){
            return null;
        }
        UserAccount userAccount = new UserAccount();
        UserAccountDTO data = result.getData();
        BeanUtil.copyProperties(data,userAccount);
        // 组装角色数据
        if (CollectionUtil.isNotEmpty(data.getRoles())) {
            data.getRoles().forEach(role -> authorities.add(BeanUtil.copyProperties(role, Role.class)));
        }
        // 组装资源数据
        if (CollectionUtil.isNotEmpty(data.getResources())) {
            data.getResources().forEach(s -> authorities.add(BeanUtil.copyProperties(s, Authority.class)));
        }
        userAccount.setEnabled(data.getEnabled()==Integer.valueOf(0));
        userAccount.setUserId(data.getId());
        userAccount.setAccountNonExpired(data.getEnabled()==Integer.valueOf(0));
        userAccount.setAccountNonLocked(data.getLocked()==Integer.valueOf(0));
        userAccount.setCredentialsNonExpired(data.getExpired()==Integer.valueOf(0));
        userAccount.setAuthorities(authorities);
        return userAccount;
    }

    /**
     * 修改用户登录时间
     * @Author GZL
     * @Date 2023/2/2 9:18
     * @Param userName 用户名
     **/
    @Override
    public void updateUserLoginTime(String userName) {
        remoteUserService.updateUserLoginTime(userName);
    }

    private UserAccount caseToUserAccount(UserAccountDTO dto) {
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        // 组装角色数据
        if (CollectionUtil.isNotEmpty(dto.getRoles())) {
            dto.getRoles().forEach(role -> authorities.add(BeanUtil.copyProperties(role, Role.class)));
        }
        // 组装资源数据
        if (CollectionUtil.isNotEmpty(dto.getResources())) {
            dto.getResources().forEach(s -> authorities.add(BeanUtil.copyProperties(s, Authority.class)));
        }
        return new UserAccount().setUserId(String.valueOf(dto.getId()))
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setPhone(dto.getPhone())
                .setAccountNonExpired(Objects.equals(SystemConstant.YES_OR_NO.NO, dto.getExpired()))
                .setAccountNonLocked(Objects.equals(SystemConstant.YES_OR_NO.NO, dto.getLocked()))
                .setCredentialsNonExpired(Objects.equals(SystemConstant.YES_OR_NO.NO, dto.getExpired()))
                .setEnabled(Objects.equals(SystemConstant.YES_OR_NO.YES, dto.getEnabled()))
                // 角色不能为空
                .setAuthorities(authorities)
                .setOrgId(dto.getOrgId())
                .setAreaCode(dto.getAreaCode())
                .setArea(dto.getArea())
                .setUserType(dto.getUserType())
                .setUserDetailType(dto.getUserDetailType())
                .setNickName(dto.getNickName());
    }
}
