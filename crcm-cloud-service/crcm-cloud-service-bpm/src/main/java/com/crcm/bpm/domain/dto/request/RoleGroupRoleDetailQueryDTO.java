package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.*;

/**
 * @ClassName RoleGroupRoleDetailQueryDTO
 * @Description：
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
public class RoleGroupRoleDetailQueryDTO extends BaseRequestDTO {
    private static final long serialVersionUID = 3120277011676353337L;

    private Long roleGroupId;

    private String roleGroupCode;

    private String businessLine;

    private Integer roleGroupLevel;

    private Integer roleGroupType;

    private String tenantId;

    private Long roleId;

    private String roleCode;

    private Long companyId;

    private Long deptId;

    private int pageSize;

    private int pageIndex;
}
