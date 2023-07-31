package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.SystemConstantUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 流程单对象 t_flow_form
 * true
 * @author zzyt
 * @date 2020-08-26
 */
@Setter
@Getter
@ToString
@TableName("bpm_form")
public class FormDO extends BaseEntity {
    private static final Long serialVersionUID = 1L;

    /** 表单编号 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 表单名称 */
    private String name;

    /** 表单数据 */
    private String formData;

    /** 解析获取的属性字符串 */
    private String jsonData;

    /** 表单字段(JSON) */
    private String fields;

    /** 字段个数 */
    private Integer fieldSize;

    /** 解析后的HTML代码 */
    private String html;

    /** 表单状态 （0 未启用， 1 已启用 -1 已停用） */
    private String status;

    /** 租户Id */
    private String tenantId;
    /** 表单key */
    private String formKey;
    /** 表单描述 */
    private String remark;
    /** 表单类型 */
    private Integer formType;
    /** 公司id */
    private String companyId;
    /** 公司名 */
    private String companyName;


//    public String getFormTypeCh() {
//        return UtilDic.getDictName(DictConstant.FLOW_FORM_TYPE.CODE, this.formType + "");
//    }

    public String getStatusCh() {
        return SystemConstantUtil.getChName(this.status == null ? "" : this.status, SystemConstant.ENABLE_STATUS.class);
    }

}
