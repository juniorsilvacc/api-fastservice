package com.juniorsilvacc.fastservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "bearer"
)
public class OpenApiConfig {
	
	final String securitySchemeName = "bearerAuth";
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
			.info(new Info()
				.title("RESTFul API - Fast Service")
				.version("v1")
				.description("Some description about your API")
				.termsOfService("https://fastservice.com.br")
					.license(
						new License().name("Apache 2.0")
						.url("https://fastservice.com.br")
					)
				);
	}

}
