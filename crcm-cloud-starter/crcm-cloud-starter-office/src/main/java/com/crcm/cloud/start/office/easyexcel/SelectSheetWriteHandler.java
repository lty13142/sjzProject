package com.crcm.cloud.start.office.easyexcel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName SelectSheetWriteHandler
 * @Description 下拉处理
 * @Author GZL
 * @Date 2023/4/4 17:51
 * @Version 1.0
 **/
public class SelectSheetWriteHandler implements SheetWriteHandler {
    /**
     * 下拉框数据项Map, key为第几列，List<String>为下拉框数据项
     */
    private final Map<Integer, List<String>> selectMap;

    /**
     * 设置下拉框位置首行
     */
    private final Integer firstRow;

    /**
     * 数据字典集
     */
    private final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * @param selectMap 设置下拉框的数据项
     * @param firstRow  设置下拉框的开始行（实质从firstRow的下一行开始生效）
     */
    public SelectSheetWriteHandler(Map<Integer, List<String>> selectMap, Integer firstRow) {
        this.selectMap = selectMap;
        this.firstRow = firstRow;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {}

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        if (Objects.isNull(selectMap) || selectMap.keySet().isEmpty()) {
            return;
        }
        Sheet sheet = writeSheetHolder.getSheet();
        /// 开始设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 设置下拉框
        String dictSheetName = "字典sheet";
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        //数据字典的sheet页
        Sheet dictSheet = workbook.createSheet(dictSheetName);
        //将数据字典的sheet隐藏（对用户不可见）
        workbook.setSheetHidden(workbook.getSheetIndex(dictSheet), true);
        for (Map.Entry<Integer, List<String>> entry : selectMap.entrySet()) {
            // 设置下拉框位置首行
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, 65535, entry.getKey(), entry.getKey());
            int rowLen = entry.getValue().size();
            // 设置字典sheet页的值 每一列一个字典项
            for (int i = 0; i < rowLen; i++) {
                Row row = dictSheet.getRow(i);
                if (row == null) {
                    row = dictSheet.createRow(i);
                }
                row.createCell(entry.getKey()).setCellValue(entry.getValue().get(i));
            }
            String excelColumn = getExcelColumn(entry.getKey());
            // 下拉框数据来源 eg:字典sheet!$B1:$B2
            String refers = dictSheetName + "!$" + excelColumn + "$1:$" + excelColumn + "$" + rowLen;
            // 创建可被其他单元格引用的名称
            Name name = workbook.createName();
            // 设置名称的名字
            name.setNameName("dict" + entry.getKey());
            // 设置公式
            name.setRefersToFormula(refers);
            // 设置下拉框数据
            //DataValidationConstraint constraint = helper.createExplicitListConstraint((String[]) entry.getValue().toArray());
            DataValidationConstraint constraint = helper.createFormulaListConstraint("dict" + entry.getKey());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            // 处理Excel兼容性问题
            if (dataValidation instanceof HSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(false);
            } else {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            }
            //阻止输入非下拉框的值
            dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            dataValidation.createErrorBox("提示", "请选择选择框内存在的值！");
            sheet.addValidationData(dataValidation);
        }
    }

    /**
     * 将数字列转化成为字母列
     * 主要作用在于根据传入的列获取数据字典sheet中对应列
     *
     * @param num 数字
     * @return 字母
     */
    private String getExcelColumn(int num) {
        String column;
        int len = alphabet.length - 1;
        int first = num / len;
        int second = num % len;
        if (num <= len) {
            column = alphabet[num] + "";
        } else {
            column = alphabet[first - 1] + "";
            if (second == 0) {
                column = column + alphabet[len] + "";
            } else {
                column = column + alphabet[second - 1] + "";
            }
        }
        return column;
    }
}
