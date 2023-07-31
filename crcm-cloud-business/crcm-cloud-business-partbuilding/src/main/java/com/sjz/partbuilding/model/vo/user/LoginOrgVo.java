package com.sjz.partbuilding.model.vo.user;

import lombok.Data;

/**
 * 登录组织信息表
 * @author rmc
 * @version 1.0
 * @date 2020/10/19 0:05
 */
@Data
public class LoginOrgVo {

    /**
     * 组织名
     */
    private String OrgName;
    /**
     * 组织id
     */
    private String OrgId;
    /**
     * 判断组织性质
     */
    private Boolean orgType;
    /**
     * 组织性质
     */
    private String type;
    /**
     * 公司ID
     */
    private String CompanyId;
    /**
     * 公司名称
     */
    private String SubPartyName;
}
