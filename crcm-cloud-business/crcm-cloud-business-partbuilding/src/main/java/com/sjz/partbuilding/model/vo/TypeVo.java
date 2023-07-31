package com.sjz.partbuilding.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Setter
@Getter
@ApiModel("组织活动数据统计")
public class TypeVo {

    private String type;

    private String typeName;

    private String number;

}
