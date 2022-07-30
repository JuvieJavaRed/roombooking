package com.coindirect.recruitment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Value("${swagger.application.version}")
    private String applicationVersion;

    @Value("${swagger.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title(applicationName)
                        .description("Booking System")
                        .version(applicationVersion)
                        .contact(new Contact()
                                .name("BNVK")));
    }
}
