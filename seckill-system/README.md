# 商城秒杀系统

## 项目简介

这是一个基于 Spring Boot + Redis + RabbitMQ + MyBatis-Plus 实现的商城秒杀系统，体现了秒杀场景下的核心技术思想：

- **限流** - 防止恶意刷接口
- **削峰** - MQ 异步处理，平滑流量峰值
- **防超卖** - Redis 原子操作 + 分布式锁
- **高性能** - 缓存热点数据，减少数据库压力

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.18 | 基础框架 |
| MyBatis-Plus | 3.5.3.1 | ORM 框架 |
| Redis | - | 缓存、分布式锁 |
| Redisson | 3.17.1 | Redis 客户端 |
| RabbitMQ | - | 消息队列 |
| MySQL | 8.0+ | 数据库 |
| Lombok | - | 简化代码 |
| Hutool | 5.8.31 | 工具库 |

## 项目结构

```
seckill-system/
├── seckill-backend/
│   ├── src/main/java/com/seckill/
│   │   ├── SeckillApplication.java      # 启动类
│   │   ├── controller/
│   │   │   └── SeckillController.java   # 秒杀 API 控制器
│   │   ├── service/
│   │   │   ├── SeckillService.java      # 服务接口
│   │   │   └── impl/
│   │   │       └── SeckillServiceImpl.java  # 服务实现
│   │   ├── mapper/                      # MyBatis DAO 层
│   │   ├── entity/                      # 实体类
│   │   ├── config/                      # 配置类
│   │   │   ├── RedisConfig.java         # Redis 配置
│   │   │   └── RabbitMQConfig.java      # RabbitMQ 配置
│   │   ├── common/                      # 公共类
│   │   │   ├── Result.java              # 统一响应
│   │   │   └── ResultCode.java          # 响应码枚举
│   │   ├── util/                        # 工具类
│   │   │   ├── DistributedLockUtil.java # 分布式锁工具
│   │   │   └── RateLimiterUtil.java     # 限流工具
│   │   └── aspect/                      # AOP 切面
│   │       └── RateLimitAspect.java     # 限流切面
│   └── src/main/resources/
│       ├── application.yml              # 配置文件
│       ├── mapper/                      # MyBatis XML
│       └── sql/                         # SQL 脚本
│           └── seckill.sql              # 初始化脚本
└── pom.xml
```

## 快速开始

### 1. 环境准备

确保以下服务已启动：
- MySQL 8.0+
- Redis
- RabbitMQ

### 2. 初始化数据库

执行 `src/main/resources/sql/seckill.sql` 脚本：

```bash
mysql -u root -p < src/main/resources/sql/seckill.sql
```

### 3. 修改配置

编辑 `application.yml`，修改以下配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/seckill
    username: root
    password: your_password
  redis:
    host: localhost
    port: 6379
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

### 4. 启动项目

```bash
cd seckill-backend
mvn spring-boot:run
```

## API 接口

### 1. 获取秒杀商品列表
```
GET /api/seckill/product/list
```

### 2. 获取秒杀商品详情
```
GET /api/seckill/product/{id}
```

### 3. 获取秒杀路径（防刷）
```
GET /api/seckill/path?productId={productId}
Header: X-User-Id: {userId}
```

### 4. 执行秒杀
```
POST /api/seckill/execute?productId={productId}
Header:
  X-User-Id: {userId}
  X-Seckill-Path: {path}
```

### 5. 查询秒杀结果
```
GET /api/seckill/result?productId={productId}
Header: X-User-Id: {userId}
```

## 核心流程

```
用户请求 → 限流过滤 → 验证秒杀路径 → 检查时间 → 分布式锁检查重复
                                              ↓
        返回结果 ← 缓存订单结果 ← MQ 异步创建订单 ← Redis 预减库存
```

## 秒杀核心思想

### 1. Redis 预减库存
- 秒杀开始前将库存加载到 Redis
- 使用 `decr` 原子操作预减库存
- 库存不足直接返回失败，避免数据库压力

### 2. 分布式锁（Redisson）
- 锁粒度：`userId + productId`
- 防止同一用户重复提交
- 使用 `tryLock` 避免死锁

### 3. RabbitMQ 异步下单
- 秒杀请求成功后发送消息到 MQ
- 后台消费者异步创建订单
- 平滑数据库压力，削峰填谷

### 4. 接口限流
- 基于 Redis 滑动窗口实现
- 限制单用户每秒请求数
- 使用 AOP 切面统一处理

### 5. 秒杀路径隐藏
- 秒杀 URL 动态生成
- 加入随机参数防刷
- 5 分钟过期

## 压测建议

使用 JMeter 进行压测：
- 线程数：1000-5000
- Ramp-Up 时间：1 秒
- 循环次数：1 次

观察：
- 无超卖现象
- 订单正确生成
- 响应时间在可接受范围内

## 注意事项

1. **Redis 持久化**：建议开启 AOF，防止库存数据丢失
2. **MQ 消息确认**：使用手动 ACK，确保消息不丢失
3. **数据库索引**：确保 productId、userId 等字段有索引
4. **库存回滚**：如果订单创建失败，需要恢复库存
