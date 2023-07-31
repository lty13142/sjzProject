package com.crcm.admin.model.entity;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>Title:日志信息 </p>
 * <p>Description:日志信息 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2019-12-11
 */
@Data
@ApiModel("日志信息")
@TableName("sys_log")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    private String requestIp;
    /**
     * 日志类型（0 登录日志、1 操作日志、2 异常日志、3 错误日志、 4 测试日志）
     */
    @ApiModelProperty(value = "日志类型（0 登录日志、1 操作日志、2 异常日志、3 错误日志、 4 测试日志）")
    private Integer type;
    /**
     * 操作描述
     * description：varchar(2000) ==》 description：String
     */
    @ApiModelProperty(value = "操作描述")
    private String title;
    /**
     * 返回参数(json格式)
     * response_data：mediumtext ==》 responseData：String
     */
    @ApiModelProperty(value = "返回参数(json格式)")
    private String responseData;
    /**
     * 请求URL
     */
    @ApiModelProperty(value = "请求URL")
    private String requestUri;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 操作设备
     */
    @ApiModelProperty(value = "操作设备")
    private String userAgent;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    private String requestMethod;

    /** 错误消息 */
    @ApiModelProperty(value = "错误信息")
    private String errorMsg;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "操作人ID")
    private String operateId;

    @ApiModelProperty(value = "操作人")
    private String operateName;
    /**
     * 请求IP地址
     */
    @ApiModelProperty(value = "请求IP地址")
    private String requestAddr;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;
    /**
     * 服务ID
     */
    @ApiModelProperty(value = "应用标识")
    private String serviceId;

    /**
     * 转换请求参数为Json
     *
     * @param paramMap 请求参数Json
     */
    @JsonIgnore
    public void setMapToParams(Map<String, String[]> paramMap) {
        this.params = JSON.toJSONString(paramMap);
    }


    /**
     * 添加日志类型名称
     *
     * @return 日志类型名称
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getTypeCh() {
        return UtilDic.getDictName(SystemConstant.LOG_TYPE.CODE, this.type + "");
    }


}