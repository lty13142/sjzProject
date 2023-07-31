package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:流程-角色关联表 </p>
 * <p>Description:流程-角色关联表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author ${Author}
 * @Date  2020-10-28
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("流程-角色关联表")
@TableName("bpm_process_role")
public class ProcessRoleDo extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * id
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 流程id
     * process_no：varchar(32) ==》 processNo：String
     */
    @ApiModelProperty(value = "流程id")
    private Long processId;
    /**
     * 角色id
     * process_name：varchar(64) ==》 processName：String
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;
    /**
     * 公司id
     * process_name：varchar(64) ==》 processName：String
     */
    @ApiModelProperty(value = "公司id")
    private String companyId;
}