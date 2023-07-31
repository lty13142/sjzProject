package com.crcm.bpm.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseResponseDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/2020/9/24/14:05
 **/
@Data
public class BaseResponseDTO implements Serializable {
    private String createBy;
    private Date createTime;
    private Date updateTime;
    private String updateBy;
}
