#!/bin/bash

# 企业级集成服务启动脚本
# 基于Spring Boot 3.x + JDK 21

echo "=========================================="
echo "  企业级集成服务 (Integrated Services)"
echo "  基于Spring Boot 3.x + JDK 21"
echo "=========================================="

# 检查JDK版本
echo "检查JDK版本..."
java -version

# 检查Maven版本
echo "检查Maven版本..."
mvn -version

# 清理并编译项目
echo "清理并编译项目..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "编译失败，请检查代码错误"
    exit 1
fi

# 打包项目
echo "打包项目..."
mvn package -DskipTests

if [ $? -ne 0 ]; then
    echo "打包失败，请检查配置"
    exit 1
fi

echo "=========================================="
echo "项目构建完成！"
echo ""
echo "启动应用前请确保："
echo "1. MySQL 8.0+ 已启动"
echo "2. Redis 6.0+ 已启动"
echo "3. 已执行数据库初始化脚本："
echo "   mysql -u root -p < src/main/resources/sql/schema.sql"
echo "   mysql -u root -p < src/main/resources/sql/data.sql"
echo ""
echo "启动应用："
echo "java -jar target/integrated-services-1.0.0-SNAPSHOT.jar"
echo ""
echo "访问地址："
echo "- 应用地址: http://localhost:8080/api"
echo "- API文档: http://localhost:8080/api/doc.html"
echo "- 监控端点: http://localhost:8080/api/actuator"
echo "- Druid监控: http://localhost:8080/api/druid (admin/123456)"
echo "=========================================="
