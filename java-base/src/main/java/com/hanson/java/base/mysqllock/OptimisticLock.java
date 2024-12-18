package com.hanson.java.base.mysqllock;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName OptimisticLock
 * @date 2024/12/18 18:40
 **/
public interface OptimisticLock {

    boolean lock(String lockName);

    void unLock(String lockName);
}
