package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ApplyUserInfoDTO
 * @Description：申请用户信息传输类
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
public class ApplyUserInfoDTO implements Serializable {

    private static final long serialVersionUID = 3005642456568132789L;
    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;
    /** 公司ID */
    private String companyId;
    /** 公司名称 */
    private String companyName;
    /** 部门ID */
    private String deptId;
    /** 部门名称 */
    private String deptName;
    /** 所属租户 */
    private String tenantId;
}
