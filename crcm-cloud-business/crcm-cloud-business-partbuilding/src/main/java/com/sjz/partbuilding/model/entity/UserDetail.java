package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.constants.YTDicConstant;
import com.sjz.partbuilding.enums.DictionaryBusinessEnum;
import com.sjz.partbuilding.enums.PartyTypeEnum;
import com.sjz.partbuilding.util.UtilDic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("")
@TableName("dj_sys_user_detail")
public class UserDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 姓名
     * name：varchar(64) ==》 name：String
     */
    @ApiModelProperty(value = "姓名")
    @TableField(value = "name", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String name;
    /**
     * 性别
     * sex：varchar(16) ==》 sex：String
     */
    @ApiModelProperty(value = "性别")
    @TableField(value = "sex", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String sex;
    /**
     * 身份证号码
     * id_card：varchar(32) ==》 idCard：String
     */
    @ApiModelProperty(value = "身份证号码")
    @TableField(value = "id_card", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String idCard;
    /**
     * 身份证照片（附件ID）
     * id_card_pic：varchar(32) ==》 idCardPic：String
     */
    @ApiModelProperty(value = "身份证照片（附件ID）")
    @TableField(value = "id_card_pic", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String idCardPic;
    /**
     * 毕业学校
     * school：varchar(255) ==》 school：String
     */
    @ApiModelProperty(value = "毕业学校")
    @TableField(value = "school", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String school;
    /**
     * 学历
     * education：varchar(255) ==》 education：String
     */
    @ApiModelProperty(value = "学历")
    @TableField(value = "education", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String education;
    /**
     * 联系电话
     * phone：varchar(32) ==》 phone：String
     */
    @ApiModelProperty(value = "联系电话")
    @TableField(value = "phone", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String phone;
    /**
     * 联系邮箱
     * email：varchar(255) ==》 email：String
     */
    @TableField(value = "email", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "联系邮箱")
    private String email;
    /**
     * 联系地址
     * address：varchar(255) ==》 address：String
     */
    @ApiModelProperty(value = "联系地址")
    @TableField(value = "address", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String address;
    /**
     * 备注
     * comments：mediumtext ==》 comments：String
     */
    @TableField(value = "comments", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED,fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "备注")
    private String comments;
    /**
     * 人员编号
     * person_no：varchar(32) ==》 personNo：String
     */
    @ApiModelProperty(value = "人员编号")
    @TableField(value = "person_no", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String personNo;
    /**
     * 证件照，面部识别照
     * face_pic：varchar(2000) ==》 facePic：String
     */
    @TableField(value = "face_pic", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "证件照，面部识别照")
    private String facePic;
    /**
     * 现居地址
     * current_address：varchar(1000) ==》 currentAddress：String
     */
    @TableField(value = "current_address", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "现居地址")
    private String currentAddress;
    /**
     * 籍贯
     * native_place：varchar(255) ==》 nativePlace：String
     */
    @ApiModelProperty(value = "籍贯")
    @TableField(value = "native_place", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String nativePlace;
    /**
     * 民族
     * nation：varchar(64) ==》 nation：String
     */
    @ApiModelProperty(value = "民族")
    @TableField(value = "nation", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String nation;
    /**
     * 出生日期
     * birthday：datetime ==》 birthday：Date
     */
    @TableField(value = "birthday", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
    /**
     * 用户ID
     * user_id：varchar(32) ==》 userId：String
     */
    @ApiModelProperty(value = "用户ID")
    @TableField(value = "user_id", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String userId;
    /**
     * 党员类别
     * 0 党员  1 预备党员 2 入党积极分子  3 申请人管理
     * party_type：varchar(32) ==》 partyType：String
     */
    @TableField(value = "party_type",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED,fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "党员类别")
    private String partyType;
    /**
     * 婚姻状况
     * marital_status：varchar(32) ==》 maritalStatus：String
     */
    @ApiModelProperty(value = "婚姻状况")
    @TableField(value = "marital_status", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String maritalStatus;
    /**
     * 入党时间
     * enter_party_time：datetime ==》 enterPartyTime：Date
     */
    @ApiModelProperty(value = "入党时间")
    @TableField(value = "enter_party_time", insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date enterPartyTime;
    /**
     * 转正时间
     * correction_time：datetime ==》 correctionTime：Date
     */
    @ApiModelProperty(value = "转正时间")
    @TableField(value = "correction_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date correctionTime;
    /**
     * 参加工作时间
     * work_time：datetime ==》 workTime：Date
     */
    @ApiModelProperty(value = "参加工作时间")
    @TableField(value = "work_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date workTime;
    /**
     * 入党时所在支部
     * party_branch：varchar(64) ==》 partyBranch：String
     */
    @ApiModelProperty(value = "入党时所在支部")
    @TableField(value = "party_branch",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String partyBranch;
    /**
     * 入党介绍人
     * enter_party_name：varchar(32) ==》 enterPartyName：String
     */
    @ApiModelProperty(value = "入党介绍人")
    @TableField(value = "enter_party_name",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String enterPartyName;
    /**
     * 入党介绍人党内职务
     * enter_party_post：varchar(32) ==》 enterPartyPost：String
     */
    @ApiModelProperty(value = "入党介绍人党内职务")
    @TableField(value = "enter_party_post",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String enterPartyPost;
    /**
     * 转出党支部名称
     * turn_party_name：varchar(32) ==》 turnPartyName：String
     */
    @ApiModelProperty(value = "转出党支部名称")
    @TableField(value = "turn_party_name",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String turnPartyName;
    /**
     * 现任党内职务
     * party_posts：varchar(32) ==》 partyPosts：String
     */
    @TableField(value = "party_posts",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED,fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "现任党内职务")
    private String partyPosts;
    /**
     * 职业类型
     * occupation_type：varchar(32) ==》 occupationType：String
     */
    @ApiModelProperty(value = "职业类型")
    @TableField(value = "occupation_type",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String occupationType;
    /**
     * 职业类型其他
     * other：varchar(32) ==》 other：String
     */
    @ApiModelProperty(value = "职业类型其他")
    @TableField(value = "other",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String other;
    /**
     * 现工作单位及职务
     * job_duties：varchar(64) ==》 jobDuties：String
     */
    @ApiModelProperty(value = "现工作单位及职务")
    @TableField(value = "job_duties",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String jobDuties;
    /**
     * 专业
     * major：varchar(128) ==》 major：String
     */
    @ApiModelProperty(value = "专业")
    @TableField(value = "major",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String major;
    /**
     * qq或微信
     * qq_or_wechat：varchar(32) ==》 qqOrWechat：String
     */
    @ApiModelProperty(value = "qq或微信")
    @TableField(value = "qq_or_wechat",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String qqOrWechat;
    /**
     * 党员创建时间
     * party_create_time：dateTime ==》 partyCreateTime：Date
     */
    @ApiModelProperty(value = "党员创建时间")
    @TableField(value = "party_create_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date partyCreateTime;
    /**
     * 党员是否删除
     * party_deleted：int(1) ==》 partyDeleted：Integer
     */
    @ApiModelProperty(value = "党员是否删除")
    @TableField(value = "party_deleted",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Integer partyDeleted;
    /**
     * 转入支部时间
     * shift_party_time：dateTime ==》 shiftPartyTime：Date
     */
    @ApiModelProperty(value = "转入支部时间")
    @TableField(value = "shift_party_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date shiftPartyTime;

    /**
     * 入团时间
     * joining_time：dateTime ==》 joiningTime：Date
     */
    @ApiModelProperty(value = "入团时间")
    @TableField(value = "joining_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date joiningTime;
    /**
     * 申请入党时间
     * application_time：dateTime ==》 applicationTime：Date
     */
    @ApiModelProperty(value = "申请入党时间")
    @TableField(value = "application_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date applicationTime;
    /**
     * 申请入党形式
     * application_form：varchar(32) ==》 applicationForm：String
     */
    @ApiModelProperty(value = "申请入党形式")
    @TableField(value = "application_form",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String applicationForm;
    /**
     * 何时确定为积极分子
     * determine_time：dateTime ==》 determineTime：Date
     */
    @ApiModelProperty(value = "何时确定为积极分子")
    @TableField(value = "determine_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date determineTime;
    /**
     * 何时何地受何种奖励;何时何地受何种处分
     * punishment：text ==》 punishment：String
     */
    @ApiModelProperty(value = "何时何地受何种奖励;何时何地受何种处分")
    @TableField(value = "punishment",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String punishment;
    /**
     * 简历
     * resume：text ==》 resume：String
     */
    @ApiModelProperty(value = "简历")
    @TableField(value = "resume",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String resume;

    /**
     * 党组织id
     * party_org_id：varchar(32) ==》 partyOrgId：String
     *//*
    @ApiModelProperty(value = "党组织id")
    private String partyOrgId;
    */
    /**
     * 党内职务id
     * role_org_id：varchar(32) ==》 roleOrgId：String
     *//*
    @ApiModelProperty(value = "党内职务id")
    private String roleOrgId;*/
    /**
     * 全程纪实表图片-base64
     * record_pic_base64：text ==》 recordPicBase64：String
     */
    @ApiModelProperty(value = "全程纪实表图片-base64")
    @TableField(value = "record_pic_base64",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String recordPicBase64;

    /**
     * 入党积极分子的确定培养,会议时间
     * r_meet_time：datetime ==》 rMeetTime：Date
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,会议时间")
    @TableField(value = "r_meet_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date rMeetTime;
    /**
     * 入党积极分子的确定培养,参加人员
     * r_join_person：varchar(32) ==》 rJoinPerson：String
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,参加人员")
    @TableField(value = "r_join_person",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String rJoinPerson;
    /**
     * 入党积极分子的确定培养,研究结果
     * r_yj_result：varchar(32) ==》 rYjResult：String
     */
    @TableField(value = "r_yj_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "入党积极分子的确定培养,研究结果")
    private String rYjResult;
    /**
     * 入党积极分子的确定培养,培养联系人姓名
     * r_name：varchar(32) ==》 rName：String
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,培养联系人姓名")
    @TableField(value = "r_name",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String rName;
    /**
     * 入党积极分子的确定培养,培养联系人姓名
     * r_name1：varchar(32) ==》 rName1：String
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,培养联系人姓名")
    @TableField(value = "r_name1",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String rName1;
    /**
     * 入党积极分子的确定培养,报上级党委备案时间
     * r_ba_time：datetime ==》 rBaTime：Date
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,报上级党委备案时间")
    @TableField(value = "r_ba_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date rBaTime;
    /**
     * 入党积极分子的确定培养,报上级党委备案时间
     * r_qd_time：datetime ==》 rBaTime：Date
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,培养确定人时间")
    @TableField(value = "r_qd_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date rQdTime;
    /**
     * 入党积极分子的确定培养,参加培训时间
     * r_px_time：datetime ==》 rPxTime：Date
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,参加培训时间")
    @TableField(value = "r_px_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date rPxTime;
    /**
     * 入党积极分子的确定培养,培训方式
     * r_px_mode：varchar(32) ==》 rPxMode：String
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,培训方式")
    @TableField(value = "r_px_mode",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String rPxMode;
    /**
     * 入党积极分子的确定培养,培训成绩
     * r_px_record：varchar(32) ==》 rPxRecord：String
     */
    @ApiModelProperty(value = "入党积极分子的确定培养,培训成绩")
    @TableField(value = "r_px_record",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String rPxRecord;
    /**
     * 发展对象的确定和考察,会议时间
     * f_meet_time：datetime ==》 fMeetTime：Date
     */
    @ApiModelProperty(value = "发展对象的确定和考察,会议时间")
    @TableField(value = "f_meet_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date fMeetTime;
    /**
     * 发展对象的确定和考察,参会人员
     * f_meet_person：datetime ==》 fMeetPerson：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,参会人员")
    @TableField(value = "f_meet_person",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fMeetPerson;
    /**
     * 发展对象的确定和考察,研究结果
     * f_yj_result：varchar(32) ==》 fYjResult：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,研究结果")
    @TableField(value = "f_yj_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fYjResult;
    /**
     * 发展对象的确定和考察,报上级党委备案时间
     * f_ba_time：datetime ==》 fBaTime：Date
     */
    @ApiModelProperty(value = "发展对象的确定和考察,报上级党委备案时间")
    @TableField(value = "f_ba_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date fBaTime;
    /**
     * 发展对象的确定和考察,审查时间
     * f_sc_time：datetime ==》 fScTime：Date
     */
    @ApiModelProperty(value = "发展对象的确定和考察,审查时间")
    @TableField(value = "f_sc_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date fScTime;
    /**
     * 发展对象的确定和考察,政府审情况
     * f_zs_situation：varchar(32) ==》 fZsSituation：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,政府审情况")
    @TableField(value = "f_zs_situation",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fZsSituation;
    /**
     * 发展对象的确定和考察,政审人员签字
     * f_zs_sign：varchar(32) ==》 fZsSign：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,政审人员签字")
    @TableField(value = "f_zs_sign",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fZsSign;
    /**
     * 发展对象的确定和考察,培训时间
     * f_px_time：datetime ==》 fPxTime：Date
     */
    @ApiModelProperty(value = "发展对象的确定和考察,培训时间")
    @TableField(value = "f_px_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date fPxTime;
    /**
     * 发展对象的确定和考察,培训成绩
     * f_px_record：varchar(32) ==》 fPxRecord：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,培训成绩")
    @TableField(value = "f_px_record",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fPxRecord;
    /**
     * 发展对象的确定和考察,入党介绍人姓名
     * f_rdjsr_name：varchar(32) ==》 fRdjsrName：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,入党介绍人姓名")
    @TableField(value = "f_rdjsr_name",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fRdjsrName;
    /**
     * 发展对象的确定和考察,入党介绍人姓名
     * f_rdjsr_name1：varchar(32) ==》 fRdjsrName1：String
     */
    @ApiModelProperty(value = "发展对象的确定和考察,入党介绍人姓名")
    @TableField(value = "f_rdjsr_name1",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String fRdjsrName1;
    /**
     * 预备党员的接收,会议时间
     * y1_meet_tine：datetime ==》 y1MeetTine：Date
     */
    @ApiModelProperty(value = "预备党员的接收,会议时间")
    @TableField(value = "y1_meet_tine",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y1MeetTine;
    /**
     * 预备党员的接收,参会时间
     * y1_join_person：varchar(32) ==》 y1JoinPerson：String
     */
    @ApiModelProperty(value = "预备党员的接收,参会时间")
    @TableField(value = "y1_join_person",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1JoinPerson;
    /**
     * 预备党员的接收,研究结果
     * y1_yj_result：varchar(32) ==》 y1YjResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,研究结果")
    @TableField(value = "y1_yj_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1YjResult;
    /**
     * 预备党员的接收,上级党委预审时间
     * y1_ys_time：datetime ==》 y1YsTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,上级党委预审时间")
    @TableField(value = "y1_ys_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y1YsTime;
    /**
     * 预备党员的接收,预审结论
     * y1_ys_result：varchar(32) ==》 y1YsResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,预审结论")
    @TableField(value = "y1_ys_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1YsResult;
    /**
     * 预备党员的接收,组织人事部审核时间
     * y1_sh_time：varchar(32) ==》 y1ShTime：String
     */
    @ApiModelProperty(value = "预备党员的接收,组织人事部审核时间")
    @TableField(value = "y1_sh_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y1ShTime;
    /**
     * 预备党员的接收,审核结论
     * y1_sh_result：varchar(32) ==》 y1ShResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,审核结论")
    @TableField(value = "y1_sh_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1ShResult;
    /**
     * 预备党员的接收,公示时间
     * y1_gs_time：datetime ==》 y1GsTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,公示时间")
    @TableField(value = "y1_gs_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y1GsTime;
    /**
     * 预备党员的接收,公示结果
     * y1_gs_result：varchar(32) ==》 y1GsResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,公示结果")
    @TableField(value = "y1_gs_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1GsResult;
    /**
     * 预备党员的接收,会议时间
     * y1_meet_time1：datetime ==》 y1MeetTime1：Date
     */
    @ApiModelProperty(value = "预备党员的接收,会议时间")
    @TableField(value = "y1_meet_time1",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y1MeetTime1;
    /**
     * 预备党员的接收,应到人数
     * y1_yd_count：varchar(32) ==》 y1YdCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,应到人数")
    @TableField(value = "y1_yd_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1YdCount;
    /**
     * 预备党员的接收,实到人数
     * y1_sd_count：varchar(32) ==》 y1SdCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,实到人数")
    @TableField(value = "y1_sd_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1SdCount;
    /**
     * 预备党员的接收,表决方式:（无记名投票)
     * y1_bj_mode：varchar(32) ==》 y1BjMode：String
     */
    @ApiModelProperty(value = "预备党员的接收,表决方式:（无记名投票)")
    @TableField(value = "y1_bj_mode",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1BjMode;
    /**
     * 预备党员的接收,同意人数
     * y1_ty_count：varchar(32) ==》 y1TyCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,同意人数")
    @TableField(value = "y1_ty_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1TyCount;
    /**
     * 预备党员的接收,反对人数
     * y1_fd_count：varchar(32) ==》 y1FdCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,反对人数")
    @TableField(value = "y1_fd_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1FdCount;
    /**
     * 预备党员的接收,弃权人数
     * y1_qq_count：varchar(32) ==》 y1QqCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,弃权人数")
    @TableField(value = "y1_qq_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1QqCount;
    /**
     * 预备党员的接收,大会讨论结果
     * y1_meet_result：varchar(128) ==》 y1MeetResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,大会讨论结果")
    @TableField(value = "y1_meet_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1MeetResult;
    /**
     * 预备党员的接收,会议程序到位的在⬜上打✔
     * y1_dagou：varchar(32) ==》 y1Dagou：String
     */
    @ApiModelProperty(value = "预备党员的接收,会议程序到位的在⬜上打✔")
    @TableField(value = "y1_dagou",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1Dagou;
    /**
     * 预备党员的接收,党员委审议（社区党委、党总支不能审批可审议）
     * y1_dyw_sy：varchar(255) ==》 y1DywSy：String
     */
    @ApiModelProperty(value = "预备党员的接收,党员委审议（社区党委、党总支不能审批可审议）")
    @TableField(value = "y1_dyw_sy",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1DywSy;
    /**
     * 预备党员的接收,意见时间（无社区党委、党总支可不）
     * y1_yj_time：datetime ==》 y1YjTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,意见时间（无社区党委、党总支可不）")
    @TableField(value = "y1_yj_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y1YjTime;
    /**
     * 预备党员的接收,党委审批意见及时间（三个月内）
     * y1_sp_time：datetime ==》 y1SpTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,党委审批意见及时间（三个月内）")
    @TableField(value = "y1_sp_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y1SpTime;
    /**
     * 预备党员的接收,入党宣誓时间
     * y2_xs_time：datetime ==》 y2XsTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,入党宣誓时间")
    @TableField(value = "y2_xs_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2XsTime;
    /**
     * 预备党员的接收,入党宣誓地点
     * y2_xs_address：varchar(64) ==》 y2XsAddress：String
     */
    @ApiModelProperty(value = "预备党员的接收,入党宣誓地点")
    @TableField(value = "y2_xs_address",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2XsAddress;
    /**
     * 预备党员的接收,个人申请转正时间
     * y2_zz_time：datetime ==》 y2ZzTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,个人申请转正时间")
    @TableField(value = "y2_zz_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2ZzTime;
    /**
     * 预备党员的接收,公示时间
     * y2_gs_time：datetime ==》 y2GsTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,公示时间")
    @TableField(value = "y2_gs_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2GsTime;
    /**
     * 预备党员的接收,公示结果
     * y2_gs_result：varchar(32) ==》 y2GsResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,公示结果")
    @TableField(value = "y2_gs_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2GsResult;
    /**
     * 预备党员的接收,会议时间
     * y2_meet_time：datetime ==》 y2MeetTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,会议时间")
    @TableField(value = "y2_meet_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2MeetTime;
    /**
     * 预备党员的接收,参加人员
     * y2_join_person：varchar(32) ==》 y2JoinPerson：String
     */
    @ApiModelProperty(value = "预备党员的接收,参加人员")
    @TableField(value = "y2_join_person",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2JoinPerson;
    /**
     * 预备党员的接收,研究结果
     * y2_yj_result：varchar(32) ==》 y2YjResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,研究结果")
    @TableField(value = "y2_yj_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2YjResult;
    /**
     * 预备党员的接收,上级党委预审时间
     * y2_ys_time：datetime ==》 y2YsTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,上级党委预审时间")
    @TableField(value = "y2_ys_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2YsTime;
    /**
     * 预备党员的接收,预审结论
     * y2_ys_result：varchar(255) ==》 y2YsResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,预审结论")
    @TableField(value = "y2_ys_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2YsResult;
    /**
     * 预备党员的接收,会议时间
     * y2_meet_time1：datetime ==》 y2MeetTime1：Date
     */
    @ApiModelProperty(value = "预备党员的接收,会议时间")
    @TableField(value = "y2_meet_time1",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2MeetTime1;
    /**
     * 预备党员的接收,应到人数
     * y2_yd_count：varchar(32) ==》 y2YdCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,应到人数")
    @TableField(value = "y2_yd_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2YdCount;
    /**
     * 预备党员的接收,实到人数
     * y2_sd_count：varchar(32) ==》 y2SdCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,实到人数")
    @TableField(value = "y2_sd_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2SdCount;
    /**
     * 预备党员的接收,表决方式:（无记名投票)
     * y2_bj_mode：varchar(32) ==》 y2BjMode：String
     */
    @ApiModelProperty(value = "预备党员的接收,表决方式:（无记名投票)")
    @TableField(value = "y2_bj_mode",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2BjMode;
    /**
     * 预备党员的接收,同意人数
     * y2_ty_count：varchar(32) ==》 y2TyCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,同意人数")
    @TableField(value = "y2_ty_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2TyCount;
    /**
     * 预备党员的接收,反对人数
     * y2_fd_count：varchar(32) ==》 y2FdCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,反对人数")
    @TableField(value = "y2_fd_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2FdCount;
    /**
     * 预备党员的接收,弃权人数
     * y2_qq_count：varchar(32) ==》 y2QqCount：String
     */
    @ApiModelProperty(value = "预备党员的接收,弃权人数")
    @TableField(value = "y2_qq_count",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2QqCount;
    /**
     * 预备党员的接收,大会讨论结果
     * y2_meet_result：varchar(255) ==》 y2MeetResult：String
     */
    @ApiModelProperty(value = "预备党员的接收,大会讨论结果")
    @TableField(value = "y2_meet_result",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2MeetResult;
    /**
     * 预备党员的接收,会议程序到位的在⬜上打✔
     * y2_dagou：varchar(32) ==》 y2Dagou：String
     */
    @ApiModelProperty(value = "预备党员的接收,会议程序到位的在⬜上打✔")
    @TableField(value = "y2_dagou",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2Dagou;
    /**
     * 预备党员的接收,党委审议（社区党委、党总支不能审批可审议）
     * y2_dw_sy：varchar(32) ==》 y2DwSy：String
     */
    @ApiModelProperty(value = "预备党员的接收,党委审议（社区党委、党总支不能审批可审议）")
    @TableField(value = "y2_dw_sy",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2DwSy;
    /**
     * 预备党员的接收,意见时间（无社区党委、党总支可不填写）
     * y2_yj_time：datetime ==》 y2YjTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,意见时间（无社区党委、党总支可不填写）")
    @TableField(value = "y2_yj_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date y2YjTime;
    /**
     * 预备党员的接收,党委审批意见及时间（三个月内）
     * y2_sp_time：datetime ==》 y2SpTime：Date
     */
    @ApiModelProperty(value = "预备党员的接收,党委审批意见及时间（三个月内）")
    @TableField(value = "y2_sp_time",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String y2SpTime;
    /**
     * 重要情况
     * import_situation：varchar(255) ==》 importSituation：String
     */
    @ApiModelProperty(value = "重要情况")
    @TableField(value = "import_situation",insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String importSituation;

    /**
     * 组织id
     */
    @ApiModelProperty(value = "机构ID")
    private String orgId;

    /**
     * 年龄
     */
    @TableField(exist = false)
    private int age;

    /**
     * 职位姓名
     */
    @TableField(exist = false)
    private String postName;
    /**
     * 序号
     */
    @TableField(exist = false)
    private int rowNo;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

//    /**
//     * 前端性别处理
//     * @return
//     */
//    public String getSexCh() {
//        return UtilDic.getChNameByCode(DictionaryBusinessEnum.SEX.code, this.sex);
//    }
//
    /**
     * 前端党内职务处理
     * @return
     */
    public String getPartyPostsCh() {
        return UtilDic.getChNameByCode(DictionaryBusinessEnum.ORG_POST_TYPE.code, this.partyPosts);
    }
//
    /**
     * 前端学历处理
     * @return
     */
    public String getEducationCh() {
        return UtilDic.getChNameByCode(DictionaryBusinessEnum.EDUCATION.code, this.education);
    }
//
    /**
     * 前端民族处理
     * @return
     */
    public String getNationCh() {
        return UtilDic.getChNameByCode(YTDicConstant.NATION.CODE, this.nation);
    }
//
    public String getPartyTypeCh() {
        if(!ObjectUtils.isEmpty(this.partyType)){
            return PartyTypeEnum.getValueByCode(Integer.parseInt(this.partyType));
        } else {
          return null;
        }
    }
//
    /**
     * 出生日期的日期处理
     * @return
     */
    public String getBirthdayCh() {
        return UtilDic.getSpecificDate(this.birthday);
    }
//
//    /**
//     * 入党时间的日期处理
//     * @return
//     */
    public String getEnterPartyTimeCh() {
        return UtilDic.getSpecificDate(this.enterPartyTime);
    }

    public String getWorkTimeCh() {
        return UtilDic.getSpecificDate(this.workTime);
    }

    public String getDetermineTimeCh() {
        return UtilDic.getSpecificDate(this.determineTime);
    }
//
//    /**
//     * 转正时间的日期处理
//     * @return
//     */
    public String getCorrectionTimeCh() {
        return UtilDic.getSpecificDate(this.correctionTime);
    }
//
//    /**
//     * 转入支部时间的日期处理
//     * @return
//     */
    public String getShiftPartyTimeCh() {
        return UtilDic.getSpecificDate(this.shiftPartyTime);
    }
}