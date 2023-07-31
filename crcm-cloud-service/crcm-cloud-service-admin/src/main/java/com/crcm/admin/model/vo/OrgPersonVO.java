package com.crcm.admin.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: OrgPersonVo
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/10/17
 **/
@Data
public class OrgPersonVO {
    private String id;
    private String name;
    private String code;
    private String type;
    private String pid;
    private String number;
    private String icon;
    private List<OrgPersonVO> children;
}
