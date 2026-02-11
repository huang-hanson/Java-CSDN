-- Create database
CREATE DATABASE IF NOT EXISTS mall DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE mall;

-- User table
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'User ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT 'Username',
    `password` VARCHAR(100) NOT NULL COMMENT 'Password',
    `email` VARCHAR(100) COMMENT 'Email',
    `phone` VARCHAR(20) COMMENT 'Phone number',
    `status` TINYINT DEFAULT 1 COMMENT 'Status: 0-disabled, 1-enabled',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='User table';

-- Category table
CREATE TABLE IF NOT EXISTS `category` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Category ID',
    `name` VARCHAR(50) NOT NULL COMMENT 'Category name',
    `parent_id` BIGINT DEFAULT 0 COMMENT 'Parent category ID',
    `sort_order` INT DEFAULT 0 COMMENT 'Sort order',
    `status` TINYINT DEFAULT 1 COMMENT 'Status: 0-disabled, 1-enabled',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Category table';

-- Product table
CREATE TABLE IF NOT EXISTS `product` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Product ID',
    `name` VARCHAR(200) NOT NULL COMMENT 'Product name',
    `category_id` BIGINT NOT NULL COMMENT 'Category ID',
    `price` DECIMAL(10, 2) NOT NULL COMMENT 'Price',
    `stock` INT DEFAULT 0 COMMENT 'Stock quantity',
    `description` TEXT COMMENT 'Product description',
    `image` VARCHAR(500) COMMENT 'Product image URL',
    `status` TINYINT DEFAULT 1 COMMENT 'Status: 0-off shelf, 1-on shelf',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Product table';

-- Cart table
CREATE TABLE IF NOT EXISTS `cart` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Cart ID',
    `user_id` BIGINT NOT NULL COMMENT 'User ID',
    `product_id` BIGINT NOT NULL COMMENT 'Product ID',
    `quantity` INT DEFAULT 1 COMMENT 'Quantity',
    `checked` TINYINT DEFAULT 1 COMMENT 'Checked: 0-unchecked, 1-checked',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Cart table';

-- Order table
CREATE TABLE IF NOT EXISTS `order` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Order ID',
    `order_no` VARCHAR(50) NOT NULL UNIQUE COMMENT 'Order number',
    `user_id` BIGINT NOT NULL COMMENT 'User ID',
    `total_amount` DECIMAL(10, 2) NOT NULL COMMENT 'Total amount',
    `payment_amount` DECIMAL(10, 2) NOT NULL COMMENT 'Payment amount',
    `status` TINYINT DEFAULT 0 COMMENT 'Status: 0-pending, 1-paid, 2-shipped, 3-completed, 4-cancelled',
    `payment_time` DATETIME COMMENT 'Payment time',
    `delivery_time` DATETIME COMMENT 'Delivery time',
    `receiver_name` VARCHAR(50) COMMENT 'Receiver name',
    `receiver_phone` VARCHAR(20) COMMENT 'Receiver phone',
    `receiver_address` VARCHAR(200) COMMENT 'Receiver address',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Order table';

-- Order item table
CREATE TABLE IF NOT EXISTS `order_item` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Order item ID',
    `order_id` BIGINT NOT NULL COMMENT 'Order ID',
    `product_id` BIGINT NOT NULL COMMENT 'Product ID',
    `product_name` VARCHAR(200) NOT NULL COMMENT 'Product name',
    `product_image` VARCHAR(500) COMMENT 'Product image',
    `price` DECIMAL(10, 2) NOT NULL COMMENT 'Unit price',
    `quantity` INT NOT NULL COMMENT 'Quantity',
    `total_price` DECIMAL(10, 2) NOT NULL COMMENT 'Total price',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Order item table';

-- Insert sample data
-- Insert categories
INSERT INTO `category` (`name`, `parent_id`, `sort_order`) VALUES
('Electronics', 0, 1),
('Clothing', 0, 2),
('Books', 0, 3),
('Smartphones', 1, 1),
('Laptops', 1, 2),
('Men''s Clothing', 2, 1),
('Women''s Clothing', 2, 2);

-- Insert products
INSERT INTO `product` (`name`, `category_id`, `price`, `stock`, `description`, `image`, `status`) VALUES
('iPhone 15 Pro', 4, 7999.00, 100, 'Latest iPhone with A17 Pro chip', 'https://via.placeholder.com/300x300?text=iPhone15', 1),
('MacBook Pro 14', 5, 14999.00, 50, 'M3 Pro chip, 18GB RAM, 512GB SSD', 'https://via.placeholder.com/300x300?text=MacBook', 1),
('Samsung Galaxy S24', 4, 5999.00, 80, 'Samsung flagship with AI features', 'https://via.placeholder.com/300x300?text=GalaxyS24', 1),
('Men''s T-Shirt', 6, 99.00, 200, '100% cotton, comfortable fit', 'https://via.placeholder.com/300x300?text=TShirt', 1),
('Women''s Dress', 7, 199.00, 150, 'Elegant summer dress', 'https://via.placeholder.com/300x300?text=Dress', 1),
('Java Programming Guide', 3, 89.00, 300, 'Comprehensive Java learning guide', 'https://via.placeholder.com/300x300?text=JavaBook', 1),
('Dell XPS 15', 5, 12999.00, 30, 'Intel i9, 32GB RAM, 1TB SSD', 'https://via.placeholder.com/300x300?text=DellXPS', 1),
('iPad Pro 12.9', 4, 8999.00, 60, 'M2 chip, Liquid Retina XDR display', 'https://via.placeholder.com/300x300?text=iPad', 1);

-- Insert a test user (password is 123456 encoded with BCrypt)
INSERT INTO `user` (`username`, `password`, `email`, `phone`, `status`) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'admin@mall.com', '13800138000', 1),
('test', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'test@mall.com', '13800138001', 1);