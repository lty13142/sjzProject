package com.zsgf.platform.vo.businessLicense;

import com.crcm.core.constant.SystemConstant;
import com.zsgf.platform.utils.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName BusinessLicenseVo
 * @Description 经营许可证Vo
 * @Author GZL
 * @Date 2023/3/29 9:14
 * @Version 1.0
 **/
@Data
public class BusinessLicenseVo {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private String companyId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String companyName;

    /**
     * 核准经营方式
     */
    @ApiModelProperty(value = "核准经营方式")
    private String operateWay;

    /**
     * 经营规模
     */
    @ApiModelProperty(value = "经营规模")
    private BigDecimal operateScale;

    /**
     * 发证机构
     */
    @ApiModelProperty(value = "发证机构")
    private String certAuth;

    /**
     * 发证机构级别
     */
    @ApiModelProperty(value = "发证机构级别")
    private String certAuthLev;

    /**
     * 许可证编号
     */
    @ApiModelProperty(value = "许可证编号")
    private String licenseCode;

    /**
     * 初次发证日期
     */
    @ApiModelProperty(value = "初次发证日期")
    private LocalDate firstTime;

    /**
     * 发证日期
     */
    @ApiModelProperty(value = "发证日期")
    private LocalDate certTime;

    /**
     * 许可证有效期开始
     */
    @ApiModelProperty(value = "许可证有效期开始")
    private LocalDate validStart;

    /**
     * 许可证有效期结束
     */
    @ApiModelProperty(value = "许可证有效期结束")
    private LocalDate validEnd;

    /**
     * 经营设施地址
     */
    @ApiModelProperty(value = "经营设施地址")
    private String facilitiesAddress;

    /**
     * 经营单位类别
     */
    @ApiModelProperty(value = "经营单位类别")
    private String companyType;

    /**
     * 许可证类型
     */
    @ApiModelProperty(value = "许可证类型")
    private String permitType;

    /**
     * 作废日期
     */
    @ApiModelProperty(value = "作废日期")
    private LocalDate nullifyTime;

    /**
     * 是否限制区域
     */
    @ApiModelProperty(value = "是否限制区域")
    private String limitArea;

    /**
     * 是否限制单位类型
     */
    @ApiModelProperty(value = "是否限制单位类型")
    private String limitCompanyType;

    /**
     * 申报状态 （0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停）, 字典：BUSINESS_LICENSE_STATE
     */
    @ApiModelProperty(value = "申报状态 （0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停, 字典：BUSINESS_LICENSE_STATE）")
    @Size(max = 100, message = "申报状态 最多输入100个字符")
    private String state;

    /**
     * 核准经营危险废物类别
     */
    @ApiModelProperty(value = "核准经营危险废物类别")
    private String wasteType;

    /**
     * 数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）
     */
    @ApiModelProperty(value = "数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）")
    private String dataSource;

    /**
     * 经营规模附件
     */
    @ApiModelProperty(value = "经营规模附件")
    private String operateScaleFile;

    public String getDataSourceCh() {
        return UtilDic.getDictName(SystemConstant.DATA_SOURCE.CODE, this.getDataSource());
    }

    public String getStateCh() {
        return UtilDic.getDictName(SystemConstant.BUSINESS_LICENSE_STATE.CODE, this.getState());
    }
}
