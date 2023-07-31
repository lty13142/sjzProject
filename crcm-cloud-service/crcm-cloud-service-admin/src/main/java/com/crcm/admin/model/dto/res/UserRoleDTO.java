package com.crcm.admin.model.dto.res;

import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.vo.UserInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserRoleVO
 * @Description 用户角色传输类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/4/30 11:25
 **/
@Data
public class UserRoleDTO implements Serializable {

    private static final long serialVersionUID = 609066218825054677L;
    private String userId;
    private String account;
    private String roleId;
    private String roleIds;
    private List<SysRole> SysRoleS;
    private UserInfoVO userInfo;
}
