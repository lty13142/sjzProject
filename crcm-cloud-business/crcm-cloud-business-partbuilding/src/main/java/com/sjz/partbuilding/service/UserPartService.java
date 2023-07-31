package com.sjz.partbuilding.service;

import com.crcm.core.dto.TreeView;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.user.LoginOrgVo;

import java.util.List;

public interface UserPartService {
    /**
     * 根据组织活动类型获取出席人员
     *
     * @param orgEvent
     * @return
     */
    List<TreeView> getUserByType(OrgEvent orgEvent, String orgPersonId);
    /**
     * 获取当前登陆人的组织信息
     *
     * @return
     */
    LoginOrgVo getCurrentLoginOrg(String OrgId);
    /**
     * 判断是否有树权限
     *
     * @param orgType
     * @return
     */
    boolean toOrgType(String orgType);

}
