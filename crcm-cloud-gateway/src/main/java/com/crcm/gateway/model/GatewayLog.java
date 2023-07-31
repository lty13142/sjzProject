package com.crcm.gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GatewayLog implements Serializable {
    private static final long serialVersionUID = 1L;
    /**访问实例*/
    private String targetServer;
    /**请求路径*/
    private String requestPath;
    /**请求方法*/
    private String requestMethod;
    /**协议 */
    private String protocol;
    /**请求体*/
    private String requestBody;
    /**响应体*/
    private String responseData;
    /**请求ip*/
    private String ip;
	/**请求时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requestTime;
	/**响应时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date responseTime;
    /**执行时间*/
    private long executeTime;

    private String msgType;

    //消费者账号
    private String consumerAccount;

    private String ak;

}