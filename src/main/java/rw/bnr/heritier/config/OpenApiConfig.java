package rw.bnr.heritier.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : OpenApiConfig.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Configures OpenAPI and Swagger documentation
 * --------------------------------------------------------------------
 */

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .info(
                        new Info()
                                .title(
                                        "Bank Licensing & Compliance Portal API"
                                )
                                .version("1.0.0")
                                .description(
                                        "REST APIs for managing licensing applications, workflow approvals, audit logs, and compliance operations."
                                )
                                .contact(
                                        new Contact()
                                                .name("Heritier Ntaganira")
                                                .email("ntaganira71@gmail.com")
                                )
                                .license(
                                        new License()
                                                .name("Internal Assessment License")
                                )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME_NAME)
                )

                .schemaRequirement(
                        SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }

}