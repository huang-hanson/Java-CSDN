# 秒杀系统流程图

## 1. 秒杀核心流程

```mermaid
graph TD
    Start([用户发起秒杀请求]) --> PathCheck{验证秒杀路径}
    PathCheck -->|路径无效 | PathError([返回失败])
    PathCheck -->|路径有效 | TimeCheck{检查秒杀时间}
    TimeCheck -->|未开始 | TimeError1([返回未开始])
    TimeCheck -->|已结束 | TimeError2([返回已结束])
    TimeCheck -->|进行中 | RepeatCheck{检查是否重复购买}
    RepeatCheck -->|已购买 | RepeatError([返回已购买])
    RepeatCheck -->|未购买 | StockCheck[Redis 预减库存]
    StockCheck --> StockResult{库存是否充足}
    StockResult -->|不足 | StockError([返回库存不足])
    StockResult -->|充足 | UserMark[标记用户已购买]
    UserMark --> MQSend[发送消息到 RabbitMQ]
    MQSend --> MQResult([返回排队中])
    MQSend --> MQConsumer[RabbitMQ 消费者异步处理]
    MQConsumer --> Idempotent{幂等性检查}
    Idempotent -->|未秒杀 | CreateOrder[创建秒杀订单]
    Idempotent -->|已秒杀 | SkipEnd([跳过])
    CreateOrder --> OrderResult{订单创建成功}
    OrderResult -->|失败 | Rollback[恢复库存删除标记]
    OrderResult -->|成功 | DBStock[数据库扣减库存]
    DBStock --> CacheResult[缓存订单到 Redis]
    CacheResult --> SkipEnd
```

## 2. 限流流程

```mermaid
graph TD
    Request([请求到达]) --> Filter{是否秒杀接口}
    Filter -->|否 | Pass[放行]
    Filter -->|是 | GetId[获取用户标识]
    GetId --> RateLimit[Redis 滑动窗口限流]
    RateLimit --> Check{是否超过限制}
    Check -->|是 | LimitError([返回请求频繁])
    Check -->|否 | Pass
```

## 3. 分布式锁防重复

```mermaid
graph TD
    Start([开始秒杀]) --> GetLock[尝试获取分布式锁]
    GetLock --> LockResult{获取锁成功}
    LockResult -->|失败 | Wait{等待重试}
    Wait -->|超时 | LockError([返回重复请求])
    Wait --> GetLock
    LockResult -->|成功 | CheckRedis[检查 Redis 是否已购买]
    CheckRedis --> Purchased{是否已购买}
    Purchased -->|是 | ReleaseLock[释放锁]
    Purchased -->|否 | Continue[继续秒杀流程]
    ReleaseLock --> PurchasedError([返回已购买])
    Continue --> ReleaseLock2[释放锁]
    ReleaseLock2 --> Stock[Redis 预减库存]
```

## 4. Redis 预减库存流程

```mermaid
graph TD
    Start([开始扣减库存]) --> CheckRedis{Redis 中有库存数据}
    CheckRedis -->|否 | LoadStock[从数据库加载库存到 Redis]
    CheckRedis -->|是 | Decr[执行 decr 原子操作]
    LoadStock --> Decr
    Decr --> Result{返回值大于等于 0}
    Result -->|否 | Increment[执行 increment 恢复]
    Result -->|是 | Success([秒杀成功])
    Increment --> Fail([返回秒杀失败])
```

## 5. RabbitMQ 异步下单流程

```mermaid
graph TD
    Start([消费者接收消息]) --> ParseMsg[解析消息]
    ParseMsg --> Idempotent{幂等性检查}
    Idempotent -->|否 | Log[记录日志跳过]
    Idempotent -->|是 | CreateOrder[创建秒杀订单]
    CreateOrder --> OrderResult{创建成功}
    OrderResult -->|否 | Restore[恢复 Redis 库存删除标记]
    OrderResult -->|是 | DecrDB[数据库扣减库存]
    DecrDB --> CacheOrder[缓存订单到 Redis]
    CacheOrder --> ACK[手动 ACK 确认消息]
```

## 6. 整体架构流程

```mermaid
graph LR
    User([用户]) --> Gateway[网关限流层]
    Gateway --> Controller[SeckillController]
    Controller --> Service[SeckillService]
    Service --> Redis[(Redis 缓存)]
    Service --> MQ[RabbitMQ]
    MQ --> Consumer[MQ 消费者]
    Consumer --> DB[(MySQL 数据库)]
    Service --> DB
```

## 7. 时序图

```mermaid
sequenceDiagram
    participant U as 用户
    participant C as Controller
    participant S as Service
    participant Redis as Redis
    participant MQ as RabbitMQ
    participant DB as MySQL

    U->>C: 1. 获取秒杀路径
    C->>Redis: 生成随机 path 存入 Redis
    Redis-->>C: 返回 path
    C-->>U: 返回 path

    U->>C: 2. 发起秒杀请求
    C->>S: executeSeckill

    S->>Redis: 验证 path
    S->>Redis: 检查是否重复购买
    S->>Redis: decr 预减库存

    alt 库存不足
        Redis-->>S: 库存不足
        S-->>C: 返回失败
        C-->>U: 秒杀失败
    else 库存充足
        S->>Redis: 标记用户已购买
        S->>MQ: 发送秒杀消息
        MQ-->>S: 消息已接收
        S-->>C: 返回排队中
        C-->>U: 秒杀成功等待结果

        par 异步处理
            MQ->>MQ: 消费者处理消息
            MQ->>Redis: 幂等性检查
            MQ->>DB: 创建订单
            DB->>DB: 扣减库存
            MQ->>Redis: 缓存订单结果
        end

        U->>C: 3. 轮询查询结果
        C->>Redis: 查询订单
        Redis-->>C: 返回订单
        C-->>U: 返回订单信息
    end
```

