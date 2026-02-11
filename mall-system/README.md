# Mall System 电商系统

一个前后端分离的电商商城系统，支持用户注册登录、商品浏览、购物车、订单管理等功能。

## 技术栈

### 后端
- **Spring Boot 2.7.18** - 基础框架
- **MyBatis Plus 3.5.3.1** - ORM框架，简化数据库操作
- **Spring Security** - 安全框架
- **JWT (jjwt 0.9.1)** - Token认证
- **BCrypt** - 密码加密
- **MySQL 8.0** - 数据库
- **Lombok** - 简化代码

### 前端
- **Vue 2.7.14** - 前端框架
- **Vue Router 3.6.5** - 路由管理
- **Vuex 3.6.2** - 状态管理
- **Element UI 2.15.14** - UI组件库
- **Axios 1.6.0** - HTTP请求

## 项目结构

```
mall-system/
├── mall-backend/                   # 后端项目
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/mall/
│       │   ├── MallApplication.java        # 启动类
│       │   ├── common/
│       │   │   └── Result.java             # 统一响应结果
│       │   ├── config/
│       │   │   └── SecurityConfig.java     # Security配置
│       │   ├── controller/
│       │   │   ├── UserController.java     # 用户接口
│       │   │   ├── ProductController.java  # 商品接口
│       │   │   ├── CategoryController.java # 分类接口
│       │   │   ├── CartController.java     # 购物车接口
│       │   │   └── OrderController.java    # 订单接口
│       │   ├── entity/
│       │   │   ├── User.java               # 用户实体
│       │   │   ├── Product.java            # 商品实体
│       │   │   ├── Category.java           # 分类实体
│       │   │   ├── Cart.java               # 购物车实体
│       │   │   ├── Order.java              # 订单实体
│       │   │   └── OrderItem.java          # 订单项实体
│       │   ├── mapper/                     # MyBatis Mapper接口
│       │   ├── service/                    # 业务接口
│       │   │   └── impl/                   # 业务实现
│       │   └── util/
│       │       └── JwtUtil.java            # JWT工具类
│       └── resources/
│           └── application.yml             # 配置文件
│
└── mall-frontend/                  # 前端项目
    ├── package.json
    ├── vue.config.js
    └── src/
        ├── main.js
        ├── App.vue
        ├── router/
        │   └── index.js                    # 路由配置
        ├── store/
        │   └── index.js                    # Vuex状态管理
        └── views/
            ├── Home.vue                    # 首页
            ├── Login.vue                   # 登录页
            ├── Register.vue                # 注册页
            ├── Products.vue                # 商品列表
            ├── ProductDetail.vue           # 商品详情
            ├── Cart.vue                    # 购物车
            └── Orders.vue                  # 我的订单
```

## 数据库设计

### 用户表 (user)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| username | VARCHAR(50) | 用户名，唯一 |
| password | VARCHAR(100) | 密码(BCrypt加密) |
| email | VARCHAR(100) | 邮箱 |
| phone | VARCHAR(20) | 手机号 |
| status | INT | 状态(1正常,0禁用) |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 分类表 (category)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| name | VARCHAR(50) | 分类名称 |
| parent_id | BIGINT | 父分类ID(支持多级分类) |
| sort_order | INT | 排序序号 |
| status | INT | 状态(1启用,0禁用) |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 商品表 (product)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| name | VARCHAR(200) | 商品名称 |
| category_id | BIGINT | 分类ID |
| price | DECIMAL(10,2) | 价格 |
| stock | INT | 库存数量 |
| description | TEXT | 商品描述 |
| image | VARCHAR(255) | 商品图片URL |
| status | INT | 状态(1上架,0下架) |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 购物车表 (cart)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| user_id | BIGINT | 用户ID |
| product_id | BIGINT | 商品ID |
| quantity | INT | 数量 |
| checked | INT | 是否选中(1是,0否) |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 订单表 (order)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| order_no | VARCHAR(50) | 订单编号 |
| user_id | BIGINT | 用户ID |
| total_amount | DECIMAL(10,2) | 订单总金额 |
| payment_amount | DECIMAL(10,2) | 实付金额 |
| status | INT | 订单状态 |
| payment_time | DATETIME | 支付时间 |
| delivery_time | DATETIME | 发货时间 |
| receiver_name | VARCHAR(50) | 收货人姓名 |
| receiver_phone | VARCHAR(20) | 收货人电话 |
| receiver_address | VARCHAR(255) | 收货地址 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 订单项表 (order_item)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| order_id | BIGINT | 订单ID |
| product_id | BIGINT | 商品ID |
| product_name | VARCHAR(200) | 商品名称(快照) |
| product_image | VARCHAR(255) | 商品图片(快照) |
| price | DECIMAL(10,2) | 商品单价(快照) |
| quantity | INT | 购买数量 |
| total_price | DECIMAL(10,2) | 小计金额 |
| create_time | DATETIME | 创建时间 |

## API 接口设计

