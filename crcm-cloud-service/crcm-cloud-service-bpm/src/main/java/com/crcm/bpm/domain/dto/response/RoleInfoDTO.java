package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName RoleInfoDTO
 * @Description：角色信息数据传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/29
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleInfoDTO {
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;
}
