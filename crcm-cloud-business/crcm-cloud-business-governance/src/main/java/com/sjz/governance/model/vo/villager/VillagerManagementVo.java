package com.sjz.governance.model.vo.villager;

import com.baomidou.mybatisplus.annotation.TableId;
import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * @ClassName VillagerManagementVo
 * @Description 村民信息VO
 * @Author GZL
 * @Date 2023/4/3 17:08
 * @Version 1.0
 **/
@Data
public class VillagerManagementVo {

    /**
     * ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 村民姓名
     */
    @ApiModelProperty(value = "村民姓名")
    private String villagerName;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    /**
     * 性别 字典：SEX
     */
    @ApiModelProperty(value = "性别 字典：SEX")
    private String sex;

    /**
     * 类别 字典：VILLAGER_TYPE
     */
    @ApiModelProperty(value = "类别 字典：VILLAGER_TYPE")
    private String type;

    /**
     * 所属管区id
     */
    @ApiModelProperty(value = "所属管区id")
    private String district;

    /**
     * 所属村id
     */
    @ApiModelProperty(value = "所属村id")
    private String village;

    /**
     * 所属村
     */
    @ApiModelProperty(value = "所属村")
    private String villageName;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String phone;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址")
    private String address;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private int theAge;

    @ApiModelProperty(value = "身份证号")
    private String idNum;


    public String getSexCh() {
        return UtilDic.getDictName(DicConstant.SEX.CODE, this.getSex());
    }

    public String getTypeCh() {
        return UtilDic.getDictName(DicConstant.VILLAGER_TYPE.CODE, this.getType());
    }

    public Integer getAge() {
        return Objects.nonNull(this.getBirthday()) ? Period.between(LocalDate.now(), this.getBirthday()).getYears() : null;
    }


    public String getVillageCh() {
        return UtilSysArea.getAreaName(SystemConstant.AREA_TYPE.VILLAGE, this.getVillage());
    }

    public String getDistrictCh() {
        return UtilSysArea.getAreaName(SystemConstant.AREA_TYPE.AREA, this.getDistrict());
    }
}
