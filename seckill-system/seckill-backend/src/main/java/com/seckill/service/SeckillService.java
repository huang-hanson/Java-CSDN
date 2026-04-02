package com.seckill.service;

import com.seckill.entity.SeckillOrder;
import com.seckill.entity.SeckillProduct;

import java.util.List;

/**
 * 秒杀服务接口
 */
public interface SeckillService {

    /**
     * 获取秒杀商品列表
     */
    List<SeckillProduct> getSeckillProductList();

    /**
     * 获取秒杀商品详情
     */
    SeckillProduct getSeckillProductById(Long productId);

    /**
     * 获取秒杀路径（用于防刷）
     * @param userId 用户 ID
     * @param productId 商品 ID
     * @return 秒杀路径
     */
    String getSeckillPath(Long userId, Long productId);

    /**
     * 执行秒杀
     * @param userId 用户 ID
     * @param productId 商品 ID
     * @param path 秒杀路径（验证用）
     * @return 秒杀结果
     */
    SeckillResult executeSeckill(Long userId, Long productId, String path);

    /**
     * 查询秒杀结果
     * @param userId 用户 ID
     * @param productId 商品 ID
     * @return 秒杀订单
     */
    SeckillOrder getSeckillResult(Long userId, Long productId);

    /**
     * 秒杀结果类
     */
    class SeckillResult {
        private boolean success;
        private String message;
        private SeckillOrder order;

        public SeckillResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public SeckillResult(boolean success, String message, SeckillOrder order) {
            this.success = success;
            this.message = message;
            this.order = order;
        }

        public static SeckillResult success(String message, SeckillOrder order) {
            return new SeckillResult(true, message, order);
        }

        public static SeckillResult error(String message) {
            return new SeckillResult(false, message, null);
        }

        // Getters
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public SeckillOrder getOrder() { return order; }
    }
}
