package com.crcm.admin.task;

import com.crcm.admin.model.dto.user.UserQueryDTO;
import com.crcm.admin.model.entity.SysUser;
import com.crcm.admin.service.UserService;
import com.crcm.core.constant.SystemConstant;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserEnableJob
 * @Description 不活跃用户处理任务
 * @Author GZL
 * @Date 2023/2/2 9:56
 * @Version 1.0
 **/
@Slf4j
@Component
public class InactiveUserHandleJob {

    /**
     * 限定最大天数
     */
    private final Integer LIMIT_MAX_DAY = 60;

    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        InactiveUserHandleJob.userService = userService;
    }

    @XxlJob("inactiveUserHandle")
    public void inactiveUserHandle() {
        // 查询所有用户
        List<SysUser> userList = userService.findList(new UserQueryDTO());
        // 不活跃用户列表
        List<SysUser> userOutTmeList = new ArrayList<>();
        userList.stream().filter(user -> Objects.equals(SystemConstant.ENABLE_STATUS.ENABLE, user.getEnabled())
                && ((Objects.isNull(user.getLastLoginTime()) && user.getLastActiveTime().plusDays(LIMIT_MAX_DAY).isBefore(LocalDateTime.now()))
                || (Objects.nonNull(user.getLastLoginTime()) && user.getLastLoginTime().plusDays(LIMIT_MAX_DAY).isBefore(LocalDateTime.now())))).forEach(user -> {
            user.setEnabled(SystemConstant.ENABLE_STATUS.DISABLE);
            userOutTmeList.add(user);
        });
        if (CollectionUtils.isNotEmpty(userOutTmeList)) {
            userService.updateBatchById(userOutTmeList);
        }
        XxlJobHelper.log("共发现并处理不活跃用户 {} 个！！！", userOutTmeList.size());
    }
}
