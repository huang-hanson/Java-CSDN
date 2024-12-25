package com.csdn.dev.controller;

import com.csdn.dev.entity.User;
import com.csdn.dev.service.SelectOneDemoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SelectOneTestCOntrollwe
 * @Description TODO
 * @date 2024/12/25 19:48
 **/
@RestController
public class SelectOneTestController {

    @Resource
    private SelectOneDemoService selectOneDemoService;

    @PostMapping("/test")
    public User test(@RequestBody User user) {
        return selectOneDemoService.selectOne(user);
    }

}