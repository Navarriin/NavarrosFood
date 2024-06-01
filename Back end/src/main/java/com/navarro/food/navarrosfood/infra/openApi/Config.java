package com.navarro.food.navarrosfood.infra.openApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI().info(
                new Info().description("Api de cadastro")
                        .version("1.0.0")
                        .title("Restaurante Navarro's Food.")
        );
    }
}
