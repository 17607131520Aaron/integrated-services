# 企业级集成服务 (Integrated Services)

基于 Spring Boot 3.x + JDK 21 的企业级Java单体服务架构，采用阿里、腾讯等大厂成熟的分层架构模式。

## 🏗️ 技术架构

### 核心技术栈
- **JDK**: 21
- **Spring Boot**: 3.2.0
- **Spring Framework**: 6.x
- **MyBatis Plus**: 3.5.4.1
- **MySQL**: 8.0.33
- **Redis**: 基于 Lettuce 连接池
- **Druid**: 1.2.20 (数据库连接池)

### 企业级组件
- **API文档**: Knife4j 4.4.0 (Swagger 3.0)
- **工具库**: Hutool 5.8.23
- **JSON处理**: FastJSON2 2.0.43
- **缓存**: Redisson 3.24.3
- **监控**: Spring Boot Actuator + Micrometer + Prometheus
- **日志**: Logback (Spring Boot 默认)

## 📁 项目结构

```
integrated-services/
├── src/main/java/com/enterprise/integrated/
│   ├── IntegratedServicesApplication.java    # 主启动类
│   ├── common/                               # 通用组件
│   │   ├── BaseEntity.java                   # 基础实体类
│   │   ├── Constants.java                    # 系统常量
│   │   ├── PageQuery.java                    # 分页查询参数
│   │   ├── RedisUtils.java                   # Redis工具类
│   │   ├── Result.java                       # 统一响应结果
│   │   └── ResultCode.java                   # 响应状态码枚举
│   ├── config/                               # 配置类
│   │   ├── MetaObjectHandler.java            # MyBatis Plus 自动填充
│   │   ├── MybatisPlusConfig.java            # MyBatis Plus 配置
│   │   ├── SwaggerConfig.java                # API文档配置
│   │   └── WebConfig.java                    # Web配置
│   ├── controller/                           # 控制层
│   │   └── UserController.java               # 用户管理控制器
│   ├── dao/                                  # 数据访问层
│   │   └── UserMapper.java                   # 用户数据访问接口
│   ├── dto/                                  # 数据传输对象
│   │   └── UserDTO.java                      # 用户DTO
│   ├── entity/                               # 实体类
│   │   └── User.java                         # 用户实体
│   ├── exception/                            # 异常处理
│   │   ├── BusinessException.java            # 业务异常
│   │   └── GlobalExceptionHandler.java       # 全局异常处理器
│   └── service/                              # 业务逻辑层
│       ├── UserService.java                  # 用户服务接口
│       └── impl/
│           └── UserServiceImpl.java          # 用户服务实现
├── src/main/resources/
│   ├── mapper/                               # MyBatis XML映射文件
│   │   └── UserMapper.xml
│   ├── sql/                                  # 数据库脚本
│   │   ├── schema.sql                        # 建表脚本
│   │   └── data.sql                          # 初始化数据
│   ├── application.yml                       # 主配置文件
│   ├── application-dev.yml                   # 开发环境配置
│   └── application-prod.yml                  # 生产环境配置
└── pom.xml                                   # Maven依赖配置
```

## 🚀 快速开始

### 环境要求
- JDK 21+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 1. 克隆项目
```bash
git clone <repository-url>
cd integrated-services
```

### 2. 数据库初始化
```bash
# 创建数据库
mysql -u root -p < src/main/resources/sql/schema.sql

# 导入初始化数据
mysql -u root -p < src/main/resources/sql/data.sql
```

### 3. 修改配置
编辑 `src/main/resources/application-dev.yml`，修改数据库和Redis连接信息：

```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://localhost:3306/integrated_services_dev
      username: your_username
      password: your_password
  data:
    redis:
      host: localhost
      port: 6379
      password: your_redis_password
```

### 4. 启动应用
```bash
# 使用Maven启动
mvn spring-boot:run

# 或者打包后启动
mvn clean package
java -jar target/integrated-services-1.0.0-SNAPSHOT.jar
```

