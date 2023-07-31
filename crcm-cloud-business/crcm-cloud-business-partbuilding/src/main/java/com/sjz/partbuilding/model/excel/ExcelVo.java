package com.sjz.partbuilding.model.excel;

import lombok.Data;
import org.jxls.common.Context;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Data
public class ExcelVo {
    //响应
    private HttpServletResponse response;

    //下载文件的文件名
    private String fileName;

    //模板文件文件名
    private String templateName;

    //模板中用到的变量
    private Context context;

    //需要往模板填充的数据(用于列表导出）
    private List<?> data;
}
