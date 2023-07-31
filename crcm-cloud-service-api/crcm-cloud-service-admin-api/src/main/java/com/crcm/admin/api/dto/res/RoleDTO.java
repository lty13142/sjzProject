package com.crcm.admin.api.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName RoleDTO
 * @Description 系统角色数据
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 7524829479927111250L;
    /**
     * ID
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 授权标识
     */
    private String value;
    /**
     * 机构
     */
    private String organize;
}
