package com.crcm.admin.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserRoleVo
 * @Description 用户角色传输类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/4/30 11:25
 **/
@Data
@Accessors(chain = true)
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = 609066218825054677L;
    private Long userId;
    private String username;
    private String personName;
    private Long roleId;
    private List<String> userIds;
//    private String roleIds;
//    private List<SysRole> roles;
}
