package com.eva.evaexchangeapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
@OpenAPIDefinition(
    info =
    @Info(
        title = "EVA EXCHANGE API",
        version = "1.0",
        contact =
        @Contact(
            name = "EVA",
            email = "akglzfr@gmail.com",
            url = "akglzfr@gmail.com"),
        license =
        @License(
            name = "2024 2024 2024")),

    servers =
        {@Server(url = "http://localhost:9080/evaexchangeapi", description = "Eva Backend Case Swagger")}
)
public class SwaggerConfiguration {

  @Bean
  public OperationCustomizer customGlobalHeaders() {

    return (Operation operation, HandlerMethod handlerMethod) -> {
      return operation;
    };
  }
}
