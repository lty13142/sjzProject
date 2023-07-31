package com.crcm.cloud.start.data.mybatis.bean;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName: PageResult
 * @Description: 简单分页对象返回封装，复杂分页查询请使用com.baomidou.mybatisplus.extension.plugins.pagination.Page
 * @Copyright: Copyright(c) 2021
 * @Company: 中再云图技术有限公司
 * @Author: diaoy
 * @Date: 2021/7/6
 **/
public class PageT<T> implements IPage<T> {

    private static final long serialVersionUID = 914952214865234022L;

    @ApiModelProperty(value = "数据集合")
    protected List<T> records;
    @ApiModelProperty(value = "总条数")
    protected long total;
    @ApiModelProperty(value = "分页数")
    protected long size;
    @ApiModelProperty(value = "页码")
    protected long current;
    @ApiModelProperty(value = "自定义排序")
    protected List<OrderItem> orders;

    public PageT() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList();
    }

    public PageT(long current, long size) {
        this(current, size, 0L);
    }

    public PageT(long current, long size, long total) {
        this(current, size, total, true);
    }

    public PageT(long current, long size, boolean isSearchCount) {
        this(current, size, 0L, isSearchCount);
    }

    public PageT(long current, long size, long total, boolean isSearchCount) {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList();
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
    }

    @Override
    public List<OrderItem> orders() {
        return this.getOrders();
    }

    @Override
    public List<T> getRecords() {
        return records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @JsonIgnore
    public List<OrderItem> getOrders() {
        return this.orders;
    }

    public void setOrders(final List<OrderItem> orders) {
        this.orders = orders;
    }

    private String[] mapOrderToArray(Predicate<OrderItem> filter) {
        List<String> columns = new ArrayList(this.orders.size());
        this.orders.forEach((i) -> {
            if (filter.test(i)) {
                columns.add(i.getColumn());
            }

        });
        return (String[]) columns.toArray(new String[0]);
    }

    private void removeOrder(Predicate<OrderItem> filter) {
        for (int i = this.orders.size() - 1; i >= 0; --i) {
            if (filter.test(this.orders.get(i))) {
                this.orders.remove(i);
            }
        }

    }

    public IPage<T> addOrder(OrderItem... items) {
        this.orders.addAll(Arrays.asList(items));
        return this;
    }

    public IPage<T> addOrder(List<OrderItem> items) {
        this.orders.addAll(items);
        return this;
    }

    @Override
    @JsonIgnore
    public boolean isHitCount() {
        return IPage.super.isHitCount();
    }

    @Override
    @JsonIgnore
    public boolean isSearchCount() {
        return IPage.super.isSearchCount();
    }


}
