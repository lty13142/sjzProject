package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>Title:附件表 </p>
 * <p>Description:附件表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${USER}
 * @version 1.0
 * @Date 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("附件表")
@TableName("dj_sys_attachments")
public class Attachments extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 文件名
     * file_name：varchar(255) ==》 fileName：String
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;
    /**
     * 文件全名
     * file_full_name：varchar(255) ==》 fileFullName：String
     */
    @ApiModelProperty(value = "文件全名")
    private String fileFullName;
    /**
     * 文件大小
     * size：varchar(32) ==》 size：String
     */
    @ApiModelProperty(value = "文件大小")
    private String size;
    /**
     * 文件路径
     * path：varchar(255) ==》 path：String
     */
    @ApiModelProperty(value = "文件路径")
    private String path;
    /**
     * 文件类型
     * file_type：varchar(255) ==》 fileType：String
     */
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    /**
     * 文件后缀
     * ext：varchar(32) ==》 ext：String
     */
    @ApiModelProperty(value = "文件后缀")
    private String ext;
    /**
     * 存储文件名称
     * save_name：varchar(255) ==》 saveName：String
     */
    @ApiModelProperty(value = "存储文件名称")
    private String saveName;
    /**
     * 文件上传方式
     * upload_mode：varchar(32) ==》 uploadMode：String
     */
    @ApiModelProperty(value = "文件上传方式")
    private String uploadMode;
    /**
     * 文件上传方式
     * upload_mode：varchar(32) ==》 uploadMode：String
     */
    @ApiModelProperty(value = "文件上传方式")
    private String minioPath;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;


}