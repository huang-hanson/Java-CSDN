package com.db.test.controller;

import com.db.test.entity.request.StoreProductRequest;
import com.db.test.service.StoreProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    /**
     * 添加数据
     *
     * @param storeProductRequest 需要添加的数据
     * @return
     */
    @PostMapping("/addData")
    public String addData(@RequestBody StoreProductRequest storeProductRequest) {
        storeProductService.insertData(storeProductRequest);
        return "success";
    }

    /**
     * 通过id查询数据
     *
     * @param id 根据id查询数据
     * @return
     */
    @GetMapping("/selectData/{id}")
    public Map<String, Object> selectData(@PathVariable Integer id) {
        return storeProductService.getData(id);
    }
}