-- 切换到 db2
USE db2;

-- 创建表 user_info_0 到 user_info_19
DELIMITER $$
BEGIN
    DECLARE i INT DEFAULT 0;
    WHILE i < 20 DO
        SET @sql = CONCAT('CREATE TABLE IF NOT EXISTS user_info_', i, ' (
            id BIGINT NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
            user_id BIGINT DEFAULT NULL COMMENT ''用户id'',
            user_name VARCHAR(100) DEFAULT NULL COMMENT ''用户名'',
            user_age INT DEFAULT NULL COMMENT ''用户年龄'',
            user_sex VARCHAR(10) DEFAULT NULL COMMENT ''用户性别'',
            user_phone VARCHAR(20) DEFAULT NULL COMMENT ''用户手机号'',
            user_email VARCHAR(100) DEFAULT NULL COMMENT ''用户邮箱'',
            user_address VARCHAR(255) DEFAULT NULL COMMENT ''用户地址'',
            view_time DATETIME DEFAULT NULL COMMENT ''最后查看时间'',
            create_time DATETIME DEFAULT NULL COMMENT ''创建时间'',
            update_time DATETIME DEFAULT NULL COMMENT ''更新时间'',
            PRIMARY KEY (id)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT=''用户信息分表''');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
SET i = i + 1;
END WHILE;
END$$
DELIMITER ;
