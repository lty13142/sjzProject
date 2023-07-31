package com.sjz.partbuilding.util;

import com.sjz.partbuilding.model.excel.ExcelVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.util.JxlsHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UtilExcel {

    public static UtilExcel getInstance() {
        return new UtilExcel();
    }

    public void exportExcelByStream(ExcelVo excelVo) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        excelVo.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
        // 下载文件的默认名称
        excelVo.getResponse().setHeader("Content-Disposition", "attachment; filename="+  URLEncoder.encode(excelVo.getFileName(),"UTF-8"));

        excelVo.getResponse().setHeader("fileName", URLEncoder.encode(excelVo.getFileName(),"UTF-8") );
        excelVo.getResponse().setHeader("Access-Control-Expose-Headers", "fileName");

        try(InputStream templateStream = getClass().getClassLoader().getResourceAsStream("excel/"+excelVo.getTemplateName())) {
            JxlsHelper.getInstance().processTemplate(templateStream, excelVo.getResponse().getOutputStream(), excelVo.getContext());
        }
    }


    /**
     * 解析表格文件并返回表格中的数据(含合并单元格)
     * @param fileurl
     * @return
     */
    public static List analysisExcel(String fileurl){
        InputStream input = null;
        List list = new ArrayList();
        try {
            URL url = new URL(fileurl);//path为url路径
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.setConnectTimeout(300000);
            conn.connect();
            input= conn.getInputStream(); // 得到网络返回的输入流
            list = readExcel(input,excelType(fileurl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 解析表格文件并返回表格中的数据(含合并单元格)
     * @param file
     * @return
     */
    public static List analysisExcelByFile(MultipartFile file){
        String path = file.getOriginalFilename();
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readExcel(input,excelType(path));
    }

    /**
     * 判断表格文件类型
     * @param s
     * @return
     */
    public static String excelType(String s){
        if (s.endsWith(".xls")) {
            return "xls";
        }
        if (s.endsWith(".xlsx")) {
            return "xlsx";
        }
        return "";
    }

    /**
     * 读取表格
     * @param is
     * @param excelType
     * @return
     */
    public static List readExcel(InputStream is,String excelType){
        Workbook workBook = null;
        List res = new ArrayList();
        try {
            if (excelType=="xls") {
                workBook = new HSSFWorkbook(is);
            } else if (excelType=="xlsx") {
                workBook = new XSSFWorkbook(is);
            }

            if (workBook != null) {
                // 获取sheet的个数
                int sheetCount = workBook.getNumberOfSheets();
                // 判断是否存在sheet
                if (sheetCount > 0) {
                    // 遍历
                    for (int i = 0; i < sheetCount; i++) {
                        // 获得Sheet对象
                        Sheet sheet = workBook.getSheetAt(i);
                        //如果表中有数据
                        if(sheet.getFirstRowNum()!=sheet.getLastRowNum()){
                            Row titlerow = sheet.getRow(0);
                            int cellNum = titlerow.getLastCellNum();
                            for (int rows = sheet.getFirstRowNum()+1; rows <= sheet.getLastRowNum(); rows++) {
                                // 获取Row对象
                                Row row = sheet.getRow(rows);
                                if (row == null || row.getLastCellNum() < 0) {
                                    break;
                                }
                                // 遍历列
                                List cellList = new ArrayList();;
                                int columnnulls = row.getFirstCellNum();
                                if(columnnulls!=0){
                                    for(int j=0;j<columnnulls;j++){
                                        cellList.add("");
                                    }
                                }
                                for (int columns = columnnulls ; columns <= cellNum; columns++) {
                                    Cell cell = row.getCell(columns);
                                    if(cell!=null) {
                                        //按照文本的形式读取
                                        cell.setCellType(CellType.STRING);
                                        boolean isMerge = isMergedRegion(sheet, rows, columns);
                                        String value = "";
                                        if(isMerge) {
                                            value = getMergedRegionValue(sheet, row.getRowNum(), cell.getColumnIndex());
                                        }else {
                                            value = cell.getRichStringCellValue().toString();
                                        }
                                        cellList.add(value);
                                    }else{
                                        cellList.add(null);
                                    }
                                }

                                res.add(cellList);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet, int row , int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet , int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();
        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }
        return null ;
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        if(cell == null) return "";
        return cell.getRichStringCellValue().toString();
    }
}
