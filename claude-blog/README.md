# Claude Blog 博客系统

一个前后端分离的博客系统，支持用户注册登录、发布文章、评论和收藏等功能。

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
- **mavon-editor 2.10.4** - Markdown编辑器
- **marked 4.3.0** - Markdown解析

## 项目结构

```
claude-blog/
├── sql/
│   └── init.sql                    # 数据库初始化脚本
│
├── blog-backend/                   # 后端项目
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/blog/
│       │   ├── BlogApplication.java        # 启动类
│       │   ├── common/
│       │   │   └── Result.java             # 统一响应结果
│       │   ├── config/
│       │   │   ├── SecurityConfig.java     # Security配置
│       │   │   └── WebConfig.java          # CORS配置
│       │   ├── controller/
│       │   │   ├── UserController.java     # 用户接口
│       │   │   ├── PostController.java     # 文章接口
│       │   │   ├── CommentController.java  # 评论接口
│       │   │   └── FavoriteController.java # 收藏接口
│       │   ├── entity/
│       │   │   ├── User.java               # 用户实体
│       │   │   ├── Post.java               # 文章实体
│       │   │   ├── Comment.java            # 评论实体
│       │   │   └── Favorite.java           # 收藏实体
│       │   ├── mapper/                     # MyBatis Mapper接口
│       │   ├── service/                    # 业务接口
│       │   │   └── impl/                   # 业务实现
│       │   └── util/
│       │       └── JwtUtil.java            # JWT工具类
│       └── resources/
│           └── application.yml             # 配置文件
│
└── blog-frontend/                  # 前端项目
    ├── package.json
    ├── vue.config.js
    └── src/
        ├── main.js
        ├── App.vue
        ├── api/
        │   └── index.js                    # API封装
        ├── router/
        │   └── index.js                    # 路由配置
        ├── store/
        │   └── index.js                    # Vuex状态管理
        └── views/
            ├── Home.vue                    # 首页(文章列表)
            ├── Login.vue                   # 登录页
            ├── Register.vue                # 注册页
            ├── PostDetail.vue              # 文章详情
            ├── CreatePost.vue              # 发布/编辑文章
            ├── MyPosts.vue                 # 我的文章
            ├── Favorites.vue               # 我的收藏
            └── Profile.vue                 # 个人中心
```

## 数据库设计

### 用户表 (user)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| username | VARCHAR(50) | 用户名，唯一 |
| password | VARCHAR(100) | 密码(BCrypt加密) |
| email | VARCHAR(100) | 邮箱 |
| nickname | VARCHAR(50) | 昵称 |
| avatar | VARCHAR(255) | 头像URL |
| status | INT | 状态(1正常,0禁用) |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 文章表 (post)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| title | VARCHAR(200) | 标题 |
| content | TEXT | 内容(Markdown) |
| summary | VARCHAR(500) | 摘要 |
| author_id | BIGINT | 作者ID |
| view_count | INT | 浏览量 |
| like_count | INT | 点赞数 |
| comment_count | INT | 评论数 |
| status | INT | 状态(1已发布,0草稿) |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 评论表 (comment)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| post_id | BIGINT | 文章ID |
| user_id | BIGINT | 评论用户ID |
| parent_id | BIGINT | 父评论ID(支持回复) |
| reply_user_id | BIGINT | 回复用户ID |
| content | VARCHAR(1000) | 评论内容 |
| create_time | DATETIME | 创建时间 |

### 收藏表 (favorite)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| user_id | BIGINT | 用户ID |
| post_id | BIGINT | 文章ID |
| create_time | DATETIME | 收藏时间 |

## API 接口设计

### 用户模块 `/api/user`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| POST | /login | 用户登录 | 否 |
| POST | /register | 用户注册 | 否 |
| GET | /info/{id} | 获取用户信息 | 否 |
| PUT | /update | 更新用户信息 | 是 |

### 文章模块 `/api/post`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| GET | /list | 获取文章列表(分页) | 否 |
| GET | /{id} | 获取文章详情 | 否 |
| POST | / | 发布文章 | 是 |
| PUT | /{id} | 更新文章 | 是 |
| DELETE | /{id} | 删除文章 | 是 |
| GET | /my | 获取我的文章 | 是 |

