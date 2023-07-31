package com.crcm.cloud.start.office.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.crcm.cloud.start.office.easyexcel.BaseProcessor;
import com.crcm.cloud.start.office.easyexcel.EasyExcelPR;
import com.crcm.cloud.start.office.easyexcel.ExcelException;
import com.crcm.cloud.start.office.easyexcel.SelectSheetWriteHandler;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Title:UtilEasyExcel 
 * Description:  excel表格导出工具类，基于阿里 easyExcel，适用于简单格式的Excel导入和导出（读取Excel支持xls和xlsx表格，导出只支持xlsx）
 * Copyright: Copyright (c) 2021
 * Company: 中再云图科技有限公司
 * @author diaoyunnie 2021.6.25
 * @version 2.0
 */
public class UtilEasyExcel {

    private static final Logger log = LoggerFactory.getLogger(UtilEasyExcel.class);
    /**
     * 行号索引
     */
    public static final Integer ROW_NUMBER_INDEX = -1;
    /**
     * 每个sheet的容量，即超过60000时就会把数据分sheet
     */
    private static final int PAGE_SIZE = 60000;

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);

    }

    private UtilEasyExcel() {

    }

    /**
     * 读取Excel表头
     *
     * @param inputStream 输入流
     * @param headSize    excel表头个数（指定返回的个数）
     * @return
     */
    public static List<String> getExcelHead(InputStream inputStream, Integer headSize) {
        List<String> excelHeadList = getExcelHead(inputStream);
        if (headSize != null && headSize > 0 && CollectionUtils.isNotEmpty(excelHeadList) && excelHeadList.size() >= headSize) {
            return excelHeadList.subList(0, headSize);
        }
        return excelHeadList;
    }


    /**
     * 读取Excel表头
     *
     * @param inputStream 输入流
     * @return excel表头
     */
    public static List<String> getExcelHead(InputStream inputStream) {
        // 表头名称列表
        final List<String> headList = new ArrayList<>();

        //继续执行标志
        final AtomicBoolean execFlag = new AtomicBoolean(true);

        EasyExcel.read(inputStream, new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                if (MapUtils.isNotEmpty(headMap)) {
                    for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
                        headList.add(entry.getValue());
                    }
                }
                // 终止读取
                execFlag.set(false);
            }

            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                // Do nothing
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // Do nothing
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return execFlag.get();
            }
        }).sheet().doRead();
        return headList;
    }


    /**
     * 读取数据 （默认1000）
     *
     * @param inputStream   文件流
     * @param baseProcessor 自定义处理器
     * @return 读取数据总数量
     */
    public static long readData(InputStream inputStream, BaseProcessor baseProcessor) {
        return readData(inputStream, baseProcessor, 1000);
    }


    /**
     * 读取数据
     *
     * @param inputStream   文件流
     * @param baseProcessor 处理器
     * @param batchSize     单批次处理数量
     * @return 读取数据总数量
     */
    public static long readData(InputStream inputStream, BaseProcessor baseProcessor, final Integer batchSize) {

        // 数据行数
        final AtomicLong dataCount = new AtomicLong(0L);

        EasyExcel.read(inputStream, new AnalysisEventListener<Map<Integer, String>>() {

            /**
             * 缓存集合
             */
            private List<Map<Integer, String>> list = new ArrayList<>(batchSize);

            /**
             * 当前行号
             */
            private Integer lineNumber = 1;

            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                lineNumber++;
                // 记录总行数
                dataCount.addAndGet(1);
                if (list.size() >= batchSize) {
                    // 批量执行任务
                    if (baseProcessor != null) {
                        baseProcessor.invoke(list);
                    }
                    // 清除缓存
                    list.clear();
                } else {
                    // 记录行号
                    data.put(ROW_NUMBER_INDEX, String.valueOf(lineNumber));
                    // 写入缓存表
                    list.add(data);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                if (baseProcessor != null) {
                    baseProcessor.invoke(list);
                }
            }
        }).sheet().doRead();
        return dataCount.get();
    }

    /**
     * 浏览器下载报表(使用注解方式设置Excel头数据)
     *
     * @param response   响应请求
     * @param data       报表数据
     * @param fileName   文件名字
     * @param excelClass 报表实体类的Class（根据该Class的属性来设置Excel的头属性）
     * @throws IOException
     */
    public static void downloadExcelByModel(HttpServletResponse response, List<?> data, String fileName, Class<?> excelClass) throws IOException {
        @Cleanup ServletOutputStream os = response.getOutputStream();
        long exportStartTime = System.currentTimeMillis();
        ;
        log.info("报表导出Size: " + data.size() + "条。");

        // 把查询到的数据按设置的sheet的容量进行切割
        List<? extends List<?>> lists = CollUtil.splitList(data, PAGE_SIZE);
        // 设置响应头
        EasyExcelPR.setHead(response, fileName);
        ExcelWriter excelWriter = EasyExcel.write(os, excelClass)
                .registerWriteHandler(EasyExcelPR.formatExcel())
                .registerWriteHandler(new EasyExcelPR.ExcelWidthStyleStrategy()).build();
        ExcelWriterSheetBuilder excelWriterSheetBuilder;
        WriteSheet writeSheet;
        for (int i = 1; i <= lists.size(); ++i) {
            excelWriterSheetBuilder = new ExcelWriterSheetBuilder(excelWriter);
            excelWriterSheetBuilder.sheetNo(i).sheetName("sheet" + i);
            writeSheet = excelWriterSheetBuilder.build();
            excelWriter.write(lists.get(i - 1), writeSheet);
        }
        // 必须要finish才会写入，不finish只会创建empty的文件
        excelWriter.finish();
        System.out.println("报表导出成功,导出耗时: " + (System.currentTimeMillis() - exportStartTime) + "ms");
    }

    /**
     * 浏览器下载报表,根据模板生成，只适用于普通格式的表格，复杂格式请自行根据easyExcel文档进行操作
     *
     * @param response 响应请求
     * @param map     报表数据
     * @param templatePath     模板地址
     * @param fileName 文件名
     * @throws IOException
     */
    public static void downloadByTemplate(HttpServletResponse response, Map<String, Object> map, String templatePath, String fileName) throws IOException {
        @Cleanup ServletOutputStream os = response.getOutputStream();
        long exportStartTime = System.currentTimeMillis();
        // 设置响应头
        EasyExcelPR.setHead(response, fileName);
        ExcelWriter excelWriter = EasyExcel.write(os).withTemplate(templatePath)
                .registerWriteHandler(EasyExcelPR.formatExcel())
                .registerWriteHandler(new EasyExcelPR.ExcelWidthStyleStrategy()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 写入列表数据
        for (String key : map.keySet()) {
            if (Objects.nonNull(map.get(key)) && map.get(key) instanceof Collection<?>) {
                // 直接写入数据
                excelWriter.fill(CollUtil.toList(map.get(key)), writeSheet);
                // 清楚列表数据，减少后续操作
                map.remove(key);
            }
        }
        // 写入其它数据
        excelWriter.fill(map, writeSheet);
        // 必须要finish才会写入，不finish只会创建empty的文件
        excelWriter.finish();
        System.out.println("报表导出成功,导出耗时: " + (System.currentTimeMillis() - exportStartTime) + "ms");
    }

    /**
     * 浏览器下载报表,根据模板生成，只适用于普通格式的表格，复杂格式请自行根据easyExcel文档进行操作
     *
     * @param response 响应请求
     * @param map     报表数据
     * @param map     下拉
     * @param fileName 文件名
     * @throws IOException
     */
    public static void downloadByModel(HttpServletResponse response,Integer dataIndex, Map<Integer, List<String>> map, Class<?> modelClass, String fileName) throws IOException {
        @Cleanup ServletOutputStream os = response.getOutputStream();
        // 设置响应头
        EasyExcelPR.setHead(response, fileName);
        EasyExcel.write(os, modelClass)
                .registerWriteHandler(EasyExcelPR.formatExcel())
                .registerWriteHandler(new EasyExcelPR.ExcelWidthStyleStrategy())
                .registerWriteHandler(new SelectSheetWriteHandler(map, dataIndex)).sheet("sheet")
                .doWrite(new ArrayList<>());
    }

    /**
     * 浏览器下载报表,不使用注解
     *
     * @param response 响应请求
     * @param data     报表数据
     * @param data     报表头
     * @param fileName 文件名字
     * @throws IOException
     */
    public static void downloadExcel(HttpServletResponse response, List<?> data, List<String> header, String fileName) throws IOException {
        @Cleanup ServletOutputStream os = response.getOutputStream();
        long exportStartTime = System.currentTimeMillis();
        log.info("报表导出Size: " + data.size() + "条。");
        // 把查询到的数据按设置的sheet的容量进行切割
        List<? extends List<?>> lists = CollUtil.splitList(data, PAGE_SIZE);
        // 设置响应头
        EasyExcelPR.setHead(response, fileName);
        ExcelWriter excelWriter = EasyExcel.write(os).head(getHeaders(header))
                .registerWriteHandler(EasyExcelPR.formatExcel())
                .registerWriteHandler(new EasyExcelPR.ExcelWidthStyleStrategy()).build();
        ExcelWriterSheetBuilder excelWriterSheetBuilder;
        WriteSheet writeSheet;
        for (int i = 1; i <= lists.size(); ++i) {
            excelWriterSheetBuilder = new ExcelWriterSheetBuilder(excelWriter);
            excelWriterSheetBuilder.sheetNo(i).sheetName("sheet" + i);
            writeSheet = excelWriterSheetBuilder.build();
            excelWriter.write(lists.get(i - 1), writeSheet);
        }
        // 必须要finish才会写入，不finish只会创建empty的文件
        excelWriter.finish();
        System.out.println("报表导出成功,导出耗时: " + (System.currentTimeMillis() - exportStartTime) + "ms");
    }


    /**
     * 导出报表(使用注解方式设置Excel头数据)
     *
     * @param data       报表数据
     * @param filePath   文件保存路径
     * @param excelClass 报表实体类的Class（根据该Class的属性来设置Excel的头属性）
     */
    public static void exportExcelByModel(List<?> data, String filePath, Class<?> excelClass) throws IOException {
        @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
        long exportStartTime = System.currentTimeMillis();
        log.info("报表导出Size: " + data.size() + "条。");
        // 把查询到的数据按设置的sheet的容量进行切割
        List<? extends List<?>> lists = CollUtil.splitList(data, PAGE_SIZE);
        // 格式化Excel数据
        // EasyExcelPR.formatExcel()：设置Excel的格式
        // EasyExcelPR.ExcelWidthStyleStrategy():设置头部单元格宽度
        ExcelWriter excelWriter = EasyExcel.write(os, excelClass)
                .registerWriteHandler(EasyExcelPR.formatExcel())
                .registerWriteHandler(new EasyExcelPR.ExcelWidthStyleStrategy()).build();
        ExcelWriterSheetBuilder excelWriterSheetBuilder;
        WriteSheet writeSheet;
        for (int i = 1; i <= lists.size(); ++i) {
            excelWriterSheetBuilder = new ExcelWriterSheetBuilder(excelWriter);
            excelWriterSheetBuilder.sheetNo(i).sheetName("sheet" + i);
            writeSheet = excelWriterSheetBuilder.build();
            excelWriter.write(lists.get(i - 1), writeSheet);
        }
        // 必须要finish才会写入，不finish只会创建empty的文件
        excelWriter.finish();
        byte[] content = os.toByteArray();
        @Cleanup InputStream is = new ByteArrayInputStream(content);

        // 文件落地，用来测试文件的格式和数据的完整性
        // @Cleanup:lombok关流注解
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        @Cleanup BufferedInputStream bis = new BufferedInputStream(is);
        @Cleanup BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        log.info("文件存储到磁盘:{}", filePath);
        System.out.println("报表导出成功,导出耗时: " + (System.currentTimeMillis() - exportStartTime) + "ms");
    }

    /**
     * 导出Excel
     *
     * @param response HttpServletResponse
     * @param fileName Excel文件导出文件名
     * @param data     数据源
     * @param head     表头
     */
    public static void exportExcel(HttpServletResponse response, String fileName, List<List<Object>> data, List<String> head) {
        exportExcelBySheet(response, fileName, data, head, null);
    }

    /**
     * 自定义样式导出Excel
     *
     * @param response HttpServletResponse
     * @param fileName Excel文件导出文件名
     * @param data     数据源
     * @param head     表头
     * @param sheet    Excel页面样式
     */
    public static void exportExcelBySheet(HttpServletResponse response, String fileName, List<List<Object>> data, List<String> head, Sheet sheet) {
        sheet = (sheet != null) ? sheet : initSheet;

        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        ExcelWriter writer = null;
        OutputStream out = getOutputStream(fileName, response);
        try {
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            writer.write1(data, sheet);
            if (writer != null) {
                writer.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 生成表头
     *
     * @param headers
     * @return
     */
    public static List<List<String>> getHeaders(List<String> headers) {
        if (CollUtil.isEmpty(headers)) {
            return new ArrayList<>();
        }
        return headers.stream().map(h -> Arrays.asList(h)).collect(Collectors.toList());
    }


    /***************************************************    方法   ******************************************/


    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        try {
            fileName = new String(fileName.getBytes(), "UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setCharacterEncoding("UTF-8");
            log.info(response.getContentType());
            return response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("输出流获取失败！");
        }
    }


    /**
     * 根据输入流，判断为xls还是xlsx，该方法原本存在于easyexcel 1.1.0 的ExcelTypeEnum中。
     */
    public static ExcelTypeEnum typeOf(InputStream inputStream) {
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if (FileMagic.OLE2.equals(fileMagic)) {
                return ExcelTypeEnum.XLS;
            }
            if (FileMagic.OOXML.equals(fileMagic)) {
                return ExcelTypeEnum.XLSX;
            }
            throw new ExcelException("文件格式错误！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        Font headFont = new Font();
        headFont.setBold(true);
        headFont.setFontHeightInPoints((short) 22);
        headFont.setFontName("楷体");
        tableStyle.setTableHeadFont(headFont);
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

        Font contentFont = new Font();
        contentFont.setBold(true);
        contentFont.setFontHeightInPoints((short) 22);
        contentFont.setFontName("黑体");
        tableStyle.setTableContentFont(contentFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
        return tableStyle;
    }
}
