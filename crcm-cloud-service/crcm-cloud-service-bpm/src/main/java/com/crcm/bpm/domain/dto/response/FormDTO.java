package com.crcm.bpm.domain.dto.response;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName FormDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
@ToString
public class FormDTO extends BaseResponseDTO {

    private static final long serialVersionUID = -8166168767129259661L;

    /** 表单编号 */
    private Long id;

    /** 表单名称 */
    private String name;

    /** 表单数据 */
    private String formData;

    /** 解析获取的属性字符串 */
    private String jsonData;

    /** 字段个数 */
    private Integer fields;

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
    private String formType;
    /**
     * 乐观锁注解
     */
    private Integer  version;
    /***
     * 逻辑删除
     */
    private Integer  deleted;
}