### 用户模块 `/api/user`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| POST | /login | 用户登录 | 否 |
| POST | /register | 用户注册 | 否 |
| GET | /info/{id} | 获取用户信息 | 否 |

### 分类模块 `/api/category`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| GET | /list | 获取所有分类 | 否 |
| GET | /{id} | 获取分类详情 | 否 |

### 商品模块 `/api/product`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| GET | /list | 获取所有商品 | 否 |
| GET | /page | 分页获取商品(支持分类和关键字筛选) | 否 |
| GET | /{id} | 获取商品详情 | 否 |
| GET | /category/{categoryId} | 按分类获取商品 | 否 |
| POST | /add | 添加商品 | 否 |
| PUT | /update | 更新商品 | 否 |
| DELETE | /delete/{id} | 删除商品 | 否 |

### 购物车模块 `/api/cart`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| GET | /list/{userId} | 获取用户购物车 | 是 |
| POST | /add | 添加商品到购物车 | 是 |
| PUT | /quantity/{id} | 更新商品数量 | 是 |
| PUT | /checked/{id} | 更新选中状态 | 是 |
| DELETE | /delete/{id} | 删除购物车项 | 是 |
| DELETE | /clear/{userId} | 清空购物车 | 是 |

### 订单模块 `/api/order`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| GET | /list/{userId} | 获取用户订单列表 | 是 |
| GET | /{id} | 获取订单详情 | 是 |
| GET | /no/{orderNo} | 按订单号查询 | 是 |
| POST | /create | 创建订单 | 是 |
| PUT | /pay/{id} | 支付订单 | 是 |
| PUT | /deliver/{id} | 发货 | 是 |
| PUT | /cancel/{id} | 取消订单 | 是 |

## 核心功能实现

### 1. 用户认证流程
1. 用户登录/注册时，后端验证凭据
2. 验证成功后生成JWT Token返回给前端
3. 前端将Token存储在localStorage中
4. 后续请求通过Authorization头携带Token
5. 后端通过JwtUtil验证Token有效性

### 2. 购物车功能
1. 用户可以将商品添加到购物车
2. 可以修改商品数量和选中状态
3. 购物车关联商品信息，展示商品详情
4. 支持删除单个商品或清空购物车

### 3. 订单流程
1. **创建订单**: 从购物车选中商品生成订单
2. **订单状态流转**:
   - 0: 待支付
   - 1: 已支付
   - 2: 已发货
   - 3: 已完成
   - 4: 已取消
3. **订单快照**: 下单时保存商品名称、图片、价格等快照信息
4. **支付/发货/取消**: 通过API更新订单状态

### 4. 商品管理
1. 支持按分类筛选商品
2. 支持关键字搜索
3. 支持分页查询
4. 商品的增删改查操作

## 配置说明

### 后端配置 (application.yml)
```yaml
server:
  port: 8080                    # 服务端口

spring:
  datasource:
    url: jdbc:mysql://localhost:3307/mall?...
    username: root
    password: 123456

jwt:
  secret: mallSecretKey123456789012345678901234567890
  expiration: 86400000          # Token有效期(24小时)
```

### 前端配置 (vue.config.js)
```javascript
module.exports = {
  devServer: {
    port: 8081,                 # 前端开发服务器端口
    proxy: {
      '/api': {
        target: 'http://localhost:8080'  # 后端API地址
      }
    }
  }
}
```

## 快速开始

### 1. 初始化数据库
确保MySQL数据库运行在localhost:3307，创建mall数据库和相应表。

### 2. 启动后端
```bash
cd mall-backend
mvn spring-boot:run
```

### 3. 启动前端
```bash
cd mall-frontend
npm install
npm run serve
```

### 4. 访问应用
打开浏览器访问 http://localhost:8081

## 设计思路总结

### 为什么选择这套技术栈？

1. **Spring Boot + MyBatis Plus**
   - Spring Boot简化了配置和部署
   - MyBatis Plus提供了强大的CRUD功能
   - 适合快速开发电商业务

2. **Vue 2 + Element UI**
   - Vue 2生态成熟稳定
   - Element UI组件丰富，适合后台系统
   - 开发效率高

3. **JWT认证**
   - 无状态认证，易于扩展
   - 适合前后端分离架构

### 架构设计原则

1. **分层架构**: Controller -> Service -> Mapper，职责清晰
2. **统一响应**: Result类封装所有API响应
3. **数据快照**: 订单项保存下单时的商品信息，防止商品修改影响历史订单
4. **安全设计**: 密码BCrypt加密，敏感操作需登录验证

### 业务特点

1. **多级分类**: category表通过parent_id支持多级分类
2. **购物车选中**: cart表checked字段支持部分结算
3. **订单状态机**: 完整的订单状态流转
4. **库存管理**: 商品库存扣减逻辑

### 扩展性考虑

1. 可以添加商品评价功能
2. 可以添加优惠券系统
3. 可以添加物流追踪
4. 可以添加支付集成(支付宝/微信)
5. 可以添加后台管理系统
6. 可以使用Redis缓存热点数据
