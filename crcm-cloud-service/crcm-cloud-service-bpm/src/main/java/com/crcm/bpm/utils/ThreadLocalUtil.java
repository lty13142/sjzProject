package com.crcm.bpm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: open-ai-center
 * @description:
 * @author: zxb
 * @create: 2021-03-31 17:31
 **/
public class ThreadLocalUtil {
    //使用InheritableThreadLocal，使得共享变量可被子线程继承
    private static final InheritableThreadLocal<Map<String,String>> headerMap = new InheritableThreadLocal<Map<String, String>>(){
        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    public static Map<String,String> get(){
        return headerMap.get();
    }

    public static String get(String key) {
        return headerMap.get().get(key);
    }

    public static void set(String key, String value){
        headerMap.get().put(key,value);
    }
}
