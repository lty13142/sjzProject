package com.crcm.develop.tool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 数据源配置
 *
 * @author diaoy
 * @date 2020-04-01 17:12:29
 */
@Data
@TableName("gen_datasource_conf")
@EqualsAndHashCode(callSuper = true)
public class DatasourceConfEntity extends Model<DatasourceConfEntity> {
private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(type = IdType.AUTO)
  private String id;
  /**
   * 名称
   */
  @NotBlank(message = "数据库名称不能为空")
  private String name;
  /**
   * 数据库连接地址
   */
  @NotBlank(message = "数据库连接地址不能为空")
  private String url;
  /**
   * 用户名
   */
  @NotBlank(message = "数据库用户名不能为空")
  private String username;
  /**
   * 数据库密码
   */
  @NotBlank(message = "数据库密码不能为空")
  private String password;
  /**
   * 创建时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private LocalDateTime createTime;
  /**
   * 更新
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private LocalDateTime updateTime;
  /**
   * 数据库类型
   */
  @NotBlank(message = " 数据库类型不能为空")
  private String dbType;
  /**
   * 备注
   */
  private String remark;

}
