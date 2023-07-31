package com.crcm.cloud.start.data.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.core.vo.PageVO;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName YTPageUtil
 * @Description：分页格式化工具
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/5/15 10:18
 **/
public class UtilPage {

    /**
     * Mybatis-Plus分页封装
     * @param page
     * @return
     */
    public static Page initPage(PageVO page){

        Page p = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        if(pageNumber<1){
            pageNumber = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(StringUtils.isNotBlank(sort)) {
            Boolean isAsc = false;
            if(StringUtils.isBlank(order)) {
                isAsc = false;
            } else {
                if("desc".equals(order.toLowerCase())){
                    isAsc = false;
                } else if("asc".equals(order.toLowerCase())){
                    isAsc = true;
                }
            }
            p = new Page(pageNumber, pageSize);
            if(isAsc){
                p.setAsc(sort);
            } else {
                p.setDesc(sort);
            }
        } else {
            p = new Page(pageNumber, pageSize);
        }
        return p;
    }
}
