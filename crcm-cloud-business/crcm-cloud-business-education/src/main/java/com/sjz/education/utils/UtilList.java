package com.sjz.education.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @PackageName: com.sjz.education.utils
 * @ClassName: UtilList
 * @Author: cb
 * @Date: 2023-04-09 16:46
 * @Description: list转换工具类
 */
public class UtilList {

	/**
	 * list转map
	 * @param list
	 * @param funX
	 * @param funY
	 * @param <T>
	 * @param <X>
	 * @param <Y>
	 * @return
	 */
	public static  <T,X,Y> Map<String,Object> listToMap(List<T> list, Function<T,X> funX, Function<T,Y> funY){
		Map<String,Object> result = new HashMap<>();
		if(list.isEmpty()){
			return result;
		}
		List<X> xList = new ArrayList<>();
		List<Y> yList = new ArrayList<>();
		list.forEach(data ->{
			xList.add(funX.apply(data));
			yList.add(funY.apply(data));
		});
		result.put("xList", xList);
		result.put("yList", yList);
		return result;
	}

	/**
	 * list根据属性排序
	 * @param list
	 * @param x
	 * @param <T>
	 * @param <U>
	 * @return
	 */
	public static <T,U extends Comparable<? super U>> List<T> sortBASC(List<T> list,Function<T,U> x){

		if (CollectionUtil.isEmpty(list)){
			return new ArrayList<>();
		}
		List<T> result = list.stream().sorted(Comparator.comparing(x)).collect(Collectors.toList());
		return result;
	}

	/**
	 * list补值
	 * @param list
	 * @param <T>
	 * @return
	 */
	public static <T,D,LV> List<Map<String, Object>> supplementaryValue(List<T> list,List<D> dicList,
	                                                                            Function<T, String> listKey,
	                                                                            Function<D, String> dicKey,
	                                                                            Function<T, LV> listValue,
	                                                                            LV defaultValue){
		List<Map<String, Object>> result = new ArrayList<>();
		if(CollectionUtil.isEmpty(dicList)){
			return result;
		}
		Map<String, Object> collect = list.stream().collect(Collectors.toMap(listKey, listValue));
		dicList.forEach(dic->{
			Map<String, Object> data = new HashMap<>();
			data.put("value", collect.getOrDefault(dicKey.apply(dic),defaultValue));
			data.put("name", dicKey.apply(dic));
			result.add(data);
		});
		return result;
	}


}
