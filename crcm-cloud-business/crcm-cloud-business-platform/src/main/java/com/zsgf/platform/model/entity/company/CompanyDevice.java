package com.zsgf.platform.model.entity.company;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 公司设备信息对象 tbl_company_device
 *
 * @author gzl
 * @date 2023-03-24
 */
@Setter
@Getter
@ToString
@ApiModel("公司设备信息")
@TableName("tbl_company_device")
public class CompanyDevice extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 100, message = "id最多输入100个字符")
    private String id;

    /**
     * 所属公司id
     */
    @ApiModelProperty(value = "所属公司id")
    @Size(max = 100, message = "所属公司id最多输入100个字符")
    private String companyId;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    @Size(max = 5, message = "设备类型最多输入5个字符")
    private String type;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    @Size(max = 100, message = "设备名称最多输入100个字符")
    private String name;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    @Size(max = 100, message = "设备编号最多输入100个字符")
    private String code;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @Size(max = 50, message = "用户名最多输入50个字符")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Size(max = 50, message = "密码最多输入50个字符")
    private String password;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    @Size(max = 50, message = "ip地址最多输入50个字符")
    private String ipAddress;

    /**
     * 端口
     */
    @ApiModelProperty(value = "端口")
    @Size(max = 10, message = "端口最多输入10个字符")
    private String port;

    /**
     * mac地址
     */
    @ApiModelProperty(value = "mac地址")
    @Size(max = 50, message = "mac地址最多输入50个字符")
    private String macAddress;

    /**
     * 设备状态
     */
    @ApiModelProperty(value = "设备状态")
    @Size(max = 2, message = "设备状态最多输入2个字符")
    private String status;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