### 5. 访问应用
- **应用地址**: http://localhost:8080/api
- **API文档**: http://localhost:8080/api/doc.html
- **监控端点**: http://localhost:8080/api/actuator
- **Druid监控**: http://localhost:8080/api/druid (admin/123456)

## 📊 核心功能

### 用户管理模块
- ✅ 用户CRUD操作
- ✅ 分页查询
- ✅ 参数校验
- ✅ 密码加密 (BCrypt)
- ✅ 状态管理
- ✅ 批量操作

### 系统特性
- ✅ 统一响应格式
- ✅ 全局异常处理
- ✅ 参数校验
- ✅ 分页查询
- ✅ 乐观锁
- ✅ 逻辑删除
- ✅ 自动填充 (创建时间、更新时间等)
- ✅ Redis缓存支持
- ✅ 数据库连接池监控
- ✅ 系统监控指标

## 🔧 配置说明

### 数据库配置
项目使用Druid连接池，支持：
- 连接池监控
- SQL监控
- 慢SQL检测
- 防火墙功能

### 缓存配置
集成Redis和Redisson：
- 支持分布式锁
- 支持分布式缓存
- 连接池管理

### 监控配置
集成Spring Boot Actuator：
- 健康检查: `/actuator/health`
- 应用信息: `/actuator/info`
- 指标监控: `/actuator/metrics`
- Prometheus: `/actuator/prometheus`

## 🏢 企业级特性

### 分层架构
- **Controller层**: 接收请求，参数校验，调用Service
- **Service层**: 业务逻辑处理，事务管理
- **DAO层**: 数据访问，SQL操作
- **Entity层**: 数据实体，映射数据库表

### 代码规范
- 统一的异常处理机制
- 标准的响应格式
- 完善的参数校验
- 详细的API文档
- 规范的日志记录

### 安全特性
- 密码加密存储
- SQL注入防护
- 参数校验防护
- 敏感信息脱敏

## 📝 开发指南

### 添加新模块
1. 创建Entity实体类
2. 创建DTO传输对象
3. 创建Mapper接口和XML
4. 创建Service接口和实现
5. 创建Controller控制器
6. 编写单元测试

### 数据库操作
使用MyBatis Plus提供的BaseMapper，支持：
- 基础CRUD操作
- 条件构造器查询
- 分页查询
- 批量操作

### 缓存使用
```java
@Autowired
private RedisUtils redisUtils;

// 设置缓存
redisUtils.set("key", value, 3600);

// 获取缓存
Object value = redisUtils.get("key");
```

## 🧪 测试

### 默认测试用户
| 用户名 | 密码 | 角色 | 描述 |
|--------|------|------|------|
| admin | 123456 | 超级管理员 | 拥有所有权限 |
| system | 123456 | 系统管理员 | 系统管理权限 |
| zhangsan | 123456 | 部门经理 | 部门管理权限 |
| lisi | 123456 | 普通用户 | 基本操作权限 |

### API测试
使用Knife4j进行API测试：
1. 访问 http://localhost:8080/api/doc.html
2. 选择对应的接口
3. 填入参数进行测试

## 📈 性能优化

### 数据库优化
- 使用Druid连接池
- 配置合理的连接池参数
- 开启SQL监控和慢查询检测
- 使用索引优化查询

### 缓存优化
- Redis缓存热点数据
- 合理设置缓存过期时间
- 使用分布式锁避免缓存击穿

### JVM优化
- 使用JDK 21的新特性
- 配置合理的堆内存参数
- 开启GC日志监控

## 🔄 部署

### 开发环境
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 生产环境
```bash
java -jar -Dspring.profiles.active=prod integrated-services.jar
```

### Docker部署
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/integrated-services-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📞 技术支持

如有问题，请提交Issue或联系开发团队。

## 📄 许可证

本项目采用 MIT 许可证。