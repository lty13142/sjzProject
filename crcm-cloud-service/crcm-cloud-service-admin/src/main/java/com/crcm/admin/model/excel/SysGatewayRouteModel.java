package com.crcm.admin.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName SysGatewayRouteModel
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/24
 **/
@Data
public class SysGatewayRouteModel implements Serializable {
    private static final long serialVersionUID = -3086669803122277308L;
    @ExcelProperty(value = "ID",index = 0)
    private Long id;
    /**
     * 路由ID
     */
    @ExcelProperty(value = "路由ID",index = 1)
    private String routeId;
    /**
     * 路由名称
     */
    @ExcelProperty(value = "路由名称",index = 2)
    private String routeName;
    /**
     * 断言
     */
    @ExcelProperty(value = "断言",index = 3)
    private String predicates;
    /**
     * 过滤器
     */
    @ExcelProperty(value = "过滤器",index = 4)
    private String filters;
    /**
     * 路由地址
     */
    @ExcelProperty(value = "路由地址",index = 5)
    private String uri;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序",index = 6)
    private Integer order;
    /**
     * 状态 0 无效 1 有效
     */
    @ExcelProperty(value = "状态 0 无效 1 有效",index = 7)
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间",index = 8)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "修改时间",index = 9)
    private LocalDateTime updateTime;
    /***
     * 是否删除
     */
    @ExcelProperty(value = "是否删除",index = 10)
    private Integer deleted;
}
