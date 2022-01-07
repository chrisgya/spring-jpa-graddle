package com.chrisgya.springjpagraddle.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Experimental APIs",
                description = "Building standard APIs with API first approach",
                termsOfService = "",
                license = @License(
                        name = "MIT License",
                        url = ""
                ),
                contact = @Contact(
                        name = "Christian Gyaban",
                        email = "chrisgya500@gmail.com",
                        url = "https://chrisgya-solutions.com"
                )
        )
)
@SecurityScheme(name = "api", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in= SecuritySchemeIn.HEADER)
public class SwaggerConfig {
}
