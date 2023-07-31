package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @ClassName SysUserDetail
 * @Description 系统用户详情
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统用户详情")
@TableName("sys_user_detail")
public class SysUserDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 姓名
     * name：varchar(64) ==》 name：String
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 性别
     * sex：varchar(16) ==》 sex：String
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 身份证号码
     * id_card：varchar(32) ==》 idCard：String
     */
    @ApiModelProperty(value = "身份证号码")
    private String idCard;
    /**
     * 身份证照片（附件ID）
     * id_card_pic：varchar(32) ==》 idCardPic：String
     */
    @ApiModelProperty(value = "身份证照片（附件ID）")
    private String idCardPic;
    /**
     * 毕业学校
     * school：varchar(255) ==》 school：String
     */
    @ApiModelProperty(value = "毕业学校")

    private String school;
    /**
     * 学历
     * education：varchar(255) ==》 education：String
     */
    @ApiModelProperty(value = "学历")
    private String education;
    /**
     * 联系电话
     * phone：varchar(32) ==》 phone：String
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 联系邮箱
     * email：varchar(255) ==》 email：String
     */
    @ApiModelProperty(value = "联系邮箱")
    private String email;
    /**
     * 联系地址
     * address：varchar(255) ==》 address：String
     */
    @ApiModelProperty(value = "联系地址")
    private String address;
    /**
     * 备注
     * comments：mediumtext ==》 comments：String
     */
    @ApiModelProperty(value = "备注")
    private String comments;
    /**
     * 人员编号
     * person_no：varchar(32) ==》 personNo：String
     */
    @ApiModelProperty(value = "人员编号")
    private String personNo;
    /**
     * 证件照，面部识别照
     * face_pic：varchar(2000) ==》 facePic：String
     */
    @ApiModelProperty(value = "证件照，面部识别照")
    private String facePic;
    /**
     * 现居地址
     * current_address：varchar(1000) ==》 currentAddress：String
     */
    @ApiModelProperty(value = "现居地址")
    private String currentAddress;
    /**
     * 籍贯
     * native_place：varchar(255) ==》 nativePlace：String
     */
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;
    /**
     * 民族
     * nation：varchar(64) ==》 nation：String
     */
    @ApiModelProperty(value = "民族")
    private String nation;
    /**
     * 出生日期
     * birthday：datetime ==》 birthday：Date
     */
    @ApiModelProperty(value = "出生日期")

    private LocalDate birthday;
    /**
     * 排列顺序
     * sort_order：int(11) ==》 sortOrder：Integer
     */
    @ApiModelProperty(value = "排列顺序")
    private Integer sortOrder;
    /**
     * 用户ID
     * user_id：varchar(32) ==》 userId：String
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;


}