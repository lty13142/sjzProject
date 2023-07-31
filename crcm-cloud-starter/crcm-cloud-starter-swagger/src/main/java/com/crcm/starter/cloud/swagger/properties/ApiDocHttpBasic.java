package com.crcm.starter.cloud.swagger.properties;

import lombok.Data;

/**
 * @ClassName ApiDocHttpBasic
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
@Data
public class ApiDocHttpBasic {
    private boolean enable = false;
    private String username;
    private String password;
}
