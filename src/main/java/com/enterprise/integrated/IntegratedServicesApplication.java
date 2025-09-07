package com.enterprise.integrated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 企业级集成服务主启动类
 * 
 * @author Enterprise Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
public class IntegratedServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratedServicesApplication.class, args);
    }
}
