package com.zsgf.platform.vo.waste;

import com.alibaba.fastjson.annotation.JSONField;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsgf.platform.utils.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @ClassName WasteDisposalWayVo
 * @Description 废物处置方式
 * @Author GZL
 * @Date 2023/3/30 11:20
 * @Version 1.0
 **/
@Getter
@Setter
public class WasteDisposalWayVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 处置方式类型(大类,小类, 字典：WASTE_DISPOSAL_WAY_TYPE)
     */
    @ApiModelProperty(value = "处置方式类型(0：大类, 1：小类, 字典：WASTE_DISPOSAL_WAY_TYPE)")
    private String disposalWayType;

    /**
     * 处置方式名称
     */
    @ApiModelProperty(value = "处置方式名称")
    private String disposalWayName;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String parentId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    public String getDisposalWayTypeCh() {
        return UtilDic.getDictName(SystemConstant.WASTE_DISPOSAL_WAY_TYPE.CODE, this.getDisposalWayType());
    }
}
