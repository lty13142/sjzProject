package com.crcm.develop.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 用户
 * @author diaoy
 * @date 2020-04-01 17:06:57
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends Model<SysUser> {
private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /**
   * 用户名
   */
  @NotBlank(message = "用户名不能为空")
  @Size(min = 0, max = 30, message = "用户名称长度不能超过30个字符")
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 创建人
   */
  private String createBy;
  /**
   * 是否删除
   */
  private Integer deleted;
  /**
   * 所属组织
   */
  private String orgId;
  /**
   * 备注
   */
  private String remark;
  /**
   * 电话号码
   */
  private String phonenumber;
  /**
   * 邮箱
   */
  @Email(message = "邮箱格式不正确")
  @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
  private String email;
  /**
   * 职业
   */
  private String work;

  public boolean isAdmin()
  {
    return isAdmin(this.id);
  }

  public static boolean isAdmin(Integer userId)
  {
    return userId != null && 1 == userId;
  }

}
