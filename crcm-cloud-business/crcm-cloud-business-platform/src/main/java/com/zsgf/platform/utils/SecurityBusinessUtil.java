package com.zsgf.platform.utils;

import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @ClassName SecurityBusinessUtil
 * @Description 系统权限工具
 * @Author GZL
 * @Date 2023/3/28 10:05
 * @Version 1.0
 **/
public class SecurityBusinessUtil extends SecurityUtil {
    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     * @Author GZL
     * @Date 2023/3/28 10:08
     * @Param limitCompany 是否限定只有企业能操作
     **/
    public static AuthUser getCurrentUserInfo(boolean limitCompany) {
        AuthUser currentUser = getCurrentUser();
        // 用户未登录
        if (Objects.isNull(currentUser)) {
            throw new CustomException(ResultCode.TOKEN_AUTH_FAILED.code, ResultCode.TOKEN_AUTH_FAILED.msg);
        }
//        if (limitCompany && (!Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, currentUser.getUserType())
//                || StringUtils.isBlank(currentUser.getCompanyId()))) {
//            throw new CustomException(ResultCode.NOT_IS_COMPANY.code, ResultCode.NOT_IS_COMPANY.msg);
//        }
        return currentUser;
    }

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     * @Author GZL
     * @Date 2023/3/28 10:08
     **/
    public static AuthUser getCurrentUserInfo() {
        return getCurrentUserInfo(false);
    }

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     * @Author GZL
     * @Date 2023/3/28 10:08
     **/
    public static String getCurrentNickName() {
        AuthUser currentUserInfo = getCurrentUserInfo(false);
        return currentUserInfo.getNickName();
    }
}