### 评论模块 `/api/comment`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| GET | /list/{postId} | 获取文章评论 | 否 |
| POST | / | 发表评论 | 是 |
| DELETE | /{id} | 删除评论 | 是 |

### 收藏模块 `/api/favorite`
| 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|
| POST | /{postId} | 收藏文章 | 是 |
| DELETE | /{postId} | 取消收藏 | 是 |
| GET | /list | 获取我的收藏 | 是 |
| GET | /check/{postId} | 检查是否已收藏 | 是 |

## 核心功能实现

### 1. 用户认证流程
1. 用户登录/注册时，后端验证凭据
2. 验证成功后生成JWT Token返回给前端
3. 前端将Token存储在localStorage中
4. 后续请求通过Authorization头携带Token
5. 后端通过JwtUtil验证Token有效性

### 2. 文章发布流程
1. 用户使用Markdown编辑器编写文章
2. 提交时自动生成摘要(截取前200字符)
3. 支持发布和保存草稿两种状态
4. 只有作者可以编辑和删除自己的文章

### 3. 评论系统
1. 支持对文章的直接评论
2. 支持对评论的回复(通过parent_id关联)
3. 前端构建树形结构展示评论和回复
4. 只有评论者可以删除自己的评论

### 4. 收藏功能
1. 使用user_id + post_id唯一索引防止重复收藏
2. 在文章详情页显示收藏状态
3. 用户可以在收藏列表管理已收藏的文章

## 配置说明

### 后端配置 (application.yml)
```yaml
server:
  port: 25000                    # 服务端口

spring:
  datasource:
    url: jdbc:mysql://localhost:3307/claude_blog?...
    username: root
    password: 123456

jwt:
  secret: claudeBlogSecretKey...  # JWT密钥
  expiration: 86400000            # Token有效期(24小时)
```

### 前端配置 (vue.config.js)
```javascript
module.exports = {
  devServer: {
    port: 8082,                   # 前端开发服务器端口
    proxy: {
      '/api': {
        target: 'http://localhost:25000'  # 后端API地址
      }
    }
  }
}
```

## 快速开始

### 1. 初始化数据库
```bash
mysql -h localhost -P 3307 -u root -p < sql/init.sql
```

### 2. 启动后端
```bash
cd blog-backend
mvn spring-boot:run
```

### 3. 启动前端
```bash
cd blog-frontend
npm install
npm run serve
```

### 4. 访问应用
打开浏览器访问 http://localhost:8082

### 测试账号
- 用户名: `admin` 或 `demo`
- 密码: `123456`

## 设计思路总结

### 为什么选择这套技术栈？

1. **Spring Boot + MyBatis Plus**
   - Spring Boot简化了配置和部署
   - MyBatis Plus提供了强大的CRUD功能和代码生成能力
   - 两者结合可以快速开发RESTful API

2. **Vue 2 + Element UI**
   - Vue 2生态成熟稳定，社区资源丰富
   - Element UI提供了美观的组件和完善的文档
   - 适合快速构建企业级后台应用

3. **JWT认证**
   - 无状态认证，服务端不需要存储Session
   - 适合前后端分离架构
   - Token可以携带用户信息

4. **Markdown编辑器**
   - 博客文章适合使用Markdown格式
   - mavon-editor提供了丰富的编辑功能
   - marked用于渲染Markdown内容

### 架构设计原则

1. **分层架构**: Controller -> Service -> Mapper，职责清晰
2. **统一响应**: Result类封装所有API响应，格式一致
3. **安全设计**: 密码BCrypt加密，敏感操作需登录验证
4. **用户体验**: 路由守卫保护需登录页面，友好的错误提示

### 扩展性考虑

1. 可以添加Redis缓存热门文章
2. 可以添加全文搜索功能(如Elasticsearch)
3. 可以添加文件上传功能支持图片
4. 可以添加标签和分类功能
5. 可以添加关注和私信功能
