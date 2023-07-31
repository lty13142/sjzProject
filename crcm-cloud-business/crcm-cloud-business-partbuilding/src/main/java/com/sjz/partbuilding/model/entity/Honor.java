package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.enums.HonorTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>Title:荣誉表 </p>
 * <p>Description:荣誉表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-09-30
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("荣誉表")
@TableName("dj_honor")
public class Honor extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * id主键
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "id主键")
        @TableId(value = "id", type = IdType.ASSIGN_UUID)
        private String id;
    /**
     * 0集体荣誉  1 个人荣誉
     * type：int(1) ==》 type：Integer
     */
    @ApiModelProperty(value = "0集体荣誉  1 个人荣誉")
    private String type;
    /**
     * 荣誉称号
     * honorary：varchar(255) ==》 honorary：String
     */
    @ApiModelProperty(value = "荣誉称号")
    private String honorary;
    /**
     * 附件id
     * attachment_ids：text ==》 attachmentIds：String
     */
    @ApiModelProperty(value = "证明文件id")
    private String attachmentIds;
    /**
     * 备注
     * remark：text ==》 remark：String
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 人员获得荣誉
     * emp_by：varchar(32) ==》 empBy：String
     */
    @ApiModelProperty(value = "人员id")
    private String empBy;
    /**
     * 组织获得荣誉
     * com_by：varchar(32) ==》 comBy：String
     */
    @ApiModelProperty(value = "组织id")
    private String comBy;
    /**
     * 颁发单位
     * issuer：varchar(32) ==》 issuer：String
     */
    @ApiModelProperty(value = "颁发单位")
    private String issuer;
    /**
     * 颁发时间
     * time：varchar(32) ==》 time：String
     */
    @ApiModelProperty(value = "颁发时间")
    private String time;

    /**
     * 排序
     * sort: int(11) ==》sort: Integer
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;

    public String getTypeCh() {
        return HonorTypeEnum.getValueByCode(Integer.parseInt(this.type));
    }

}