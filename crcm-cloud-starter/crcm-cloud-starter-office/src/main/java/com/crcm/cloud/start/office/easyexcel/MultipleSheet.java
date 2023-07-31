package com.crcm.cloud.start.office.easyexcel;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MultipleSheet
 * @Description 多页数据承载类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/7/24
 **/
public class MultipleSheet implements Serializable {

    private static final long serialVersionUID = -1237759654737896364L;

    private Sheet sheet;
    private List<? extends BaseRowModel> modelData;
    private List<List<Object>> data;
    private List<List<String>> heads;


    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<? extends BaseRowModel> getModelData() {
        return modelData;
    }

    public void setModelData(List<? extends BaseRowModel> modelData) {
        this.modelData = modelData;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public List<List<String>> getHeads() {
        return heads;
    }

    public void setHeads(List<List<String>> heads) {
        this.heads = heads;
    }
}
