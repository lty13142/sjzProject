package com.crcm.admin.model.vo;

import com.crcm.core.constant.SystemConstant;
import com.crcm.admin.utils.UtilDic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RoleVO
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/22 14:51
 **/
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -9013961684113241958L;
    private String roleName;
    private String authorizedSigns;
    private String type;
    private String treeId;
    private String roleRemark;
    private String systemName;
    private String userId;
    private String id;
    private String createBy;
    private String updateBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getTypeCh() {
        return UtilDic.getDictName(SystemConstant.ROLE_TYPE.CODE, this.type);

    }
}
