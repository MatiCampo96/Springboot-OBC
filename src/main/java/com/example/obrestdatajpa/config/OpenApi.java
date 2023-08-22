package com.example.obrestdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


/**
 * Configuracion Swagger para la generación de documentación de la API REST
 * 
 * HTML: http://localhost:8080/doc/swagger-ui/index.html
 * JSON: http://localhost:8080/v3/api-docs
 */
//@EnableSwagger2

@Configuration
public class OpenApi {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                .title("Heladeria Rojelio")
                .version("1.0")
                .description("Los mejores helados en java")
                .termsOfService("https://abc.com/123")
                .license(new License().name("MIT").url("https://abc.com/456")));
    }
    
    
}
