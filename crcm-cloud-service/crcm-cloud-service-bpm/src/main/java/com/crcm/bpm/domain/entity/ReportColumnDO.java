package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("表格配置")
@TableName("bpm_report_column")
public class ReportColumnDO extends BaseEntity implements Serializable {

    /**
     * 编号
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "模板编号")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /** 配置表名称 */
    private String name;

    /** 公司id */
    private String companyId;

    /** 公司名 */
    private String companyName;

    /**
     * 列类型名
     */
    private String columnTypeName;

    /**
     * 数据库字段名
     */
    private String value;
    /**
     * 数据库字段名
     */
    private String filed;

    /**
     * 流程KEY
     */
    private String processKey;

    /**
     * 表单ID
     */
    private Long formId;
    /**
     * 模板ID
     */
    private Long modelId;
    /**
     * 字段类型
     */
    private String formType;
}
