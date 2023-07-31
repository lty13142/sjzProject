package com.crcm.cloud.start.office.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.crcm.cloud.start.office.easyexcel.ExcelException;
import com.crcm.cloud.start.office.easypoi.MapImportHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UtilEasyPoi
 * @Description EasyPoi文档工具
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Administrator
 * @Date 2019/7/25
 **/
public class UtilEasyPoi {
    private static final Logger log = LoggerFactory.getLogger(UtilEasyPoi.class);

    // 默认导入参数
    private static ImportParams initImportParams;
    private static TemplateExportParams templateExportParams;

    static {
        initImportParams = new ImportParams();
        templateExportParams = new TemplateExportParams();
        templateExportParams.setSheetNum(new Integer[1]);

    }

    /**
     * 通过模型读取Excel
     *
     * @param inputStream 文档输入流
     * @param modelClazz  模型类
     * @return
     */
    public static List<Object> readExcelWithModel(final InputStream inputStream, Class<?> modelClazz) {
        return readExcelWithModel(inputStream, modelClazz, initImportParams);
    }

    /**
     * 通过模型读取Excel，需要在实体类上面使用@Excel、@ExcelTarget、@ExcelEntity、@ExcelCollection、@ExcelIgnore等注解
     * 详情请查看 http://easypoi.mydoc.io/#text_186900
     *
     * @param inputStream  文档输入流
     * @param modelClazz   模型类
     * @param importParams 自定义导入参数
     * @return
     */
    public static List<Object> readExcelWithModel(final InputStream inputStream, Class<?> modelClazz,
                                                  ImportParams importParams) {
        importParams = importParams != null ? importParams : initImportParams;
        try {
            return ExcelImportUtil.importExcel(inputStream, modelClazz, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 读excel，返回map
     *
     * @param inputStream 文档文件输入流
     * @return
     */
    public static List<Map<String, Object>> readExcelWithMap(final InputStream inputStream) {
        return readExcelWithMap(inputStream, initImportParams);
    }

    /**
     * 自定义参数读excel，返回map
     *
     * @param inputStream  文档文件输入流
     * @param importParams 文档读取参数
     * @return
     */
    public static List<Map<String, Object>> readExcelWithMap(final InputStream inputStream, ImportParams importParams) {
        importParams = importParams != null ? importParams : initImportParams;
        importParams.setDataHandler(new MapImportHandler());
        try {
            return ExcelImportUtil.importExcel(inputStream, Map.class, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过模板导出
     *
     * @param templateUrl 模板文件地址
     * @param outUrl      导出路径
     * @param data        数据
     * @param sheetName   sheet名称
     * @param sheetNum    sheet序号
     */
    public static void writeExcelWithTemplate(String templateUrl, String outUrl, Map<String, Object> data, String sheetName, Integer... sheetNum) {
        TemplateExportParams params = new TemplateExportParams(templateUrl, sheetName, sheetNum);
        writeExcelWithTemplate(data, outUrl, params);

    }

    /*********************************************** write导出 *********************************************************************/

    /**
     * 通过模板导出（自定义导出方式）
     *
     * @param data   数据
     * @param outUrl 导出路径
     * @param params 自定义模板参数
     */
    public static void writeExcelWithTemplate(Map<String, Object> data, String outUrl, TemplateExportParams params) {
        FileOutputStream fos = null;
        Workbook book = ExcelExportUtil.exportExcel(params, data);
        try {
            fos = new FileOutputStream(outUrl);
            book.write(fos);
        } catch (FileNotFoundException e) {
            log.warn("Excel导出失败，未找到导出文件地址！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 07版excel转HTML
     *
     * @param inputStream
     * @return String HTML字符串
     */
    public static String excelToHtmlOf07Base(final InputStream inputStream) {
        try {
            ExcelToHtmlParams params = new ExcelToHtmlParams(WorkbookFactory.create(inputStream));
            return ExcelXorHtmlUtil.excelToHtml(params);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 通过模板导出word文档
     *
     * @param templateUrl 模板文件路径
     * @param outUrl      导出路径
     * @param data        数据
     */
    public static void writeWord2007WithTemplate(String templateUrl, String outUrl, Map<String, Object> data) {
        try {
            XWPFDocument document = WordExportUtil.exportWord07(templateUrl, data);
            writeWord2007WithTemplate(document, outUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过模板导出word文档
     *
     * @param document 自定义文档属性
     * @param outUrl   导出路径
     */
    public static void writeWord2007WithTemplate(XWPFDocument document, String outUrl) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outUrl);
            document.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*********************************************** 文档转excel **********************************************************/
    /**
     * 03版excel转HTML
     *
     * @param inputStream
     * @return String HTML字符串
     */
    public static String excelToHtmlOf03Base(final InputStream inputStream) {
        try {
            ExcelToHtmlParams params = new ExcelToHtmlParams(WorkbookFactory.create(inputStream), true, "yes");
            return ExcelXorHtmlUtil.excelToHtml(params);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * html转excel（html的table标签里面必须要有 sheetName这一属性，用做导出excel的sheet名称）
     *
     * @param htmlStr HTML字符串
     * @param outPath 导出文件地址
     */
    public static void htmlToExcel(String htmlStr, String outPath) {
        Workbook workbook = ExcelXorHtmlUtil.htmlToExcel(htmlStr, ExcelType.XSSF);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            log.warn("表格导出失败，原因是：导出文件路径错误！");
            e.printStackTrace();
        } catch (IOException e) {
            log.warn("HTML转换Excel失败！");
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /*********************************************** export导出 **********************************************************/
    /**
     * 根据模板导出excel（07版）
     *
     * @param response    响应
     * @param templateUrl 模板路径
     * @param fileName    导出文件名称
     * @param data        数据
     * @param sheetName   sheet名称
     * @param sheetNum    sheet编号
     */
    public static void exportExcelWithTemplate(HttpServletResponse response, String templateUrl, String fileName, Map<String, Object> data,
                                               String sheetName, Integer... sheetNum) {
        TemplateExportParams params = new TemplateExportParams(templateUrl, sheetName, sheetNum);
        exportExcelWithTemplate(response, fileName, data, params);

    }

    /**
     * 根据模板导出excel（07版）
     *
     * @param response
     * @param fileName
     * @param data
     * @param params
     */
    public static void exportExcelWithTemplate(HttpServletResponse response, String fileName, Map<String, Object> data, TemplateExportParams params) {
        Workbook book = ExcelExportUtil.exportExcel(params, data);
        OutputStream os = null;
        ByteArrayOutputStream bos = null;
        try {
            os = getExcelOutputStream(fileName, response);
            book.write(os);
            bos = new ByteArrayOutputStream();
            book.write(bos);
            byte[] content = bos.toByteArray();
            os.write(content);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void exportWord07WithTemplate(HttpServletResponse response, String fileName, String templateUrl, Map<String, Object> data) {
        OutputStream os = null;
        ByteArrayOutputStream bos = null;
        try {
            XWPFDocument document = WordExportUtil.exportWord07(templateUrl, data);
            os = getWordOutputStream(fileName, response);
            bos = new ByteArrayOutputStream();
            document.write(bos);
            byte[] content = bos.toByteArray();
            os.write(content);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*********************************************** PDF *********************************************************************/


    /*********************************************** 工具 *********************************************************************/


    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getExcelOutputStream(String fileName, HttpServletResponse response) {
        try {
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            return response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("输出流获取失败！");
        }
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getWordOutputStream(String fileName, HttpServletResponse response) {
        try {
            response.setHeader("content-Type", "application/vnd.ms-word");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            log.info(response.getHeader("Content-disposition"));
            return response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("输出流获取失败！");
        }
    }

}
