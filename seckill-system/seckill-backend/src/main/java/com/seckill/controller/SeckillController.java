package com.seckill.controller;

import com.seckill.common.Result;
import com.seckill.entity.SeckillOrder;
import com.seckill.entity.SeckillProduct;
import com.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 秒杀控制器
 */
@RestController
@RequestMapping("/api/seckill")
@CrossOrigin
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取秒杀商品列表
     */
    @GetMapping("/product/list")
    public Result<List<SeckillProduct>> getSeckillProductList() {
        List<SeckillProduct> products = seckillService.getSeckillProductList();
        return Result.success(products);
    }

    /**
     * 获取秒杀商品详情
     */
    @GetMapping("/product/{id}")
    public Result<SeckillProduct> getSeckillProductById(@PathVariable Long id) {
        SeckillProduct product = seckillService.getSeckillProductById(id);
        if (product != null) {
            return Result.success(product);
        }
        return Result.error("商品不存在");
    }

    /**
     * 获取秒杀路径（用于防刷）
     * 用户在秒杀前需要先调用此接口获取路径
     */
    @GetMapping("/path")
    public Result<String> getSeckillPath(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam Long productId) {
        String path = seckillService.getSeckillPath(userId, productId);
        return Result.success(path);
    }

    /**
     * 执行秒杀
     * @param userId 用户 ID（从 header 获取）
     * @param productId 商品 ID
     * @param path 秒杀路径（从 header 获取）
     */
    @PostMapping("/execute")
    public Result<SeckillService.SeckillResult> executeSeckill(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam Long productId,
            @RequestHeader("X-Seckill-Path") String path) {

        SeckillService.SeckillResult result = seckillService.executeSeckill(userId, productId, path);
        if (result.isSuccess()) {
            return Result.success(result.getMessage(), result.getOrder());
        } else {
            return Result.error(result.getMessage());
        }
    }

    /**
     * 查询秒杀结果
     */
    @GetMapping("/result")
    public Result<SeckillOrder> getSeckillResult(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam Long productId) {

        SeckillOrder order = seckillService.getSeckillResult(userId, productId);
        if (order != null) {
            return Result.success(order);
        }
        return Result.success(null); // 返回 null 表示结果还未出来
    }

    /**
     * 获取用户秒杀订单列表
     */
    @GetMapping("/order/{userId}")
    public Result<List<SeckillOrder>> getUserSeckillOrders(@PathVariable Long userId) {
        // 这里可以扩展一个方法来查询用户的所有秒杀订单
        return Result.success(java.util.Collections.emptyList());
    }
}
