-- ----------------------------
-- Database: db0
-- ----------------------------
USE db0;


-- db0.user_info_0
CREATE TABLE IF NOT EXISTS `user_info_0` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_1
CREATE TABLE IF NOT EXISTS `user_info_1` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_2
CREATE TABLE IF NOT EXISTS `user_info_2` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_3
CREATE TABLE IF NOT EXISTS `user_info_3` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_4
CREATE TABLE IF NOT EXISTS `user_info_4` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_5
CREATE TABLE IF NOT EXISTS `user_info_5` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_6
CREATE TABLE IF NOT EXISTS `user_info_6` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_7
CREATE TABLE IF NOT EXISTS `user_info_7` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_8
CREATE TABLE IF NOT EXISTS `user_info_8` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_9
CREATE TABLE IF NOT EXISTS `user_info_9` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_10
CREATE TABLE IF NOT EXISTS `user_info_10` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_11
CREATE TABLE IF NOT EXISTS `user_info_11` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_12
CREATE TABLE IF NOT EXISTS `user_info_12` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_13
CREATE TABLE IF NOT EXISTS `user_info_13` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_14
CREATE TABLE IF NOT EXISTS `user_info_14` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_15
CREATE TABLE IF NOT EXISTS `user_info_15` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_16
CREATE TABLE IF NOT EXISTS `user_info_16` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_17
CREATE TABLE IF NOT EXISTS `user_info_17` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_18
CREATE TABLE IF NOT EXISTS `user_info_18` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db0.user_info_19
CREATE TABLE IF NOT EXISTS `user_info_19` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';

-- ----------------------------
-- Database: db1
-- ----------------------------
USE db1;


-- db1.user_info_0
CREATE TABLE IF NOT EXISTS `user_info_0` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_1
CREATE TABLE IF NOT EXISTS `user_info_1` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_2
CREATE TABLE IF NOT EXISTS `user_info_2` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_3
CREATE TABLE IF NOT EXISTS `user_info_3` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_4
CREATE TABLE IF NOT EXISTS `user_info_4` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_5
CREATE TABLE IF NOT EXISTS `user_info_5` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_6
CREATE TABLE IF NOT EXISTS `user_info_6` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_7
CREATE TABLE IF NOT EXISTS `user_info_7` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_8
CREATE TABLE IF NOT EXISTS `user_info_8` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_9
CREATE TABLE IF NOT EXISTS `user_info_9` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_10
CREATE TABLE IF NOT EXISTS `user_info_10` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_11
CREATE TABLE IF NOT EXISTS `user_info_11` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_12
CREATE TABLE IF NOT EXISTS `user_info_12` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_13
CREATE TABLE IF NOT EXISTS `user_info_13` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_14
CREATE TABLE IF NOT EXISTS `user_info_14` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_15
CREATE TABLE IF NOT EXISTS `user_info_15` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_16
CREATE TABLE IF NOT EXISTS `user_info_16` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_17
CREATE TABLE IF NOT EXISTS `user_info_17` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_18
CREATE TABLE IF NOT EXISTS `user_info_18` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db1.user_info_19
CREATE TABLE IF NOT EXISTS `user_info_19` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';

-- ----------------------------
-- Database: db2
-- ----------------------------
USE db2;


-- db2.user_info_0
CREATE TABLE IF NOT EXISTS `user_info_0` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_1
CREATE TABLE IF NOT EXISTS `user_info_1` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_2
CREATE TABLE IF NOT EXISTS `user_info_2` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_3
CREATE TABLE IF NOT EXISTS `user_info_3` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_4
CREATE TABLE IF NOT EXISTS `user_info_4` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_5
CREATE TABLE IF NOT EXISTS `user_info_5` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_6
CREATE TABLE IF NOT EXISTS `user_info_6` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_7
CREATE TABLE IF NOT EXISTS `user_info_7` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_8
CREATE TABLE IF NOT EXISTS `user_info_8` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_9
CREATE TABLE IF NOT EXISTS `user_info_9` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_10
CREATE TABLE IF NOT EXISTS `user_info_10` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_11
CREATE TABLE IF NOT EXISTS `user_info_11` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_12
CREATE TABLE IF NOT EXISTS `user_info_12` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_13
CREATE TABLE IF NOT EXISTS `user_info_13` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_14
CREATE TABLE IF NOT EXISTS `user_info_14` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_15
CREATE TABLE IF NOT EXISTS `user_info_15` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_16
CREATE TABLE IF NOT EXISTS `user_info_16` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_17
CREATE TABLE IF NOT EXISTS `user_info_17` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_18
CREATE TABLE IF NOT EXISTS `user_info_18` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';


-- db2.user_info_19
CREATE TABLE IF NOT EXISTS `user_info_19` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_age` INT DEFAULT NULL COMMENT '用户年龄',
    `user_sex` VARCHAR(10) DEFAULT NULL COMMENT '用户性别',
    `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户手机号',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `user_address` VARCHAR(255) DEFAULT NULL COMMENT '用户地址',
    `view_time` DATETIME DEFAULT NULL COMMENT '最后查看时间',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息分表';
