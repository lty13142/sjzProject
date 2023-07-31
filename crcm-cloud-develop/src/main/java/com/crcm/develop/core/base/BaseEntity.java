package com.crcm.develop.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>Title:实体层基类 </p>
 * <p>Description: 基础entity，保存数据版本信息 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 中再云图科技有限公司</p>
 * @author diaoyunnie 2019/4/15 10:02
 * @version 1.0
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //数据库数据基础信息，每个数据库都必须有!
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)   //公共字段自动填充（新增），详见com.crcm.core.config.mybatis.MybatisMateHandler
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//公共字段自动填充（新增、修改）
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
    /**
     * 版本
     */
//    @Version // 乐观锁注解 每次修改操作都必须传入，version不一致则无法修改，修改后自增
//    @TableField(fill = FieldFill.INSERT_UPDATE, update="%s+1")
//    private Integer  version;
    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
//    @TableLogic
//    @TableField(fill = FieldFill.INSERT)
//    private Integer  deleted;

}