## 8. 秒杀路径示例 - 接口返回与前端调用

### 8.1 接口返回数据格式

#### 第一步：获取秒杀路径

**前端请求：**
```http
GET /api/seckill/path?productId=1001
Header:
  X-User-Id: 12345
```

**后端返回 JSON：**
```json
{
  "code": 200,
  "message": "success",
  "data": "a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6",
  "timestamp": 1712048400000
}
```

这个 `data` 就是一个 32 位的随机 UUID 字符串。

#### 第二步：发起秒杀

**前端请求：**
```http
POST /api/seckill/execute?productId=1001
Header:
  X-User-Id: 12345
  X-Seckill-Path: a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6
```

**后端返回 JSON（秒杀成功，排队中）：**
```json
{
  "code": 200,
  "message": "秒杀成功，正在处理订单",
  "data": null,
  "timestamp": 1712048401000
}
```

**后端返回 JSON（秒杀失败）：**
```json
{
  "code": 500,
  "message": "库存不足",
  "data": null,
  "timestamp": 1712048401000
}
```

#### 第三步：查询秒杀结果

**前端请求：**
```http
GET /api/seckill/result?productId=1001
Header:
  X-User-Id: 12345
```

**后端返回 JSON（有订单）：**
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 10001,
    "orderNo": "SK1712048401000A1B2C3D4",
    "userId": 12345,
    "productId": 1001,
    "productName": "iPhone 15 Pro Max",
    "seckillPrice": 4999.00,
    "status": 0,
    "createTime": "2026-04-02 10:00:01"
  },
  "timestamp": 1712048405000
}
```

### 8.2 前端页面完整示例

```html
<!DOCTYPE html>
<html>
<head>
    <title>秒杀商品</title>
</head>
<body>
    <div id="app">
        <h1>{{ productName }}</h1>
        <p>秒杀价：￥{{ seckillPrice }}</p>
        <p>库存：{{ stock }}</p>
        <button id="seckillBtn" onclick="doSeckill()">立即秒杀</button>
        <div id="result"></div>
    </div>

    <script>
        const userId = 12345;  // 实际从登录信息获取
        const productId = 1001;
        let seckillPath = null;

        // 页面加载时，先获取秒杀路径
        async function loadSeckillPath() {
            const res = await fetch(`/api/seckill/path?productId=${productId}`, {
                headers: { 'X-User-Id': userId }
            });
            const result = await res.json();

            if (result.code === 200) {
                seckillPath = result.data;
                console.log('获取到秒杀路径:', seckillPath);
                // 此时按钮才启用
                document.getElementById('seckillBtn').disabled = false;
            }
        }

        // 点击秒杀按钮
        async function doSeckill() {
            const res = await fetch(`/api/seckill/execute?productId=${productId}`, {
                method: 'POST',
                headers: {
                    'X-User-Id': userId,
                    'X-Seckill-Path': seckillPath
                }
            });
            const result = await res.json();

            document.getElementById('result').innerHTML =
                `<p>${result.message}</p>`;

            // 开始轮询查询结果
            if (result.code === 200) {
                pollResult();
            }
        }

        // 轮询查询秒杀结果
        async function pollResult() {
            const timer = setInterval(async () => {
                const res = await fetch(`/api/seckill/result?productId=${productId}`, {
                    headers: { 'X-User-Id': userId }
                });
                const result = await res.json();

                if (result.data) {
                    clearInterval(timer);
                    document.getElementById('result').innerHTML +=
                        `<p>订单号：${result.data.orderNo}</p>
                         <p>价格：￥${result.data.seckillPrice}</p>`;
                }
            }, 1000);  // 每秒轮询一次
        }

        // 页面加载时获取 path
        loadSeckillPath();
    </script>
</body>
</html>
```

### 8.3 完整流程示意图

```
┌─────────────┐
│   用户打开   │
│  秒杀页面    │
└──────┬──────┘
       │
       ▼
┌─────────────────────────────────┐
│ 前端自动调用 /api/seckill/path  │
└──────┬──────────────────────────┘
       │
       ▼
┌─────────────────────────────────┐
│ 后端返回 path                    │
│ "a1b2c3d4e5f6g7h8..."          │
│ 前端存储起来，启用秒杀按钮       │
└──────┬──────────────────────────┘
       │
       ▼
┌─────────────┐
│  用户点击    │
│ "立即秒杀"  │
└──────┬──────┘
       │
       ▼
┌─────────────────────────────────┐
│ 前端携带 path 发起秒杀请求       │
│ Header: X-Seckill-Path: xxx    │
└──────┬──────────────────────────┘
       │
       ▼
┌─────────────────────────────────┐
│ 后端验证 path 是否正确           │
│ ✓ 正确 → 继续秒杀逻辑           │
│ ✗ 错误 → 返回"非法的秒杀路径"   │
└─────────────────────────────────┘
```

### 8.4 秒杀路径设计要点

| 要点 | 说明 |
|------|------|
| path 是**后端生成**的 | 前端无法预测，只能请求获取 |
| path 存在 Redis | 带过期时间（5 分钟），超时失效 |
| path 与用户绑定 | `userId:productId` 作为 key，不能跨用户复用 |
| 先有 path 才能秒杀 | 没有 path 的请求直接被拦截 |

这样设计，攻击者就算抓包分析了接口，每次的 path 都不一样，而且会过期，大大增加了攻击成本。
