package com.crcm.cloud.start.office.easyexcel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:UtilEasyExcel </p>
 * <p>Description:  easyExcel监听类，可以自定义 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 中再云图科技有限公司</p>
 *
 * @author diaoyunnie 2019-7-23
 * @version 1.0
 */
public class ExcelListener extends AnalysisEventListener {

    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<Object> data = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        data.add(object);
        //根据业务自行 do something
        doSomething();

        /*
        如数据过大，可以进行定量分批处理
        if(datas.size()<=100){
            datas.add(object);
        }else {
            doSomething();
            datas = new ArrayList<Object>();
        }
         */

    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 解析结束销毁不用的资源
        // data.clear();
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}