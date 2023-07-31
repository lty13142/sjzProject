package com.sjz.education.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: crcm-cloud-master
 * @description: 分页
 * @author: sssccc
 * @create: 2023-02-06 15:07
 **/
public class UtilPage {
    /**
     * 集合转换为分页工具
     * @param page
     * @param list
     * @return
     */
    public  static IPage getPage(PageT page, List list){
        List pageList = new ArrayList<>();
        long curIdx = page.getCurrent() > 1 ? (page.getCurrent() - 1) * page.getSize() : 0;
        for (int i = 0; i < page.getSize() && curIdx + i < list.size(); i++) {
            pageList.add(list.get((int)curIdx + i));
        }
        IPage pages = new Page(page.getCurrent(),page.getSize());
        pages.setRecords(pageList);
        pages.setTotal(list.size());
        return pages;
    }
}
