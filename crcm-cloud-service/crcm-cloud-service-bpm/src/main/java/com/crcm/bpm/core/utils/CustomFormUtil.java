package com.crcm.bpm.core.utils;

import com.crcm.bpm.api.enums.CustomFormEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: open-ai-center
 * @description:
 **/
@Slf4j
public class CustomFormUtil {
    private static final String method = "execute";

    private static final String finish = "finishExecute";

    /**
     * 保存定制表单信息
     * @param formCode   表单code
     * @param applyId   申请编号
     * @param formData  表单信息
     */
    public static boolean saveCustomForm(String formCode, Long applyId, String formData, String authority) {
        Boolean flag = true;
        if (isInclude(CustomFormEnum.class, formCode)) {
            try {
                log.info("保存定制表单信息：formCode:{}; applyId:{}; formData{}; authority{}", formCode, applyId, formData, authority);
                Class[] argsClass = {Long.class, String.class, String.class};
                // 在枚举类中，执行各定制表单的保存方法
                Class c = Class.forName(CustomFormEnum.getJavaClassByValue(formCode)); // 通过类名获得Class
                Object obj = c.newInstance(); // 实例化类
                Method methodReflect = obj.getClass().getMethod(method, argsClass); // 反射获得方法
                methodReflect.invoke(obj, applyId, formData, authority); // 调用此方法
            } catch (InstantiationException e) {
                e.printStackTrace();
                flag = false;
            } catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();// 获取目标异常
                t.printStackTrace();
                flag = false;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                flag = false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                flag = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 保存定制表单信息
     * @param formCode   表单code
     * @param applyId   申请编号
     */
    public static boolean customFormFinish(String formCode, Long applyId) {
        Boolean flag = true;
        if (isInclude(CustomFormEnum.class, formCode)) {
            try {
                log.info("保存定制表单信息：formCode:{}; applyId:{}", formCode, applyId);
                Class[] argsClass = {Long.class};
                // 在枚举类中，执行各定制表单的保存方法
                Class c = Class.forName(CustomFormEnum.getJavaClassByValue(formCode)); // 通过类名获得Class
                Object obj = c.newInstance(); // 实例化类
                Method methodReflect = obj.getClass().getMethod(finish, argsClass); // 反射获得方法
                methodReflect.invoke(obj, applyId); // 调用此方法
            } catch (InstantiationException e) {
                e.printStackTrace();
                flag = false;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                flag = false;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                flag = false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                flag = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 判断某个枚举是否包某个code值
     * @param enumClass 需要判断是否存在那个枚举类中
     * @param value 需要判断的值
     * @return 包含返回true，否则返回false
     */
    public static boolean isInclude(Class enumClass, String value){
        List enumList = EnumUtils.getEnumList(enumClass);
        for (int i = 0;i<enumList.size(); i++){
            Object en = enumList.get(i);
            Class<?> enClass = en.getClass();
            try {
                Method method = enClass.getMethod("getValue"); // 需要与枚举类方法对应
                Object invoke = method.invoke(en, null);
                if(value.equals(invoke.toString())) {
                    return true;
                }
            }catch (Exception e){
                log.error("枚举执行getCode方法失败...");
            }
        }
        return false;
    }

}
