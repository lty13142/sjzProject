package com.crcm.bpm.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseRequestDTO
 * @Description：基础请求DTO
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/24/13:53
 **/
@Data
public class BaseRequestDTO implements Serializable {

    private static final long serialVersionUID = 3199489028379528184L;
    /** 来源系统 **/
//    protected String system;
    /** token **/
    protected String accessToken;
    /** 当前操作用户ID */
    protected String  currentUserId;
    /** 当前操作用户名 */
    protected String currentUserName;

//    protected String currentRealName;
    /** 租户ID */
    protected String tenantId;
    /** 平台 */
//    protected String platform;
}
