package com.crcm.bpm.utils;


import com.crcm.core.exception.CustomException;

/**
 * @author Administrator
 */
public class PrecessThreadLocalUtil {

    public static final ThreadLocal<CustomException> THREAD_LOCAL = new ThreadLocal<>();
}
