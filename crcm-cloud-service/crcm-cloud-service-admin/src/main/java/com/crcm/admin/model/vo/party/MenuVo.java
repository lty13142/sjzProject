package com.crcm.admin.model.vo.party;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Company：中再云图科技有限公司
 **/
@Data
public class MenuVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1602755257642836150L;
    private String id;
    private String name;
    private Integer level;
    private String type;
    private String title;
    private String component;
    private String icon;
    private String code;
    private String pid;
    private String description;
    private String enabled;
    private String url;
    private String path;
    private String redirect;
    private String buttonType;
    private List<MenuVo> children;
    private List<String> roles;
    private List<String> buttons;
    private String ids;
    private String menuId;
    private List<String> roleIds;
    private String actionIds;
    private String appWhetherExists;
    private String appMenuName;
    private String appMenuIcon;

}
