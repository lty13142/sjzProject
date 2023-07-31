package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName UserInfoDTO
 * @Description：用户信息传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/21
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    /** 主键ID */
    private String id;

    /** 用户名 */
    private String username;

    /** 简介 */
    private String phone;

    /** 头像 */
    private String avatar;

    /** 所属租户 */
    private String tenantId;

    /** 姓名 */
    private String name;
    /** 角色 */
    private List<SysRoleDTO> roles;
    /** 公司ID */
    private String companyId;
    private String companyCode;
    private String companyName;
    /** 所属部门ID集合 */
    private List<String> deptIdList;

    private String deptId;
    private String deptCode;
    private String deptName;
}
