package com.sjz.partbuilding.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sjz.partbuilding.model.entity.UserDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName UserDetailVo
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2020/4/1
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetailVo extends UserDetail {
    private static final long serialVersionUID = 1L;

    private String facePicPath;

    private String saveName;

    private String path;

    private String partyTime;

    private String orgId;

    private String pId;

    private String roleOrgId;

    private String roleName;

    @JSONField(serialize = false)
    private List<UserDetailVo> children;

    private String avatar;

    private String orgName;

    private String partyOrgName;

    private String postName;

}
