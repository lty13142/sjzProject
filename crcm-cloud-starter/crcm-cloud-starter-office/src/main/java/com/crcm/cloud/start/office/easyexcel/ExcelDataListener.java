package com.crcm.cloud.start.office.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @ClassName ExcelDataListener
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/25
 **/
public class ExcelDataListener<T> extends AnalysisEventListener<T> {
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
