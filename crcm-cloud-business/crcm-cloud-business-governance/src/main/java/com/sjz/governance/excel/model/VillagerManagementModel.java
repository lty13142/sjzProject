package com.sjz.governance.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @ClassName VillagerManagementModel
 * @Description 村民信息导出模板
 * @Author GZL
 * @Date 2023/4/4 14:22
 * @Version 1.0
 **/
@Data
public class VillagerManagementModel {
    /**
     * 村民姓名
     */
    @ExcelProperty(value = "村民姓名(必填)", index = 0)
    private String villagerName;

    /**
     * 所属管区
     */
    @ExcelProperty(value = "所属管区(必填)", index = 1)
    private String district;

    /**
     * 所属村
     */
    @ExcelProperty(value = "所属村(必填)", index = 2)
    private String village;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号", index = 3)
    private String idNum;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", index = 4)
    private String sex;

    /**
     * 类别
     */
    @ExcelProperty(value = "类别(必填)", index = 5)
    private String type;

    /**
     * 家庭住址
     */
    @ExcelProperty(value = "家庭住址", index = 6)
    private String address;

    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式", index = 7)
    private String phone;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 8)
    private String remark;
}
