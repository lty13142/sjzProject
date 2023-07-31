package com.crcm.core.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * @ClassName UtilDataFormat
 * @Description 数据处理工具类型
 * @Author GZL
 * @Date 2023/3/20 10:13
 * @Version 1.0
 **/
public class UtilDataFormat {

    /**
     * list 数据过滤
     *
     * @return 过滤后列表
     * @Author GZL
     * @Date 2023/3/15 10:38
     * @Param list 列表
     * @Param predicate 条件
     **/
    public static <T> List<T> listFilter(List<T> list, Predicate<T> predicate) {
        return CollectionUtils.isEmpty(list) ? new ArrayList<>() : list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * list 属性值列表
     *
     * @return 属性值列表
     * @Author GZL
     * @Date 2023/3/15 10:38
     * @Param list 列表
     * @Param value 值
     **/
    public static <T, V> List<V> listAttr(List<T> list, Function<T, V> value) {
        return CollectionUtils.isEmpty(list) ? new ArrayList<>() : list.stream().map(value).collect(Collectors.toList());
    }

    /**
     * list to map
     *
     * @return map
     * @Author GZL
     * @Date 2023/3/15 10:38
     * @Param list 列表
     * @Param key 键
     * @Param value 值
     **/
    public static <T, K, V> Map<K, V> listToMap(List<T> list, Function<T, K> key, Function<T, V> value) {
        return CollectionUtils.isEmpty(list) ? new HashMap<>() : list.stream().collect(Collectors.toMap(key, value, (k1, k2) -> k2));
    }

    /**
     * list to map
     *
     * @return map
     * @Author GZL
     * @Date 2023/3/15 10:38
     * @Param list 列表
     * @Param key 键
     * @Param value 值
     **/
    public static <T, K, V> Map<K, V> listToMap(List<T> list, Predicate<T> predicate, Function<T, K> key, Function<T, V> value) {
        return CollectionUtils.isEmpty(list) ? new HashMap<>() : list.stream().filter(predicate).collect(Collectors.toMap(key, value, (k1, k2) -> k2));
    }

    /**
     * list to map
     *
     * @return map
     * @Author GZL
     * @Date 2023/3/15 10:38
     * @Param list 列表
     * @Param key 键
     **/
    public static <T, K> Map<K, T> listToMap(List<T> list, Function<T, K> key) {
        return CollectionUtils.isEmpty(list) ? new HashMap<>() : list.stream().collect(Collectors.toMap(key, Function.identity(), (k1, k2) -> k2));
    }

    /**
     * list to map
     *
     * @return map
     * @Author GZL
     * @Date 2023/3/27 10:57
     * @Param list 列表
     * @Param key 键
     **/
    public static <T, K> Map<K, Object> listToMapObj(List<T> list, Function<T, K> key) {
        return CollectionUtils.isEmpty(list) ? new HashMap<>() : list.stream().collect(Collectors.toMap(key, Function.identity(), (k1, k2) -> k2));
    }

    /**
     * list分组
     *
     * @return map
     * @Author GZL
     * @Date 2023/3/15 14:38
     * @Param list 列表
     * @Param type 类型
     **/
    public static <T, K> Map<K, List<T>> listGroup(List<T> list, Function<T, K> type) {
        return CollectionUtils.isEmpty(list) ? new HashMap<>() : list.stream().collect(Collectors.groupingBy(type));
    }

    /**
     * list分组
     *
     * @return map
     * @Author GZL
     * @Date 2023/3/15 14:38
     * @Param list 列表
     * @Param type 类型
     **/
    public static <T, K> Map<K, List<T>> listGroup(List<T> list, Predicate<T> predicate, Function<T, K> type) {
        return CollectionUtils.isEmpty(list) ? new HashMap<>() : list.stream().filter(predicate).collect(Collectors.groupingBy(type));
    }

    /**
     * list 数据过滤
     *
     * @return 过滤后列表
     * @Author GZL
     * @Date 2023/3/15 10:38
     * @Param list 列表
     * @Param predicate 条件
     **/
    public static <T> int getListSum(List<T> list, Predicate<T> predicate, ToIntFunction<T> intFunction) {
        return CollectionUtils.isEmpty(list) ? 0 : list.stream().filter(predicate).mapToInt(intFunction).sum();
    }

    /**
     * list 数据过滤
     *
     * @return 过滤后列表
     * @Author GZL
     * @Date 2023/4/10 10:09
     * @Param list 列表
     * @Param predicate 条件
     **/
    public static <T> int getListSum(List<T> list, ToIntFunction<T> intFunction) {
        return CollectionUtils.isEmpty(list) ? 0 : list.stream().mapToInt(intFunction).sum();
    }

    /**
     * list 数据过滤
     *
     * @return 过滤后列表
     * @Author GZL
     * @Date 2023/4/10 10:46
     * @Param list 列表
     * @Param predicate 条件
     **/
    public static <T, U extends Comparable<? super U>> List<T> getListSort(List<T> list, Function<T, U> comparing) {
        return CollectionUtils.isEmpty(list) ? new ArrayList<>() : list.stream().sorted(Comparator.comparing(comparing)).collect(Collectors.toList());
    }

    /**
     * list 数据过滤
     *
     * @return 过滤后列表
     * @Author GZL
     * @Date 2023/4/10 10:46
     * @Param list 列表
     * @Param predicate 条件
     **/
    public static <T, U extends Comparable<? super U>> List<T> getListSortDesc(List<T> list, Function<T, U> comparing) {
        return CollectionUtils.isEmpty(list) ? new ArrayList<>() : list.stream().sorted(Comparator.comparing(comparing).reversed()).collect(Collectors.toList());
    }
}
