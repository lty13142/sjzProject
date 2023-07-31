package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.*;

import java.util.List;

/**
 * @ClassName UserRoleDetailQueryDTO
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
public class UserRoleDetailQueryDTO extends BaseRequestDTO {
    private static final long serialVersionUID = 2366765530178876011L;
    /**
     * 用户ID 集合
     */
    private List<String> userIdList;
    /**
     * 角色ID集合
     */
    private List<String> roleIdList;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 公司ID
     */
    private String companyId;
}
