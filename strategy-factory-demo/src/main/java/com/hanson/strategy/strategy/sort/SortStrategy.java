package com.hanson.strategy.strategy.sort;

import com.hanson.strategy.strategy.BaseStrategy;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName SortStrategy
 * @Description TODO
 * @date 2024/7/30 19:37
 **/
public interface SortStrategy extends BaseStrategy {

    /**
     * 对数组进行排序
     * @param array 待排序的数组
     */
    void sort(int[] array);
}
