package com.crcm.admin.api.dto.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName ReqLogSaveDTO
 * @Description 日志存储DTO
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/23
 **/
@Data
public class ReqLogSaveDTO implements Serializable {
    private static final long serialVersionUID = -2950374525635425192L;
    /**
     * 编号
     */
    @ApiModelProperty(value = "日志编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 操作状态（0正常 1异常）
     */
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private Integer status;

    /**
     * 日志类型
     */
    @NotBlank(message = "日志类型不能为空")
    @ApiModelProperty(value = "日志类型（0-正常 9-错误")
    private Integer type;
    /**
     * 日志标题
     */
    @NotBlank(message = "日志标题不能为空")
    @ApiModelProperty(value = "日志标题")
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    private Integer businessType;
    /**
     * 操作IP地址
     */
    @ApiModelProperty(value = "操作ip地址")
    private String remoteAddr;
    /**
     * 用户浏览器
     */
    @ApiModelProperty(value = "用户浏览器")
    private String userAgent;
    /**
     * 请求URI
     */
    @ApiModelProperty(value = "请求uri")
    private String requestUri;

    /**
     * 操作方式
     */
    @ApiModelProperty(value = "操作方式")
    private String method;

    /**
     * 操作提交的数据
     */
    @ApiModelProperty(value = "数据")
    private String params;

    /**
     * 异常信息
     */
    @ApiModelProperty(value = "异常信息")
    private String errorMsg;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "应用标识")
    private String serviceId;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    private String requestMethod;
    /**
     * 请求IP
     */
    @ApiModelProperty(value = "请求IP")
    private String requestIp;
    /**
     * 请求IP地址
     */
    @ApiModelProperty(value = "请求IP地址")
    private String requestAddr;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 操作人ID
     */
    private String operateId;
    /**
     * 操作人名称
     */
    private String operateName;

    @ApiModelProperty(value = "返回参数(json格式)")
    private String responseData;
}
