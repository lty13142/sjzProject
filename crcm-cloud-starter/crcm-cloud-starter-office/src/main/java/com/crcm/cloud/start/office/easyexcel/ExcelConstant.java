package com.crcm.cloud.start.office.easyexcel;

/**
 * @ClassName ExcelConstant
 * @Description Excel导出常量
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/10 17:16
 **/
public interface ExcelConstant {

    /**
     * 每个sheet存储的记录数 100W
     */
    Integer PER_SHEET_ROW_COUNT = 1000000;

    /**
     * 每次向EXCEL写入的记录数(查询每页数据大小) 20W
     */
    Integer PER_WRITE_ROW_COUNT = 200000;
}
