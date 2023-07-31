package com.zsgf.platform.vo.study;

import com.alibaba.fastjson.annotation.JSONField;
import com.crcm.core.constant.DicConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsgf.platform.utils.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @ClassName DocumentInfoVo
 * @Description 文档信息Vo
 * @Author GZL
 * @Date 2023/3/28 9:31
 * @Version 1.0
 **/
@Data
public class DocumentInfoVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    private String fileId;

    /**
     * 类型（政策法规、指导行文件、标准规范、相关函复等）
     */
    @ApiModelProperty(value = "类型（政策法规、指导行文件、标准规范、相关函复等, 字典：STUDY_DOCUMENT_TYPE）")
    private Integer docType;

    /**
     * 上传人
     */
    @ApiModelProperty(value = "上传人")
    private String createUser;

    /**
     * 创建时间 公共字段自动填充（新增），详见com.zzyt.core.config.mybatis.MybatisMateHandler
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    public String getShapeCh() {
        return UtilDic.getDictName(DicConstant.STUDY_DOCUMENT_TYPE.CODE, String.valueOf(this.getDocType()));
    }
}
