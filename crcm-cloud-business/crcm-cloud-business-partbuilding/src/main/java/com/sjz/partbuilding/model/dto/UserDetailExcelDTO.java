package com.sjz.partbuilding.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserDetailExcelDTO {

    @ExcelProperty(index = 1)
    private String username;

    @ExcelProperty(index = 2)
    private String gender;

    @ExcelProperty(index = 3)
    private String nation;

    @ExcelProperty(index = 4)
    private String birthday;

    @ExcelProperty(index = 5)
    private String address;

    @ExcelProperty(index = 6)
    private String joiningTime;

    @ExcelProperty(index = 7)
    private String partyPositions;

    @ExcelProperty(index = 8)
    private String unit;

    @ExcelProperty(index = 9)
    private String phone;

}
