package com.hanson.strategy.controller;

import com.hanson.strategy.strategy.sort.SortStrategy;
import com.hanson.strategy.strategy.sort.SortStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SortController
 * @Description TODO
 * @date 2024/7/30 19:55
 **/
@RestController
@RequestMapping("/sort")
@Slf4j
public class SortController {

    @Autowired
    private SortStrategyFactory strategyFactory;

    @GetMapping("/array")
    public ResponseEntity<Map<String,String>> sortArray(@RequestParam String strategyName, @RequestParam int[] array) {
        Map<String,String> result = new HashMap<>();

        SortStrategy strategy = strategyFactory.getStrategy(strategyName);
        strategy.sort(array);

        // 返回排序后的数组
        String sortedArray = Arrays.toString(array);
        log.info("排序后的数组：{}", sortedArray);
        result.put("排序结果", sortedArray);
        result.put("排序策略", strategy.getStrategyName());
        return ResponseEntity.ok(result);
    }
}