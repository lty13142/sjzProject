package com.sjz.partbuilding.model.vo;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MenuVo
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/5/22 14:13
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
