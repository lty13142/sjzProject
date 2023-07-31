package com.crcm.bpm.api.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDTO {

    private String companyId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 公司类型
     */
    private String comType;
    /**
     * 上级公司ID
     */
    private String parentId;

    /**
     * 所属角色中文集合名称
     */
    private List<String> roleNameList;

    /**
     * 所属角色授权标识集名称
     */
    private List<String> authorizedSignsList;
}
