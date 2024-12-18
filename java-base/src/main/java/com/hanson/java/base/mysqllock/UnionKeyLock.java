package com.hanson.java.base.mysqllock;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName UnionKeyLock
 * @date 2024/12/18 16:59
 **/
public interface UnionKeyLock {
    Boolean lock(String lockName, Integer second);

    void unLock(String lockName);
}
