package com.netprodex.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI getOpenApiDefinition() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Netprodex")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Joseph Magallanes")
                                .email("josephcarlos.jcmn@gmail.com")
                                .url("https://www.linkedin.com/in/joseph-magallanes-nolazco/")
                        )
                        .description("Application Backend NetProdex with BD PostgreSQL")
                );
    }
}
