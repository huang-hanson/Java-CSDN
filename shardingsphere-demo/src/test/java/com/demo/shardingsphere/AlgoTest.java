package com.demo.shardingsphere;

import com.demo.shardingsphere.algo.DeliveryShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AlgoTest
 * @Description 算法测试
 * @date 2025/4/22 16:24
 **/
@Slf4j
@SpringBootTest
public class AlgoTest {

    @Resource
    DeliveryShardingAlgorithm deliveryShardingAlgorithm;

    @Test
    public void test() {
        String s = deliveryShardingAlgorithm.tableAlgorithm("4215215");
        log.info("s:{}",s);
    }
}