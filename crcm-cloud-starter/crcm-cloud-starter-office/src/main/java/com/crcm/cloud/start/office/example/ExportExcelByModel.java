package com.crcm.cloud.start.office.example;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.crcm.cloud.start.office.easyexcel.LocalDateTimeConverter;
import com.crcm.cloud.start.office.utils.UtilEasyExcel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName com.crcm.cloud.common.office.example.ExcelTest
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/24
 **/
public class ExportExcelByModel {
    /**
     * 根据model导出Excel
     */
    public static void main(String[] args) {

        // 写法1
        String fileName = "D:\\test\\" + System.currentTimeMillis() + ".xlsx";
        try {
            UtilEasyExcel.exportExcelByModel(data(), fileName, TestModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<TestModel> data() {
        List<TestModel> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new TestModel(i, "user" + i, BigDecimal.valueOf(i), LocalDateTime.now()));
        }
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ContentRowHeight(10) // 行高10
    @HeadRowHeight(20) // 表头高20
    @ColumnWidth(25)
    static
            // 行宽25
    class TestModel implements Serializable {

        @ExcelProperty(value = "编号", index = 0)
        private Integer no;
        @ExcelProperty(value = "姓名", index = 1)
        private String name;
        @ExcelProperty(value = "成绩", index = 2)
        private BigDecimal value;
        /**
         * 宽度为50
         */
        @ColumnWidth(50)
        @ExcelProperty(value = "时间", index = 3, converter = LocalDateTimeConverter.class)
        private LocalDateTime time;
    }


}
