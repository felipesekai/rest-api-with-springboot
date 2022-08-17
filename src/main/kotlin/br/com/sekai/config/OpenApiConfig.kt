package br.com.sekai.config


import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Restful API with kotlin and spring boot")
                    .version("v1")
                    .description("Some description about your API")
                    .termsOfService("leia aqui")
                    .license(
                        License().name("SEkai")
                    )
            )
    }
}