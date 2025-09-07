package com.enterprise.integrated.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger API文档配置
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("企业级集成服务API")
                        .version("1.0.0")
                        .description("基于Spring Boot 3.x + JDK 21的企业级单体服务")
                        .contact(new Contact()
                                .name("Enterprise Team")
                                .email("team@enterprise.com")));
    }
}
