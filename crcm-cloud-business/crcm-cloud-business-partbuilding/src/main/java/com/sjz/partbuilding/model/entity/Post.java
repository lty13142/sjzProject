package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>Title:党内职务表 </p>
 * <p>Description:党内职务表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-09-23
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("党内职务表")
@TableName("dj_post")
public class Post extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
        @TableId(value = "id", type = IdType.UUID)
        private String id;
    /**
     * 职务名称
     * post_name：varchar(255) ==》 postName：String
     */
    @ApiModelProperty(value = "职务名称")
    private String postName;
    /**
     * 父角色id
     * pid：varchar(32) ==》 pid：String
     */
    @ApiModelProperty(value = "父角色id")
    private String pid;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;



}