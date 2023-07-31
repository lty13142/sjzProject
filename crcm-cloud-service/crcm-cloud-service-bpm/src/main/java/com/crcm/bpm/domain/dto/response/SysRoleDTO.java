package com.crcm.bpm.domain.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName SysRoleDTO
 * @Description：系统角色数据传输类
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
public class SysRoleDTO {
    /**
     * ID
     */
    private String id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 授权标识
     */
    @ApiModelProperty(value = "授权标识")
    private String authorizedSigns;
    /**
     * 父角色id
     */
    private String pid;

    /**
     * 公司id
     */
    private String comId;

    /**
     * 角色备注
     */
    private String roleRemark;
    /**
     * 角色类型
     */
    private String type;
}
