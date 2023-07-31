package com.sjz.partbuilding.model.vo.org;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.Data;

/**
 * @ClassName OrgSystemVo
 * @Description：组织系统数据传输类
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 **/
@Data
public class OrgSystemVo extends BaseEntity {

    private String orgId;
    private String orgIds;
}
