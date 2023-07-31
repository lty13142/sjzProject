package com.crcm.auth.extend;

/**
 * @ClassName IntegrationAuthenticationContext
 * @Description 集成认证上下文
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class IntegrationAuthenticationContext {

    /**
     * ThreadLocal无法解决共享对象的更新问题ThreadLocal 对象建议使用 static 修饰。
     * 这个变量是针对一个线程内所有操作共有的，所以设置为静态变量，
     * 所有此类实例共享 此静态变量 ，也就是说在类第一次被使用时装载，只分配一块存储空间，
     * 所有此类的对象(只 要是这个线程内定义的)都可以操控这个变量
     */
    private static ThreadLocal<IntegrationAuthenticationEntity> holder = new ThreadLocal<>();

    static void set(IntegrationAuthenticationEntity entity){
        holder.set(entity);
    }

    public static IntegrationAuthenticationEntity get(){
        return holder.get();
    }

    /**
     * 回收自定义的ThreadLocal变量
     * <p>
     * 必须回收自定义的ThreadLocal变量，
     * 尤其在线程池的场景下，线程经常会被复用，
     * 如果不清理自定义的ThreadLocal变量，
     * 可能会影响后续业务逻辑和造成内存泄露等问题
     * </p>
     */
    static void clear(){
        holder.remove();
    }
}
