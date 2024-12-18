package com.hanson.java.base.mysqllock;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName SelectForUpdateLock
 * @date 2024/12/18 17:42
 **/
public interface SelectForUpdateLock {
    void lock(String lockName, Runnable runnable);
}
