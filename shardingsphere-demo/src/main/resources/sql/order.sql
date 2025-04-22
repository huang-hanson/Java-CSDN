USE `order`;

-- 循环创建 t_order_info01 到 t_order_info10
DELIMITER $$

CREATE PROCEDURE create_order_info_tables()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE table_name VARCHAR(64);

    WHILE i <= 10 DO
        SET @table_name = CONCAT('t_order_info', LPAD(i, 2, '0'));
        SET @create_sql = CONCAT('
            CREATE TABLE IF NOT EXISTS ', @table_name, ' (
                orderid BIGINT NOT NULL,
                userid BIGINT,
                amount DECIMAL(18, 2),
                status VARCHAR(50),
                create_time VARCHAR(50),
                update_time VARCHAR(50),
                PRIMARY KEY (orderid),
                KEY idx_create_time (create_time)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
        ');
PREPARE stmt FROM @create_sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET i = i + 1;
END WHILE;
END$$

DELIMITER ;

-- 调用存储过程生成表
CALL create_order_info_tables();

-- 删除存储过程（可选）
DROP PROCEDURE create_order_info_tables;
