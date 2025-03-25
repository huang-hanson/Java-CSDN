package com.design.patterns.behavioural.iterator;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName Iterator
 * @Description 迭代器接口
 * @date 2025/3/25 19:09
 **/
public interface Iterator {
    boolean hasNext();
    Book next();
}
