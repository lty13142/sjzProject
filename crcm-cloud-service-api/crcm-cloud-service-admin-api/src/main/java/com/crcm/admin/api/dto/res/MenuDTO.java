package com.crcm.admin.api.dto.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MenuDTO
 * @Description 菜单数据
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
@Data
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = -7580478633270936455L;
    private Long id;
    private String name;
    private Integer level;
    private String type;
    private String title;
    private String component;
    private String icon;
    private String code;
    private Long pid;
    private String description;
    private String enabled;
    private String url;
    private String path;
    private String redirect;
    private String buttonType;
    private List<MenuDTO> children;
    private List<String> roles;
    private List<String> buttons;
    private String ids;
    private String menuId;
    private List<Long> roleIds;
    private String actionIds;
}
