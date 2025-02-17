package com.db.test.controller;

import com.db.test.entity.request.StoreProductRequest;
import com.db.test.service.StoreProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DbTestController
 * @Description 存储中间件api测试
 * @date 2025/2/17 14:41
 **/
@RestController
@RequestMapping("/dbTest")
public class DbTestController {

    @Resource
    private StoreProductService storeProductService;

    @PostMapping("/addData")
    public String addData(@RequestBody StoreProductRequest storeProductRequest) {
        storeProductService.insertData(storeProductRequest);
        return "success";
    }

}