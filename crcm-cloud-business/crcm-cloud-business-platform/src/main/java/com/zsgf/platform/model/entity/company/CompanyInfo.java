package com.zsgf.platform.model.entity.company;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息对象 pp_company_info
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("省平台企业信息")
@TableName("pp_company_info")
public class CompanyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 64, message = "单位ID最多输入64个字符")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @Size(max = 50, message = "用户名最多输入50个字符")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Size(max = 100, message = "密码最多输入100个字符")
    private String passcode;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 255, message = "企业名称最多输入255个字符")
    private String entername;

    /**
     * 曾用企业名称
     */
    @ApiModelProperty(value = "曾用企业名称")
    @Size(max = 255, message = "曾用企业名称最多输入255个字符")
    private String enternameonce;

    /**
     * 单位证件类型
     */
    @ApiModelProperty(value = "单位证件类型")
    @Size(max = 255, message = "单位证件类型最多输入255个字符")
    private String dwzjlx;

    /**
     * 单位证件号码
     */
    @ApiModelProperty(value = "单位证件号码")
    @Size(max = 255, message = "单位证件号码最多输入255个字符")
    private String entercode;

    /**
     * 省代码
     */
    @ApiModelProperty(value = "省代码")
    @Size(max = 40, message = "省代码最多输入40个字符")
    private String sssheng;

    /**
     * 市代码
     */
    @ApiModelProperty(value = "市代码")
    @Size(max = 40, message = "市代码最多输入40个字符")
    private String ssshi;

    /**
     * 区代码
     */
    @ApiModelProperty(value = "区代码")
    @Size(max = 40, message = "区代码最多输入40个字符")
    private String ssqu;

    /**
     * 街道代码
     */
    @ApiModelProperty(value = "街道代码")
    @Size(max = 40, message = "街道代码最多输入40个字符")
    private String ssjd;

    /**
     * 单位详细地址
     */
    @ApiModelProperty(value = "单位详细地址")
    @Size(max = 1000, message = "单位详细地址最多输入1000个字符")
    private String address;

    /**
     * 生产经营场所地址
     */
    @ApiModelProperty(value = "生产经营场所地址")
    @Size(max = 1000, message = "生产经营场所地址最多输入1000个字符")
    private String scjycsdz;

    /**
     * 企业规模
     */
    @ApiModelProperty(value = "企业规模")
    @Size(max = 255, message = "企业规模最多输入255个字符")
    private String qygm;

    /**
     * 企业运行状态
     */
    @ApiModelProperty(value = "企业运行状态")
    @Size(max = 255, message = "企业运行状态最多输入255个字符")
    private String qyyxzt;

    /**
     * 行业类型1
     */
    @ApiModelProperty(value = "行业类型1")
    @Size(max = 60, message = "行业类型1最多输入60个字符")
    private String hylxtop;

    /**
     * 行业类型2
     */
    @ApiModelProperty(value = "行业类型2")
    @Size(max = 60, message = "行业类型2最多输入60个字符")
    private String hylxer;

    /**
     * 行业类型3
     */
    @ApiModelProperty(value = "行业类型3")
    @Size(max = 60, message = "行业类型3最多输入60个字符")
    private String hylxsan;

    /**
     * 行业类型4
     */
    @ApiModelProperty(value = "行业类型4")
    @Size(max = 60, message = "行业类型4最多输入60个字符")
    private String hylxcode;

    /**
     * 登记注册类型
     */
    @ApiModelProperty(value = "登记注册类型")
    @Size(max = 60, message = "登记注册类型最多输入60个字符")
    private String jjlx;

    /**
     * 环保隶属关系
     */
    @ApiModelProperty(value = "环保隶属关系")
    @Size(max = 60, message = "环保隶属关系最多输入60个字符")
    private String hblsgx;

    /**
     * 投产日期
     */
    @ApiModelProperty(value = "投产日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tcrq;

    /**
     * 法人代表名称
     */
    @ApiModelProperty(value = "法人代表名称")
    @Size(max = 200, message = "法人代表名称最多输入200个字符")
    private String frdbname;

    /**
     * 法人代表证件类型
     */
    @ApiModelProperty(value = "法人代表证件类型")
    @Size(max = 255, message = "法人代表证件类型最多输入255个字符")
    private String frdbzjlx;

    /**
     * 法人代表有效证件号
     */
    @ApiModelProperty(value = "法人代表有效证件号")
    @Size(max = 255, message = "法人代表有效证件号最多输入255个字符")
    private String frdbyxzjh;

    /**
     * 本单位环保部门名称
     */
    @ApiModelProperty(value = "本单位环保部门名称")
    @Size(max = 255, message = "本单位环保部门名称最多输入255个字符")
    private String bdwhbbmname;

    /**
     * 联系人名称
     */
    @ApiModelProperty(value = "联系人名称")
    @Size(max = 200, message = "联系人名称最多输入200个字符")
    private String lxrname;

    /**
     * 联系人证件类型
     */
    @ApiModelProperty(value = "联系人证件类型")
    @Size(max = 255, message = "联系人证件类型最多输入255个字符")
    private String lxrzjlx;

    /**
     * 联系人身份证
     */
    @ApiModelProperty(value = "联系人身份证")
    @Size(max = 255, message = "联系人身份证最多输入255个字符")
    private String lxrsfz;

    /**
     * 联系人手机号码
     */
    @ApiModelProperty(value = "联系人手机号码")
    @Size(max = 255, message = "联系人手机号码最多输入255个字符")
    private String lxrphone;

    /**
     * 联系人座机
     */
    @ApiModelProperty(value = "联系人座机")
    @Size(max = 255, message = "联系人座机最多输入255个字符")
    private String lxrdh;

    /**
     * 经度-度
     */
    @ApiModelProperty(value = "经度-度")
    @Size(max = 100, message = "经度-度最多输入100个字符")
    private String lonD;

    /**
     * 经度-分
     */
    @ApiModelProperty(value = "经度-分")
    @Size(max = 100, message = "经度-分最多输入100个字符")
    private String lonF;

    /**
     * 经度-秒
     */
    @ApiModelProperty(value = "经度-秒")
    @Size(max = 100, message = "经度-秒最多输入100个字符")
    private String lonM;

    /**
     * 纬度-度
     */
    @ApiModelProperty(value = "纬度-度")
    @Size(max = 100, message = "纬度-度最多输入100个字符")
    private String latD;

    /**
     * 纬度-分
     */
    @ApiModelProperty(value = "纬度-分")
    @Size(max = 100, message = "纬度-分最多输入100个字符")
    private String latF;

    /**
     * 纬度-秒
     */
    @ApiModelProperty(value = "纬度-秒")
    @Size(max = 100, message = "纬度-秒最多输入100个字符")
    private String latM;

    /**
     * 运输单位证号
     */
    @ApiModelProperty(value = "运输单位证号")
    @Size(max = 100, message = "运输单位证号最多输入100个字符")
    private String wxfwysqyzh;

    /**
     * 单位网站
     */
    @ApiModelProperty(value = "单位网站")
    @Size(max = 255, message = "单位网站最多输入255个字符")
    private String dwwz;

    /**
     * 企业备注
     */
    @ApiModelProperty(value = "企业备注")
    @Size(max = 500, message = "企业备注最多输入500个字符")
    private String remark;

    /**
     * 填写状态
     */
    @ApiModelProperty(value = "填写状态")
    @Size(max = 10, message = "填写状态最多输入10个字符")
    private String states;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zcrq;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
