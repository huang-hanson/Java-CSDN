package com.seckill.service;

import com.seckill.entity.SeckillOrder;
import com.seckill.entity.SeckillProduct;
import com.seckill.entity.SeckillUser;
import com.seckill.mapper.SeckillOrderMapper;
import com.seckill.mapper.SeckillProductMapper;
import com.seckill.mapper.SeckillUserMapper;
import com.seckill.util.DistributedLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀服务实现类
 * 核心思想：
 * 1. Redis 预减库存（防超卖）
 * 2. RabbitMQ 异步下单（削峰）
 * 3. 分布式锁（防止重复秒杀）
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillProductMapper seckillProductMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private SeckillUserMapper seckillUserMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DistributedLockUtil distributedLockUtil;

    /**
     * Redis Key 定义
     */
    private static final String SECKILL_STOCK_KEY = "seckill:stock:";
    private static final String SECKILL_USER_KEY = "seckill:user:";
    private static final String SECKILL_RESULT_KEY = "seckill:result:";
    private static final String SECKILL_PATH_KEY = "seckill:path:";

    @Override
    public List<SeckillProduct> getSeckillProductList() {
        return seckillProductMapper.selectList(null);
    }

    @Override
    public SeckillProduct getSeckillProductById(Long productId) {
        SeckillProduct product = seckillProductMapper.selectById(productId);
        if (product != null) {
            // 从 Redis 获取实时库存
            Object stockObj = redisTemplate.opsForValue().get(SECKILL_STOCK_KEY + productId);
            if (stockObj != null) {
                product.setStockCount(Integer.parseInt(stockObj.toString()));
            }
        }
        return product;
    }

    @Override
    public String getSeckillPath(Long userId, Long productId) {
        // 生成随机路径，用于防刷
        String path = UUID.randomUUID().toString().replace("-", "");

        // 将路径存入 Redis，5 分钟有效
        String key = SECKILL_PATH_KEY + userId + ":" + productId;
        redisTemplate.opsForValue().set(key, path, 5, TimeUnit.MINUTES);

        return path;
    }

    @Override
    public SeckillResult executeSeckill(Long userId, Long productId, String path) {
        // 1. 验证秒杀路径
        String pathKey = SECKILL_PATH_KEY + userId + ":" + productId;
        Object storedPath = redisTemplate.opsForValue().get(pathKey);
        if (storedPath == null || !storedPath.toString().equals(path)) {
            return SeckillResult.error("非法的秒杀路径");
        }

        // 2. 检查秒杀时间
        SeckillProduct product = seckillProductMapper.selectById(productId);
        if (product == null) {
            return SeckillResult.error("商品不存在");
        }

        Date now = new Date();
        if (now.before(product.getStartTime())) {
            return SeckillResult.error("秒杀尚未开始");
        }
        if (now.after(product.getEndTime())) {
            return SeckillResult.error("秒杀已经结束");
        }

        // 3. 检查用户是否已经购买过（使用分布式锁）
        String userLockKey = "seckill:lock:" + userId + ":" + productId;
        Boolean hasPurchased = distributedLockUtil.executeWithLock(userLockKey, () -> {
            String userKey = SECKILL_USER_KEY + userId + ":" + productId;
            return redisTemplate.hasKey(userKey);
        });

        if (Boolean.TRUE.equals(hasPurchased)) {
            return SeckillResult.error("您已经购买过该商品，不能重复秒杀");
        }

        // 4. Redis 预减库存（原子操作，防止超卖）
        String stockKey = SECKILL_STOCK_KEY + productId;
        // 如果 Redis 中没有库存数据，先加载
        if (!redisTemplate.hasKey(stockKey)) {
            redisTemplate.opsForValue().set(stockKey, String.valueOf(product.getStockCount()));
        }

        long stock = redisTemplate.opsForValue().decrement(stockKey);
        if (stock < 0) {
            // 库存不足，恢复
            redisTemplate.opsForValue().increment(stockKey);
            return SeckillResult.error("库存不足，秒杀失败");
        }

        // 5. 标记用户已购买
        String userKey = SECKILL_USER_KEY + userId + ":" + productId;
        redisTemplate.opsForValue().set(userKey, "1", 24, TimeUnit.HOURS);

        // 6. 发送消息到 RabbitMQ，异步创建订单
        SeckillMessage message = new SeckillMessage(userId, productId, product.getSeckillPrice());
        redisTemplate.convertAndSend("seckill.order.exchange", "seckill.order", message);

        // 7. 返回排队中结果
        return SeckillResult.success("秒杀成功，正在处理订单", null);
    }

    @Override
    public SeckillOrder getSeckillResult(Long userId, Long productId) {
        // 先从 Redis 缓存的结果中获取
        String resultKey = SECKILL_RESULT_KEY + userId + ":" + productId;
        Object orderObj = redisTemplate.opsForValue().get(resultKey);
        if (orderObj != null) {
            return (SeckillOrder) orderObj;
        }

        // 从数据库查询
        SeckillOrder order = seckillOrderMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<SeckillOrder>()
                        .eq(SeckillOrder::getUserId, userId)
                        .eq(SeckillOrder::getProductId, productId)
                        .orderByDesc(SeckillOrder::getCreateTime)
                        .last("LIMIT 1")
        );

        // 缓存到 Redis
        if (order != null) {
            redisTemplate.opsForValue().set(resultKey, order, 5, TimeUnit.MINUTES);
        }

        return order;
    }

    /**
     * 消费 RabbitMQ 消息，异步创建订单
     */
    @RabbitListener(queues = "seckill.order.queue")
    public void consumeSeckillMessage(@Payload SeckillMessage message,
                                       @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            Long userId = message.getUserId();
            Long productId = message.getProductId();

            logger.info("收到秒杀消息：userId={}, productId={}", userId, productId);

            // 1. 检查是否已经创建过订单（幂等性检查）
            String userKey = SECKILL_USER_KEY + userId + ":" + productId;
            if (!Boolean.TRUE.equals(redisTemplate.hasKey(userKey))) {
                logger.warn("用户未进行秒杀操作，跳过订单创建：userId={}, productId={}", userId, productId);
                return;
            }

            // 2. 创建秒杀订单
            SeckillOrder order = new SeckillOrder();
            order.setOrderNo(generateOrderNo());
            order.setUserId(userId);
            order.setProductId(productId);
            order.setSeckillPrice(message.getPrice());
            order.setStatus(0); // 待支付

            int result = seckillOrderMapper.insert(order);
            if (result > 0) {
                // 3. 扣减数据库库存（最终一致性）
                seckillProductMapper.decreaseStock(productId);

                // 4. 将订单结果缓存到 Redis
                String resultKey = SECKILL_RESULT_KEY + userId + ":" + productId;
                redisTemplate.opsForValue().set(resultKey, order, 5, TimeUnit.MINUTES);

                logger.info("秒杀订单创建成功：orderNo={}", order.getOrderNo());

                // 手动 ACK
                // 注意：需要配置手动 ACK 模式
            } else {
                logger.error("秒杀订单创建失败：userId={}, productId={}", userId, productId);
                // 恢复库存
                String stockKey = SECKILL_STOCK_KEY + productId;
                redisTemplate.opsForValue().increment(stockKey);
                redisTemplate.delete(userKey);
            }
        } catch (Exception e) {
            logger.error("处理秒杀消息异常", e);
            // 这里可以根据业务需求选择重试或拒绝消息
        }
    }

    /**
     * 生成订单号
     */
    private String generateOrderNo() {
        return "SK" + System.currentTimeMillis() +
               UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    /**
     * 秒杀消息类
     */
    public static class SeckillMessage {
        private Long userId;
        private Long productId;
        private java.math.BigDecimal price;

        public SeckillMessage() {
        }

        public SeckillMessage(Long userId, Long productId, java.math.BigDecimal price) {
            this.userId = userId;
            this.productId = productId;
            this.price = price;
        }

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public java.math.BigDecimal getPrice() { return price; }
        public void setPrice(java.math.BigDecimal price) { this.price = price; }
    }
}
