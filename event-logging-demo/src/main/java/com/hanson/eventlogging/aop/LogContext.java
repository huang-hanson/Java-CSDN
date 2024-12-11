package com.hanson.eventlogging.aop;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LogContext
 * @Description TODO
 * @date 2024/12/9 14:31
 **/
public class LogContext {

    // ThreadLocal 用于存储每个线程的接口名称
    private static ThreadLocal<String> interfaceNameThreadLocal = new ThreadLocal<>();

    public static void setInterfaceName(String interfaceName) {
        interfaceNameThreadLocal.set(interfaceName);
    }

    public static String getInterfaceName() {
        return interfaceNameThreadLocal.get();
    }

    public static void clear() {
        interfaceNameThreadLocal.remove();
    }
}
