package com.hanson.strategy.strategy.sort;


import com.csdn.dev.common.enums.CommonErrorEnum;
import com.csdn.dev.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SortStrategyFactory
 * @Description TODO
 * @date 2024/7/30 19:43
 **/
@Component
@Slf4j
public class SortStrategyFactory implements ApplicationContextAware {

    private static Map<String, SortStrategy> strategyMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, SortStrategy> map = applicationContext.getBeansOfType(SortStrategy.class);
        log.info("初始化排序策略工厂，共找到 {} 个排序策略：", map.size());
        map.forEach((key, value) -> {
            strategyMap.put(value.getStrategyName(), value);
            log.info("策略名称：{}，策略类：{}", key, value.getClass().getSimpleName());
        });
    }

    public <T extends SortStrategy> T getStrategy(String strategyName) {
        SortStrategy strategy = strategyMap.get(strategyName);
        if (strategy == null) {
            log.error("获取策略失败，策略名称：{}",strategyName);
            throw new BaseException(CommonErrorEnum.SYSTEM_ERROR);
        }
        return (T) strategy;
    }
}