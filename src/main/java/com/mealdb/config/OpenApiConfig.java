package com.mealdb.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mealDbExplorerAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MealDB Explorer API")
                        .description("API documentation for MealDB Explorer Task")
                        .version("1.0.0"));
    }
}

