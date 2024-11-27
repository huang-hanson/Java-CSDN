package com.hanson.java.controller;

import com.hanson.java.entity.UserRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ModelAttributeController
 * @Description 测试@ModelAttribute注解
 * @date 2024/11/25 18:36
 **/
@RestController
public class ModelAttributeController {

    @PostMapping("/open/noauth/mkt/get_click_data")
    public UserRequest getClickData(@ModelAttribute UserRequest request) {
        // 返回封装的请求数据
        return request;
    }

}