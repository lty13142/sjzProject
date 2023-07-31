package com.sjz.partbuilding.model.vo.org;

import lombok.Data;

import java.util.List;

/**
 * @ClassName：OrgPersonVo
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 **/
@Data
public class OrgPersonVo {
    private String id;
    private String name;
    private String code;
    private String type;
    private String pid;
    private String number;
    private String icon;
    private List<OrgPersonVo> children;
//    private List<UserDetail> userDetails;
}
