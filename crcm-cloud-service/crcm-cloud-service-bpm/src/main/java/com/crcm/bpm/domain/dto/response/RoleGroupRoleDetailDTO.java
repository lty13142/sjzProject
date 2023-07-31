package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName RoleGroupRoleDetailDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
@ToString
public class RoleGroupRoleDetailDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -6583380907808637655L;

    private Long roleGroupId;

    private String roleGroupCode;

    private String roleGroupName;

    private String roleGroupBusinessLine;

    private Integer roleGroupLevel;

    private Integer roleGroupType;

    private String tenantId;

    private Long roleId;

    private String roleName;

    private String roleCode;

    private String roleAbbr;

    private Integer  roleLevel;

    private String roleAliasName;

    private Long companyId;

    private String companyCode;

    private String companyName;

    private Long deptId;

    private String deptName;

    private String deptCode;

    private String deptBusinessLine;
}
