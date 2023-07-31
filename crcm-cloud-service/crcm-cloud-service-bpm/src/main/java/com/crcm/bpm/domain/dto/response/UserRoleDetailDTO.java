package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.*;

/**
 * @ClassName UserRoleDetailDTO
 * @Description：用户角色信息传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDetailDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -8898425989864063552L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司代码
     */
    private String companyCode;
}
