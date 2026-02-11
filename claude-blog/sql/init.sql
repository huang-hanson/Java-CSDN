-- Claude Blog Database Initialization Script
-- Create database
CREATE DATABASE IF NOT EXISTS claude_blog DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE claude_blog;

-- User table
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `username` VARCHAR(50) NOT NULL COMMENT 'Username',
    `password` VARCHAR(100) NOT NULL COMMENT 'Password (BCrypt encrypted)',
    `email` VARCHAR(100) DEFAULT NULL COMMENT 'Email',
    `nickname` VARCHAR(50) DEFAULT NULL COMMENT 'Nickname',
    `avatar` VARCHAR(255) DEFAULT NULL COMMENT 'Avatar URL',
    `status` INT DEFAULT 1 COMMENT 'Status (1=active, 0=disabled)',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User table';

-- Post table
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `title` VARCHAR(200) NOT NULL COMMENT 'Post title',
    `content` TEXT NOT NULL COMMENT 'Post content',
    `summary` VARCHAR(500) DEFAULT NULL COMMENT 'Post summary',
    `author_id` BIGINT NOT NULL COMMENT 'Author ID',
    `view_count` INT DEFAULT 0 COMMENT 'View count',
    `like_count` INT DEFAULT 0 COMMENT 'Like count',
    `comment_count` INT DEFAULT 0 COMMENT 'Comment count',
    `status` INT DEFAULT 1 COMMENT 'Status (1=published, 0=draft)',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    PRIMARY KEY (`id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Post table';

-- Comment table
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `post_id` BIGINT NOT NULL COMMENT 'Post ID',
    `user_id` BIGINT NOT NULL COMMENT 'User ID',
    `parent_id` BIGINT DEFAULT 0 COMMENT 'Parent comment ID (0 for top-level)',
    `reply_user_id` BIGINT DEFAULT NULL COMMENT 'Reply to user ID',
    `content` VARCHAR(1000) NOT NULL COMMENT 'Comment content',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    PRIMARY KEY (`id`),
    KEY `idx_post_id` (`post_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Comment table';

-- Favorite table
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `user_id` BIGINT NOT NULL COMMENT 'User ID',
    `post_id` BIGINT NOT NULL COMMENT 'Post ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_post` (`user_id`, `post_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Favorite table';

-- Insert demo data
-- Demo user (password: 123456, BCrypt encrypted)
INSERT INTO `user` (`username`, `password`, `email`, `nickname`, `status`) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKTNDp8GsF5Vm6MjqVGxQK.Pd.FG', 'admin@blog.com', 'Administrator', 1),
('demo', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKTNDp8GsF5Vm6MjqVGxQK.Pd.FG', 'demo@blog.com', 'Demo User', 1);

-- Demo posts
INSERT INTO `post` (`title`, `content`, `summary`, `author_id`, `view_count`, `like_count`, `status`) VALUES
('Welcome to Claude Blog', '# Welcome!\n\nThis is a demo blog post created by Claude.\n\n## Features\n\n- User registration and login\n- Create and publish blog posts\n- Comment on posts\n- Favorite posts you like\n\nEnjoy blogging!', 'Welcome to Claude Blog - a full-featured blog system.', 1, 100, 10, 1),
('Getting Started with Spring Boot', '# Spring Boot Tutorial\n\nSpring Boot makes it easy to create stand-alone, production-grade Spring based Applications.\n\n## Key Features\n\n1. Auto-configuration\n2. Starter dependencies\n3. Embedded servers\n4. Production-ready features\n\n```java\n@SpringBootApplication\npublic class Application {\n    public static void main(String[] args) {\n        SpringApplication.run(Application.class, args);\n    }\n}\n```', 'Learn the basics of Spring Boot framework.', 1, 50, 5, 1);

-- Demo comments
INSERT INTO `comment` (`post_id`, `user_id`, `parent_id`, `content`) VALUES
(1, 2, 0, 'Great blog system!'),
(1, 1, 1, 'Thank you for your comment!');

-- Demo favorites
INSERT INTO `favorite` (`user_id`, `post_id`) VALUES
(2, 1);
