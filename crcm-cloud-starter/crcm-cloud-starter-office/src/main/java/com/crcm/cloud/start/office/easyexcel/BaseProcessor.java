package com.crcm.cloud.start.office.easyexcel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseProcessor
 * @Description 数据处理器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/25
 **/
public interface BaseProcessor {
    /**
     * 回调方法
     * @param list
     */
    void invoke(List<Map<Integer,String>> list);
}
