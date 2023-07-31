package com.crcm.develop.tool.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.crcm.develop.common.constant.GenConstants;
import com.crcm.develop.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 业务表 gen_table
 *
 * @author zzyt
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    /**
     * 实体类名称(首字母大写)
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;

    /**
     * 生成包路径
     */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /**
     * 生成模块名
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 生成业务名
     */
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /**
     * 生成功能名
     */
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 主键信息
     */
    @TableField(exist = false)
    private GenTableColumn pkColumn;
    /**
     * 实体父类
     */
    private String superEntityClass;

    /**
     * 表列信息
     */
    @Valid
    @TableField(exist = false)
    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 树编码字段
     */
    @TableField(exist = false)
    private String treeCode;

    /**
     * 树父编码字段
     */
    @TableField(exist = false)
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @TableField(exist = false)
    private String treeName;

    /**
     * 数据源ID
     */
    private String datasourceId;

    /**
     * 数据源ID
     */
    @TableField(exist = false)
    private String datasourceName;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }
}