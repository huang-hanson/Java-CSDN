package com.design.patterns.behavioural.iterator;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName Aggregate
 * @Description 聚合接口
 * @date 2025/3/25 19:10
 **/
public interface Aggregate {
    Iterator createIterator();
}
