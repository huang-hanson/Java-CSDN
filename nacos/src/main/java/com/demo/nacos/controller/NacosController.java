package com.demo.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NacosController
 * @Description TODO
 * @date 2025/3/10 14:00
 **/
@RestController
public class NacosController {

    @Value("${nacos.test.value1:111}")
    private String value1;

    @Value("${nacos.test.value2:222}")
    private String value2;

    @GetMapping("/nacos")
    public String nacos() {
        return value1 + ":" + value2;
    }
}