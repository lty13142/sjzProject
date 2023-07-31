package com.crcm.file.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName AttachmentDO
 * @Description 附件表
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/8/12
 **/
@Data
@ApiModel("附件表")
@TableName("sys_attachment")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 6248155142648243700L;
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
     * 上传文件/合并文件的格式
     * suffix：varchar(32) ==》 suffix：String
     */
    @ApiModelProperty(value = "上传文件/合并文件的格式")
    private String suffix;
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
    private Integer uploadMode;
    /**
     * 文件MD5识别码
     * md5：varchar(255) ==》 md5：String
     */
    @ApiModelProperty(value = "文件MD5识别码")
    private String md5;
    /**
     * 上传状态 0.上传完成 1.已上传部分
     * upload_status：int(11) ==》 uploadStatus：Integer
     */
    @ApiModelProperty(value = "上传状态 0.未上传 1.已上传部分 2 上传完成")
    private Integer uploadStatus;
    /**
     * 存储桶名称
     * bucket_name：varchar(255) ==》 bucketName：String
     */
    @ApiModelProperty(value = "存储桶名称")
    private String bucketName;
    /**
     * 分片数量
     * chunk_count：int(11) ==》 chunkCount：Integer
     */
    @ApiModelProperty(value = "分片数量")
    private Integer chunkCount;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 获取文件全名
     * @return
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getFileFullName() {
        return this.fileName + this.suffix;
    }

}