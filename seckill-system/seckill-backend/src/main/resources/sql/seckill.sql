-- 商城秒杀系统数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS seckill DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE seckill;

-- 秒杀商品表
DROP TABLE IF EXISTS `seckill_product`;
CREATE TABLE `seckill_product` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `product_id` BIGINT(20) NOT NULL COMMENT '关联商品 ID',
    `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
    `product_image` VARCHAR(500) DEFAULT NULL COMMENT '商品图片',
    `original_price` DECIMAL(10,2) NOT NULL COMMENT '原价',
    `seckill_price` DECIMAL(10,2) NOT NULL COMMENT '秒杀价',
    `stock_count` INT(11) NOT NULL DEFAULT 0 COMMENT '秒杀库存',
    `sold_count` INT(11) NOT NULL DEFAULT 0 COMMENT '已售数量',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME NOT NULL COMMENT '结束时间',
    `status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '状态：0-未开始 1-进行中 2-已结束',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_status` (`status`),
    KEY `idx_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';

-- 秒杀订单表
DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `order_no` VARCHAR(64) NOT NULL COMMENT '订单号',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户 ID',
    `product_id` BIGINT(20) NOT NULL COMMENT '商品 ID',
    `product_name` VARCHAR(200) DEFAULT NULL COMMENT '商品名称',
    `seckill_price` DECIMAL(10,2) NOT NULL COMMENT '秒杀价格',
    `status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已完成 3-已取消',
    `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀订单表';

-- 秒杀用户表（用于限流和防刷）
DROP TABLE IF EXISTS `seckill_user`;
CREATE TABLE `seckill_user` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户 ID',
    `seckill_count` INT(11) NOT NULL DEFAULT 0 COMMENT '今日已参与秒杀次数',
    `last_seckill_time` DATETIME DEFAULT NULL COMMENT '最后秒杀时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`),
    KEY `idx_seckill_count` (`seckill_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀用户表';

-- 插入测试数据
INSERT INTO `seckill_product` (`product_id`, `product_name`, `product_image`, `original_price`, `seckill_price`, `stock_count`, `start_time`, `end_time`, `status`) VALUES
(1001, 'iPhone 15 Pro Max', '/images/iphone15.jpg', 9999.00, 4999.00, 100, '2026-04-02 10:00:00', '2026-04-02 22:00:00', 1),
(1002, 'MacBook Pro 14 寸', '/images/macbook.jpg', 14999.00, 9999.00, 50, '2026-04-02 10:00:00', '2026-04-02 22:00:00', 1),
(1003, 'AirPods Pro 2', '/images/airpods.jpg', 1899.00, 999.00, 200, '2026-04-02 14:00:00', '2026-04-02 16:00:00', 0),
(1004, 'iPad Air 5', '/images/ipad.jpg', 4799.00, 2999.00, 80, '2026-04-02 20:00:00', '2026-04-02 22:00:00', 0),
(1005, 'Apple Watch S9', '/images/watch.jpg', 2999.00, 1499.00, 150, '2026-04-03 10:00:00', '2026-04-03 12:00:00', 0);
