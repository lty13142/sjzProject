package com.crcm.admin.api.dto.res;


import com.crcm.core.constant.SystemConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysOrg
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
public class SysOrg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 组织代码
     */
    private String code;
    /**
     * 上级
     */
    private Long pid;
    /**
     * 编号
     */
    private String number;
    /**
     * 备注
     */
    private String comments;

    /**
     * 组织类型
     */
    private String type;

    /**
     * 图标
     */
    private String icon;
    /***
     * 逻辑删除
     */
    private Integer deleted;

    private String branchType;

    private String directlyBranch;

    private String groundBranch;

    private String dataType;

    private String branchTypeCh;
    private String typeCh;
    public List<SysOrg> children;
    public Boolean orgType;

//
//    public String getTypeCh() {
//        return UtilDic.getDictName(SystemConstant.ORG_TYPE.CODE, this.type);
//    }

}