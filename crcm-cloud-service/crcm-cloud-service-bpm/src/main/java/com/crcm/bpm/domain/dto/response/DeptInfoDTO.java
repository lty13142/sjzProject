package com.crcm.bpm.domain.dto.response;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @ClassName DeptInfoDTO
 * @Description：部门信息传输类
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
public class DeptInfoDTO {

    /**
     * id
     * id：int ==》 id：Integer
     */
    private String id;
    /**
     * 部门名称
     * dept_name：varchar(64) ==》 deptName：String
     */
    private String deptName;
    /**
     * 部门编码
     * dept_code：varchar(32) ==》 deptCode：String
     */
    private String deptCode;
    /**
     * 所属公司编码
     */
    private String companyId;
    /**
     * 所属租户
     */
    private String tenantId;
    /**
     * 上级部门编号
     */
    private String deptParentId;
    /**
     * 上级部门编码
     */
    private String deptParentCode;
    /**
     * 部门层级
     */
    private Integer deptLevel;
    /**
     * 部门类型
     */
    private String deptType;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 部门负责人岗位ID
     */
    private String deptHeader;
    /**
     * 部门负责人
     */
    private String deptManager;
    /**
     * 部门负责人名称
     */
    private String deptHeaderName;
}
