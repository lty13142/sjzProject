package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单/模板管理日志
 * </p>
 *
 * @author
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bpm_form_use_log")
@ApiModel(value="FormModelUseLog对象", description="表单管理日志")
public class FormUseLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "调用方法")
    private String useUri;

    @ApiModelProperty(value = "调用方法名")
    private String useMethod;

    @ApiModelProperty(value = "使用者用户id")
    private String useUserId;

    @ApiModelProperty(value = "使用者用户名")
    private String useUserName;

    @ApiModelProperty(value = "原始数据")
    private String oldData;

    @ApiModelProperty(value = "新数据")
    private String newData;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "调用者ip")
    private String useIp;

    @ApiModelProperty(value = "业务id")
    private String businessId;

    public FormUseLogDO(){}

    public FormUseLogDO(Long id, String useUri, String useMethod, String useUserId, String useUserName,
                        String oldData, String newData, String useIp, String businessId){
        this.id = id;
        this.useUri = useUri;
        this.useMethod = useMethod;
        this.useUserId = useUserId;
        this.useUserName = useUserName;
        this.oldData = oldData;
        this.newData = newData;
        this.useIp = useIp;
        this.businessId = businessId;
    }
}
